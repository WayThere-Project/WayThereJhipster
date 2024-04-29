package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.RateAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Rate;
import com.mycompany.myapp.repository.RateRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RateResourceIT {

    private static final Long DEFAULT_RATE_ID = 1L;
    private static final Long UPDATED_RATE_ID = 2L;

    private static final Integer DEFAULT_TIME_OF_DAY = 1;
    private static final Integer UPDATED_TIME_OF_DAY = 2;

    private static final Integer DEFAULT_VEHICLE_SIZE = 1;
    private static final Integer UPDATED_VEHICLE_SIZE = 2;

    private static final Integer DEFAULT_BASE_RATE = 1;
    private static final Integer UPDATED_BASE_RATE = 2;

    private static final String ENTITY_API_URL = "/api/rates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRateMockMvc;

    private Rate rate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rate createEntity(EntityManager em) {
        Rate rate = new Rate()
            .rateID(DEFAULT_RATE_ID)
            .timeOfDay(DEFAULT_TIME_OF_DAY)
            .vehicleSize(DEFAULT_VEHICLE_SIZE)
            .baseRate(DEFAULT_BASE_RATE);
        return rate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rate createUpdatedEntity(EntityManager em) {
        Rate rate = new Rate()
            .rateID(UPDATED_RATE_ID)
            .timeOfDay(UPDATED_TIME_OF_DAY)
            .vehicleSize(UPDATED_VEHICLE_SIZE)
            .baseRate(UPDATED_BASE_RATE);
        return rate;
    }

    @BeforeEach
    public void initTest() {
        rate = createEntity(em);
    }

    @Test
    @Transactional
    void createRate() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Rate
        var returnedRate = om.readValue(
            restRateMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rate)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Rate.class
        );

        // Validate the Rate in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRateUpdatableFieldsEquals(returnedRate, getPersistedRate(returnedRate));
    }

    @Test
    @Transactional
    void createRateWithExistingId() throws Exception {
        // Create the Rate with an existing ID
        rate.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rate)))
            .andExpect(status().isBadRequest());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRates() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        // Get all the rateList
        restRateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rate.getId().intValue())))
            .andExpect(jsonPath("$.[*].rateID").value(hasItem(DEFAULT_RATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].timeOfDay").value(hasItem(DEFAULT_TIME_OF_DAY)))
            .andExpect(jsonPath("$.[*].vehicleSize").value(hasItem(DEFAULT_VEHICLE_SIZE)))
            .andExpect(jsonPath("$.[*].baseRate").value(hasItem(DEFAULT_BASE_RATE)));
    }

    @Test
    @Transactional
    void getRate() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        // Get the rate
        restRateMockMvc
            .perform(get(ENTITY_API_URL_ID, rate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rate.getId().intValue()))
            .andExpect(jsonPath("$.rateID").value(DEFAULT_RATE_ID.intValue()))
            .andExpect(jsonPath("$.timeOfDay").value(DEFAULT_TIME_OF_DAY))
            .andExpect(jsonPath("$.vehicleSize").value(DEFAULT_VEHICLE_SIZE))
            .andExpect(jsonPath("$.baseRate").value(DEFAULT_BASE_RATE));
    }

    @Test
    @Transactional
    void getNonExistingRate() throws Exception {
        // Get the rate
        restRateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRate() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rate
        Rate updatedRate = rateRepository.findById(rate.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRate are not directly saved in db
        em.detach(updatedRate);
        updatedRate.rateID(UPDATED_RATE_ID).timeOfDay(UPDATED_TIME_OF_DAY).vehicleSize(UPDATED_VEHICLE_SIZE).baseRate(UPDATED_BASE_RATE);

        restRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRate.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRate))
            )
            .andExpect(status().isOk());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRateToMatchAllProperties(updatedRate);
    }

    @Test
    @Transactional
    void putNonExistingRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rate.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRateMockMvc
            .perform(put(ENTITY_API_URL_ID, rate.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rate)))
            .andExpect(status().isBadRequest());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rate))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRateMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rate)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRateWithPatch() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rate using partial update
        Rate partialUpdatedRate = new Rate();
        partialUpdatedRate.setId(rate.getId());

        partialUpdatedRate.rateID(UPDATED_RATE_ID);

        restRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRate))
            )
            .andExpect(status().isOk());

        // Validate the Rate in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRateUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedRate, rate), getPersistedRate(rate));
    }

    @Test
    @Transactional
    void fullUpdateRateWithPatch() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rate using partial update
        Rate partialUpdatedRate = new Rate();
        partialUpdatedRate.setId(rate.getId());

        partialUpdatedRate
            .rateID(UPDATED_RATE_ID)
            .timeOfDay(UPDATED_TIME_OF_DAY)
            .vehicleSize(UPDATED_VEHICLE_SIZE)
            .baseRate(UPDATED_BASE_RATE);

        restRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRate))
            )
            .andExpect(status().isOk());

        // Validate the Rate in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRateUpdatableFieldsEquals(partialUpdatedRate, getPersistedRate(partialUpdatedRate));
    }

    @Test
    @Transactional
    void patchNonExistingRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rate.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRateMockMvc
            .perform(patch(ENTITY_API_URL_ID, rate.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rate)))
            .andExpect(status().isBadRequest());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rate))
            )
            .andExpect(status().isBadRequest());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rate.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRateMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rate)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Rate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRate() throws Exception {
        // Initialize the database
        rateRepository.saveAndFlush(rate);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rate
        restRateMockMvc
            .perform(delete(ENTITY_API_URL_ID, rate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rateRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Rate getPersistedRate(Rate rate) {
        return rateRepository.findById(rate.getId()).orElseThrow();
    }

    protected void assertPersistedRateToMatchAllProperties(Rate expectedRate) {
        assertRateAllPropertiesEquals(expectedRate, getPersistedRate(expectedRate));
    }

    protected void assertPersistedRateToMatchUpdatableProperties(Rate expectedRate) {
        assertRateAllUpdatablePropertiesEquals(expectedRate, getPersistedRate(expectedRate));
    }
}

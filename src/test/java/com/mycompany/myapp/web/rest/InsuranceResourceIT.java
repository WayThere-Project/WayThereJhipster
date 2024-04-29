package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.InsuranceAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Insurance;
import com.mycompany.myapp.repository.InsuranceRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link InsuranceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceResourceIT {

    private static final Long DEFAULT_INS_ID = 1L;
    private static final Long UPDATED_INS_ID = 2L;

    private static final String DEFAULT_INS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_INS_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EXPIRATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPIRATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/insurances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceMockMvc;

    private Insurance insurance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Insurance createEntity(EntityManager em) {
        Insurance insurance = new Insurance()
            .insID(DEFAULT_INS_ID)
            .insDescription(DEFAULT_INS_DESCRIPTION)
            .startDate(DEFAULT_START_DATE)
            .expirationDate(DEFAULT_EXPIRATION_DATE);
        return insurance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Insurance createUpdatedEntity(EntityManager em) {
        Insurance insurance = new Insurance()
            .insID(UPDATED_INS_ID)
            .insDescription(UPDATED_INS_DESCRIPTION)
            .startDate(UPDATED_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE);
        return insurance;
    }

    @BeforeEach
    public void initTest() {
        insurance = createEntity(em);
    }

    @Test
    @Transactional
    void createInsurance() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Insurance
        var returnedInsurance = om.readValue(
            restInsuranceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insurance)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Insurance.class
        );

        // Validate the Insurance in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertInsuranceUpdatableFieldsEquals(returnedInsurance, getPersistedInsurance(returnedInsurance));
    }

    @Test
    @Transactional
    void createInsuranceWithExistingId() throws Exception {
        // Create the Insurance with an existing ID
        insurance.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insurance)))
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsurances() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        // Get all the insuranceList
        restInsuranceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].insID").value(hasItem(DEFAULT_INS_ID.intValue())))
            .andExpect(jsonPath("$.[*].insDescription").value(hasItem(DEFAULT_INS_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].expirationDate").value(hasItem(DEFAULT_EXPIRATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        // Get the insurance
        restInsuranceMockMvc
            .perform(get(ENTITY_API_URL_ID, insurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insurance.getId().intValue()))
            .andExpect(jsonPath("$.insID").value(DEFAULT_INS_ID.intValue()))
            .andExpect(jsonPath("$.insDescription").value(DEFAULT_INS_DESCRIPTION))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.expirationDate").value(DEFAULT_EXPIRATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingInsurance() throws Exception {
        // Get the insurance
        restInsuranceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the insurance
        Insurance updatedInsurance = insuranceRepository.findById(insurance.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedInsurance are not directly saved in db
        em.detach(updatedInsurance);
        updatedInsurance
            .insID(UPDATED_INS_ID)
            .insDescription(UPDATED_INS_DESCRIPTION)
            .startDate(UPDATED_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE);

        restInsuranceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInsurance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedInsurance))
            )
            .andExpect(status().isOk());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInsuranceToMatchAllProperties(updatedInsurance);
    }

    @Test
    @Transactional
    void putNonExistingInsurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insurance.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insurance.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insurance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insurance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(insurance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insurance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insurance)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceWithPatch() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the insurance using partial update
        Insurance partialUpdatedInsurance = new Insurance();
        partialUpdatedInsurance.setId(insurance.getId());

        partialUpdatedInsurance
            .insDescription(UPDATED_INS_DESCRIPTION)
            .startDate(UPDATED_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE);

        restInsuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsurance.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInsurance))
            )
            .andExpect(status().isOk());

        // Validate the Insurance in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInsuranceUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInsurance, insurance),
            getPersistedInsurance(insurance)
        );
    }

    @Test
    @Transactional
    void fullUpdateInsuranceWithPatch() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the insurance using partial update
        Insurance partialUpdatedInsurance = new Insurance();
        partialUpdatedInsurance.setId(insurance.getId());

        partialUpdatedInsurance
            .insID(UPDATED_INS_ID)
            .insDescription(UPDATED_INS_DESCRIPTION)
            .startDate(UPDATED_START_DATE)
            .expirationDate(UPDATED_EXPIRATION_DATE);

        restInsuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsurance.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInsurance))
            )
            .andExpect(status().isOk());

        // Validate the Insurance in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInsuranceUpdatableFieldsEquals(partialUpdatedInsurance, getPersistedInsurance(partialUpdatedInsurance));
    }

    @Test
    @Transactional
    void patchNonExistingInsurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insurance.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insurance.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(insurance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insurance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(insurance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insurance.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(insurance)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Insurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the insurance
        restInsuranceMockMvc
            .perform(delete(ENTITY_API_URL_ID, insurance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return insuranceRepository.count();
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

    protected Insurance getPersistedInsurance(Insurance insurance) {
        return insuranceRepository.findById(insurance.getId()).orElseThrow();
    }

    protected void assertPersistedInsuranceToMatchAllProperties(Insurance expectedInsurance) {
        assertInsuranceAllPropertiesEquals(expectedInsurance, getPersistedInsurance(expectedInsurance));
    }

    protected void assertPersistedInsuranceToMatchUpdatableProperties(Insurance expectedInsurance) {
        assertInsuranceAllUpdatablePropertiesEquals(expectedInsurance, getPersistedInsurance(expectedInsurance));
    }
}

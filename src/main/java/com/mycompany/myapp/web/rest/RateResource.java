package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Rate;
import com.mycompany.myapp.repository.RateRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Rate}.
 */
@RestController
@RequestMapping("/api/rates")
@Transactional
public class RateResource {

    private final Logger log = LoggerFactory.getLogger(RateResource.class);

    private static final String ENTITY_NAME = "rate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RateRepository rateRepository;

    public RateResource(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    /**
     * {@code POST  /rates} : Create a new rate.
     *
     * @param rate the rate to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rate, or with status {@code 400 (Bad Request)} if the rate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Rate> createRate(@RequestBody Rate rate) throws URISyntaxException {
        log.debug("REST request to save Rate : {}", rate);
        if (rate.getId() != null) {
            throw new BadRequestAlertException("A new rate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rate = rateRepository.save(rate);
        return ResponseEntity.created(new URI("/api/rates/" + rate.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rate.getId().toString()))
            .body(rate);
    }

    /**
     * {@code PUT  /rates/:id} : Updates an existing rate.
     *
     * @param id the id of the rate to save.
     * @param rate the rate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rate,
     * or with status {@code 400 (Bad Request)} if the rate is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(@PathVariable(value = "id", required = false) final Long id, @RequestBody Rate rate)
        throws URISyntaxException {
        log.debug("REST request to update Rate : {}, {}", id, rate);
        if (rate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rate.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rate = rateRepository.save(rate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rate.getId().toString()))
            .body(rate);
    }

    /**
     * {@code PATCH  /rates/:id} : Partial updates given fields of an existing rate, field will ignore if it is null
     *
     * @param id the id of the rate to save.
     * @param rate the rate to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rate,
     * or with status {@code 400 (Bad Request)} if the rate is not valid,
     * or with status {@code 404 (Not Found)} if the rate is not found,
     * or with status {@code 500 (Internal Server Error)} if the rate couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Rate> partialUpdateRate(@PathVariable(value = "id", required = false) final Long id, @RequestBody Rate rate)
        throws URISyntaxException {
        log.debug("REST request to partial update Rate partially : {}, {}", id, rate);
        if (rate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rate.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Rate> result = rateRepository
            .findById(rate.getId())
            .map(existingRate -> {
                if (rate.getRateID() != null) {
                    existingRate.setRateID(rate.getRateID());
                }
                if (rate.getTimeOfDay() != null) {
                    existingRate.setTimeOfDay(rate.getTimeOfDay());
                }
                if (rate.getVehicleSize() != null) {
                    existingRate.setVehicleSize(rate.getVehicleSize());
                }
                if (rate.getBaseRate() != null) {
                    existingRate.setBaseRate(rate.getBaseRate());
                }

                return existingRate;
            })
            .map(rateRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rate.getId().toString())
        );
    }

    /**
     * {@code GET  /rates} : get all the rates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rates in body.
     */
    @GetMapping("")
    public List<Rate> getAllRates() {
        log.debug("REST request to get all Rates");
        return rateRepository.findAll();
    }

    /**
     * {@code GET  /rates/:id} : get the "id" rate.
     *
     * @param id the id of the rate to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rate, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRate(@PathVariable("id") Long id) {
        log.debug("REST request to get Rate : {}", id);
        Optional<Rate> rate = rateRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(rate);
    }

    /**
     * {@code DELETE  /rates/:id} : delete the "id" rate.
     *
     * @param id the id of the rate to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable("id") Long id) {
        log.debug("REST request to delete Rate : {}", id);
        rateRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

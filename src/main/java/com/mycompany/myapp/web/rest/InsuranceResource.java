package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Insurance;
import com.mycompany.myapp.repository.InsuranceRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Insurance}.
 */
@RestController
@RequestMapping("/api/insurances")
@Transactional
public class InsuranceResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceResource.class);

    private static final String ENTITY_NAME = "insurance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceRepository insuranceRepository;

    public InsuranceResource(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    /**
     * {@code POST  /insurances} : Create a new insurance.
     *
     * @param insurance the insurance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insurance, or with status {@code 400 (Bad Request)} if the insurance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) throws URISyntaxException {
        log.debug("REST request to save Insurance : {}", insurance);
        if (insurance.getId() != null) {
            throw new BadRequestAlertException("A new insurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        insurance = insuranceRepository.save(insurance);
        return ResponseEntity.created(new URI("/api/insurances/" + insurance.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, insurance.getId().toString()))
            .body(insurance);
    }

    /**
     * {@code PUT  /insurances/:id} : Updates an existing insurance.
     *
     * @param id the id of the insurance to save.
     * @param insurance the insurance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insurance,
     * or with status {@code 400 (Bad Request)} if the insurance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insurance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Insurance insurance
    ) throws URISyntaxException {
        log.debug("REST request to update Insurance : {}, {}", id, insurance);
        if (insurance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insurance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        insurance = insuranceRepository.save(insurance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insurance.getId().toString()))
            .body(insurance);
    }

    /**
     * {@code PATCH  /insurances/:id} : Partial updates given fields of an existing insurance, field will ignore if it is null
     *
     * @param id the id of the insurance to save.
     * @param insurance the insurance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insurance,
     * or with status {@code 400 (Bad Request)} if the insurance is not valid,
     * or with status {@code 404 (Not Found)} if the insurance is not found,
     * or with status {@code 500 (Internal Server Error)} if the insurance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Insurance> partialUpdateInsurance(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Insurance insurance
    ) throws URISyntaxException {
        log.debug("REST request to partial update Insurance partially : {}, {}", id, insurance);
        if (insurance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insurance.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Insurance> result = insuranceRepository
            .findById(insurance.getId())
            .map(existingInsurance -> {
                if (insurance.getInsID() != null) {
                    existingInsurance.setInsID(insurance.getInsID());
                }
                if (insurance.getInsDescription() != null) {
                    existingInsurance.setInsDescription(insurance.getInsDescription());
                }
                if (insurance.getStartDate() != null) {
                    existingInsurance.setStartDate(insurance.getStartDate());
                }
                if (insurance.getExpirationDate() != null) {
                    existingInsurance.setExpirationDate(insurance.getExpirationDate());
                }

                return existingInsurance;
            })
            .map(insuranceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insurance.getId().toString())
        );
    }

    /**
     * {@code GET  /insurances} : get all the insurances.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insurances in body.
     */
    @GetMapping("")
    public List<Insurance> getAllInsurances(@RequestParam(name = "filter", required = false) String filter) {
        if ("reservation-is-null".equals(filter)) {
            log.debug("REST request to get all Insurances where reservation is null");
            return StreamSupport.stream(insuranceRepository.findAll().spliterator(), false)
                .filter(insurance -> insurance.getReservation() == null)
                .toList();
        }
        log.debug("REST request to get all Insurances");
        return insuranceRepository.findAll();
    }

    /**
     * {@code GET  /insurances/:id} : get the "id" insurance.
     *
     * @param id the id of the insurance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insurance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Insurance> getInsurance(@PathVariable("id") Long id) {
        log.debug("REST request to get Insurance : {}", id);
        Optional<Insurance> insurance = insuranceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(insurance);
    }

    /**
     * {@code DELETE  /insurances/:id} : delete the "id" insurance.
     *
     * @param id the id of the insurance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable("id") Long id) {
        log.debug("REST request to delete Insurance : {}", id);
        insuranceRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

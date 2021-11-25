package ua.pp.russian.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import ua.pp.russian.domain.MLog;
import ua.pp.russian.repository.MLogRepository;
import ua.pp.russian.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ua.pp.russian.domain.MLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MLogResource {

    private final Logger log = LoggerFactory.getLogger(MLogResource.class);

    private static final String ENTITY_NAME = "mLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MLogRepository mLogRepository;

    public MLogResource(MLogRepository mLogRepository) {
        this.mLogRepository = mLogRepository;
    }

    /**
     * {@code POST  /m-logs} : Create a new mLog.
     *
     * @param mLog the mLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mLog, or with status {@code 400 (Bad Request)} if the mLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/m-logs")
    public ResponseEntity<MLog> createMLog(@Valid @RequestBody MLog mLog) throws URISyntaxException {
        log.debug("REST request to save MLog : {}", mLog);
        if (mLog.getId() != null) {
            throw new BadRequestAlertException("A new mLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MLog result = mLogRepository.save(mLog);
        return ResponseEntity
            .created(new URI("/api/m-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /m-logs/:id} : Updates an existing mLog.
     *
     * @param id the id of the mLog to save.
     * @param mLog the mLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLog,
     * or with status {@code 400 (Bad Request)} if the mLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/m-logs/{id}")
    public ResponseEntity<MLog> updateMLog(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody MLog mLog)
        throws URISyntaxException {
        log.debug("REST request to update MLog : {}, {}", id, mLog);
        if (mLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MLog result = mLogRepository.save(mLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /m-logs/:id} : Partial updates given fields of an existing mLog, field will ignore if it is null
     *
     * @param id the id of the mLog to save.
     * @param mLog the mLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mLog,
     * or with status {@code 400 (Bad Request)} if the mLog is not valid,
     * or with status {@code 404 (Not Found)} if the mLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the mLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/m-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MLog> partialUpdateMLog(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MLog mLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update MLog partially : {}, {}", id, mLog);
        if (mLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MLog> result = mLogRepository
            .findById(mLog.getId())
            .map(existingMLog -> {
                if (mLog.getRating() != null) {
                    existingMLog.setRating(mLog.getRating());
                }

                return existingMLog;
            })
            .map(mLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mLog.getId().toString())
        );
    }

    /**
     * {@code GET  /m-logs} : get all the mLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mLogs in body.
     */
    @GetMapping("/m-logs")
    public List<MLog> getAllMLogs() {
        log.debug("REST request to get all MLogs");
        return mLogRepository.findAll();
    }

    /**
     * {@code GET  /m-logs/:id} : get the "id" mLog.
     *
     * @param id the id of the mLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/m-logs/{id}")
    public ResponseEntity<MLog> getMLog(@PathVariable Long id) {
        log.debug("REST request to get MLog : {}", id);
        Optional<MLog> mLog = mLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mLog);
    }

    /**
     * {@code DELETE  /m-logs/:id} : delete the "id" mLog.
     *
     * @param id the id of the mLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/m-logs/{id}")
    public ResponseEntity<Void> deleteMLog(@PathVariable Long id) {
        log.debug("REST request to delete MLog : {}", id);
        mLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

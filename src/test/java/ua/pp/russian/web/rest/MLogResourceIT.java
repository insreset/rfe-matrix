package ua.pp.russian.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.russian.IntegrationTest;
import ua.pp.russian.domain.Event;
import ua.pp.russian.domain.MLog;
import ua.pp.russian.domain.Member;
import ua.pp.russian.repository.MLogRepository;

/**
 * Integration tests for the {@link MLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MLogResourceIT {

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    private static final String ENTITY_API_URL = "/api/m-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MLogRepository mLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMLogMockMvc;

    private MLog mLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLog createEntity(EntityManager em) {
        MLog mLog = new MLog().rating(DEFAULT_RATING);
        // Add required entity
        Event event;
        if (TestUtil.findAll(em, Event.class).isEmpty()) {
            event = EventResourceIT.createEntity(em);
            em.persist(event);
            em.flush();
        } else {
            event = TestUtil.findAll(em, Event.class).get(0);
        }
        mLog.setEvent(event);
        // Add required entity
        Member member;
        if (TestUtil.findAll(em, Member.class).isEmpty()) {
            member = MemberResourceIT.createEntity(em);
            em.persist(member);
            em.flush();
        } else {
            member = TestUtil.findAll(em, Member.class).get(0);
        }
        mLog.setMember(member);
        return mLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MLog createUpdatedEntity(EntityManager em) {
        MLog mLog = new MLog().rating(UPDATED_RATING);
        // Add required entity
        Event event;
        if (TestUtil.findAll(em, Event.class).isEmpty()) {
            event = EventResourceIT.createUpdatedEntity(em);
            em.persist(event);
            em.flush();
        } else {
            event = TestUtil.findAll(em, Event.class).get(0);
        }
        mLog.setEvent(event);
        // Add required entity
        Member member;
        if (TestUtil.findAll(em, Member.class).isEmpty()) {
            member = MemberResourceIT.createUpdatedEntity(em);
            em.persist(member);
            em.flush();
        } else {
            member = TestUtil.findAll(em, Member.class).get(0);
        }
        mLog.setMember(member);
        return mLog;
    }

    @BeforeEach
    public void initTest() {
        mLog = createEntity(em);
    }

    @Test
    @Transactional
    void createMLog() throws Exception {
        int databaseSizeBeforeCreate = mLogRepository.findAll().size();
        // Create the MLog
        restMLogMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isCreated());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeCreate + 1);
        MLog testMLog = mLogList.get(mLogList.size() - 1);
        assertThat(testMLog.getRating()).isEqualTo(DEFAULT_RATING);
    }

    @Test
    @Transactional
    void createMLogWithExistingId() throws Exception {
        // Create the MLog with an existing ID
        mLog.setId(1L);

        int databaseSizeBeforeCreate = mLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMLogMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRatingIsRequired() throws Exception {
        int databaseSizeBeforeTest = mLogRepository.findAll().size();
        // set the field null
        mLog.setRating(null);

        // Create the MLog, which fails.

        restMLogMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isBadRequest());

        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMLogs() throws Exception {
        // Initialize the database
        mLogRepository.saveAndFlush(mLog);

        // Get all the mLogList
        restMLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)));
    }

    @Test
    @Transactional
    void getMLog() throws Exception {
        // Initialize the database
        mLogRepository.saveAndFlush(mLog);

        // Get the mLog
        restMLogMockMvc
            .perform(get(ENTITY_API_URL_ID, mLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mLog.getId().intValue()))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING));
    }

    @Test
    @Transactional
    void getNonExistingMLog() throws Exception {
        // Get the mLog
        restMLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMLog() throws Exception {
        // Initialize the database
        mLogRepository.saveAndFlush(mLog);

        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();

        // Update the mLog
        MLog updatedMLog = mLogRepository.findById(mLog.getId()).get();
        // Disconnect from session so that the updates on updatedMLog are not directly saved in db
        em.detach(updatedMLog);
        updatedMLog.rating(UPDATED_RATING);

        restMLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMLog.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMLog))
            )
            .andExpect(status().isOk());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
        MLog testMLog = mLogList.get(mLogList.size() - 1);
        assertThat(testMLog.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    void putNonExistingMLog() throws Exception {
        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();
        mLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, mLog.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMLog() throws Exception {
        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();
        mLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMLog() throws Exception {
        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();
        mLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMLogMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMLogWithPatch() throws Exception {
        // Initialize the database
        mLogRepository.saveAndFlush(mLog);

        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();

        // Update the mLog using partial update
        MLog partialUpdatedMLog = new MLog();
        partialUpdatedMLog.setId(mLog.getId());

        partialUpdatedMLog.rating(UPDATED_RATING);

        restMLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMLog))
            )
            .andExpect(status().isOk());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
        MLog testMLog = mLogList.get(mLogList.size() - 1);
        assertThat(testMLog.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    void fullUpdateMLogWithPatch() throws Exception {
        // Initialize the database
        mLogRepository.saveAndFlush(mLog);

        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();

        // Update the mLog using partial update
        MLog partialUpdatedMLog = new MLog();
        partialUpdatedMLog.setId(mLog.getId());

        partialUpdatedMLog.rating(UPDATED_RATING);

        restMLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMLog))
            )
            .andExpect(status().isOk());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
        MLog testMLog = mLogList.get(mLogList.size() - 1);
        assertThat(testMLog.getRating()).isEqualTo(UPDATED_RATING);
    }

    @Test
    @Transactional
    void patchNonExistingMLog() throws Exception {
        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();
        mLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, mLog.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMLog() throws Exception {
        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();
        mLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMLog() throws Exception {
        int databaseSizeBeforeUpdate = mLogRepository.findAll().size();
        mLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(mLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MLog in the database
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMLog() throws Exception {
        // Initialize the database
        mLogRepository.saveAndFlush(mLog);

        int databaseSizeBeforeDelete = mLogRepository.findAll().size();

        // Delete the mLog
        restMLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, mLog.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MLog> mLogList = mLogRepository.findAll();
        assertThat(mLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

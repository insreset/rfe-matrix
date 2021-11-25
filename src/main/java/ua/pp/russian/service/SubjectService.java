package ua.pp.russian.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.russian.service.dto.SubjectDTO;

/**
 * Service Interface for managing {@link ua.pp.russian.domain.Subject}.
 */
public interface SubjectService {
    /**
     * Save a subject.
     *
     * @param subjectDTO the entity to save.
     * @return the persisted entity.
     */
    SubjectDTO save(SubjectDTO subjectDTO);

    /**
     * Partially updates a subject.
     *
     * @param subjectDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SubjectDTO> partialUpdate(SubjectDTO subjectDTO);

    /**
     * Get all the subjects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubjectDTO> findAll(Pageable pageable);

    /**
     * Get the "id" subject.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubjectDTO> findOne(Long id);

    /**
     * Delete the "id" subject.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package ua.pp.russian.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.russian.service.dto.TypeDTO;

/**
 * Service Interface for managing {@link ua.pp.russian.domain.Type}.
 */
public interface TypeService {
    /**
     * Save a type.
     *
     * @param typeDTO the entity to save.
     * @return the persisted entity.
     */
    TypeDTO save(TypeDTO typeDTO);

    /**
     * Partially updates a type.
     *
     * @param typeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeDTO> partialUpdate(TypeDTO typeDTO);

    /**
     * Get all the types.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" type.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeDTO> findOne(Long id);

    /**
     * Delete the "id" type.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

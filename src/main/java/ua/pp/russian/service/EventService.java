package ua.pp.russian.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.russian.service.dto.EventDTO;

/**
 * Service Interface for managing {@link ua.pp.russian.domain.Event}.
 */
public interface EventService {
    /**
     * Save a event.
     *
     * @param eventDTO the entity to save.
     * @return the persisted entity.
     */
    EventDTO save(EventDTO eventDTO);

    /**
     * Partially updates a event.
     *
     * @param eventDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EventDTO> partialUpdate(EventDTO eventDTO);

    /**
     * Get all the events.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventDTO> findAll(Pageable pageable);

    /**
     * Get the "id" event.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventDTO> findOne(Long id);

    /**
     * Delete the "id" event.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

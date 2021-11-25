package ua.pp.russian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.russian.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}

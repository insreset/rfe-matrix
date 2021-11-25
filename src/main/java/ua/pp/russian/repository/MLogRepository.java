package ua.pp.russian.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ua.pp.russian.domain.MLog;

/**
 * Spring Data SQL repository for the MLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLogRepository extends JpaRepository<MLog, Long> {}

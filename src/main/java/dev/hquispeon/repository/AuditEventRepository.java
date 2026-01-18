package dev.hquispeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.hquispeon.entity.AuditEvent;

public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> {
}

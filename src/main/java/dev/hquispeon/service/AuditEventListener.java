package dev.hquispeon.service;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import dev.hquispeon.entity.AuditEvent;
import dev.hquispeon.event.UserCreatedEvent;
import dev.hquispeon.repository.AuditEventRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditEventListener {
	private final AuditEventRepository repository;
	
	@EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        repository.save(
            new AuditEvent(
                "USER_CREATED",
                LocalDateTime.now()
            )
        );
    }
}
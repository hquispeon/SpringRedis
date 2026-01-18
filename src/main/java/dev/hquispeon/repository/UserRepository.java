package dev.hquispeon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.hquispeon.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

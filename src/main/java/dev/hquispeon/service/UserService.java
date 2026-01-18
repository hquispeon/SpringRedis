package dev.hquispeon.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import dev.hquispeon.entity.User;
import dev.hquispeon.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis como CACHÉ
     */
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    /**
     * Redis como MENSAJERÍA
     */
    public User createUser(User user) {
        User saved = userRepository.save(user);

        redisTemplate.convertAndSend(
                "user-events",
                "USER_CREATED"
        );

        return saved;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @CacheEvict(value = "users", key = "#id")
    public void deleteUserById(Long id) {
    		userRepository.deleteById(id);
    }
}

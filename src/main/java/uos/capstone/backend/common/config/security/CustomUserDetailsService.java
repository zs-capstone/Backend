package uos.capstone.backend.common.config.security;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.config.redis.CacheKey;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotFoundException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return CustomUserDetails.of(
                userRepository.findById(Long.parseLong(username))
                        .orElseThrow(() -> new UserNotFoundException())
        );
    }
}

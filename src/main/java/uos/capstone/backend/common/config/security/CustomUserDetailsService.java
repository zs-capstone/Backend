package uos.capstone.backend.common.config.security;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return CustomUserDetails.of(
                userRepository.findById(Long.parseLong(username))
                        .orElseThrow(() -> new UserNotFoundException())
        );
    }
}

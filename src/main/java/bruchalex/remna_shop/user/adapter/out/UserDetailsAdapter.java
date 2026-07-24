package bruchalex.remna_shop.user.adapter.out;

import bruchalex.remna_shop.user.domain.Email;
import bruchalex.remna_shop.user.domain.MyUser;
import bruchalex.remna_shop.user.domain.UserRepository;
import bruchalex.remna_shop.user.domain.UserRole;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@NullMarked
public class UserDetailsAdapter implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {
        MyUser domainUser = userRepository
            .findByEmail(new Email(email))
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + email)
            );

        return User.builder()
            .username(domainUser.getUuid().value().toString())
            .password(domainUser.getHashedPassword().value())
            .authorities(mapAuthorities(domainUser.getRole()))
            .build();
    }

    private Collection<GrantedAuthority> mapAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getValue()));
    }
}

package civa.shared.seed;

import civa.security.model.dto.request.RegisterRequestDto;
import civa.security.service.IAuthService;
import civa.user.model.entity.User;
import civa.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeed implements CommandLineRunner {

    @Autowired
    private IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserSeed(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private void seedUserData() {
        var userData = userRepository.findAll();

        if (userData.isEmpty()) {
            var user = User.builder()
                    .name("admin")
                    .password(passwordEncoder.encode("12345")).build();
            userRepository.save(user);
        }
    }

    @Override
    public void run(String... args) {
        seedUserData();
    }
}

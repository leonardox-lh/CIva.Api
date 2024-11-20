package civa;

import civa.shared.util.Utilities;
import civa.user.model.enums.ERole;
import civa.user.repository.IRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(IRoleRepository roleRepository) {
        return args -> {
            Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_USER);
            Utilities.insertRoleIfNotFound(roleRepository, ERole.ROLE_ADMIN);
        };
    }
}

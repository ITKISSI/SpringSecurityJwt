package ma.ehei.springsecurityjwt;

import ma.ehei.springsecurityjwt.model.ApplicationUser;
import ma.ehei.springsecurityjwt.model.Role;
import ma.ehei.springsecurityjwt.repository.RoleRepository;
import ma.ehei.springsecurityjwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    CommandLineRunner run (RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {

            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = roleRepository.save(new Role(1,"ADMIN"));
            Set<Role> roles =new HashSet<Role>();
            roles.add(adminRole);

            ApplicationUser admin = new ApplicationUser(1,"admin",encoder.encode("password"),roles);
            userRepository.save(admin);
        };
    }

}

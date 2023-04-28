package ma.ehei.springsecurityjwt.services;

import jakarta.transaction.Transactional;
import ma.ehei.springsecurityjwt.dto.LoginResponseDTO;
import ma.ehei.springsecurityjwt.model.ApplicationUser;
import ma.ehei.springsecurityjwt.model.Role;
import ma.ehei.springsecurityjwt.repository.RoleRepository;
import ma.ehei.springsecurityjwt.repository.UserRepository;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username,String password){

        String encodedPassword =encoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<Role>();
        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(0,username,encodedPassword,authorities));
    }

    public LoginResponseDTO loginUser(String username,String password){
        try{
            Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            String token = tokenService.generateJwt(auth);

            return  new LoginResponseDTO(userRepository.findUserByUsername(username).get(),token);

        }catch(AuthenticationException e){
            return new LoginResponseDTO(null,"");
        }
    }
}

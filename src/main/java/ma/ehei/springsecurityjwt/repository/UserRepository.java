package ma.ehei.springsecurityjwt.repository;

import ma.ehei.springsecurityjwt.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {


    Optional<ApplicationUser> findUserByUsername(String username);
}

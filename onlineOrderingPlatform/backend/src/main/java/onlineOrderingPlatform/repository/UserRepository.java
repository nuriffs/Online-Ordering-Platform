package onlineOrderingPlatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotBlank;
import onlineOrderingPlatform.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByUsername(@NotBlank String username);

	@Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findById(@Param("userId") long userId);

	@Query("SELECT u FROM User u WHERE u.userId = :userId")
    User findByUserId(@Param("userId") long userId);
}

package repository;

import Entity.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO,String> {
    Optional<UserDTO> findByUsername(String username);
}

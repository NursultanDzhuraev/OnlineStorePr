package java16.onlinestorepr.repo;


import java16.onlinestorepr.exceptions.NotFoundException;
import java16.onlinestorepr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
    }
}
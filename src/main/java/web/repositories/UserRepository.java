package web.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}

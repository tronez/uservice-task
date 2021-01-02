package uservice.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface EmailRepository extends CrudRepository<Email, Long> {

    Optional<Email> findByMail(String mail);

}

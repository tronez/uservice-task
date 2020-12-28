package uservice.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmailRepository extends CrudRepository<Email, Long> {
}

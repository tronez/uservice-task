package uservice.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
}

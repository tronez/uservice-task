package uservice.user.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {

    Optional<PhoneNumber> findByNumber(String number);
}

package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.PhoneNumberDto;

@Service
public class PhoneNumberService {

    private PhoneNumberRepository repository;
    private PhoneNumberMapper mapper;

    public PhoneNumberService(PhoneNumberRepository repository, PhoneNumberMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PhoneNumber savePhoneNumber(PhoneNumberDto phoneNumberDto, User user) {
        final PhoneNumber phoneNumber = mapper.toEntity(phoneNumberDto);
        phoneNumber.setUser(user);
        return repository.save(phoneNumber);
    }
}

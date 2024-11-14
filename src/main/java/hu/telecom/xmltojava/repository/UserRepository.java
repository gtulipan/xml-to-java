package hu.telecom.xmltojava.repository;

import hu.telecom.xmltojava.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}

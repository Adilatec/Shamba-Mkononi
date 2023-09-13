package tz.go.ega.shambamkononibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.go.ega.shambamkononibackend.model.UserAccount;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUuid(String uuid);
    Optional<UserAccount> findByUsername(String email);
    Optional<UserAccount> findByPhone(String phone);

}

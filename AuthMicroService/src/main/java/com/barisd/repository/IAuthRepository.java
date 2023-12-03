package com.barisd.repository;

import com.barisd.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {
    /**
     * Email daha önce kullanılmış mı kontrol edecek metod:
     */
    Boolean existsByEmail(String email);

    /*
     Email ve şifre bilgisi kayıtlı ise o bilgiyi döndür.
     */
    Optional<Auth> findOptionalByEmailAndPassword(String email, String password);


}

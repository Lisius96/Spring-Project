package org.example.springsecurityclient.Repository;

import org.example.springsecurityclient.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Integer> {

    PasswordResetToken findByToken(String token);
}

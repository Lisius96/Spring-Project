package org.example.springsecurityclient.Repository;

import org.example.springsecurityclient.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer> {
    VerificationToken findByToken(String token);
}

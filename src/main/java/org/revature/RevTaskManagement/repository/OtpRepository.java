package org.revature.RevTaskManagement.repository;

import org.revature.RevTaskManagement.models.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByUserId(int userId);
}
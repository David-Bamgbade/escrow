package com.escrow.repository;

import com.escrow.model.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerDetailsRepo extends JpaRepository<SellerDetails, Long> {
    Optional<SellerDetails> findSellerDetailsBySellerPhoneNumber(String phoneNumber);
    Optional<SellerDetails> findSellerDetailsBySellerEmailAddress (String email);
    Optional<SellerDetails> findSellerDetailsByClientPhoneNumber(String phoneNumber);
}

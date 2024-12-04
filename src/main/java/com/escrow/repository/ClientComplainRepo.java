package com.escrow.repository;

import com.escrow.model.ClientComplain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientComplainRepo extends JpaRepository<ClientComplain, Long> {
    Optional<ClientComplain> findClientComplainByClientPhoneNumber(String phoneNumber);
}

package com.escrow.repository;

import com.escrow.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    Client findClientByEmail(String email);
    Optional<Client>findClientByPhoneNumber(String phoneNumber);
}

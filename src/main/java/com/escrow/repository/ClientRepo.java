package com.escrow.repository;

import com.escrow.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findClientByEmail(String email);
    Optional<Client>findClientByPhoneNumber(String phoneNumber);
    Optional<Client>findClientByEmailAndPassword(String email, String password);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}

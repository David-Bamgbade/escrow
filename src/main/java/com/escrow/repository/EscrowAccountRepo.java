package com.escrow.repository;

import com.escrow.model.EscrowAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscrowAccountRepo extends JpaRepository<EscrowAccount, Long> {


}

package com.escrow.repository;

import com.escrow.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ComplainRepo extends JpaRepository<Complain, Long> {
}

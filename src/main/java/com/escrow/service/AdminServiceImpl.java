package com.escrow.service;

import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.response.AdminEscrowResponse;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.model.*;
import com.escrow.repository.AdminRepo;
import com.escrow.repository.ClientRepo;
import com.escrow.repository.EscrowAccountRepo;
import com.escrow.repository.SellerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    EscrowAccountRepo escrowAccountRepo;

    @Autowired
    SellerDetailsRepo sellerDetailsRepo;

    @Autowired
    ClientRepo clientRepo;


    @Override
    public AdminEscrowResponse confirmClientPayment(AdminEscrowRequest adminRequest) {
        Optional<EscrowAccount> escrowPayment = escrowAccountRepo.findByClientPhoneNumber(adminRequest.getClientPhoneNumber());
        Optional<SellerDetails> sellerDetails = sellerDetailsRepo.findSellerDetailsByClientPhoneNumber(adminRequest.getClientPhoneNumber());

        if (escrowPayment.isPresent() && sellerDetails.isPresent()) {
            escrowPayment.get().setPaymentStatus("PAID");
            escrowPayment.get().setPaymentDate(LocalDateTime.now());
            escrowAccountRepo.save(escrowPayment.get());
            sellerDetails.get().setClientPaymentStatus("Paid");
            sellerDetails.get().setClientPaymentDate(LocalDateTime.now());
            sellerDetailsRepo.save(sellerDetails.get());
        }
        else  {
            throw new IllegalArgumentException("Check Client Or Seller PhoneNumber");
        }
        AdminEscrowResponse adminResponse = new AdminEscrowResponse();
        adminResponse.setNotifySeller("Payment Will Be Sent Shortly, Once Received Please Send Product To Buyer");
        adminResponse.setPaymentStatus(true);
        return adminResponse;
    }
}

package com.escrow.service;

import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.response.AdminEscrowResponse;
import com.escrow.model.*;
import com.escrow.repository.AdminRepo;
import com.escrow.repository.ClientRepo;
import com.escrow.repository.EscrowAccountRepo;
import com.escrow.repository.SellerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (escrowPayment.isPresent()) {
            escrowPayment.get().setPaymentStatus("PAID");
            System.out.println(escrowPayment.get().getSellerName());
        }
        else  {
            throw new IllegalArgumentException("Check Client Or Seller PhoneNumber");
        }
        AdminEscrowResponse adminResponse = new AdminEscrowResponse();
        adminResponse.setClientPaymentStatus("Payment Confirmed, We Have Notified ");
        adminResponse.setNotifySeller("Payment Confirmed, Please Send Product To");
        adminResponse.setPaymentStatus(true);
        return adminResponse;
    }
}

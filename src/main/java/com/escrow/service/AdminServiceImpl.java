package com.escrow.service;

import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.request.ResolveComplainRequest;
import com.escrow.dto.request.ViewClientComplainRequest;
import com.escrow.dto.response.AdminEscrowResponse;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.ResolveComplainResponse;
import com.escrow.dto.response.ViewClientComplainResponse;
import com.escrow.model.*;
import com.escrow.repository.*;
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

    @Autowired
    ClientComplainRepo clientComplainRepo;


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

    public ViewClientComplainResponse viewClientComplain(ViewClientComplainRequest request) {
        Optional <ClientComplain> clientComplain = clientComplainRepo.findClientComplainByClientPhoneNumber(request.getClientPhoneNumber());
        if (clientComplain.isPresent()) {
            ViewClientComplainResponse response = new ViewClientComplainResponse();
            response.setClientComplainId(clientComplain.get().getClientComplainId());
            response.setComplainTime(clientComplain.get().getComplainTime());
            response.setComplainMessage(clientComplain.get().getComplainMessage());
            response.setClientPhoneNumber(clientComplain.get().getClientPhoneNumber());
            response.setPastTransactionTime(clientComplain.get().getPastTransactionTime());
            response.setSellerName(clientComplain.get().getSellerName());
            response.setProductName(clientComplain.get().getProductName());
            response.setProductPrice(clientComplain.get().getProductPrice());
            response.setSellerPhoneNumber(clientComplain.get().getSellerPhoneNumber());
            response.setSuccess(true);
            return response;
        }
        else {
            throw new IllegalArgumentException("No Such Complain");
        }
    }

    @Override
    public ResolveComplainResponse resolveClientComplain(ResolveComplainRequest request) {
        Optional <ClientComplain> clientComplain = clientComplainRepo.findClientComplainByClientPhoneNumber(request.getClientPhoneNumber());
        if (clientComplain.isPresent()) {
            clientComplain.get().setComplainResolved("Yes");
            clientComplainRepo.save(clientComplain.get());
        }
        ResolveComplainResponse response = new ResolveComplainResponse();
        response.setSuccess(true);
        return response;
    }


}

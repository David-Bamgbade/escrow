package com.escrow.service;

import com.escrow.dto.request.EscrowPaymentRequest;
import com.escrow.dto.request.SellerPaymentDetailsRequest;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.SellerPaymentDetailsResponse;
import com.escrow.model.EscrowAccount;
import com.escrow.model.SellerDetails;
import com.escrow.repository.EscrowAccountRepo;
import com.escrow.repository.SellerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    SellerDetailsRepo sellerDetailsRepo;

    @Autowired
    EscrowAccountRepo escrowAccountRepo;

    @Override
    public SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request) {
        SellerDetails sellerDetails = new SellerDetails();
        sellerDetails.setSellerName(request.getSellerName());
        sellerDetails.setSellerEmailAddress(request.getSellerEmail());
        sellerDetails.setSellerPhoneNumber(request.getSellerPhoneNumber());
        sellerDetails.setSellerAccountNumber(request.getSellerAccountNumber());
        sellerDetails.setSellerBankName(request.getSellerBankName());
        sellerDetails.setProductName(request.getProductName());
        sellerDetails.setProductPrice(request.getProductPrice());
        sellerDetailsRepo.save(sellerDetails);
        SellerPaymentDetailsResponse response = new SellerPaymentDetailsResponse();
        response.setSuccess(true);
        response.setMessage("Seller Details Saved Successfully");
        return response;
    }

    @Override
    public EscrowPaymentResponse makePaymentToEscrow(EscrowPaymentRequest request) {
        EscrowAccount escrowAccount = new EscrowAccount();
         Optional<SellerDetails> sellerDetails = sellerDetailsRepo.findSellerDetailsBySellerPhoneNumber(request.getSellerPhoneNumber());

         if (sellerDetails.isPresent()) {
             escrowAccount.setProductPrice(sellerDetails.get().getProductPrice());
             escrowAccount.setProductName(sellerDetails.get().getProductName());
             escrowAccount.setSellerAccountNumber(sellerDetails.get().getSellerAccountNumber());
             escrowAccount.setSellerBankName(sellerDetails.get().getSellerBankName());
             escrowAccount.setSellerPhoneNumber(sellerDetails.get().getSellerPhoneNumber());
             escrowAccount.setSellerName(sellerDetails.get().getSellerName());
             escrowAccountRepo.save(escrowAccount);
         }
            else {
                throw new RuntimeException("No such seller");
         }
         EscrowPaymentResponse response = new EscrowPaymentResponse();
            response.setSuccess(true);
            response.setMessage("Payment Successful");
            return response;
    }
}

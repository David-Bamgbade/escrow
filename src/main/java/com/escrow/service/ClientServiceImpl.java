package com.escrow.service;

import com.escrow.dto.request.ClientEscrowPaymentRequest;

import com.escrow.dto.request.ClientComplainRequest;

import com.escrow.dto.request.RegisterClientRequest;

import com.escrow.dto.request.SellerPaymentDetailsRequest;
import com.escrow.dto.response.ClientComplainResponse;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.RegisterClientResponse;
import com.escrow.dto.response.SellerPaymentDetailsResponse;
import com.escrow.model.Client;
import com.escrow.model.Complain;
import com.escrow.model.EscrowAccount;
import com.escrow.model.SellerDetails;
import com.escrow.repository.ClientRepo;
import com.escrow.repository.ComplainRepo;
import com.escrow.repository.EscrowAccountRepo;
import com.escrow.repository.SellerDetailsRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

   private final ModelMapper modelMapper;

    @Autowired
    SellerDetailsRepo sellerDetailsRepo;

    @Autowired
    EscrowAccountRepo escrowAccountRepo;

    private final ClientRepo clientRepo;

    private final ComplainRepo complainRepo;


    @Override
    public SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request) {
        Optional<Client> mapClient = clientRepo.findClientByPhoneNumber(request.getClientPhoneNumber());

        if (mapClient.isPresent()) {
            SellerDetails sellerDetails = new SellerDetails();
            sellerDetails.setSellerName(request.getSellerName());
            sellerDetails.setSellerEmailAddress(request.getSellerEmail());
            sellerDetails.setSellerPhoneNumber(request.getSellerPhoneNumber());
            sellerDetails.setSellerAccountNumber(request.getSellerAccountNumber());
            sellerDetails.setSellerBankName(request.getSellerBankName());
            sellerDetails.setProductName(request.getProductName());
            sellerDetails.setClientPhoneNumber(request.getClientPhoneNumber());
            sellerDetails.setProductPrice(request.getProductPrice());
            sellerDetails.setClientPaymentStatus("NOT YET PAID");
            sellerDetails.setCreatedOn(LocalDateTime.now());
            sellerDetailsRepo.save(sellerDetails);
        }
        else {
            throw new IllegalArgumentException("Save Your Number To Use Service");
        }
        SellerPaymentDetailsResponse response = new SellerPaymentDetailsResponse();
        response.setSuccess(true);
        response.setMessage("Seller Details Saved Successfully");
        return response;
    }

    @Override
    public EscrowPaymentResponse makePaymentToEscrow(ClientEscrowPaymentRequest request) {
        EscrowAccount escrowAccount = new EscrowAccount();
        Optional<SellerDetails> sellerDetails = sellerDetailsRepo.findSellerDetailsBySellerPhoneNumber(request.getSellerPhoneNumber());
        Optional<Client> clientPhoneNumber = clientRepo.findClientByPhoneNumber(request.getClientPhoneNumber());

         if (sellerDetails.isPresent() && clientPhoneNumber.isPresent()) {
             escrowAccount.setProductPrice(sellerDetails.get().getProductPrice());
             escrowAccount.setProductName(sellerDetails.get().getProductName());
             escrowAccount.setSellerAccountNumber(sellerDetails.get().getSellerAccountNumber());
             escrowAccount.setSellerBankName(sellerDetails.get().getSellerBankName());
             escrowAccount.setSellerPhoneNumber(sellerDetails.get().getSellerPhoneNumber());
             escrowAccount.setSellerName(sellerDetails.get().getSellerName());
             escrowAccount.setClientPhoneNumber(clientPhoneNumber.get().getPhoneNumber());
             escrowAccount.setPaymentStatus("PENDING");
             escrowAccount.setPaymentDate(LocalDateTime.now());
             escrowAccountRepo.save(escrowAccount);
         }
            else {
                throw new IllegalArgumentException("No such seller or check your phone number");
         }
         EscrowPaymentResponse response = new EscrowPaymentResponse();
            response.setSuccess(true);
            response.setMessage("Payment Successful " + "We'll Notify " + sellerDetails.get().getSellerName() + " To Send The Product");
            return response;
    }

    @Override
    public RegisterClientResponse signUp(RegisterClientRequest request) {
         validateClientEmail(request);
         signUpValidate(request);
         validateClientPhoneNumber(request.getPhoneNumber());
         Client client = modelMapper.map(request, Client.class);
         clientRepo.save(client);
         RegisterClientResponse response = new RegisterClientResponse();
         response.setMessage("Client Successfully signed up");
         return response;
    }

    @Override
    public ClientComplainResponse makeComplain(ClientComplainRequest complainRequest) {
        verifyClient(complainRequest);
        Complain complain = modelMapper.map(complainRequest, Complain.class);
        complain.setComplainTime(LocalDateTime.now());
        complainRepo.save(complain);

        ClientComplainResponse response = new ClientComplainResponse();
        response.setMessage("Complain Successfully");
        return response;
    }

    public void validateClientEmail(RegisterClientRequest request) {
        Optional<Client> client = clientRepo.findByEmail(request.getEmail());
        if (client.isPresent()) {
            throw new RuntimeException("Email Already Exists");
        }
    }



    private void signUpValidate(RegisterClientRequest request) {
        if (request.getLastName().trim().isEmpty() ||
                request.getPassword().trim().isEmpty() ||
                request.getEmail().trim().isEmpty() ||
                request.getFirstName().trim().isEmpty() ||
                request.getPhoneNumber().trim().isEmpty()){
            throw new RuntimeException("Invalid detail entered");
        }

    }
    private void validateClientPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() != 11){
            throw new RuntimeException("Invalid phone number must be 11 digit");
        }
    }

    private void verifyClient(ClientComplainRequest request) {
        Client client = clientRepo.findClientByEmail(request.getEmail());
        if (client == null)
            throw new RuntimeException("You are not allowed to make a complain request");
    }
}



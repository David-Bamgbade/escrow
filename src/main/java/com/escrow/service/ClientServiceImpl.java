package com.escrow.service;

import com.escrow.authentication.DetailValidation;
import com.escrow.dto.request.*;

import com.escrow.dto.response.*;
import com.escrow.model.*;
import com.escrow.repository.*;
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

    private final ClientComplainRepo clientComplainRepo;

    @Override
    public SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request) {
        DetailValidation detailValidation = new DetailValidation();
        Optional<Client> mapClient = clientRepo.findClientByPhoneNumber(request.getClientPhoneNumber());

        if (mapClient.isPresent()) {
            SellerDetails sellerDetails = new SellerDetails();
            sellerDetails.setSellerName(detailValidation.validateFirstName(request.getSellerName()));
            sellerDetails.setSellerEmailAddress(detailValidation.validateEmail(duplicateSellerEmail(request.getSellerEmail())));
            sellerDetails.setSellerPhoneNumber(detailValidation.validatePhoneNumber(duplicateSellerPhoneNumber(request.getSellerPhoneNumber())));
            sellerDetails.setSellerAccountNumber(request.getSellerAccountNumber());
            sellerDetails.setSellerBankName(request.getSellerBankName());
            sellerDetails.setProductName(request.getProductName());
            sellerDetails.setClientPhoneNumber(clientPhoneNumberExist(detailValidation.validatePhoneNumber(request.getClientPhoneNumber())));
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
        EscrowPaymentResponse response = new EscrowPaymentResponse();
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
             escrowAccount.setEscrowBank(BankName.POLARIS_BANK);
             escrowAccount.setEscrowAccountNumber("1130632430");
             escrowAccount.setPaymentStatus("PENDING");
             escrowAccount.setPaymentDate(LocalDateTime.now());
             escrowAccountRepo.save(escrowAccount);
             response.setSuccess(true);
             response.setMessage("Please Make Payment To Escrow, So We Can " + "Notify " + sellerDetails.get().getSellerName() + " To Send The Product");
             response.setEscrowBankName(escrowAccount.getSellerBankName());
             response.setEscrowAccountNumber(escrowAccount.getSellerAccountNumber());
         }
            else {
                throw new IllegalArgumentException("No such seller or check your phone number");
         }
            return response;
    }

    @Override
    public RegisterClientResponse signUp(RegisterClientRequest request) {
         validateClientEmail(request);
         signUpValidate(request);
         validateClientPhoneNumber(duplicateClientPhoneNumber(request.getPhoneNumber()));
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


    public ClientAdminComplainResponse clientMakeComplain(ClientAdminComplainRequest request) {
        Optional<EscrowAccount> clientTransaction = escrowAccountRepo.findBySellerPhoneNumber(request.getSellerPhoneNumber());

        if (clientTransaction.isPresent()) {
            ClientComplain complain = new ClientComplain();
            complain.setSellerName(clientTransaction.get().getSellerName());
            complain.setProductName(clientTransaction.get().getProductName());
            complain.setProductPrice(clientTransaction.get().getProductPrice());
            complain.setSellerPhoneNumber(duplicateSellerPhoneNumber(request.getSellerPhoneNumber()));
            complain.setComplainMessage(request.getComplainMessage());
            complain.setPastTransactionTime(clientTransaction.get().getPaymentDate());
            complain.setClientPhoneNumber(clientTransaction.get().getClientPhoneNumber());
            complain.setTransactionPaymentStatus(clientTransaction.get().getPaymentStatus());
            complain.setComplainTime(LocalDateTime.now());
            complain.setComplainResolved("No");
            clientComplainRepo.save(complain);
        }
        else {
            throw new IllegalArgumentException("No Such Transaction");
        }
        ClientAdminComplainResponse response = new ClientAdminComplainResponse();
        response.setMessage("Complain Sent");
        response.setSuccess(true);
        return response;
    }

    @Override
    public SameSellerDetailsResponse buyFromSameSeller(SameSellerDetailsRequest request) {
      Optional<SellerDetails> sellerDetails = sellerDetailsRepo.findSellerDetailsBySellerPhoneNumber(request.getSellerPhoneNumber());
        SameSellerDetailsResponse response = new SameSellerDetailsResponse();

      if (sellerDetails.isPresent()) {
          EscrowAccount escrowAccount = new EscrowAccount();
          escrowAccount.setSellerName(sellerDetails.get().getSellerName());
          escrowAccount.setProductName(request.getProductName());
          escrowAccount.setProductPrice(request.getProductPrice());
          escrowAccount.setSellerAccountNumber(sellerDetails.get().getSellerAccountNumber());
          escrowAccount.setSellerBankName(sellerDetails.get().getSellerBankName());
          escrowAccount.setSellerPhoneNumber(sellerDetails.get().getSellerPhoneNumber());
          escrowAccount.setPaymentStatus("PENDING");
          escrowAccount.setPaymentDate(LocalDateTime.now());
          escrowAccount.setEscrowAccountNumber("1130632430");
          escrowAccount.setEscrowBank(BankName.POLARIS_BANK);
          escrowAccount.setClientPhoneNumber(clientPhoneNumberExist(request.getClientPhoneNumber()));
          escrowAccountRepo.save(escrowAccount);
          response.setEscrowBankName(escrowAccount.getEscrowBank());
          response.setEscrowAccountNumber(escrowAccount.getEscrowAccountNumber());
          response.setMessage("Make Payment To Our Escrow Account");
          response.setSuccess(true);
      }
        return response;
    }

    @Override
    public LoginClientResponse loginClient(LoginClientRequest request) {
        DetailValidation validation = new DetailValidation();
        LoginClientResponse response = new LoginClientResponse();
        Optional<Client> client = clientRepo.findClientByEmailAndPassword(validation.validateEmail(request.getClientEmail()), request.getPassword());
        if (client.isPresent()) {
            client.get().setLoggedIn(true);
            clientRepo.save(client.get());
        }
        else  {
            throw new RuntimeException("Register Before You Login");
        }
        response.setSuccess(true);
        return response;
    }


    private void validateClientEmail(RegisterClientRequest request) {
        Optional<Client> client = clientRepo.findClientByEmail(request.getEmail());
        if (client.isPresent()) {
            throw new RuntimeException("Email Already Exists");
        }
    }

    private String duplicateClientPhoneNumber(String phoneNumber) {
     Optional<Client> client = clientRepo.findClientByPhoneNumber(phoneNumber);
     if (client.isPresent()) {
         throw new RuntimeException("Phone Number Already Exists");
     }
     return phoneNumber;
    }

    private String duplicateSellerPhoneNumber(String phoneNumber) {
        Optional<SellerDetails> seller = sellerDetailsRepo.findSellerDetailsBySellerPhoneNumber(phoneNumber);
        if (seller.isPresent()) {
            throw new RuntimeException("Phone Number Already Exists");
        }
        return phoneNumber;
    }

    private String duplicateSellerEmail(String email) {
        Optional<SellerDetails> seller = sellerDetailsRepo.findSellerDetailsBySellerEmailAddress(email);
        if (seller.isPresent()) {
            throw new RuntimeException("Email Already Exists");
        }
        return email;
    }

    private String clientPhoneNumberExist(String phoneNumber) {
        Optional<Client> client = clientRepo.findClientByPhoneNumber(phoneNumber);
        if (client.isPresent()) {
            return phoneNumber;
        } else  {
            throw new IllegalArgumentException("PhoneNumber Does Not Exist Register To Use Service");
        }
    }


    private void signUpValidate(RegisterClientRequest request) {
        DetailValidation detailValidation = new DetailValidation();
        if (request.getLastName().trim().isEmpty() ||
                request.getPassword().trim().isEmpty() ||
               detailValidation.validateEmail(request.getEmail()).trim().isEmpty() ||
                request.getFirstName().trim().isEmpty() ||
               detailValidation.validatePhoneNumber(request.getPhoneNumber()).trim().isEmpty()){
            throw new RuntimeException("Invalid detail entered");
        }

    }
    private void validateClientPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() != 11){
            throw new RuntimeException("Invalid phone number must be 11 digit");
        }
    }

    private void verifyClient(ClientComplainRequest request) {
        Optional <Client> client = clientRepo.findClientByEmail(request.getEmail());
        if (client.isEmpty())
            throw new RuntimeException("You are not allowed to make a complain request");
    }






}



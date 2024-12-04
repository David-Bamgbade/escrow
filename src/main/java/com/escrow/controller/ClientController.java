package com.escrow.controller;

import com.escrow.dto.request.*;
import com.escrow.dto.response.*;
import com.escrow.service.ClientService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/signUp")
    public ResponseEntity<RegisterClientResponse> registerClient(@RequestBody RegisterClientRequest request) {
        try {
          RegisterClientResponse register = clientService.signUp(request);
          return ResponseEntity.ok(register);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

    @PostMapping("/sendSellerDetails")
    public ResponseEntity<SellerPaymentDetailsResponse> sendSellerDetails(@RequestBody SellerPaymentDetailsRequest request) {
        try {
            SellerPaymentDetailsResponse sendDetails = clientService.sendSellerDetails(request);
            return ResponseEntity.ok(sendDetails);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/sendPaymentToEscrow")
    public ResponseEntity<EscrowPaymentResponse> sendPaymentToEscrow(@RequestBody ClientEscrowPaymentRequest request) {
        try {
            EscrowPaymentResponse response = clientService.makePaymentToEscrow(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/makeComplain")
    public ResponseEntity<ClientAdminComplainResponse> clientComplain(@RequestBody ClientAdminComplainRequest request) {
        try {
            ClientAdminComplainResponse response = clientService.clientMakeComplain(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }





    }




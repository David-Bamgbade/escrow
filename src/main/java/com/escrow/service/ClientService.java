package com.escrow.service;


<<<<<<< HEAD
import com.escrow.dto.request.ClientEscrowPaymentRequest;
=======
import com.escrow.dto.request.ClientComplainRequest;
import com.escrow.dto.request.EscrowPaymentRequest;
import com.escrow.dto.request.RegisterClientRequest;
>>>>>>> 02a2cb458e46fef74d5b13085c048570960b4048
import com.escrow.dto.request.SellerPaymentDetailsRequest;
import com.escrow.dto.response.ClientComplainResponse;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.RegisterClientResponse;
import com.escrow.dto.response.SellerPaymentDetailsResponse;

public interface ClientService {
    SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request);

<<<<<<< HEAD
    EscrowPaymentResponse makePaymentToEscrow(ClientEscrowPaymentRequest request);
=======
    EscrowPaymentResponse makePaymentToEscrow(EscrowPaymentRequest request);

    RegisterClientResponse signUp(RegisterClientRequest request);

    ClientComplainResponse makeComplain(ClientComplainRequest complainRequest);
>>>>>>> 02a2cb458e46fef74d5b13085c048570960b4048
}

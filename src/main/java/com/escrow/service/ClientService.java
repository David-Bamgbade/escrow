package com.escrow.service;

import com.escrow.dto.request.*;
import com.escrow.dto.response.*;

public interface ClientService {
    SellerPaymentDetailsResponse sendSellerDetails(SellerPaymentDetailsRequest request);
    EscrowPaymentResponse makePaymentToEscrow(ClientEscrowPaymentRequest request);
    RegisterClientResponse signUp(RegisterClientRequest request);
    ClientComplainResponse makeComplain(ClientComplainRequest complainRequest);
    ClientAdminComplainResponse clientMakeComplain(ClientAdminComplainRequest request);
    SameSellerDetailsResponse buyFromSameSeller(SameSellerDetailsRequest request);

    LoginClientResponse loginClient(LoginClientRequest request);
}

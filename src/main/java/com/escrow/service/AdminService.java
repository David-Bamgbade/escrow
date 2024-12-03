package com.escrow.service;

import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.response.AdminEscrowResponse;

public interface AdminService {
    AdminEscrowResponse confirmClientPayment(AdminEscrowRequest adminRequest);

}

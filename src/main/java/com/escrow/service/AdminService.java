package com.escrow.service;

import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.request.ResolveComplainRequest;
import com.escrow.dto.request.ViewClientComplainRequest;
import com.escrow.dto.response.AdminEscrowResponse;
import com.escrow.dto.response.ResolveComplainResponse;
import com.escrow.dto.response.ViewClientComplainResponse;

public interface AdminService {
    AdminEscrowResponse confirmClientPayment(AdminEscrowRequest adminRequest);
    ViewClientComplainResponse viewClientComplain(ViewClientComplainRequest request);
    ResolveComplainResponse resolveClientComplain(ResolveComplainRequest request);
}

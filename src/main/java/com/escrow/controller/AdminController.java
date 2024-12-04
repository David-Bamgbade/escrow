package com.escrow.controller;

import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.request.ResolveComplainRequest;
import com.escrow.dto.request.ViewClientComplainRequest;
import com.escrow.dto.response.AdminEscrowResponse;
import com.escrow.dto.response.ResolveComplainResponse;
import com.escrow.dto.response.ViewClientComplainResponse;
import com.escrow.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("confirmPayment")
    public ResponseEntity<AdminEscrowResponse> confirmPayment(@RequestBody AdminEscrowRequest adminEscrowRequest) {
        try {
            AdminEscrowResponse response = adminService.confirmClientPayment(adminEscrowRequest);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("viewClientComplain")
    public ResponseEntity<ViewClientComplainResponse> viewComplain(@RequestBody ViewClientComplainRequest request) {
        try{
            ViewClientComplainResponse response = adminService.viewClientComplain(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("resolveComplain")
    public ResponseEntity<ResolveComplainResponse> resolveComplain(@RequestBody ResolveComplainRequest request) {
        try {
            ResolveComplainResponse response = adminService.resolveClientComplain(request);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }





}

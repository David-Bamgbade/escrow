package com.escrow;

import com.escrow.dto.request.*;
import com.escrow.dto.response.*;
import com.escrow.model.BankName;
import com.escrow.repository.ClientComplainRepo;
import com.escrow.repository.SellerDetailsRepo;
import com.escrow.service.AdminService;
import com.escrow.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EscrowApplicationTests {

	@Autowired
	SellerDetailsRepo sellerDetailsRepo;

	@Autowired
	ClientService clientService;

	@Autowired
	AdminService adminService;



	@Test
	void testForClientToSendSellerDetailsToEscrow() {
		SellerPaymentDetailsRequest sellerDetails = new SellerPaymentDetailsRequest();
		sellerDetails.setSellerName("SellerName");
		sellerDetails.setProductPrice(new BigDecimal("1000"));
		sellerDetails.setProductName("Cloth");
		sellerDetails.setSellerPhoneNumber("08169468242");
		sellerDetails.setSellerEmail("selleremail@gmail.com");
		sellerDetails.setSellerAccountNumber("1130632430");
		sellerDetails.setClientPhoneNumber("08109643956");
		sellerDetails.setSellerBankName(BankName.FIRST_BANK);
		SellerPaymentDetailsResponse sellerDetailsResponse = clientService.sendSellerDetails(sellerDetails);
		assertTrue(sellerDetailsResponse.isSuccess());
	}

	@Test
	void testForClientToMakePaymentToEscrow() {
		ClientEscrowPaymentRequest payment = new ClientEscrowPaymentRequest();
		payment.setSellerPhoneNumber("08169468242");
		payment.setClientPhoneNumber("08109643956");
		EscrowPaymentResponse paymentResponse = clientService.makePaymentToEscrow(payment);
		assertTrue(paymentResponse.isSuccess());
	}

	@Test
	void testForAdminToConfirmPaymentToEscrow() {
		AdminEscrowRequest adminRequest = new AdminEscrowRequest();
		adminRequest.setClientPhoneNumber("08109643956");
		AdminEscrowResponse adminResponse = adminService.confirmClientPayment(adminRequest);
		assertTrue(adminResponse.isPaymentStatus());
	}

	@Test
	void testForClientToMakeComplain() {
		ClientAdminComplainRequest clientComplainRequest = new ClientAdminComplainRequest();
		clientComplainRequest.setSellerPhoneNumber("08169468242");
		clientComplainRequest.setComplainMessage("What I Ordered Versus What I Got");
		ClientAdminComplainResponse clientComplainResponse = clientService.clientMakeComplain(clientComplainRequest);
		assertTrue(clientComplainResponse.isSuccess());
	}

	@Test
	void testForAdminToViewClientComplain() {
		ViewClientComplainRequest request = new ViewClientComplainRequest();
		request.setClientPhoneNumber("08109643956");
		ViewClientComplainResponse response = adminService.viewClientComplain(request);
		assertTrue(response.isSuccess());
	}

	@Test
	void testForAdminToResolveComplain() {
		ResolveComplainRequest resolveComplainRequest = new ResolveComplainRequest();
		resolveComplainRequest.setClientPhoneNumber("08109643956");
		ResolveComplainResponse complainResponse = adminService.resolveClientComplain(resolveComplainRequest);
		assertTrue(complainResponse.isSuccess());
	}








}

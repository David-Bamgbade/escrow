package com.escrow;

import com.escrow.dto.request.ClientEscrowPaymentRequest;
import com.escrow.dto.request.AdminEscrowRequest;
import com.escrow.dto.request.ClientSignUpRequest;
import com.escrow.dto.request.SellerPaymentDetailsRequest;
import com.escrow.dto.response.EscrowPaymentResponse;
import com.escrow.dto.response.SellerPaymentDetailsResponse;
import com.escrow.model.BankName;
import com.escrow.model.PaymentStatus;
import com.escrow.repository.SellerDetailsRepo;
import com.escrow.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EscrowApplicationTests {

	@Autowired
	SellerDetailsRepo sellerDetailsRepo;

	@Autowired
	ClientService clientService;

	@Test
	void testForClientToSendSellerDetailsToEscrow() {
		SellerPaymentDetailsRequest sellerDetails = new SellerPaymentDetailsRequest();
		sellerDetails.setSellerName("SellerName");
		sellerDetails.setProductPrice(new BigDecimal("1000"));
		sellerDetails.setProductName("Cloth");
		sellerDetails.setSellerPhoneNumber("08169468242");
		sellerDetails.setSellerEmail("selleremail@gmail.com");
		sellerDetails.setSellerAccountNumber("1130632430");
		sellerDetails.setSellerBankName(BankName.FIRST_BANK);
		SellerPaymentDetailsResponse sellerDetailsResponse = clientService.sendSellerDetails(sellerDetails);
		assertTrue(sellerDetailsResponse.isSuccess());
	}

	@Test
	void testForClientToMakePaymentToEscrow() {
		ClientEscrowPaymentRequest payment = new ClientEscrowPaymentRequest();
		payment.setProductPrice(new BigDecimal("1000"));
		payment.setSellerPhoneNumber("08169468242");
		EscrowPaymentResponse paymentResponse = clientService.makePaymentToEscrow(payment);
		assertTrue(paymentResponse.isSuccess());
	}

	@Test
	void testForAdminToConfirmPaymentToEscrow() {
		AdminEscrowRequest adminRequest = new AdminEscrowRequest();
		adminRequest.setClientPaymentStatus(PaymentStatus.PAID);
		adminRequest.setMessageSeller("Payment Has Been Confirmed, please release goods");
	}

	@Test
	void testToSignUpClient() {
		ClientSignUpRequest signUpRequest = new ClientSignUpRequest();
	}





}

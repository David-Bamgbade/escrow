package com.escrow;

import com.escrow.dto.request.ClientComplainRequest;
import com.escrow.dto.request.RegisterClientRequest;
import com.escrow.dto.response.ClientComplainResponse;
import com.escrow.dto.response.RegisterClientResponse;
import com.escrow.service.ClientService;
import com.escrow.service.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientServiceTest {
//    @Autowired
////    ComplainServiceImpl complainService;
    @Autowired
     ClientServiceImpl clientService;
    @Test
    public void testClientCanComplain() {
        ClientComplainRequest request = new ClientComplainRequest();
        request.setEmail("email@email.com");
        request.setComplainMessage("i am yet to receive my product from seller ola");
        ClientComplainResponse response = clientService.makeComplain(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
    }
    @Test
    public void testClientCanSignUp(){
        RegisterClientRequest request = new RegisterClientRequest();
        request.setEmail("beez@email.com");
        request.setPassword("password");
        request.setFirstName("Abdul");
        request.setLastName("Azeez");
        request.setPhoneNumber("08104743956");
        RegisterClientResponse response = clientService.signUp(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
    }



}

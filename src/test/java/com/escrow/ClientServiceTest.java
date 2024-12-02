package com.escrow;

import com.escrow.dto.request.ClientComplainRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {
    @Test
    public void testClientCanComplain() {
        ClientComplainRequest request = new ClientComplainRequest();
    }
}

//package com.escrow.service;
//
//import com.escrow.dto.request.ClientComplainRequest;
//import com.escrow.dto.response.ClientComplainResponse;
//import com.escrow.model.Client;
//import com.escrow.model.Complain;
//import com.escrow.repository.ClientRepo;
//import com.escrow.repository.ComplainRepo;
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class ComplainServiceImpl implements ComplainService {
//
// private final ClientRepo clientRepo;
// private final ModelMapper modelMapper;
// private final ComplainRepo complainRepo;
//
//    @Override
//    public ClientComplainResponse makeComplain(ClientComplainRequest complainRequest) {
//        Client client = clientRepo.findBy(complainRequest.getEmail());
//       if (client == null) {
//           throw new RuntimeException("You are not allowed to make a complain request");
//       }
//          Complain complain = modelMapper.map(complainRequest, Complain.class);
//          complainRepo.save(complain);
//
//          ClientComplainResponse complainResponse = new ClientComplainResponse();
//          complainResponse.setMessage("Successfully make a complain request");
//          return complainResponse;
//    }
//}

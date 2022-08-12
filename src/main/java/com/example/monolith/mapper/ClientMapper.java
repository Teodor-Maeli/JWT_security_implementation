package com.example.monolith.mapper;


import com.example.monolith.dto.ClientResponse;
import com.example.monolith.entity.Client;

import java.util.List;

public interface ClientMapper<T, T1> {

    ClientResponse entityToResponse(T type);
    List<ClientResponse> AllEntityToAllResponse(List<T> type);
    Client requestToEntity(T1 Type);

}

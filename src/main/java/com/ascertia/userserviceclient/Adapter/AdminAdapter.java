package com.ascertia.userserviceclient.Adapter;


import com.ascertia.userserviceclient.dto.ApiResponse;
import com.ascertia.userserviceclient.dto.UsersDTO;
import com.ascertia.userserviceclient.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AdminAdapter {

    @Autowired
    RestTemplate restTemplate;

    public String callAdminForCreateUser(UsersDTO usersDTO){
        try {
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<UsersDTO> entity=new HttpEntity<>(usersDTO,httpHeaders);
            ResponseEntity<ApiResponse<String>> response=restTemplate.exchange("http://admin-service/api/save-user", HttpMethod.POST, entity, new ParameterizedTypeReference<ApiResponse<String>>() {
            });
            return response.getBody().getData();
        }
        catch (Exception e){
            throw new ApplicationException("1001",e.getMessage());
        }
    }


    public String callAdminForUpdateUser(UsersDTO usersDTO){
        try {
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<UsersDTO> entity=new HttpEntity<>(usersDTO,httpHeaders);
            ResponseEntity<ApiResponse<String>> response=restTemplate.exchange("http://admin-service/api/update-user", HttpMethod.PUT, entity, new ParameterizedTypeReference<ApiResponse<String>>() {
            });
            return response.getBody().getData();
        }
        catch (Exception e){
            throw new ApplicationException("1001",e.getMessage());
        }
    }

    public String callAdminForDeleteUser(Long id){
        try {
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<UsersDTO> entity=new HttpEntity<>(null,httpHeaders);
            String url= UriComponentsBuilder.fromHttpUrl("http://admin-service/api/delete-user/{id}").toString();
            ResponseEntity<String> response=restTemplate.exchange(url, HttpMethod.DELETE,entity,String.class,id);
            return response.getBody();
        }
        catch (Exception e){
            throw new ApplicationException("1001",e.getMessage());
        }
    }


    public List<UsersDTO> callAdminForSearchUser(UsersDTO usersDTO){
        try {
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<UsersDTO> entity=new HttpEntity<>(null,httpHeaders);
            Map<String,String> map=new HashMap<>();
            map.put("name",usersDTO.getName());
            map.put("email",usersDTO.getEmail());
            map.put("mobile",usersDTO.getMobile());
            String url= UriComponentsBuilder.fromHttpUrl("http://admin-service/api/search-user").toString();
            ResponseEntity<ApiResponse<List<UsersDTO>>> response=restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<ApiResponse<List<UsersDTO>>>() {
            });
            return response.getBody().getData();
        }
        catch (Exception e){
            throw new ApplicationException("1001",e.getMessage());
        }
    }

    public List<UsersDTO> callAdminForGetAllUsers(){
        try {
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<UsersDTO> entity=new HttpEntity<>(null,httpHeaders);
            String url= UriComponentsBuilder.fromHttpUrl("http://admin-service/api/get-all-users").toString();
            ResponseEntity<ApiResponse<List<UsersDTO>>> response=restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<ApiResponse<List<UsersDTO>>>() {
            });
            return response.getBody().getData();
        }
        catch (Exception e){
            throw new ApplicationException("1001",e.getMessage());
        }
    }
}

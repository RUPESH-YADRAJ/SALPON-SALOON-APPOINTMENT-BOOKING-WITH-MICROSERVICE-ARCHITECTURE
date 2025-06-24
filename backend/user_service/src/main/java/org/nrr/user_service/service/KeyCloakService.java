package org.nrr.user_service.service;

import lombok.RequiredArgsConstructor;
import org.nrr.user_service.payload.dto.*;
import org.nrr.user_service.payload.resopnse.TokenResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class KeyCloakService {



    private static final String KEYCLOAK_BASE_URL="http://localhost:8080";
    private static final String KEYCLOAK_ADMIN_API=KEYCLOAK_BASE_URL +"/admin/realms/master/users";
    private static final String TOKEN_URL=KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/token";
    private static final String CLIENT_ID="salon-booking-client";
    private static final String CLIENT_SECRET="94zS2UHhEEXYt9YDb4JWL4AvbdF7lPYj";
    private static final String GRANT_TYPE="password";
    private static final String scope="openid email profile";
    private static final String username="nishan";
    private static final String password="1234";
    private static final String clientId="172120cd-deff-4dbd-b0cf-31f9c707598d";

    private final RestTemplate restTemplate;




    public void createUser(SignupDto signupDto) throws Exception{

        String ACCESS_TOKEN=getAdminAccessToken(username,
                password,
                GRANT_TYPE,
                null
        ).getAccessToken();

        Credential credential=new Credential();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(signupDto.getPassword());

        UserRequest userRequest=new UserRequest();
        userRequest.setUsername(signupDto.getUsername());
        userRequest.setEmail(signupDto.getEmail());
        userRequest.setEnabled(true);
        userRequest.setFirstName(signupDto.getFullName());
        userRequest.getCredentials().add(credential);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(ACCESS_TOKEN);

        HttpEntity<UserRequest> requestEntity=new HttpEntity<>(userRequest,httpHeaders);

        ResponseEntity<String> response=  restTemplate.exchange(
                KEYCLOAK_ADMIN_API,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if(response.getStatusCode()==HttpStatus.CREATED){
            System.out.println("User created Successfully");

            KeyCloakUserDto user=fetchFirstUserByUserName(signupDto.getUsername(),ACCESS_TOKEN);

            KeyCloakRole role=getRoleByName(clientId,
                    ACCESS_TOKEN,
                    signupDto.getRole().toString()
            );

            List<KeyCloakRole> roles=new ArrayList<>();
            roles.add(role);

            assignRoleToUser(user.getId(),
                    clientId,
                    roles,
                    ACCESS_TOKEN);
        }else{
            System.out.println("User creation failed");
            throw new Exception(response.getBody());
        }
    }

    public TokenResponse getAdminAccessToken(String username,
                                             String password,
                                             String grantType,
                                             String refreshToken) throws Exception {

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> requestBody=new LinkedMultiValueMap<>();
        requestBody.add("grant_type",grantType);
        requestBody.add("username",username);
        requestBody.add("password",password);
        requestBody.add("refresh_token",refreshToken);
        requestBody.add("client_id",CLIENT_ID);
        requestBody.add("client_secret",CLIENT_SECRET);
        requestBody.add("scope",scope);

        HttpEntity<MultiValueMap<String,String>> requestEntity=new HttpEntity<>(requestBody,headers);

        try {

            ResponseEntity<TokenResponse> response = restTemplate.exchange(
                    TOKEN_URL,
                    HttpMethod.POST,
                    requestEntity,
                    TokenResponse.class
            );
            return response.getBody();
        } catch (Exception e) {

            System.err.println("Client error: " + e.getMessage());
            throw new Exception(e.getMessage());

        }


    }

    public KeyCloakRole getRoleByName(String clientId,
                                      String token,
                                      String role) throws Exception {

        String url=KEYCLOAK_BASE_URL+"/admin/realms/master/clients/"+clientId+"/roles/"+role;


        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization","Bearer "+token);
        headers.setContentType(MediaType.APPLICATION_JSON);




        HttpEntity<Void> requestEntity=new HttpEntity<>(headers);

        ResponseEntity<KeyCloakRole> response=  restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                KeyCloakRole.class
        );

            return response.getBody();
    }

    public KeyCloakUserDto fetchFirstUserByUserName(String username,String token) throws Exception {

        String url=KEYCLOAK_BASE_URL+"/admin/realms/master/users?username="+username;


        HttpHeaders headers=new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);




        HttpEntity<String> requestEntity=new HttpEntity<>(headers);

        ResponseEntity<KeyCloakUserDto[]> response=  restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                KeyCloakUserDto[].class
        );


        KeyCloakUserDto[] users=response.getBody();
        if(users!=null &&users.length>0){
            return users[0];
        }

            throw new Exception("User not found with user name "+ username);
    }

    public void assignRoleToUser(String userId, String clientId, List<KeyCloakRole> roles,String token) throws Exception {

        String url=KEYCLOAK_BASE_URL+"/admin/realms/master/users/"+userId+"/role-mappings/clients/"+clientId;


        HttpHeaders headers=new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);




        HttpEntity<List<KeyCloakRole>> requestEntity=new HttpEntity<>(roles,headers);



        try{
            ResponseEntity<String> response=  restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
        }catch (Exception e){
            throw new Exception("Failed to assign new role "+ e.getMessage());
        }


    }


    public KeyCloakUserDto fetchUserProfileByJwt(String token) throws Exception {

        String url=KEYCLOAK_BASE_URL+"/realms/master/protocol/openid-connect/userinfo";


        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization",token);
        headers.setContentType(MediaType.APPLICATION_JSON);




        HttpEntity<String> requestEntity=new HttpEntity<>(headers);



        try{
            ResponseEntity<KeyCloakUserDto> response=  restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    KeyCloakUserDto.class
            );
            return response.getBody();
        }catch (Exception e){
            throw new Exception("Failed to get user info "+ e.getMessage());
        }


    }

}

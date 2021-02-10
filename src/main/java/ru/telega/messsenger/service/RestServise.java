package ru.telega.messsenger.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.telega.messsenger.model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class RestServise {

    public void registerUserRequest(User user, HttpServletResponse response){
        try {
            this.postRequest(
                    "http://localhost:8082/api/registration",
                    user,
                    new ParameterizedTypeReference<>() {},
                    response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //  Requests -------------------------------------------------
    private <T> T postRequest(String url, T requestBody, ParameterizedTypeReference<T> responseType, HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> request = new HttpEntity<>(requestBody);
        ResponseEntity<T> respEntity;

        try {
            respEntity = restTemplate.exchange(url, HttpMethod.POST, request, responseType);

            return respEntity.getBody();

        } catch (HttpClientErrorException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (RestClientException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "couldn't reach auth service");
            e.printStackTrace();
        }

        return null;
    }

    private <T> T getRequest(String url, ParameterizedTypeReference<T> responseType, HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> respEntity;

        try {
            respEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

            return respEntity.getBody();

        } catch (HttpClientErrorException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (RestClientException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "couldn't reach auth service");
            e.printStackTrace();
        }

        return null;
    }

    private <T> List<T> getListRequest(String url, ParameterizedTypeReference<List<T>> responseType, HttpServletResponse response) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<T>> respEntity;

        try {
            respEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

            return respEntity.getBody();

        } catch (HttpClientErrorException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (RestClientException e) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "couldn't reach auth service");
            e.printStackTrace();
        }

        return null;
    }

//    void deletePost(long postId, HttpServletResponse response) throws IOException {
//        String url = MAIN_MODULE_URL + "api/post/"+ postId;
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        try {
//            restTemplate.exchange(url, HttpMethod.DELETE, null, Object.class);
//
//        } catch (HttpClientErrorException e) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } catch (RestClientException e) {
//            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "couldn't reach auth service");
//        }
//
//    }
}

package com.aathavan.backend.logic;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.Map;

@Component
public class TempLogic {

    private HttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;


    public Object httpHit() throws Exception {
        httpClient = HttpClient.newHttpClient();
        return getWeatherData(prepareWeatherApi(null));
    }

    private Object getWeatherData(String url) throws Exception {

//        RestClient restClient=RestClient.create(url).get();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> str = restTemplate.getForEntity(url, String.class);

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> object = objectMapper.readValue(httpResponse.body(), new TypeReference<>() {
        });
        httpClient = null;
        return object;
    }

    private String prepareWeatherApi(String city) {
        if (city == null)
            city = "erode";
        return "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5978d7d6cb07f54a21025ff61bbe9aa1";
    }


    public Object httpHit(String city) throws Exception {
        httpClient = HttpClient.newHttpClient();
        return getWeatherData(prepareWeatherApi(city));
    }

    private String generateToken(String userid) {
        return Jwts.builder().setSubject(userid).issuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 1000))
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    }

    private boolean authenticateToken(String token) {
        try {
            Jwts.parser().setSigningKey("asecretkey").build().parseClaimsJwt(token);

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

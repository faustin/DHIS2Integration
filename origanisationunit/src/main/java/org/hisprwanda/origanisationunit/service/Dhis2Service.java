
package org.hisprwanda.origanisationunit.service;

import org.hisprwanda.origanisationunit.model.OrganisationUnitsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Dhis2Service {

    private final WebClient webClient;

    public Dhis2Service(@Value("${dhis2.api.url}") String dhis2ApiUrl,
                        @Value("${dhis2.api.username}") String username,
                        @Value("${dhis2.api.password}") String password) {

        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        this.webClient = WebClient.builder()
                .baseUrl(dhis2ApiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<OrganisationUnitsResponse> getOrganisationUnits() {
        return webClient.get()
                .uri("/organisationUnits?fields=id,name,level,children[id,name,level]")
                .retrieve()
                .bodyToMono(OrganisationUnitsResponse.class);
    }
}

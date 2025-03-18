package org.hisprwanda.origanisationunit.controllers;

import org.hisprwanda.origanisationunit.model.OrganisationUnitsResponse;
import org.hisprwanda.origanisationunit.service.Dhis2Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dhis2")
public class Dhis2Controller {

    private final Dhis2Service dhis2Service;

    public Dhis2Controller(Dhis2Service dhis2Service) {
        this.dhis2Service = dhis2Service;
    }

    @GetMapping("/organisationUnits")
    public Mono<OrganisationUnitsResponse> getOrganisationUnits() {
        return dhis2Service.getOrganisationUnits();
    }
}

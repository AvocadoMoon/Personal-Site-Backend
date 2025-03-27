package com.ezequielvalencia.backend.controller;

import com.ezequielvalencia.backend.db.GeoCacheRepo;
import com.ezequielvalencia.backend.models.db.GeoCacheDBModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestScope
@RequestMapping("/api/v1/geoCache")
public class GeoCache {

//    https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
//    https://stackoverflow.com/questions/1247762/regex-for-all-printable-characters
    public static final String printableCharsOnlyOrEmptyRegex = "^$|^[ -~]+$";

    @Autowired
    GeoCacheRepo geoCacheRepo;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "sendSubmission", description = "Send a geo cache submission to the endpoint.")
    public void takeSubmission(@Valid @RequestBody GeoCacheSubmission geoCacheSubmission){
        geoCacheRepo.saveAndFlush(new GeoCacheDBModel(
                geoCacheSubmission.name, geoCacheSubmission.note, geoCacheSubmission.secret, LocalDateTime.now(),
                geoCacheSubmission.latitude, geoCacheSubmission.longitude,
                geoCacheSubmission.locationName
        ));
    }

    @ApiResponse(responseCode = "200")
    @GetMapping(value = "/{pageNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getSubmission", description = "Retrieve geo cache submission.")
    public List<GeoCacheSubmission> giveSubmission(@PathVariable Integer pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        Page<GeoCacheDBModel> allSubmissions = geoCacheRepo.findAll(pageRequest);
        List<GeoCacheSubmission> dtoSubmissions = allSubmissions.map((geoCacheDBModel -> {
            return new GeoCacheSubmission(geoCacheDBModel.username, geoCacheDBModel.note, geoCacheDBModel.secret,
                    geoCacheDBModel.date.toLocalDate().toString(),
                    geoCacheDBModel.latitude, geoCacheDBModel.longitude,
                    geoCacheDBModel.location_name);
        })).getContent();
        return dtoSubmissions;
    }

    public static class GeoCacheSubmission{
        @NotNull
        @Size(max = 15, min = 3, message = "Size discrepancy: name.")
        @Pattern(regexp = GeoCache.printableCharsOnlyOrEmptyRegex, message = "Not printable characters: name.")
        public String name;

        @NotNull
        @Size(max = 250, min = 3, message = "Size discrepancy: note.")
        @Pattern(regexp = GeoCache.printableCharsOnlyOrEmptyRegex,  message = "Not printable characters: note.")
        public String note;

        @Size(max = 50, message = "Size discrepancy: secret.")
        @Pattern(regexp = GeoCache.printableCharsOnlyOrEmptyRegex, message = "Not printable characters: secret.")
        public String secret = "";

        @Size(max = 50, message = "Size discrepancy: location name.")
        @Pattern(regexp = GeoCache.printableCharsOnlyOrEmptyRegex, message = "Not printable characters: secret.")
        public String locationName = "";

        public Double longitude;

        public Double latitude;

        // Added on return
        public String date;

        public GeoCacheSubmission(){}

        public GeoCacheSubmission(String name, String note, String secret, String date,
                                  Double latitude, Double longitude, String locationName){
            this.name = name; this.note = note; this.secret = secret;
            this.date = date; this.latitude = latitude; this.longitude = longitude;
            this.locationName = locationName;
        }
    }
}

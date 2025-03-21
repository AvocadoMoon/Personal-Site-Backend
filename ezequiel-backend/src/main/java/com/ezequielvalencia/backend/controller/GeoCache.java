package com.ezequielvalencia.backend.controller;

import com.ezequielvalencia.backend.db.GeoCacheRepo;
import com.ezequielvalencia.backend.models.db.GeoCacheDBModel;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestScope
@RequestMapping("/api/v1/geoCache")
public class GeoCache {

//    https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
//    https://stackoverflow.com/questions/1247762/regex-for-all-printable-characters
    public static final String printableCharsOnlyRegex = "^[ -~]+$";

    @Autowired
    GeoCacheRepo geoCacheRepo;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "sendSubmission", description = "Send a geo cache submission to the endpoint.")
    public void takeSubmission(@Valid @RequestBody GeoCacheSubmission geoCacheSubmission){
        geoCacheRepo.saveAndFlush(new GeoCacheDBModel(
                geoCacheSubmission.name, geoCacheSubmission.note, geoCacheSubmission.secret, LocalDate.now()
        ));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeoCacheSubmission giveSubmission(){
        List<GeoCacheDBModel> allSubmissions = geoCacheRepo.findAll();
        return new GeoCacheSubmission(allSubmissions.get(0).username, allSubmissions.get(0).note, allSubmissions.get(0).secret, allSubmissions.get(0).date.toString());
    }

    public static class GeoCacheSubmission{
        @NotNull
        @Size(max = 50, min = 3, message = "Size discrepancy: name.")
        @Pattern(regexp = GeoCache.printableCharsOnlyRegex, message = "Not printable characters: name.")
        public String name;

        @NotNull
        @Size(max = 250, min = 3, message = "Size discrepancy: note.")
        @Pattern(regexp = GeoCache.printableCharsOnlyRegex,  message = "Not printable characters: note.")
        public String note;

        @NotNull
        @Size(max = 50, min = 3, message = "Size discrepancy: secret.")
        @Pattern(regexp = GeoCache.printableCharsOnlyRegex, message = "Not printable characters: secret.")
        public String secret;

        public String date;

        public GeoCacheSubmission(){}

        public GeoCacheSubmission(String name, String note, String secret, String date){
            this.name = name; this.note = note; this.secret = secret; this.date = date;
        }
    }
}

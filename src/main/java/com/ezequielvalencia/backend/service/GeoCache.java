package com.ezequielvalencia.backend.service;

import com.ezequielvalencia.backend.db.GeoCacheRepo;
import com.ezequielvalencia.backend.models.db.GeoCacheDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestScope
@RequestMapping("/api/v1/geoCache")
public class GeoCache {

    @Autowired
    GeoCacheRepo geoCacheRepo;

    @PostMapping("")
    public void receiveSubmission(GeoCacheSubmission geoCacheSubmission){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date and Time: " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowString = now.format(formatter);
        geoCacheRepo.saveAndFlush(new GeoCacheDB(
                geoCacheSubmission.name, geoCacheSubmission.note, geoCacheSubmission.secret, LocalDate.now()
        ));
    }

    public record GeoCacheSubmission(
            String name,
            String note,
            String secret
    ){ }

}

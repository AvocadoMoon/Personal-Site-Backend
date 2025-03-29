package com.ezequielvalencia.backend.controller;

import com.ezequielvalencia.backend.ImproperInputText;
import com.ezequielvalencia.backend.db.GeoCacheRepo;
import com.ezequielvalencia.backend.models.db.GeoCacheDBModel;
import com.modernmt.text.profanity.ProfanityFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestScope
@RequestMapping("/api/v1/geoCache")
public class GeoCache {

//    https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
//    https://stackoverflow.com/questions/1247762/regex-for-all-printable-characters
    public static final String printableCharsOnlyOrEmptyRegex = "^$|^[ -~]+$";
//    https://blog.codinghorror.com/the-problem-with-urls/
//    https://stackoverflow.com/questions/6038061/regular-expression-to-find-urls-within-a-string
    public static final String detectURLRegex = "(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])";

    private static final java.util.regex.Pattern detectURL = java.util.regex.Pattern.compile(detectURLRegex, java.util.regex.Pattern.CASE_INSENSITIVE);

    @Autowired
    GeoCacheRepo geoCacheRepo;

    @Autowired
    HttpServletRequest request;

    private final ProfanityFilter profanityFilter = new ProfanityFilter();

    // Static because the class is request scoped
    private static final LinkedHashMap<String, LastSubmission> recentSubmissions = new LinkedHashMap<>(){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, LastSubmission> eldest) {
            return size() > 500; // Max size for hashmap of 500
        }
    };

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "sendSubmission", description = "Send a geo cache submission to the endpoint.")
    public void takeSubmission(@Valid @RequestBody GeoCacheSubmission geoCacheSubmission){
        if (profanityFilter.test(geoCacheSubmission.note) || profanityFilter.test(geoCacheSubmission.name)
        || profanityFilter.test(geoCacheSubmission.secret) || profanityFilter.test(geoCacheSubmission.locationName)){
            throw new ImproperInputText("Can't submit profanity.");
        } else if (detectURL.matcher(geoCacheSubmission.note).find() || detectURL.matcher(geoCacheSubmission.name).find() ||
        detectURL.matcher(geoCacheSubmission.secret).find() || detectURL.matcher(geoCacheSubmission.locationName).find()){
            throw new ImproperInputText("Can't post a URL.");
        }

        LocalDateTime today = LocalDateTime.now();

        // Three per day to submit
        String ipAddress = request.getLocalAddr();
        if (recentSubmissions.containsKey(ipAddress)){
            LastSubmission latestSubmission = recentSubmissions.get(ipAddress);
            boolean maxSubmissions = latestSubmission.count >= 3;
            boolean submissionToday = latestSubmission.mostRecentSubmission.isEqual(today.toLocalDate());
            if (maxSubmissions && submissionToday){
                throw new ImproperInputText("Max submissions sent.");
            } else if (submissionToday){
                latestSubmission.count += 1;
            } else{
                latestSubmission.mostRecentSubmission = today.toLocalDate();
                latestSubmission.count = 1;
            }
        } else{
            recentSubmissions.put(ipAddress, new LastSubmission(1, today.toLocalDate()));
        }

        geoCacheRepo.saveAndFlush(new GeoCacheDBModel(
                geoCacheSubmission.name, geoCacheSubmission.note, geoCacheSubmission.secret, today,
                geoCacheSubmission.latitude, geoCacheSubmission.longitude,
                geoCacheSubmission.locationName
        ));
    }

    private static class LastSubmission{
        Integer count;
        LocalDate mostRecentSubmission;
        public LastSubmission(Integer count, LocalDate mostRecentSubmission){
            this.count = count;
            this.mostRecentSubmission = mostRecentSubmission;
        }
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

package com.ezequielvalencia.backend.models.db;

import jakarta.persistence.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "geo_cache")
@Entity
public class GeoCacheDBModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String username;
    public String note;
    public String latitude;
    public String longitude;
    public String secret;
    public LocalDateTime date;

    public GeoCacheDBModel(){}

    public GeoCacheDBModel(String username, String note, String secret, LocalDateTime date, String latitude, String longitude){
        this.username = username;
        this.note = note;
        this.secret = secret;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

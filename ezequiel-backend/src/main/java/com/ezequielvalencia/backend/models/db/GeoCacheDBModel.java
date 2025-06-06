package com.ezequielvalencia.backend.models.db;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "geo_cache")
@Entity
public class GeoCacheDBModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String username;
    public String note;
    public String location_name;
    public Double latitude;
    public Double longitude;
    public String secret;
    public LocalDateTime date;

    public GeoCacheDBModel(){}

    public GeoCacheDBModel(String username, String note, String secret, LocalDateTime date,
                           Double latitude, Double longitude, String location_name){
        this.username = username;
        this.note = note;
        this.secret = secret;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_name = location_name;
    }
}

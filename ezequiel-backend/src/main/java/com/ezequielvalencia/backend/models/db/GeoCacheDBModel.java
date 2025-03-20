package com.ezequielvalencia.backend.models.db;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "geo_cache")
@Entity
public class GeoCacheDBModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String note;
    String secret;
    LocalDate date;

    public GeoCacheDBModel(String username, String note, String secret, LocalDate date){
        this.username = username;
        this.note = note;
        this.secret = secret;
        this.date = date;
    }
}

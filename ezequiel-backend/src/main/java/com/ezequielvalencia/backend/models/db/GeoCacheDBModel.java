package com.ezequielvalencia.backend.models.db;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "geo_cache")
@Entity
public class GeoCacheDBModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String username;
    public String note;
    public String secret;
    public LocalDate date;

    public GeoCacheDBModel(String username, String note, String secret, LocalDate date){
        this.username = username;
        this.note = note;
        this.secret = secret;
        this.date = date;
    }
}

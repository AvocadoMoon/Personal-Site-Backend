package com.ezequielvalencia.backend.models.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class GeoCacheDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String note;
    String secret;
    LocalDate date;

    public GeoCacheDB(String username, String note, String secret, LocalDate date){
        this.username = username;
        this.note = note;
        this.secret = secret;
        this.date = date;
    }
}

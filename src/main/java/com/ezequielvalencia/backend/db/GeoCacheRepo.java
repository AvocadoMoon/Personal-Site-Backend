package com.ezequielvalencia.backend.db;


import com.ezequielvalencia.backend.models.db.GeoCacheDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// <Object to be stored, Primary Key>
@Repository
public interface GeoCacheRepo extends JpaRepository<GeoCacheDB, Integer> {

}

package com.ezequielvalencia.backend.db;


import com.ezequielvalencia.backend.models.db.GeoCacheDBModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// <Object to be stored, Primary Key>
@Repository
public interface GeoCacheRepo extends JpaRepository<GeoCacheDBModel, Integer> {

    @Override
    @Query(value = "SELECT g.id, g.username, g.note, g.latitude, g.longitude, g.secret, g.date " +
            "FROM geo_cache g ORDER BY g.date DESC", nativeQuery = true)
    @NotNull
    Page<GeoCacheDBModel> findAll(Pageable pageable);
}

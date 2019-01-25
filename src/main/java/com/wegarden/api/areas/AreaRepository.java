package com.wegarden.api.areas;


import com.wegarden.api.gardens.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Area entity.
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findAllByGarden(Garden garden);
}
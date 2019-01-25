package com.wegarden.api.plants;

import com.wegarden.api.areas.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Plant entity.
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {
    List<Plant> findAllByArea(Area area);
}

package com.wegarden.api.coords;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordRepository extends JpaRepository<Coord,Long> {

}

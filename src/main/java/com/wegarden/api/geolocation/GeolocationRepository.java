package com.wegarden.api.geolocation;


import org.springframework.data.jpa.repository.JpaRepository;

public interface GeolocationRepository  extends JpaRepository<Geolocation,Long> {

}

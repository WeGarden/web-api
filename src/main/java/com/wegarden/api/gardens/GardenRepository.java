package com.wegarden.api.gardens;

import com.wegarden.api.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GardenRepository extends JpaRepository<Garden,Long> {
    List<Garden> findAllByUser(User user);
}

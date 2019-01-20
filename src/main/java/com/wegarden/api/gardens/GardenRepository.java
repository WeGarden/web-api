package com.wegarden.api.gardens;

import com.wegarden.api.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenRepository extends JpaRepository<Garden,Long> {
    List<Garden> findAllByUser(User user);
}

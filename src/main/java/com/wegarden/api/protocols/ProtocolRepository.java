package com.wegarden.api.protocols;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol,Long> {
}

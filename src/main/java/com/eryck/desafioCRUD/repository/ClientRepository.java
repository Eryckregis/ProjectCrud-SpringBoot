package com.eryck.desafioCRUD.repository;

import com.eryck.desafioCRUD.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}

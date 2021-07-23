package com.wonderlabz.bank.dao;

import com.wonderlabz.bank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {


}
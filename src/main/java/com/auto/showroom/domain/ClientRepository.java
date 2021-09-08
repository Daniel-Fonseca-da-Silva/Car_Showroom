package com.auto.showroom.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	Client findByLogin(String login);
	
}

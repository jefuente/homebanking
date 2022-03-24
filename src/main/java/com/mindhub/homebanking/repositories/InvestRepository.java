package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Invest;
import com.mindhub.homebanking.models.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface InvestRepository extends JpaRepository<Invest, Long> {

    List<Invest> findByRisk(RiskType risk);
    Invest findInvestById(Long id);

}

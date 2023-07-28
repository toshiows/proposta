package org.br.mineradora.repository;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProposalRepository implements PanacheRepository<ProposalRepository>{

	public Optional<Object> findCustomer(String customer) {
		return Optional.of(find("customer", customer).firstResult());

	}
}

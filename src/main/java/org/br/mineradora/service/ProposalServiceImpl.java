package org.br.mineradora.service;

import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.message.KafkaEvent;
import org.br.mineradora.repository.ProposalRepository;

import jakarta.inject.Inject;

public class ProposalServiceImpl implements ProposalService{

	@Inject
	ProposalRepository proposalRepository;
	
	@Inject
	KafkaEvent kafkaEvent;
	
	@Override
	public ProposalDetailsDTO findFullProposal(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProposal(long id) {
		// TODO Auto-generated method stub
		
	}

}

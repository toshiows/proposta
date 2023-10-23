package org.br.mineradora.service;

import java.util.Date;

import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.entity.ProposalEntity;
import org.br.mineradora.message.KafkaEvent;
import org.br.mineradora.repository.ProposalRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService{

	@Inject
	ProposalRepository proposalRepository;
	
	@Inject
	KafkaEvent kafkaEvent;
	
	@Override
	public ProposalDetailsDTO findFullProposal(long id) {
		ProposalEntity proposal = proposalRepository.findById(id);
		
		return ProposalDetailsDTO.builder()
				.proposalId(proposal.getId())
				.country(proposal.getCountry())
				.customer(proposal.getCustomer())
				.proposalValidityDays(proposal.getProposalValidDays())
				.tonnes(proposal.getTonnes())
				.priceTonne(proposal.getPriceTonne())
				.build();
	}

	@Override
	@Transactional
	public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
		ProposalDTO proposal = buildAndSaveNewProposal(proposalDetailsDTO);
		
		kafkaEvent.sendNewKafkaEvent(proposal);
	}

	@Override
	@Transactional
	public void removeProposal(long id) {
		proposalRepository.deleteById(id);
	}
	
	private ProposalDTO buildAndSaveNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
		try {
			
			ProposalEntity proposal = new ProposalEntity();

			proposal.setCreated(new Date());
			proposal.setProposalValidDays(proposalDetailsDTO.getProposalValidityDays());
			proposal.setCountry(proposalDetailsDTO.getCountry());
			proposal.setCustomer(proposalDetailsDTO.getCustomer());
			proposal.setPriceTonne(proposalDetailsDTO.getPriceTonne());
			proposal.setTonnes(proposalDetailsDTO.getTonnes());
			
			proposalRepository.persist(proposal);
			
			return ProposalDTO.builder()
					.proposalId(proposalRepository.findByCustomer(proposal.getCustomer()).get().getId())
					.priceTonne(proposal.getPriceTonne())
					.customer(proposal.getCustomer())
					.build();
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}

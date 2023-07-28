package org.br.mineradora.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@AllArgsConstructor
@Jacksonized
public class ProposalDetailsDTO {

	private Long proposalId;
	
	private String customer;
	
	private BigDecimal priceTonne;
	
	private Integer tonnes;
	
	private String country;
	
	private Integer proposalValidityDays;
}

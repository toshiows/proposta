package org.br.mineradora.controller;

import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/proposal")
public class ProposalController {
	
	private final Logger LOG = LoggerFactory.getLogger(ProposalController.class);
	
	@Inject
	ProposalService proposalService;
	
	@GET
	@Path("/{id}")
	public ProposalDetailsDTO findDetailsProposal(@PathParam("id") long id) {
		return proposalService.findFullProposal(id);
	}
	
	@POST
	@RolesAllowed("proposal-customer")
	public Response createProposal(ProposalDetailsDTO proposalDetails) {
		LOG.info("--- Recebendo proposta de compra ---");
		
		try {
			proposalService.createNewProposal(proposalDetails);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeProposal(@PathParam("id") long id) {
		try {
			proposalService.removeProposal(id);
			return Response.ok().build();
		} catch(Exception e) {
			return Response.serverError().build();
		}
	}
}

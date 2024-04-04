package br.org.mongoose.api;

import java.util.List;

import br.org.mongoose.model.CardSet;
import br.org.mongoose.service.broker.SetServiceBroker;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/set-search")
public class SearchSetResource {
	
	@Inject
	SetServiceBroker service;

	@GET
	@Path("")
	public List<CardSet> getAll() {
		return service.retrieveAllSets();
	}

	@GET
	@Path("/{code}")
	public CardSet getByCode(@PathParam("code") String code) {
		return service.findSet(code);
	}
}

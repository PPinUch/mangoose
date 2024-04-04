package br.org.mongoose.api;

import br.org.mongoose.model.Card;
import br.org.mongoose.service.CodeNumberSearchService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/code-number")
public class SetNumberSearchResource {
	
	@Inject
	CodeNumberSearchService service;

	@GET
	@Path("/{code}")
	public Card search(@PathParam("code") String code) {
		return service.findCard(code);
	}
}

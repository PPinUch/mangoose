package br.org.mongoose.external.api;

import br.org.mongoose.external.model.ScryfallCard;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cards")
public interface ScryfallCardsApi {
	
	@GET
	@Path("/{set}/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	ScryfallCard getCardBySetAndNumber(
		@PathParam("set") String set, @PathParam("number") Integer number);

}

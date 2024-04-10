package br.org.mongoose.api;

import java.util.List;

import br.org.mongoose.model.Card;
import br.org.mongoose.service.broker.CardServiceBroker;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/card-search")
public class SearchCardResource {

	@Inject
	CardServiceBroker cardService;

	@GET
	@Path("")
	public List<Card> getAll() {
		return Card.listAll();
	}

	@GET
	@Path("/{set}/{number}")
	public Card getCardSetAndNumber(@PathParam("set") String set, @PathParam("number") String number) {
		return cardService.findBySetAndNumber(set, number);
	}
}

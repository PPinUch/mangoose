package br.org.mongoose.service.broker;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.org.mongoose.external.client.ScryfallCardsClient;
import br.org.mongoose.external.model.ScryfallCard;
import br.org.mongoose.model.Card;
import br.org.mongoose.utils.ReflectionUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CardServiceBroker {
	
	@Inject
    @RestClient
    ScryfallCardsClient scryfallCardsClient;

	public Card findBySetAndNumber(String set, String number) {
		Card card = Card.findBySetAndNumber(set, number);
		if(card == null || card.toggleUpdate) {
			card = fetchFromExternal(set, number);
			card.persist();
		}
		return card;
	}

	private Card fetchFromExternal(String set, String number) {
		ScryfallCard external = scryfallCardsClient.getCardBySetAndNumber(set, number);
		if(external == null) {
			throw new RuntimeException("No card found with "+set+"/"+number);
		}
		Card card = ReflectionUtils.createFromObject(external, Card.class);
		card.scryfallId = external.getId();
		return card;
	}
}

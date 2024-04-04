package br.org.mongoose.external.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.org.mongoose.external.api.ScryfallCardsApi;

@RegisterRestClient(baseUri = "https://api.scryfall.com/cards")
public interface ScryfallCardsClient extends ScryfallCardsApi {
}

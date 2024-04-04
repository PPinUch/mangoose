package br.org.mongoose.external.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.org.mongoose.external.api.ScryfallSetsApi;

@RegisterRestClient(baseUri = "https://api.scryfall.com/sets")
public interface ScryfallSetsClient extends ScryfallSetsApi{

	
}
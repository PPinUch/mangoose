package br.org.mongoose.service.broker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.org.mongoose.external.client.ScryfallSetsClient;
import br.org.mongoose.external.model.ScryFallSet;
import br.org.mongoose.model.CardSet;
import br.org.mongoose.utils.ReflectionUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SetServiceBroker {

	@Inject
	@RestClient
	ScryfallSetsClient scryfallSetsClient;

	public Integer countSets() {
		return retrieveAllSets().size();
	}
	
	public List<CardSet> retrieveAllSets() {
		List<CardSet> sets = CardSet.listAll();
		if(sets == null || sets.isEmpty()) {
			sets = fetchExternalAndSave();
		}
		return sets;
	}

	private List<CardSet> fetchExternalAndSave() {
		List<ScryFallSet> scrSets = scryfallSetsClient.getAllSets().getData();
		if(scrSets == null || scrSets.isEmpty()) {
			throw new RuntimeException("The servers didn't returned any sets. Try again later.");
		}
		List<CardSet> cardSets = new ArrayList<>();
		for(ScryFallSet set : scrSets) {
			CardSet cs = ReflectionUtils.createFromObject(set, CardSet.class);
			cs.persistOrUpdate();
			cardSets.add(cs);
		}
		return cardSets;
	}

	public CardSet findSet(String code) {
		CardSet set = CardSet.find("code", code).firstResult();
		if(set == null) {
			set = fetchFromExternal(code);
			set.persist();
		}
		return set;
	}

	private CardSet fetchFromExternal(String code) {
		ScryFallSet externalSet = scryfallSetsClient.getSet(code);
		if(externalSet == null) {
			throw new RuntimeException("No set found with argument "+code);
		}
		CardSet set = ReflectionUtils.createFromObject(externalSet, CardSet.class);
		return set;
	}
}

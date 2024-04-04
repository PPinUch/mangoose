package br.org.mongoose.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.org.mongoose.model.Card;
import br.org.mongoose.model.CardSet;
import br.org.mongoose.service.broker.CardServiceBroker;
import br.org.mongoose.service.broker.SetServiceBroker;
import br.org.mongoose.utils.SetToolsUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CodeNumberSearchService {
	
	@Inject
	SetToolsUtils utils;

	@Inject
	SetServiceBroker setBroker;
	@Inject
	CardServiceBroker cardBroker;

	List<CardSet> allSets = new ArrayList<>();

	public Card findCard(String setCodeAndNumber) {
		CardSet set = utils.filterSets(setCodeAndNumber.toLowerCase(), safeRetrieveAllSets());
		if(set == null) {
			throw new RuntimeException(setCodeAndNumber + " doesen't correspond with any card in our base");
		}
		String number = setCodeAndNumber.replace(set.code, "");
		Card card = Optional.ofNullable(cardBroker.findBySetAndNumber(set.code, number))
			.orElseThrow(() -> new RuntimeException("Cannot find card with code and number: "+setCodeAndNumber));
		return card;
	}

	private List<CardSet> safeRetrieveAllSets() {
		if(this.allSets.isEmpty()) {
			this.allSets = setBroker.retrieveAllSets();
		}
		return this.allSets;
	}
}
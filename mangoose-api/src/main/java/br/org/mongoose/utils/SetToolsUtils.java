package br.org.mongoose.utils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import br.org.mongoose.model.CardSet;
import jakarta.enterprise.context.RequestScoped;
import lombok.NonNull;

@RequestScoped
public final class SetToolsUtils {
	private static final int MAX_COUNT = 5;
	private static final int START_COUNT = 3;

	private int count=START_COUNT;

	public CardSet filterSets(@NonNull String code, Collection<CardSet> collSet) {
		if(collSet.size() == 1) {
			this.count = START_COUNT;
			return collSet.iterator().next();
		}
		if(count > MAX_COUNT || collSet == null || collSet.isEmpty() || code.length() < START_COUNT) {
			count = START_COUNT;
			return null;
		}
		final String keyCode = code.substring(0,count++);
		Set<CardSet> filtered = collSet.stream().filter(set -> set.code.startsWith(keyCode)).collect(Collectors.toSet());
		return filterSets(code, filtered);
	}
}

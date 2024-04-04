package br.org.mongoose.external.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScryFallSetBulk {
	private List<ScryFallSet> data = new ArrayList<>();
}

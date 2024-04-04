package br.org.mongoose.external.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScryfallCard {
    
    private String id;
	
	private String name;

	private String set;

	@JsonProperty("collector_number")
	private String collectorNumber;
	
	@JsonProperty("oracle_id")
	private String oracleId;
	
	@JsonProperty("type_line")
	private String typeLine;

	@JsonProperty("mana_cost")
	private String manaCost;

	private String cmc;

	private List<String> colors;
	@JsonProperty("color_identity")
	private List<String> colorIdentity;

	@JsonProperty("scryfall_uri")
	private String scryfallUri;

}

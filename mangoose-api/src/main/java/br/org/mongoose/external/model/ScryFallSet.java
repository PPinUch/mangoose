package br.org.mongoose.external.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScryFallSet {
	private String id;
	private String name;
	private String code;
	
	@JsonProperty("card_count")
	private Integer cardCount;

	@JsonProperty("released_at")
	private String dateOfRelease;

	@JsonProperty("icon_sgv_uri")
	private String iconSVGURI;
}

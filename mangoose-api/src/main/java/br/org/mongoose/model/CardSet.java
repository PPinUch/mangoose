package br.org.mongoose.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "set")
public class CardSet extends PanacheMongoEntity {
	@BsonProperty("scryfall_id")
	public String scryFallId;

	public String name;

	public String code;

	@BsonProperty("card_count")
	public Integer cardCount;

	@BsonProperty("released_at")
	public String dateOfRelease;

	@BsonProperty("icon_sgv_uri")
	public String iconSVGURI;

}

package br.org.mongoose.model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "card")
public class Card extends PanacheMongoEntity {

	@BsonProperty("scryfall_id")
	public String scryfallId;
	@BsonProperty("oracle_id")
	public String oracleId;

	public String name;

	public String set;

	@BsonProperty("collector_number")
	public String collectorNumber;
	
	@BsonProperty("type_line")
	public String typeLine;

	@BsonProperty("mana_cost")
	public String manaCost;
	public String cmc;

	public String colors;
	@BsonProperty("color_identity")
	public String colorIdentity;

	@BsonProperty("scryfall_uri")
	public String scryfallUri;

	@BsonProperty("toggle_update")
	public Boolean toggleUpdate = false;

	// REP METHODS
	public static Card findBySetAndNumber(String set, String number) {
		return find("set = ?1 and collector_number = ?2", set, number).firstResult();
	}
}

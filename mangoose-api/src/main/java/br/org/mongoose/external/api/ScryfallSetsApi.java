package br.org.mongoose.external.api;

import br.org.mongoose.external.model.ScryFallSet;
import br.org.mongoose.external.model.ScryFallSetBulk;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("")
public interface ScryfallSetsApi {
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	ScryFallSetBulk getAllSets();

	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	ScryFallSet getSet(@PathParam("code") String code);
}

package pp.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Singleton;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Singleton
@RegisterRestClient
public interface ReservationService {

    @POST
    @Path("/add")
    Response addReservation(String reservation);

    @GET
    @Path("/classic-library")
    Response getClassicLibraryReservations();

    @GET
    @Path("/rental-service")
    Response getUserRentalServiceReservations();

    @GET
    @Path("/classic-library/{username}")
    Response getClassicLibraryReservationsForUser(@PathParam String username);

    @GET
    @Path("/rental-service/{username}")
    Response getUserRentalServiceReservationsForUser(@PathParam String username);

    @POST
    @Path("/update")
    Response updateReservation(String reservation);

}

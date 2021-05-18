package pp.gateway.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import pp.gateway.client.ReservationService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/reservations")
@RequestScoped
public class ReservationResource {

    @Inject
    @RestClient
    ReservationService reservationService;

    @POST
    @Path("/add")
    @RolesAllowed("ROLE_USER")
    public Response addReservation(String reservation) {
        reservationService.addReservation(reservation);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/classic-library")
    @RolesAllowed("ROLE_ADMIN")
    public Response getClassicLibraryReservations() {
        return reservationService.getClassicLibraryReservations();
    }

    @GET
    @Path("/rental-service")
    @RolesAllowed("ROLE_ADMIN")
    public Response getUserRentalServiceReservations() {
        return reservationService.getUserRentalServiceReservations();
    }

    @GET
    @Path("/classic-library/{username}")
    @RolesAllowed("ROLE_USER")
    public Response getClassicLibraryReservationsForUser(@PathParam String username) {
        return reservationService.getClassicLibraryReservationsForUser(username);
    }

    @GET
    @Path("/rental-service/{username}")
    @RolesAllowed("ROLE_USER")
    public Response getUserRentalServiceBooksForUser(@PathParam String username) {
        return reservationService.getUserRentalServiceReservationsForUser(username);
    }

    @POST
    @Path("/update")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Response updateReservation(String reservation) {
        reservationService.updateReservation(reservation);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }
}

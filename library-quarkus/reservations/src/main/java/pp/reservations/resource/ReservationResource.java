package pp.reservations.resource;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import pp.reservations.domain.Reservation;
import pp.reservations.service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/")
public class ReservationResource {

    @Inject
    ReservationService reservationService;

    @POST
    @Path("/add")
    public Response addReservation(Reservation reservation) {
        reservationService.addReservation(reservation);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/classic-library")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClassicLibraryReservations() {
        return Response.ok().entity(reservationService.getClassicLibraryReservations()).build();
    }

    @GET
    @Path("/rental-service")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserRentalServiceReservations() {
        return Response.ok().entity(reservationService.getUserRentalServiceReservations()).build();
    }

    @GET
    @Path("/classic-library/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClassicLibraryReservationsForUser(@PathParam String username) {
        return Response.ok().entity(reservationService.getClassicLibraryReservationsForUser(username)).build();
    }

    @GET
    @Path("/rental-service/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserRentalServiceReservationsForUser(@PathParam String username) {
        return Response.ok(reservationService.getUserRentalServiceReservationsForUser(username)).build();
    }

    @POST
    @Path("/update")
    public Response updateReservation(Reservation reservation) {
        reservationService.updateReservation(reservation);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/save-test-data")
    public String saveTestData() {
        return reservationService.saveTestData();
    }
}

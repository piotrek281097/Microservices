package pp.gateway.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import pp.gateway.client.BookService;
import pp.gateway.client.UserDataService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonStructure;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/books")
@RequestScoped
public class BookResource {

    @Inject
    @RestClient
    BookService bookService;

    @POST
    @Path("/add")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    public Response addBook(String book) {
        bookService.addBook(book);
        return Response.ok().build();
    }

    @GET
    @Path("/classic-library")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    public Response getClassicLibraryBooks() {
        return bookService.getClassicLibraryBooks();
    }

    @GET
    @Path("/rental-service")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    public Response getUserRentalServiceBooks() {
        return bookService.getUserRentalServiceBooks();
    }

    @DELETE
    @Path("/delete/{bookId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    public Response deleteBookById(@PathParam long bookId) {
        bookService.deleteBookById(bookId);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/{bookId}")
    @RolesAllowed("ROLE_USER")
    public Response getBookById(@PathParam long bookId) {
        return bookService.getBookById(bookId);
    }

    @POST
    @Path("/update")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Response updateBook(String book) {
        bookService.updateBook(book);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @POST
    @Path("/add-opinion")
    @RolesAllowed("ROLE_USER")
    public Response addOpinion(String opinionDto) {
        bookService.addOpinion(opinionDto);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/ratings")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Response getBooksOrderedByAvgRate() {
        return bookService.getBooksOrderedByAvgRate();
    }

}

package pp.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Singleton;
import javax.json.JsonArray;
import javax.json.JsonStructure;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Singleton
@RegisterRestClient
public interface BookService {

    @POST
    @Path("/add")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    Response addBook(String book);

    @GET
    @Path("/classic-library")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    JsonArray getClassicLibraryBooks();

    @GET
    @Path("/rental-service")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    JsonArray getUserRentalServiceBooks();

    @DELETE
    @Path("/delete/{bookId}")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    Response deleteBookById(@PathParam long bookId);

    @GET
    @Path("/{bookId}")
//    @Secured("ROLE_USER")
    JsonStructure getBookById(@PathParam long bookId);

    @POST
    @Path("/update")
//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    Response updateBook(String book);

    @POST
    @Path("/add-opinion")
//    @Secured("USER")
    Response addOpinion(String opinionDto);

    @GET
    @Path("/ratings")
//    @Secured({"ADMIN", "USER"})
    JsonArray getBooksOrderedByAvgRate();

}

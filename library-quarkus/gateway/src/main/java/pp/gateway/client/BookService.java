package pp.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Singleton
@RegisterRestClient
public interface BookService {

    @POST
    @Path("/add")
    Response addBook(String book);

    @GET
    @Path("/classic-library")
    Response getClassicLibraryBooks();

    @GET
    @Path("/rental-service")
    Response getUserRentalServiceBooks();

    @DELETE
    @Path("/delete/{bookId}")
    Response deleteBookById(@PathParam long bookId);

    @GET
    @Path("/{bookId}")
    Response getBookById(@PathParam long bookId);

    @POST
    @Path("/update")
    Response updateBook(String book);

    @POST
    @Path("/add-opinion")
    Response addOpinion(String opinionDto);

    @GET
    @Path("/ratings")
    Response getBooksOrderedByAvgRate();

}

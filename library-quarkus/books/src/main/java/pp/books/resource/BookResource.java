package pp.books.resource;

import pp.books.domain.Book;
import pp.books.dto.OpinionDto;
import pp.books.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/")
public class BookResource {

    @Inject
    BookService bookService;

    @POST
    @Path("/add")
    public Response addBook(Book book) {
        bookService.addBook(book);
        return Response.ok().build();
    }

    @GET
    @Path("/classic-library")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClassicLibraryBooks() {
        return Response.ok().entity(bookService.getClassicLibraryBooks()).build();
    }

    @GET
    @Path("/rental-service")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserRentalServiceBooks() {
        return Response.ok().entity(bookService.getUserRentalServiceBooks()).build();
    }

    @DELETE
    @Path("/delete/{bookId}")
    public Response deleteBookById(@PathParam long bookId) {
        bookService.deleteBookById(bookId);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/{bookId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBookById(@PathParam long bookId) {
        return Response.ok(bookService.getBookById(bookId)).build();
    }

    @POST
    @Path("/update")
    public Response updateBook(Book book) {
        bookService.updateBook(book);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @POST
    @Path("/add-opinion")
    public Response addOpinion(OpinionDto opinionDto) {
        bookService.addOpinion(opinionDto);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/ratings")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBooksOrderedByAvgRate() {
        return Response.ok(bookService.getBooksOrderedByAvgRate()).build();
    }

    @GET
    @Path("/save-test")
    @Produces({MediaType.TEXT_PLAIN})
    public String savePerformanceTest() {
        return bookService.savePerformanceTest();
    }

    @GET
    @Path("/read-test")
    @Produces({MediaType.TEXT_PLAIN})
    public String readPerformanceTest() {
        return bookService.readPerformanceTest();
    }
}

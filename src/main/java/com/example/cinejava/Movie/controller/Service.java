import com.example.cinejava.Movie.controller.Movie;
import com.example.cinejava.Movie.model.MovieDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import mx.edu.utez.client.model.Categoria;
import mx.edu.utez.client.model.Pelicula;
import mx.edu.utez.client.model.PeliculaDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/pelicula")
public class Service {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getPelicula(){
        return new MovieDAO().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getPelicula(@PathParam("id") int id){
        return new MovieDAO().findByPeliculaId(id);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Movie save(MultivaluedMap<String, String> formParams){
        int id = Integer.parseInt(formParams.get("id").get(0));
        if(new MovieDAO().save(getParams(id, formParams), true))
            return new MovieDAO().findByPeliculaId(id);
        return null;
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Movie save(@PathParam("id") int id, MultivaluedMap<String, String> formParams){
        if(new MovieDAO().save(getParams(id, formParams), false))
            return new MovieDAO().findByPeliculaId(id);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deletePelicula(@PathParam("id") int id){
        return new MovieDAO().delete(id);
    }

    private Movie getParams(int id, MultivaluedMap<String, String> formParams){

        String titulo = formParams.get("titulo").get(0);
        String descripcion = formParams.get("descripcion").get(0);
        String sinopsis = formParams.get("sinopsis").get(0);
        int rating = Integer.parseInt(formParams.get("rating").get(0));
        String fechaRegistro = formParams.get("fechaRegistro").get(0);
        String fechaActualizacion = formParams.get("fechaActualizacion").get(0);
        int estado = Integer.parseInt(formParams.get("estado").get(0));
        int categoria = Integer.parseInt(formParams.get("categoria").get(0));

        Movie movie = new Movie(id, titulo,descripcion, sinopsis,rating,fechaRegistro, fechaActualizacion,estado, categoria);
        System.out.println(movie);
        return movie;
    }

}

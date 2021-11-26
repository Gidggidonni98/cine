package com.example.cinejava.Movie.model;




import com.example.cinejava.Service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    PreparedStatement pstm;

    public List<Movie> findAll(){
        List<Movie> listPelicula = new ArrayList<>();

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM pelicula;");
            rs = cstm.executeQuery();

            while(rs.next()){
                Movie movie = new Movie();

                movie.setId(rs.getInt("id"));
                movie.setTitulo(rs.getString("titulo"));
                movie.setDescripcion(rs.getString("descripcion"));
                movie.setSinopsis(rs.getString("sinopsis"));
                movie.setRating(rs.getInt("rating"));
                movie.setFechaRegistro(rs.getString("fechaRegistro"));
                movie.setFechaActualizacion(rs.getString("fechaActualizacion"));
                movie.setEstado(rs.getInt("estado"));
                movie.setCategoria(rs.getInt("categoria"));
                listPelicula.add(movie);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return listPelicula;
    }

    public Movie findByPeliculaId(int id){
        Movie movie = null;

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM pelicula WHERE id = ?;");
            cstm.setInt(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                movie = new Movie();

                movie.setId(rs.getInt("id"));
                movie.setTitulo(rs.getString("titulo"));
                movie.setDescripcion(rs.getString("descripcion"));
                movie.setSinopsis(rs.getString("sinopsis"));
                movie.setRating(rs.getInt("rating"));
                movie.setFechaRegistro(rs.getString("fechaRegistro"));
                movie.setFechaActualizacion(rs.getString("fechaActualizacion"));
                movie.setEstado(rs.getInt("estado"));
                movie.setCategoria(rs.getInt("categoria"));

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return movie;
    }

    public boolean save(Movie movie, boolean isCreate){
        boolean flag = false;

        try{
            con = ConnectionMySQL.getConnection();
            if(isCreate){
                cstm = con.prepareCall("INSERT INTO pelicula (id,titulo, descripcion, sinopsis, rating, fechaRegistro, fechaActualizacion, estado, categoria)VALUES(?,?,?,?,?,?,?,?,?);");

                cstm.setInt(1, movie.getId());
                cstm.setString(2, movie.getTitulo());
                cstm.setString(3, movie.getDescripcion());
                cstm.setString(4, movie.getSinopsis());
                cstm.setInt(5, movie.getRating());
                cstm.setString(6, movie.getFechaRegistro());
                cstm.setString(7, movie.getFechaActualizacion());
                cstm.setInt(8,movie.getEstado());
                cstm.setInt(9,movie.getCategoria());

            } else {
                cstm = con.prepareCall("UPDATE pelicula SET titulo = ?, descripcion = ?, sinopsis = ?, rating = ?, fechaRegistro = ?, fechaActualizacion = ?, estado = ?, categoria = ? WHERE id = ?;");

                cstm.setString(1, movie.getTitulo());
                cstm.setString(2, movie.getDescripcion());
                cstm.setString(3, movie.getSinopsis());
                cstm.setInt(4, movie.getRating());
                cstm.setString(5, movie.getFechaRegistro());
                cstm.setString(6, movie.getFechaActualizacion());
                cstm.setInt(7,movie.getEstado());
                cstm.setInt(8,movie.getCategoria());
            }
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean delete(int id){
        boolean flag = false;

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("DELETE FROM pelicula WHERE id = ?;");
            cstm.setInt(1, id);
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }
    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

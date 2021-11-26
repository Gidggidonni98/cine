package com.example.cinejava.Movie.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    @XmlElement
    private int id;
    @XmlElement
    private String titulo;
    @XmlElement
    private String descripcion;
    @XmlElement
    private String sinopsis;
    @XmlElement
    private int rating;
    @XmlElement
    private String fechaRegistro;
    @XmlElement
    private String fechaActualizacion;
    @XmlElement
    private int estado;
    @XmlElement
    private int categoria;

    public Movie(){

    }

    public Movie(int id, String titulo, String descripcion, String sinopsis, int
            rating, String fechaRegistro, String fechaActualizacion, int estado, int categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.sinopsis = sinopsis;
        this.rating = rating;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", rating=" + rating +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", fechaActualizacion='" + fechaActualizacion + '\'' +
                ", estado=" + estado +
                ", categoria=" + categoria +
                '}';
    }
}
package com.example.cinejava.Category.controller;


import com.example.cinejava.Category.model.Category;
import com.example.cinejava.Category.model.CategoryDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;

import java.util.List;

@Path("/category")
public class Service {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getCategory(){
        return new CategoryDAO().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategory(@PathParam("id") int id){
        return new CategoryDAO().findCategoryById(id);
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Category save(MultivaluedMap<String, String> formParams){
        int id = Integer.parseInt(formParams.get("id").get(0));
        if(new CategoryDAO().createCategory(getParams(id, formParams), true))
            return new CategoryDAO().findCategoryById(id);
        return null;
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCategory(@PathParam("id") int id){
        return new CategoryDAO().delete(id);
    }

    private Category getParams(int id, MultivaluedMap<String, String> formParams){
        String name = formParams.get("name").get(0);


        Category category = new Category();
        System.out.println(category);
        return category;
    }
}

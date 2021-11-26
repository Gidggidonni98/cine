package com.example.cinejava.Category.model;

import com.example.cinejava.Service.ConnectionMySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    public List<Category> findAll(){
        List<Category> listCategory = new ArrayList<>();
       try{
           con = ConnectionMySQL.getConnection();
           cstm = con.prepareCall("SELECT * FROM category");
           rs = cstm.executeQuery();

           while (rs.next()){
               Category category = new Category();
               category.setId(rs.getInt("id"));
               category.setName(rs.getString("name"));

               listCategory.add(category);
           }
       }catch (SQLException e){
           System.out.printf("Ha sucedido algun error: %s", e.getMessage());
       }finally {
           ConnectionMySQL.closeConnections(con, cstm,rs);
       }
       return  listCategory;

    }
    public Category findCategoryById (int id){
        Category category = null;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM category WHERE id = ?");
            cstm.setInt(1, id);
            rs = cstm.executeQuery();
            if(rs.next()){
                category = new Category();

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }

        }catch (SQLException e){
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return category;
    }

    public boolean createCategory(Category category, boolean isCreate) {
        boolean flag = false;
        try {
            if (isCreate) {
                cstm = con.prepareCall("INSERT INTO category(id, name) VALUES  (?,?)");
                cstm.setInt(1, category.getId());
                cstm.setString(2, category.getName());
            } else {
                cstm = con.prepareCall("UPDATE category SET name = ? WHERE id = ?");
                cstm.setString(1, category.getName());
                cstm.setInt(2, category.getId());
            }
            flag = cstm.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return flag;
    }
    public boolean delete(int id){
        boolean flag = false;

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("DELETE FROM category WHERE id = ?;");
            cstm.setInt(1, id);
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return flag;
    }
}
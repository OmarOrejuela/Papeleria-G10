package Productos;

import Conexion.Conexion;
import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicioProductos {
    public ArrayList<ModeloProductos> getProductos(){
        ArrayList<ModeloProductos> lista = new ArrayList<>();
        Conexion con = new Conexion();
        String sql = "select * from producto";
        
        try{
            Statement stm = con.getConnection().createStatement();
            ResultSet rse = stm.executeQuery(sql);
            while (rse.next()){
            ModeloProductos producto = new ModeloProductos();
            producto.setId_producto(rse.getInt("id_producto"));
            producto.setDescripcion(rse.getString("descripcion"));
            producto.setPrecio(rse.getFloat("precio"));
            lista.add(producto);}
        } catch(SQLException sqle){
            System.out.println("Error"+sqle.getMessage());}
        return lista;
    }

}
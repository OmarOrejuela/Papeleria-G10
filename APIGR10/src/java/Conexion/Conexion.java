package Conexion;

import java.sql.*;

public class Conexion {
    Connection con;
    Statement sql;
    ResultSet res;
    
        public static final String url = "jdbc:mysql://localhost:3306/almacen?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";  
        public static final String name = "com.mysql.cj.jdbc.Driver";  
        public static final String user = "root";  
        public static final String password = "";  
        
        public Connection getConnection(){
            try{
                Class.forName(name);
                System.out.println("El controlador se carg�");
            }   catch(ClassNotFoundException e) {
                System.out.println("error controlador"+e.getMessage());
            }
            try {
                con = DriverManager.getConnection(url,user,password);
                System.out.println("Conexi�n de base de datos exitosa");
            }   catch(SQLException sqle){
                System.out.println("error conexi�n base de datos "+sqle.getMessage());}
            return con;
        }
        public static void main(String[] args){
            Conexion c = new Conexion();
            c.getConnection();
        }
}

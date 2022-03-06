package com.unab.db;

import java.sql.*;

public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/Lab?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "perlera";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }

    public static void close(Statement st) throws SQLException{
        st.close();
    }

    public static void close(Connection cn) throws SQLException{
        cn.close();
    }
    
}

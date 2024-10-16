/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author Carlos Cotom
 */
public class ConexionDB {
    
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/app_revistas_bd";
    private static final String USER = "root";
    private static final String PASSWORD = "uipc2w8e9u1ner";
    
    private static ConexionDB UNICA_INSTANCIA_CONEXION;
    
    private DataSource datasource;

    private ConexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            PoolProperties p = new PoolProperties();
            p.setUrl(URL_MYSQL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(USER);
            p.setPassword(PASSWORD);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource(p);
        } catch (ClassNotFoundException e) {
            System.out.println("Error en el Pool de Conexiones = " + e.getMessage());
        }
    }
    
    public static ConexionDB getInstance() {
        if (UNICA_INSTANCIA_CONEXION == null) {
            UNICA_INSTANCIA_CONEXION = new ConexionDB();
            System.out.println("Conexion Exitosa");
        }
        return UNICA_INSTANCIA_CONEXION;
    }
    
    public DataSource getDataResource() {
        return datasource;
    }
    
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }
    
}

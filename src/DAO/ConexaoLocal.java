/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f2419488
 */
public class ConexaoLocal {

    private Connection conn;
    private boolean conectado;
    private String driver;
    private String banco;
    private String host;
    private String url;
    private String usuario;
    private String senha;

    //conexão no BD
    public ConexaoLocal() {
        this.conn = null;
        this.conectado = false;
        this.driver = "com.mysql.jdbc.Driver"; //Classe do driver JDBC
        this.banco = "bdjudicial"; ///servidor
        //this.banco = "cadernogestaoarrecadacao"; ///servidor
        this.host = "localhost"; //ip do banco de dados
        //this.host = "172.29.50.88"; //ip do banco de dados
        //this.host = "172.29.15.144"; //ip do banco de dados-novo 28/05/2013
        this.url = "jdbc:mysql://" + host + ":3306/" + banco; //URL de conexão
        //this.usuario = username;
        //this.senha = password;
        //this.usuario = "userjud";
        //this.senha = "userjud1981";
        this.usuario = "root";
        this.senha = "root";
    }

    //Conexão local//
  /*  public Conexao() {
     this.conn = null;
     this.conectado = false;
     this.driver = "com.mysql.jdbc.Driver"; //Classe do driver JDBC
     // this.banco = "arrec"; ///servidor
     this.banco = "cadernogestaoarrecadacao"; ///servidor
     this.host = "localhost"; //ip do banco de dados
     //  this.host = "172.29.50.88"; //ip do banco de dados
     this.url = "jdbc:mysql://" + host + ":3306/" + banco; //URL de conexão
     //    this.usuario = username;
     //    this.senha = password;
     //  this.usuario = "CDC";
     //  this.senha = "CDC";
     this.usuario = "root";
     this.senha = "";
     }*/
   // public Conexao() {
    //throw new UnsupportedOperationException("Not yet implemented");
    //}
    public boolean autenticar() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoLocal.class.getName()).log(Level.SEVERE, null, ex);
            conectado = false;
            return conectado;
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoLocal.class.getName()).log(Level.SEVERE, null, ex);
            conectado = false;
            return conectado;
        }
        conectado = true;
        return conectado;
    }

    public Connection conectar() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoLocal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}

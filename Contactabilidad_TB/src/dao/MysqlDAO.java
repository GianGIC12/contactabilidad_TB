/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class MysqlDAO {

    Connection con;
    String gestor, bd, user, pass;

    public MysqlDAO() {

        gestor = "db.todobusco.com";
        bd = "db_todobusco_prod";
        user = "u_tbusco_des";
        pass = "UNguphiehaes1a";

    }

    public void conectar() throws SQLException {

        String url = "jdbc:mysql://" + gestor + ":3306/" + bd;
        con = (Connection) DriverManager.getConnection(url, user, pass);
    }

    public void desconectar() throws SQLException {

        con.close();

    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}

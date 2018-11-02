/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 *
 * @author user
 */
public class MongoDAO {

    MongoClient mongoC;
    String gestor, bd, user, pass;
    DB database = null;

    public MongoDAO() {

        gestor = "mdb.todobusco.com";
        bd = "mdb_todobusco_prod";
        user = "u_todobusco_prod";
        pass = "Shai7te5aesheu";

    }

    public void conectar() {

        MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + pass + "@" + gestor + ":27017" + "/" + bd);
        mongoC = new MongoClient(uri);
        database = mongoC.getDB(bd);

    }

    public void desconectar() {

        mongoC.close();

    }

    public MongoClient getMongoC() {
        return mongoC;
    }

    public void setMongoC(MongoClient mongoC) {
        this.mongoC = mongoC;
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

    public DB getDatabase() {
        return database;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

}

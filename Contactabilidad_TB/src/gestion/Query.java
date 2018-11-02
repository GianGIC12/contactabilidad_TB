/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion;

import beans.AvisoBean;
import beans.ListaBean;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import dao.MongoDAO;
import dao.MysqlDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Query {

    String[] fechas;
    int[] idFechas;
    String[] fechas2;
    String sql;

    List<AvisoBean> avisos;

    MongoDAO mongo;

    public Query() {

        fechas = new String[366];
        fechas2 = new String[366];
        idFechas = new int[366];

        avisos = new ArrayList<>();

    }

    public void llenarFechas() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        String fecha = dateFormat.format(date);
        String annio = fecha.substring(0, 4);
        int aux = Integer.parseInt(annio) - 1;

        fechas[0] = aux + "-12-31";

        int contador = 0;

        for (int i = 1; i <= 12; i++) {

            String mes = "-0";

            if (i >= 10) {
                mes = "-";
            }

            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {

                for (int j = 1; j <= 31; j++) {
                    contador++;

                    if (j < 10) {

                        fechas[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas[contador] = annio + mes + i + "-" + j;

                    }

                }

            } else if (i == 2) {

                for (int j = 1; j <= 28; j++) {
                    contador++;

                    if (j < 10) {

                        fechas[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas[contador] = annio + mes + i + "-" + j;

                    }

                }

            } else {

                for (int j = 1; j <= 30; j++) {
                    contador++;

                    if (j < 10) {

                        fechas[contador] = annio + mes + i + "-0" + j;

                    } else {

                        fechas[contador] = annio + mes + i + "-" + j;

                    }

                }

            }

        }

    }

    public void listarfechas() {

        for (int i = 0; i < fechas.length; i++) {

            System.out.println(fechas[i]);
        }

    }

    public void llenarFechas2() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        String fecha = dateFormat.format(date);
        String annio = fecha.substring(0, 4);
        int aux = Integer.parseInt(annio) - 1;

        fechas2[0] = "31/12/" + aux;
        idFechas[0] = aux * 10000 + 1200 + 31;

        int contador = 0;

        for (int i = 1; i <= 12; i++) {

            String mes = "/0";

            if (i >= 10) {
                mes = "/";
            }

            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {

                for (int j = 1; j <= 31; j++) {
                    contador++;

                    if (j < 10) {

                        fechas2[contador] = "0" + j + mes + i + "/" + annio;
                        idFechas[contador] = Integer.parseInt(annio) * 10000 + i * 100 + j;

                    } else {

                        fechas2[contador] = j + mes + i + "/" + annio;
                        idFechas[contador] = Integer.parseInt(annio) * 10000 + i * 100 + j;

                    }

                }

            } else if (i == 2) {

                for (int j = 1; j <= 28; j++) {
                    contador++;

                    if (j < 10) {

                        fechas2[contador] = "0" + j + mes + i + "/" + annio;
                        idFechas[contador] = Integer.parseInt(annio) * 10000 + i * 100 + j;

                    } else {

                        fechas2[contador] = j + mes + i + "/" + annio;
                        idFechas[contador] = Integer.parseInt(annio) * 10000 + i * 100 + j;

                    }

                }

            } else {

                for (int j = 1; j <= 30; j++) {
                    contador++;

                    if (j < 10) {

                        fechas2[contador] = "0" + j + mes + i + "/" + annio;
                        idFechas[contador] = Integer.parseInt(annio) * 10000 + i * 100 + j;

                    } else {

                        fechas2[contador] = j + mes + i + "/" + annio;
                        idFechas[contador] = Integer.parseInt(annio) * 10000 + i * 100 + j;

                    }

                }

            }

        }

    }

    public void listarfechas2() {

        for (int i = 0; i < fechas2.length; i++) {

            System.out.println(fechas2[i]);
        }

    }

    public void listarIdFechas() {

        for (int i = 0; i < idFechas.length; i++) {

            System.out.println(idFechas[i] + "*****");
        }

    }

    public void obtenerContactabilidad(int num) {

        num = num - 1;

        String fechaB = fechas2[num];

        System.out.println("**********" + fechaB + "************");

        mongo = new MongoDAO();
        mongo.conectar();

        DBCollection coleccion = mongo.getDatabase().getCollection("mod_statistic_by_announcement");
        BasicDBObject query = new BasicDBObject("fechaFormat", fechaB);

        DBCursor cursor = coleccion.find(query);

        int i = 0;

        int idAviso = 0;
        int idPerfil = 0;
        int idUser = 0;
        int idPais = 0;
        int idPortal = 0;
        int idCategoria = 0;
        int idSubCategoria = 0;

        int destaque = 0;
        int visitas = 0;
        int contactos = 0;
        int mensajes = 0;
        int seephone = 0;
        int favoritos = 0;
        int call = 0;
        int whatsapp = 0;
        int origen = 0;
        int estado = 1;

        String fecha = fechaB;
        int idFecha = idFechas[num];

        while (cursor.hasNext()) {

            i++;

            DBObject dbo = cursor.next();

            AvisoBean aviso = new AvisoBean();
            ListaBean lista = new ListaBean();

            idAviso = Integer.parseInt(dbo.get("idAviso").toString());
            idPerfil = Integer.parseInt(dbo.get("idPerfil").toString());
            idUser = Integer.parseInt(dbo.get("idUser").toString());
            idPais = Integer.parseInt(dbo.get("idPais").toString());
            idPortal = Integer.parseInt(dbo.get("idPortal").toString());
            idCategoria = Integer.parseInt(dbo.get("idCategoria").toString());
            idSubCategoria = Integer.parseInt(dbo.get("idSubCategoria").toString());

            destaque = Integer.parseInt(dbo.get("destaque").toString());
            visitas = Integer.parseInt(dbo.get("visitas").toString());
            contactos = Integer.parseInt(dbo.get("contactos").toString());
            mensajes = Integer.parseInt(dbo.get("mensajes").toString());
            seephone = Integer.parseInt(dbo.get("seephone").toString());
            favoritos = Integer.parseInt(dbo.get("favoritos").toString());
            call = Integer.parseInt(dbo.get("call").toString());
            whatsapp = Integer.parseInt(dbo.get("whastapp").toString());

            fecha = dbo.get("fechaFormat").toString();

            aviso.setIdAviso(idAviso);
            aviso.setIdPerfil(idPerfil);
            aviso.setIdUser(idUser);
            aviso.setIdPais(idPais);
            aviso.setIdPortal(idPortal);
            aviso.setIdCategoria(idCategoria);
            aviso.setIdSubCategoria(idSubCategoria);

            aviso.setDestaque(destaque);
            aviso.setVisitas(visitas);
            aviso.setContactos(contactos);
            aviso.setMensajes(mensajes);
            aviso.setSeephone(seephone);
            aviso.setFavoritos(favoritos);
            aviso.setCall(call);
            aviso.setWhatsapp(whatsapp);

            aviso.setFecha(fecha);
            aviso.setIdFecha(idFecha);

            aviso.setOrigen(origen);
            aviso.setEstado(estado);

            lista.setId(idAviso);

            avisos.add(aviso);

        //    System.out.println(i+" : "+aviso.getIdAviso());
        }

        mongo.desconectar();

    }

    public void listarContactabilidad() {

        int j = 0;
        for (AvisoBean aviso : avisos) {
            j++;

            System.out.println(j + " : "
                    + " idAviso: " + aviso.getIdAviso()
                    + " idPerfil: " + aviso.getIdPerfil()
                    + " idUser: " + aviso.getIdUser()
                    + " idPais: " + aviso.getIdPais()
                    + " idPortal: " + aviso.getIdPortal()
                    + " idCategoria: " + aviso.getIdCategoria()
                    + " idSubCategoria: " + aviso.getIdSubCategoria()
                    + " destaque: " + aviso.getDestaque()
                    + " visitas: " + aviso.getVisitas()
                    + " contactos: " + aviso.getContactos()
                    + " mensajes: " + aviso.getMensajes()
                    + " seephone: " + aviso.getSeephone()
                    + " favoritos: " + aviso.getFavoritos()
                    + " call: " + aviso.getCall()
                    + " whatsapp: " + aviso.getWhatsapp()
                    + " fecha: " + aviso.getFecha()
                    + " idFecha: " + aviso.getIdFecha()
                    + " origen: " + aviso.getOrigen()
                    + " estado: " + aviso.getEstado()
            );

        }

    }

    public void obtenerStock(int num) throws SQLException {

        MysqlDAO m = new MysqlDAO();
        m.conectar();

        num = num - 1;

        String fechaB = fechas2[num];

        sql = "select a.id,a.idPerfil,p.idUser,a.idPais,a.idCategoria,a.idSubcategoria,a.destaque,a.estado "
                + " from db_todobusco_prod.mod_aviso_aviso as a "
                + " join db_todobusco_prod.mod_usuario_perfil as p "
                + " on a.idPerfil=p.id "
                + " where  (a.estado='10' or a.estado='11' or a.estado='1') ";

        PreparedStatement stm = m.getCon().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        int k = 0;

        int idAviso = 0;
        int idPerfil = 0;
        int idUser = 0;
        int idPais = 0;
        int idPortal = 0;
        int idCategoria = 0;
        int idSubCategoria = 0;

        int destaque = 0;
        int visitas = 0;
        int contactos = 0;
        int mensajes = 0;
        int seephone = 0;
        int favoritos = 0;
        int call = 0;
        int whatsapp = 0;
        int origen = 1;
        int estado = 1;

        String fecha = fechaB;
        int idFecha = idFechas[num];

        while (rs.next()) {
            k++;

            idAviso = rs.getInt(1);
            idPerfil = rs.getInt(2);
            idUser = rs.getInt(3);
            idPais = rs.getInt(4);
            idCategoria = rs.getInt(5);

            if (idCategoria == 1) {
                idPortal = 2;

            } else if (idCategoria == 2) {
                idPortal = 3;
            } else {
                idPortal = 4;
            }
            idSubCategoria = rs.getInt(6);
            destaque = rs.getInt(7);
            estado = rs.getInt(8);

            AvisoBean aviso = new AvisoBean();

            aviso.setIdAviso(idAviso);
            aviso.setIdPerfil(idPerfil);
            aviso.setIdUser(idUser);
            aviso.setIdPais(idPais);
            aviso.setIdPortal(idPortal);
            aviso.setIdCategoria(idCategoria);
            aviso.setIdSubCategoria(idSubCategoria);

            aviso.setDestaque(destaque);
            aviso.setVisitas(visitas);
            aviso.setContactos(contactos);
            aviso.setMensajes(mensajes);
            aviso.setSeephone(seephone);
            aviso.setFavoritos(favoritos);
            aviso.setCall(call);
            aviso.setWhatsapp(whatsapp);

            aviso.setFecha(fecha);
            aviso.setIdFecha(idFecha);

            aviso.setOrigen(origen);
            aviso.setEstado(estado);

            avisos.add(aviso);

            System.out.println(k + " : " + aviso.getIdAviso());

        }

        m.desconectar();

    }

    public String[] getFechas() {
        return fechas;
    }

    public void setFechas(String[] fechas) {
        this.fechas = fechas;
    }

    public int[] getIdFechas() {
        return idFechas;
    }

    public void setIdFechas(int[] idFechas) {
        this.idFechas = idFechas;
    }

    public String[] getFechas2() {
        return fechas2;
    }

    public void setFechas2(String[] fechas2) {
        this.fechas2 = fechas2;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<AvisoBean> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<AvisoBean> avisos) {
        this.avisos = avisos;
    }

    public MongoDAO getMongo() {
        return mongo;
    }

    public void setMongo(MongoDAO mongo) {
        this.mongo = mongo;
    }

}

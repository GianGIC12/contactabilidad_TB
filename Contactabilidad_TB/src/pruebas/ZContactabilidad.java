/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import archivos.ExportarCSV;
import gestion.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author user
 */
public class ZContactabilidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        // TODO code application logic here

        Calendar calendar = Calendar.getInstance();

        int num_dia = calendar.get(Calendar.DAY_OF_YEAR);

        System.out.println("" + num_dia);

        System.out.println("estamos en el día: " + num_dia);

        Query q = new Query();

        q.llenarFechas();
        //  q.listarfechas();

        System.out.println("*********************");

        q.llenarFechas2();
        //  q.listarfechas2();

        System.out.println("*************************");

     //   q.listarIdFechas();
        q.obtenerContactabilidad(num_dia);
        q.listarContactabilidad();

        System.out.println("esta es la lista mongo");

     //  q.listarMysql();
        q.obtenerStock(num_dia);
        //  q.listarStock();
        q.listarContactabilidad();

        ExportarCSV e = new ExportarCSV();

        e.exportar(q.getAvisos(), q.getIdFechas()[num_dia - 1]);

    }

}

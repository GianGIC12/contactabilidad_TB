/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import beans.AvisoBean;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author user
 */
public class ExportarCSV {

    public void exportar(List<AvisoBean> avisos, int idFecha) throws IOException {

        String outputFile = "C:/Users/user/Desktop/Retiros_DepositosAT/bd_todoBusco" + idFecha + ".csv";

        boolean alreadyExists = new File(outputFile).exists();

        if (alreadyExists) {
            File bd_detalle_10 = new File(outputFile);
            bd_detalle_10.delete();
        }

        CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

        csvOutput.write("IdAviso");
        csvOutput.write("IdPerfil");
        csvOutput.write("IdUser");
        csvOutput.write("IdPais");
        csvOutput.write("IdPortal");
        csvOutput.write("IdCategoria");
        csvOutput.write("IdSubCategoria");
        csvOutput.write("Destaque");

        csvOutput.write("Visitas");
        csvOutput.write("Contactos");
        csvOutput.write("Mensajes");
        csvOutput.write("Seephone");
        csvOutput.write("Favoritos");
        csvOutput.write("Call");
        csvOutput.write("Whatsapp");

        csvOutput.write("Fecha");
        csvOutput.write("IdFecha");
        csvOutput.write("Origen");
        csvOutput.write("Estado");

        csvOutput.endRecord();

        for (AvisoBean aviso : avisos) {

            csvOutput.write(aviso.getIdAviso() + "");
            csvOutput.write(aviso.getIdPerfil() + "");
            csvOutput.write(aviso.getIdUser() + "");
            csvOutput.write(aviso.getIdPais() + "");
            csvOutput.write(aviso.getIdPortal() + "");
            csvOutput.write(aviso.getIdCategoria() + "");
            csvOutput.write(aviso.getIdSubCategoria() + "");
            csvOutput.write(aviso.getDestaque() + "");

            csvOutput.write(aviso.getVisitas() + "");
            csvOutput.write(aviso.getContactos() + "");
            csvOutput.write(aviso.getMensajes() + "");
            csvOutput.write(aviso.getSeephone() + "");
            csvOutput.write(aviso.getFavoritos() + "");
            csvOutput.write(aviso.getCall() + "");
            csvOutput.write(aviso.getWhatsapp() + "");

            csvOutput.write(aviso.getFecha());
            csvOutput.write(aviso.getIdFecha() + "");
            csvOutput.write(aviso.getOrigen() + "");
            csvOutput.write(aviso.getEstado() + "");

            csvOutput.endRecord();

        }

        csvOutput.close();

    }

}

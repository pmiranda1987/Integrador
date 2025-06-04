package com.mycompany.integrador;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerPDF {
    
    public void leerExcelEnTabla(JTable TblClientes) {
    String archivo = "DatosFormulario.xlsx"; // o puedes usar ruta completa
    File file = new File(archivo);

    if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "El archivo no existe.");
        return;
    }
    try (FileInputStream fis = new FileInputStream(file);
         Workbook workbook = new XSSFWorkbook(fis)) {

        Sheet sheet = workbook.getSheetAt(0);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Nombre", "Edad", "Email"});

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Empieza en 1 (salta encabezado)
            Row row = sheet.getRow(i);
            if (row != null) {
                String nombre = row.getCell(0).getStringCellValue();
                int edad = (int) row.getCell(1).getNumericCellValue();
                String email = row.getCell(2).getStringCellValue();
                modelo.addRow(new Object[]{nombre, edad, email});
            }
        }

        TblClientes.setModel(modelo);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
    }
}
}

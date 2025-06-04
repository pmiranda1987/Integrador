/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportarPDF {
    
    private String nombre, email;
    private int edad;

    public ExportarPDF(String nombre, String email, int edad) {
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }
    
    public void guardarexcel(){
    String archivo = "DatosFormulario.xlsx";
     Workbook workbook;
    Sheet sheet;

    File file = new File(archivo);
    if (file.exists()) {
        try (FileInputStream fis = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo existente: " + e.getMessage());
            return;
        }
    } else {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Clientes");

        // Crear encabezado si el archivo es nuevo
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Nombre");
        header.createCell(1).setCellValue("Edad");
        header.createCell(2).setCellValue("Email");
    }

    // Agregar nueva fila al final
    int ultimaFila = sheet.getLastRowNum() + 1;
    Row nuevaFila = sheet.createRow(ultimaFila);
    nuevaFila.createCell(0).setCellValue(nombre);
    nuevaFila.createCell(1).setCellValue(edad);
    nuevaFila.createCell(2).setCellValue(email);

    try (FileOutputStream outputStream = new FileOutputStream(archivo)) {
        workbook.write(outputStream);
        workbook.close();
        JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
    }     
    }
}

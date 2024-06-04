package modelo;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exportxls {

	public void exportarAExcel(TableModel tableModel) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Datos de stock exportados");

		// Crear la fila de encabezado
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(tableModel.getColumnName(i));
		}

		// Crear las filas de datos
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			Row row = sheet.createRow(i + 1);
			for (int j = 0; j < tableModel.getColumnCount(); j++) {
				Cell cell = row.createCell(j);
				Object value = tableModel.getValueAt(i, j);
				if (value instanceof Number) {
					cell.setCellValue(((Number) value).doubleValue());
				} else {
					cell.setCellValue(value.toString());
				}
			}
		}

		// Escribir el archivo
		try (FileOutputStream out = new FileOutputStream("datos_exportados.xlsx")) {
			workbook.write(out);
			System.out.println("Excel exportado con éxito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
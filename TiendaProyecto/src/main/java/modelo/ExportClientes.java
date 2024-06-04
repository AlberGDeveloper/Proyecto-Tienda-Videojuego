package modelo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportClientes {

	public void exportarAExcel(TableModel tableModel) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Datos de clientes exportados");

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
				if (value != null) { // Verificar si el valor es null
					if (value instanceof Number) {
						cell.setCellValue(((Number) value).doubleValue());
					} else {
						cell.setCellValue(value.toString());
					}
				} else {
					cell.setCellValue(""); // Asignar un string vacío si el valor es null
				}
			}
		}

		// Escribir el archivo
		try (FileOutputStream out = new FileOutputStream("datos_exportados2.xlsx")) {
			workbook.write(out);
			System.out.println("Excel exportado con éxito.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
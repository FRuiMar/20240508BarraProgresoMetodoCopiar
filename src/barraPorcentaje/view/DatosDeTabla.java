package barraPorcentaje.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DatosDeTabla {
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	

	public static String[] getTitulosColumnas() {
		return new String[] {"Nombre", "Tamaño", "Última Modificación"};
	}
	
	/**
	 * Método de Rafa.
	 * @return
	 */
	public static Object[][] getDatosDeTabla(List<File> ficheros) {
		
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[ficheros.size()][3];
		
		// Cargo los datos de la lista de ficheros en la matriz de los datos
		for (int i = 0; i < ficheros.size(); i++) {
			File f = ficheros.get(i);
			// Obtenemos el nombre del fichero.
			datos[i][0] = f.getName();
			// Obtenemos el tamaño del fichero en KB.(al final no hace falta dividirlo por 8)
			// se pone float para mostrar más decimales.
			datos[i][1] = (float) f.length() /1024 + "KB";
			// Obtenemos la última fecha de modificación.
			Date fecha = new Date(f.lastModified());
			datos[i][2] = sdf.format(fecha);
		}
		
		return datos;
	}
	
	
}
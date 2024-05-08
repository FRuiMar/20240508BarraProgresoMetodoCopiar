package barraPorcentaje;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;

public class BarritaCopiaYPega extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfOrigen;
	private JTextField jtfDestino;
	JFileChooser jfileChooser;
	JProgressBar progressBar; 
	
	
	/**
	 * Create the panel.
	 */
	public BarritaCopiaYPega() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Copiado del contenido de carpetas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(10, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carpeta Origen");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfOrigen = new JTextField();
		GridBagConstraints gbc_jtfOrigen = new GridBagConstraints();
		gbc_jtfOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_jtfOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfOrigen.gridx = 1;
		gbc_jtfOrigen.gridy = 1;
		add(jtfOrigen, gbc_jtfOrigen);
		jtfOrigen.setColumns(10);
		
		JButton jbtOrigen = new JButton("Selecciona carpeta origen");
		jbtOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaCarpetaOrigen();
			}
		});
		GridBagConstraints gbc_jbtOrigen = new GridBagConstraints();
		gbc_jbtOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_jbtOrigen.gridx = 2;
		gbc_jbtOrigen.gridy = 1;
		add(jbtOrigen, gbc_jbtOrigen);
		
		JLabel lblNewLabel_2 = new JLabel("Carpeta Destino");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfDestino = new JTextField();
		GridBagConstraints gbc_jtfDestino = new GridBagConstraints();
		gbc_jtfDestino.insets = new Insets(0, 0, 5, 5);
		gbc_jtfDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDestino.gridx = 1;
		gbc_jtfDestino.gridy = 2;
		add(jtfDestino, gbc_jtfDestino);
		jtfDestino.setColumns(10);
		
		JButton jbtDestino = new JButton("Selecciona carpeta destino");
		jbtDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaCarpetaDestino();
			}
		});
		GridBagConstraints gbc_jbtDestino = new GridBagConstraints();
		gbc_jbtDestino.insets = new Insets(0, 0, 5, 5);
		gbc_jbtDestino.gridx = 2;
		gbc_jbtDestino.gridy = 2;
		add(jbtDestino, gbc_jbtDestino);
		
		JButton jbtCopiar = new JButton("Copiar Ficheros");
		jbtCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Copia los archivos de la carpeta origen a la carpeta destino
	            try {
	            	copiaCarpetas(new File(jtfOrigen.getText()), new File(jtfDestino.getText()));
	            	JOptionPane.showMessageDialog(null, "Copia realizada con éxito");
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
			}
		});
		GridBagConstraints gbc_jbtCopiar = new GridBagConstraints();
		gbc_jbtCopiar.insets = new Insets(0, 0, 5, 0);
		gbc_jbtCopiar.gridwidth = 3;
		gbc_jbtCopiar.gridx = 0;
		gbc_jbtCopiar.gridy = 3;
		add(jbtCopiar, gbc_jbtCopiar);
		
		progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		progressBar.setStringPainted(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridwidth = 3;
		gbc_progressBar.insets = new Insets(15, 5, 0, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 4;
		add(progressBar, gbc_progressBar);

	}

	
	/**
	 * 
	 */
	private void seleccionaCarpetaOrigen() {
		
		 progressBar.setValue(0); // Pongo el valor de la barra de progreso a 0 para la siguiente copia
		
		this.jfileChooser = new JFileChooser();

		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));

		// Tipo de selección que se hace en el diálogo
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sólo selecciona ficheros

		// Filtro del tipo de ficheros que puede abrir (en este caso directorios)
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Sólo puedes seleccionar carpetas o directorios";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return false;
			}
		});

		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = this.jfileChooser.showOpenDialog(null);

		// Compruebo que se ha seleccionado el directorio de forma correcta.
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File directorio = this.jfileChooser.getSelectedFile();

			// actualizamos la ruta en el jtextfield.
			this.jtfOrigen.setText(directorio.getAbsolutePath());

		}

	}

	/**
	 * 
	 */
	private void seleccionaCarpetaDestino() {
		this.jfileChooser = new JFileChooser();

		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));

		// Tipo de selección que se hace en el diálogo
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sólo selecciona ficheros

		// Filtro del tipo de ficheros que puede abrir (en este caso directorios)
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Sólo puedes seleccionar carpetas o directorios";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return false;
			}
		});

		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = this.jfileChooser.showOpenDialog(null);

		// Compruebo que se ha seleccionado el directorio de forma correcta.
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File directorio = this.jfileChooser.getSelectedFile();

			// actualizamos la ruta en el jtextfield.
			this.jtfDestino.setText(directorio.getAbsolutePath());

		}

	}

	
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	private void copiaCarpetas(File source, File destination) throws IOException {

		// Verifica si la carpeta de destion existe, si no, 
		// la creamos...(por si escribes en el jtextfield). 
		if (!destination.exists()) {
			destination.mkdirs();
		}

		
		////  -----   BARRA DE PROGRESO  PARTE 1--------  ////
		 // Obtengo la lista de archivos de la carpeta origen
	    File[] files = source.listFiles();
	    // Calcula el número total de archivos a copiar
	    int totalFiles = files.length;
	    // Inicializo el progreso de la barra
	    int progreso = 0;
		
		
		
		// Hago un for-each para iterar sobre los archivos en la carpeta origen
		for (File file : source.listFiles()) {
			// Crea la ruta de destino para el archivo
			Path destPath = new File(destination, file.getName()).toPath();

	
			
	        // Verifico si el archivo ya existe en el destino
	        if (Files.exists(destPath)) {
	            // Si ya existe, pregunta al usuario si desea reemplazarlo
	            int opcion = JOptionPane.showConfirmDialog(null, "El archivo " + file.getName() + " ya existe en la carpeta de destino. ¿Desea reemplazarlo?", "Confirmar reemplazo", JOptionPane.YES_NO_OPTION);
	            
	            // Si el usuario elige no reemplazar, salta este archivo y continúa con el siguiente
	            if (opcion != JOptionPane.YES_OPTION) {
	                continue;
	            }
	        }
			
					
			// Copio el archivo a la carpeta de destino  (añado el StandardCopyOption por si es necesario reemplazar el archivo).
			Files.copy(file.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
			
		////  -----   BARRA DE PROGRESO  PARTE 2 --------  ////
			 // Incrementa el progreso
	        progreso++;
	        // Calcula el porcentaje completado
	        int percentage = (int) ((double) progreso / totalFiles * 100);
	        // Actualiza el valor del progreso en el JProgressBar
	        progressBar.setValue(percentage);
			
			
		}

	}
	
	
	
	
	
}

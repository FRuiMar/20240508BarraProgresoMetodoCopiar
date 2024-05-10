package barraPorcentaje.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JSlider;

public class PanelSliderTamanoArchivoYTabla extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpeta;
	private JTable table;

	private JFileChooser jfileChooser;
	private JScrollPane scrollPane;
	private List<File> ficheros = null;
	private JSlider slider;
	JLabel jlabelSlider;
	JLabel jlblNumFiles;

	// es el tamaño indicado en el JSlider.
	private int sizeSelected;

	/**
	 * Create the panel.
	 */
	public PanelSliderTamanoArchivoYTabla() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Búsqueda de ficheros por tamaño");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(7, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Carpeta: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		jtfCarpeta = new JTextField();
		GridBagConstraints gbc_jtfCarpeta = new GridBagConstraints();
		gbc_jtfCarpeta.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpeta.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpeta.gridx = 1;
		gbc_jtfCarpeta.gridy = 1;
		add(jtfCarpeta, gbc_jtfCarpeta);
		jtfCarpeta.setColumns(10);

		JButton jbtCarpeta = new JButton("Selecciona carpeta");
		jbtCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionaCarpeta();
			}
		});
		GridBagConstraints gbc_jbtCarpeta = new GridBagConstraints();
		gbc_jbtCarpeta.gridwidth = 2;
		gbc_jbtCarpeta.insets = new Insets(0, 0, 5, 0);
		gbc_jbtCarpeta.gridx = 2;
		gbc_jbtCarpeta.gridy = 1;
		add(jbtCarpeta, gbc_jbtCarpeta);

		JLabel lblNewLabel_2 = new JLabel("Tamaño del fichero");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		//
		slider = new JSlider();
		slider.setValue(0); // valor inicial
		slider.setPaintLabels(false);// al final no uso esto, no aparece en el dibujo.
		slider.setMaximum(5); // valor máximo. de 0 a 5 son 6 posiciones.
		slider.setMinorTickSpacing(1); // nos movemos de uno en uno
		slider.setMajorTickSpacing(1); // nos movemos de uno en uno
		slider.setPaintTicks(true); // pintamos las señales de división. No viene en el ejercicio, pero se lo voy a
									// dejar para mayor claridad.
		slider.addChangeListener(new ChangeListener() { // este es el método de Fran, yo lo ponía directamente, pero
														// luego tienes que usarlo por separado
			@Override // así que mejor pones el método a parte.
			public void stateChanged(ChangeEvent e) {
				getSizeSelected(slider.getValue()); // sacamos el valor que le pasamos al método getSizeSelected
				cargaArchivosenTabla(); // cargamos archivos en tabla, de forma que cada vez que movemos el Slider se
										// carge la nueva lista con la información correspondiente.
			}
		});
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 2;
		add(slider, gbc_slider);

		jlabelSlider = new JLabel("");
		GridBagConstraints gbc_jlabelSlider = new GridBagConstraints();
		gbc_jlabelSlider.insets = new Insets(0, 0, 5, 5);
		gbc_jlabelSlider.gridx = 2;
		gbc_jlabelSlider.gridy = 2;
		add(jlabelSlider, gbc_jlabelSlider);

		jlblNumFiles = new JLabel("");
		GridBagConstraints gbc_jlblNumFiles = new GridBagConstraints();
		gbc_jlblNumFiles.insets = new Insets(0, 0, 5, 0);
		gbc_jlblNumFiles.gridx = 3;
		gbc_jlblNumFiles.gridy = 2;
		add(jlblNumFiles, gbc_jlblNumFiles);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// Al inicio, se mostrará el valor inicial del JSlider
		// como el número de ficheros mostrados (0).
		jlabelSlider.setText("> " + slider.getValue() + " KB");
		jlblNumFiles.setText("(0 ficheros)");
	}

	private void seleccionaCarpeta() {

		// Configurando el componente
		this.jfileChooser = new JFileChooser();

		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));

		// Tipo de selección que se hace en el diálogo
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// Filtro del tipo de ficheros que puede abrir
		this.jfileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Seleccione una carpeta para mostrar su contenido";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return false;
			}
		});

		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = jfileChooser.showOpenDialog(null);

		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File Carpeta = this.jfileChooser.getSelectedFile(); // aquí está todo.

			// Muestro la ruta de la carpeta en el JTextField
			this.jtfCarpeta.setText(Carpeta.getAbsolutePath());
		}

		// no hay botón, así que al seleccionar la carpeta, debemos cargar
		// automáticamente los datos en la tabla
		cargaArchivosenTabla();
	}

	/**
	 * literalmente el mismo que en el otro.
	 */
	private void cargaArchivosenTabla() {

		// Comprobamos si la ruta de carpeta está vacía.
		if (this.jtfCarpeta.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Seleccione una carpeta a visualizar");
			return;
		}

		// Obtenemos los ficheros de la carpeta
		ficheros = getFiles(this.jtfCarpeta.getText());   /// AQUÍ ESTA TODA LA INFO.

		// Inicializamos la tabla con los ficheros obtenidos.
		table = new JTable(DatosDeTabla.getDatosDeTabla(ficheros), DatosDeTabla.getTitulosColumnas());

		// la tabla la cargamos en el viewport.
		scrollPane.setViewportView(table);
		
		//AÑADIMOS QUE AL CARGAR LOS ARCHIVOS SE ACTUALICE LA CANTIDAD DE ARCHIVOS EN EL JLABEL
		jlblNumFiles.setText("(" + ficheros.size() + "ficheros)");
	}

	/**
	 * Copiado de Rafa, revisar. Yo lo tengo con source. en vez de crear folder.
	 * 
	 * @param ruta de la carpeta
	 * @return Lista parametrizada(File)
	 */
	private List<File> getFiles(String rutaCarpeta) {
		List<File> ficheros = new ArrayList<File>();
		File folder = new File(rutaCarpeta);

		// Verificamos que se ha seleccionado una carpeta.
		if (!folder.isDirectory()) {
			JOptionPane.showMessageDialog(null, "La ruta indicada no es una carpeta");
			return ficheros;
		}

		// Obtenemos los posibles ficheros de la carpeta.
		File[] files = folder.listFiles();
		if (files != null) {
			// Recorremos cada fichero.
			for (File file : files) {
				// Si es un fichero Y
//-------		// cumple con las condiciones del tamaño   ---////
				// seleccionado en el jSlider
				if (file.isFile() && ((float) file.length() / 1024) > sizeSelected) {
					ficheros.add(file);
				}
			}
		} else {
			System.err.println("No se han podido obtener los ficheros " + "de la carpeta seleccionada");
		}

		return ficheros;
	}

	/**
	 * Método copiado de Fran. En este caso tenemos que usar el tamaño seleccionado
	 * para filtrar los archivos que vamos a poner en la tabla por lo que tener el
	 * método a parte es muy cómodo. Y controlamos también el segundo jlabel.
	 * 
	 * @param option
	 */
	private void getSizeSelected(int option) {
		// Información Slider del tamaño de los ficheros.
		String txt = null;

		// Elegimos el texto a mostrar según el valor
		// del JSlider y el valor del tamaño elegido.
		switch (option) {
		case 0:
			txt = "> 0 KB";
			sizeSelected = 0; // 0 KB
			break;
		case 1:
			txt = "> 100 KB";
			sizeSelected = 100; // 100 KB
			break;
		case 2:
			txt = "> 1 MB";
			sizeSelected = 1024; // 1 MB en KB (1 MB = 1024 KB)
			break;
		case 3:
			txt = "> 10 MB";
			sizeSelected = 10 * 1024; // 10 MB en KB (1 MB = 1024 KB)
			break;
		case 4:
			txt = "> 100 MB";
			sizeSelected = 100 * 1024; // 100 MB en KB (1 MB = 1024 KB)
			break;
		case 5:
			txt = "> 1 GB";
			sizeSelected = 1024 * 1024; // 1 GB en KB (1 GB = 1024 MB)
			break;
		}

		// Actualizamos el label del JSlider.
		this.jlabelSlider.setText(txt);
	}

}

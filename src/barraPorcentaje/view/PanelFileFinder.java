package barraPorcentaje.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelFileFinder extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpeta;
	private JTextField jtfFiltro;
	private JTable table;
	
	
	private JFileChooser jfileChooser;
	private JScrollPane scrollPane;
	private List<File> ficheros = null;

	/**
	 * Create the panel.
	 */
	public PanelFileFinder() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Búsqueda de ficheros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
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
		gbc_jbtCarpeta.insets = new Insets(0, 0, 5, 0);
		gbc_jbtCarpeta.gridx = 2;
		gbc_jbtCarpeta.gridy = 1;
		add(jbtCarpeta, gbc_jbtCarpeta);
		
		JLabel lblNewLabel_2 = new JLabel("Texto en nombre: ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 1;
		gbc_jtfFiltro.gridy = 2;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		JButton jbtBuscarFiles = new JButton("Buscar ficheros");
		jbtBuscarFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargaArchivosenTabla();
			}
		});
		GridBagConstraints gbc_jbtBuscarFiles = new GridBagConstraints();
		gbc_jbtBuscarFiles.insets = new Insets(0, 0, 5, 0);
		gbc_jbtBuscarFiles.gridx = 2;
		gbc_jbtBuscarFiles.gridy = 2;
		add(jbtBuscarFiles, gbc_jbtBuscarFiles);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

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
			File Carpeta = this.jfileChooser.getSelectedFile();
			
			// Muestro la ruta de la carpeta en el JTextField
			this.jtfCarpeta.setText(Carpeta.getAbsolutePath());
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 */
	private void cargaArchivosenTabla() {
		
		// Comprobamos si la ruta de carpeta está vacía.
		if (this.jtfCarpeta.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Seleccione una carpeta a visualizar");
			return;
		}
		
		// Obtenemos los ficheros
		ficheros = getFiles(this.jtfCarpeta.getText());
		
		// Inicializamos la tabla con los ficheros obtenidos.
		table = new JTable(DatosDeTabla.getDatosDeTabla(ficheros),
				DatosDeTabla.getTitulosColumnas());
		
		//la tabla la cargamos en el viewport.
		scrollPane.setViewportView(table);
	}
	
	
	
	
	
	/**
	 *	Copiado de Rafa, revisar.
	 *  Yo lo tengo con source. en vez de crear folder.
	 * @param ruta de la carpeta
	 * @return Lista parametrizada(File)
	 */
	private List<File> getFiles(String rutaCarpeta) {
		List<File> ficheros = new ArrayList<File>();
		File folder = new File(rutaCarpeta);
		
		// Verificamos que se ha seleccionado una carpeta.
		if (!folder.isDirectory()) {
			JOptionPane.showMessageDialog(null, 
					"La ruta indicada no es una carpeta");
			return ficheros;
		}
		
		// Obtenemos los posibles ficheros de la carpeta.
		File[] files = folder.listFiles();
		if (files != null) {
			// Recorremos cada fichero.
			for (File file : files) {
				// Si es un archivo y
				// cumple con el filtrado indicado, se agregará
				// a la lista.
				if (file.isFile() && isFileFiltered(file)) {
					ficheros.add(file);
				}
			}
		} else {
			System.err.println(
					"No se han podido obtener los ficheros "
					+ "de la carpeta seleccionada");
		}
		
		return ficheros;
	}
	
	
	
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	private boolean isFileFiltered(File f) {
		String str = this.jtfFiltro.getText();
		
		// Si no ponemos nada en el filtro, es true, y carga todos los ficheros.
		if (str.trim().isEmpty()) {
			return true;
		}
		
		// Si coincide una substring con el nombre del fichero, lo carga en tabla. 
		if (f.getName().toUpperCase().contains(str.trim().toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	

	
}

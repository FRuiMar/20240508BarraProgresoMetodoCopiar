package barraPorcentaje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import barraPorcentaje.view.BarritaCopiaYPega;
import barraPorcentaje.view.PanelFileFinder;
import barraPorcentaje.view.PanelSliderTamanoArchivoYTabla;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	PanelFileFinder panelBuscador; 
	PanelSliderTamanoArchivoYTabla panelSlider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		super("Probando varias tabs");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		//CREO EL PANEL DE PESTAÑAS (TABS).
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH); // no sé si es necesario.
		
		
		//CREO LOS PANELES QUE VOY A CARGAR  EN EL PANEL DE TABS.
		BarritaCopiaYPega panelBarrita = new BarritaCopiaYPega();
		PanelFileFinder panelBuscador = new PanelFileFinder();
		PanelSliderTamanoArchivoYTabla panelSlider = new PanelSliderTamanoArchivoYTabla();
		
		//CREO LAS PESTAÑAS QUE SE VAN A AÑADIR Y PONGO NOMBRE VISIBLE DE LA PESTAÑA Y QUÉ PANEL CARGA.
		tabbedPane.addTab("Copiado de carpetas", panelBarrita);
		tabbedPane.addTab("Muestra archivos de carpeta", panelBuscador);
		tabbedPane.addTab("Busca archivos por tamaño", panelSlider);
		
		
		
	}

}

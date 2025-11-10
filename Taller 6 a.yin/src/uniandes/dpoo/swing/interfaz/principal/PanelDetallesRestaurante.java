package uniandes.dpoo.swing.interfaz.principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelDetallesRestaurante extends JPanel
{
    /**
     * La etiqueta donde se muestra el nombre de un restaurante
     */
    private JLabel labNombre;

    /**
     * La etiqueta donde se muestra la calificación de un restaurante, usando imágenes de estrellas
     */
    private JLabel labCalificacion;

    /**
     * Un checkbox en el que se muestra si un restaurante fue visitado o no
     */
    private JCheckBox chkVisitado;

    public PanelDetallesRestaurante() {
	    setLayout(new BorderLayout());
	
		
		JPanel panelCentro = new JPanel(new GridLayout(4, 1)); 
		
		// Nombre del restaurante
		JLabel labNombre1 = new JLabel("Nombre:");
		labNombre = new JLabel();
		labNombre.setFont(new Font("Arial", Font.BOLD, 14));
		
		// Calificación con estrellas
		JLabel cal1 = new JLabel("Calificacion:");
		labCalificacion = new JLabel();
		
		// Checkbox de visitado
		JLabel vis1 = new JLabel("Visitado:");
		chkVisitado = new JCheckBox();
		chkVisitado.setEnabled(false);
		
		JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nombrePanel.add(labNombre1);
		nombrePanel.add(labNombre);
		
		JPanel calificacionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		calificacionPanel.add(cal1);
		calificacionPanel.add(labCalificacion);
		
		JPanel visitadoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		visitadoPanel.add(vis1);
		visitadoPanel.add(chkVisitado);
		
		panelCentro.add(nombrePanel);
		panelCentro.add(calificacionPanel);
		panelCentro.add(visitadoPanel);
		
		add(panelCentro, BorderLayout.CENTER);
	}


    /**
     * Actualiza los datos mostrados del restaurante, indicando los valores por separado.
     * @param nombre
     * @param calificacion
     * @param visitado
     */
    private void actualizarRestaurante(String nombre, int calificacion, boolean visitado) {
        labNombre.setText(nombre);
        chkVisitado.setSelected(visitado);
        labCalificacion.setIcon(buscarIconoCalificacion(calificacion));
    }


    /**
     * Actualiza los datos que se muestran de un restaurante
     * @param r El restaurante que se debe mostrar
     */
    public void actualizarRestaurante( Restaurante r )
    {
        this.actualizarRestaurante( r.getNombre( ), r.getCalificacion( ), r.isVisitado( ) );
    }

    /**
     * Dada una calificación, retorna una imagen para utilizar en la etiqueta que muestra la calificación
     * @param calificacion La calificación del restaurante, que debe ser un numero entre 1 y 5.
     * @return Una imagen a la que corresponde la calificación
     */
    private ImageIcon buscarIconoCalificacion( int calificacion )
    {
        String imagen = "./imagenes/stars" + calificacion + ".png";
        return new ImageIcon( imagen );
    }
}

package gui.panel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controller.Controller;
import cpu.MVError;
import cpu.mv.out.OutStream;
import cpu.mv.out.OutStreamGUI;


/**
 * Clase que representa el panel de salida.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class OutputPanel extends JPanel {

	//Atributos.
	private static final long serialVersionUID = 1L;
	private Controller guiCtrl;
	private JTextArea outputTextArea;

	
	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param guiCtrl controlador.
	 */
	public OutputPanel(Controller guiCtrl){
		this.guiCtrl=guiCtrl;
		try {
			initGUI();
		} catch (MVError e) {
			e.printStackTrace();
		}
	}
	
	
	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI.
	 * 
	 * @throws MVError
	 */
	private void initGUI() throws MVError{
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("OUT"));
		this.outputTextArea = new JTextArea(BorderLayout.CENTER);
		this.outputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		this.outputTextArea.setEditable(false);
		this.add(new JScrollPane(outputTextArea));
		OutStream outCurr = guiCtrl.getOutStream();
		OutStream outNew = new OutStreamGUI( outCurr, outputTextArea); 
		guiCtrl.setOutStream(outNew); 
	}

}

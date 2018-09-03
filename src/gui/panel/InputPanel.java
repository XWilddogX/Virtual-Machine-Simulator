package gui.panel;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controller.Controller;
import cpu.MVError;
import cpu.mv.in.InStream;
import cpu.mv.in.InStreamGUI;


/**
 * Clase que representa el panel de entrada.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class InputPanel extends JPanel{

	//Atributos.
	private static final long serialVersionUID = 1L;
	private Controller guiCtrl;
	private JTextArea inputTextArea;


	//Constructora.
	/**
	 * Constructora por defecto.
	 * 
	 * @param ctrl controlador.
	 */
	public InputPanel(Controller ctrl){
		this.guiCtrl=ctrl;
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
		this.setBorder(new TitledBorder("IN"));
		this.inputTextArea = new JTextArea(BorderLayout.CENTER);
		this.inputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		this.inputTextArea.setEditable(false);
		this.add(new JScrollPane(inputTextArea));
		InStream inCurr = guiCtrl.getInStream();
		InStream inNew = new InStreamGUI( inCurr, inputTextArea); 
		guiCtrl.setInStream( inNew ); 
	}

}

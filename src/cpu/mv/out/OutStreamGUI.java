package cpu.mv.out;

import javax.swing.JTextArea;


/**
 * Clase que representa la estrategia de escritura de ventana. 
 * Implementa la interfaz OutStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class OutStreamGUI implements OutStream {

	//Atributos.
	private JTextArea outputTextArea;
	private OutStream old;
	private StringBuilder content;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param old Stream anterior.
	 * @param outputArea area de salida de texto.
	 */
	public OutStreamGUI (OutStream old, JTextArea outputArea){
		this.old = old;
		this.outputTextArea = outputArea;
		this.outputTextArea.setText("");
		this.content=new StringBuilder();
	}


	//Metodos.
	/**
	 * Metodo encargado de realizar la escritura.
	 */
	public void write(int value) {
		this.old.write(value);
		this.content.append((char) value);
		this.outputTextArea.setText(this.content.toString());
	}

	/**
	 * Metodo encargado de abrir el Stream.
	 */
	public void open() {this.old.open();}

	/**
	 * Metodo encargado de cerrar el Stream.
	 */
	public void close() {
		this.old.close();		
	}

}

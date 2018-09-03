package cpu.mv.in;

import javax.swing.JTextArea;


/**
 * Clase que representa la estrategia de lectura en ventana. 
 * Implementa la interfaz InStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class InStreamGUI implements InStream {

	//Atributos.
	private JTextArea inputTextArea;
	private InStream old;
	private StringBuilder content;
	private int pos;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param old Stream anterior
	 * @param inputTextArea area de entrada de texto.
	 */
	public InStreamGUI(InStream old, JTextArea inputTextArea){
		this.old = old;
		this.inputTextArea = inputTextArea;
		this.pos = 0;
		this.content=new StringBuilder();
		setContent();
	}


	//Metodos.
	/**
	 * Metodo encargado de modificar el parametro content.
	 */
	private void setContent(){
		int a;
		do	{
			a=this.old.read();
			if(a!=-1){
				this.content.append((char)a);
			}
		}
		while(a!=-1);
		this.inputTextArea.setText(this.content.toString());
	}

	/**
	 * Metodo encargado de realizar la lectura.
	 * 
	 * @return entero correspondiente al caracter leido.
	 */
	public int read() {
		int c=0;
		if( this.pos != content.length()){
			c=content.codePointAt(pos);
			if(c!=13 && c!=10 && c!=32){
				this.content.setCharAt(pos,'*');
				this.inputTextArea.setText(this.content.toString());
			}
			this.pos++;
			return c;
		}
		else
			return -1;	
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

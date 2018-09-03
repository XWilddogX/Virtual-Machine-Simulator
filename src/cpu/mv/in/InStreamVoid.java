package cpu.mv.in;


/**
 * Clase que representa la estrategia de lectura vacia. 
 * Implementa la interfaz InStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class InStreamVoid implements InStream {

	//Metodos.
	/**
	 * Metodo encargado de realizar la lectura vacia.
	 * @return entero correspondiente al caracter leido.
	 */
	public int read() {return -1;}

	/**
	 * Metodo encargado de abrir la lectura vacia.
	 */
	public void open() {}

	/**
	 * Metodo encargado de cerrar la lectura vacia.
	 */
	public void close() {}

}

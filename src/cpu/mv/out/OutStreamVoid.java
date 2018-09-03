package cpu.mv.out;


/**
 * Clase que representa la estrategia de escritura vacia. 
 * Implementa la interfaz OutStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class OutStreamVoid implements OutStream {

	//Metodos.
	/**
	 * Metodo encargado de realizar la escritura vacia.
	 * 
	 * @param entero correspondiente al caracter escrito.
	 */
	public void write(int value) {}

	/**
	 * Metodo encargado de abrir la escritura vacia.
	 */
	public void open() {}

	/**
	 * Metodo encargado de cerrar la escritura vacia.
	 */
	public void close() {}

}

package cpu.mv.in;


/**
 * Interfaz que representa una estrategia de lectura.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public interface InStream {

	//Metodos.
	/**
	 * Metodo encargado de realizar la lectura.
	 * 
	 * @return entero correspondiente al caracter leido.
	 */
	abstract public int read();

	/**
	 * Metodo encargado de abrir la lectura.
	 */
	abstract public void open();

	/**
	 * Metodo encargado de cerrar la lectura.
	 */
	abstract public void close();

}

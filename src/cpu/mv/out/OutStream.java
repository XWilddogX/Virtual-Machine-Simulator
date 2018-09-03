package cpu.mv.out;


/**
 * Interfaz que representa una estrategia de escritura.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public interface OutStream {

	//Metodos.
	/**
	 * Metodo encargado de realizar la escritura.
	 * @param entero correspondiente al caracter escrito.
	 */
	abstract public void write(int value);

	/**
	 * Metodo encargado de abrir la escritura.
	 */
	abstract public void open();

	/**
	 * Metodo encargado de cerrar la escritura.
	 */
	abstract public void close();

}

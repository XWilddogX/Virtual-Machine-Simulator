package cpu.mv.out;


/**
 * Clase que representa la estrategia de escritura estandar. 
 * Implementa la interfaz OutStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class OutStreamStd implements OutStream {

	//Metodos.
	/**
	 * Metodo encargado de realizar la escritura estandar.
	 * 
	 * @param entero correspondiente al caracter escrito.
	 */
	public void write(int value) {
		System.out.print((char)value);
	}

	/**
	 * Metodo encargado de abrir la escritura estandar.
	 */
	public void open() {}

	/**
	 * Metodo encargado de cerrar la escritura estandar.
	 */
	public void close() {}

}

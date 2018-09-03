package cpu.mv.in;

import java.io.IOException;


/**
 * Clase que representa la estrategia de lectura estandar. 
 * Implementa la interfaz InStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class InStreamStd implements InStream {

	//Metodos.
	/**
	 * Metodo encargado de realizar la lectura estandar.
	 * @return entero correspondiente al caracter leido.
	 */
	public int read() {
		try {
			return System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Metodo encargado de abrir la lectura estandar.
	 */
	public void open() {}

	/**
	 * Metodo encargado de cerrar la lectura estandar.
	 */
	public void close() {}

}

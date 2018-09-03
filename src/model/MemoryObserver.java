package model;


/**
 * Clase que representa el observador de la memoria.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public  interface MemoryObserver<T> {

	//Metodos.
	/**
	 * Metodo encargado de avisar a los oyentes del cambio de contenido
	 * de la posicion index a value.
	 * 
	 * @param index posicion.
	 * @param value valor.
	 */
	public  void onWrite(int index, T value);

	/**
	 * Metodo encargado de avisar a los oyentes del reinicio de la memoria.
	 */
	public  void onMemReset();


}

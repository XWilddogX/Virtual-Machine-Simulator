package model;


/**
 * Clase que representa el observador de la pila.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public  interface  StackObserver<T> {

	//Metodos.
	/**
	 * Metodo encargado de avisar a los oyentes del push en la pila.
	 * 
	 * @param value 
	 */
	public  void onPush(T value);

	/**
	 * Metodo encargado de avisar a los oyentes del pop en la pila.
	 */
	public  void onPop();

	/**
	 * Metodo encargado de avisar a los oyentes del reinicio de la pila.
	 */
	public  void onStackReset();


}

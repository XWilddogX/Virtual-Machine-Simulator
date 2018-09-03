package model;


/**
 * Clase que representa la interfaz observable.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public  interface Observable<T>  {

	//Metodos.
	/**
	 * Metodo encargado de añadir un observador.
	 */
	public  void addObserver(T o); 

	/**
	 * Metodo encargado de eliminar un observador.
	 */
	public  void removeObserver(T o); 


}




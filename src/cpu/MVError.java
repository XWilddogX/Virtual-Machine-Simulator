package cpu;


/**
 * Clase que representa los errores de la MV.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class MVError extends Exception {

	//Atributos.
	private static final long serialVersionUID = 1L;
	private String fail;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param s mensaje de error.
	 */
	public MVError(String s){
		this.fail=s;
	}

	/**
	 * Metodo encargado de devolver el mensaje de error.
	 * 
	 * @return mensaje de error.
	 */
	public String getMessage(){
		return fail;
	}

}

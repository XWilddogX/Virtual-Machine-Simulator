package instructions.booleans;

import instructions.Instruction;


/**
 * Clase que representa la instruccion Or.
 * Hereda de la clase AndOr.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Or extends AndOr{

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Or(){
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la comparacion.
	 * 
	 * @param top cima de la pila.
	 * @param subtop subcima de la pila.
	 * @return TRUE si es distinto de cero, FALSE en caso contrario. 
	 */
	protected boolean compare(int top, int subtop) {
		return top+subtop!=0;
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("OR")) 
			return new Or();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "OR";
	}

}

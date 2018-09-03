package instructions.numericCond;

import instructions.Instruction;


/**
 * Clase que representa la instruccion Eq.
 * Hereda de la clase NumericCond.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Eq extends NumericCond {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Eq() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la comparacion.
	 * 
	 * @param top cima de la pila.
	 * @param subtop subcima de la pila.
	 * @return TRUE si son iguales, FALSE no lo son. 
	 */
	protected boolean compare(int top, int subtop) {
		return top==subtop;
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("EQ")) 
			return new Eq();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "EQ";
	}

}
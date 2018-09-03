package instructions.arithmetics;

import instructions.Instruction;


/**
 * Clase que representa la instruccion Div.
 * Hereda de la clase Arithmetics.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Div extends Arithmetics {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Div() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion.
	 * 
	 * @param n1 primer valor.
	 * @param n2 segundo valor.
	 */
	protected void execute(int n1, int n2) throws ArithmeticException {
		this.result = n1 / n2;
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("DIV")) 
			return new Div();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "DIV";
	}

}



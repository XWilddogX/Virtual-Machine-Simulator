package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Dup.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Dup extends RestSeq {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Dup() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 */
	protected void executeAux(Cpu cpu)throws Exception {
		int a;
		a=cpu.pop();
		cpu.push(a);
		cpu.push(a);
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("DUP")) 
			return new Dup();
		else return null;

	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "DUP";
	}

}

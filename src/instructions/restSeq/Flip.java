package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Flip.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Flip extends RestSeq{

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Flip() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 */
	protected void executeAux(Cpu cpu) throws Exception{
		int a,b;
		a=cpu.pop();
		b=cpu.pop();
		cpu.push(a);
		cpu.push(b);
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("FLIP")) 
			return new Flip();
		return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "FLIP";
	}

}

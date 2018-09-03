package instructions.restSeq;

import cpu.Cpu;
import cpu.MVError;
import instructions.Instruction;


/**
 * Clase que representa la instruccion In.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class In extends RestSeq{

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public In(){
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu 
	 * @throws MVError 
	 */
	protected void executeAux(Cpu cpu) throws MVError {
		cpu.push(cpu.getInStream().read());

	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("IN")) 
			return new In();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "IN";
	}

}

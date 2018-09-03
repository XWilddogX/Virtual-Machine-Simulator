package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion LoadInd.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class LoadInd extends RestSeq {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public LoadInd() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 * @throws Exception 
	 */
	protected void executeAux(Cpu cpu) throws Exception {
		Integer value=cpu.Load(cpu.pop());
		if(value!=null)
			cpu.push(value);
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("LOADIND")) 
			return new LoadInd();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "LOADIND";
	}

}

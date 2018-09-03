package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion StoreInd.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class StoreInd extends RestSeq {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public StoreInd() {	
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 */
	protected void executeAux(Cpu cpu)throws Exception {
		int value=cpu.pop();
		int dir=cpu.pop();
		cpu.store(dir,value);	
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("STOREIND")) 
			return new StoreInd();
		else return null;
	}


	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "STOREIND";
	}

}

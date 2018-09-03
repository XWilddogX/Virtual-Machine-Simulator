package instructions.booleans;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Not.
 * Hereda de la clase Instruction.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Not implements Instruction {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Not() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la instruccion.
	 * 
	 * @param cpu
	 */
	public void execute(Cpu cpu) throws Exception {
		int value=cpu.pop();
		if (value==0) 
			cpu.push(1);
		else cpu.push(0);
		cpu.increaseProgramCounter();
	}	

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("NOT")) 
			return new Not();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "NOT";
	}

}


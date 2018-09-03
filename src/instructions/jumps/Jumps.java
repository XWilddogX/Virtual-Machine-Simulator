package instructions.jumps;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase abstracta que representa la instruccion Jumps.
 * Hereda de la clase Instruction.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
abstract public class Jumps implements Instruction{

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Jumps() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el salto.
	 * 
	 * @param cpu
	 * @return TRUE si se ha ejecutado correctamente, FALSE en caso contrario. 
	 * @throws Exception 
	 */	
	abstract protected boolean branch(Cpu cpu) throws Exception;

	/**
	 * Metodo encargado de ejecutar la instruccion.
	 * 
	 * @param cpu .
	 */
	public void execute(Cpu cpu)throws Exception {
		if(!this.branch(cpu))
			cpu.increaseProgramCounter();
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	abstract public Instruction parse(String[] s);

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	abstract public String toString();

}

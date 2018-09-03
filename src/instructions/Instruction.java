package instructions;

import cpu.Cpu;



/**
 * Interfaz que representa una instruccion.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
abstract public interface Instruction {

	//Metodos.
	/**
	 * Metodo encargado de ejecutar la instruccion.
	 * 
	 * @param cpu
	 * @throws Exception 
	 */
	abstract public void execute(Cpu cpu) throws Exception;

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

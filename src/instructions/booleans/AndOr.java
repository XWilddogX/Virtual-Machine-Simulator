package instructions.booleans;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase abstracta que representa las instrucciones de tipo AndOr.
 * Implementa la clase Instruction.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
abstract public class AndOr implements Instruction {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public AndOr() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la comparacion.
	 * 
	 * @param top cima de la pila.
	 * @param subtop subcima de la pila.
	 * @return TRUE si se ha ejecutado correctamente, FALSE en caso contrario. 
	 */
	abstract protected boolean compare(int top, int subtop);

	/**
	 * Metodo encargado de ejecutar la instruccion.
	 * 
	 * @param cpu
	 */
	public void execute(Cpu cpu) throws Exception {
		int top,subtop;
		top = cpu.pop();
		subtop = cpu.pop();
		if (compare(top,subtop)) cpu.push(1);
		else cpu.push(0);
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
	 * @return Cadena con el nombre de la instruccion.
	 */
	abstract public String toString();
}

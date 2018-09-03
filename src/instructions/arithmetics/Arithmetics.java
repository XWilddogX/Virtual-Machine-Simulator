package instructions.arithmetics;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase abstracta que representa las instrucciones de tipo Arithmetics.
 * Implementa la clase Instruction.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
abstract public class Arithmetics implements Instruction {

	//Atributos.
	protected int result;


	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Arithmetics() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion.
	 * 
	 * @param n1 primer valor.
	 * @param n2 segundo valor.
	 */
	abstract protected void execute(int n1, int n2);

	/**
	 * Metodo encargado de ejecutar la instruccion.
	 * 
	 * @param cpu
	 */
	public void execute(Cpu cpu) throws Exception  {
		int n2 = cpu.pop();
		int n1 = cpu.pop();
		this.execute(n1, n2);
		cpu.push(this.result);
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

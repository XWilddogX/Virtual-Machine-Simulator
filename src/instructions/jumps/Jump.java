package instructions.jumps;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Jump.
 * Hereda de la clase Instruction.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Jump implements Instruction {

	//Atributos.
	private int value;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public Jump() {
		super();
	}

	/**
	 * Constructora con parametros.
	 * @param i valor de salto.
	 */
	public Jump(int i) {
		super();
		this.value = i;
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la instruccion.
	 * 
	 * @param cpu .
	 */
	public void execute(Cpu cpu)throws Exception {
		cpu.branch(this.value);
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("JUMP")) 
			return new Jump(Integer.parseInt(s[1]));
		else return null;

	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "JUMP "+this.value;
	}

}

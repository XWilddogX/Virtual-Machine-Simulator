package instructions.jumps;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion JumpInd.
 * Hereda de la clase Jumps.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class JumpInd extends Jumps{

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public JumpInd() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el salto.
	 * 
	 * @param cpu
	 * @return TRUE. 
	 */
	protected boolean branch(Cpu cpu) throws Exception{
		cpu.branch(cpu.pop());
		return true;
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("JUMPIND")) 
			return new JumpInd();
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "JUMPIND";
	}

}

package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Push.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Push extends RestSeq {

	//Atributos.
	private int value;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public Push() {
		super();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param i valor a insertar.
	 */
	public Push(int i){
		super();
		this.value=i;	
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 */
	protected void executeAux(Cpu cpu)throws Exception {
		cpu.push(value);
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s)  {
		if (s.length==2 && s[0].equalsIgnoreCase("PUSH"))
			return new Push(Integer.parseInt(s[1]));
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "PUSH "+this.value;
	}

}

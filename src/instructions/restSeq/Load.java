package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Load.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Load extends RestSeq {

	//Atributos.
	private int dir;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public Load() {
		super();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param i direccion a cargar.
	 */
	public Load(int i) {
		super();
		this.dir=i;	
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 */
	protected void executeAux(Cpu cpu) throws Exception {
		Integer value = cpu.Load(this.dir);
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
		if (s.length==2 && s[0].equalsIgnoreCase("LOAD")) 
			return new Load(Integer.parseInt(s[1]));
		else return null;

	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "LOAD "+this.dir;
	}

}

package instructions.restSeq;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion Store.
 * Hereda de la clase RestSeq.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class Store extends RestSeq {

	//Atributos.
	private int dir;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public Store() {	
		super();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param i direccion donde almacenar.
	 */
	public Store(int i) {
		super();
		this.dir=i;
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar la operacion auxiliar.
	 * 
	 * @param cpu
	 */
	protected void executeAux(Cpu cpu)throws Exception {
		int value=cpu.pop();
		cpu.store(this.dir,value);	
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("STORE")) 
			try{return new Store(Integer.parseInt(s[1]));}
		catch(NumberFormatException e){
			return null;
		}
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "STORE "+this.dir;
	}

}

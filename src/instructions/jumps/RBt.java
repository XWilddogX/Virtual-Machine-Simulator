package instructions.jumps;

import cpu.Cpu;
import instructions.Instruction;


/**
 * Clase que representa la instruccion RBt.
 * Hereda de la clase Jumps.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class RBt extends Jumps {

	//Atributos.
	private int value;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public RBt(){
		super();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param i valor de salto.
	 */
	public RBt(int i){
		super();
		this.value=i;
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el salto.
	 * 
	 * @param cpu
	 * @return TRUE si se ha ejecutado correctamente, FALSE en caso contrario. 
	 */
	protected boolean branch(Cpu cpu)throws Exception {
		int i=cpu.getPc();
		if(cpu.pop()!=0) {
			i+=value;
			cpu.branch(i);
			return true;
		}
		return false;
	}

	/**
	 * Metodo encargado de parsear la instruccion.
	 * 
	 * @param s cadena a parsear.
	 * @return Instruccion parseada.
	 */
	public Instruction parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("RBt")) 
			return new RBt(Integer.parseInt(s[1]));
		else return null;
	}

	/**
	 * Metodo encargado de devolver una cadena con el nombre de la instruccion.
	 * 
	 * @return Cadena con el nombre de la instruccion.
	 */
	public String toString() {
		return "RBt "+this.value;
	}

}

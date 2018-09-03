package view;

import instructions.Instruction;
import cpu.Memory;
import cpu.OperandStack;
import model.CpuObserver;
import model.Observable;


/**
 * Clase que representa la vista Interactive.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class InteractiveView implements CpuObserver{

	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param cpu
	 */
	public InteractiveView(Observable<CpuObserver> cpu) {
		cpu.addObserver(this);
	}


	//Metodos.
	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución de una instruccion.
	 */
	public void onStartInstrExecution(Instruction instr ) {
		System.out.println("comienza la ejecucion de: "+instr.toString());	
	}
	
	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución de una instruccion.
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> mem,OperandStack<Integer> stack) {
		System.out.println("El estado tras ejecutar la instruccion es:"); 
		System.out.println("memoria: "+mem.toString());
		System.out.println("pila de operandos: " +stack.toString());
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onStartRun() {}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onEndRun() {}

	/**
	 * Metodo encargado de avisar de que ha ocurrido
	 * algun error durante la ejecucion de cualquier
	 * metodo de la cpu.
	 */
	public void onError(String msg) {
		System.err.println("Error: "+msg);
	}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt() {}


}

package view;

import controller.Controller;
import instructions.Instruction;
import cpu.Memory;
import cpu.OperandStack;
import model.CpuObserver;
import model.Observable;


/**
 * Clase que representa la vista Batch.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class BatchView implements CpuObserver {

	//Atributos.
	private Controller cntrl;
	
	
	//Constructora.
	/**
	 * Constructora por defecto.
	 * 
	 * @param cpu
	 */
	public BatchView(Observable<CpuObserver> cpu, Controller cntrl) {
		cpu.addObserver(this);
		this.cntrl=cntrl;
	}


	//Metodos.
	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución de una instruccion.
	 */
	public void onStartInstrExecution(Instruction instr) {}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución de una instruccion.
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> mem,
			OperandStack<Integer> stack) {}

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
		this.cntrl.quit();
	}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt() {this.cntrl.quit();}


}

package controller;

import cpu.Cpu;


/**
 * Clase que representa el controlador del modo Batch.
 * Hereda de la clase Controller.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class BatchController extends Controller {

	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param cpu.
	 */
	public BatchController(Cpu cpu) {
		super(cpu);
	}


	//Metodos.
	/**
	 * Metodo que comienza la ejecucion.
	 */
	public void start() {
		run();	
	}
	

}

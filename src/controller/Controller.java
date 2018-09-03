package controller;

import cpu.Cpu;
import cpu.MVError;
import cpu.mv.ProgramMV;
import cpu.mv.in.InStream;
import cpu.mv.out.OutStream;


/**
 * Clase que representa el controlador.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public abstract  class Controller {

	//Atributos.
	protected Cpu cpu;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param cpu del programa.
	 */
	public Controller(Cpu cpu) {
		this.cpu = cpu;
	}


	//Metodos.
	/**
	 * Metodo que ejecuta el comando step de la cpu.
	 */
	public void step() {
		try {
			cpu.step();
		} catch (Exception e) {
		}
	}

	/**
	 * Metodo que ejecuta el comando run de la cpu.
	 */
	public void run() {
		try {
			cpu.run();
		} catch (Exception e) {
		}
	}

	/**
	 * Metodo que ejecuta el comando pop de la cpu.
	 */
	public void pop() {
		try {
			cpu.pop();
		} catch (MVError e) {
		}
	}

	/**
	 * Metodo que ejecuta el comando push de la cpu.
	 */
	public void push(int v) {cpu.getStack().stackPush(v);} // ejecuta el push del cpu

	/**
	 * Metodo que ejecuta el comando setMem de la cpu.
	 */
	public void memorySet(int i, int v) {cpu.getMemory().memStore(i, v);}

	/**
	 * Metodo que devuelve el programa actual.
	 */
	public ProgramMV getProgram() {return this.cpu.getProgram();}

	/**
	 * Metodo que modifica el inStream.
	 */
	public void setInStream(InStream in) throws MVError{this.cpu.setInStream(in);}

	/**
	 * Metodo que modifica el outStream.
	 */
	public void setOutStream(OutStream out) throws MVError {this.cpu.setOutStream(out);}

	/**
	 * Metodo que devuelve el inStream actual.
	 */
	public InStream getInStream() {return this.cpu.getInStream();}

	/**
	 * Metodo que devuelve el outStream actual.
	 */
	public OutStream getOutStream(){return this.cpu.getOutStream();}

	/**
	 * Metodo abstracto que comienza la ejecución.
	 * Depende del modo (Batch, Interactive, o GUI).
	 */
	public abstract void start();

	/**
	 * Metodo que termina la ejecución.
	 */
	public void quit() { 
		getOutStream().close();
		getInStream().close();
		System.exit(0);
	}

}


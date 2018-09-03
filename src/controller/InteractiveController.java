package controller;

import java.util.Scanner;

import commands.CommandInterpreter;
import parsers.CommandParser;
import cpu.Cpu;


/**
 * Clase que representa el controlador del modo Interactive.
 * Hereda de la clase Controller.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class InteractiveController extends Controller{

	//Constructora.
	/**
	 * Constructora con parametro.
	 * 
	 * @param cpu.
	 */
	public InteractiveController(Cpu cpu) {
		super(cpu);
	}


	//Metodos.
	/**
	 * Metodo que comienza la ejecucion.
	 */
	public void start(){
		Scanner sc=new Scanner(System.in);
		String commandInst = null;
		CommandInterpreter instrCommand = null;
		while(!cpu.abortComputation()){
			System.out.print(">");
			commandInst = sc.nextLine();
			instrCommand = CommandParser.commandParser(commandInst);
			if(instrCommand!=null){
				try{	
					//Ejecuta la instruccion.
					instrCommand.executeCommand();
				}
				catch(Exception e)
				{
				}
			}
			else 
				System.out.println("Comando Erroneo");
		}
		sc.close();	
		this.quit();
	}

}

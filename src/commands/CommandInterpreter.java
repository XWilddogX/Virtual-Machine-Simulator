package commands;

import cpu.Cpu;


/**
 * Clase que representa un interprete de comandos.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
abstract public class CommandInterpreter {

	// Atributos.	
	protected static Cpu cpu;
	//Comprueba si la instruccion QUIT se ha ejecutado.
	protected boolean instructionError;


	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public CommandInterpreter() {
		this.instructionError = false;
	}


	//Metodos.
	/**
	 * Metodo abstracto encargado de ejecutar el comando.
	 */
	abstract public void executeCommand() throws Exception;

	/**
	 * Metodo abstracto encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	abstract public CommandInterpreter parse(String[] s);

	/**
	 * Metodo encargado de configurar un CommandInterpreter.
	 * 
	 * @param cpu Cpu para inicializar el atributo del mismo nombre.
	 */
	public static void configureCommandInterpreter(Cpu cpu) {
		CommandInterpreter.cpu=cpu;
	}

	/**
	 * Metodo encargado de modificar el atributo instructionError del comando.
	 * 
	 * @param status estado del atributo instructionError a modificar.
	 */
	public void setInstructionError(boolean status){
		this.instructionError=status;
	}


}

package commands;


/**
 * Clase que representa el comando Quit. 
 * Hereda de la clase CommandInterpreter.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandQuit extends CommandInterpreter {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public CommandQuit() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el comando.
	 */
	public void executeCommand() {
		CommandInterpreter.cpu.exit();
	}

	/**
	 * Metodo encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	public CommandInterpreter parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("QUIT")) 
			return new CommandQuit();
		else return null;
	}

}

package commands;


/**
 * Clase que representa el comando Pop. 
 * Hereda de la clase CommandInterpreter.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandPop extends CommandInterpreter{

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public CommandPop(){
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el comando.
	 */
	public void executeCommand()throws Exception {
		cpu.pop();
	}

	/**
	 * Metodo encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	public CommandInterpreter parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("POP")) 
			return new CommandPop();
		else return null;
	}

	
}

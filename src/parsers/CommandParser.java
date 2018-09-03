package parsers;

import commands.CommandInterpreter;
import commands.CommandPop;
import commands.CommandPush;
import commands.CommandQuit;
import commands.CommandRun;
import commands.CommandStep;
import commands.CommandSteps;
import commands.CommandWrite;


/**
 * Clase encargada de analizar la entrada de texto del usuario
 * y de crear el comando correspondiente.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandParser {

	private static CommandInterpreter commandSet[] = 
		{new CommandStep(), new CommandSteps(), new CommandRun(), new CommandQuit(),new CommandPush(),new CommandPop(),new CommandWrite()};


	// Metodos.
	/**
	 * Metodo que analiza el texto pasado por parametro y devuelve
	 * el comando correspondiente. En caso de error devuelve null.
	 * 
	 * @param s cadena a convertir en comando.
	 * @return comando correspondiente.
	 */
	public static CommandInterpreter commandParser(String s){
		String[] split = s.split(" ");

		CommandInterpreter command=null;

		// Bucle para hacer el proceso de parseo de commandos
		int i=0;
		boolean stop=false;
		while (i<CommandParser.commandSet.length && !stop){
			command =CommandParser.commandSet[i].parse(split);
			if (command!=null) stop = true;
			else i++;
		}
		return command;
	}

}

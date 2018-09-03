package commands;


/**
 * Clase que representa el comando Run. 
 * Hereda de la clase Step.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandRun extends CommandStep {

	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public CommandRun() {
		super();
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el comando.
	 * 
	 * @throws Exception 
	 */
	public void executeCommand() throws Exception {
		cpu.resetCPU();
		while (!cpu.abortComputation()){
			super.executeCommand();	
		}
	}

	/**
	 * Metodo encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	public CommandInterpreter parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("RUN")) 
			return new CommandRun();
		else return null;
	}

}

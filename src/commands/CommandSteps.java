package commands;


/**
 * Clase que representa el comando Steps. 
 * Hereda de la clase Step.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandSteps extends CommandStep {

	//Atributos.
	private int value;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public CommandSteps() {
		super();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param value numero de pasos.
	 */
	public CommandSteps(int value) {
		super();
		this.value = value;
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el comando.
	 * 
	 * @throws Exception 
	 */
	public void executeCommand() throws Exception {
		int i = 0;
		while (i < this.value && !cpu.abortComputation()) {
			super.executeCommand();
			i++;
		}
	}

	/**
	 * Metodo encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	public CommandInterpreter parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("STEPS")) {
			int value = Integer.parseInt(s[1]);
			return new CommandSteps(value);
		}
		else return null;
	}

}

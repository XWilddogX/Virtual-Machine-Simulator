package commands;


/**
 * Clase que representa el comando Push. 
 * Hereda de la clase CommandInterpreter.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandPush extends CommandInterpreter {

	// Atributos.
	int value;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public CommandPush(){
		super();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param i valor a añadir a la cima de la pila.
	 */	
	public CommandPush(int i){
		super();
		this.value=i;
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el comando.
	 */
	public void executeCommand(){
		cpu.getStack().getStack().push(value);

	}

	/**
	 * Metodo encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	public CommandInterpreter parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("PUSH"))
			try{
				return new CommandPush(Integer.parseInt(s[1]));}
		catch(Exception e){
			System.err.println("entero no introducido");
		}
		return null;
	}

}

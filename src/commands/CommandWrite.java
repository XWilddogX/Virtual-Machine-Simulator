package commands;


/**
 * Clase que representa el comando Write. 
 * Hereda de la clase CommandInterpreter.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class CommandWrite extends CommandInterpreter {

	//Atributos.
	private int pos;


	//Constructoras.
	/**
	 * Constructora sin parametros.
	 */
	public CommandWrite(){
		super();
	}

	/**
	 * Constructora con parametros.
	 * @param i posición donde escribir.
	 */
	public CommandWrite(int i){
		super();
		this.pos=i;
	}


	//Metodos.
	/**
	 * Metodo encargado de ejecutar el comando.
	 * 
	 * @throws Exception 
	 */
	public void executeCommand() throws Exception {
		int value=cpu.pop();
		cpu.getMemory().memStore(this.pos,value);
	}

	/**
	 * Metodo encargado de parsear el comando.
	 * 
	 * @param s cadena a parsear.
	 * @return Comando parseado.
	 */
	public CommandInterpreter parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("WRITE")) 
			try{return new CommandWrite(Integer.parseInt(s[1]));}
		catch(NumberFormatException e){
			System.err.println("Direccion de memoria no insertada");
			return null;
		}
		else return null;
	}

}

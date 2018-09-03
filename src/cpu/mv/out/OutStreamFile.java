package cpu.mv.out;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.Main;


/**
 * Clase que representa la estrategia de escritura de fichero. 
 * Implementa la interfaz OutStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class OutStreamFile implements OutStream{

	//Atributos.
	private String fileName; 
	private PrintWriter pw ;
	private FileWriter fileWrite;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param fileName cadena con el nombre del fichero.
	 */	
	public OutStreamFile(String fileName){
		this.fileName=fileName;
	}


	//Metodos.
	/**
	 * Metodo encargado de realizar la escritura del fichero.
	 * 
	 * @param entero correspondiente al caracter escrito.
	 */
	public void write(int value){
		if(value ==10)
			pw.write(Main.NEWLINE);	
		else
			pw.write((char)value);
	}

	/**
	 * Metodo encargado de abrir el fichero de escritura.
	 */
	public void open() {
		try{
			this.fileWrite = new FileWriter(this.fileName);
			this.pw =new PrintWriter(this.fileWrite);
		}
		catch(FileNotFoundException e){
			System.err.println("Error: "+this.fileName+"not found");
			System.exit(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de cerrar el fichero de lectura.
	 */
	public void close(){
		try{
			this.pw.close();
			this.fileWrite.close();
		}
		catch(IOException e)
		{System.err.println("Error: "+this.fileName+"not open");}
	}

}

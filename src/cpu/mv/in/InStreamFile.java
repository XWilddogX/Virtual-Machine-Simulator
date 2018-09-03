package cpu.mv.in;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Clase que representa la estrategia de lectura de fichero. 
 * Implementa la interfaz InStream.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class InStreamFile implements InStream {

	//Atributos.
	String fname;
	FileReader f;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param file cadena con el nombre del fichero.
	 */	
	public InStreamFile(String file){
		this.fname=file;
	}


	//Metodos.
	/**
	 * Metodo encargado de realizar la lectura del fichero.
	 * 
	 * @return entero correspondiente al caracter leido.
	 */
	public int read(){
		try {
			if(f.ready()){
				return f.read();
			}
		} catch (IOException e) {
			System.err.println("Error: File " +this.fname+" not open");
		}
		return -1;
	}

	/**
	 * Metodo encargado de abrir el fichero de lectura.
	 */
	public void open(){
		try {
			f= new FileReader(this.fname);
		} catch (FileNotFoundException e) {
			System.err.println("Error: File "+this.fname+" not found");
			System.exit(2);
		}
	}

	/**
	 * Metodo encargado de cerrar el fichero de lectura.
	 */
	public void close(){
		try {
			f.close();
		} catch (IOException e) {
			System.err.println("Error: File " +this.fname+" not open");
		}
	}

}

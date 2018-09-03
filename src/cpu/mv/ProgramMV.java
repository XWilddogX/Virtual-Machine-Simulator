package cpu.mv;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import cpu.MVError;
import parsers.InstructionParser;
import main.Main;
import instructions.Instruction;


/**
 * Clase que representa un programa de la maquina virtual.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 3.0
 */
public class ProgramMV {

	//Atributos.
	private ArrayList<Instruction> program;
	private int sizeProgram;


	//Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public ProgramMV() {
		this.sizeProgram=0;
		this.program=new ArrayList<Instruction>();
	}


	//Metodos.
	/**
	 * Metodo encargado de añadir una instruccion al programa.
	 * 
	 * @param ins instruccion a añadir al programa.
	 */
	public  void push(Instruction ins){
		this.program.add(this.sizeProgram, ins);
		this.sizeProgram++;
	}

	/**
	 * Metodo encargado de recoger la instruccion solicitada de la MV.
	 * 
	 * @return Instruction instruccion a realizar, en caso de que no haya devuelve null.
	 */
	public Instruction get(int value){
		if(value<this.sizeProgram)
			return this.program.get(value);
		else return null;
	}

	/**
	 * Metodo encargado de recoger el tamaño del programa de la MV.
	 * 
	 * @return int tamaño del programa.
	 */
	public int getSizeProgram() {
		return this.sizeProgram;
	}

	/**
	 * Metodo encargado de leer el programa introducido por el usuario
	 * que posteriormente será ejecutado por la CPU.
	 * 
	 * @param sc
	 * @return ProgramMV programa leido.
	 */
	public static  ProgramMV readProgram(){
		ProgramMV readProgram =new ProgramMV();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String instructionIn;
		boolean end=false;
		Instruction ins=null;
		System.out.println("Introduce el programa fuente");
		while(!end) {
			System.out.print(">");
			instructionIn = sc.nextLine();
			try {
				ins = InstructionParser.instructionParser(instructionIn);}
			catch (NumberFormatException e){
				ins=null;
			}
			if(ins!=null)
				readProgram.push(ins);
			else if (ins==null && instructionIn.equalsIgnoreCase("END"))
				end=true;
			else
				System.out.println("Error al introducir la instruccion");
		}
		return readProgram;
	}

	/**
	 * Metodo encargado de leer el fichero del programa
	 * que posteriormente será ejecutado por la CPU.
	 * 
	 * @param sc
	 * @return ProgramMV programa leido.
	 */
	public static ProgramMV readFileASM(String fileName) throws Exception{
		ProgramMV readFile =new ProgramMV();
		Instruction ins;
		boolean found=false;
		int i=0;
		String instruction,sp[];
		Scanner scf = new Scanner(new FileReader(fileName));
		while(scf.hasNextLine()){
			instruction=scf.nextLine();
			sp=instruction.split(" ");
			while(i<sp.length && !found){
				if(!sp[i].equals(";") && !sp[i].equals(""))
					i++;
				else found=true;}
			instruction="";
			for(int j=0;j<i;j++)
				instruction+=sp[j]+" ";
			if(!instruction.equals("")){
				try {
					ins = InstructionParser.instructionParser(instruction);}
				catch (NumberFormatException e){
					ins=null;
				}
				if(ins!=null)
					readFile.push(ins);
				else{
					scf.close();
					throw new MVError("Error en el programa. Linea: "+instruction);
				}
			}
			i=0;
			found=false;
		}
		scf.close();
		return readFile;
	}

	/**
	 * Metodo encargado de devolver una representacion
	 * textual de la MV.
	 * 
	 * @return String string con el programa de la MV.
	 */
	public String toString(){
		String ProgramString="";
		for(int i=0; i<this.sizeProgram; i++)
			ProgramString +=i+":"+this.program.get(i).toString()+Main.NEWLINE;
		if(this.sizeProgram==0)
			ProgramString +="<Niguna Instruccion>";
		return ProgramString;
	}


}
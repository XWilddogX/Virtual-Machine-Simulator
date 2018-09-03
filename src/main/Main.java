package main;

import java.io.FileNotFoundException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import view.BatchView;
import view.InteractiveView;
import view.MainWindow;
import commands.CommandInterpreter;
import controller.BatchController;
import controller.GUIController;
import controller.InteractiveController;
import cpu.Cpu;
import cpu.MVError;
import cpu.mv.ProgramMV;
import cpu.mv.in.InStream;
import cpu.mv.in.InStreamFile;
import cpu.mv.in.InStreamStd;
import cpu.mv.in.InStreamVoid;
import cpu.mv.out.OutStream;
import cpu.mv.out.OutStreamFile;
import cpu.mv.out.OutStreamStd;
import cpu.mv.out.OutStreamVoid;


/**
 * Clase principal que contiene el metodo Main de la practica.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 3.0
 */
public class Main {

	// Utilizado para que el salto de linea funcione independientemente del SO.
	public static String NEWLINE = System.getProperty("line.separator");


	// Atributos.
	private static final int _BATCH_MODE = 0; 
	private static final int _INTER_MODE = 1; 
	private static final int _WINDOW_MODE = 2; 
	private static final int _NULL_STREAM = 3; 
	private static final int _STD_STREAM = 4; 
	private static final int _FILE_STREAM = 5;

	private static Cpu cpu; 
	private static int executionMode = _BATCH_MODE; 
	private static int inStreamMode = _NULL_STREAM; 
	private static int outStreamMode = _NULL_STREAM; 
	private static String inStreamFileName = null; 
	private static String outStreamFileName = null; 
	private static String programFileName = null;


	// Metodos.
	/**
	 * Metodo auxiliar que parsea las opciones del programa.
	 * 
	 * @param args argumentos.
	 */
	@SuppressWarnings("static-access")
	private static void parseOptions(String [] args){

		//Atributos.
		CommandLine commands = null;
		Options options = new Options();
		CommandLineParser parser = new BasicParser();
		String mode = "interactive";

		options.addOption("h", "help", false, "Muestra este texto de ayuda");
		options.addOption(OptionBuilder.withArgName("infile").withLongOpt("in").hasArg().withDescription("Entrada del programa de la maquina-p").create("i"));
		options.addOption(OptionBuilder.withArgName("mode").withLongOpt("mode").hasArg().withDescription("Modo de funcionamiento (batch | interactive | window). Por defecto, batch").create("m"));
		options.addOption(OptionBuilder.withArgName("outfile").withLongOpt("out").hasArg().withDescription("Fichero donde se guarda la salida del programa de la maquina-p.").create("o"));
		options.addOption(OptionBuilder.withArgName("asmfile").withLongOpt("asm").hasArg().withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.").create("a"));

		try {
			commands = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println("Uso incorrecto: "+e.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			System.exit(2);
		}

		try{
			//Si solicitamos ayuda, la muestra y sale del programa.
			if(commands.hasOption("-h")){
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]", options);
				System.exit(1);
			}

			//Si hay parametro de modo de ejecucion, se guarda.
			//Si no lo hay, el modo por defecto es interactive.
			if(commands.hasOption("-m"))
				mode=commands.getOptionValue("m");

			//Si el modo es interactive...
			if(mode.equalsIgnoreCase("interactive")){
				executionMode = _INTER_MODE;

				//Si tiene el parametro i, usamos la estrategia de entrada de fichero.
				//Si no, la estrategia de entrada vacía.
				if(commands.hasOption("-i")){
					inStreamMode=_FILE_STREAM;
					inStreamFileName=commands.getOptionValue("i");
				}

				//Si tiene el parametro o, usamos la estrategia de salida de fichero.
				//Si no, la estrategia de salida vacía.
				if(commands.hasOption("-o")){
					outStreamMode=_FILE_STREAM;
					outStreamFileName=commands.getOptionValue("o");
				}
			}

			//Si el modo es batch...
			else if (mode.equalsIgnoreCase("batch")) {
				executionMode = _BATCH_MODE;

				//Si tiene el parametro i, usamos la estrategia de entrada de fichero.
				//Si no, la estrategia de entrada estandar.
				if(commands.hasOption("-i")){
					inStreamMode=_FILE_STREAM;
					inStreamFileName=commands.getOptionValue("i");
				} else
					inStreamMode =_STD_STREAM;

				//Si tiene el parametro o, usamos la estrategia de salida de fichero.
				//Si no, la estrategia de salida estandar.
				if(commands.hasOption("-o")){
					outStreamMode=_FILE_STREAM;
					outStreamFileName=commands.getOptionValue("o");
				} else
					outStreamMode =_STD_STREAM;
			}

			//Si es el modo window...
			else if (mode.equalsIgnoreCase("window")){
				executionMode = _WINDOW_MODE;

				//Si tiene el parametro i, usamos la estrategia de entrada de fichero.
				if(commands.hasOption("-i")){
					inStreamMode=_FILE_STREAM;
					inStreamFileName=commands.getOptionValue("i");
				}
				//Si tiene el parametro o, usamos la estrategia de salida de fichero.
				//Si no, la estrategia de salida estandar.
				if(commands.hasOption("-o")){
					outStreamMode=_FILE_STREAM;
					outStreamFileName=commands.getOptionValue("o");
				} else
					outStreamMode =_STD_STREAM;
			}


			//Si no es interactive, batch o window, error modo incorrecto.
			else{
				System.err.println("Uso incorrecto: Modo incorrecto (parametro -m|--mode).");
				System.err.println("se -h|--help para más detalles.");
				System.exit(1);
			}

			//Si tiene el parametro a, leemos el fichero ASM.
			//Si no, actuar según el modo (ver metodos auxiliares debajo).
			if	(commands.hasOption("-a"))
				programFileName=commands.getOptionValue("a");
		}

		catch(Exception e){
			System.err.print("fail");
		}
	}

	/**
	 * Metodo auxiliar encargado de ejecutar el modo interactive.
	 * 
	 * @throws Exception
	 */	
	private static void interactiveMode() throws Exception { 

		//Atributos.
		ProgramMV programReaded = new ProgramMV();
		InStream in = null;
		OutStream out = null;


		//--Crear y abrir los inStream y outStream y pasarlos a la CPU--
		//Si tiene el parametro i, usamos la estrategia de entrada de fichero.
		//Si no, la estrategia de entrada vacía.
		if(inStreamMode==_FILE_STREAM)
			in = new InStreamFile(inStreamFileName);
		else
			in = new InStreamVoid();		
		//Si tiene el parametro o, usamos la estrategia de salida de fichero.
		//Si no, la estrategia de salida vacía.		
		if(outStreamMode==_FILE_STREAM)
			out=new OutStreamFile(outStreamFileName);
		else
			out=new OutStreamVoid();	

		cpu.setInStream(in);
		cpu.setOutStream(out);
		cpu.getInStream().open();
		cpu.getOutStream().open();


		//--Lee el programa--
		//Si el parametro a es nulo, lectura estandar.
		//Si no, lee el fichero ASM.
		if(programFileName==null)
			programReaded = ProgramMV.readProgram();
		else
			try{
				programReaded = ProgramMV.readFileASM(programFileName);
			}
		catch(FileNotFoundException e){
			System.err.println("Uso incorrecto: Fichero ASM no encontrado.");
			System.err.println("Use -h|--help para más detalles");
			System.exit(2);
		}


		//--Carga el programa en la CPU--
		cpu.setProgram(programReaded);

		//Pasa al interprete de comandos.
		CommandInterpreter.configureCommandInterpreter(cpu);
		InteractiveController ctrl = new InteractiveController(cpu);
		@SuppressWarnings("unused")
		InteractiveView view = new InteractiveView(cpu);
		ctrl.start();

	}

	/**
	 * Metodo auxiliar encargado de ejecutar el modo batch.
	 * 
	 * @throws Exception
	 */
	private static void batchMode() throws Exception {

		//Atributos.
		ProgramMV programReaded = new ProgramMV();
		InStream in = null;
		OutStream out = null;


		//--Crear y abrir los inStream y outStream y pasarlos a la CPU--
		//Si tiene el parametro i, usamos la estrategia de entrada de fichero.
		//Si no, la estrategia de entrada estandar.
		if(inStreamMode==_FILE_STREAM)
			in=new InStreamFile(inStreamFileName);
		else
			in=new InStreamStd();
		//Si tiene el parametro o, usamos la estrategia de salida de fichero.
		//Si no, la estrategia de salida estandar.
		if(outStreamMode==_FILE_STREAM)
			out=new OutStreamFile(outStreamFileName);
		else
			out=new OutStreamStd();

		cpu.setInStream(in);
		cpu.setOutStream(out);
		cpu.getInStream().open();
		cpu.getOutStream().open();


		//--Lee el programa--
		//Si el parametro a no es nulo, lee el fichero ASM.
		//Si no, error. el modo batch necesita fichero ASM.
		if	(programFileName!=null){
			try{
				programReaded = ProgramMV.readFileASM(programFileName);
			}
			catch(FileNotFoundException e){
				System.err.println("Uso incorrecto: Fichero ASM no especificado.");
				System.err.println("Use -h|--help para más detalles");
				System.exit(2);
			}
			catch(MVError e){
				System.err.println(e.getMessage());
				System.exit(2);
			}
		} else {
			System.err.println("Error:No existe fichero de codigo para modo Batch");
			System.exit(2);
		}


		//--Carga el programa en la CPU--
		cpu.setProgram(programReaded);

		//Pasa al interprete de comandos.
		CommandInterpreter.configureCommandInterpreter(cpu);
		BatchController ctrl = new BatchController(cpu);
		@SuppressWarnings("unused")
		BatchView view = new BatchView(cpu,ctrl);
		ctrl.start();

		//Ejecuta el programa.
		cpu.run();

	}

	/**
	 * Metodo auxiliar encargado de ejecutar el modo window.
	 * 
	 * @throws Exception
	 */
	private static void windowMode() throws Exception {

		//Atributos.
		ProgramMV programReaded = new ProgramMV();
		InStream in = null;
		OutStream out = null;


		//--Crear y abrir lo s inStream y outStream y pasarlos a la CPU--
		//Si tiene el parametro i, usamos la estrategia de entrada de fichero.
		//Si no, la estrategia de entrada vacía.
		if(inStreamMode==_FILE_STREAM)
			in=new InStreamFile(inStreamFileName);
		else
			in=new InStreamVoid();
		//Si tiene el parametro o, usamos la estrategia de salida de fichero.
		//Si no, la estrategia de salida vacía.
		if(outStreamMode==_FILE_STREAM)
			out=new OutStreamFile(outStreamFileName);
		else
			out=new OutStreamVoid();		

		cpu.setInStream(in);
		cpu.setOutStream(out);
		cpu.getInStream().open();
		cpu.getOutStream().open();


		//--Lee el programa--
		//Si el parametro a no es nulo, lee el fichero ASM.
		//Si no, error. el modo batch necesita fichero ASM.		
		if	(programFileName!=null){
			try{
				programReaded = ProgramMV.readFileASM(programFileName);
			}
			catch(FileNotFoundException e){
				System.err.println("Uso incorrecto: Fichero ASM no especificado.");
				System.err.println("Use -h|--help para más detalles");
				System.exit(2);
			}
			catch(MVError e){
				System.err.println(e.getMessage());
				System.exit(2);
			}
		} else {
			System.err.println("Error:No existe fichero de codigo para modo Batch");
			System.exit(2);
		}


		//--Carga el programa en la CPU--
		cpu.setProgram(programReaded);

		// Construye el objeto que corresponde a la vista
		GUIController ctrl = new GUIController(cpu);
		MainWindow view = new MainWindow(ctrl,cpu,cpu.getStack(),cpu.getMemory());
		ctrl.start();
		view.setSize(1000,800);
		view.setVisible(true);
	}	

	/**
	 * Metodo auxiliar encargado de ejecutar la Maquina Virtual
	 * 
	 * @param args
	 */
	public static void startMV(String [] args) {
		cpu = new Cpu();
		try { 
			parseOptions(args); 
			switch (executionMode) {
			case _INTER_MODE: interactiveMode(); break;
			case _BATCH_MODE: batchMode(); break;
			case _WINDOW_MODE: windowMode(); break; 
			} 
		} 
		catch (Exception e) { 
			System.err.println("Error: "+e.getMessage()); 
			cpu.getOutStream().close();
			cpu.getInStream().close();
			System.exit(1); }
	}	

	/**
	 * Método principal del programa. 
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String [] args) {startMV(args);}


}

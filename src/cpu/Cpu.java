package cpu;

import java.util.ArrayList;

import cpu.mv.ProgramMV;
import cpu.mv.in.InStream;
import cpu.mv.out.OutStream;
import model.CpuObserver;
import model.Observable;
import instructions.Instruction;


/**
 * Clase que representa la CPU de la maquina virtual. 
 * Gestiona la memoria y la pila.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 3.0
 */
public class Cpu  implements Observable<CpuObserver>{

	// Atributos.
	private ArrayList <CpuObserver> observer;
	private Memory<Integer> memory;
	private OperandStack<Integer> stack;
	private boolean exit;
	private boolean correctPC;
	private int pc;
	private ProgramMV program;
	private InStream in;
	private OutStream out;


	// Contructora, getters y setters.
	/**
	 * Constructora por defecto.
	 */
	public Cpu() {
		this.exit = false;
		this.memory = new Memory<Integer>();
		this.stack = new OperandStack<Integer>();	
		this.pc=0;
		this.correctPC=false;
		this.observer=new ArrayList<CpuObserver>();
	}

	/**
	 * Getter encargado de devolver la pila.
	 * 
	 * @return pila.
	 */
	public OperandStack<Integer> getStack() {
		return this.stack;
	}

	/**
	 * Getter encargado de devolver la memoria.
	 * 
	 * @return memoria.
	 */	
	public Memory<Integer> getMemory() { 
		return this.memory;
	}

	/**
	 * Getter encargado de devolver el contador de programa.
	 * 
	 * @return pc valor del contador de programa.
	 */	
	public int getPc(){
		return this.pc;
	}

	/**
	 * Getter que devuelve el valor del atributo program.
	 * 
	 * @return program.
	 */
	public ProgramMV getProgram() {
		return this.program;
	}

	/**
	 * Getter que devuelve el valor del atributo in.
	 * 
	 * @return in.
	 */
	public InStream getInStream() { return in; }

	/**
	 * Getter que devuelve el valor del atributo out.
	 * 
	 * @return out.
	 */
	public OutStream getOutStream() { return out; }

	/**
	 * Setter encargado de modificar el atributo program.
	 * 
	 * @param program del atributo program.
	 */
	public void setProgram(ProgramMV program){
		this.program=program;
	}

	/**
	 * Setter que modifica el valor del atributo in.
	 * 
	 * @param s valor del atributo in.
	 */
	public void setInStream(InStream s) throws MVError { 
		if (s == null) throw new MVError("Cannot set inStream to null");
		else this.in = s;
	}

	/**
	 * Setter que modifica el valor del atributo out.
	 * 
	 * @param s valor del atributo out.
	 */
	public void setOutStream(OutStream s) throws MVError {
		if (s == null) throw new MVError("Cannot set outStream to null");
		else this.out = s;
	}


	//Metodos.
	/**
	 * Metodo encargado de controlar el fin de la ejecucion mediante Halt.
	 */
	public void exit(){
		this.exit =true;
	}

	/**
	 * Metodo encargado de inicializar todos los atributos de la CPU
	 * para preparar una ejecucion con Run.
	 */
	public void resetCPU(){
		this.exit = false;
		this.memory.clearMemory();
		this.stack.clearStack();
		this.pc=0;
		this.correctPC=false;
		this.in.close();
		this.out.close();
		this.in.open();
		this.out.open();
	}

	/**
	 * Metodo encargado de devolver el tamaño del programa.
	 * 
	 * @return int tamaño del programa.
	 */
	public int SizeProgram() {
		return this.program.getSizeProgram();
	}

	/**
	 * Metodo encargado de realizar un salto en el programa, 
	 * modificando el contador de programa.
	 * 
	 * @param value nuevo valor para el contador de programa.
	 */
	public void branch(int value) {
		this.pc=value;
	}

	/**
	 * Metodo encargado de devolver la siguiente instruccion.
	 * 
	 * @return instruccion situada en el contador de programa 
	 * en caso de que este sea correcto. En caso contrario devuelve null.
	 */
	public Instruction getCurrentInstruction() {
		return this.program.get(this.pc);
	}

	/**
	 * Metodo encargado de controlar la ejecucion del programa.
	 * 
	 * @return TRUE si la ejecucion debe detenerse porque el contador de programa
	 * es incorrecto o porque se ejecutó la instrucción Halt. FALSE en caso contrario.
	 */
	public boolean abortComputation() {
		if(this.exit || this.SizeProgram()<this.pc+1)
			this.correctPC=true;
		return correctPC;
	}

	/**
	 * Metodo encargado de incrementar el contador de programa.
	 */
	public void increaseProgramCounter() {
		this.pc++;
	}

	/**
	 * Metodo encargado de extraer la cima de la pila.
	 * 
	 * @return valor de la cima de la pila.
	 * @throws MVError 
	 */
	public int pop() throws MVError { 
		return this.stack.stackPop();
	}

	/**
	 * Metodo encargado de introducir un elemento en la pila.
	 * 
	 * @param i elemento a introducir.
	 * @throws MVError
	 */
	public void push(int i) throws MVError{
		this.stack.stackPush(i);
	}

	/**
	 * Metodo encargado de cargar el valor almacenado en la direccion
	 * de memoria indicada por parametro.
	 * 
	 * @param dir direccion de memoria.
	 * @return valor cargado de memoria.
	 * @throws Exception 
	 */
	public Integer Load(int dir) throws Exception {
		return this.memory.memLoad(dir);
	}

	/**
	 * Metodo encargado de almacenar en memoria 
	 * un valor en una posicion concreta.
	 * 
	 * @param pos posicion donde almacenar.
	 * @param value valor a almacenar.
	 */
	public void store(int pos,int value) {
		this.memory.memStore(pos, value);
	}

	/**
	 * Metodo encargado de ejecutar la siguiente instruccion situada
	 * en el contador de programa.
	 * 
	 * @throws Exception 
	 */
	public void step() throws Exception {
		onStartInstrExecution();
		try{
			Instruction ins=getCurrentInstruction();
			if(ins!=null) {
				ins.execute(this);	
			}
		}catch(Exception e){
			this.onError(e.getMessage());
			throw e;
		}
		onEndInstrExecution(pc, memory, stack);
		if(this.abortComputation()) {
			onHalt();
			this.in.close();
			this.out.close();
		}
	}
	
	/**
	 * Metodo encargado de ejecutar n instrucciones.
	 * 
	 * @throws Exception 
	 */
	public void stepn(int n) throws Exception { 
		for(int i=0;i<n;i++)
			step();
	}
	
	/**
	 * Metodo encargado de ejecutar el programa.
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		onStartRun();
		while(!abortComputation()){
			step();
		}	
		onEndRun();
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución de una instruccion.
	 */
	public void onStartInstrExecution(){
		for(CpuObserver o : observer) {
			o.onStartInstrExecution(this.program.get(pc));
		}
	}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución de una instruccion.
	 */
	public void onEndInstrExecution(int pc, Memory <Integer> memory, OperandStack <Integer> stack){
		for(CpuObserver o : observer) {
			o.onEndInstrExecution(pc, memory, stack);
		}
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onStartRun(){
		for(CpuObserver o : observer) {
			o.onStartRun();
		}
	}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onEndRun(){
		for(CpuObserver o : observer) {
			o.onEndRun();
		}
	}

	/**
	 * Metodo encargado de avisar de que ha ocurrido
	 * algun error durante la ejecucion de cualquier
	 * metodo de la cpu.
	 */
	public void onError(String msg){
		for(CpuObserver o : observer) {
			o.onError(msg);
		}
	}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt(){
		for(CpuObserver o : observer) {
			o.onHalt();
		}
	}

	/**
	 * Metodo encargado de añadir un observador
	 * de la cpu.
	 */
	public void addObserver(CpuObserver o) {
		observer.add(o);		
	}

	/**
	 * Metodo encargado de eliminar un observador
	 * de la cpu.
	 */
	public void removeObserver(CpuObserver o) {
		observer.remove(o);	
	}


}


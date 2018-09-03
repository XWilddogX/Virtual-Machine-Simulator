package gui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import instructions.Instruction;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import cpu.Memory;
import cpu.OperandStack;
import model.CpuObserver;
import model.MemoryObserver;
import model.Observable;
import model.StackObserver;


/**
 * Clase que representa el panel de status.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class StatusBarPanel extends JPanel implements  StackObserver<Integer>, MemoryObserver<Integer>, CpuObserver { 

	//Atributos.
	private JCheckBox memoryCheck, stackCheck;
	private int count;
	private JLabel counter;
	private JLabel halt;
	private static final long serialVersionUID = 1L;


	//Constructora
	/**
	 * Constructora con parametros.
	 * 
	 * @param stack
	 * @param memory
	 * @param cpu
	 */
	public StatusBarPanel(Observable<StackObserver<Integer>> stack,
			Observable<MemoryObserver<Integer>> memory,
			Observable<CpuObserver> cpu) {
		this.count=0;
		cpu.addObserver(this);
		memory.addObserver(this);
		stack.addObserver(this);
		initGUI();
	}


	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI.
	 */
	private void initGUI(){
		this.halt = new JLabel();
		this.counter=new JLabel("Numero de Instrucciones Ejecutadas: "+this.count);
		this.halt.setFont(new Font("Courier", Font.PLAIN, 16));
		this.halt.setForeground(Color.RED);
		this.memoryCheck=new JCheckBox("Memoria Modificada");
		this.stackCheck=new JCheckBox("Pila Modificada");
		this.setLayout(new GridBagLayout());
		JPanel MainPanel= new JPanel(new GridBagLayout());
		GridBagConstraints panel=new GridBagConstraints();
		panel.gridx=0;
		panel.gridy=0;
		panel.weightx=0.25;
		panel.weighty=0.50;
		MainPanel.add(this.halt,panel);
		panel.gridx=1;
		MainPanel.add(counter,panel);
		panel.gridx=2;
		this.memoryCheck.setEnabled(false);
		this.memoryCheck.setSelected(false);
		MainPanel.add(this.memoryCheck,panel);
		panel.gridx=3;
		this.stackCheck.setEnabled(false);
		this.stackCheck.setSelected(false);
		MainPanel.add(this.stackCheck,panel);
		this.add(MainPanel);
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución de una instruccion.
	 */
	public void onStartInstrExecution(Instruction instr) {
		this.memoryCheck.setSelected(false);
		this.stackCheck.setSelected(false);		
	}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución de una instruccion.
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> mem,
			OperandStack<Integer> stack) {
		this.count++;
		this.counter.setText("Numero de Instrucciones Ejecutadas: "+this.count);

	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onStartRun() {
		this.count=0;
		this.counter.setText("Numero de Instrucciones Ejecutadas: "+this.count);
	}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onEndRun() {}

	/**
	 * Metodo encargado de avisar de que ha ocurrido
	 * algun error durante la ejecucion de cualquier
	 * metodo de la cpu.
	 */
	public void onError(String msg) {}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt() {
		this.halt.setText("Maquina Parada");	
	}

	/**
	 * Metodo encargado de avisar del cambio de contenido
	 * de la posicion index a value.
	 * 
	 * @param index posicion.
	 * @param value valor.
	 */
	public void onWrite(int index, Integer value) {
		this.memoryCheck.setSelected(true);	
	}

	/**
	 * Metodo encargado de avisar del reinicio de la memoria.
	 */
	public void onMemReset() {}

	/**
	 * Metodo encargado de añadir un elemento a la pila.
	 */
	public void onPush(Integer value) {
		this.stackCheck.setSelected(true);	
	}

	/**
	 * Metodo encargado de eliminar la cima de la pila.
	 */
	public void onPop() {
		this.stackCheck.setSelected(true);
	}

	/**
	 * Metodo encargado de avisar del reinicio de la pila.
	 */
	public void onStackReset() {}


}
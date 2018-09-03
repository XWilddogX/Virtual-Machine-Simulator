package view;

import gui.panel.InputPanel;
import gui.panel.MemoryPanel;
import gui.panel.OutputPanel;
import gui.panel.ProgramPanel;
import gui.panel.StackPanel;
import gui.panel.StatusBarPanel;
import gui.panel.ToolBarPanel;
import instructions.Instruction;

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.CpuObserver;
import model.MemoryObserver;
import model.Observable;
import model.StackObserver;
import controller.Controller;
import cpu.Memory;
import cpu.OperandStack;


/**
 * Clase que representa la ventana principal.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class MainWindow extends JFrame implements CpuObserver{

	//Atributos.
	private static final long serialVersionUID = 1L;
	private ToolBarPanel toolBar;
	private ProgramPanel program;
	private InputPanel in;
	private OutputPanel out;
	private Controller ctrl;
	private StatusBarPanel status;
	private StackPanel stack;
	private MemoryPanel memory;
	private Observable<CpuObserver> cpu;
	private Observable<StackObserver<Integer>> observerStack;
	private Observable<MemoryObserver<Integer>> observerMemory;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param cpu
	 */
	public MainWindow(Controller ctrl, 
			Observable<CpuObserver> cpu, 
			Observable<StackObserver<Integer>> stack, 
			Observable<MemoryObserver<Integer>> memory){
		super("Virtual Machine");	
		this.ctrl=ctrl;
		this.cpu=cpu;
		this.observerMemory=memory;
		this.observerStack=stack;
		initGUI();
		cpu.addObserver(this);	
	}


	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI
	 */
	private void initGUI(){
		this.program=new ProgramPanel(ctrl,cpu);
		this.stack=new StackPanel(ctrl,cpu,observerStack);
		this.memory= new MemoryPanel(ctrl,cpu,observerMemory);
		this.in=new InputPanel(ctrl);
		this.out=new OutputPanel(ctrl);
		this.toolBar = new ToolBarPanel(ctrl,cpu);
		this.status=new StatusBarPanel(observerStack,observerMemory,cpu);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel secondPanel= new JPanel(new GridBagLayout());
		GridBagConstraints panel=new GridBagConstraints();

		//reparte en celdas las partes del panel secundairo y se añaden al panel principal
		panel.fill=GridBagConstraints.BOTH;
		panel.gridx=0;
		panel.gridy=0;
		panel.weightx=0.5;
		panel.weighty=0.75;
		secondPanel.add((Component) stack,panel);
		panel.gridx=1;
		secondPanel.add((Component) memory,panel);
		panel.gridx=0;
		panel.gridy=1;
		panel.gridwidth=2;
		panel.weighty=0.5;
		secondPanel.add(in,panel);
		panel.gridy=2;
		secondPanel.add(out,panel);
		this.setContentPane(mainPanel);
		mainPanel.add(toolBar, BorderLayout.PAGE_START);
		mainPanel.add(program, BorderLayout.WEST);
		mainPanel.add(status,BorderLayout.SOUTH);
		mainPanel.add(secondPanel, BorderLayout.CENTER);

		mainPanel.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		this.addWindowListener(new WindowAdapter() {
			// Al cerrar con X, aparece la ventana de dialogo.
			public void windowClosing(WindowEvent windowEvent) {
				if(JOptionPane.showConfirmDialog(null, "¿Realmente deseas abandonar la MV?", "MV", JOptionPane.OK_CANCEL_OPTION) == 0) {
					ctrl.quit();
				}
			}
		});
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución de una instruccion.
	 */
	public void onStartInstrExecution(Instruction instr) {}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución de una instruccion.
	 */
	public void onEndInstrExecution(int pc, Memory<Integer> mem,
			OperandStack<Integer> stack) {}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onStartRun() {}

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
	public void onError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt() {
		JOptionPane.showMessageDialog(null, "Se termino de realizar la ejecucion", "Maquina parada", JOptionPane.INFORMATION_MESSAGE);	
	}


}

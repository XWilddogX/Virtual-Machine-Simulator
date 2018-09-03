package gui.panel;

import instructions.Instruction;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.CpuObserver;
import model.Observable;
import model.StackObserver;
import controller.Controller;
import cpu.Memory;
import cpu.OperandStack;


/**
 * Clase que representa el panel de pila.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class StackPanel extends JPanel implements CpuObserver, StackObserver<Integer>{

	//Atributos.
	private static final long serialVersionUID = 1L;
	private Controller guiCtrl;
	private JList<Integer> stackArea;
	private JTextField stackElem;
	private DefaultListModel<Integer> model;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param ctrl
	 * @param cpu
	 * @param stack
	 */
	public StackPanel(Controller ctrl, Observable<CpuObserver> cpu,
			Observable<StackObserver<Integer>> stack) {
		this.guiCtrl = ctrl;
		initGUI();
		cpu.addObserver(this);
		stack.addObserver(this);
	}


	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI.
	 */
	private void initGUI() {
		this.model = new DefaultListModel<Integer>();	
		this.stackArea = new JList<Integer> (model);
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Stack"));
		JPanel secondPanel= new JPanel(new GridBagLayout());
		GridBagConstraints panel=new GridBagConstraints();
		panel.fill=GridBagConstraints.LINE_END;
		panel.gridx=0;
		panel.gridy=0;
		panel.weightx=1;
		panel.weighty=0.5;
		this.stackArea.setVisible(true);
		this.stackArea.setFont((new Font("Courier", Font.PLAIN, 16)));
		this.add(new JScrollPane(stackArea));
		JButton push = new JButton("Push");
		this.stackElem=new JTextField(5);
		JLabel jlabel = new JLabel("value");
		JButton pop =new JButton("Pop");
		secondPanel.add(jlabel,panel);
		panel.gridx=1;
		secondPanel.add(stackElem,panel);
		panel.gridx=2;
		secondPanel.add(push,panel);
		panel.gridx=3;
		secondPanel.add(pop,panel);
		this.add(secondPanel,BorderLayout.SOUTH);
		push.addActionListener( new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				try{
				guiCtrl.push(Integer.parseInt(stackElem.getText()));
				}
				catch(Exception a){			
				}
					
			}
		});
		pop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				guiCtrl.pop(); 
				}
				catch(Exception a){}
			}
		});
	}


	/**
	 * Metodo encargado de añadir un elemento a la pila.
	 */
	public void onPush(Integer value) {
		this.model.addElement(value);	
	}


	/**
	 * Metodo encargado de eliminar la cima de la pila.
	 */
	public void onPop() {
	this.model.remove(this.model.size()-1);		
	}

	/**
	 * Metodo encargado de avisar del reinicio de la pila.
	 */
	public void onStackReset() {}

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
			OperandStack<Integer> stack) {	
	}

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
	public void onError(String msg) {}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt() {}


}

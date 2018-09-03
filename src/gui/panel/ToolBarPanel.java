package gui.panel;

import instructions.Instruction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.CpuObserver;
import model.Observable;
import controller.Controller;
import cpu.Memory;
import cpu.OperandStack;


/**
 * Clase que representa el panel de barra de herramientas.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class ToolBarPanel extends JPanel implements CpuObserver {

	//Atributos.
	private static final long serialVersionUID = 1L;
	private Controller guiCtrl;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param ctrl
	 * @param cpu
	 */
	public ToolBarPanel(Controller ctrl, Observable<CpuObserver> cpu) {
		this.guiCtrl = ctrl;
		initGUI();
		cpu.addObserver(this);
	}


	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI.
	 */
	private void initGUI(){
		JButton stepButton = new JButton(); 
		JButton runButton = new JButton();
		JButton quitButton = new JButton();
		stepButton.setIcon(new ImageIcon("./bin/gui/swing/step.png"));
		stepButton.setToolTipText("Step");
		runButton.setIcon(new ImageIcon("./bin/gui/swing/run.png"));
		runButton.setToolTipText("Run");
		quitButton.setIcon(new ImageIcon("./bin/gui/swing/exit.png"));
		quitButton.setToolTipText("Quit");
		this.add(stepButton);
		this.add(runButton);
		this.add(quitButton);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.step();
			}
		});
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Realmente deseas abandonar la MV?", "MV", JOptionPane.OK_CANCEL_OPTION) == 0)
					guiCtrl.quit();
			}
		});
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.run(); 
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
			OperandStack<Integer> stack) {	
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onStartRun() {
		this.setEnabled(false);
	}

	/**
	 * Metodo encargado de avisar de que ha terminado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onEndRun() {
		this.setEnabled(false);
	}

	/**
	 * Metodo encargado de avisar de que ha ocurrido
	 * algun error durante la ejecucion de cualquier
	 * metodo de la cpu.
	 */
	public void onError(String msg) {
		this.setEnabled(true);	
	}

	/**
	 * Metodo encargado de avisar de que la ejecucion
	 * del programa ha terminado.
	 */
	public void onHalt() {this.setEnabled(true);}


}

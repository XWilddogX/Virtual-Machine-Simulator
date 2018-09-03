package gui.panel;

import instructions.Instruction;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

import model.CpuObserver;
import model.Observable;
import controller.Controller;
import cpu.Memory;
import cpu.OperandStack;
import cpu.mv.ProgramMV;


/**
 * Clase que representa el panel de programa.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class ProgramPanel extends JPanel implements CpuObserver {

	//Atributos.
	private static final long serialVersionUID = 1L;
	private Controller guiCtrl;
	private JTextArea programTextArea;
	private ProgramMV program; 
	private int pc;


	//Constructora.
	/**
	 * Constructora con parametros.
	 * 
	 * @param ctrl
	 * @param cpu
	 */
	public ProgramPanel(Controller ctrl, Observable<CpuObserver> cpu) {
		this.guiCtrl = ctrl;
		this.program=this.guiCtrl.getProgram();
		this.pc=0;
		initGUI();
		cpu.addObserver(this);
		showProgram();
	}


	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI
	 */
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Program"));
		this.programTextArea = new JTextArea(20,15);
		this.programTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		this.programTextArea.setEditable(false);
		this.add(new JScrollPane(programTextArea));
	}

	/**
	 * Metodo encargado de actualizar la vista.
	 */
	private void showProgram(){
		this.programTextArea.setText("");
		this.programTextArea.setText(this.program.toString());
		try {
			this.programTextArea.insert("*", this.programTextArea.getLineStartOffset(this.pc));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
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
		this.pc=pc;
		showProgram();
	}

	/**
	 * Metodo encargado de avisar de que ha comenzado
	 * la ejecución del metodo run de la cpu.
	 */
	public void onStartRun() {
		this.pc=0;
		showProgram();
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
	public void onHalt() {}

}


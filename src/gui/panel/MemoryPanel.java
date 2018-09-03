package gui.panel;

import instructions.Instruction;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import model.CpuObserver;
import model.MemoryObserver;
import model.Observable;
import controller.Controller;
import controller.GUIController;
import cpu.Memory;
import cpu.OperandStack;


/**
 * Clase que representa el panel de memoria.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 2.0
 */
public class MemoryPanel extends JPanel implements  CpuObserver, MemoryObserver<Integer>{

	//Atributos.
	private static final long serialVersionUID = 1L;
	private Controller guiCtrl;
	private MemTableModel model;
	private JTextField memPos;
	private JTextField memValue;


	//Constructoras.
	/**
	 * Constructora con parametros.
	 * 
	 * @param guiCtrl controlador.
	 */
	public MemoryPanel(GUIController guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	/**
	 * Constructora con parametros.
	 * 
	 * @param ctrl controlador.
	 * @param cpu.
	 * @param memory.
	 */
	public MemoryPanel(Controller ctrl, Observable<CpuObserver> cpu,
			Observable<MemoryObserver<Integer>> memory) {
		this.guiCtrl = ctrl;
		initGUI();
		cpu.addObserver(this);
		memory.addObserver(this);
	}


	//Metodos.
	/**
	 * Metodo encargado de inicializar el GUI.
	 */
	private void initGUI() {
		model = new MemTableModel();
		JTable table = new JTable(model);
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Memory"));
		table.setVisible(true);
		table.setFont((new Font("Courier", Font.PLAIN, 16)));
		this.add(new JScrollPane(table));
		JPanel secondPanel= new JPanel(new GridBagLayout());
		GridBagConstraints panel=new GridBagConstraints();
		panel.fill=GridBagConstraints.LINE_END;
		panel.gridx=0;
		panel.gridy=0;
		panel.weightx=0.15;
		panel.weighty=2;
		JLabel value = new JLabel("Value");
		JLabel pos = new JLabel("Position");
		secondPanel.add(pos,panel);
		this.memPos=new JTextField(5);
		panel.gridx=1;
		secondPanel.add(memPos,panel);
		panel.gridx=2;
		secondPanel.add(value,panel);
		this.memValue=new JTextField(5);
		panel.gridx=3;
		secondPanel.add(memValue,panel);
		panel.gridx=4;
		JButton write = new JButton("Write");
		secondPanel.add(write,panel);
		write.addActionListener( new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				try{
				guiCtrl.memorySet(Integer.parseInt(memPos.getText()),Integer.parseInt(memValue.getText()));		
				}
				catch(Exception a){}
				
			}
		});
		this.add(secondPanel,BorderLayout.SOUTH);
	}



	/**
	 * Metodo encargado de avisar del cambio de contenido
	 * de la posicion index a value.
	 * 
	 * @param index posicion.
	 * @param value valor.
	 */
	public void onWrite(int index, Integer value) {
		this.model.setValue(index, value);
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

	/**
	 * Metodo encargado de eliminar la cima de la pila.
	 */
	public void onMemReset() {}



	/**
	 * Clase interna que representa la tabla de memoria.
	 * 
	 * @author Alejandro Lopez, Sergio Montero
	 * @version 1.0
	 */
	private class MemTableModel extends AbstractTableModel { 

		//Atributos.
		private static final long serialVersionUID = 1L;
		String [] colNames = {"Address","Value"}; 
		int[][] memTable; // tiene dos columnas 
		TreeMap<Integer, Integer> content;


		//Constructora.
		/**
		 * Constructora por defecto.
		 */
		public MemTableModel() {
			this.content=new TreeMap<Integer,Integer>();
		}

		//Metodos.
		/**
		 * Metodo enargado de realizar cambios en la memoria.
		 * 
		 * @param index
		 * @param value
		 */
		public void setValue(int index, Integer value) {      
			this.content.put(index,value);
			refresh();
		}
		/**
		 * Metodo encargado de devolver el nombre de la columna.
		 * 
		 * @return String nombre de la columna.
		 */
		public String getColumnName(int i){
			return this.colNames[i].toString();
		}

		/**
		 * Metodo encargado de devolver la cantidad de columnas.
		 * 
		 * @return int nº de columnas.
		 */
		public int getColumnCount() {
			return this.colNames.length;
		}


		/**
		 * Metodo encargado de refrescar la tabla.
		 */
		public void refresh() {
			int numRows=getRowCount(),i=0;
			this.memTable=new int [numRows][2];
			for(Integer key: this.content.keySet()){
				this.memTable[i][0]=key;
				this.memTable[i][1]=content.get(key);
				i++;
			}
			fireTableDataChanged();
		}

		/**
		 * Metodo encargado de devolver el numero de filas.
		 */
		public int getRowCount() {
			return this.content.size();
		}

		/**
		 * Metodo encargado de devolver un valor concreto
		 * de la tabla de memoria.
		 */
		public Object getValueAt(int index, int value) {
			return this.memTable[index][value];
		}
	}


}

package cpu;

import java.util.ArrayList;

import model.MemoryObserver;
import model.Observable;


/**
 * Clase que representa la memoria de la maquina virtual. 
 * Aunque se supone que la memoria es infinita, en realidad la constructora
 * define la capacidad de la misma, ampliandola cuando sea necesario.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 3.0
 */
public class Memory<T> implements Observable<MemoryObserver<T>>{

	// Atributos.
	private ArrayList<T> memory;
	private int capacity;
	private ArrayList<MemoryObserver<T>> observer;

	// Constructora.
	/**
	 * Constructora sin parametros.
	 */
	public Memory(){
		this.observer=new ArrayList<MemoryObserver<T>>();
		this.memory = new ArrayList<T>();
		this.capacity=50;
		for(int i=0;i<this.capacity;i++){
			this.memory.add(i,null);
		}
	}


	// Metodos.
	/**
	 * Metodo encargado de devolver el valor
	 * en la posicion de memoria indicada (LOAD).
	 * 
	 * @param pos posicion de memoria donde buscar el valor a cargar.
	 * @return valor de la posicion de memoria indicada.
	 * @throws MVError 
	 */
	public T memLoad(int pos) throws MVError{
		try{
			return this.memory.get(pos);
		}
		catch(IndexOutOfBoundsException e){
			throw new MVError("La direccion de memoria cargada esta vacia");
		}
	}

	/**
	 * Metodo encargado de reiniciar la memoria
	 */
	public void clearMemory(){
		this.memory.clear();
		for(int i=0;i<this.capacity;i++){
			this.memory.add(i,null);
		}
	}

	/**
	 * Metodo encargado de almacenar un valor
	 * en la posicion de memoria indicada (STORE).
	 * 
	 * @param pos posicion de memoria donde almacenar el valor.
	 * @param value valor a almacenar en la posicion de memoria.
	 */
	public void memStore(int pos,T value){
		while (pos>=this.capacity)
			amplifyCapacity();
		this.memory.set(pos, value);
		onWrite(pos,value);

	}

	/**
	 * Metodo encargado de ampliar la capacidad de la memoria.
	 */
	private void amplifyCapacity(){
		for(int i=this.capacity;i<this.capacity+50;i++)
			this.memory.add(i,null);
		this.capacity+=50;
	}

	/**
	 * Metodo encargado de devolver una representacion textual
	 * del estado de la memoria.
	 * 
	 * @return String cadena con el estado de la memoria.
	 */
	public String toString(){
		boolean isEmpty = true;
		int i=0;
		String memoria ="";
		while(this.capacity>i){
			if (this.memory.get(i) != null){
				memoria = memoria+"["+i+"]"+":"+this.memory.get(i)+" ";
				isEmpty = false;
			}
			i++;
		}
		if(isEmpty)
			memoria ="<vacia>";
		return memoria;
	}


	/**
	 * Clase interna que representa una celda de memoria para el modo ventana.
	 * 
	 * @author Alejandro Lopez, Sergio Montero
	 * @version 1.0
	 * @param <T>
	 */
	public static class MemCell<T>{

		//Atributos.
		int pos;
		T value;


		//Constructora, getters y setters.
		/**
		 * Constructora con parametros.
		 * 
		 * @param pos posicion de memoria.
		 * @param value valor almacenado en memoria.
		 */
		public MemCell(int pos, T value) {
			this.pos = pos;
			this.value = value;
		}

		/**
		 * Getter encargado de devolver la posicion.
		 * 
		 * @return pos posicion de memoria.
		 */
		public int getPos() {
			return pos; 
		}

		/**
		 * Getter encargado de devolver el valor de memoria.
		 * 
		 * @return value valor en memoria.
		 */
		public T getValue() {
			return value;
		}
	}


	/**
	 * Metodo encargado de devolver un array de memoria para el modo ventana.
	 * 
	 * @return Array de memoria.
	 */
	public ArrayList<MemCell<T>> getArray() {
		ArrayList<MemCell<T>> memCell=new ArrayList<MemCell<T>>();
		int i=0;
		while(this.capacity>i){
			if (this.memory.get(i) != null)
				memCell.add(new MemCell<T>(i,this.memory.get(i)));	
			i++;
		}
		return memCell;
	}

	/**
	 * Metodo encargado de añadir un observador de la memoria.
	 */
	public void addObserver(MemoryObserver<T> o) {
		this.observer.add(o);
	}

	/**
	 * Metodo encargado de eliminar un observador de la memoria.
	 */
	public void removeObserver(MemoryObserver<T> o) {
		this.observer.remove(o);
	}

	/**
	 * Metodo encargado de avisar a los oyentes del cambio de contenido
	 * de la posicion index a value.
	 * 
	 * @param index posicion.
	 * @param value valor.
	 */
	public  void onWrite(int index, T value){
		for(MemoryObserver<T> o : this.observer) {
			o.onWrite(index, value);
		}
	}

}

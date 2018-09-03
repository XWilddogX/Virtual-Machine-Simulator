package cpu;

import java.util.ArrayList;
import java.util.Stack;

import model.Observable;
import model.StackObserver;


/**
 * Clase que representa la pila de operandos.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 3.0
 */
public class OperandStack<T> implements Observable<StackObserver<T>> {

	// Atributos.
	private Stack<T> operands;
	private ArrayList<StackObserver<T>> observer;


	// Constructora, getters y setters.
	/**
	 * Constructora por defecto.
	 */
	public OperandStack(){
		this.operands = new Stack<T>();
		this.observer=new ArrayList<StackObserver<T>>();
	}
	/**
	 * Metodo encargado de reiniciar la pila.
	 * 

	 */
	public void clearStack(){
		this.operands.clear();
	}

	/**
	 * Getter encargado de devolver la pila.
	 * 
	 * @return operands pila.
	 */
	public Stack<T> getStack(){
		return this.operands;	
	}


	//Metodos.
	/**
	 * Metodo encargado de apilar un elemento en la pila.
	 * 
	 * @param value elemento a apilar.
	 */
	public  void stackPush(T value){
		this.operands.push(value);
		onPush(value);
	}

	/**
	 * Metodo encargado de eliminar la cima de la pila .
	 * 
	 * @return devuelve su valor eliminado.
	 */
	public  T stackPop()throws MVError{
		try{T pop=this.operands.pop();
		onPop();
		return pop;
		}
		catch(Exception e){
			throw new MVError("Pila Vacia");
		}
	}

	/**
	 * Metodo encargado de devolver una representacion
	 * textual del estado de la pila.
	 * 
	 * @return String cadena con el estado de la pila.
	 */
	public String toString(){
		String stack="";	
		int j=0;
		if(this.operands.size()!=0) {
			for( @SuppressWarnings("unused") T i: this.operands){
				stack += this.operands.get(j)+ " ";
				j++;
			}
		}
		else
			stack ="<vacio>";
		return stack;
	}

	/**
	 * Metodo encargado de añadir un observador de la pila.
	 */
	public void addObserver(StackObserver<T> o) {	
		this.observer.add(o);
	}

	/**
	 * Metodo encargado de eliminar un observador de la pila.
	 */
	public void removeObserver(StackObserver<T> o) {
		this.observer.remove(o);
	}

	/**
	 * Metodo encargado de avisar a los oyentes del push en la pila.
	 * 
	 * @param value 
	 */
	public  void onPush(T value){
		for(StackObserver<T> o : this.observer) {
			o.onPush(value);
		}
	}

	/**
	 * Metodo encargado de avisar a los oyentes del pop en la pila. 
	 */
	public  void onPop(){
		for(StackObserver<T> o : this.observer) {
			o.onPop();
		}
	}


}

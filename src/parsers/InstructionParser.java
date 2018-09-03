package parsers;

import instructions.Instruction;
import instructions.arithmetics.Add;
import instructions.arithmetics.Div;
import instructions.arithmetics.Mul;
import instructions.arithmetics.Sub;
import instructions.booleans.And;
import instructions.booleans.Not;
import instructions.booleans.Or;
import instructions.jumps.Bf;
import instructions.jumps.Bt;
import instructions.jumps.Jump;
import instructions.jumps.JumpInd;
import instructions.jumps.RBf;
import instructions.jumps.RBt;
import instructions.jumps.RJump;
import instructions.numericCond.Eq;
import instructions.numericCond.Gt;
import instructions.numericCond.Le;
import instructions.numericCond.Lt;
import instructions.restSeq.Dup;
import instructions.restSeq.Flip;
import instructions.restSeq.Halt;
import instructions.restSeq.In;
import instructions.restSeq.Load;
import instructions.restSeq.LoadInd;
import instructions.restSeq.Neg;
import instructions.restSeq.Out;
import instructions.restSeq.Pop;
import instructions.restSeq.Push;
import instructions.restSeq.Store;
import instructions.restSeq.StoreInd;


/**
 * Clase encargada de analizar la entrada de texto del usuario
 * y de crear la instrucción correspondiente.
 * 
 * @author Alejandro Lopez, Sergio Montero
 * @version 1.0
 */
public class InstructionParser {

	private static Instruction instructionSet[] = 
		{new Add(), new Sub(), new Div(), new Mul(), new And(), new Or(), 
		new Not(), new Jump(), new Bf(), new Bt(), new Lt(), new Le(),
		new Eq(), new Gt(), new Dup(), new Flip(), new Halt(), new Load(),
		new Neg(), new Out(), new Pop(), new Push(), new Store(),new RJump(),new RBf(),new RBt(),new In(),new JumpInd(),new StoreInd(),
		new LoadInd()};


	// Metodos.
	/**
	 * Metodo que analiza el texto pasado por parametro y devuelve
	 * la instruccion correspondiente. En caso de error devuelve null.
	 * 
	 * @param s cadena a convertir en instruccion.
	 * @return instruccion correspondiente.
	 */
	public static Instruction instructionParser(String s){
		String[] split = s.split(" ");

		Instruction instr=null;

		int i=0;
		boolean stop=false;
		while (i<InstructionParser.instructionSet.length && !stop){
			instr =InstructionParser.instructionSet[i].parse(split);
			if (instr!=null) stop = true;
			else i++;
		}
		return instr;
	}

}

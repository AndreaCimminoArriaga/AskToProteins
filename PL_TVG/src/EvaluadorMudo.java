// $ANTLR : "treeparserMudo.g" -> "EvaluadorMudo.java"$

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;


public class EvaluadorMudo extends antlr.TreeParser       implements EvaluadorMudoTokenTypes
 {
public EvaluadorMudo() {
	tokenNames = _tokenNames;
}

	public final void consulta(AST _t) throws RecognitionException {
		
		AST consulta_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
		try {      // for error handling
			AST __t1149 = _t;
			AST tmp1_AST_in = (AST)_t;
			match(_t,CONSULTA);
			_t = _t.getFirstChild();
			{
			int _cnt1151=0;
			_loop1151:
			do {
				if (_t==null) _t=ASTNULL;
				if ((_t.getType()==PROTEIN||_t.getType()==OR)) {
					elementos(_t);
					_t = _retTree;
				}
				else {
					if ( _cnt1151>=1 ) { break _loop1151; } else {throw new NoViableAltException(_t);}
				}
				
				_cnt1151++;
			} while (true);
			}
			_t = __t1149;
			_t = _t.getNextSibling();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	public final void elementos(AST _t) throws RecognitionException {
		
		AST elementos_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST pr = null;
		AST p = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case OR:
			{
				AST __t1153 = _t;
				AST tmp2_AST_in = (AST)_t;
				match(_t,OR);
				_t = _t.getFirstChild();
				System.out.print("NODE: OR -> ");
				{
				int _cnt1155=0;
				_loop1155:
				do {
					if (_t==null) _t=ASTNULL;
					if ((_t.getType()==PROTEIN)) {
						pr = (AST)_t;
						match(_t,PROTEIN);
						_t = _t.getNextSibling();
						System.out.print(" "+pr.getText());
					}
					else {
						if ( _cnt1155>=1 ) { break _loop1155; } else {throw new NoViableAltException(_t);}
					}
					
					_cnt1155++;
				} while (true);
				}
				System.out.println();
				_t = __t1153;
				_t = _t.getNextSibling();
				break;
			}
			case PROTEIN:
			{
				p = (AST)_t;
				match(_t,PROTEIN);
				_t = _t.getNextSibling();
				System.out.println("NODE: "+p.getText());
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			if (_t!=null) {_t = _t.getNextSibling();}
		}
		_retTree = _t;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"CONSULTA",
		"PROTEIN",
		"OR",
		"\";\"",
		"COM",
		"\"(\"",
		"\")\"",
		"\"|\"",
		"PROTEINA",
		"PUNT",
		"GEN"
	};
	
	}
	

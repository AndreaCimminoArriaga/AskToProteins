// $ANTLR : "anasinConsulta.g" -> "AnasintConsulta.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class AnasintConsulta extends antlr.LLkParser       implements AnasintConsultaTokenTypes
 {

protected AnasintConsulta(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public AnasintConsulta(TokenBuffer tokenBuf) {
  this(tokenBuf,3);
}

protected AnasintConsulta(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public AnasintConsulta(TokenStream lexer) {
  this(lexer,3);
}

public AnasintConsulta(ParserSharedInputState state) {
  super(state,3);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void entradaC() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST entradaC_AST = null;
		
		try {      // for error handling
			consulta();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp1_AST = null;
			tmp1_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1_AST);
			match(Token.EOF_TYPE);
			entradaC_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = entradaC_AST;
	}
	
	public final void consulta() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST consulta_AST = null;
		
		try {      // for error handling
			elementos();
			astFactory.addASTChild(currentAST, returnAST);
			match(7);
			consulta_AST = (AST)currentAST.root;
			consulta_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(CONSULTA,"CONSULTA")).add(consulta_AST));
			currentAST.root = consulta_AST;
			currentAST.child = consulta_AST!=null &&consulta_AST.getFirstChild()!=null ?
				consulta_AST.getFirstChild() : consulta_AST;
			currentAST.advanceChildToEnd();
			consulta_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = consulta_AST;
	}
	
	public final void elementos() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST elementos_AST = null;
		
		try {      // for error handling
			elemento();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop5:
			do {
				if ((LA(1)==COM)) {
					match(COM);
					elemento();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop5;
				}
				
			} while (true);
			}
			elementos_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = elementos_AST;
	}
	
	public final void elemento() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST elemento_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case PROTEINA:
			case PUNT:
			case GEN:
			{
				proteinas();
				astFactory.addASTChild(currentAST, returnAST);
				elemento_AST = (AST)currentAST.root;
				break;
			}
			case 9:
			{
				match(9);
				expresion();
				astFactory.addASTChild(currentAST, returnAST);
				match(10);
				elemento_AST = (AST)currentAST.root;
				elemento_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(OR,"OR")).add(elemento_AST));
				currentAST.root = elemento_AST;
				currentAST.child = elemento_AST!=null &&elemento_AST.getFirstChild()!=null ?
					elemento_AST.getFirstChild() : elemento_AST;
				currentAST.advanceChildToEnd();
				elemento_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = elemento_AST;
	}
	
	public final void proteinas() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST proteinas_AST = null;
		Token  p = null;
		AST p_AST = null;
		Token  o = null;
		AST o_AST = null;
		Token  g = null;
		AST g_AST = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case PROTEINA:
			{
				p = LT(1);
				p_AST = astFactory.create(p);
				astFactory.addASTChild(currentAST, p_AST);
				match(PROTEINA);
				proteinas_AST = (AST)currentAST.root;
				proteinas_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(PROTEIN,p.getText())));
				currentAST.root = proteinas_AST;
				currentAST.child = proteinas_AST!=null &&proteinas_AST.getFirstChild()!=null ?
					proteinas_AST.getFirstChild() : proteinas_AST;
				currentAST.advanceChildToEnd();
				proteinas_AST = (AST)currentAST.root;
				break;
			}
			case PUNT:
			{
				o = LT(1);
				o_AST = astFactory.create(o);
				astFactory.addASTChild(currentAST, o_AST);
				match(PUNT);
				proteinas_AST = (AST)currentAST.root;
				proteinas_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(PROTEIN,o.getText())));
				currentAST.root = proteinas_AST;
				currentAST.child = proteinas_AST!=null &&proteinas_AST.getFirstChild()!=null ?
					proteinas_AST.getFirstChild() : proteinas_AST;
				currentAST.advanceChildToEnd();
				proteinas_AST = (AST)currentAST.root;
				break;
			}
			case GEN:
			{
				g = LT(1);
				g_AST = astFactory.create(g);
				astFactory.addASTChild(currentAST, g_AST);
				match(GEN);
				proteinas_AST = (AST)currentAST.root;
				proteinas_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(PROTEIN,g.getText())));
				currentAST.root = proteinas_AST;
				currentAST.child = proteinas_AST!=null &&proteinas_AST.getFirstChild()!=null ?
					proteinas_AST.getFirstChild() : proteinas_AST;
				currentAST.advanceChildToEnd();
				proteinas_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = proteinas_AST;
	}
	
	public final void expresion() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expresion_AST = null;
		
		try {      // for error handling
			proteina();
			astFactory.addASTChild(currentAST, returnAST);
			{
			int _cnt9=0;
			_loop9:
			do {
				if ((LA(1)==11)) {
					match(11);
					proteina();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt9>=1 ) { break _loop9; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt9++;
			} while (true);
			}
			expresion_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
		returnAST = expresion_AST;
	}
	
	public final void proteina() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST proteina_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		try {      // for error handling
			p = LT(1);
			p_AST = astFactory.create(p);
			astFactory.addASTChild(currentAST, p_AST);
			match(PROTEINA);
			proteina_AST = (AST)currentAST.root;
			proteina_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(PROTEIN,p.getText())));
			currentAST.root = proteina_AST;
			currentAST.child = proteina_AST!=null &&proteina_AST.getFirstChild()!=null ?
				proteina_AST.getFirstChild() : proteina_AST;
			currentAST.advanceChildToEnd();
			proteina_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
		returnAST = proteina_AST;
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
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 128L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 1024L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 3072L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}

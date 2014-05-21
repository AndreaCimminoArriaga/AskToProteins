// $ANTLR : "anasinEntrada.g" -> "AnasintEntrada.java"$

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

public class AnasintEntrada extends antlr.LLkParser       implements AnasintEntradaTokenTypes
 {

	Traductor trad = new TraductorImpl();	

protected AnasintEntrada(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public AnasintEntrada(TokenBuffer tokenBuf) {
  this(tokenBuf,4);
}

protected AnasintEntrada(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public AnasintEntrada(TokenStream lexer) {
  this(lexer,4);
}

public AnasintEntrada(ParserSharedInputState state) {
  super(state,4);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void entrada() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST entrada_AST = null;
		
		try {      // for error handling
			{
			int _cnt686=0;
			_loop686:
			do {
				if ((LA(1)==LETRA)) {
					cadenas();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt686>=1 ) { break _loop686; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt686++;
			} while (true);
			}
			match(Token.EOF_TYPE);
			entrada_AST = (AST)currentAST.root;
			entrada_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SEC,"SEC")).add(entrada_AST));
			currentAST.root = entrada_AST;
			currentAST.child = entrada_AST!=null &&entrada_AST.getFirstChild()!=null ?
				entrada_AST.getFirstChild() : entrada_AST;
			currentAST.advanceChildToEnd();
			entrada_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
		returnAST = entrada_AST;
	}
	
	public final void cadenas() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cadenas_AST = null;
		String s="";
		
		try {      // for error handling
			{
			s=cadena();
			astFactory.addASTChild(currentAST, returnAST);
			}
			match(8);
			cadenas_AST = (AST)currentAST.root;
			if(s=="CADENA"){
				{cadenas_AST = (AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(CADENA,"CADENA")).add(cadenas_AST));}
			}else{
				{cadenas_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(ERROR,"ERROR")));}
			}
			
			currentAST.root = cadenas_AST;
			currentAST.child = cadenas_AST!=null &&cadenas_AST.getFirstChild()!=null ?
				cadenas_AST.getFirstChild() : cadenas_AST;
			currentAST.advanceChildToEnd();
			cadenas_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
		returnAST = cadenas_AST;
	}
	
	public final String  cadena() throws RecognitionException, TokenStreamException {
		String s="";
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST cadena_AST = null;
		String t="",t1="";
		
		try {      // for error handling
			{
			t1=ini();
			astFactory.addASTChild(currentAST, returnAST);
			
										  			 if(t1.contains("Error")){
										  					s="ERROR";
										  			 }
										  			
			}
			{
			int _cnt692=0;
			_loop692:
			do {
				if ((LA(1)==LETRA)) {
					t=tripleta();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					if ( _cnt692>=1 ) { break _loop692; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt692++;
			} while (true);
			}
			if(t!="STOP"){
										  		s="ERROR";
										  		}
										  	  if(!s.contains("Error") && s!="ERROR"){
										  	   	s="CADENA";
										  	   	}
										  	
			cadena_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
		returnAST = cadena_AST;
		return s;
	}
	
	public final String  ini() throws RecognitionException, TokenStreamException {
		String trip="";
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST ini_AST = null;
		String s="";
		
		try {      // for error handling
			s=tripleta();
			astFactory.addASTChild(currentAST, returnAST);
			if(s!="Met"){trip="Error";}else{trip=s;}
			ini_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
		returnAST = ini_AST;
		return trip;
	}
	
	public final String  tripleta() throws RecognitionException, TokenStreamException {
		String trip="";
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tripleta_AST = null;
		Token  l1 = null;
		AST l1_AST = null;
		Token  l2 = null;
		AST l2_AST = null;
		Token  l3 = null;
		AST l3_AST = null;
		String s1="";
		
		try {      // for error handling
			l1 = LT(1);
			l1_AST = astFactory.create(l1);
			astFactory.addASTChild(currentAST, l1_AST);
			match(LETRA);
			l2 = LT(1);
			l2_AST = astFactory.create(l2);
			astFactory.addASTChild(currentAST, l2_AST);
			match(LETRA);
			l3 = LT(1);
			l3_AST = astFactory.create(l3);
			astFactory.addASTChild(currentAST, l3_AST);
			match(LETRA);
			trip = trad.adnProteina(l1,l2,l3);
			tripleta_AST = (AST)currentAST.root;
			tripleta_AST = (AST)astFactory.make( (new ASTArray(1)).add(astFactory.create(PROT,trip)));
			currentAST.root = tripleta_AST;
			currentAST.child = tripleta_AST!=null &&tripleta_AST.getFirstChild()!=null ?
				tripleta_AST.getFirstChild() : tripleta_AST;
			currentAST.advanceChildToEnd();
			tripleta_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
		returnAST = tripleta_AST;
		return trip;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"SEC",
		"CADENA",
		"ERROR",
		"PROT",
		"\";\"",
		"LETRA"
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
		long[] data = { 514L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 512L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 768L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}

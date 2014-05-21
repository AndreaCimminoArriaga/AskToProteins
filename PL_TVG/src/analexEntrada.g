///////////////////////////////
// Analizador lexico base
///////////////////////////////
class AnalexEntrada extends Lexer;

options{
	importVocab = AnasintEntrada;
	k=3;
	charVocabulary = '\3'..'\377';
}

// Blancos
protected NUEVA_LINEA : "\n" {newline();};
BLANCO : (' '|'\t'|NUEVA_LINEA) {$setType(Token.SKIP);};

// Letras
LETRA : 'A'..'Z';

// Separador de linea
SEPARADOR: ';';

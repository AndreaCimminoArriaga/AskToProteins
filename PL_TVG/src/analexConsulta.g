///////////////////////////////
// Analizador lexico base
///////////////////////////////
class AnalexConsulta extends Lexer;

options{
	importVocab = AnasintConsulta;
	k=3;
	charVocabulary = '\3'..'\377';
}

// Blancos
protected NUEVA_LINEA : "\n" {newline();};
BLANCO : (' '|'\t'|NUEVA_LINEA) {$setType(Token.SKIP);};

// Identificadores
protected LETRA : 'A'..'Z'|'a'..'z';

PROTEINA: ( LETRA LETRA LETRA)  (LETRA)?;
	
// Separadores:
	//De elementos
COM: ",";
	//De linea
FIN: ";" ;
	//Otros
PA: "(" | ")";

// Operadores
GEN: "*";
PUNT:".";
OPOR: "|" ;







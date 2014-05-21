///////////////////////////////
// Analizador sintactico
///////////////////////////////
class AnasintConsulta extends Parser;

options{
	k=3;
	buildAST=true;	
}
tokens{
	CONSULTA;
	PROTEIN;
	OR;
}

entradaC : consulta EOF ;

consulta: elementos ";"! 
 		  {## = #( #[CONSULTA,"CONSULTA"], ## );}
 		  ;

elementos: elemento ( COM! elemento )* ;

elemento:  proteinas              //Caso proteina: concreta, generica, o lista de genericas					     
	     | "("! expresion ")"!  {## = #( #[OR,"OR"], ## );}    //Caso lista OR de proteinas
         ;			
         	
expresion: proteina ("|"! proteina)+;
	
proteinas:  p:PROTEINA {## = #( #[PROTEIN,p.getText()] );}	//Proteina concreta
		  | o:PUNT	   {## = #( #[PROTEIN,o.getText()] );}  //Proteina generica
		  | g:GEN	   {## = #( #[PROTEIN,g.getText()] );}  //Numero arbitrario de proteinas
	      ;				

proteina: p:PROTEINA {## = #( #[PROTEIN,p.getText()] );};

/*
NOTA: En este fichero he definido un tipo Token llamado PROTEIN que se asocia a las proteinas, los puntos
	y los asteriscos. Podria haber definido para cada uno un token distinto, pero en vista de que al fin
	y al cabo los tres elementos definen una proteina y que no es necesario distinguirlos en el arbol, 
	los tres los he asociado a un mismo token. 
	El unico sitio donde es necesario distinguir el tipo de String asociado al token PROTEIN es a la hora
	de cotejar la consulta con la cadena, pero para eso simplemente uso los metodos comparadores de 
	String (este proceso se realiza en el treeParser).
*/		
		
		
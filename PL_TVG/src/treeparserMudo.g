///////////////////////////////
// Evaluador de expresiones
///////////////////////////////

class EvaluadorMudo extends TreeParser;
options{
	importVocab = AnasintConsulta;	
}

consulta: #(CONSULTA  ( elementos )+   ) ;
  								  								
elementos :  #(OR {System.out.print("NODE: OR -> ");} (pr:PROTEIN {System.out.print(" "+pr.getText());} )+  {System.out.println();}) 
			| p:PROTEIN {System.out.println("NODE: "+p.getText());}
			;
			




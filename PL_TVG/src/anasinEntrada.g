///////////////////////////////
// Analizador sintactico
///////////////////////////////
class AnasintEntrada extends Parser;

options{
	k=4;
	buildAST=true;	
}
tokens{
	SEC;	
	CADENA;
	ERROR;
	PROT;
}
{
	Traductor trad = new TraductorImpl();	
}

entrada : (cadenas)+ EOF!
         {## = #(#[SEC,"SEC"], ##);}
        ;


cadenas {String s="";}: (s=cadena) ";"! 
                        {if(s=="CADENA"){
                        	{## = #(#[CADENA,"CADENA"], ##);}
                         }else{
                         	{## = #(#[ERROR,"ERROR"]);}
                         }
                        };


cadena returns [String s=""]{String t="",t1="";}:  
							  ( t1=ini {
							  			 if(t1.contains("Error")){
							  					s="ERROR";
							  			 }
							  			}) 
							  ( t=tripleta )+ 
							  { if(t!="STOP"){
							  		s="ERROR";
							  		}
							  	  if(!s.contains("Error") && s!="ERROR"){
							  	   	s="CADENA";
							  	   	}
							  	 } 
							   ;

							
ini returns [String trip=""] {String s="";}: s=tripleta { if(s!="Met"){trip="Error";}else{trip=s;} } ;


tripleta returns [String trip=""] {String s1="";}: l1:LETRA l2:LETRA l3:LETRA { trip = trad.adnProteina(l1,l2,l3);}
									           	{## = #(#[PROT,trip]);} ;



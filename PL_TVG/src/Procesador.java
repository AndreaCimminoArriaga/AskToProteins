/////////////////////////////////////
// Procesador.java (clase principal)
/////////////////////////////////////
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import antlr.*;
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;

public class Procesador {

	public static void main(String args[]) {
		try {
			
			
			//Entrada
			
			FileInputStream fisEntrada = new FileInputStream("entrada.txt");			
			AnalexEntrada analexEntrada = new AnalexEntrada(fisEntrada);
			AnasintEntrada anasintEntrada = new AnasintEntrada(analexEntrada);
			anasintEntrada.entrada();
			AST arbolEntrada = anasintEntrada.getAST();
			
				//Tratamiento de las entradas -> Almacenar en Map
			Map<Integer,List<String>> entradas = trataArbolEntrada(arbolEntrada);

				//Frame Entrada
			ASTFrame frameEntrada= new ASTFrame("entrada.txt",arbolEntrada);
		    frameEntrada.setVisible(true);
			
		  //----------------------------\\//----------------------------\\
		    
			//Consulta

			FileInputStream fisConsulta = new FileInputStream("consulta.txt");
			AnalexConsulta analexConsulta = new AnalexConsulta(fisConsulta);
			AnasintConsulta anasintConsulta = new AnasintConsulta(analexConsulta);
			anasintConsulta.entradaC();
			AST arbolConsulta = anasintConsulta.getAST();
				//Frame Consulta
			ASTFrame frameConsulta= new ASTFrame("consulta.txt",arbolConsulta);
		    frameConsulta.setVisible(true);
			
		    //----------------------------\\//----------------------------\\
		    
			//TreeParser
			System.out.println("-------// Cotejamiento \\---------");
			System.out.println();
			Evaluador evaluador = new Evaluador();
			
			
			Integer i=1;
				//TreeParser Mudo
			//System.out.println("\t\t---// TreeParser Mudo \\---");
			//EvaluadorMudo evaluadorMudo = new EvaluadorMudo();
			//evaluadorMudo.consulta(arbolConsulta);
				
				//Variables para almacenar resultados
			
			Map<List<String>,Double> resultados = new HashMap<List<String>,Double>();
			Double percentage = 0.0;
			
				//Recorrer las entradas
			
			for(Integer key:entradas.keySet()){
				List<String> entrada = entradas.get(key);
				System.out.println("- Procesando Cadena entrada "+i+" : "+entrada.toString()+"\n");
				try{
				
					percentage = evaluador.consulta(arbolConsulta, entrada, true);
				
				}catch(Exception e){
					
					System.out.println("\n"+e.toString());
					break;
				
				}
				resultados.put(entrada, percentage);
				i++;
			}
			
			System.out.println("---------------- RESULTADOS ---------------- \n");
			
			System.out.println("\tConsulta: [ "+arbolConsulta.toStringTree().substring(11, arbolConsulta.toStringTree().length()-1)+" ]\n");
			
			i=0;
			List<List<String>> keys = Lists.newArrayList(resultados.keySet());
			while(i<keys.size()){
				System.out.println(keys.get(i).toString()+"  :  "+Math.round(resultados.get(keys.get(i)))+"%");
				i++;
			}
			
			
			
			
		} catch (ANTLRException ae) {
			System.err.println(ae.getMessage());
		} catch (FileNotFoundException fnfe) {
			System.err.println("No se encontro el fichero");
		}
	}
	
	
	
	private static Map<Integer, List<String>> trataArbolEntrada(AST arbolEntrada){
		//Arbol de la entrada en lista de string
		String tree = arbolEntrada.toStringTree();
		tree = tree.substring(7, tree.length()-1);
		List<String> arbol = Lists.newArrayList(tree.split(" "));
		//Mapa que contendra las cadenas de entrada validas
		Map<Integer,List<String>> consultas = new HashMap<Integer, List<String>>();
		//Comprueba cuando empieza y termina una cadena
		Boolean comienzo = false;
		//Sera el indicador que nos dira la clave para una cadena dada.
		Integer puntero=0;
		
		//En tree ahora tenemos ERROR o (CADENA ... ) 
		for(String nodo:arbol){
			
			if(nodo.equals("(")){
				comienzo=true;
			}
			if(nodo.equals(")")){
				comienzo=false;
				puntero++;
			}
			//Si es una cadena
			if(comienzo){
				//Creamos la clave con el puntero actual
				if(nodo.equals("CADENA")){
					List<String> valor = Lists.newArrayList();
					consultas.put(puntero, valor);
				}
				//A–adimos y actualizamos la lista de proteinas para dicha clave
				if(!(nodo.equals("CADENA")) && !(nodo.equals("("))){
					
					List<String> values = consultas.get(puntero);
					//Si la lista de proteinas estaba vacia la inicializamos
					if(values.isEmpty()){
						List<String> value = Lists.newArrayList(nodo);
						consultas.put(puntero, value);
					}else{
						//si la lista de proteinas no estaba vacia a–adimos el nuevo valor
						values.add(nodo);
						consultas.put(puntero, values);
						
					}						
				}
			}
		}
		return consultas;
	}

}

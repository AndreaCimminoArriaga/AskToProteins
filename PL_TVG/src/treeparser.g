header{
	import java.util.List;
	import com.google.common.collect.Lists;
}
///////////////////////////////
// Evaluador de expresiones
///////////////////////////////
class Evaluador extends TreeParser;

options{
	importVocab = AnasintConsulta;	
}

{
////////////////////////////////////////////////////////////////
// 							VARIABLES AUXILIARES
////////////////////////////////////////////////////////////////

	//Necesarias para cotejar el *
static Boolean  asterisk_mode = false;   //Indicara si el nodo anterior fue un *
static List<String> asterisk_proteins = Lists.newArrayList();  //Si el elemento anterior fue * indicara las posibles proteinas que podrian estar incluidas en el *
static Integer ajuste_aciertos = 0; //Sumara o restara aciertos en base al * 
static Boolean with_asterisk = false;	//Se activara si la consulta posee un nodo *
	// Necesarias para cotejar OR
List<String> or = Lists.newArrayList(); //Almacenara las posibilidades de un nodo OR
static Boolean last_is_or = false; //Indicara si el ultimo nodo es un OR y antes se habia lanzado un *
	// Necesarias para cotejar todos los elementos en general
static List<String> elementos = Lists.newArrayList(); //contendra una copia de la cadena de entrada
static Integer counter = 0;	//Indicara la posicion del recorrido de la entrada-consulta
static String node = null;	//Almacenara en cada caso el nodo actual de la consulta
static Boolean err_consulta = false;	//Indicara si la cadena tiene un error sintactico
Boolean last_elem_match = false;	//Indicara si el ultimo elemento se machea o no --> OPCIONAL, SU CODIGO ESTA COMENTADO
String elemento = null;	//Almacenara el elemento i-esimo de la entrada
	// Necesarias para la inferencia estadistica
Integer total = 0;	//Total elementos de la entrada
Integer total_consulta = 0;	//Total elementos de la consulta, numero de nodos
Integer aciertos = 0; //Numero de aciertos
Integer fallos = 0;	//Numero de fallos

////////////////////////////////////////////////////////////////
// 							FUNCIONES AUXILIARES
////////////////////////////////////////////////////////////////

// Funcion Auxiliar "estadisticas" --> Muestra las estadisticas de la consulta, si hay error devolvera -1.
public static Double estadisticas(Integer aciert, Integer fallos, Integer total, Boolean por100, Boolean asterisk_mode){
	//Calcula el porcentaje de aciertos
	Double percent = (((aciert*1.0)/total));
	//Si hubo un error de cualquier clase devuelve -1.0
	if(err_consulta){
		return -1.0;
	}
	//Si el usuario desea ver el porcentaje de parecido entre la cadena y la consulta
	if(por100){
  		//Ajuste de porcentaje
  		if(percent>1){
  			percent += (1-percent);	
  		}
  		return (percent*100);	
  		
  	}else{
  		//En caso contrario devolvemos 100 o 0 dependiendo si coincide totalmente o no la cadena y la consulta
  		if(percent==1){
  			return 100.0;
  		}else{
  			return 0.0;
  		}
 	}
}

// Funcion Auxiliar "coteja" --> Coteja los elementos ".", "PROTEINA" y "*" 
public static Boolean coteja(String node, String elemento ){
	//Si el nodo actual es un . y la entrada es correcta, no habia un * antes no es Met
	if(node.equals(".") && !elemento.equals("Met")  && !asterisk_mode){	
		return true;
	}
	//Si el nodo actual es un . y la entrada es Met --> ERROR						
	if(node.equals(".") && elemento.equals("Met")){
		err_consulta = true;
		throw new IllegalArgumentException("No esta permitido comenzar una consulta con el operador .");
	}
	//Si el nodo actual es un . y antes se habia lanzado un * --> ERROR	
	if(node.equals(".") && asterisk_mode){	
		err_consulta = true;
		throw new IllegalArgumentException("En la consulta no se permite el operador * seguido del operador. El operador * incluye a ., reescriba la misma consulta sin el .");
	}		
	//Si el nodo actual coincide con la proteina de la entrada y no se habia lanzado un * antes
	if(node.equals(elemento) && !asterisk_mode){
		return true;
	}
	//Si  se habia lanzado un * antes
	if(asterisk_mode){
		//Ver si el nodo actual coincide con alguna de las futuras entradas
		
		asterisk_mode = false;
		
		if(asterisk_proteins.contains(node) && !node.equals(".")){
			//En caso afirmativo calculamos la posicion del nodo que coincide con una futura entrada
			counter = elementos.lastIndexOf(node)-1;
			//Todas las entradas hasta la coincidente con el nodo son aciertos, los a–adimos al ajuste de aciertos
			ajuste_aciertos = asterisk_proteins.size()-2;
			//Limpiamos la lista para futuras iteraciones
			asterisk_proteins.clear();
			return true;
			
		}else{
			//En caso contrario a–adimos al ajuste de aciertos el numero de proteinas falladas, tiene un -2 porque la primera proteina que llama al*
			//automaticamente es acierto, y hay que restarle otro error mas de la actual.
			ajuste_aciertos =  (asterisk_proteins.size()-3);
			//Calculamos la posicion del nodo que coincide con una futura entrada, la ultima de la lista de *
			counter = elementos.lastIndexOf(asterisk_proteins.get(asterisk_proteins.size()-1))-1;
			//Limpiamos la lista para futuras iteraciones
			asterisk_proteins.clear();
			return false;	
		}
	}
	
	//Si el nodo actual es un *, no entrara nunca si antes se lanzo un *
	if(node.equals("*") && !asterisk_mode){
		//Activamos el modo asterisco para la proxima iteracion
		asterisk_mode = true;
		//A–adimos la entrada actual a la lista de proteinas *
		asterisk_proteins.add(elemento);
		//Activamos la variable que nos indicara que la cadena de consulta tenia un * entre sus elementos
		with_asterisk = true;
		return true;			
	}
	return false;
}

/////////////////////////////////////////////////////////////////////////////////////////

}

consulta [List<String> entrada, Boolean mostrar_por100] returns [Double percentage=0.0;]: #(CONSULTA {elementos=entrada;total=entrada.size();} 
															(e:elementos {total_consulta++;})+ 
																{
																 if(with_asterisk){
																 	//Si la consulta contenia *
																 	//Lanzar estadisticas	
																	 if(total >= total_consulta){
																 		if(total == total_consulta){
																 			percentage = estadisticas(aciertos, fallos, total,mostrar_por100, asterisk_mode);
																 		}else{
																 			//Si despues de un * se lanza un or la variable nodo no cambiara del valor *
																 			//por eso la varible last_is_or es necesaria
																 			if(node.equals("*") && !last_is_or){
																 				//Si el ultimo elemento de la consulta era * la diferencia
																 				//de tama–o entre entrada y consulta son aciertos.
																 				aciertos += total-total_consulta;
																 			}else{
																 				//Si el ultimo elemento de la consulta no fue * el numero
																 				//de aciertos que hay que a–adir estaran contenidos en ajuste_aciertos
																 				aciertos += ajuste_aciertos;
										
																 			}
																 			percentage = estadisticas(aciertos, fallos, total,mostrar_por100, asterisk_mode);
																 		}
																	 }else{	
																	 	
																		percentage = estadisticas(aciertos, fallos, total_consulta,mostrar_por100, asterisk_mode); 	
																 	}
																 
																 }else{
																 	//Si la consulta no contiene *
																 	//Lanzar estadisticas	
																	 if(total >= total_consulta){
																 		if(total == total_consulta){
																 			percentage = estadisticas(aciertos, fallos, total,mostrar_por100, asterisk_mode);
																 		}else{
																 			fallos += total-total_consulta;
																 			//Dejar o quitaro À?!------------------------------->>
																 			/*if(node.equals(elementos.get(elementos.size()-1))){
																 				aciertos++;
																 				fallos--;	
																 			}*/
																 			
																 			percentage = estadisticas(aciertos, fallos, total,mostrar_por100, asterisk_mode);
																 		}
																	 }else{	
																	 	fallos += total_consulta-total;
																	 	/*Dejar o quitaro À?!-------------------------->> 
																	 	if(last_elem_match){
																	 		last_elem_match = false;
																	 		aciertos++;
																	 		fallos--;
																	 	}*/
																		percentage = estadisticas(aciertos, fallos, total_consulta,mostrar_por100, asterisk_mode); 	
																 	
																 	}
																 }
																 //Operaciones de puesta a punto para proximas iteraciones
																 total =0;
																 aciertos = 0;
																 fallos = 0;
																 counter = 0;
																 total_consulta=0;
																 ajuste_aciertos = 0;
																 asterisk_mode = false;
																 asterisk_proteins.clear();
																 or.clear();
																 });
  									

elementos :  #(OR  (p:PROTEIN {or.add(p.getText());} )+  
								{
									//Si el contador en cualquier momento por el motivo que sea tiene un valor menor que 0
									//Lanzamos una excepcion
									if(counter <0){
										throw new IllegalArgumentException("Internal Error. Counter: "+ counter);
									}
									//Controlamos que el ultimo nodo no es un OR
									last_is_or = false;
									
									//En caso de que el contador este dentro de un rango aceptable; esto es, menor que el 
									//tama–o de la cadena de entrada.
								 	if( counter < total){
								 	//Obtenemos los elementos para cotejar: entrada y la lista or con las opciones
								 	 elemento = elementos.get(counter);
									 //Cotejamiento
									 if(asterisk_mode){
									 	last_is_or = true;
									 	//Si el elemento anterior fue un *
									 	for(String protein:elementos.subList(counter, elementos.size())){
											asterisk_proteins.add(protein);	
										}
										//Vamos a comprobar si algunas de las opciones coincide con el nodo actual
										Boolean is_contained = false;
										String node_aux = "";
										//Recorremos las proteinas contenidas en las opciones
										for(String protein:or){
											//Si alguna esta contenida en las proteinas del * la guardamos
											if(asterisk_proteins.contains(protein)){
												node_aux = protein;	
											}
											is_contained = (is_contained) || asterisk_proteins.contains(protein);
										}
										
										//Operaciones de puesta a punto para la proxima iteracion
										asterisk_mode = false;
										
										
										if(is_contained){
											//Si alguno de los ementos OR coincide actualizamos el counter y los aciertos
											counter = elementos.lastIndexOf(node_aux)-1;
											ajuste_aciertos = asterisk_proteins.size()-2;
											asterisk_proteins.clear();
											aciertos++;
										}else{
											//Si ninguno de los elementos OR coincide ajustamos los aciertos
											ajuste_aciertos =  (asterisk_proteins.size()-3);
											//Calculamos la posicion del nodo que coincide con una futura entrada, la ultima de la lista de *
											counter = elementos.lastIndexOf(asterisk_proteins.get(asterisk_proteins.size()-1))-1;
											asterisk_proteins.clear();
											fallos++;
										}		
									 }else{
										//Si el elemento anterior no fue un *
										 if(or.contains(elemento) ){
								 			//Si alguna de las opciones contiene a la entrada
								 			aciertos++;	
								 		}else{
								 			fallos++;
								 	 }
								 	//Operaciones de puesta a punto para proximas iteraciones
								 	 or.clear();
									 counter++;	
							   	 }
						 		}    	
							 }) 
						 
			| pr:PROTEIN {		
							//Si el contador en cualquier momento por el motivo que sea tiene un valor menor que 0
							//Lanzamos una excepcion
							if(counter <0){
								throw new IllegalArgumentException("Internal Error. Counter: "+ counter);
							}
									
							//Operaciones auxiliares para el operador *: si antes se lanzo un * creamos una posible lista de proteinas
							//que serian las abarcadas por el *
							if(asterisk_mode){
								for(String protein:elementos.subList(counter, elementos.size())){
									asterisk_proteins.add(protein);	
								}								
							}
							
							//Obteniendo nodo.
							node = pr.getText();					
							//Si la consulta tiene un tama–o menor que la entrada
							if(counter < total ){	
								//Obteniendo elemento de la entrada
								elemento = elementos.get(counter);
								//Cotejamiento
								if(coteja(node, elemento)){
									aciertos++;
								}else{
									fallos++;
								}
								//Operaciones de puesta a punto para proximas iteraciones
								counter++;	
							}else{
								//Si la consulta es mayor que la cadena y tenemos un *, se arrastrara el ultimo 
								//elemento de la entrada y se cotejara con el resto de nodos de la consulta
								if(with_asterisk){
									if(coteja(node, elemento) ){
										aciertos++;
									}else{
										fallos++;
									}
								}else{
									//Si no hubo asterisco tambien podriamos arrastrar el ultimo elemento. --->> OPCIONA CODIGO COMENTADO
									last_elem_match = coteja(node, elemento) ;
								}
							}
						};				
			




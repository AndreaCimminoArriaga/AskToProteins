
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import antlr.Token;


public class TraductorImpl implements Traductor {
	private Map<String,String> mapaArnM;
	private Map<String,String> mapaProteinas;
	private Map<String,String> mapaAbreviaturas;
	
	//Constructor
	public TraductorImpl(){
		this.mapaArnM = new HashMap<String, String>();
		this.mapaProteinas = new HashMap<String, String>();
		this.mapaAbreviaturas = new HashMap<String, String>();
		
		//Inicializacion del mapa traductor de AND a ARN mensajero
		inicializaMapaArnM();
		//Inicializacion del mapa traductor de Tripletas a proteinas
		inicializaMapaProteinas();
		//metodo que inicializa el mapa de Abreviaturas
		inicializaMapaAbreviaturas();
	}
	
	/*
	 * Metodos Getters de los atributos
	 */
	
	public Map<String, String> getMapaArnM() {
		return mapaArnM;
	}
	
	public Map<String, String> getMapaProteinas() {
		return mapaProteinas;
	}
	
	public Map<String, String> getMapaAvreviaturas() {
		return mapaAbreviaturas;
	}
	
	/*
	 * Metodos de traduccion
	 */
	
	// Traduce una letra (Token_type) del ADN a ARN mensajero
	/* (non-Javadoc)
	 * @see Traductor#ArnM(antlr.Token)
	 */
	@Override
	public String ArnM (Token t){
		return getMapaArnM().get(t.getText());
	}
	
	// Dada una tripleta devuelve una proteina, la tripleta puede ser String o 3 tokens
	/* (non-Javadoc)
	 * @see Traductor#Proteinas(java.lang.String)
	 */
	@Override
	public String Proteinas(String s){
		return getMapaProteinas().get(s);
	}
	
	/* (non-Javadoc)
	 * @see Traductor#Proteinas(antlr.Token, antlr.Token, antlr.Token)
	 */
	@Override
	public String Proteinas(Token l1, Token l2, Token l3){
		String s1 = l1.getText();
		String s2 = l2.getText();
		String s3 = l3.getText();
		String s = s1.concat(s2.concat(s3));
		
		return getMapaProteinas().get(s);		
	}
	
	//Traduce tres letras de ADN en una proteina
	public String adnProteina(Token l1, Token l2, Token l3){
		String s1 = this.ArnM(l1);
		String s2 = this.ArnM(l2);
		String s3 = this.ArnM(l3);
		String s = s1.concat(s2.concat(s3));
		return getMapaProteinas().get(s);	
	}
	
	//Dada una proteina devuelve su abreviatura
	/* (non-Javadoc)
	 * @see Traductor#abreviaProteina(java.lang.String)
	 */
	@Override
	public String abreviaProteina(String proteina){
		String abrev = null;
		for(Entry<String, String> i:this.getMapaAvreviaturas().entrySet()){
			if( (i.getValue()) == proteina){
				abrev = i.getKey();
			}
		}
		return abrev;
	
	}
	
	//Dada una abreviatura de una proteina devuelve el nombre completo
	/* (non-Javadoc)
	 * @see Traductor#nombreProteina(java.lang.String)
	 */
	@Override
	public String nombreProteina(String proteina){
		return this.getMapaAvreviaturas().get(proteina);
	}
	
	
	
	
	/*
	 * Metodos de inicializacion de los Atributos.
	 * 		(Invocados en el constructor)
	 */
	
	//Inicializacion del mapa traductor de AND a ARN mensajero
	private void inicializaMapaArnM(){
		this.mapaArnM.put("A", "U");
		this.mapaArnM.put("T", "A");
		this.mapaArnM.put("C", "G");
		this.mapaArnM.put("G", "C");
	}
	
	//metodo que inicializa el mapa de proteinas
	private void inicializaMapaProteinas(){
		//UUX
		this.mapaProteinas.put("UUU", "Phe");
		this.mapaProteinas.put("UUC", "Phe");
		this.mapaProteinas.put("UUA", "Leu");
		this.mapaProteinas.put("UUG", "Leu");
		//UCX
		this.mapaProteinas.put("UCU", "Ser");
		this.mapaProteinas.put("UCC", "Ser");
		this.mapaProteinas.put("UCA", "Ser");
		this.mapaProteinas.put("UCG", "Ser");
		//UAX
		this.mapaProteinas.put("UAU", "Tyr");
		this.mapaProteinas.put("UAC", "Tyr");
		this.mapaProteinas.put("UAA", "STOP");
		this.mapaProteinas.put("UAG", "STOP");
		//UGX
		this.mapaProteinas.put("UGU", "Cys");
		this.mapaProteinas.put("UGC", "Cys");
		this.mapaProteinas.put("UGA", "STOP");
		this.mapaProteinas.put("UGG", "Trp");
		
		//CUX
		this.mapaProteinas.put("CUU", "Leu");
		this.mapaProteinas.put("CUC", "Leu");
		this.mapaProteinas.put("CUA", "Leu");
		this.mapaProteinas.put("CUG", "Leu");
		//CCX
		this.mapaProteinas.put("CCU", "Pro");
		this.mapaProteinas.put("CCC", "Pro");
		this.mapaProteinas.put("CCA", "Pro");
		this.mapaProteinas.put("CCG", "Pro");
		//CAX
		this.mapaProteinas.put("CAU", "His");
		this.mapaProteinas.put("CAC", "His");
		this.mapaProteinas.put("CAA", "Gln");
		this.mapaProteinas.put("CAG", "Gln");
		//CGX
		this.mapaProteinas.put("CGU", "Arg");
		this.mapaProteinas.put("CGC", "Arg");
		this.mapaProteinas.put("CGA", "Arg");
		this.mapaProteinas.put("CGG", "Arg");
		
		// AUX
		this.mapaProteinas.put("AUU", "Ile");
		this.mapaProteinas.put("AUC", "Ile");
		this.mapaProteinas.put("AUA", "Ile");
		this.mapaProteinas.put("AUG", "Met");
		// ACX
		this.mapaProteinas.put("ACU", "Thr");
		this.mapaProteinas.put("ACC", "Thr");
		this.mapaProteinas.put("ACA", "Thr");
		this.mapaProteinas.put("ACG", "Thr");
		// AAX
		this.mapaProteinas.put("AAU", "Asn");
		this.mapaProteinas.put("AAC", "Asn");
		this.mapaProteinas.put("AAA", "Lys");
		this.mapaProteinas.put("AAG", "Lys");
		// AGX
		this.mapaProteinas.put("AGU", "Ser");
		this.mapaProteinas.put("AGC", "Ser");
		this.mapaProteinas.put("AGA", "Arg");
		this.mapaProteinas.put("AGG", "Arg");
		
		// GUX
		this.mapaProteinas.put("GUU", "Val");
		this.mapaProteinas.put("GUC", "Val");
		this.mapaProteinas.put("GUA", "Val");
		this.mapaProteinas.put("GUG", "Val");
		// GCX
		this.mapaProteinas.put("GCU", "Ala");
		this.mapaProteinas.put("GCC", "Ala");
		this.mapaProteinas.put("GCA", "Ala");
		this.mapaProteinas.put("GCG", "Ala");
		// GAX
		this.mapaProteinas.put("GAU", "Asp");
		this.mapaProteinas.put("GAC", "Asp");
		this.mapaProteinas.put("GAA", "Glu");
		this.mapaProteinas.put("GAG", "Glu");
		// GGX
		this.mapaProteinas.put("GGU", "Gly");
		this.mapaProteinas.put("GGC", "Gly");
		this.mapaProteinas.put("GGA", "Gly");
		this.mapaProteinas.put("GGG", "Gly");
		
	}
	
	//metodo que inicializa el mapa de Abreviaturas
		private void inicializaMapaAbreviaturas(){
			this.mapaAbreviaturas.put("Phe", "Fenilanina");
			this.mapaAbreviaturas.put("Leu", "Leucina");
			this.mapaAbreviaturas.put("Ile", "Isoleucina");
			this.mapaAbreviaturas.put("Met", "Metonina");
			this.mapaAbreviaturas.put("Val", "Valina");
			this.mapaAbreviaturas.put("Ser", "Serina");
			this.mapaAbreviaturas.put("Pro", "Prolina");
			this.mapaAbreviaturas.put("Thr", "Treonina");
			this.mapaAbreviaturas.put("Ala", "Alanina");
			this.mapaAbreviaturas.put("Tyr", "Tyrosina");
			this.mapaAbreviaturas.put("STOP", "STOP");
			this.mapaAbreviaturas.put("His", "Histidina");
			this.mapaAbreviaturas.put("Gln", "Glutamina");
			this.mapaAbreviaturas.put("Asn", "Aspargina");
			this.mapaAbreviaturas.put("Lys", "Lisina");
			this.mapaAbreviaturas.put("Asp", "Acido aspartico");
			this.mapaAbreviaturas.put("Glu", "Acido glutamico");
			this.mapaAbreviaturas.put("Cys", "Cisteina");
			this.mapaAbreviaturas.put("Trp", "Triptofano");
			this.mapaAbreviaturas.put("Arg", "Argenina");
			this.mapaAbreviaturas.put("Ser", "Serina");
			this.mapaAbreviaturas.put("Gly", "Glicina");
			this.mapaAbreviaturas.put("STOP", "STOP");
		}
	
}

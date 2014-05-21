
import antlr.Token;

public interface Traductor {

	// Traduce una letra (Token_type) del ADN a ARN mensajero
	public abstract String ArnM(Token t);

	// Dada una tripleta devuelve una proteina, la tripleta puede ser String o 3 tokens
	public abstract String Proteinas(String s);

	public abstract String Proteinas(Token l1, Token l2, Token l3);

	//Dada una proteina devuelve su abreviatura
	public abstract String abreviaProteina(String proteina);

	//Dada una abreviatura de una proteina devuelve el nombre completo
	public abstract String nombreProteina(String proteina);

	//Traduce tres letras de ADN en una proteina
	public String adnProteina(Token l1, Token l2, Token l3);
}
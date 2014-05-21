// $ANTLR : "treeparser.g" -> "Evaluador.java"$

	import java.util.List;
	import com.google.common.collect.Lists;

public interface EvaluadorTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int CONSULTA = 4;
	int PROTEIN = 5;
	int OR = 6;
	// ";" = 7
	int COM = 8;
	// "(" = 9
	// ")" = 10
	// "|" = 11
	int PROTEINA = 12;
	int PUNT = 13;
	int GEN = 14;
}

package eapli.base.surveys.src.domain;

import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class FormGrammar {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("/Users/jtiagoaraujo/OneDrive - Instituto Superior de Engenharia do Porto/ISEP/2º ANO/2º SEMESTRE/LEI21_22_S4_2DK_01/base.core/src/main/java/eapli/base/surveys/questionnaire.txt"));
        FormGrammarLexer lexer = new FormGrammarLexer(new ANTLRInputStream(fis));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormGrammarParser parser = new FormGrammarParser(tokens);
        ParseTree tree = parser.lprog(); // parse
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}

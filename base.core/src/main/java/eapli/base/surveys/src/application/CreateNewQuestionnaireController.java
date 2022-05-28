package eapli.base.surveys.src.application;

import eapli.base.surveys.src.domain.FormGrammarLexer;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;
import java.nio.file.Paths;

public class CreateNewQuestionnaireController {

    public boolean validation(String path) throws IOException{
        FormGrammarLexer validate = new FormGrammarLexer(CharStreams.fromPath(Paths.get(path)));

        return true;
    }
}

package eapli.base.surveys.src.domain;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;


public class EvalVisitor extends FormGrammarBaseVisitor<String> {

    public String visitParameterAnswer(FormGrammarParser.ParameterAnswerContext ctx) {
        FormGrammarParser.ParameterQuestionContext questionContext = (FormGrammarParser.ParameterQuestionContext) ctx.getParent();
        String obligatoriness = questionContext.parameterQuestion().parameterOblig().getText();
        String d = questionContext.parameterAnswer().getText();

        List<String> error = new ArrayList<>();

        String[] words = {"horrible", "awful", "disappointing", "hate"};
        for (String e : words) {
            if (d.contains(e))
                error.add("Error the answer contains inappropriate words and is not accepted: " + e);
            //System.out.println("Error the answer contains inappropriate words and is not accepted: " + e);
        }

        if (!questionContext.parameterQuestion().parameterQuestionType().getText().equalsIgnoreCase("Type: Free-Text.")) {
            if (obligatoriness.equals("Obligatoriness: Mandatory.")) {
                if ((!(d.matches("[1-4]")))) {
                    error.add("Error while choosing the option for mandatory question :" + d + " not valid");
                    // System.out.println("Error while choosing the option for mandatory question :" + d + " not valid");

                }
            }
        }

        if (d.matches(RandomStringUtils.randomAlphabetic(10).toLowerCase(Locale.ROOT))) {
            error.add("Error while answering, random phrase: " + d);
            //System.out.println("Error while answering, random phrase: " + d);

        }
        if ((obligatoriness.equals("Obligatoriness: Mandatory.") && (questionContext.parameterQuestion().parameterQuestionType().getText().equalsIgnoreCase("Type: Free-Text.")))) {
            if (d.length() == 1 || d.length() > 50) {
                error.add("Error while answering a mandatory question, length of answer is not valid: " + d);
                //System.out.println("Error while answering a mandatory question, length of answer is not valid: " + d);
            }
        }

        for (String fg : error) {
            System.out.println(fg);

        }

        return "Answers are all checked";
    }

}
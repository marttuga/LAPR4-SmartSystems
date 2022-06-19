package eapli.base.surveys.src.domain;

import eapli.base.ordersmanagement.answer.application.AnswerController;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.survey.domain.Survey;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;



public class EvalVisitor extends FormGrammarBaseVisitor<String> {


    public String visitParameterAnswer(FormGrammarParser.ParameterAnswerContext ctx) {
        FormGrammarParser.ParameterQuestionContext questionContext = (FormGrammarParser.ParameterQuestionContext) ctx.getParent();
        String obligatoriness = questionContext.parameterQuestion().parameterOblig().getText();
        String d = questionContext.parameterAnswer().getText();
        List<String> words = new ArrayList<>();
        words.add("hated");
        words.add("horrible");
        words.add("awful");
        words.add("disappointing");
        words.add("hate");
        List<String> error = new ArrayList<>();
        if (words.contains(d)) {
            error.add("Error the answer contains inappropriate words and is not accepted: " + d);


        }
        if ((questionContext.parameterQuestion().parameterQuestionType().getText().startsWith("Type: Single-Choice.") || questionContext.parameterQuestion().parameterQuestionType().getText().startsWith("TYPE: SINGLE-CHOICE.")) && !obligatoriness.equals("Obligatoriness: Optional.")) {
            if (!(d.matches("[1-6]"))) {
                System.out.println("Error while choosing the option :" + d + " not valid");
            }

        }
        if (d.matches(RandomStringUtils.randomAlphabetic(10).toLowerCase(Locale.ROOT))) {
            System.out.println("Error while answering, random phrase " + d);

        }
        if ((obligatoriness.equals("Obligatoriness: Mandatory.") && !(questionContext.parameterQuestion().parameterQuestionType().getText().startsWith("Type: Single-Choice.")))) {
            if (d.length() == 1 || d.length() > 50) {
                System.out.println("Error while answering a mandatory question, length of answer is not valid");
            }


        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String e : error
        ) {
            stringBuilder.append(e).append("\n");
        }


        //System.out.println("Answers are all checked");
        return String.valueOf(stringBuilder);
    }


}
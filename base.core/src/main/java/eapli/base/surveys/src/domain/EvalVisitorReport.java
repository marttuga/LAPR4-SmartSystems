package eapli.base.surveys.src.domain;

import eapli.base.ordersmanagement.answer.domain.Answer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

public class EvalVisitorReport extends FormGrammarBaseVisitor<String> {

    private final List<Answer> answers;

    public EvalVisitorReport(List<Answer> answers) {
        this.answers = answers;
    }


    @Override
    public String visitParameterMultipleChoice(FormGrammarParser.ParameterMultipleChoiceContext ctx) {
        System.out.println("=============== MULTIPLE CHOICE STATISTICS ===================");
        List<String> answersMultipleList = new ArrayList<>();
        fillWithAnswers(ctx, answersMultipleList);
        Map<String, Integer> answersMulMap = new LinkedHashMap<>();
        Map<String, Integer> answersMultipleMap = new LinkedHashMap<>();
        List<String> multList ;
       for (String answer : answersMultipleList) {
            boolean correct = false;
            FormGrammarParser.ChoicesContext choicesCtx = ctx.choices();
            while (!(choicesCtx == null)) {
                String choice = visitChoice(choicesCtx.choice());


                if (!answersMulMap.containsKey(String.valueOf(choice.charAt(0)))) {
                    String option = String.valueOf(choice.charAt(0));
                    answersMulMap.put(option, 0);
                }


                multList = new ArrayList<>(Arrays.asList(answer.split(",")));
                for (String s: multList) {
                    if (s.equals(String.valueOf(choice.charAt(0)))) {
                        correct = true;
                        int n = answersMulMap.get(s);
                        answersMulMap.put(s, n + 1);
                    }
                }
                answersMultipleMap.put(answer, 0);
                choicesCtx = choicesCtx.choices();
            }
            if (correct) {
                int i = answersMultipleMap.get(answer);
                answersMultipleMap.put(answer, i+1);
                System.out.println("Answer: " + answer + " (Valid!)\n");
            } else System.out.println("Incorrect answer: " + answer + " (No option available)\n");
        }
        multipleChoiceStatistics(answersMulMap, answersMultipleMap);
        return "done";
    }


    @Override
    public String visitChoice(FormGrammarParser.ChoiceContext ctx) {
        return ctx.getText();
    }



    @Override
    public String visitParameterSingleChoice(FormGrammarParser.ParameterSingleChoiceContext ctx) {
        List<String> answersList = new ArrayList<>();
        fillWithAnswers(ctx, answersList);
        Map<String, Integer> answersMap = new LinkedHashMap<>();
        for (String answer : answersList) {
            boolean correct = false;
            FormGrammarParser.ChoicesContext choicesCtx = ctx.choices();
            while (!(choicesCtx == null)) {
                String choice = visitChoice(choicesCtx.choice());
                if (!answersMap.containsKey(String.valueOf(choice.charAt(0)))) {
                    String option = String.valueOf(choice.charAt(0));
                    answersMap.put(option, 0);
                }
                if (answer.equals(String.valueOf(choice.charAt(0)))) {
                    correct = true;
                    int n = answersMap.get(answer);
                    answersMap.put(answer, n + 1);
                }
                choicesCtx = choicesCtx.choices();
            }
            if (correct) {
                System.out.println("Answer: " + answer + " (Valid!)\n");
            } else System.out.println("Incorrect answer: " + answer + " (No option available)\n");
        }
        singleChoiceStatistics(answersMap);
        return "done";
    }

    private void fillWithAnswers(ParserRuleContext ctx, List<String> AnswerList) {
        FormGrammarParser.ParameterQuestionContext questionContext = (FormGrammarParser.ParameterQuestionContext) ctx.getParent().getParent().getParent().getParent();
        String questionId = questionContext.parameterQuestionId().getText();
        String question = questionContext.parameterQ().getText();
        String obligatoriness = questionContext.parameterOblig().getText();
        System.out.println("\n" + questionId + ": " + question + "\n");
        for (Answer a : answers) {
            try {
                String answer = a.getEvery_Answer().get(questionId);
                if (!answer.equalsIgnoreCase("Not answered")) {
                    AnswerList.add(answer);
                } else if (!obligatoriness.equals("Obligatoriness: Optional.")) {
                    System.out.println("This question is mandatory and was not answered: " + questionId);
                }

            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void singleChoiceStatistics(Map<String, Integer> answersMap) {
        int amount = 0;
        for (String choice : answersMap.keySet()) {
            amount = amount + answersMap.get(choice);
        }
        for (String choice : answersMap.keySet()) {
            float a = answersMap.get(choice);
            float result = a / amount;
            float resultPerc = result * 100;
            System.out.println(choice + " - " + resultPerc + "%.");
        }
    }

    private void multipleChoiceStatistics(Map<String, Integer> answersMap, Map<String, Integer> answersMMap) {
        singleChoiceStatistics(answersMap);
        System.out.println("\n============ COMBINED MULTIPLE CHOICE STATISTICS =============\n");
        singleChoiceStatistics(answersMMap);
        System.out.println("\n");

    }


}

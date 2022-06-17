package eapli.base.surveys.src.domain;

import eapli.base.ordersmanagement.answer.domain.Answer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitorReport extends FormGrammarBaseVisitor<String>{

    private final List<Answer> answers;

    public EvalVisitorReport(List<Answer> answers){
        this.answers=answers;
    }

    @Override
    public String visitChoices(FormGrammarParser.ChoicesContext ctx){
        return ctx.getText();
    }

    @Override
    public String visitChoice(FormGrammarParser.ChoiceContext ctx){
        return ctx.getText();
    }
    @Override
    public String visitParameterSingleChoice(FormGrammarParser.ParameterSingleChoiceContext ctx){
        List<String> answersList = new ArrayList<>();
        List<String> numbQuestions = new ArrayList<>();
        fillWithAnswers(ctx, answersList);
        for (String s:
             answersList) {
            System.out.println(s);
        }
        Map<String, Integer> answersMap = new LinkedHashMap<>();

        for (String answer: answersList) {
            boolean correct = false;
            FormGrammarParser.ChoicesContext choicesCtx= ctx.choices();
            while (!(choicesCtx == null)){
                String choice = visitChoice(choicesCtx.choice());

                if (!answersMap.containsKey(choice.charAt(0))){
                    answersMap.put(String.valueOf(choice.charAt(0)),0);
                }
                for (var entry:answersMap.entrySet()) {
                    if (answer.equals(entry.getKey())){
                        numbQuestions.add(choice);
                        correct = true;
                        int i = answersMap.get(answer);
                        answersMap.put(answer, i+1);
                    }
                }
                choicesCtx =choicesCtx.choices();
            }
            if (correct) System.out.println( "Answer: " + answersList.get(0) + " (Valid!)");
            else System.out.println("Incorrect answer: " + answersList.get(0) + " (No option available)");
        }
        singleChoiceStatistics(answersMap);
        return "done";
    }

    private void fillWithAnswers(ParserRuleContext ctx, List<String> AnswerList){
        FormGrammarParser.ParameterQuestionContext questionContext =  (FormGrammarParser.ParameterQuestionContext) ctx.getParent().getParent().getParent().getParent();
        String questionId = questionContext.parameterQuestionId().getText();
        String question = questionContext.parameterQ().getText();
        String obligatoriness = questionContext.parameterOblig().getText();
        for (Answer a: answers) {
            try {
                String answer = a.getEvery_Answer().get(questionId);
                //System.out.println(answer);
                if (!answer.equalsIgnoreCase("Not answered")){
                    AnswerList.add(answer);
                    //System.out.println("\n" + questionId + ": " + question);
                }else if (!obligatoriness.equals("Obligatoriness: Optional.")){
                    System.out.println("This question is mandatory and was not answered: " + questionId);
                }
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
    }

    private void singleChoiceStatistics(Map<String, Integer> answersMap){
        int amount = 0;
        for(String choice : answersMap.keySet()){
            amount = amount + answersMap.get(choice);
        }
        for(String choice : answersMap.keySet()){
            System.out.println(choice + " - " + ((answersMap.get(choice)/amount)*100) + "%.");
        }
    }




}

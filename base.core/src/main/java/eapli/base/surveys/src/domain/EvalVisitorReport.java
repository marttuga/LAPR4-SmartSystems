package eapli.base.surveys.src.domain;

import eapli.base.ordersmanagement.answer.domain.Answer;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EvalVisitorReport extends FormGrammarBaseVisitor<String>{

    private List<Answer> answers;

    public EvalVisitorReport(List<Answer> answers){
        this.answers=answers;
    }

    @Override
    public String visitParameterSingleChoice(FormGrammarParser.ParameterSingleChoiceContext ctx){
        List<String> AnswerList = new ArrayList<>();
        fillWithAnswers(ctx, AnswerList);
        Map<String, Integer> singleOpts = new LinkedHashMap<>();
        for (String answer: AnswerList) {
            boolean isCorrect = true;
            while (!(ctx == null)){
                String choice = visit(ctx.getChild(0));
                if (!singleOpts.containsKey(choice)){
                    singleOpts.put(choice,0);
                }
                if (answer.equals(visit(ctx.getChild(0)))){
                    isCorrect = true;
                    int i = singleOpts.get(choice);
                    singleOpts.put(choice, i+1);
                }
            }
            if (isCorrect) System.out.println("Correct" + AnswerList.get(0));
        }
        analyseSingleChoice(singleOpts);
        return "done";
    }

    private void fillWithAnswers(ParserRuleContext ctx, List<String> AnswerList){
        FormGrammarParser.ParameterQuestionContext questionContext = (FormGrammarParser.ParameterQuestionContext) ctx.getParent().getParent();
        String questionId = questionContext.parameterQuestionId().getText();
        String obligatoriness = questionContext.parameterOblig().getText();
        for (Answer a: answers) {
            try {
                String answer = a.getEvery_Answer().get(questionId);
                if (!answer.equalsIgnoreCase("Not answered")){
                    AnswerList.add(answer);
                }else if (!obligatoriness.equals("Optional")){
                    System.out.println("This question is mandatory and was not answered: " + questionId);
                }
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
    }

    private void analyseSingleChoice(Map<String, Integer> singleOpts){
        int total = 0;
        for(String choice : singleOpts.keySet()){
            total = total + singleOpts.get(choice);
        }
        for(String choice : singleOpts.keySet()){

        }
    }


}

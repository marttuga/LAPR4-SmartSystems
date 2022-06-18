package eapli.base.surveys.src.domain;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.strings.util.StringPredicates;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;


public class EvalVisitor extends FormGrammarBaseVisitor<String> {
    List<String> answers;
    public EvalVisitor(List<String> answers) {
        this.answers=answers;
    }
/*
    public String visitStart(FormGrammarParser.StartContext ctx) {
        String all = ctx.allParameter().getText();
        System.out.println(all);

        return all;
    }
*/


   /* public String visitAllParameter(FormGrammarParser.AllParameterContext ctx) {
        String identifier = ctx.parameterId().getText();
        System.out.println(identifier);
        String title = ctx.parameterTitle().getText();
        System.out.println(title);
        String welcomeMessage = ctx.parameterWelcomeMes().getText();
        System.out.println(welcomeMessage);
        Set<String> sectionList = new LinkedHashSet<>();
        for (FormGrammarParser.ParameterSectionContext context : ctx.parameterSection()) {
            visitSection(context);
            sectionList.add(visitSection(context));
        }
        //String endMessage = ctx.parameterFinalMes().getText();
        //System.out.println(endMessage);
        String q = "ID: " + identifier + "\nTITLE: " + title + "\nWELCOME MESSAGE:" + welcomeMessage + "\nSECTION: " + sectionList;
        System.out.println(q);
        return q;
    }

    public String visitSection(FormGrammarParser.ParameterSectionContext ctx) {
        String identifier = ctx.parameterSectionId().toString();
        System.out.println(identifier);
        String title = ctx.parameterSectionTitle().toString();
        System.out.println(title);
        String description = ctx.parameterSectionDesc().toString();
        System.out.println(description);
        String obligatoriness = ctx.parameterOblig().toString();
        System.out.println(obligatoriness);
        Set<String> questionSet = new LinkedHashSet<>();
        for (FormGrammarParser.ParameterQuestionContext context : ctx.parameterQuestion()) {
            visitQuestion(context);
            questionSet.add(visitQuestion(context));
        }
        String section = "ID: " + identifier + "\nTITLE: " + title + "\nDESCRIPTION:" + description + "\nOBLIGATORINESS: " + obligatoriness + "\nQUESTION: " + questionSet;
        System.out.println(section);
        return section;
    }*/


    /*public String visitQuestion(FormGrammarParser.ParameterQuestionContext ctx) {
       FormGrammarParser.ParameterAnswerContext c= ctx.parameterAnswer();
       String answer =c.toString();
                FormGrammarParser.ParameterObligContext ct = ctx.parameterOblig();
        while (!(ct == null)) {
            if (ct.getChild(2).getText().equals("Mandatory.") && answer == null) {
                return "This question is mandatory, please answer";
            }else{
                return "";
            }
        }
        String type = ctx.parameterQuestionType().getText();
            System.out.println(type);

        }*/


    public String visitParameterAnswer(FormGrammarParser.ParameterAnswerContext ctx) {
        System.out.println(answers);
        for (String s:answers ) {
            if (s.equalsIgnoreCase(ctx.getText())){
                System.out.println(s + ctx.getText());
               System.out.println("valido");
            }else {
                System.out.println(ctx.getText()+" "+ s+ " " +"erro");
            }

        }
        ;return ctx.getText();
    }
/*
    public String visitParameterOblig(FormGrammarParser.ParameterObligContext ctx) {
        String a;
        List<String> answers = new ArrayList<>();
        if (this.visitParameterOblig(ctx).equals("Optional.")) {
            System.out.println("press enter to skip question");
        }
        System.out.println();
        do {
            a = Utils.readLine("Answer: ");
            if (StringPredicates.isNullOrEmpty(a) && this.visitParameterOblig(ctx).equals("Mandatory.")) {
                System.out.println("this question is mandatory");
                if (this.visitParameterOblig(ctx).equals("Optional.")) {
                    answers.add(answers.size() - 1, "Not Answered");
                }
            } else {
                answers.add(a);
            }

        } while (StringPredicates.isNullOrEmpty(a) && this.visitParameterOblig(ctx).equals("Mandatory."));

        return ctx.getText();
    }

    public String visitParameterFreeText(FormGrammarParser.ParameterFreeTextContext ctx) {
        return visitChildren(ctx);
    }

    public String visitParameterDiffType(FormGrammarParser.ParameterDiffTypeContext ctx) {

        return
        ctx.getText();
    }

    private void seeAnswer(ParserRuleContext ctx,String a) {
        FormGrammarParser.ParameterAnswerContext answerContext = (FormGrammarParser.ParameterAnswerContext) ctx.getParent();
        for (Answer a : answer) {
            try {
                String answer = a.getEvery_Answer().get(questionId);
                if (!answer.equalsIgnoreCase("Not answered")) {
                    a.add(answer);
                } else if (!obligatoriness.equals("Obligatoriness: Optional.")) {
                    System.out.println("This question is mandatory and was not answered: " + questionId);
                }

            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }*/
}
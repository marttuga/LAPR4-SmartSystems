package eapli.base.surveys.src.domain;

import eapli.base.utilitarianClasses.Utils;
import eapli.framework.strings.util.StringPredicates;

import java.util.*;


public class EvalVisitor extends FormGrammarBaseVisitor<String>{

    public String visitStart(FormGrammarParser.StartContext ctx) {
        String all = ctx.allParameter().getText();
        System.out.println(all);

        return all;
    }


    public String visitAllParameter(FormGrammarParser.AllParameterContext ctx) {
        String identifier = ctx.parameterId().getText();
        System.out.println(identifier);
        String title = ctx.parameterTitle().getText();
        System.out.println(title);
        String welcomeMessage = ctx.parameterWelcomeMes().getText();
        System.out.println(welcomeMessage);
        Set<String> sectionList = new LinkedHashSet<>();
        for (FormGrammarParser.ParameterSectionContext context: ctx.parameterSection()) {
            visitSection(context);
            sectionList.add(visitSection(context));
        }
        //String endMessage = ctx.parameterFinalMes().getText();
        //System.out.println(endMessage);
        String q= "ID: " +identifier + "\nTITLE: "+title+ "\nWELCOME MESSAGE:" +welcomeMessage+ "\nSECTION: " +sectionList;
        System.out.println(q);
        return q;
    }

    public String  visitSection(FormGrammarParser.ParameterSectionContext ctx) {
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
        String section= "ID: " +identifier + "\nTITLE: "+title+ "\nDESCRIPTION:" +description+ "\nOBLIGATORINESS: " +obligatoriness+ "\nQUESTION: "+ questionSet;
        System.out.println(section);
        return section;
    }


    public String visitQuestion(FormGrammarParser.ParameterQuestionContext ctx) {
        String identifier = ctx.parameterQuestionId().getText();
        System.out.println(identifier);
        String text = ctx.parameterQ().getText();
        System.out.println(text);
        String obli = ctx.parameterOblig().getText();
        System.out.println(obli);
        String type = ctx.parameterQuestionType().getText();
        System.out.println(type);

        String question= "ID: " +identifier + "\nTEXT: "+text+ "\nOBLIGATORINESS: " +obli+ "\nQUESTION TYPE: "+ type;
        System.out.println(question);
        return question;    }









    public String visitParameterOblig(FormGrammarParser.ParameterObligContext ctx) {
String a;
List<String> answers = new ArrayList<>();

if (this.visitParameterOblig(ctx).equals("Optional.")) {
    System.out.println("press enter to skip question");
}
        System.out.println();
do {
   a= Utils.readLine("Answer: ");
   if (StringPredicates.isNullOrEmpty(a) && this.visitParameterOblig(ctx).equals("Mandatory.")){
       System.out.println("this question is mandatory");
       if (this.visitParameterOblig(ctx).equals("Optional.")){
          answers.add(answers.size()-1,"Not Answered");
       }
   }else{
       answers.add(a);
   }

} while (StringPredicates.isNullOrEmpty(a) && this.visitParameterOblig(ctx).equals("Mandatory."));

   return ctx.getText();
    }


    public String visitParameterDiffType(FormGrammarParser.ParameterDiffTypeContext ctx) {

        StringBuilder m= new StringBuilder();
        int numAnswers;

        return ctx.getText();
    }
}

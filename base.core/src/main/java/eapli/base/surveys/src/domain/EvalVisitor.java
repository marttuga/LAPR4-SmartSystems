package eapli.base.surveys.src.domain;

import eapli.base.utilitarianClasses.Utils;
import eapli.framework.strings.util.StringPredicates;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.Console;
import java.util.*;


public class EvalVisitor extends FormGrammarBaseVisitor<String>{

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
        if (this.visitParameterDiffType(ctx).equalsIgnoreCase("Single-Choice.")){

        }


        return ctx.getText();
    }
}

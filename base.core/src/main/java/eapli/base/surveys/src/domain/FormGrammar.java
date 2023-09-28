package eapli.base.surveys.src.domain;

import java.io.*;
import java.util.*;

import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.domain.AnswerId;
import eapli.base.ordersmanagement.customer.domain.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class FormGrammar {
    public static void main(String[] args) throws IOException {
        Set<CustomerPostalAddress> postalAddress = new HashSet<>();
        CustomerPostalAddress customerPostalAddress = new CustomerPostalAddress("Cornelia", 22, "San Francisco", "USA", 90213);
        postalAddress.add(customerPostalAddress);
        Customer customer = new Customer(CustomerId.valueOf("87654321"), CustomerFirstName.valueOf("Gabi"), CustomerLastName.valueOf("Hall"), CustomerEmailAdress.valueOf("gabi@gmail.com"), CustomerPhoneNumber.valueOf("910477203"), CustomerVATIdentifier.valueOf("9263748"), CustomerBirthDay.valueOf(new Date(Calendar.YEAR + 100, 3, 2)), CustomerGender.valueOf("FEMALE"), postalAddress);
        Customer customer1 = new Customer(CustomerId.valueOf("12345678"), CustomerFirstName.valueOf("Joana"), CustomerLastName.valueOf("Hall"), CustomerEmailAdress.valueOf("jo@gmail.com"), CustomerPhoneNumber.valueOf("910477203"), CustomerVATIdentifier.valueOf("9263748"), CustomerBirthDay.valueOf(new Date(Calendar.YEAR + 100, 3, 2)), CustomerGender.valueOf("FEMALE"), postalAddress);
        List<Customer> customers = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
        Map<String, String> answers  = new HashMap<>();
        answers.put("Q1.","87654321");
        answers.put("Q2.","Not Answered");
        answers.put("Q3.","3");
        answers.put("Q4.","gabi@gmail.com");
        answers.put("Q5.","4");
        answers.put("Q6.","yes");
        answers.put("Q7.","yes");
        answers.put("Q8.","no");

        Answer answer = new Answer( new AnswerId("123"),customer, answers);
        Map<String, String> answersJo  = new HashMap<>();
        answersJo.put("Q1.","12345678");
        answersJo.put("Q2.","Not Answered");
        answersJo.put("Q3.","2");
        answersJo.put("Q4.","jo@gmail.com");
        answersJo.put("Q5.","2");
        answersJo.put("Q6.","yes");
        answersJo.put("Q7.","yes");
        answersJo.put("Q8.","no");

        Answer answerj = new Answer( new AnswerId("456"),customer1, answersJo);
        answerList.add(answer);
        answerList.add(answerj);
/*        List<String> a = new ArrayList<>();
        a.add("vrttgt");
        a.add("Not Answered");
        a.add("Not Answered");
        a.add("Not Answered");
        a.add("vbtgb brtbtr");
        a.add("4");
        a.add("gtrg");
        a.add("gtrg vv");
        String str = String.join("\n" ,a);
        System.out.println(str);*/

  /*      File file = new File("base.core//src//main//java//eapli//base//surveys//Answers.txt");
        FileInputStream f1 = null;
        try {
            f1 = new FileInputStream(file);
            byte[] surveyFile = new byte[(int) file.length()];
            f1.read(surveyFile);
            FormGrammarLexer lexer = new FormGrammarLexer(new ANTLRInputStream(f1));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FormGrammarParser parser = new FormGrammarParser(tokens);
            ParseTree tree = parser.start(); // parse
            EvalVisitor eval = new EvalVisitor();
            eval.visit(tree);

            f1.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
       // FileInputStream fis = new FileInputStream(new File("C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys\\Answers.txt"));
        //CharStream charStream = CharStreams.fromString(String.valueOf(f1));
        //System.out.println( );

        FileInputStream fis = new FileInputStream(new File("C:\\Users\\marti\\Documents\\2ANO2SEMESTRE\\LAPR4\\LEI21_22_S4_2DK_01\\base.core\\src\\main\\java\\eapli\\base\\surveys\\Answers.txt"));
        FormGrammarLexer lexer = new FormGrammarLexer(new ANTLRInputStream(fis));
        org.antlr.v4.runtime.CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormGrammarParser parser = new FormGrammarParser(tokens);
        ParseTree tree = parser.start(); // parse
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}





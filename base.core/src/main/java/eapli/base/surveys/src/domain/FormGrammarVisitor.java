// Generated from /Users/jtiagoaraujo/OneDrive - Instituto Superior de Engenharia do Porto/ISEP/2º ANO/2º SEMESTRE/LEI21_22_S4_2DK_01/base.core/src/main/java/eapli/base/surveys/FormGrammar.g4 by ANTLR 4.10.1
package eapli.base.surveys.src.domain;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#lprog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLprog(FormGrammarParser.LprogContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(FormGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#allParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllParameter(FormGrammarParser.AllParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSections(FormGrammarParser.ParameterSectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSection(FormGrammarParser.ParameterSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterQuestionPrinc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterQuestionPrinc(FormGrammarParser.ParameterQuestionPrincContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterQuestion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterQuestion(FormGrammarParser.ParameterQuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterId(FormGrammarParser.ParameterIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterTitle(FormGrammarParser.ParameterTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterQ(FormGrammarParser.ParameterQContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterText(FormGrammarParser.ParameterTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSentence(FormGrammarParser.ParameterSentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterWelcomeMes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterWelcomeMes(FormGrammarParser.ParameterWelcomeMesContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSectionId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSectionId(FormGrammarParser.ParameterSectionIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSectionTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSectionTitle(FormGrammarParser.ParameterSectionTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSectionDesc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSectionDesc(FormGrammarParser.ParameterSectionDescContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterOblig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterOblig(FormGrammarParser.ParameterObligContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterStrings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterStrings(FormGrammarParser.ParameterStringsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterQuestionId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterQuestionId(FormGrammarParser.ParameterQuestionIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterQuestionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterQuestionType(FormGrammarParser.ParameterQuestionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterType(FormGrammarParser.ParameterTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterDiffType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDiffType(FormGrammarParser.ParameterDiffTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterSingleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSingleChoice(FormGrammarParser.ParameterSingleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterFreeText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterFreeText(FormGrammarParser.ParameterFreeTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterMultipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterMultipleChoice(FormGrammarParser.ParameterMultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormGrammarParser#parameterFinalMes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterFinalMes(FormGrammarParser.ParameterFinalMesContext ctx);
}
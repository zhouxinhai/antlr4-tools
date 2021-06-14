// Generated from D:/code/java/learn/antlr4-tools/src/main/resources/antlr\GroovyExp.g4 by ANTLR 4.9.1
package com.newland.bd.workflow.expression.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GroovyExpParser}.
 */
public interface GroovyExpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GroovyExpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GroovyExpParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GroovyExpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GroovyExpParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueExpressionDefault}
	 * labeled alternative in {@link GroovyExpParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterValueExpressionDefault(GroovyExpParser.ValueExpressionDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueExpressionDefault}
	 * labeled alternative in {@link GroovyExpParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitValueExpressionDefault(GroovyExpParser.ValueExpressionDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticBinary}
	 * labeled alternative in {@link GroovyExpParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticBinary(GroovyExpParser.ArithmeticBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticBinary}
	 * labeled alternative in {@link GroovyExpParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticBinary(GroovyExpParser.ArithmeticBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticUnary}
	 * labeled alternative in {@link GroovyExpParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticUnary(GroovyExpParser.ArithmeticUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticUnary}
	 * labeled alternative in {@link GroovyExpParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticUnary(GroovyExpParser.ArithmeticUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryLiteral(GroovyExpParser.BinaryLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryLiteral(GroovyExpParser.BinaryLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dereference}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterDereference(GroovyExpParser.DereferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dereference}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitDereference(GroovyExpParser.DereferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambda}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambda(GroovyExpParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambda}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambda(GroovyExpParser.LambdaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnReference}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterColumnReference(GroovyExpParser.ColumnReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnReference}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitColumnReference(GroovyExpParser.ColumnReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(GroovyExpParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(GroovyExpParser.NullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscript}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(GroovyExpParser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscript}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(GroovyExpParser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(GroovyExpParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(GroovyExpParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(GroovyExpParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(GroovyExpParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryExpression}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryExpression(GroovyExpParser.SubqueryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryExpression}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryExpression(GroovyExpParser.SubqueryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(GroovyExpParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(GroovyExpParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(GroovyExpParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link GroovyExpParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(GroovyExpParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GroovyExpParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(GroovyExpParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GroovyExpParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(GroovyExpParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicStringLiteral}
	 * labeled alternative in {@link GroovyExpParser#string}.
	 * @param ctx the parse tree
	 */
	void enterBasicStringLiteral(GroovyExpParser.BasicStringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicStringLiteral}
	 * labeled alternative in {@link GroovyExpParser#string}.
	 * @param ctx the parse tree
	 */
	void exitBasicStringLiteral(GroovyExpParser.BasicStringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unicodeStringLiteral}
	 * labeled alternative in {@link GroovyExpParser#string}.
	 * @param ctx the parse tree
	 */
	void enterUnicodeStringLiteral(GroovyExpParser.UnicodeStringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unicodeStringLiteral}
	 * labeled alternative in {@link GroovyExpParser#string}.
	 * @param ctx the parse tree
	 */
	void exitUnicodeStringLiteral(GroovyExpParser.UnicodeStringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GroovyExpParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(GroovyExpParser.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GroovyExpParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(GroovyExpParser.BooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GroovyExpParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(GroovyExpParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GroovyExpParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(GroovyExpParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code backQuotedIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterBackQuotedIdentifier(GroovyExpParser.BackQuotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code backQuotedIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitBackQuotedIdentifier(GroovyExpParser.BackQuotedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mapidentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterMapidentifier(GroovyExpParser.MapidentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mapidentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitMapidentifier(GroovyExpParser.MapidentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code quotedIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterQuotedIdentifier(GroovyExpParser.QuotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code quotedIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitQuotedIdentifier(GroovyExpParser.QuotedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code digitIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterDigitIdentifier(GroovyExpParser.DigitIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code digitIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitDigitIdentifier(GroovyExpParser.DigitIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unquotedIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterUnquotedIdentifier(GroovyExpParser.UnquotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unquotedIdentifier}
	 * labeled alternative in {@link GroovyExpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitUnquotedIdentifier(GroovyExpParser.UnquotedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link GroovyExpParser#number}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(GroovyExpParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link GroovyExpParser#number}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(GroovyExpParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link GroovyExpParser#number}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(GroovyExpParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link GroovyExpParser#number}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(GroovyExpParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link GroovyExpParser#number}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(GroovyExpParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link GroovyExpParser#number}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(GroovyExpParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link GroovyExpParser#reserved}.
	 * @param ctx the parse tree
	 */
	void enterReserved(GroovyExpParser.ReservedContext ctx);
	/**
	 * Exit a parse tree produced by {@link GroovyExpParser#reserved}.
	 * @param ctx the parse tree
	 */
	void exitReserved(GroovyExpParser.ReservedContext ctx);
	/**
	 * Enter a parse tree produced by {@link GroovyExpParser#nonReserved}.
	 * @param ctx the parse tree
	 */
	void enterNonReserved(GroovyExpParser.NonReservedContext ctx);
	/**
	 * Exit a parse tree produced by {@link GroovyExpParser#nonReserved}.
	 * @param ctx the parse tree
	 */
	void exitNonReserved(GroovyExpParser.NonReservedContext ctx);
}
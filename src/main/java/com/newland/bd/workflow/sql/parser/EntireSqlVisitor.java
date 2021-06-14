// Generated from D:/code/java/learn/antlr4-tools/src/main/resources/antlr\EntireSql.g4 by ANTLR 4.9.1
package com.newland.bd.workflow.sql.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EntireSqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EntireSqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#multiStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiStatement(EntireSqlParser.MultiStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#singleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleStatement(EntireSqlParser.SingleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#allStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllStatement(EntireSqlParser.AllStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2AlterModify}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2AlterModify(EntireSqlParser.Db2AlterModifyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2TruncateTable}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2TruncateTable(EntireSqlParser.Db2TruncateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2CreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2CreateTableByLike(EntireSqlParser.Db2CreateTableByLikeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2RefreshTable}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2RefreshTable(EntireSqlParser.Db2RefreshTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2CreateView}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2CreateView(EntireSqlParser.Db2CreateViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2CreateSequence}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2CreateSequence(EntireSqlParser.Db2CreateSequenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseCreateTableAs}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseCreateTableAs(EntireSqlParser.GBaseCreateTableAsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseCreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseCreateTableByLike(EntireSqlParser.GBaseCreateTableByLikeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseDropNocopies}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseDropNocopies(EntireSqlParser.GBaseDropNocopiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseShrinkSpace}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseShrinkSpace(EntireSqlParser.GBaseShrinkSpaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterChange}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterChange(EntireSqlParser.GBaseAlterChangeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterModify}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterModify(EntireSqlParser.GBaseAlterModifyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseRenameTable}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseRenameTable(EntireSqlParser.GBaseRenameTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterView}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterView(EntireSqlParser.GBaseAlterViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseCreateIndex(EntireSqlParser.GBaseCreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterIndex(EntireSqlParser.GBaseAlterIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseDropIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseDropIndex(EntireSqlParser.GBaseDropIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterAutoextend}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterAutoextend(EntireSqlParser.GBaseAlterAutoextendContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterColCompress}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterColCompress(EntireSqlParser.GBaseAlterColCompressContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseAlterTableCompress}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseAlterTableCompress(EntireSqlParser.GBaseAlterTableCompressContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseMerge}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseMerge(EntireSqlParser.GBaseMergeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gBaseSelectIntoOutfile}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGBaseSelectIntoOutfile(EntireSqlParser.GBaseSelectIntoOutfileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gbaseLoadData}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGbaseLoadData(EntireSqlParser.GbaseLoadDataContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gbaseLoadDataOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGbaseLoadDataOptions(EntireSqlParser.GbaseLoadDataOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#loadDataFiledsOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadDataFiledsOptions(EntireSqlParser.LoadDataFiledsOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#outfileOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutfileOption(EntireSqlParser.OutfileOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#fieldsOutputOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldsOutputOptions(EntireSqlParser.FieldsOutputOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#fieldsOutputOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldsOutputOption(EntireSqlParser.FieldsOutputOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#lineOutputOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineOutputOptions(EntireSqlParser.LineOutputOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#lineOutputOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineOutputOption(EntireSqlParser.LineOutputOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#otherOutputOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOtherOutputOptions(EntireSqlParser.OtherOutputOptionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveMultiInsert}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveMultiInsert(EntireSqlParser.HiveMultiInsertContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveWriteDataToFile}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveWriteDataToFile(EntireSqlParser.HiveWriteDataToFileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveSetProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveSetProperties(EntireSqlParser.HiveSetPropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreateDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreateDatabase(EntireSqlParser.HiveCreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveDropDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveDropDatabase(EntireSqlParser.HiveDropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterDatabase(EntireSqlParser.HiveAlterDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveDropTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveDropTable(EntireSqlParser.HiveDropTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveTruncateTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveTruncateTable(EntireSqlParser.HiveTruncateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreateTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreateTable(EntireSqlParser.HiveCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreateTableByLike(EntireSqlParser.HiveCreateTableByLikeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterTableProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterTableProperties(EntireSqlParser.HiveAlterTablePropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterTableSerDe}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterTableSerDe(EntireSqlParser.HiveAlterTableSerDeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterTableStorageProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterTableStorageProperties(EntireSqlParser.HiveAlterTableStoragePropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterTableSkewed}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterTableSkewed(EntireSqlParser.HiveAlterTableSkewedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterTableConstraints}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterTableConstraints(EntireSqlParser.HiveAlterTableConstraintsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterPartition}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterPartition(EntireSqlParser.HiveAlterPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code archivePartition}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArchivePartition(EntireSqlParser.ArchivePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterColumn}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterColumn(EntireSqlParser.HiveAlterColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreateView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreateView(EntireSqlParser.HiveCreateViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveDropView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveDropView(EntireSqlParser.HiveDropViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterView(EntireSqlParser.HiveAlterViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterViewAsSelect}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterViewAsSelect(EntireSqlParser.HiveAlterViewAsSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreateIndex(EntireSqlParser.HiveCreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveDropIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveDropIndex(EntireSqlParser.HiveDropIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveAlterIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveAlterIndex(EntireSqlParser.HiveAlterIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreateTemporaryFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreateTemporaryFunctions(EntireSqlParser.HiveCreateTemporaryFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveCreatePermanentFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveCreatePermanentFunctions(EntireSqlParser.HiveCreatePermanentFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveDropFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveDropFunctions(EntireSqlParser.HiveDropFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveReloadFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveReloadFunctions(EntireSqlParser.HiveReloadFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveLoadFileIntoTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveLoadFileIntoTable(EntireSqlParser.HiveLoadFileIntoTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpVacuum}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpVacuum(EntireSqlParser.GpVacuumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpTruncate}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpTruncate(EntireSqlParser.GpTruncateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpCreateTable}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpCreateTable(EntireSqlParser.GpCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpCreateIndex(EntireSqlParser.GpCreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpRecreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpRecreateIndex(EntireSqlParser.GpRecreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpAlterPartition}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpAlterPartition(EntireSqlParser.GpAlterPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpAlterColumn}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpAlterColumn(EntireSqlParser.GpAlterColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpAlterConstraint}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpAlterConstraint(EntireSqlParser.GpAlterConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gpPartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpPartitionItems(EntireSqlParser.GpPartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gpPartitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpPartitionItem(EntireSqlParser.GpPartitionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gpSubpartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpSubpartitionItems(EntireSqlParser.GpSubpartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gpSubPartitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpSubPartitionItem(EntireSqlParser.GpSubPartitionItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code writeDataSingle}
	 * labeled alternative in {@link EntireSqlParser#hiveWriteDataToFileStm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteDataSingle(EntireSqlParser.WriteDataSingleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code writeDataMulti}
	 * labeled alternative in {@link EntireSqlParser#hiveWriteDataToFileStm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteDataMulti(EntireSqlParser.WriteDataMultiContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#constraintSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintSpecification(EntireSqlParser.ConstraintSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#fileFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileFormat(EntireSqlParser.FileFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#rowFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowFormat(EntireSqlParser.RowFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#stringExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExp(EntireSqlParser.StringExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#stringAndNumberExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAndNumberExp(EntireSqlParser.StringAndNumberExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#multiStrEvaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiStrEvaluation(EntireSqlParser.MultiStrEvaluationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#multiKeyEvaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiKeyEvaluation(EntireSqlParser.MultiKeyEvaluationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnDefinitionNoType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinitionNoType(EntireSqlParser.ColumnDefinitionNoTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#nullExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullExp(EntireSqlParser.NullExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#standaloneExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStandaloneExpression(EntireSqlParser.StandaloneExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#standalonePathSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStandalonePathSpecification(EntireSqlParser.StandalonePathSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#queryStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryStatement(EntireSqlParser.QueryStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#statementNoEof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementNoEof(EntireSqlParser.StatementNoEofContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementDefault}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDefault(EntireSqlParser.StatementDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setOracleComment}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetOracleComment(EntireSqlParser.SetOracleCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code update}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(EntireSqlParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code use}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUse(EntireSqlParser.UseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code createSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateSchema(EntireSqlParser.CreateSchemaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropSchema(EntireSqlParser.DropSchemaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code renameSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameSchema(EntireSqlParser.RenameSchemaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code createTableAsSelect}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code createTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(EntireSqlParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code truncateTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruncateTable(EntireSqlParser.TruncateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(EntireSqlParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code createIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(EntireSqlParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddIndex(EntireSqlParser.AddIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropIndex(EntireSqlParser.DropIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterDropIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterDropIndex(EntireSqlParser.AlterDropIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code insert}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert(EntireSqlParser.InsertContext ctx);
	/**
	 * Visit a parse tree produced by the {@code delete}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(EntireSqlParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code renameTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTable(EntireSqlParser.RenameTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modifyTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifyTable(EntireSqlParser.ModifyTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code changeTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeTable(EntireSqlParser.ChangeTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterColumn(EntireSqlParser.AlterColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code renameColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameColumn(EntireSqlParser.RenameColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropColumn(EntireSqlParser.DropColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddColumn(EntireSqlParser.AddColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code createView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateView(EntireSqlParser.CreateViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropView(EntireSqlParser.DropViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(EntireSqlParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code grant}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrant(EntireSqlParser.GrantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code revoke}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRevoke(EntireSqlParser.RevokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGrants(EntireSqlParser.ShowGrantsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code explain}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplain(EntireSqlParser.ExplainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateTable(EntireSqlParser.ShowCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateView(EntireSqlParser.ShowCreateViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showTables}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowTables(EntireSqlParser.ShowTablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showSchemas}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSchemas(EntireSqlParser.ShowSchemasContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowIndex(EntireSqlParser.ShowIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCatalogs}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCatalogs(EntireSqlParser.ShowCatalogsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowColumns(EntireSqlParser.ShowColumnsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showStats}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStats(EntireSqlParser.ShowStatsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showStatsForQuery}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatsForQuery(EntireSqlParser.ShowStatsForQueryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showFunctions}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowFunctions(EntireSqlParser.ShowFunctionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSession(EntireSqlParser.ShowSessionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showPartition}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowPartition(EntireSqlParser.ShowPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetSession(EntireSqlParser.SetSessionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code resetSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetSession(EntireSqlParser.ResetSessionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code startTransaction}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartTransaction(EntireSqlParser.StartTransactionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commit}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommit(EntireSqlParser.CommitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rollback}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollback(EntireSqlParser.RollbackContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prepare}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrepare(EntireSqlParser.PrepareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code deallocate}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeallocate(EntireSqlParser.DeallocateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code execute}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecute(EntireSqlParser.ExecuteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code describeInput}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeInput(EntireSqlParser.DescribeInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code describeOutput}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeOutput(EntireSqlParser.DescribeOutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setPath}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetPath(EntireSqlParser.SetPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setProperty}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetProperty(EntireSqlParser.SetPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repairTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepairTable(EntireSqlParser.RepairTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code insertInto}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertInto(EntireSqlParser.InsertIntoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hiveInsertIntoSelect}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHiveInsertIntoSelect(EntireSqlParser.HiveInsertIntoSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code insertIntoSelect}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(EntireSqlParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(EntireSqlParser.WithContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableComment}
	 * labeled alternative in {@link EntireSqlParser#oracleComment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableComment(EntireSqlParser.TableCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnComment}
	 * labeled alternative in {@link EntireSqlParser#oracleComment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnComment(EntireSqlParser.ColumnCommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oraclePartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOraclePartitionItems(EntireSqlParser.OraclePartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#db2PartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2PartitionItems(EntireSqlParser.Db2PartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#db2CreateTableAsSelectItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2CreateTableAsSelectItem(EntireSqlParser.Db2CreateTableAsSelectItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleTemporayItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleTemporayItem(EntireSqlParser.OracleTemporayItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#mysqlTemporayItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlTemporayItem(EntireSqlParser.MysqlTemporayItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gbaseTemporayItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGbaseTemporayItem(EntireSqlParser.GbaseTemporayItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleRangePartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleRangePartitionItems(EntireSqlParser.OracleRangePartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleRangePartitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleRangePartitionItem(EntireSqlParser.OracleRangePartitionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleListPartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleListPartitionItems(EntireSqlParser.OracleListPartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleListPartitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleListPartitionItem(EntireSqlParser.OracleListPartitionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleHashPartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleHashPartitionItems(EntireSqlParser.OracleHashPartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleHashPartitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleHashPartitionItem(EntireSqlParser.OracleHashPartitionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleCompositePartitionItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleCompositePartitionItems(EntireSqlParser.OracleCompositePartitionItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleCompositePartitionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleCompositePartitionItem(EntireSqlParser.OracleCompositePartitionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#constraintInfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintInfo(EntireSqlParser.ConstraintInfoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#mysqlColumnCreateIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlColumnCreateIndex(EntireSqlParser.MysqlColumnCreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#gbaseColumnCreateIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGbaseColumnCreateIndex(EntireSqlParser.GbaseColumnCreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#tableElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableElement(EntireSqlParser.TableElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#tableElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableElements(EntireSqlParser.TableElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinitions(EntireSqlParser.ColumnDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(EntireSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#autoIncrement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutoIncrement(EntireSqlParser.AutoIncrementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#compressType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompressType(EntireSqlParser.CompressTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnNullable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnNullable(EntireSqlParser.ColumnNullableContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnConstraint(EntireSqlParser.ColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#likeClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeClause(EntireSqlParser.LikeClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(EntireSqlParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(EntireSqlParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#queryNoWith}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryNoWith(EntireSqlParser.QueryNoWithContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#limitStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitStat(EntireSqlParser.LimitStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code queryTermDefault}
	 * labeled alternative in {@link EntireSqlParser#queryTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryTermDefault(EntireSqlParser.QueryTermDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setOperation}
	 * labeled alternative in {@link EntireSqlParser#queryTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetOperation(EntireSqlParser.SetOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code queryPrimaryDefault}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryPrimaryDefault(EntireSqlParser.QueryPrimaryDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code table}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(EntireSqlParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subquery}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubquery(EntireSqlParser.SubqueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#insertValues}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertValues(EntireSqlParser.InsertValuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#sortItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortItem(EntireSqlParser.SortItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#querySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#groupBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupBy(EntireSqlParser.GroupByContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleGroupingSet}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleGroupingSet(EntireSqlParser.SingleGroupingSetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rollup}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollup(EntireSqlParser.RollupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cube}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCube(EntireSqlParser.CubeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multipleGroupingSets}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleGroupingSets(EntireSqlParser.MultipleGroupingSetsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#groupingSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupingSet(EntireSqlParser.GroupingSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#namedQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedQuery(EntireSqlParser.NamedQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#setQuantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetQuantifier(EntireSqlParser.SetQuantifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectSingle}
	 * labeled alternative in {@link EntireSqlParser#selectItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectSingle(EntireSqlParser.SelectSingleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectAll}
	 * labeled alternative in {@link EntireSqlParser#selectItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectAll(EntireSqlParser.SelectAllContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#aliasName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasName(EntireSqlParser.AliasNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationDefault}
	 * labeled alternative in {@link EntireSqlParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationDefault(EntireSqlParser.RelationDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code joinRelation}
	 * labeled alternative in {@link EntireSqlParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinRelation(EntireSqlParser.JoinRelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#joinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinType(EntireSqlParser.JoinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#joinCriteria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinCriteria(EntireSqlParser.JoinCriteriaContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#sampledRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSampledRelation(EntireSqlParser.SampledRelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#sampleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSampleType(EntireSqlParser.SampleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#aliasedRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasedRelation(EntireSqlParser.AliasedRelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnAliases}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAliases(EntireSqlParser.ColumnAliasesContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#lengthDesc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthDesc(EntireSqlParser.LengthDescContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#columnAliasesByOrder}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnAliasesByOrder(EntireSqlParser.ColumnAliasesByOrderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableName}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(EntireSqlParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryRelation}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryRelation(EntireSqlParser.SubqueryRelationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unnest}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnnest(EntireSqlParser.UnnestContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lateral}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLateral(EntireSqlParser.LateralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedRelation}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedRelation(EntireSqlParser.ParenthesizedRelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(EntireSqlParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#partitionSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionSpec(EntireSqlParser.PartitionSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#partitionInfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionInfo(EntireSqlParser.PartitionInfoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalNot}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalNot(EntireSqlParser.LogicalNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicated}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicated(EntireSqlParser.PredicatedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalBinary}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalBinary(EntireSqlParser.LogicalBinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(EntireSqlParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code quantifiedComparison}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifiedComparison(EntireSqlParser.QuantifiedComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code between}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween(EntireSqlParser.BetweenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inList}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInList(EntireSqlParser.InListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inSubquery}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInSubquery(EntireSqlParser.InSubqueryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code like}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike(EntireSqlParser.LikeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code regexp}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexp(EntireSqlParser.RegexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rlike}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRlike(EntireSqlParser.RlikeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullPredicate}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullPredicate(EntireSqlParser.NullPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code distinctFrom}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistinctFrom(EntireSqlParser.DistinctFromContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticShift}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticShift(EntireSqlParser.ArithmeticShiftContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueExpressionDefault}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueExpressionDefault(EntireSqlParser.ValueExpressionDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatenation(EntireSqlParser.ConcatenationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpTypeConvert}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpTypeConvert(EntireSqlParser.GpTypeConvertContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticBinary}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticBinary(EntireSqlParser.ArithmeticBinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticUnary}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticUnary(EntireSqlParser.ArithmeticUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atTimeZone}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtTimeZone(EntireSqlParser.AtTimeZoneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mySqlTypeConvert}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMySqlTypeConvert(EntireSqlParser.MySqlTypeConvertContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#shiftOpt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftOpt(EntireSqlParser.ShiftOptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dereference}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDereference(EntireSqlParser.DereferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isBooleanValue}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsBooleanValue(EntireSqlParser.IsBooleanValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specialDateTimeFunction}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialDateTimeFunction(EntireSqlParser.SpecialDateTimeFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code substring}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstring(EntireSqlParser.SubstringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cast}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCast(EntireSqlParser.CastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambda}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(EntireSqlParser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(EntireSqlParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallListAgg}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallListAgg(EntireSqlParser.FunctionCallListAggContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(EntireSqlParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code normalize}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalize(EntireSqlParser.NormalizeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intervalLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalLiteral(EntireSqlParser.IntervalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(EntireSqlParser.NumericLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(EntireSqlParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleCase}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleCase(EntireSqlParser.SimpleCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnReference}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnReference(EntireSqlParser.ColumnReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLiteral(EntireSqlParser.NullLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowConstructor}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowConstructor(EntireSqlParser.RowConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpFunctionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpFunctionCall(EntireSqlParser.GpFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscript}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscript(EntireSqlParser.SubscriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentPath}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentPath(EntireSqlParser.CurrentPathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryExpression}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryExpression(EntireSqlParser.SubqueryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryLiteral(EntireSqlParser.BinaryLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentUser}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentUser(EntireSqlParser.CurrentUserContext ctx);
	/**
	 * Visit a parse tree produced by the {@code extract}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtract(EntireSqlParser.ExtractContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(EntireSqlParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayConstructor}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayConstructor(EntireSqlParser.ArrayConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(EntireSqlParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mysqlFunctionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlFunctionCall(EntireSqlParser.MysqlFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exists}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExists(EntireSqlParser.ExistsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code db2CurrentTimeFunc}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDb2CurrentTimeFunc(EntireSqlParser.Db2CurrentTimeFuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code position}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPosition(EntireSqlParser.PositionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code searchedCase}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearchedCase(EntireSqlParser.SearchedCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code groupingOperation}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupingOperation(EntireSqlParser.GroupingOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpOverLayFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpOverLayFunction(EntireSqlParser.GpOverLayFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpTrimFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpTrimFunction(EntireSqlParser.GpTrimFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpTocharFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpTocharFunction(EntireSqlParser.GpTocharFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpAgeFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpAgeFunction(EntireSqlParser.GpAgeFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpdatePartFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpdatePartFunction(EntireSqlParser.GpdatePartFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpExtractFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpExtractFunction(EntireSqlParser.GpExtractFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpIsfiniteFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpIsfiniteFunction(EntireSqlParser.GpIsfiniteFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpJustifyDaysFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpJustifyDaysFunction(EntireSqlParser.GpJustifyDaysFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpJustifyHourFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpJustifyHourFunction(EntireSqlParser.GpJustifyHourFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gpJustifyIntervalFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGpJustifyIntervalFunction(EntireSqlParser.GpJustifyIntervalFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mysqlTrimFunction}
	 * labeled alternative in {@link EntireSqlParser#mysqlFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlTrimFunction(EntireSqlParser.MysqlTrimFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mysqlDateFunction}
	 * labeled alternative in {@link EntireSqlParser#mysqlFunctionCalls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlDateFunction(EntireSqlParser.MysqlDateFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#oracleJoinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOracleJoinType(EntireSqlParser.OracleJoinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#typeConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeConstructor(EntireSqlParser.TypeConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicStringLiteral}
	 * labeled alternative in {@link EntireSqlParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicStringLiteral(EntireSqlParser.BasicStringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unicodeStringLiteral}
	 * labeled alternative in {@link EntireSqlParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnicodeStringLiteral(EntireSqlParser.UnicodeStringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timeZoneInterval}
	 * labeled alternative in {@link EntireSqlParser#timeZoneSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeZoneInterval(EntireSqlParser.TimeZoneIntervalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timeZoneString}
	 * labeled alternative in {@link EntireSqlParser#timeZoneSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimeZoneString(EntireSqlParser.TimeZoneStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(EntireSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#comparisonQuantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonQuantifier(EntireSqlParser.ComparisonQuantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(EntireSqlParser.BooleanValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(EntireSqlParser.IntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#intervalField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalField(EntireSqlParser.IntervalFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#normalForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalForm(EntireSqlParser.NormalFormContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(EntireSqlParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#typeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameter(EntireSqlParser.TypeParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(EntireSqlParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#whenClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenClause(EntireSqlParser.WhenClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(EntireSqlParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#over}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOver(EntireSqlParser.OverContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#windowFrame}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowFrame(EntireSqlParser.WindowFrameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unboundedFrame}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnboundedFrame(EntireSqlParser.UnboundedFrameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentRowBound}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentRowBound(EntireSqlParser.CurrentRowBoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boundedFrame}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundedFrame(EntireSqlParser.BoundedFrameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code explainFormat}
	 * labeled alternative in {@link EntireSqlParser#explainOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplainFormat(EntireSqlParser.ExplainFormatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code explainType}
	 * labeled alternative in {@link EntireSqlParser#explainOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplainType(EntireSqlParser.ExplainTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isolationLevel}
	 * labeled alternative in {@link EntireSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsolationLevel(EntireSqlParser.IsolationLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code transactionAccessMode}
	 * labeled alternative in {@link EntireSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionAccessMode(EntireSqlParser.TransactionAccessModeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readUncommitted}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadUncommitted(EntireSqlParser.ReadUncommittedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code readCommitted}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadCommitted(EntireSqlParser.ReadCommittedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repeatableRead}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatableRead(EntireSqlParser.RepeatableReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code serializable}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSerializable(EntireSqlParser.SerializableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code positionalArgument}
	 * labeled alternative in {@link EntireSqlParser#callArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionalArgument(EntireSqlParser.PositionalArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code namedArgument}
	 * labeled alternative in {@link EntireSqlParser#callArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedArgument(EntireSqlParser.NamedArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code qualifiedArgument}
	 * labeled alternative in {@link EntireSqlParser#pathElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedArgument(EntireSqlParser.QualifiedArgumentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unqualifiedArgument}
	 * labeled alternative in {@link EntireSqlParser#pathElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnqualifiedArgument(EntireSqlParser.UnqualifiedArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#pathSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathSpecification(EntireSqlParser.PathSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#privilege}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivilege(EntireSqlParser.PrivilegeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#qualifiedName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedName(EntireSqlParser.QualifiedNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code backQuotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackQuotedIdentifier(EntireSqlParser.BackQuotedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mapidentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapidentifier(EntireSqlParser.MapidentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code quotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuotedIdentifier(EntireSqlParser.QuotedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code digitIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigitIdentifier(EntireSqlParser.DigitIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unquotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnquotedIdentifier(EntireSqlParser.UnquotedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#identifier_ext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier_ext(EntireSqlParser.Identifier_extContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(EntireSqlParser.DecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(EntireSqlParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteral(EntireSqlParser.IntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#reserved}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReserved(EntireSqlParser.ReservedContext ctx);
	/**
	 * Visit a parse tree produced by {@link EntireSqlParser#nonReserved}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonReserved(EntireSqlParser.NonReservedContext ctx);
}
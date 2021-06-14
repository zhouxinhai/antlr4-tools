// Generated from D:/code/java/learn/antlr4-tools/src/main/resources/antlr\EntireSql.g4 by ANTLR 4.9.1
package com.newland.bd.workflow.sql.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EntireSqlParser}.
 */
public interface EntireSqlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#multiStatement}.
	 * @param ctx the parse tree
	 */
	void enterMultiStatement(EntireSqlParser.MultiStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#multiStatement}.
	 * @param ctx the parse tree
	 */
	void exitMultiStatement(EntireSqlParser.MultiStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#singleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleStatement(EntireSqlParser.SingleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#singleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleStatement(EntireSqlParser.SingleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#allStatement}.
	 * @param ctx the parse tree
	 */
	void enterAllStatement(EntireSqlParser.AllStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#allStatement}.
	 * @param ctx the parse tree
	 */
	void exitAllStatement(EntireSqlParser.AllStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2AlterModify}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void enterDb2AlterModify(EntireSqlParser.Db2AlterModifyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2AlterModify}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void exitDb2AlterModify(EntireSqlParser.Db2AlterModifyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2TruncateTable}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void enterDb2TruncateTable(EntireSqlParser.Db2TruncateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2TruncateTable}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void exitDb2TruncateTable(EntireSqlParser.Db2TruncateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2CreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void enterDb2CreateTableByLike(EntireSqlParser.Db2CreateTableByLikeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2CreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void exitDb2CreateTableByLike(EntireSqlParser.Db2CreateTableByLikeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2RefreshTable}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void enterDb2RefreshTable(EntireSqlParser.Db2RefreshTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2RefreshTable}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void exitDb2RefreshTable(EntireSqlParser.Db2RefreshTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2CreateView}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void enterDb2CreateView(EntireSqlParser.Db2CreateViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2CreateView}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void exitDb2CreateView(EntireSqlParser.Db2CreateViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2CreateSequence}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void enterDb2CreateSequence(EntireSqlParser.Db2CreateSequenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2CreateSequence}
	 * labeled alternative in {@link EntireSqlParser#db2Statement}.
	 * @param ctx the parse tree
	 */
	void exitDb2CreateSequence(EntireSqlParser.Db2CreateSequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseCreateTableAs}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseCreateTableAs(EntireSqlParser.GBaseCreateTableAsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseCreateTableAs}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseCreateTableAs(EntireSqlParser.GBaseCreateTableAsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseCreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseCreateTableByLike(EntireSqlParser.GBaseCreateTableByLikeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseCreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseCreateTableByLike(EntireSqlParser.GBaseCreateTableByLikeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseDropNocopies}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseDropNocopies(EntireSqlParser.GBaseDropNocopiesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseDropNocopies}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseDropNocopies(EntireSqlParser.GBaseDropNocopiesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseShrinkSpace}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseShrinkSpace(EntireSqlParser.GBaseShrinkSpaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseShrinkSpace}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseShrinkSpace(EntireSqlParser.GBaseShrinkSpaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterChange}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterChange(EntireSqlParser.GBaseAlterChangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterChange}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterChange(EntireSqlParser.GBaseAlterChangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterModify}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterModify(EntireSqlParser.GBaseAlterModifyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterModify}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterModify(EntireSqlParser.GBaseAlterModifyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseRenameTable}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseRenameTable(EntireSqlParser.GBaseRenameTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseRenameTable}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseRenameTable(EntireSqlParser.GBaseRenameTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterView}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterView(EntireSqlParser.GBaseAlterViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterView}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterView(EntireSqlParser.GBaseAlterViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseCreateIndex(EntireSqlParser.GBaseCreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseCreateIndex(EntireSqlParser.GBaseCreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterIndex(EntireSqlParser.GBaseAlterIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterIndex(EntireSqlParser.GBaseAlterIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseDropIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseDropIndex(EntireSqlParser.GBaseDropIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseDropIndex}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseDropIndex(EntireSqlParser.GBaseDropIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterAutoextend}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterAutoextend(EntireSqlParser.GBaseAlterAutoextendContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterAutoextend}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterAutoextend(EntireSqlParser.GBaseAlterAutoextendContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterColCompress}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterColCompress(EntireSqlParser.GBaseAlterColCompressContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterColCompress}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterColCompress(EntireSqlParser.GBaseAlterColCompressContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseAlterTableCompress}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseAlterTableCompress(EntireSqlParser.GBaseAlterTableCompressContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseAlterTableCompress}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseAlterTableCompress(EntireSqlParser.GBaseAlterTableCompressContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseMerge}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseMerge(EntireSqlParser.GBaseMergeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseMerge}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseMerge(EntireSqlParser.GBaseMergeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gBaseSelectIntoOutfile}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGBaseSelectIntoOutfile(EntireSqlParser.GBaseSelectIntoOutfileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gBaseSelectIntoOutfile}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGBaseSelectIntoOutfile(EntireSqlParser.GBaseSelectIntoOutfileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gbaseLoadData}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterGbaseLoadData(EntireSqlParser.GbaseLoadDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gbaseLoadData}
	 * labeled alternative in {@link EntireSqlParser#gBaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitGbaseLoadData(EntireSqlParser.GbaseLoadDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gbaseLoadDataOptions}.
	 * @param ctx the parse tree
	 */
	void enterGbaseLoadDataOptions(EntireSqlParser.GbaseLoadDataOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gbaseLoadDataOptions}.
	 * @param ctx the parse tree
	 */
	void exitGbaseLoadDataOptions(EntireSqlParser.GbaseLoadDataOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#loadDataFiledsOptions}.
	 * @param ctx the parse tree
	 */
	void enterLoadDataFiledsOptions(EntireSqlParser.LoadDataFiledsOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#loadDataFiledsOptions}.
	 * @param ctx the parse tree
	 */
	void exitLoadDataFiledsOptions(EntireSqlParser.LoadDataFiledsOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#outfileOption}.
	 * @param ctx the parse tree
	 */
	void enterOutfileOption(EntireSqlParser.OutfileOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#outfileOption}.
	 * @param ctx the parse tree
	 */
	void exitOutfileOption(EntireSqlParser.OutfileOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#fieldsOutputOptions}.
	 * @param ctx the parse tree
	 */
	void enterFieldsOutputOptions(EntireSqlParser.FieldsOutputOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#fieldsOutputOptions}.
	 * @param ctx the parse tree
	 */
	void exitFieldsOutputOptions(EntireSqlParser.FieldsOutputOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#fieldsOutputOption}.
	 * @param ctx the parse tree
	 */
	void enterFieldsOutputOption(EntireSqlParser.FieldsOutputOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#fieldsOutputOption}.
	 * @param ctx the parse tree
	 */
	void exitFieldsOutputOption(EntireSqlParser.FieldsOutputOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#lineOutputOptions}.
	 * @param ctx the parse tree
	 */
	void enterLineOutputOptions(EntireSqlParser.LineOutputOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#lineOutputOptions}.
	 * @param ctx the parse tree
	 */
	void exitLineOutputOptions(EntireSqlParser.LineOutputOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#lineOutputOption}.
	 * @param ctx the parse tree
	 */
	void enterLineOutputOption(EntireSqlParser.LineOutputOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#lineOutputOption}.
	 * @param ctx the parse tree
	 */
	void exitLineOutputOption(EntireSqlParser.LineOutputOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#otherOutputOptions}.
	 * @param ctx the parse tree
	 */
	void enterOtherOutputOptions(EntireSqlParser.OtherOutputOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#otherOutputOptions}.
	 * @param ctx the parse tree
	 */
	void exitOtherOutputOptions(EntireSqlParser.OtherOutputOptionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveMultiInsert}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveMultiInsert(EntireSqlParser.HiveMultiInsertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveMultiInsert}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveMultiInsert(EntireSqlParser.HiveMultiInsertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveWriteDataToFile}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveWriteDataToFile(EntireSqlParser.HiveWriteDataToFileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveWriteDataToFile}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveWriteDataToFile(EntireSqlParser.HiveWriteDataToFileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveSetProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveSetProperties(EntireSqlParser.HiveSetPropertiesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveSetProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveSetProperties(EntireSqlParser.HiveSetPropertiesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreateDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreateDatabase(EntireSqlParser.HiveCreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreateDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreateDatabase(EntireSqlParser.HiveCreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveDropDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveDropDatabase(EntireSqlParser.HiveDropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveDropDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveDropDatabase(EntireSqlParser.HiveDropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterDatabase(EntireSqlParser.HiveAlterDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterDatabase}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterDatabase(EntireSqlParser.HiveAlterDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveDropTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveDropTable(EntireSqlParser.HiveDropTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveDropTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveDropTable(EntireSqlParser.HiveDropTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveTruncateTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveTruncateTable(EntireSqlParser.HiveTruncateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveTruncateTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveTruncateTable(EntireSqlParser.HiveTruncateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreateTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreateTable(EntireSqlParser.HiveCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreateTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreateTable(EntireSqlParser.HiveCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreateTableByLike(EntireSqlParser.HiveCreateTableByLikeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreateTableByLike}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreateTableByLike(EntireSqlParser.HiveCreateTableByLikeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterTableProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterTableProperties(EntireSqlParser.HiveAlterTablePropertiesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterTableProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterTableProperties(EntireSqlParser.HiveAlterTablePropertiesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterTableSerDe}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterTableSerDe(EntireSqlParser.HiveAlterTableSerDeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterTableSerDe}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterTableSerDe(EntireSqlParser.HiveAlterTableSerDeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterTableStorageProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterTableStorageProperties(EntireSqlParser.HiveAlterTableStoragePropertiesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterTableStorageProperties}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterTableStorageProperties(EntireSqlParser.HiveAlterTableStoragePropertiesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterTableSkewed}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterTableSkewed(EntireSqlParser.HiveAlterTableSkewedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterTableSkewed}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterTableSkewed(EntireSqlParser.HiveAlterTableSkewedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterTableConstraints}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterTableConstraints(EntireSqlParser.HiveAlterTableConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterTableConstraints}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterTableConstraints(EntireSqlParser.HiveAlterTableConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterPartition}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterPartition(EntireSqlParser.HiveAlterPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterPartition}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterPartition(EntireSqlParser.HiveAlterPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code archivePartition}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterArchivePartition(EntireSqlParser.ArchivePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code archivePartition}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitArchivePartition(EntireSqlParser.ArchivePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterColumn}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterColumn(EntireSqlParser.HiveAlterColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterColumn}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterColumn(EntireSqlParser.HiveAlterColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreateView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreateView(EntireSqlParser.HiveCreateViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreateView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreateView(EntireSqlParser.HiveCreateViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveDropView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveDropView(EntireSqlParser.HiveDropViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveDropView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveDropView(EntireSqlParser.HiveDropViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterView(EntireSqlParser.HiveAlterViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterView}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterView(EntireSqlParser.HiveAlterViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterViewAsSelect}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterViewAsSelect(EntireSqlParser.HiveAlterViewAsSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterViewAsSelect}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterViewAsSelect(EntireSqlParser.HiveAlterViewAsSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreateIndex(EntireSqlParser.HiveCreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreateIndex(EntireSqlParser.HiveCreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveDropIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveDropIndex(EntireSqlParser.HiveDropIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveDropIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveDropIndex(EntireSqlParser.HiveDropIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveAlterIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveAlterIndex(EntireSqlParser.HiveAlterIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveAlterIndex}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveAlterIndex(EntireSqlParser.HiveAlterIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreateTemporaryFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreateTemporaryFunctions(EntireSqlParser.HiveCreateTemporaryFunctionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreateTemporaryFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreateTemporaryFunctions(EntireSqlParser.HiveCreateTemporaryFunctionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveCreatePermanentFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveCreatePermanentFunctions(EntireSqlParser.HiveCreatePermanentFunctionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveCreatePermanentFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveCreatePermanentFunctions(EntireSqlParser.HiveCreatePermanentFunctionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveDropFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveDropFunctions(EntireSqlParser.HiveDropFunctionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveDropFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveDropFunctions(EntireSqlParser.HiveDropFunctionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveReloadFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveReloadFunctions(EntireSqlParser.HiveReloadFunctionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveReloadFunctions}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveReloadFunctions(EntireSqlParser.HiveReloadFunctionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveLoadFileIntoTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterHiveLoadFileIntoTable(EntireSqlParser.HiveLoadFileIntoTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveLoadFileIntoTable}
	 * labeled alternative in {@link EntireSqlParser#hiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitHiveLoadFileIntoTable(EntireSqlParser.HiveLoadFileIntoTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpVacuum}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpVacuum(EntireSqlParser.GpVacuumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpVacuum}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpVacuum(EntireSqlParser.GpVacuumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpTruncate}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpTruncate(EntireSqlParser.GpTruncateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpTruncate}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpTruncate(EntireSqlParser.GpTruncateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpCreateTable}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpCreateTable(EntireSqlParser.GpCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpCreateTable}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpCreateTable(EntireSqlParser.GpCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpCreateIndex(EntireSqlParser.GpCreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpCreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpCreateIndex(EntireSqlParser.GpCreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpRecreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpRecreateIndex(EntireSqlParser.GpRecreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpRecreateIndex}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpRecreateIndex(EntireSqlParser.GpRecreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpAlterPartition}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpAlterPartition(EntireSqlParser.GpAlterPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpAlterPartition}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpAlterPartition(EntireSqlParser.GpAlterPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpAlterColumn}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpAlterColumn(EntireSqlParser.GpAlterColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpAlterColumn}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpAlterColumn(EntireSqlParser.GpAlterColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpAlterConstraint}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void enterGpAlterConstraint(EntireSqlParser.GpAlterConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpAlterConstraint}
	 * labeled alternative in {@link EntireSqlParser#gpStatement}.
	 * @param ctx the parse tree
	 */
	void exitGpAlterConstraint(EntireSqlParser.GpAlterConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gpPartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterGpPartitionItems(EntireSqlParser.GpPartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gpPartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitGpPartitionItems(EntireSqlParser.GpPartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gpPartitionItem}.
	 * @param ctx the parse tree
	 */
	void enterGpPartitionItem(EntireSqlParser.GpPartitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gpPartitionItem}.
	 * @param ctx the parse tree
	 */
	void exitGpPartitionItem(EntireSqlParser.GpPartitionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gpSubpartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterGpSubpartitionItems(EntireSqlParser.GpSubpartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gpSubpartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitGpSubpartitionItems(EntireSqlParser.GpSubpartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gpSubPartitionItem}.
	 * @param ctx the parse tree
	 */
	void enterGpSubPartitionItem(EntireSqlParser.GpSubPartitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gpSubPartitionItem}.
	 * @param ctx the parse tree
	 */
	void exitGpSubPartitionItem(EntireSqlParser.GpSubPartitionItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code writeDataSingle}
	 * labeled alternative in {@link EntireSqlParser#hiveWriteDataToFileStm}.
	 * @param ctx the parse tree
	 */
	void enterWriteDataSingle(EntireSqlParser.WriteDataSingleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code writeDataSingle}
	 * labeled alternative in {@link EntireSqlParser#hiveWriteDataToFileStm}.
	 * @param ctx the parse tree
	 */
	void exitWriteDataSingle(EntireSqlParser.WriteDataSingleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code writeDataMulti}
	 * labeled alternative in {@link EntireSqlParser#hiveWriteDataToFileStm}.
	 * @param ctx the parse tree
	 */
	void enterWriteDataMulti(EntireSqlParser.WriteDataMultiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code writeDataMulti}
	 * labeled alternative in {@link EntireSqlParser#hiveWriteDataToFileStm}.
	 * @param ctx the parse tree
	 */
	void exitWriteDataMulti(EntireSqlParser.WriteDataMultiContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#constraintSpecification}.
	 * @param ctx the parse tree
	 */
	void enterConstraintSpecification(EntireSqlParser.ConstraintSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#constraintSpecification}.
	 * @param ctx the parse tree
	 */
	void exitConstraintSpecification(EntireSqlParser.ConstraintSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#fileFormat}.
	 * @param ctx the parse tree
	 */
	void enterFileFormat(EntireSqlParser.FileFormatContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#fileFormat}.
	 * @param ctx the parse tree
	 */
	void exitFileFormat(EntireSqlParser.FileFormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#rowFormat}.
	 * @param ctx the parse tree
	 */
	void enterRowFormat(EntireSqlParser.RowFormatContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#rowFormat}.
	 * @param ctx the parse tree
	 */
	void exitRowFormat(EntireSqlParser.RowFormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#stringExp}.
	 * @param ctx the parse tree
	 */
	void enterStringExp(EntireSqlParser.StringExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#stringExp}.
	 * @param ctx the parse tree
	 */
	void exitStringExp(EntireSqlParser.StringExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#stringAndNumberExp}.
	 * @param ctx the parse tree
	 */
	void enterStringAndNumberExp(EntireSqlParser.StringAndNumberExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#stringAndNumberExp}.
	 * @param ctx the parse tree
	 */
	void exitStringAndNumberExp(EntireSqlParser.StringAndNumberExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#multiStrEvaluation}.
	 * @param ctx the parse tree
	 */
	void enterMultiStrEvaluation(EntireSqlParser.MultiStrEvaluationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#multiStrEvaluation}.
	 * @param ctx the parse tree
	 */
	void exitMultiStrEvaluation(EntireSqlParser.MultiStrEvaluationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#multiKeyEvaluation}.
	 * @param ctx the parse tree
	 */
	void enterMultiKeyEvaluation(EntireSqlParser.MultiKeyEvaluationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#multiKeyEvaluation}.
	 * @param ctx the parse tree
	 */
	void exitMultiKeyEvaluation(EntireSqlParser.MultiKeyEvaluationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnDefinitionNoType}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinitionNoType(EntireSqlParser.ColumnDefinitionNoTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnDefinitionNoType}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinitionNoType(EntireSqlParser.ColumnDefinitionNoTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#nullExp}.
	 * @param ctx the parse tree
	 */
	void enterNullExp(EntireSqlParser.NullExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#nullExp}.
	 * @param ctx the parse tree
	 */
	void exitNullExp(EntireSqlParser.NullExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#standaloneExpression}.
	 * @param ctx the parse tree
	 */
	void enterStandaloneExpression(EntireSqlParser.StandaloneExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#standaloneExpression}.
	 * @param ctx the parse tree
	 */
	void exitStandaloneExpression(EntireSqlParser.StandaloneExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#standalonePathSpecification}.
	 * @param ctx the parse tree
	 */
	void enterStandalonePathSpecification(EntireSqlParser.StandalonePathSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#standalonePathSpecification}.
	 * @param ctx the parse tree
	 */
	void exitStandalonePathSpecification(EntireSqlParser.StandalonePathSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#queryStatement}.
	 * @param ctx the parse tree
	 */
	void enterQueryStatement(EntireSqlParser.QueryStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#queryStatement}.
	 * @param ctx the parse tree
	 */
	void exitQueryStatement(EntireSqlParser.QueryStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#statementNoEof}.
	 * @param ctx the parse tree
	 */
	void enterStatementNoEof(EntireSqlParser.StatementNoEofContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#statementNoEof}.
	 * @param ctx the parse tree
	 */
	void exitStatementNoEof(EntireSqlParser.StatementNoEofContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementDefault}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementDefault(EntireSqlParser.StatementDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementDefault}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementDefault(EntireSqlParser.StatementDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setOracleComment}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSetOracleComment(EntireSqlParser.SetOracleCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setOracleComment}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSetOracleComment(EntireSqlParser.SetOracleCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code update}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(EntireSqlParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code update}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(EntireSqlParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code use}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterUse(EntireSqlParser.UseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code use}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitUse(EntireSqlParser.UseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateSchema(EntireSqlParser.CreateSchemaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateSchema(EntireSqlParser.CreateSchemaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropSchema(EntireSqlParser.DropSchemaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropSchema(EntireSqlParser.DropSchemaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code renameSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRenameSchema(EntireSqlParser.RenameSchemaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code renameSchema}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRenameSchema(EntireSqlParser.RenameSchemaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createTableAsSelect}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createTableAsSelect}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(EntireSqlParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(EntireSqlParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code truncateTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterTruncateTable(EntireSqlParser.TruncateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code truncateTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitTruncateTable(EntireSqlParser.TruncateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(EntireSqlParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(EntireSqlParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(EntireSqlParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(EntireSqlParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAddIndex(EntireSqlParser.AddIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAddIndex(EntireSqlParser.AddIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropIndex(EntireSqlParser.DropIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropIndex(EntireSqlParser.DropIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterDropIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAlterDropIndex(EntireSqlParser.AlterDropIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterDropIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAlterDropIndex(EntireSqlParser.AlterDropIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code insert}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterInsert(EntireSqlParser.InsertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code insert}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitInsert(EntireSqlParser.InsertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code delete}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDelete(EntireSqlParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code delete}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDelete(EntireSqlParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code renameTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRenameTable(EntireSqlParser.RenameTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code renameTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRenameTable(EntireSqlParser.RenameTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modifyTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterModifyTable(EntireSqlParser.ModifyTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modifyTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitModifyTable(EntireSqlParser.ModifyTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code changeTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterChangeTable(EntireSqlParser.ChangeTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code changeTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitChangeTable(EntireSqlParser.ChangeTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAlterColumn(EntireSqlParser.AlterColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAlterColumn(EntireSqlParser.AlterColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code renameColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRenameColumn(EntireSqlParser.RenameColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code renameColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRenameColumn(EntireSqlParser.RenameColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropColumn(EntireSqlParser.DropColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropColumn(EntireSqlParser.DropColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAddColumn(EntireSqlParser.AddColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addColumn}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAddColumn(EntireSqlParser.AddColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code createView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCreateView(EntireSqlParser.CreateViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code createView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCreateView(EntireSqlParser.CreateViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDropView(EntireSqlParser.DropViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDropView(EntireSqlParser.DropViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCall(EntireSqlParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCall(EntireSqlParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code grant}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterGrant(EntireSqlParser.GrantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code grant}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitGrant(EntireSqlParser.GrantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code revoke}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRevoke(EntireSqlParser.RevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code revoke}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRevoke(EntireSqlParser.RevokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowGrants(EntireSqlParser.ShowGrantsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowGrants(EntireSqlParser.ShowGrantsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code explain}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExplain(EntireSqlParser.ExplainContext ctx);
	/**
	 * Exit a parse tree produced by the {@code explain}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExplain(EntireSqlParser.ExplainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateTable(EntireSqlParser.ShowCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateTable(EntireSqlParser.ShowCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateView(EntireSqlParser.ShowCreateViewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateView}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateView(EntireSqlParser.ShowCreateViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showTables}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowTables(EntireSqlParser.ShowTablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showTables}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowTables(EntireSqlParser.ShowTablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showSchemas}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowSchemas(EntireSqlParser.ShowSchemasContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showSchemas}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowSchemas(EntireSqlParser.ShowSchemasContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowIndex(EntireSqlParser.ShowIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showIndex}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowIndex(EntireSqlParser.ShowIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCatalogs}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowCatalogs(EntireSqlParser.ShowCatalogsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCatalogs}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowCatalogs(EntireSqlParser.ShowCatalogsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowColumns(EntireSqlParser.ShowColumnsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowColumns(EntireSqlParser.ShowColumnsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showStats}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowStats(EntireSqlParser.ShowStatsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showStats}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowStats(EntireSqlParser.ShowStatsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showStatsForQuery}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatsForQuery(EntireSqlParser.ShowStatsForQueryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showStatsForQuery}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatsForQuery(EntireSqlParser.ShowStatsForQueryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showFunctions}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowFunctions(EntireSqlParser.ShowFunctionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showFunctions}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowFunctions(EntireSqlParser.ShowFunctionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowSession(EntireSqlParser.ShowSessionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowSession(EntireSqlParser.ShowSessionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showPartition}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterShowPartition(EntireSqlParser.ShowPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showPartition}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitShowPartition(EntireSqlParser.ShowPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSetSession(EntireSqlParser.SetSessionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSetSession(EntireSqlParser.SetSessionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code resetSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterResetSession(EntireSqlParser.ResetSessionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code resetSession}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitResetSession(EntireSqlParser.ResetSessionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code startTransaction}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStartTransaction(EntireSqlParser.StartTransactionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code startTransaction}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStartTransaction(EntireSqlParser.StartTransactionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commit}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCommit(EntireSqlParser.CommitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commit}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCommit(EntireSqlParser.CommitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rollback}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRollback(EntireSqlParser.RollbackContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rollback}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRollback(EntireSqlParser.RollbackContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prepare}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrepare(EntireSqlParser.PrepareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prepare}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrepare(EntireSqlParser.PrepareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code deallocate}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDeallocate(EntireSqlParser.DeallocateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code deallocate}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDeallocate(EntireSqlParser.DeallocateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code execute}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExecute(EntireSqlParser.ExecuteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code execute}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExecute(EntireSqlParser.ExecuteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code describeInput}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDescribeInput(EntireSqlParser.DescribeInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code describeInput}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDescribeInput(EntireSqlParser.DescribeInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code describeOutput}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDescribeOutput(EntireSqlParser.DescribeOutputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code describeOutput}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDescribeOutput(EntireSqlParser.DescribeOutputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setPath}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSetPath(EntireSqlParser.SetPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setPath}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSetPath(EntireSqlParser.SetPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setProperty}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSetProperty(EntireSqlParser.SetPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setProperty}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSetProperty(EntireSqlParser.SetPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repairTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRepairTable(EntireSqlParser.RepairTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repairTable}
	 * labeled alternative in {@link EntireSqlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRepairTable(EntireSqlParser.RepairTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code insertInto}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 */
	void enterInsertInto(EntireSqlParser.InsertIntoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code insertInto}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 */
	void exitInsertInto(EntireSqlParser.InsertIntoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hiveInsertIntoSelect}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 */
	void enterHiveInsertIntoSelect(EntireSqlParser.HiveInsertIntoSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hiveInsertIntoSelect}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 */
	void exitHiveInsertIntoSelect(EntireSqlParser.HiveInsertIntoSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code insertIntoSelect}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 */
	void enterInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code insertIntoSelect}
	 * labeled alternative in {@link EntireSqlParser#insertStm}.
	 * @param ctx the parse tree
	 */
	void exitInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(EntireSqlParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(EntireSqlParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(EntireSqlParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(EntireSqlParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableComment}
	 * labeled alternative in {@link EntireSqlParser#oracleComment}.
	 * @param ctx the parse tree
	 */
	void enterTableComment(EntireSqlParser.TableCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableComment}
	 * labeled alternative in {@link EntireSqlParser#oracleComment}.
	 * @param ctx the parse tree
	 */
	void exitTableComment(EntireSqlParser.TableCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnComment}
	 * labeled alternative in {@link EntireSqlParser#oracleComment}.
	 * @param ctx the parse tree
	 */
	void enterColumnComment(EntireSqlParser.ColumnCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnComment}
	 * labeled alternative in {@link EntireSqlParser#oracleComment}.
	 * @param ctx the parse tree
	 */
	void exitColumnComment(EntireSqlParser.ColumnCommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oraclePartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterOraclePartitionItems(EntireSqlParser.OraclePartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oraclePartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitOraclePartitionItems(EntireSqlParser.OraclePartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#db2PartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterDb2PartitionItems(EntireSqlParser.Db2PartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#db2PartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitDb2PartitionItems(EntireSqlParser.Db2PartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#db2CreateTableAsSelectItem}.
	 * @param ctx the parse tree
	 */
	void enterDb2CreateTableAsSelectItem(EntireSqlParser.Db2CreateTableAsSelectItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#db2CreateTableAsSelectItem}.
	 * @param ctx the parse tree
	 */
	void exitDb2CreateTableAsSelectItem(EntireSqlParser.Db2CreateTableAsSelectItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleTemporayItem}.
	 * @param ctx the parse tree
	 */
	void enterOracleTemporayItem(EntireSqlParser.OracleTemporayItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleTemporayItem}.
	 * @param ctx the parse tree
	 */
	void exitOracleTemporayItem(EntireSqlParser.OracleTemporayItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#mysqlTemporayItem}.
	 * @param ctx the parse tree
	 */
	void enterMysqlTemporayItem(EntireSqlParser.MysqlTemporayItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#mysqlTemporayItem}.
	 * @param ctx the parse tree
	 */
	void exitMysqlTemporayItem(EntireSqlParser.MysqlTemporayItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gbaseTemporayItem}.
	 * @param ctx the parse tree
	 */
	void enterGbaseTemporayItem(EntireSqlParser.GbaseTemporayItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gbaseTemporayItem}.
	 * @param ctx the parse tree
	 */
	void exitGbaseTemporayItem(EntireSqlParser.GbaseTemporayItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleRangePartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterOracleRangePartitionItems(EntireSqlParser.OracleRangePartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleRangePartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitOracleRangePartitionItems(EntireSqlParser.OracleRangePartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleRangePartitionItem}.
	 * @param ctx the parse tree
	 */
	void enterOracleRangePartitionItem(EntireSqlParser.OracleRangePartitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleRangePartitionItem}.
	 * @param ctx the parse tree
	 */
	void exitOracleRangePartitionItem(EntireSqlParser.OracleRangePartitionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleListPartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterOracleListPartitionItems(EntireSqlParser.OracleListPartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleListPartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitOracleListPartitionItems(EntireSqlParser.OracleListPartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleListPartitionItem}.
	 * @param ctx the parse tree
	 */
	void enterOracleListPartitionItem(EntireSqlParser.OracleListPartitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleListPartitionItem}.
	 * @param ctx the parse tree
	 */
	void exitOracleListPartitionItem(EntireSqlParser.OracleListPartitionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleHashPartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterOracleHashPartitionItems(EntireSqlParser.OracleHashPartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleHashPartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitOracleHashPartitionItems(EntireSqlParser.OracleHashPartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleHashPartitionItem}.
	 * @param ctx the parse tree
	 */
	void enterOracleHashPartitionItem(EntireSqlParser.OracleHashPartitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleHashPartitionItem}.
	 * @param ctx the parse tree
	 */
	void exitOracleHashPartitionItem(EntireSqlParser.OracleHashPartitionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleCompositePartitionItems}.
	 * @param ctx the parse tree
	 */
	void enterOracleCompositePartitionItems(EntireSqlParser.OracleCompositePartitionItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleCompositePartitionItems}.
	 * @param ctx the parse tree
	 */
	void exitOracleCompositePartitionItems(EntireSqlParser.OracleCompositePartitionItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleCompositePartitionItem}.
	 * @param ctx the parse tree
	 */
	void enterOracleCompositePartitionItem(EntireSqlParser.OracleCompositePartitionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleCompositePartitionItem}.
	 * @param ctx the parse tree
	 */
	void exitOracleCompositePartitionItem(EntireSqlParser.OracleCompositePartitionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#constraintInfo}.
	 * @param ctx the parse tree
	 */
	void enterConstraintInfo(EntireSqlParser.ConstraintInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#constraintInfo}.
	 * @param ctx the parse tree
	 */
	void exitConstraintInfo(EntireSqlParser.ConstraintInfoContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#mysqlColumnCreateIndex}.
	 * @param ctx the parse tree
	 */
	void enterMysqlColumnCreateIndex(EntireSqlParser.MysqlColumnCreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#mysqlColumnCreateIndex}.
	 * @param ctx the parse tree
	 */
	void exitMysqlColumnCreateIndex(EntireSqlParser.MysqlColumnCreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#gbaseColumnCreateIndex}.
	 * @param ctx the parse tree
	 */
	void enterGbaseColumnCreateIndex(EntireSqlParser.GbaseColumnCreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#gbaseColumnCreateIndex}.
	 * @param ctx the parse tree
	 */
	void exitGbaseColumnCreateIndex(EntireSqlParser.GbaseColumnCreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#tableElement}.
	 * @param ctx the parse tree
	 */
	void enterTableElement(EntireSqlParser.TableElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#tableElement}.
	 * @param ctx the parse tree
	 */
	void exitTableElement(EntireSqlParser.TableElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#tableElements}.
	 * @param ctx the parse tree
	 */
	void enterTableElements(EntireSqlParser.TableElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#tableElements}.
	 * @param ctx the parse tree
	 */
	void exitTableElements(EntireSqlParser.TableElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinitions(EntireSqlParser.ColumnDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinitions(EntireSqlParser.ColumnDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(EntireSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(EntireSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#autoIncrement}.
	 * @param ctx the parse tree
	 */
	void enterAutoIncrement(EntireSqlParser.AutoIncrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#autoIncrement}.
	 * @param ctx the parse tree
	 */
	void exitAutoIncrement(EntireSqlParser.AutoIncrementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#compressType}.
	 * @param ctx the parse tree
	 */
	void enterCompressType(EntireSqlParser.CompressTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#compressType}.
	 * @param ctx the parse tree
	 */
	void exitCompressType(EntireSqlParser.CompressTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnNullable}.
	 * @param ctx the parse tree
	 */
	void enterColumnNullable(EntireSqlParser.ColumnNullableContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnNullable}.
	 * @param ctx the parse tree
	 */
	void exitColumnNullable(EntireSqlParser.ColumnNullableContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterColumnConstraint(EntireSqlParser.ColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitColumnConstraint(EntireSqlParser.ColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#likeClause}.
	 * @param ctx the parse tree
	 */
	void enterLikeClause(EntireSqlParser.LikeClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#likeClause}.
	 * @param ctx the parse tree
	 */
	void exitLikeClause(EntireSqlParser.LikeClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(EntireSqlParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(EntireSqlParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(EntireSqlParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(EntireSqlParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#queryNoWith}.
	 * @param ctx the parse tree
	 */
	void enterQueryNoWith(EntireSqlParser.QueryNoWithContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#queryNoWith}.
	 * @param ctx the parse tree
	 */
	void exitQueryNoWith(EntireSqlParser.QueryNoWithContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#limitStat}.
	 * @param ctx the parse tree
	 */
	void enterLimitStat(EntireSqlParser.LimitStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#limitStat}.
	 * @param ctx the parse tree
	 */
	void exitLimitStat(EntireSqlParser.LimitStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code queryTermDefault}
	 * labeled alternative in {@link EntireSqlParser#queryTerm}.
	 * @param ctx the parse tree
	 */
	void enterQueryTermDefault(EntireSqlParser.QueryTermDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code queryTermDefault}
	 * labeled alternative in {@link EntireSqlParser#queryTerm}.
	 * @param ctx the parse tree
	 */
	void exitQueryTermDefault(EntireSqlParser.QueryTermDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setOperation}
	 * labeled alternative in {@link EntireSqlParser#queryTerm}.
	 * @param ctx the parse tree
	 */
	void enterSetOperation(EntireSqlParser.SetOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setOperation}
	 * labeled alternative in {@link EntireSqlParser#queryTerm}.
	 * @param ctx the parse tree
	 */
	void exitSetOperation(EntireSqlParser.SetOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code queryPrimaryDefault}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 */
	void enterQueryPrimaryDefault(EntireSqlParser.QueryPrimaryDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code queryPrimaryDefault}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 */
	void exitQueryPrimaryDefault(EntireSqlParser.QueryPrimaryDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code table}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 */
	void enterTable(EntireSqlParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code table}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 */
	void exitTable(EntireSqlParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subquery}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 */
	void enterSubquery(EntireSqlParser.SubqueryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subquery}
	 * labeled alternative in {@link EntireSqlParser#queryPrimary}.
	 * @param ctx the parse tree
	 */
	void exitSubquery(EntireSqlParser.SubqueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#insertValues}.
	 * @param ctx the parse tree
	 */
	void enterInsertValues(EntireSqlParser.InsertValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#insertValues}.
	 * @param ctx the parse tree
	 */
	void exitInsertValues(EntireSqlParser.InsertValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void enterSortItem(EntireSqlParser.SortItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void exitSortItem(EntireSqlParser.SortItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void enterGroupBy(EntireSqlParser.GroupByContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void exitGroupBy(EntireSqlParser.GroupByContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleGroupingSet}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void enterSingleGroupingSet(EntireSqlParser.SingleGroupingSetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleGroupingSet}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void exitSingleGroupingSet(EntireSqlParser.SingleGroupingSetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rollup}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void enterRollup(EntireSqlParser.RollupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rollup}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void exitRollup(EntireSqlParser.RollupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cube}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void enterCube(EntireSqlParser.CubeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cube}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void exitCube(EntireSqlParser.CubeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multipleGroupingSets}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void enterMultipleGroupingSets(EntireSqlParser.MultipleGroupingSetsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multipleGroupingSets}
	 * labeled alternative in {@link EntireSqlParser#groupingElement}.
	 * @param ctx the parse tree
	 */
	void exitMultipleGroupingSets(EntireSqlParser.MultipleGroupingSetsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#groupingSet}.
	 * @param ctx the parse tree
	 */
	void enterGroupingSet(EntireSqlParser.GroupingSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#groupingSet}.
	 * @param ctx the parse tree
	 */
	void exitGroupingSet(EntireSqlParser.GroupingSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#namedQuery}.
	 * @param ctx the parse tree
	 */
	void enterNamedQuery(EntireSqlParser.NamedQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#namedQuery}.
	 * @param ctx the parse tree
	 */
	void exitNamedQuery(EntireSqlParser.NamedQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#setQuantifier}.
	 * @param ctx the parse tree
	 */
	void enterSetQuantifier(EntireSqlParser.SetQuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#setQuantifier}.
	 * @param ctx the parse tree
	 */
	void exitSetQuantifier(EntireSqlParser.SetQuantifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectSingle}
	 * labeled alternative in {@link EntireSqlParser#selectItem}.
	 * @param ctx the parse tree
	 */
	void enterSelectSingle(EntireSqlParser.SelectSingleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectSingle}
	 * labeled alternative in {@link EntireSqlParser#selectItem}.
	 * @param ctx the parse tree
	 */
	void exitSelectSingle(EntireSqlParser.SelectSingleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectAll}
	 * labeled alternative in {@link EntireSqlParser#selectItem}.
	 * @param ctx the parse tree
	 */
	void enterSelectAll(EntireSqlParser.SelectAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectAll}
	 * labeled alternative in {@link EntireSqlParser#selectItem}.
	 * @param ctx the parse tree
	 */
	void exitSelectAll(EntireSqlParser.SelectAllContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#aliasName}.
	 * @param ctx the parse tree
	 */
	void enterAliasName(EntireSqlParser.AliasNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#aliasName}.
	 * @param ctx the parse tree
	 */
	void exitAliasName(EntireSqlParser.AliasNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationDefault}
	 * labeled alternative in {@link EntireSqlParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelationDefault(EntireSqlParser.RelationDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationDefault}
	 * labeled alternative in {@link EntireSqlParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelationDefault(EntireSqlParser.RelationDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code joinRelation}
	 * labeled alternative in {@link EntireSqlParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterJoinRelation(EntireSqlParser.JoinRelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code joinRelation}
	 * labeled alternative in {@link EntireSqlParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitJoinRelation(EntireSqlParser.JoinRelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void enterJoinType(EntireSqlParser.JoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void exitJoinType(EntireSqlParser.JoinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#joinCriteria}.
	 * @param ctx the parse tree
	 */
	void enterJoinCriteria(EntireSqlParser.JoinCriteriaContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#joinCriteria}.
	 * @param ctx the parse tree
	 */
	void exitJoinCriteria(EntireSqlParser.JoinCriteriaContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#sampledRelation}.
	 * @param ctx the parse tree
	 */
	void enterSampledRelation(EntireSqlParser.SampledRelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#sampledRelation}.
	 * @param ctx the parse tree
	 */
	void exitSampledRelation(EntireSqlParser.SampledRelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#sampleType}.
	 * @param ctx the parse tree
	 */
	void enterSampleType(EntireSqlParser.SampleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#sampleType}.
	 * @param ctx the parse tree
	 */
	void exitSampleType(EntireSqlParser.SampleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#aliasedRelation}.
	 * @param ctx the parse tree
	 */
	void enterAliasedRelation(EntireSqlParser.AliasedRelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#aliasedRelation}.
	 * @param ctx the parse tree
	 */
	void exitAliasedRelation(EntireSqlParser.AliasedRelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnAliases}.
	 * @param ctx the parse tree
	 */
	void enterColumnAliases(EntireSqlParser.ColumnAliasesContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnAliases}.
	 * @param ctx the parse tree
	 */
	void exitColumnAliases(EntireSqlParser.ColumnAliasesContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#lengthDesc}.
	 * @param ctx the parse tree
	 */
	void enterLengthDesc(EntireSqlParser.LengthDescContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#lengthDesc}.
	 * @param ctx the parse tree
	 */
	void exitLengthDesc(EntireSqlParser.LengthDescContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#columnAliasesByOrder}.
	 * @param ctx the parse tree
	 */
	void enterColumnAliasesByOrder(EntireSqlParser.ColumnAliasesByOrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#columnAliasesByOrder}.
	 * @param ctx the parse tree
	 */
	void exitColumnAliasesByOrder(EntireSqlParser.ColumnAliasesByOrderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableName}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void enterTableName(EntireSqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableName}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void exitTableName(EntireSqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryRelation}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryRelation(EntireSqlParser.SubqueryRelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryRelation}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryRelation(EntireSqlParser.SubqueryRelationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unnest}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void enterUnnest(EntireSqlParser.UnnestContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unnest}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void exitUnnest(EntireSqlParser.UnnestContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lateral}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void enterLateral(EntireSqlParser.LateralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lateral}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void exitLateral(EntireSqlParser.LateralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedRelation}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedRelation(EntireSqlParser.ParenthesizedRelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedRelation}
	 * labeled alternative in {@link EntireSqlParser#relationPrimary}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedRelation(EntireSqlParser.ParenthesizedRelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(EntireSqlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(EntireSqlParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#partitionSpec}.
	 * @param ctx the parse tree
	 */
	void enterPartitionSpec(EntireSqlParser.PartitionSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#partitionSpec}.
	 * @param ctx the parse tree
	 */
	void exitPartitionSpec(EntireSqlParser.PartitionSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#partitionInfo}.
	 * @param ctx the parse tree
	 */
	void enterPartitionInfo(EntireSqlParser.PartitionInfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#partitionInfo}.
	 * @param ctx the parse tree
	 */
	void exitPartitionInfo(EntireSqlParser.PartitionInfoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalNot}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNot(EntireSqlParser.LogicalNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalNot}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNot(EntireSqlParser.LogicalNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicated}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterPredicated(EntireSqlParser.PredicatedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicated}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitPredicated(EntireSqlParser.PredicatedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalBinary}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalBinary(EntireSqlParser.LogicalBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalBinary}
	 * labeled alternative in {@link EntireSqlParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalBinary(EntireSqlParser.LogicalBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterComparison(EntireSqlParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitComparison(EntireSqlParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code quantifiedComparison}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterQuantifiedComparison(EntireSqlParser.QuantifiedComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code quantifiedComparison}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitQuantifiedComparison(EntireSqlParser.QuantifiedComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code between}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBetween(EntireSqlParser.BetweenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code between}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBetween(EntireSqlParser.BetweenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inList}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterInList(EntireSqlParser.InListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inList}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitInList(EntireSqlParser.InListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inSubquery}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterInSubquery(EntireSqlParser.InSubqueryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inSubquery}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitInSubquery(EntireSqlParser.InSubqueryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code like}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterLike(EntireSqlParser.LikeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code like}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitLike(EntireSqlParser.LikeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code regexp}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterRegexp(EntireSqlParser.RegexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code regexp}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitRegexp(EntireSqlParser.RegexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rlike}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterRlike(EntireSqlParser.RlikeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rlike}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitRlike(EntireSqlParser.RlikeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullPredicate}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterNullPredicate(EntireSqlParser.NullPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullPredicate}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitNullPredicate(EntireSqlParser.NullPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code distinctFrom}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterDistinctFrom(EntireSqlParser.DistinctFromContext ctx);
	/**
	 * Exit a parse tree produced by the {@code distinctFrom}
	 * labeled alternative in {@link EntireSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitDistinctFrom(EntireSqlParser.DistinctFromContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticShift}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticShift(EntireSqlParser.ArithmeticShiftContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticShift}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticShift(EntireSqlParser.ArithmeticShiftContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueExpressionDefault}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterValueExpressionDefault(EntireSqlParser.ValueExpressionDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueExpressionDefault}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitValueExpressionDefault(EntireSqlParser.ValueExpressionDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(EntireSqlParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(EntireSqlParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpTypeConvert}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterGpTypeConvert(EntireSqlParser.GpTypeConvertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpTypeConvert}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitGpTypeConvert(EntireSqlParser.GpTypeConvertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticBinary}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticBinary(EntireSqlParser.ArithmeticBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticBinary}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticBinary(EntireSqlParser.ArithmeticBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticUnary}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticUnary(EntireSqlParser.ArithmeticUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticUnary}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticUnary(EntireSqlParser.ArithmeticUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atTimeZone}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterAtTimeZone(EntireSqlParser.AtTimeZoneContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atTimeZone}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitAtTimeZone(EntireSqlParser.AtTimeZoneContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mySqlTypeConvert}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterMySqlTypeConvert(EntireSqlParser.MySqlTypeConvertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mySqlTypeConvert}
	 * labeled alternative in {@link EntireSqlParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitMySqlTypeConvert(EntireSqlParser.MySqlTypeConvertContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#shiftOpt}.
	 * @param ctx the parse tree
	 */
	void enterShiftOpt(EntireSqlParser.ShiftOptContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#shiftOpt}.
	 * @param ctx the parse tree
	 */
	void exitShiftOpt(EntireSqlParser.ShiftOptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dereference}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterDereference(EntireSqlParser.DereferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dereference}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitDereference(EntireSqlParser.DereferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isBooleanValue}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterIsBooleanValue(EntireSqlParser.IsBooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isBooleanValue}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitIsBooleanValue(EntireSqlParser.IsBooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specialDateTimeFunction}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSpecialDateTimeFunction(EntireSqlParser.SpecialDateTimeFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specialDateTimeFunction}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSpecialDateTimeFunction(EntireSqlParser.SpecialDateTimeFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code substring}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubstring(EntireSqlParser.SubstringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code substring}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubstring(EntireSqlParser.SubstringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cast}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterCast(EntireSqlParser.CastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cast}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitCast(EntireSqlParser.CastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambda}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambda(EntireSqlParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambda}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambda(EntireSqlParser.LambdaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(EntireSqlParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(EntireSqlParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallListAgg}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallListAgg(EntireSqlParser.FunctionCallListAggContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallListAgg}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallListAgg(EntireSqlParser.FunctionCallListAggContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterParameter(EntireSqlParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parameter}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitParameter(EntireSqlParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalize}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNormalize(EntireSqlParser.NormalizeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalize}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNormalize(EntireSqlParser.NormalizeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intervalLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntervalLiteral(EntireSqlParser.IntervalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intervalLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntervalLiteral(EntireSqlParser.IntervalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(EntireSqlParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numericLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(EntireSqlParser.NumericLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(EntireSqlParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(EntireSqlParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleCase}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleCase(EntireSqlParser.SimpleCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleCase}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleCase(EntireSqlParser.SimpleCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnReference}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterColumnReference(EntireSqlParser.ColumnReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnReference}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitColumnReference(EntireSqlParser.ColumnReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(EntireSqlParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(EntireSqlParser.NullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rowConstructor}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterRowConstructor(EntireSqlParser.RowConstructorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rowConstructor}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitRowConstructor(EntireSqlParser.RowConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpFunctionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterGpFunctionCall(EntireSqlParser.GpFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpFunctionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitGpFunctionCall(EntireSqlParser.GpFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscript}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubscript(EntireSqlParser.SubscriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscript}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubscript(EntireSqlParser.SubscriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentPath}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterCurrentPath(EntireSqlParser.CurrentPathContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentPath}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitCurrentPath(EntireSqlParser.CurrentPathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryExpression}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryExpression(EntireSqlParser.SubqueryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryExpression}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryExpression(EntireSqlParser.SubqueryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryLiteral(EntireSqlParser.BinaryLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryLiteral(EntireSqlParser.BinaryLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentUser}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterCurrentUser(EntireSqlParser.CurrentUserContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentUser}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitCurrentUser(EntireSqlParser.CurrentUserContext ctx);
	/**
	 * Enter a parse tree produced by the {@code extract}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterExtract(EntireSqlParser.ExtractContext ctx);
	/**
	 * Exit a parse tree produced by the {@code extract}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitExtract(EntireSqlParser.ExtractContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(EntireSqlParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(EntireSqlParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayConstructor}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayConstructor(EntireSqlParser.ArrayConstructorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayConstructor}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayConstructor(EntireSqlParser.ArrayConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(EntireSqlParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(EntireSqlParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mysqlFunctionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterMysqlFunctionCall(EntireSqlParser.MysqlFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mysqlFunctionCall}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitMysqlFunctionCall(EntireSqlParser.MysqlFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exists}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterExists(EntireSqlParser.ExistsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exists}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitExists(EntireSqlParser.ExistsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code db2CurrentTimeFunc}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterDb2CurrentTimeFunc(EntireSqlParser.Db2CurrentTimeFuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code db2CurrentTimeFunc}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitDb2CurrentTimeFunc(EntireSqlParser.Db2CurrentTimeFuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code position}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPosition(EntireSqlParser.PositionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code position}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPosition(EntireSqlParser.PositionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code searchedCase}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterSearchedCase(EntireSqlParser.SearchedCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code searchedCase}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitSearchedCase(EntireSqlParser.SearchedCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code groupingOperation}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterGroupingOperation(EntireSqlParser.GroupingOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code groupingOperation}
	 * labeled alternative in {@link EntireSqlParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitGroupingOperation(EntireSqlParser.GroupingOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpOverLayFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpOverLayFunction(EntireSqlParser.GpOverLayFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpOverLayFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpOverLayFunction(EntireSqlParser.GpOverLayFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpTrimFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpTrimFunction(EntireSqlParser.GpTrimFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpTrimFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpTrimFunction(EntireSqlParser.GpTrimFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpTocharFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpTocharFunction(EntireSqlParser.GpTocharFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpTocharFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpTocharFunction(EntireSqlParser.GpTocharFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpAgeFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpAgeFunction(EntireSqlParser.GpAgeFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpAgeFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpAgeFunction(EntireSqlParser.GpAgeFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpdatePartFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpdatePartFunction(EntireSqlParser.GpdatePartFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpdatePartFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpdatePartFunction(EntireSqlParser.GpdatePartFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpExtractFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpExtractFunction(EntireSqlParser.GpExtractFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpExtractFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpExtractFunction(EntireSqlParser.GpExtractFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpIsfiniteFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpIsfiniteFunction(EntireSqlParser.GpIsfiniteFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpIsfiniteFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpIsfiniteFunction(EntireSqlParser.GpIsfiniteFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpJustifyDaysFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpJustifyDaysFunction(EntireSqlParser.GpJustifyDaysFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpJustifyDaysFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpJustifyDaysFunction(EntireSqlParser.GpJustifyDaysFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpJustifyHourFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpJustifyHourFunction(EntireSqlParser.GpJustifyHourFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpJustifyHourFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpJustifyHourFunction(EntireSqlParser.GpJustifyHourFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gpJustifyIntervalFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterGpJustifyIntervalFunction(EntireSqlParser.GpJustifyIntervalFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gpJustifyIntervalFunction}
	 * labeled alternative in {@link EntireSqlParser#gpFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitGpJustifyIntervalFunction(EntireSqlParser.GpJustifyIntervalFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mysqlTrimFunction}
	 * labeled alternative in {@link EntireSqlParser#mysqlFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterMysqlTrimFunction(EntireSqlParser.MysqlTrimFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mysqlTrimFunction}
	 * labeled alternative in {@link EntireSqlParser#mysqlFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitMysqlTrimFunction(EntireSqlParser.MysqlTrimFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mysqlDateFunction}
	 * labeled alternative in {@link EntireSqlParser#mysqlFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void enterMysqlDateFunction(EntireSqlParser.MysqlDateFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mysqlDateFunction}
	 * labeled alternative in {@link EntireSqlParser#mysqlFunctionCalls}.
	 * @param ctx the parse tree
	 */
	void exitMysqlDateFunction(EntireSqlParser.MysqlDateFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#oracleJoinType}.
	 * @param ctx the parse tree
	 */
	void enterOracleJoinType(EntireSqlParser.OracleJoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#oracleJoinType}.
	 * @param ctx the parse tree
	 */
	void exitOracleJoinType(EntireSqlParser.OracleJoinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#typeConstructor}.
	 * @param ctx the parse tree
	 */
	void enterTypeConstructor(EntireSqlParser.TypeConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#typeConstructor}.
	 * @param ctx the parse tree
	 */
	void exitTypeConstructor(EntireSqlParser.TypeConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicStringLiteral}
	 * labeled alternative in {@link EntireSqlParser#string}.
	 * @param ctx the parse tree
	 */
	void enterBasicStringLiteral(EntireSqlParser.BasicStringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicStringLiteral}
	 * labeled alternative in {@link EntireSqlParser#string}.
	 * @param ctx the parse tree
	 */
	void exitBasicStringLiteral(EntireSqlParser.BasicStringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unicodeStringLiteral}
	 * labeled alternative in {@link EntireSqlParser#string}.
	 * @param ctx the parse tree
	 */
	void enterUnicodeStringLiteral(EntireSqlParser.UnicodeStringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unicodeStringLiteral}
	 * labeled alternative in {@link EntireSqlParser#string}.
	 * @param ctx the parse tree
	 */
	void exitUnicodeStringLiteral(EntireSqlParser.UnicodeStringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeZoneInterval}
	 * labeled alternative in {@link EntireSqlParser#timeZoneSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTimeZoneInterval(EntireSqlParser.TimeZoneIntervalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeZoneInterval}
	 * labeled alternative in {@link EntireSqlParser#timeZoneSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTimeZoneInterval(EntireSqlParser.TimeZoneIntervalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timeZoneString}
	 * labeled alternative in {@link EntireSqlParser#timeZoneSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTimeZoneString(EntireSqlParser.TimeZoneStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timeZoneString}
	 * labeled alternative in {@link EntireSqlParser#timeZoneSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTimeZoneString(EntireSqlParser.TimeZoneStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(EntireSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(EntireSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#comparisonQuantifier}.
	 * @param ctx the parse tree
	 */
	void enterComparisonQuantifier(EntireSqlParser.ComparisonQuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#comparisonQuantifier}.
	 * @param ctx the parse tree
	 */
	void exitComparisonQuantifier(EntireSqlParser.ComparisonQuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(EntireSqlParser.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(EntireSqlParser.BooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(EntireSqlParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(EntireSqlParser.IntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#intervalField}.
	 * @param ctx the parse tree
	 */
	void enterIntervalField(EntireSqlParser.IntervalFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#intervalField}.
	 * @param ctx the parse tree
	 */
	void exitIntervalField(EntireSqlParser.IntervalFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#normalForm}.
	 * @param ctx the parse tree
	 */
	void enterNormalForm(EntireSqlParser.NormalFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#normalForm}.
	 * @param ctx the parse tree
	 */
	void exitNormalForm(EntireSqlParser.NormalFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(EntireSqlParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(EntireSqlParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(EntireSqlParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(EntireSqlParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(EntireSqlParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(EntireSqlParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void enterWhenClause(EntireSqlParser.WhenClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#whenClause}.
	 * @param ctx the parse tree
	 */
	void exitWhenClause(EntireSqlParser.WhenClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(EntireSqlParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(EntireSqlParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#over}.
	 * @param ctx the parse tree
	 */
	void enterOver(EntireSqlParser.OverContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#over}.
	 * @param ctx the parse tree
	 */
	void exitOver(EntireSqlParser.OverContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#windowFrame}.
	 * @param ctx the parse tree
	 */
	void enterWindowFrame(EntireSqlParser.WindowFrameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#windowFrame}.
	 * @param ctx the parse tree
	 */
	void exitWindowFrame(EntireSqlParser.WindowFrameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unboundedFrame}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void enterUnboundedFrame(EntireSqlParser.UnboundedFrameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unboundedFrame}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void exitUnboundedFrame(EntireSqlParser.UnboundedFrameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentRowBound}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void enterCurrentRowBound(EntireSqlParser.CurrentRowBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentRowBound}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void exitCurrentRowBound(EntireSqlParser.CurrentRowBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boundedFrame}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void enterBoundedFrame(EntireSqlParser.BoundedFrameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boundedFrame}
	 * labeled alternative in {@link EntireSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void exitBoundedFrame(EntireSqlParser.BoundedFrameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code explainFormat}
	 * labeled alternative in {@link EntireSqlParser#explainOption}.
	 * @param ctx the parse tree
	 */
	void enterExplainFormat(EntireSqlParser.ExplainFormatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code explainFormat}
	 * labeled alternative in {@link EntireSqlParser#explainOption}.
	 * @param ctx the parse tree
	 */
	void exitExplainFormat(EntireSqlParser.ExplainFormatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code explainType}
	 * labeled alternative in {@link EntireSqlParser#explainOption}.
	 * @param ctx the parse tree
	 */
	void enterExplainType(EntireSqlParser.ExplainTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code explainType}
	 * labeled alternative in {@link EntireSqlParser#explainOption}.
	 * @param ctx the parse tree
	 */
	void exitExplainType(EntireSqlParser.ExplainTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isolationLevel}
	 * labeled alternative in {@link EntireSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void enterIsolationLevel(EntireSqlParser.IsolationLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isolationLevel}
	 * labeled alternative in {@link EntireSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void exitIsolationLevel(EntireSqlParser.IsolationLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code transactionAccessMode}
	 * labeled alternative in {@link EntireSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void enterTransactionAccessMode(EntireSqlParser.TransactionAccessModeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code transactionAccessMode}
	 * labeled alternative in {@link EntireSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void exitTransactionAccessMode(EntireSqlParser.TransactionAccessModeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readUncommitted}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void enterReadUncommitted(EntireSqlParser.ReadUncommittedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readUncommitted}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void exitReadUncommitted(EntireSqlParser.ReadUncommittedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readCommitted}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void enterReadCommitted(EntireSqlParser.ReadCommittedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readCommitted}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void exitReadCommitted(EntireSqlParser.ReadCommittedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repeatableRead}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void enterRepeatableRead(EntireSqlParser.RepeatableReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repeatableRead}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void exitRepeatableRead(EntireSqlParser.RepeatableReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code serializable}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void enterSerializable(EntireSqlParser.SerializableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code serializable}
	 * labeled alternative in {@link EntireSqlParser#levelOfIsolation}.
	 * @param ctx the parse tree
	 */
	void exitSerializable(EntireSqlParser.SerializableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code positionalArgument}
	 * labeled alternative in {@link EntireSqlParser#callArgument}.
	 * @param ctx the parse tree
	 */
	void enterPositionalArgument(EntireSqlParser.PositionalArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code positionalArgument}
	 * labeled alternative in {@link EntireSqlParser#callArgument}.
	 * @param ctx the parse tree
	 */
	void exitPositionalArgument(EntireSqlParser.PositionalArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code namedArgument}
	 * labeled alternative in {@link EntireSqlParser#callArgument}.
	 * @param ctx the parse tree
	 */
	void enterNamedArgument(EntireSqlParser.NamedArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code namedArgument}
	 * labeled alternative in {@link EntireSqlParser#callArgument}.
	 * @param ctx the parse tree
	 */
	void exitNamedArgument(EntireSqlParser.NamedArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code qualifiedArgument}
	 * labeled alternative in {@link EntireSqlParser#pathElement}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedArgument(EntireSqlParser.QualifiedArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code qualifiedArgument}
	 * labeled alternative in {@link EntireSqlParser#pathElement}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedArgument(EntireSqlParser.QualifiedArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unqualifiedArgument}
	 * labeled alternative in {@link EntireSqlParser#pathElement}.
	 * @param ctx the parse tree
	 */
	void enterUnqualifiedArgument(EntireSqlParser.UnqualifiedArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unqualifiedArgument}
	 * labeled alternative in {@link EntireSqlParser#pathElement}.
	 * @param ctx the parse tree
	 */
	void exitUnqualifiedArgument(EntireSqlParser.UnqualifiedArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#pathSpecification}.
	 * @param ctx the parse tree
	 */
	void enterPathSpecification(EntireSqlParser.PathSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#pathSpecification}.
	 * @param ctx the parse tree
	 */
	void exitPathSpecification(EntireSqlParser.PathSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#privilege}.
	 * @param ctx the parse tree
	 */
	void enterPrivilege(EntireSqlParser.PrivilegeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#privilege}.
	 * @param ctx the parse tree
	 */
	void exitPrivilege(EntireSqlParser.PrivilegeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(EntireSqlParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(EntireSqlParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code backQuotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterBackQuotedIdentifier(EntireSqlParser.BackQuotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code backQuotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitBackQuotedIdentifier(EntireSqlParser.BackQuotedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mapidentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterMapidentifier(EntireSqlParser.MapidentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mapidentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitMapidentifier(EntireSqlParser.MapidentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code quotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterQuotedIdentifier(EntireSqlParser.QuotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code quotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitQuotedIdentifier(EntireSqlParser.QuotedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code digitIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterDigitIdentifier(EntireSqlParser.DigitIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code digitIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitDigitIdentifier(EntireSqlParser.DigitIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unquotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterUnquotedIdentifier(EntireSqlParser.UnquotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unquotedIdentifier}
	 * labeled alternative in {@link EntireSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitUnquotedIdentifier(EntireSqlParser.UnquotedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#identifier_ext}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier_ext(EntireSqlParser.Identifier_extContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#identifier_ext}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier_ext(EntireSqlParser.Identifier_extContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(EntireSqlParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimalLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(EntireSqlParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(EntireSqlParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(EntireSqlParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(EntireSqlParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteral}
	 * labeled alternative in {@link EntireSqlParser#number}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(EntireSqlParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#reserved}.
	 * @param ctx the parse tree
	 */
	void enterReserved(EntireSqlParser.ReservedContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#reserved}.
	 * @param ctx the parse tree
	 */
	void exitReserved(EntireSqlParser.ReservedContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntireSqlParser#nonReserved}.
	 * @param ctx the parse tree
	 */
	void enterNonReserved(EntireSqlParser.NonReservedContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntireSqlParser#nonReserved}.
	 * @param ctx the parse tree
	 */
	void exitNonReserved(EntireSqlParser.NonReservedContext ctx);
}
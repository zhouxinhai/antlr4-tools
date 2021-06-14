package com.zhouxh.antlr4.tool.sql.parserExt.inspect;

import com.zhouxh.antlr4.tool.sql.bean.TableNameAndOpt;
import com.zhouxh.antlr4.tool.sql.bean.TableOpt;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.ColumnNameAndType;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.TableAndPartitionInfo;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlBaseListener;
import com.zhouxh.antlr4.tool.sql.parser.EntireSqlParser;
import com.zhouxh.antlr4.tool.sql.parserExt.ParseTreePropertyExt;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * Create by Howard on 2018/12/7
 */
public class InspectEntireSqlListener extends EntireSqlBaseListener {
    private TokenStream tokenStream;
    private ColumnAndTableAliasInspect columnAndTableAliasInspect;   //校验字段用到的表别名是否真实存在



    private List<TableNameAndOpt> tableNameAndOptList = new ArrayList<>();
    private Stack<ParseTree> queryCtxStack = new Stack<>();
    private ParseTreePropertyExt<List<String>> tablenameListCache = new ParseTreePropertyExt<>();
    private List<TableAndPartitionInfo> hiveTablePartitionInfoList = new ArrayList<>();   //hive建表语句，表名和分区信息的对应关系
    private String curCreateTableName = null;

    private Set<String> temporaryTable = new HashSet<>();  //sql语句中的临时表


    public InspectEntireSqlListener(EntireSqlParser parser){
        tokenStream = parser.getTokenStream();
        columnAndTableAliasInspect = new ColumnAndTableAliasInspect(tokenStream);
    }

    public void init(){
        hiveTablePartitionInfoList.clear();
        tableNameAndOptList.clear();
        queryCtxStack.clear();
        tablenameListCache.clear();
        columnAndTableAliasInspect.clear();
    }

    private void addTableNameAndOptList(String tablename,TableOpt tableOpt){
        if(temporaryTable.contains(tablename.toUpperCase())){//这张表是临时表，不需要进行权限校验
            return;
        }

        TableNameAndOpt tableNameAndOpt = new TableNameAndOpt(tablename,tableOpt);
        tableNameAndOptList.add(tableNameAndOpt);
    }

    public List<TableNameAndOpt> getTableNameAndOptList() {
        return tableNameAndOptList;
    }


    public List<TableAndPartitionInfo> getHiveTablePartitionInfoList() {
        return hiveTablePartitionInfoList;
    }

    //后置校验需要用的方法//////////////////////////////////////////////////////////////////
    /**
     * 把所有表别名都收集起来
     * @param ctx
     */
    @Override
    public void enterAliasedRelation(EntireSqlParser.AliasedRelationContext ctx) {
        //把所有表别名都收集起来
        columnAndTableAliasInspect.storeTableAliasNameSet(ctx);

    }


    /**
     * 把所有字段的引用到的表别名都收集起来
     * @param ctx
     */
    @Override
    public void enterDereference(EntireSqlParser.DereferenceContext ctx) {
        columnAndTableAliasInspect.storeColumnCtxAndTableAliasesList(ctx);
    }


    /**
     * 统一后置校验
     * @param ctx
     */
    @Override
    public void exitStatementNoEof(EntireSqlParser.StatementNoEofContext ctx) {
        columnAndTableAliasInspect.columnAndTableAliasInspect();
    }


    //SqlBase.g4规则中内容//////////////////////////////////////////////////////////////////
    @Override
    public void enterCommit(EntireSqlParser.CommitContext ctx) {
        addTableNameAndOptList("",TableOpt.COMMIT);
    }

    @Override
    public void enterTableComment(EntireSqlParser.TableCommentContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.UPDATE);
    }

    @Override
    public void enterRollback(EntireSqlParser.RollbackContext ctx) {
        addTableNameAndOptList("",TableOpt.ROLLBACK);
    }

    @Override
    public void enterQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {
        queryCtxStack.push(ctx);
    }

    @Override
    public void enterNamedQuery(EntireSqlParser.NamedQueryContext ctx) {
        temporaryTable.add(ctx.name.getText().toUpperCase());
    }

    @Override
    public void exitQuerySpecification(EntireSqlParser.QuerySpecificationContext ctx) {
        ParseTree queryCtx = queryCtxStack.pop();
        List<String> tablenameList = tablenameListCache.get(queryCtx);
        if(tablenameList == null || tablenameList.size() == 0){
            return;
        }

        for(String tablename:tablenameList){
            addTableNameAndOptList(tablename,TableOpt.SELECT);
        }
        tablenameListCache.removeFrom(queryCtx);
    }

    @Override
    public void exitTableName(EntireSqlParser.TableNameContext ctx) {
        ParseTree queryCtx = queryCtxStack.peek();
        String tablename = ctx.qualifiedName().getText();
        List<String> tablenameList = tablenameListCache.get(queryCtx);
        if(tablenameList == null){
            tablenameList = new ArrayList<>();
            tablenameListCache.put(queryCtx,tablenameList);
        }
        tablenameList.add(tablename);
    }

    @Override
    public void enterUpdate(EntireSqlParser.UpdateContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.UPDATE);
    }

    @Override
    public void enterCreateSchema(EntireSqlParser.CreateSchemaContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.CREATE_SCHEMA);
    }

    @Override
    public void enterDropSchema(EntireSqlParser.DropSchemaContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.DROP_SCHEMA);
    }

    @Override
    public void enterRenameSchema(EntireSqlParser.RenameSchemaContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.ALTER_RENAME_SCHEMA);
    }

    @Override
    public void enterCreateTableAsSelect(EntireSqlParser.CreateTableAsSelectContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.CREATE_TABLE);
    }

    @Override
    public void enterCreateTable(EntireSqlParser.CreateTableContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.qualifiedName().getText().toUpperCase());
            return;
        }
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.CREATE_TABLE);
        curCreateTableName = ctx.qualifiedName().getText();
    }

    @Override
    public void exitCreateTable(EntireSqlParser.CreateTableContext ctx) {
        curCreateTableName = null;
    }


    @Override
    public void enterTruncateTable(EntireSqlParser.TruncateTableContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.TRUNCATE);
    }

    @Override
    public void enterDropTable(EntireSqlParser.DropTableContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.DROP_TABLE);
    }

    @Override
    public void enterInsertInto(EntireSqlParser.InsertIntoContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.INSERT);
    }

    @Override
    public void enterInsertIntoSelect(EntireSqlParser.InsertIntoSelectContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.INSERT);
    }

    @Override
    public void enterSetProperty(EntireSqlParser.SetPropertyContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterRepairTable(EntireSqlParser.RepairTableContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.OPTIMIZE);
    }

    @Override
    public void enterSetPath(EntireSqlParser.SetPathContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterUse(EntireSqlParser.UseContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterDescribeInput(EntireSqlParser.DescribeInputContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterExecute(EntireSqlParser.ExecuteContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterDeallocate(EntireSqlParser.DeallocateContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterPrepare(EntireSqlParser.PrepareContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterStartTransaction(EntireSqlParser.StartTransactionContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterResetSession(EntireSqlParser.ResetSessionContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void exitResetSession(EntireSqlParser.ResetSessionContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowPartition(EntireSqlParser.ShowPartitionContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowSession(EntireSqlParser.ShowSessionContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowFunctions(EntireSqlParser.ShowFunctionsContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowColumns(EntireSqlParser.ShowColumnsContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowStatsForQuery(EntireSqlParser.ShowStatsForQueryContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowStats(EntireSqlParser.ShowStatsContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowCatalogs(EntireSqlParser.ShowCatalogsContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowIndex(EntireSqlParser.ShowIndexContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowSchemas(EntireSqlParser.ShowSchemasContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowTables(EntireSqlParser.ShowTablesContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowCreateView(EntireSqlParser.ShowCreateViewContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowCreateTable(EntireSqlParser.ShowCreateTableContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override public void enterExplain(EntireSqlParser.ExplainContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterShowGrants(EntireSqlParser.ShowGrantsContext ctx) {
        addTableNameAndOptList("",TableOpt.OPTIMIZE);
    }

    @Override
    public void enterHiveInsertIntoSelect(EntireSqlParser.HiveInsertIntoSelectContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.INSERT);
    }

    @Override
    public void enterDelete(EntireSqlParser.DeleteContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.DELETE);
    }

    @Override
    public void enterRenameTable(EntireSqlParser.RenameTableContext ctx) {
        addTableNameAndOptList(ctx.from.getText(),TableOpt.ALTER_RENAME_FROMTABLE);
        addTableNameAndOptList(ctx.to.getText(),TableOpt.ALTER_RENAME_TOTABLE);
    }

    @Override
    public void enterModifyTable(EntireSqlParser.ModifyTableContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_COLUMNTYPE);
    }

    @Override
    public void enterChangeTable(EntireSqlParser.ChangeTableContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    @Override
    public void enterAlterColumn(EntireSqlParser.AlterColumnContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    @Override
    public void enterRenameColumn(EntireSqlParser.RenameColumnContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_RENAME_COLUMN);
    }

    @Override
    public void enterDropColumn(EntireSqlParser.DropColumnContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_DROP_COLUMN);
    }

    @Override
    public void enterAddColumn(EntireSqlParser.AddColumnContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_ADD_COLUMN);
    }

    @Override
    public void enterCreateView(EntireSqlParser.CreateViewContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(), TableOpt.CREATE_VIEW);
    }

    @Override
    public void enterDropView(EntireSqlParser.DropViewContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.DROP_VIEW);
    }

    @Override
    public void enterCall(EntireSqlParser.CallContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.CALL);
    }

    @Override
    public void enterGrant(EntireSqlParser.GrantContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.GRANT);
    }

    @Override
    public void enterRevoke(EntireSqlParser.RevokeContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.REVOKE);
    }

    @Override
    public void enterCreateIndex(EntireSqlParser.CreateIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.CREATE_INDEX);
    }

    @Override
    public void enterAddIndex(EntireSqlParser.AddIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.CREATE_INDEX);
    }

    @Override
    public void enterDropIndex(EntireSqlParser.DropIndexContext ctx) {
        if (ctx.tableName == null) {
            addTableNameAndOptList(ctx.indexName.getText(), TableOpt.DROP_INDEX);
        } else {
            addTableNameAndOptList(ctx.tableName.getText(), TableOpt.DROP_INDEX);
        }
    }

    @Override
    public void enterAlterDropIndex(EntireSqlParser.AlterDropIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(), TableOpt.DROP_INDEX);
    }

    @Override
    public void enterOracleRangePartitionItems(EntireSqlParser.OracleRangePartitionItemsContext ctx) {
        if(curCreateTableName == null){
            return;
        }
        TableAndPartitionInfo tableAndPartitionInfo = new TableAndPartitionInfo();
        tableAndPartitionInfo.tablename = curCreateTableName;

        EntireSqlParser.ColumnAliasesContext columnAliasesContext = ctx.columnAliases();
        List<EntireSqlParser.IdentifierContext> identifierList = columnAliasesContext.identifier();
        for(EntireSqlParser.IdentifierContext identifier:identifierList){
            ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
            columnNameAndType.columnName = identifier.getText();

            tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
        }

        if(tableAndPartitionInfo.partitionInfos.size() != 0){
            hiveTablePartitionInfoList.add(tableAndPartitionInfo);
        }
    }

    @Override
    public void enterOracleListPartitionItems(EntireSqlParser.OracleListPartitionItemsContext ctx) {
        if(curCreateTableName == null){
            return;
        }
        TableAndPartitionInfo tableAndPartitionInfo = new TableAndPartitionInfo();
        tableAndPartitionInfo.tablename = curCreateTableName;

        EntireSqlParser.ColumnAliasesContext columnAliasesContext = ctx.columnAliases();
        List<EntireSqlParser.IdentifierContext> identifierList = columnAliasesContext.identifier();
        for(EntireSqlParser.IdentifierContext identifier:identifierList){
            ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
            columnNameAndType.columnName = identifier.getText();

            tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
        }

        if(tableAndPartitionInfo.partitionInfos.size() != 0){
            hiveTablePartitionInfoList.add(tableAndPartitionInfo);
        }
    }

    @Override
    public void enterOracleHashPartitionItems(EntireSqlParser.OracleHashPartitionItemsContext ctx) {
        if(curCreateTableName == null){
            return;
        }
        TableAndPartitionInfo tableAndPartitionInfo = new TableAndPartitionInfo();
        tableAndPartitionInfo.tablename = curCreateTableName;

        EntireSqlParser.ColumnAliasesContext columnAliasesContext = ctx.columnAliases();
        List<EntireSqlParser.IdentifierContext> identifierList = columnAliasesContext.identifier();
        for(EntireSqlParser.IdentifierContext identifier:identifierList){
            ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
            columnNameAndType.columnName = identifier.getText();

            tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
        }

        if(tableAndPartitionInfo.partitionInfos.size() != 0){
            hiveTablePartitionInfoList.add(tableAndPartitionInfo);
        }
    }

    @Override
    public void enterOracleCompositePartitionItems(EntireSqlParser.OracleCompositePartitionItemsContext ctx) {
        if(curCreateTableName == null){
            return;
        }
        TableAndPartitionInfo tableAndPartitionInfo = new TableAndPartitionInfo();
        tableAndPartitionInfo.tablename = curCreateTableName;
        List<EntireSqlParser.ColumnAliasesContext> columnAliasesContextsList = ctx.columnAliases();
        for(EntireSqlParser.ColumnAliasesContext columnAliasesContext:columnAliasesContextsList){
            List<EntireSqlParser.IdentifierContext> identifierList = columnAliasesContext.identifier();
            for(EntireSqlParser.IdentifierContext identifier:identifierList){
                ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
                columnNameAndType.columnName = identifier.getText();

                tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
            }
        }


        if(tableAndPartitionInfo.partitionInfos.size() != 0){
            hiveTablePartitionInfoList.add(tableAndPartitionInfo);
        }
    }


    //HiveSql.g4规则中内容//////////////////////////////////////////////////////////////////
    @Override
    public void enterHiveMultiInsert(EntireSqlParser.HiveMultiInsertContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.SELECT);
    }

    @Override
    public void enterWriteDataMulti(EntireSqlParser.WriteDataMultiContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.SELECT);
    }

    @Override
    public void enterHiveCreateDatabase(EntireSqlParser.HiveCreateDatabaseContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.CREATE_DATABASE);
    }

    @Override
    public void enterHiveDropDatabase(EntireSqlParser.HiveDropDatabaseContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.DROP_DATABASE);
    }

    @Override
    public void enterHiveAlterDatabase(EntireSqlParser.HiveAlterDatabaseContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.ALTER_MODIFY_DATABASE);
    }

    @Override
    public void enterHiveDropTable(EntireSqlParser.HiveDropTableContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.DROP_TABLE);
    }

    @Override
    public void enterHiveTruncateTable(EntireSqlParser.HiveTruncateTableContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.TRUNCATE);
    }

    @Override
    public void enterHiveCreateTable(EntireSqlParser.HiveCreateTableContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.tablename.getText().toUpperCase());
            return;
        }
        TableAndPartitionInfo tableAndPartitionInfo = new TableAndPartitionInfo();
        tableAndPartitionInfo.tablename = ctx.tablename.getText();
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.CREATE_TABLE);
        EntireSqlParser.TableElementsContext partionInfo = ctx.partionInfo;
        if(partionInfo != null){
            for(EntireSqlParser.TableElementContext tableElement: partionInfo.tableElement()){
                EntireSqlParser.ColumnDefinitionContext columnDefinitionContext = tableElement.columnDefinition();
                if(columnDefinitionContext == null){
                    continue;
                }

                ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
                columnNameAndType.columnName = columnDefinitionContext.columnName.getText();
                columnNameAndType.columnType = columnDefinitionContext.type().getText();

                tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
            }
        }
        if(tableAndPartitionInfo.partitionInfos.size() != 0){
            hiveTablePartitionInfoList.add(tableAndPartitionInfo);
        }

    }

    @Override
    public void enterHiveCreateTableByLike(EntireSqlParser.HiveCreateTableByLikeContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.tablename.getText().toUpperCase());
            return;
        }
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.CREATE_TABLE);
    }

    @Override
    public void enterHiveAlterTableProperties(EntireSqlParser.HiveAlterTablePropertiesContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_TBLPROPERTIES);
    }

    @Override
    public void enterHiveAlterTableSerDe(EntireSqlParser.HiveAlterTableSerDeContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_TBLPROPERTIES);
    }

    @Override
    public void enterHiveAlterTableStorageProperties(EntireSqlParser.HiveAlterTableStoragePropertiesContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_TBLPROPERTIES);
    }

    @Override
    public void enterHiveAlterTableSkewed(EntireSqlParser.HiveAlterTableSkewedContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_TBLPROPERTIES);
    }

    @Override
    public void enterHiveAlterTableConstraints(EntireSqlParser.HiveAlterTableConstraintsContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_TBLCONSTRAINT);
    }

    @Override
    public void enterHiveAlterPartition(EntireSqlParser.HiveAlterPartitionContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_PARTITION);
    }

    @Override
    public void enterArchivePartition(EntireSqlParser.ArchivePartitionContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_ARCHIVE_PARTITION);
    }

    @Override
    public void enterHiveAlterColumn(EntireSqlParser.HiveAlterColumnContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    @Override
    public void enterHiveCreateView(EntireSqlParser.HiveCreateViewContext ctx) {
        addTableNameAndOptList(ctx.viewName.getText(),TableOpt.CREATE_VIEW);
    }

    @Override
    public void enterHiveDropView(EntireSqlParser.HiveDropViewContext ctx) {
        addTableNameAndOptList(ctx.viewName.getText(),TableOpt.DROP_VIEW);
    }

    @Override
    public void enterHiveAlterView(EntireSqlParser.HiveAlterViewContext ctx) {
        addTableNameAndOptList(ctx.viewName.getText(),TableOpt.ALTER_SET_TBLPROPERTIES);
    }

    @Override
    public void enterHiveAlterViewAsSelect(EntireSqlParser.HiveAlterViewAsSelectContext ctx) {
        addTableNameAndOptList(ctx.viewName.getText(),TableOpt.ALTER_MODIFY_VIEW);
    }

    @Override
    public void enterHiveCreateIndex(EntireSqlParser.HiveCreateIndexContext ctx) {
        addTableNameAndOptList(ctx.baseTableName.getText(),TableOpt.CREATE_INDEX);
    }

    @Override
    public void enterHiveDropIndex(EntireSqlParser.HiveDropIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.DROP_INDEX);
    }

    @Override public void enterHiveAlterIndex(EntireSqlParser.HiveAlterIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_REBUILD_INDEX);
    }

    @Override
    public void enterHiveCreateTemporaryFunctions(EntireSqlParser.HiveCreateTemporaryFunctionsContext ctx) {
        addTableNameAndOptList(ctx.functionName.getText(),TableOpt.CREATE_FUNCTION);
    }

    @Override
    public void enterHiveCreatePermanentFunctions(EntireSqlParser.HiveCreatePermanentFunctionsContext ctx) {
        addTableNameAndOptList(ctx.functionName.getText(),TableOpt.CREATE_FUNCTION);
    }

    @Override
    public void enterHiveDropFunctions(EntireSqlParser.HiveDropFunctionsContext ctx) {
        addTableNameAndOptList(ctx.functionName.getText(),TableOpt.DROP_FUNCTION);
    }

    @Override
    public void enterHiveReloadFunctions(EntireSqlParser.HiveReloadFunctionsContext ctx) {
        addTableNameAndOptList("",TableOpt.RELOAD_FUNCTION);
    }

    @Override
    public void enterHiveLoadFileIntoTable(EntireSqlParser.HiveLoadFileIntoTableContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.LOAD_DATA);
    }

    //gpSql.g4规则中内容//////////////////////////////////////////////////////////////////
    @Override
    public void enterGpVacuum(EntireSqlParser.GpVacuumContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.SELECT);
    }

    @Override
    public void enterGpTruncate(EntireSqlParser.GpTruncateContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.TRUNCATE);
    }

    @Override
    public void enterGpCreateTable(EntireSqlParser.GpCreateTableContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.tablename.getText().toUpperCase());
            return;
        }
        TableAndPartitionInfo tableAndPartitionInfo = new TableAndPartitionInfo();
        tableAndPartitionInfo.tablename = ctx.tablename.getText();
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.CREATE_TABLE);
        EntireSqlParser.IdentifierContext mainPartition = ctx.mainPartition;
        EntireSqlParser.IdentifierContext subPartition = ctx.subPartition;
        if(mainPartition !=null){
            ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
            columnNameAndType.columnName = mainPartition.getText();

            tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
        }
        if(subPartition !=null){
            ColumnNameAndType columnNameAndType =  new ColumnNameAndType();
            columnNameAndType.columnName = subPartition.getText();

            tableAndPartitionInfo.partitionInfos.add(columnNameAndType);
        }

        if(tableAndPartitionInfo.partitionInfos.size() != 0){
            hiveTablePartitionInfoList.add(tableAndPartitionInfo);
        }
    }

    @Override
    public void enterGpCreateIndex(EntireSqlParser.GpCreateIndexContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.CREATE_INDEX);
    }

    @Override
    public void enterGpAlterPartition(EntireSqlParser.GpAlterPartitionContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_SET_PARTITION);
    }

    @Override
    public void exitGpAlterColumn(EntireSqlParser.GpAlterColumnContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    @Override
    public void enterGpAlterConstraint(EntireSqlParser.GpAlterConstraintContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    //gbaseSql.g4规则中内容//////////////////////////////////////////////////////////////////
    @Override
    public void enterGBaseCreateTableAs(EntireSqlParser.GBaseCreateTableAsContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.tableName.getText().toUpperCase());
            return;
        }
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.CREATE_TABLE);
    }

    @Override
    public void enterGBaseCreateTableByLike(EntireSqlParser.GBaseCreateTableByLikeContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.tableNameDst.getText().toUpperCase());
            return;
        }
        addTableNameAndOptList(ctx.tableNameDst.getText(),TableOpt.CREATE_TABLE);
    }

    @Override
    public void enterGBaseDropNocopies(EntireSqlParser.GBaseDropNocopiesContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER);
    }

    @Override
    public void enterGBaseShrinkSpace(EntireSqlParser.GBaseShrinkSpaceContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER);
    }

    @Override
    public void enterGBaseAlterChange(EntireSqlParser.GBaseAlterChangeContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    @Override
    public void enterGBaseAlterModify(EntireSqlParser.GBaseAlterModifyContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_COLUMN);
    }

    @Override
    public void enterGBaseRenameTable(EntireSqlParser.GBaseRenameTableContext ctx) {
        addTableNameAndOptList(ctx.oldTableName.getText(),TableOpt.ALTER_RENAME_FROMTABLE);
    }

    @Override
    public void enterGBaseAlterView(EntireSqlParser.GBaseAlterViewContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER_MODIFY_VIEW);
    }

    @Override
    public void enterGBaseCreateIndex(EntireSqlParser.GBaseCreateIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.CREATE_INDEX);
    }

    @Override
    public void enterGBaseAlterIndex(EntireSqlParser.GBaseAlterIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.CREATE_INDEX);
    }

    @Override
    public void enterGBaseDropIndex(EntireSqlParser.GBaseDropIndexContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.DROP_INDEX);
    }

    @Override
    public void enterGBaseAlterAutoextend(EntireSqlParser.GBaseAlterAutoextendContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER);
    }

    @Override
    public void enterGBaseAlterColCompress(EntireSqlParser.GBaseAlterColCompressContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER);
    }

    @Override
    public void enterGBaseAlterTableCompress(EntireSqlParser.GBaseAlterTableCompressContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.ALTER);
    }

    @Override
    public void enterGBaseMerge(EntireSqlParser.GBaseMergeContext ctx) {
        addTableNameAndOptList(ctx.tableName.getText(),TableOpt.UPDATE);
        addTableNameAndOptList(ctx.tableReference.getText(),TableOpt.SELECT);
    }

    @Override
    public void enterGbaseLoadData(EntireSqlParser.GbaseLoadDataContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.LOAD_DATA);
    }

    //db2Sql.g4规则中内容//////////////////////////////////////////////////////////////////
    @Override
    public void enterDb2AlterModify(EntireSqlParser.Db2AlterModifyContext ctx) {
        addTableNameAndOptList(ctx.tablename.getText(),TableOpt.ALTER);
    }

    @Override
    public void enterDb2TruncateTable(EntireSqlParser.Db2TruncateTableContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.TRUNCATE);
    }

    @Override
    public void enterDb2CreateTableByLike(EntireSqlParser.Db2CreateTableByLikeContext ctx) {
        if(ctx.TEMPORARY() != null){ //如果是临时表忽略掉
            temporaryTable.add(ctx.tableNameDst.getText().toUpperCase());
            return;
        }
        addTableNameAndOptList(ctx.tableNameDst.getText(),TableOpt.CREATE_TABLE);
    }

    @Override
    public void enterDb2RefreshTable(EntireSqlParser.Db2RefreshTableContext ctx) {
        addTableNameAndOptList(ctx.qualifiedName().getText(),TableOpt.UPDATE);
    }

    @Override
    public void enterDb2CreateView(EntireSqlParser.Db2CreateViewContext ctx) {
        addTableNameAndOptList(ctx.viewName.getText(),TableOpt.CREATE_VIEW);
    }

}

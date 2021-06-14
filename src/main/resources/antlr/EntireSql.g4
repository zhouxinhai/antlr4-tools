grammar EntireSql;
import SqlBase;
@parser::members{
    public boolean hiveType = false;
    public boolean oracleType = false;
    public boolean mysqlType = false;
    public boolean exploreType = false;
    public boolean gpType = false;
    public boolean db2Type = false;
    public boolean gbaseType = false;
    public boolean sqlserverType = false;

    public boolean isSqlserverType() {return sqlserverType;}
    public boolean isHiveType() {return hiveType;}
    public boolean isOracleType() {return oracleType;}
    public boolean isMysqlType() {return mysqlType;}
    public boolean isGpType() {return gpType;}
    public boolean isExploreType() {return exploreType;}
    public boolean isDb2Type() {return db2Type;}
    public boolean isGbaseType() {return gbaseType;}
    public void setAllTypeTrue() {hiveType = true; oracleType = true;mysqlType = true;gpType = true;db2Type = true;gbaseType=true;sqlserverType=true;}
}

multiStatement
    : allStatement (';' allStatement)* (';')?  EOF
    ;

singleStatement
    : allStatement EOF
    ;


allStatement
    : statement
    | {isGbaseType()}? gBaseStatement
    | {isHiveType()}? hiveStatement
    | {isGpType()}? gpStatement
    | {isDb2Type()}? db2Statement
    ;

db2Statement
    : ALTER TABLE tablename=qualifiedName
        (
        ADD CONSTRAINT constraintName=identifier PRIMARY KEY columnAliases |
        ADD CONSTRAINT constraintName=identifier FOREIGN KEY columnAliases REFERENCES otherTablename=qualifiedName columnAliases |
        ADD CONSTRAINT constraintName=identifier UNIQUE columnAliases |
        DROP PRIMARY KEY |
        DROP CONSTRAINT constraintName=identifier |
        ALTER colName=identifier SET NOT NULL |
        ALTER COLUMN columnName=identifier SET DEFAULT (string | identifier | number) |
        ALTER COLUMN columnName=identifier SET NOT NULL |
        ALTER constraintName=identifier SET DATA TYPE type |
        ACTIVATE NOT LOGGED INITIALLY WITH EMPTY TABLE |
        DROP colName=identifier
        )                                                       #db2AlterModify
    | TRUNCATE TABLE qualifiedName IMMEDIATE?                   #db2TruncateTable
    | CREATE TEMPORARY? TABLE  (IF NOT EXISTS)? tableNameDst=qualifiedName
        LIKE tableNameSrc=qualifiedName                         #db2CreateTableByLike
    | REFRESH TABLE qualifiedName                               #db2RefreshTable
    | CREATE VIEW (IF NOT EXISTS)? viewName=qualifiedName
          ('(' columnDefinitionNoType (',' columnDefinitionNoType)* ')')?
          (COMMENT string)?
          AS query                                              #db2CreateView
    | CREATE SEQUENCE sequenceName=identifier INCREMENT BY number
        MINVALUE number (MAXVALUE number | NO MAXVALUE)
        START WITH number                                       #db2CreateSequence
    ;

gBaseStatement
    : CREATE TEMPORARY? TABLE (IF NOT EXISTS)? tableName=qualifiedName
        columnAliases? NOCOPIES? REPLICATED? (DISTRIBUTED BY)?
        AS? query                                               #gBaseCreateTableAs
    | CREATE TEMPORARY? TABLE  (IF NOT EXISTS)? tableNameDst=qualifiedName
        LIKE tableNameSrc=qualifiedName                         #gBaseCreateTableByLike
    | ALTER TABLE tableName=qualifiedName DROP NOCOPIES         #gBaseDropNocopies
    | ALTER TABLE tableName=qualifiedName SHRINK SPACE          #gBaseShrinkSpace
    | ALTER TABLE tableName=qualifiedName CHANGE
        colOldName=identifier colNewName=identifier
        columnType=type                                         #gBaseAlterChange
    | ALTER TABLE tableName=qualifiedName MODIFY
        colNewName=identifier columnType=type
        (FIRST | (AFTER columnName=identifier))?                #gBaseAlterModify
    | RENAME TABLE oldTableName=qualifiedName TO
        newTableName=qualifiedName                              #gBaseRenameTable
    | ALTER VIEW tableName=qualifiedName columnAliases?
        AS query                                                #gBaseAlterView
    | CREATE INDEX idx=identifier ON tableName=qualifiedName
        '('  col=identifier ')'
        (KEY_DC_SIZE '=' number)?
        (KEY_BLOCK_SIZE '=' number)?
        USING HASH (LOCAL | GLOBAL)?                            #gBaseCreateIndex
    | ALTER TABLE tableName=qualifiedName ADD INDEX
        idx=identifier '('  col=identifier ')'
        (KEY_DC_SIZE '=' number)?
        (KEY_BLOCK_SIZE '=' number)?
         USING HASH GLOBAL?                                     #gBaseAlterIndex
    | DROP INDEX (IF EXISTS)? indexName=qualifiedName
         ON tableName=qualifiedName                             #gBaseDropIndex
    | ALTER TABLE tableName=qualifiedName
        AUTOEXTEND ((ON NEXT identifier)| OFF)                  #gBaseAlterAutoextend
    | ALTER TABLE tableName=qualifiedName
        ALTER COLUMN? col=identifier
        COMPRESS '(' INTEGER_VALUE  ')'                         #gBaseAlterColCompress
    | ALTER TABLE tableName=qualifiedName
        ALTER COMPRESS '(' INTEGER_VALUE ',' INTEGER_VALUE ')'  #gBaseAlterTableCompress
    | MERGE INTO? tableName=qualifiedName (tableNameAlias=identifier)?
       USING tableReference=qualifiedName (tableReferenceAlias=identifier)?
       ON booleanExpression
       (
           WHEN MATCHED THEN UPDATE SET
           primaryExpression EQ valueExpression
           (',' primaryExpression EQ valueExpression)*
       )?
       (
           WHEN NOT MATCHED THEN INSERT
           '(' (identifier '.')? identifier (',' (identifier '.')? identifier)* ')'
            insertValues
       )?                                                       #gBaseMerge
     |  query INTO OUTFILE fileName=string
           outfileOption*                             #gBaseSelectIntoOutfile
     | LOAD DATA INFILE fileList=string
           REPLACE? INTO TABLE tablename=qualifiedName
           gbaseLoadDataOptions*                                            #gbaseLoadData
    ;

gbaseLoadDataOptions
     : CHARACTER SET (GBK|UTF8)
     | DATA_FORMAT number (HAVING LINES SEPARATOR)?
     | NULL_VALUE string
     | FIELDS loadDataFiledsOptions+
     | LINES TERMINATED BY string
     ;

loadDataFiledsOptions
     : TERMINATED BY string
     | ENCLOSED BY string
     | PRESERVE BLANKS
     | AUTOFILL
     | LENGTH string
     | TABLE_FIELDS string
     | LINES (TERMINATED BY string)?
     | MAX_BAD_RECORDS number
     | DATETIME FORMAT format=string
     | DATE FORMAT format=string
     | TIMESTAMP FORMAT format=string
     | TIME FORMAT format=string
     | TRACE number
     | TRACE_PATH string
     | NOSPLIT
     | PARALLEL number
     | MAX_DATA_PROCESSORS number
     | MIN_CHUNK_SIZE number
     | SKIP_BAD_FILE number
     | SET  col=identifier '=' val=primaryExpression (',' col=identifier '=' val=primaryExpression)*
     | IGNORE NUM LINES
     | FILE_FORMAT format=string
     ;


outfileOption
    : fieldsOutputOptions
    | lineOutputOptions
    | otherOutputOptions
    ;


fieldsOutputOptions
    : (FIELDS|COLUMNS)
        fieldsOutputOption+
    ;
fieldsOutputOption
    : TERMINATED BY string
    | OPTIONALLY? ENCLOSED BY string
    | ESCAPED BY string
    | OPTIONALLY? DOUBLE_ENCLOSED BY string
    | LENGTH string
    | BLOBMODE identifier
    ;

lineOutputOptions
    : LINES
        lineOutputOption+
    ;

lineOutputOption
    : TERMINATED BY string
    | STARTING BY string
    ;

otherOutputOptions
    : NULL_VALUE string
    | OUTFILEMODE BY (LOCAL | HDFS)
    | WRITEMODE BY (NORMAL | OVERWRITES)
    | FILECOUNT number
    | FILESIZE number
    | CHARACTER SET (GBK | UTF8)
    | WITH HEAD
    ;


hiveStatement
    : FROM tableName=qualifiedName (insertStm)+                 #hiveMultiInsert
    | hiveWriteDataToFileStm                                    #hiveWriteDataToFile
    | SET qualifiedName EQ primaryExpression                           #hiveSetProperties
    | CREATE (DATABASE|SCHEMA) (IF NOT EXISTS)? databaseName=qualifiedName
        (COMMENT databaseComment=string)?
        (LOCATION hdfsPath=stringExp)?
        (WITH DBPROPERTIES multiKeyEvaluation)?                 #hiveCreateDatabase
    | DROP (DATABASE|SCHEMA) (IF EXISTS)? databaseName=qualifiedName
        (RESTRICT|CASCADE)?                                     #hiveDropDatabase
    | ALTER (DATABASE|SCHEMA) databaseName=qualifiedName SET
        (
        DBPROPERTIES multiKeyEvaluation |
        OWNER (USER|ROLE)? userOrRole=identifier |
        LOCATION hdfsPath=stringExp
        )                                                  #hiveAlterDatabase
    | DROP TABLE (IF EXISTS)? tableName=qualifiedName PURGE?              #hiveDropTable
    | TRUNCATE TABLE tableName=qualifiedName (PARTITION partitionSpec)?   #hiveTruncateTable
    | CREATE TEMPORARY? EXTERNAL? TABLE (IF NOT EXISTS)?
        tablename=qualifiedName
        ('(' tableElements constraintSpecification ')')?
        (COMMENT tableComment=string)?
      (PARTITIONED BY '(' partionInfo=tableElements ')')?
      (CLUSTERED BY columnAliases (SORTED BY '(' sortItem (',' sortItem)* ')' (ASC|DESC)?)? INTO numBuckets=INTEGER_VALUE BUCKETS)?
      (SKEWED BY columnAliases ON primaryExpression (STORED AS dir=string)?)?
      (
        (ROW FORMAT rowFormat)?(STORED AS fileFormat)?|
        STORED BY storageClassName=stringExp (WITH SERDEPROPERTIES multiStrEvaluation)?

      )?
      (LOCATION hdfsPath=stringExp)?
      (TBLPROPERTIES multiStrEvaluation)?
      (AS query)?                                                #hiveCreateTable
    | CREATE TEMPORARY? EXTERNAL? TABLE (IF NOT EXISTS)?
        tablename=qualifiedName
        LIKE existingTableOrViewName=qualifiedName
        (LOCATION hdfsPath=stringExp)?                          #hiveCreateTableByLike
    | ALTER TABLE tablename=qualifiedName SET
        TBLPROPERTIES multiStrEvaluation                           #hiveAlterTableProperties
    | ALTER TABLE tablename=qualifiedName (PARTITION partitionSpec )?
        (
            SET SERDEPROPERTIES multiStrEvaluation |
            SET SERDE serdeClassName=stringExp (WITH SERDEPROPERTIES multiStrEvaluation)?
        )                                                          #hiveAlterTableSerDe
    | ALTER TABLE tablename=qualifiedName
        (
        CLUSTERED BY columnAliases (SORTED BY '(' sortItem (',' sortItem)* ')' (ASC|DESC)? )? INTO numBuckets=INTEGER_VALUE BUCKETS |
        NOT STORED AS dir=string
        )                                                        #hiveAlterTableStorageProperties
    | ALTER TABLE tablename=qualifiedName
        (
        SKEWED BY columnAliases ON columnAliases (STORED AS dir=string)? |
        NOT SKEWED |
        SET SKEWED LOCATION multiKeyEvaluation
        )                                                         #hiveAlterTableSkewed
    | ALTER TABLE tablename=qualifiedName
        (
        ADD CONSTRAINT constraintName=identifier PRIMARY KEY columnAliases DISABLE NOVALIDATE |
        ADD CONSTRAINT constraintName=identifier FOREIGN KEY columnAliases REFERENCES tablename=qualifiedName columnAliases DISABLE NOVALIDATE RELY |
        DROP CONSTRAINT constraintName=identifier
        )                                                          #hiveAlterTableConstraints
    | ALTER TABLE tablename=qualifiedName
        (
        ADD (IF NOT EXISTS)? PARTITION partitionSpec (LOCATION location=stringExp)?
            ((',')? PARTITION partitionSpec (LOCATION location=stringExp)?)* |
        PARTITION partitionSpec RENAME TO PARTITION partitionSpec |
        EXCHANGE PARTITION partitionSpec WITH TABLE table_name=qualifiedName |
        ALTER TABLE partitionSpec RECOVER PARTITIONS |
        DROP (IF EXISTS)? PARTITION partitionSpec ((',')? PARTITION partitionSpec)*
            (IGNORE PROTECTION)? (PURGE)?   |
        (PARTITION partitionSpec)? SET FILEFORMAT fileFormat |
        (PARTITION partitionSpec)? SET LOCATION location=stringExp |
        TOUCH (PARTITION partitionSpec)? |
        (PARTITION partitionSpec)? (ENABLE|DISABLE) (NO_DROP (CASCADE)?|OFFLINE) |
        (PARTITION partitionSpec)?
          COMPACT compactionType=stringExp(AND WAIT)?
          (WITH OVERWRITE TBLPROPERTIES multiStrEvaluation)? |
        (PARTITION partitionSpec)? CONCATENATE |
        (PARTITION partitionSpec)? UPDATE COLUMNS
        )                                                                   #hiveAlterPartition
    | ALTER TABLE tableName=qualifiedName (ARCHIVE|UNARCHIVE) PARTITION partitionSpec #archivePartition
    | ALTER TABLE tableName=qualifiedName (PARTITION partitionSpec)?
        (
        CHANGE (COLUMN)? colOldName=identifier colNewName=identifier columnType=type
        (COMMENT colComment=string)? (FIRST|AFTER columnName=identifier)? (CASCADE|RESTRICT)? |
        (ADD|REPLACE) COLUMNS '(' (colName=identifier dataType=type (COMMENT colComment=string)?) ')' (CASCADE|RESTRICT)?
        )                                                                    #hiveAlterColumn
    | CREATE VIEW (IF NOT EXISTS)? viewName=qualifiedName
        ('(' columnDefinitionNoType (',' columnDefinitionNoType)* ')')?
        (COMMENT string)?
        (TBLPROPERTIES multiKeyEvaluation)?
        AS query                                                        #hiveCreateView
    | DROP VIEW (IF EXISTS)? viewName=qualifiedName                     #hiveDropView
    | ALTER VIEW viewName=qualifiedName
        SET TBLPROPERTIES multiKeyEvaluation                            #hiveAlterView
    | ALTER VIEW viewName=qualifiedName AS query                        #hiveAlterViewAsSelect
    | CREATE INDEX indexName=qualifiedName
        ON TABLE baseTableName=qualifiedName columnAliases?TBLPROPERTIES
        AS indexType=stringExp
        (WITH DEFERRED REBUILD)?
        (IDXPROPERTIES multiKeyEvaluation)?
        (IN TABLE indexTableName=qualifiedName)?
        (PARTITION partitionSpec)?
        (
            (ROW FORMAT rowFormat)?(STORED AS fileFormat)?|
            STORED BY storageClassName=stringExp (WITH SERDEPROPERTIES multiStrEvaluation)?
        )?
        (LOCATION hdfsPath=stringExp)?
        (TBLPROPERTIES multiStrEvaluation)?
        (COMMENT indexComment=string)?                              #hiveCreateIndex
    | DROP INDEX (IF EXISTS)? indexName=qualifiedName
        ON tableName=qualifiedName                                  #hiveDropIndex
    | ALTER INDEX indexName=qualifiedName ON tableName=qualifiedName
        (PARTITION partitionSpec)? REBUILD                          #hiveAlterIndex
    | CREATE TEMPORARY FUNCTION functionName=qualifiedName
        AS className=stringExp                                      #hiveCreateTemporaryFunctions
    | CREATE FUNCTION functionName=qualifiedName AS className=stringExp
        (USING JAR|FILE|ARCHIVE fileUri=stringExp
        (',' JAR|FILE|ARCHIVE fileUri=stringExp)* )?                #hiveCreatePermanentFunctions
    | DROP TEMPORARY? FUNCTION (IF EXISTS)? functionName=qualifiedName             #hiveDropFunctions
    | RELOAD FUNCTION                                               #hiveReloadFunctions
    | LOAD DATA (LOCAL)? INPATH filePath=stringExp (OVERWRITE)?
        INTO TABLE tablename=qualifiedName (PARTITION partitionSpec)?    #hiveLoadFileIntoTable
    ;

gpStatement
    : VACUUM FULL? FREEZE? VERBOSE?  ANALYZE? tablename=qualifiedName columnAliases?    #gpVacuum
    | TRUNCATE  tablename=qualifiedName                         #gpTruncate
    | CREATE WRITABLE? TEMPORARY? EXTERNAL? TABLE (IF NOT EXISTS)?
        tablename=qualifiedName
        (
        (LIKE srcTablename=qualifiedName)? | ('(' tableElements ')')?
        )
        (LOCATION '(' string (',' string)* ')' FORMAT string '(' (primaryExpression string?)*')' )? (ENCODING string)?
        (LOG ERRORS INTO errTablename=qualifiedName)?
        (SEGMENT REJECT LIMIT number PERCENT)?
        (TABLESPACE data_ts=identifier)?
        (ON COMMIT DELETE ROWS)?
        (WITH properties)?
        (DISTRIBUTED BY '(' rec_id=identifier ')')?
        (PARTITION BY (RANGE|LIST) '(' mainPartition=identifier ')')?
        (SUBPARTITION  BY (RANGE|LIST) '(' subPartition=identifier ')')?
        (SUBPARTITION TEMPLATE gpSubpartitionItems)?
        (gpPartitionItems)?
        (AS query)?                                             #gpCreateTable
    | CREATE UNIQUE? INDEX CONCURRENTLY? indexName=qualifiedName ON TABLE? tablename=qualifiedName
        (USING method=identifier)?
        '(' primaryExpression ')'
        (WITH properties)?
        (TABLESPACE data_ts=identifier)?                        #gpCreateIndex
    | REINDEX indexName=qualifiedName                           #gpRecreateIndex
    | ALTER TABLE tablename=qualifiedName
        (
        ADD PARTITION partitionName=identifier START '(' typeConstructor ')' END '(' typeConstructor ')' |
        ADD DEFAULT PARTITION partitionName=identifier |
        RENAME PARTITION FOR (primaryExpression) TO partitionName=identifier |
        SPLIT PARTITION FOR (primaryExpression) AT (primaryExpression) INTO '('PARTITION partitionName=identifier (',' PARTITION partitionName=identifier)* ')' |
        SPLIT DEFAULT PARTITION START '(' typeConstructor ')' END '(' typeConstructor ')' INTO '(' PARTITION partitionName=identifier ',' DEFAULT PARTITION ')' |
        DROP PARTITION FOR (primaryExpression) |
        DROP DEFAULT PARTITION |
        TRUNCATE PARTITION FOR (primaryExpression) |
        EXCHANGE PARTITION FOR (primaryExpression) WITH TABLE otherTableName=qualifiedName
        )                                                       #gpAlterPartition
    | ALTER TABLE tablename=qualifiedName
        (
        ADD COLUMN columnDefinition |
        ALTER COLUMN columnName=identifier TYPE type |
        ALTER COLUMN columnName=identifier SET DEFAULT (string | identifier | number) |
        RENAME COLUMN columnName=identifier TO newColumnName=identifier |
        DROP COLUMN columnName=identifier
        )                                                       #gpAlterColumn
    | ALTER TABLE tablename=qualifiedName
        (
        ADD CONSTRAINT ckId=identifier CHECK(booleanExpression) |
        DROP CONSTRAINT ckId=identifier |
        ADD CONSTRAINT constraintName=identifier FOREIGN KEY columnAliases REFERENCES otherTablename=qualifiedName columnAliases
        )                                                       #gpAlterConstraint
    ;

gpPartitionItems
    : '(' gpPartitionItem (',' gpPartitionItem)* ')'
    ;

gpPartitionItem
    : START '(' typeConstructor ')' END '(' typeConstructor ')' EVERY '(' typeConstructor ')'
    | PARTITION identifier VALUES(primaryExpression)
    | PARTITION identifier START '(' typeConstructor ')' (INCLUSIVE|EXCLUSIVE)? (END '(' typeConstructor ')' (INCLUSIVE|EXCLUSIVE)?)?
    | DEFAULT PARTITION identifier
    ;

gpSubpartitionItems
    : '(' gpSubPartitionItem (',' gpSubPartitionItem)* ')'
    ;

gpSubPartitionItem
    : START '(' typeConstructor ')' END '(' typeConstructor ')' EVERY '(' typeConstructor ')'
    | SUBPARTITION identifier VALUES(primaryExpression)
    | SUBPARTITION identifier START '(' typeConstructor ')' (INCLUSIVE|EXCLUSIVE)? (END '(' typeConstructor ')' (INCLUSIVE|EXCLUSIVE)?)?
    | DEFAULT SUBPARTITION identifier
    ;

hiveWriteDataToFileStm
    : INSERT OVERWRITE (LOCAL)? DIRECTORY dir=string
        (ROW FORMAT rowFormat)?(STORED AS fileFormat)?
        query                                                       #writeDataSingle
    | FROM tableName=qualifiedName
      (INSERT OVERWRITE (LOCAL)? DIRECTORY dir=string query)+       #writeDataMulti
    ;

constraintSpecification
    : (',' PRIMARY KEY '('identifier (',' identifier)* ')' DISABLE NOVALIDATE)?
      (',' CONSTRAINT constraintName=identifier
           FOREIGN KEY '('identifier (',' identifier)* ')'
           REFERENCES tablename=qualifiedName'('identifier (',' identifier)* ')' DISABLE NOVALIDATE)?
    ;

fileFormat
    : SEQUENCEFILE
    | TEXTFILE
    | RCFILE
    | ORC
    | ORCFILE
    | PARQUET
    | AVRO
    | JSONFILE
    | INPUTFORMAT input_format_classname=stringExp OUTPUTFORMAT output_format_classname=stringExp
    ;

rowFormat
    : DELIMITED (FIELDS TERMINATED BY stringExp (ESCAPED BY stringExp)?)?  (COLLECTION ITEMS TERMINATED BY stringExp)?
        (MAP KEYS TERMINATED BY stringExp)? (LINES TERMINATED BY stringExp)? (nullExp DEFINED AS stringExp)?
    | SERDE serdeName=stringExp (WITH SERDEPROPERTIES multiStrEvaluation )?
    ;

stringExp
    : QUOTED_IDENTIFIER
    | string
    ;
stringAndNumberExp
    : QUOTED_IDENTIFIER
    | string
    | number
    ;

multiStrEvaluation
    :'(' key=stringExp EQ val=stringExp (',' key=stringExp EQ val=stringExp)*')'
    ;

multiKeyEvaluation
    :'(' key=identifier EQ val=stringAndNumberExp (',' key=identifier EQ val=stringAndNumberExp)*')'
    ;


columnDefinitionNoType
    : identifier (COMMENT string)?
    ;

nullExp
    : NULL
    ;






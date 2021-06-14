grammar SqlBase;

tokens {
    DELIMITER
}

multiStatement
    : statementNoEof (';' statementNoEof)* (';')?  EOF
    ;

singleStatement
    : statementNoEof EOF
    ;

standaloneExpression
    : expression EOF
    ;

standalonePathSpecification
    : pathSpecification EOF
    ;

queryStatement
    : queryNoWith EOF
    ;



statementNoEof
    : statement
    ;


statement
    : query                                                            #statementDefault
    | oracleComment                                                    #setOracleComment
    | UPDATE tableName=qualifiedName (tableAlias=identifier)? SET
        primaryExpression EQ valueExpression
        (',' primaryExpression EQ valueExpression)*
        (WHERE booleanExpression)?                                     #update
    | USE schema=identifier                                            #use
    | USE catalog=identifier '.' schema=identifier                     #use
    | CREATE SCHEMA (IF NOT EXISTS)? qualifiedName
        (WITH properties)?                                             #createSchema
    | DROP SCHEMA (IF EXISTS)? qualifiedName (CASCADE | RESTRICT)?     #dropSchema
    | ALTER SCHEMA qualifiedName RENAME TO identifier                  #renameSchema
    | CREATE TABLE (IF NOT EXISTS)? qualifiedName
         columnAliases?
        (COMMENT string)?
        (WITH properties)? NOLOGGING? AS (query | '('query')')
        (WITH (NO)? DATA)?   (DEFINITION ONLY)?
        db2CreateTableAsSelectItem?                                     #createTableAsSelect
    | CREATE GLOBAL? TEMPORARY? EXTERNAL? TABLE (IF NOT EXISTS)? qualifiedName
        '(' tableElement (',' tableElement)* ')'
        oracleTemporayItem?
        mysqlTemporayItem?
        gbaseTemporayItem?
         (COMMENT '='? string)?
         (WITH properties)?
         oraclePartitionItems?
         db2PartitionItems?                                            #createTable
    | TRUNCATE TABLE qualifiedName                                     #truncateTable
    | DROP TEMPORARY? EXTERNAL? TABLE (IF EXISTS)? qualifiedName                  #dropTable
    | CREATE ( UNIQUE | BITMAP )? INDEX indexName=qualifiedName
        ON tableName=qualifiedName columnAliasesByOrder                #createIndex
    | ALTER TABLE tableName=qualifiedName ADD INDEX
        indexName=qualifiedName columnAliasesByOrder                   #addIndex
    | DROP INDEX indexName=qualifiedName (ON tableName=qualifiedName)?
        FORCE? (CASCADE|RESTRICT)?                                     #dropIndex
    | ALTER TABLE tableName=qualifiedName
        DROP (INDEX|PRIMARY) indexName=qualifiedName                   #alterDropIndex
    | insertStm                                                        #insert
    | DELETE FROM qualifiedName (tableAlias=identifier)?
        (WHERE booleanExpression)?                                     #delete
    | ALTER TABLE from=qualifiedName RENAME TO? to=qualifiedName        #renameTable
    | ALTER TABLE tableName=qualifiedName
        MODIFY column=qualifiedName type (NOT NULL)?
        (DEFAULT (string | identifier | number | NULL))?
        (FIRST | (AFTER columnName=identifier))?                        #modifyTable
    | ALTER TABLE tableName=qualifiedName
         CHANGE columnOld=qualifiedName columnNew=qualifiedName type
         (DEFAULT (string | identifier | number | NULL))?
        (NOT NULL)?                                                     #changeTable
    | {isMysqlType()}? ALTER TABLE tableName=qualifiedName
        ALTER column=qualifiedName
        (
            (SET DEFAULT (string | identifier | number | NULL))
            |(DROP DEFAULT)
        )                                                               #alterColumn
    | ALTER TABLE tableName=qualifiedName
        RENAME COLUMN from=identifier TO to=identifier type?           #renameColumn
    | ALTER TABLE tableName=qualifiedName
        DROP COLUMN? column=qualifiedName
        (',' DROP COLUMN? column=qualifiedName)*                         #dropColumn
    | ALTER TABLE tableName=qualifiedName
        ADD COLUMN?
        ( columnDefinition | ('(' tableElement (',' tableElement)* ')') )
        (FIRST | (AFTER columnName=identifier))?                       #addColumn
    | CREATE (OR REPLACE)? VIEW qualifiedName AS query                 #createView
    | DROP VIEW (IF EXISTS)? qualifiedName                             #dropView
    | CALL qualifiedName '(' (callArgument (',' callArgument)*)? ')'   #call
    | GRANT
        (privilege (',' privilege)* | ALL PRIVILEGES)
        ON TABLE? qualifiedName TO grantee=identifier
        (WITH GRANT OPTION)?                                           #grant
    | REVOKE
        (GRANT OPTION FOR)?
        (privilege (',' privilege)* | ALL PRIVILEGES)
        ON TABLE? qualifiedName FROM grantee=identifier                #revoke
    | SHOW GRANTS
        (ON TABLE? qualifiedName)?                                     #showGrants
    | EXPLAIN ANALYZE? VERBOSE?
        ('(' explainOption (',' explainOption)* ')')? statement        #explain
    | SHOW CREATE TABLE qualifiedName                                  #showCreateTable
    | SHOW CREATE VIEW qualifiedName                                   #showCreateView
    | SHOW TABLES ((FROM | IN) qualifiedName)?
        (LIKE pattern=string (ESCAPE escape=string)?)?                 #showTables
    | SHOW SCHEMAS ((FROM | IN) identifier)?
        (LIKE pattern=string (ESCAPE escape=string)?)?                 #showSchemas
    | SHOW INDEX ((FROM | IN) identifier)?
            (LIKE pattern=string (ESCAPE escape=string)?)?             #showIndex
    | SHOW CATALOGS (LIKE pattern=string)?                             #showCatalogs
    | SHOW COLUMNS (FROM | IN) qualifiedName                           #showColumns
    | SHOW STATS (FOR | ON) qualifiedName                              #showStats
    | SHOW STATS FOR '(' querySpecification ')'                        #showStatsForQuery
    | DESCRIBE qualifiedName                                           #showColumns
    | DESC qualifiedName                                               #showColumns
    | SHOW FUNCTIONS                                                   #showFunctions
    | SHOW SESSION                                                     #showSession
    | SHOW PARTITIONS identifier                                       #showPartition
    | SET SESSION qualifiedName EQ expression                          #setSession
    | RESET SESSION qualifiedName                                      #resetSession
    | START TRANSACTION (transactionMode (',' transactionMode)*)?      #startTransaction
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        COMMIT WORK?                                    #commit
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        ROLLBACK WORK?                                  #rollback
    | PREPARE identifier FROM statement                                #prepare
    | DEALLOCATE PREPARE identifier                                    #deallocate
    | EXECUTE identifier (USING expression (',' expression)*)?         #execute
    | DESCRIBE INPUT identifier                                        #describeInput
    | DESCRIBE OUTPUT identifier                                       #describeOutput
    | SET PATH pathSpecification                                       #setPath
    | SET  (identifier_ext '.')* identifier_ext
        EQ primaryExpression                                           #setProperty
    | MSCK REPAIR TABLE tableName=qualifiedName                        #repairTable
    ;

insertStm
    : INSERT INTO TABLE? qualifiedName (tableAlias=identifier)?
         (PARTITION partitionSpec)?
         columnAliases?
         insertValues                                    #insertInto

    | {isHiveType()}? INSERT (INTO TABLE?| OVERWRITE TABLE) qualifiedName
          (PARTITION partitionSpec)?
          columnAliases?
          query                                                          #hiveInsertIntoSelect
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        INSERT INTO TABLE? qualifiedName NOLOGGING?
        columnAliases?
        query                                                          #insertIntoSelect
    ;


query
    :  with? queryNoWith
    ;

with
    : WITH RECURSIVE? namedQuery (',' namedQuery)*
    ;

oracleComment
    : {isOracleType() || isDb2Type() || isGpType()}? COMMENT ON TABLE tableName=qualifiedName IS string #tableComment
    | {isOracleType() || isDb2Type() || isGpType()}? COMMENT ON COLUMN tableName=qualifiedName IS string #columnComment
    ;

oraclePartitionItems
    : {isOracleType() || isMysqlType() || isGbaseType()}? oracleRangePartitionItems
    | {isOracleType() || isMysqlType() || isGbaseType()}? oracleHashPartitionItems
    | {isOracleType() || isMysqlType() || isGbaseType()}? oracleListPartitionItems
    | {isOracleType() || isMysqlType() || isGbaseType()}? oracleCompositePartitionItems
    ;

db2PartitionItems
    : {isDb2Type()}? PARTITION BY RANGE columnAliases '(' STARTING (string | identifier | number) ENDING (string | identifier | number) ')'
    ;

db2CreateTableAsSelectItem
    : {isDb2Type()}? DATA INITIALLY DEFERRED REFRESH DEFERRED
    ;

oracleTemporayItem
    : {isOracleType()}? ON COMMIT (PRESERVE|DELETE) ROWS
    ;

mysqlTemporayItem
    : {isMysqlType()}? TYPE '=' primaryExpression
    | {isMysqlType()}?  (ENGINE '=' primaryExpression)?
                        (AUTO_INCREMENT '=' number)?
                        (DEFAULT CHARSET '=' identifier)?
    ;

gbaseTemporayItem
    : {isGbaseType()}?
        (COMPRESS '(' INTEGER_VALUE ',' INTEGER_VALUE ')')?
        (ENGINE '=' EXPRESS)?
        (REPLICATED |(DISTRIBUTED BY '(' string ')'))?
        (NOCOPIES)?
        (DEFAULT CHARSET '=' identifier)?
        (TABLESPACE '=' string)?
        (AUTOEXTEND ON NEXT identifier)?
    ;

oracleRangePartitionItems
    : PARTITION BY RANGE columnAliases '(' oracleRangePartitionItem (',' oracleRangePartitionItem )*  ')'
    ;

oracleRangePartitionItem
    : PARTITION ptname=identifier VALUES LESS THAN booleanExpression (TABLESPACE tsname=identifier)?
    ;


oracleListPartitionItems
    : PARTITION BY LIST columnAliases '(' oracleListPartitionItem (',' oracleListPartitionItem )*  ')'
    ;

oracleListPartitionItem
    : (PARTITION|SUBPARTITION) ptname=identifier VALUES booleanExpression (TABLESPACE tsname=identifier)?
    ;

oracleHashPartitionItems
    : PARTITION BY HASH columnAliases '(' oracleHashPartitionItem (',' oracleHashPartitionItem )*  ')'
    ;

oracleHashPartitionItem
    : (PARTITION|SUBPARTITION) ptname=identifier (TABLESPACE tsname=identifier)?
    | (PARTITION|SUBPARTITIONS) number (STORE IN columnAliases)?
    ;

oracleCompositePartitionItems
    : PARTITION BY RANGE columnAliases SUBPARTITION BY (HASH|LIST) columnAliases '(' oracleCompositePartitionItem (',' oracleCompositePartitionItem )* ')'
    ;

oracleCompositePartitionItem
    : oracleRangePartitionItem ('(' oracleHashPartitionItem (',' oracleHashPartitionItem )*  ')')?
    | oracleRangePartitionItem ('(' oracleListPartitionItem (',' oracleListPartitionItem )*  ')')?
    | oracleRangePartitionItem SUBPARTITIONS number (STORE IN columnAliases)?
    ;

constraintInfo
    : CONSTRAINT (constraintName=identifier)? PRIMARY KEY columnAliases
    | CONSTRAINT (constraintName=identifier)? UNIQUE columnAliases
    | CONSTRAINT (constraintName=identifier)? FOREIGN KEY columnAliases REFERENCES tableName=qualifiedName columnAliases
    | CONSTRAINT (constraintName=identifier)? CHECK '(' booleanExpression ')'
    ;

mysqlColumnCreateIndex
    : INDEX indexName=qualifiedName columnAliasesByOrder
    ;

gbaseColumnCreateIndex
    : KEY indexName=qualifiedName columnAliasesByOrder USING HASH (LOCAL | GLOBAL)
    ;

tableElement
    : columnDefinition
    | likeClause
    | {isGbaseType()}? gbaseColumnCreateIndex
    | {isOracleType()}? constraintInfo
    | {isMysqlType()}? mysqlColumnCreateIndex
    ;


tableElements
    : tableElement (',' tableElement)*
    ;

columnDefinitions
    : columnConstraint?
    | columnConstraint columnConstraint
    | columnConstraint columnConstraint columnConstraint
    | columnConstraint columnConstraint columnConstraint columnConstraint
    ;

columnDefinition
    : columnName=identifier type   columnDefinitions (DEFAULT (string | identifier | number | NULL | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP))? columnNullable?  autoIncrement? compressType? (COMMENT string)?
    ;

autoIncrement
    : {isMysqlType()}? AUTO_INCREMENT
    ;

compressType
    : {isGbaseType()}? COMPRESS '(' INTEGER_VALUE  ')'
    ;

columnNullable
    : (CONSTRAINT identifier)? NOT? NULL
    ;

columnConstraint
    : (WITH properties)?
    | (CONSTRAINT identifier)? NOT? NULL
    | {isGpType()}? CONSTRAINT pkId=identifier PRIMARY KEY USING INDEX TABLESPACE dataTs=identifier
    | {isGpType()}? CONSTRAINT ckId=identifier CHECK(booleanExpression)
    | {isGpType()}? ENCODING(properties)
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        (CONSTRAINT uqId=identifier)? UNIQUE
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        (CONSTRAINT uqId=identifier)? PRIMARY KEY
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        (CONSTRAINT fkId=identifier)? REFERENCES tableName=qualifiedName columnAliases
    | {isOracleType()||isMysqlType()||isGpType()||isDb2Type()||isGbaseType()}?
        (CONSTRAINT fkId=identifier)? CHECK '(' booleanExpression ')'
    ;

likeClause
    : LIKE qualifiedName (optionType=(INCLUDING | EXCLUDING) PROPERTIES)?
    ;

properties
    : '(' property (',' property)* ')'
    ;

property
    : identifier EQ expression
    ;

queryNoWith:
      queryTerm
      (ORDER BY sortItem (',' sortItem)*)?
      (SORT BY sortItem (',' sortItem)*)?
      (DISTRIBUTED BY sortItem (',' sortItem)*)?
      (LIMIT limitStat)?
      (FETCH FIRST number ROWS ONLY)?
    ;

limitStat
    : rowCount=(INTEGER_VALUE | ALL) (OFFSET INTEGER_VALUE)?
    | offset=INTEGER_VALUE ',' rowCount=INTEGER_VALUE
    ;

queryTerm
    : queryPrimary                                                             #queryTermDefault
    | left=queryTerm operator=(INTERSECT | MINUSES) setQuantifier? right=queryTerm         #setOperation
    | left=queryTerm operator=(UNION | EXCEPT) setQuantifier? right=queryTerm  #setOperation
    ;

queryPrimary
    : querySpecification                   #queryPrimaryDefault
    | TABLE qualifiedName                  #table
    | '(' queryNoWith  ')'                 #subquery
    ;

insertValues
    : VALUES expression (',' expression)*
    ;

sortItem
    : expression ordering=(ASC | DESC)? (NULLS nullOrdering=(FIRST | LAST))?
    ;

querySpecification
    : (SELECT|RMTSELECT) setQuantifier? selectItem (',' selectItem)*
      (FROM relation (',' relation)*)?
      (WHERE where=booleanExpression)?
      (GROUP BY groupBy (WITH ROLLUP)? )?
      (HAVING having=booleanExpression)?
    ;

groupBy
    : setQuantifier? groupingElement (',' groupingElement)*
    ;

groupingElement
    : groupingSet                                            #singleGroupingSet
    | ROLLUP '(' (expression (',' expression)*)? ')'         #rollup
    | CUBE '(' (expression (',' expression)*)? ')'           #cube
    | GROUPING SETS '(' groupingSet (',' groupingSet)* ')'   #multipleGroupingSets
    ;

groupingSet
    : '(' (expression (',' expression)*)? ')'
    | expression
    ;

namedQuery
    : name=identifier (columnAliases)? AS '(' query ')'
    ;

setQuantifier
    : DISTINCT
    | ALL
    ;

selectItem
    : expression (AS? aliasName)?  #selectSingle
    | {isExploreType()}? qualifiedName '.' ASTERISK    #selectAll
    | {isExploreType()}? ASTERISK                      #selectAll
    ;

aliasName
    : identifier
    | string
    ;

relation
    : left=relation
      ( CROSS JOIN right=sampledRelation
      | {isMysqlType()}?joinType JOIN rightRelation=sampledRelation joinCriteria?
      | {!isMysqlType()}?joinType JOIN rightRelation=sampledRelation joinCriteria
      | NATURAL joinType JOIN right=sampledRelation
      )                                           #joinRelation
    | sampledRelation                             #relationDefault
    ;

joinType
    : INNER?
    | LEFT SEMI? OUTER?
    | RIGHT OUTER?
    | FULL OUTER?
    | SEMI
    ;

joinCriteria
    : ON booleanExpression
    | USING '(' identifier (',' identifier)* ')'
    ;

sampledRelation
    : aliasedRelation (
        TABLESAMPLE sampleType '(' percentage=expression ')'
      )?
    ;

sampleType
    : BERNOULLI
    | SYSTEM
    ;

aliasedRelation
    : relationPrimary (AS? identifier columnAliases?)?
    ;

columnAliases
    : '(' identifier (',' identifier)* ')'
    ;

lengthDesc
    : '(' number ')'
    ;

columnAliasesByOrder
    : '(' identifier lengthDesc? (',' identifier lengthDesc?)* (ASC | DESC)? ')'
    ;

relationPrimary
    : qualifiedName (PARTITION '(' identifier ')')?                   #tableName
    | '(' query ')'                                                   #subqueryRelation
    | UNNEST '(' expression (',' expression)* ')' (WITH ORDINALITY)?  #unnest
    | LATERAL '(' query ')'                                           #lateral
    | '(' relation ')'                                                #parenthesizedRelation
    ;

expression
    : booleanExpression
    ;

partitionSpec
    : '(' partitionInfo (',' partitionInfo)*')'
    ;

partitionInfo
    : (aliasname=identifier '=')? partitionname=valueExpression
    ;



booleanExpression
    : valueExpression predicate[$valueExpression.ctx]?             #predicated
    | NOT booleanExpression                                        #logicalNot
    | left=booleanExpression operator=AND right=booleanExpression  #logicalBinary
    | left=booleanExpression operator=OR right=booleanExpression   #logicalBinary
    ;

// workaround for https://github.com/antlr/antlr4/issues/780
predicate[ParserRuleContext value]
    : comparisonOperator right=valueExpression                            #comparison
    | comparisonOperator comparisonQuantifier '(' query ')'               #quantifiedComparison
    | NOT? BETWEEN lower=valueExpression AND upper=valueExpression        #between
    | NOT? IN '(' expression (',' expression)* ')'                        #inList
    | NOT? IN '(' query ')'                                               #inSubquery
    | NOT? LIKE pattern=valueExpression (ESCAPE escape=valueExpression)?  #like
    | NOT? REGEXP pattern=valueExpression                                 #regexp
    | NOT? RLIKE pattern=valueExpression                                  #rlike
    | IS NOT? NULL                                                        #nullPredicate
    | IS NOT? DISTINCT FROM right=valueExpression                         #distinctFrom
    ;

valueExpression
    : primaryExpression                                                                 #valueExpressionDefault
    |{isGpType()}?
        primaryExpression '::' type                                                     #gpTypeConvert
    | {isMysqlType() || isGbaseType()}?
        BINARY primaryExpression                                                        #mySqlTypeConvert
    | valueExpression AT timeZoneSpecifier                                              #atTimeZone
    | operator=(MINUS | PLUS | LOGICALNOT) valueExpression                                           #arithmeticUnary
    | left=valueExpression operator=(ASTERISK | SLASH | MATHPERCENT | MATHMOD) right=valueExpression  #arithmeticBinary
    | left=valueExpression
        operator=(PLUS | MINUS | LOGICALAND | LOGICALOR | LOGICALEXCLUSIVEOR | XOR)
        right=valueExpression                                                           #arithmeticBinary
    | left=valueExpression
            operator=shiftOpt
            right=valueExpression                                                           #arithmeticShift
    | left=valueExpression CONCAT right=valueExpression                                 #concatenation
    ;

shiftOpt
    : LT LT
    | GT GT
    ;


primaryExpression
    : NULL                                                                                #nullLiteral
    | interval                                                                            #intervalLiteral
    //| identifier string                                                                   #typeConstructor1
    //| DOUBLE_PRECISION string                                                             #typeConstructor2
    | number                                                                              #numericLiteral
    | booleanValue                                                                        #booleanLiteral
    |  primaryExpression IS NOT? ( TRUE | FALSE | UNKNOWN )                                  #isBooleanValue
    | string                                                                              #stringLiteral
    | BINARY_LITERAL                                                                      #binaryLiteral
    | '?'                                                                                 #parameter
    | POSITION '(' valueExpression IN valueExpression ')'                                 #position
    | '(' expression (',' expression)+ ')'                                                #rowConstructor
    | ROW '(' expression (',' expression)* ')'                                            #rowConstructor
    | qualifiedName '(' ASTERISK ')' filter? over?                                        #functionCall
    | qualifiedName '(' (setQuantifier? expression (',' expression)*)?
        (ORDER BY sortItem (',' sortItem)*)? ')' filter? over?                            #functionCall
    | LISTAGG '(' expression (',' primaryExpression)? ')' WITHIN GROUP
          '(' ORDER BY expression ')'                                                     #functionCallListAgg
    | {isGpType()}? gpFunctionCalls                                                       #gpFunctionCall
    | {isMysqlType()}? mysqlFunctionCalls                                                  #mysqlFunctionCall
    | {isDb2Type()}?
      CURRENT  (DATE | TIME | TIMESTAMP)                                                  #db2CurrentTimeFunc
    | identifier '->' expression                                                          #lambda
    | '(' (identifier (',' identifier)*)? ')' '->' expression                             #lambda
    | '(' query ')'                                                                       #subqueryExpression
    // This is an extension to ANSI SQL, which considers EXISTS to be a <boolean expression>
    | EXISTS '(' query ')'                                                                #exists
    | CASE valueExpression whenClause+ (ELSE elseExpression=expression)? END              #simpleCase
    | CASE whenClause+ (ELSE elseExpression=expression)? END                              #searchedCase
    | CAST '(' expression AS type ')'                                                     #cast
    | TRY_CAST '(' expression AS type ')'                                                 #cast
    | ARRAY '[' (expression (',' expression)*)? ']'                                       #arrayConstructor
    | value=primaryExpression '[' index=valueExpression ']'                               #subscript
    | identifier                                                                          #columnReference
    | base=primaryExpression '.' fieldName=identifier oracleJoinType?                    #dereference
    | name=CURRENT_DATE                                                                   #specialDateTimeFunction
    | name=CURRENT_TIME ('(' precision=INTEGER_VALUE ')')?                                #specialDateTimeFunction
    | name=CURRENT_TIMESTAMP ('(' precision=INTEGER_VALUE ')')?                           #specialDateTimeFunction
    | name=LOCALTIME ('(' precision=INTEGER_VALUE ')')?                                   #specialDateTimeFunction
    | name=LOCALTIMESTAMP ('(' precision=INTEGER_VALUE ')')?                              #specialDateTimeFunction
    | name=CURRENT_USER                                                                   #currentUser
    | name=CURRENT_PATH                                                                   #currentPath
    | SUBSTRING '(' valueExpression FROM valueExpression (FOR valueExpression)? ')'       #substring
    | NORMALIZE '(' valueExpression (',' normalForm)? ')'                                 #normalize
    | EXTRACT '(' identifier FROM valueExpression ')'                                     #extract
    | '(' expression ')'                                                                  #parenthesizedExpression
    | GROUPING '(' (qualifiedName (',' qualifiedName)*)? ')'                              #groupingOperation
    ;

gpFunctionCalls
    : OVERLAY '(' expression PLACING expression FROM expression FOR expression ')'          #gpOverLayFunction
    | TRIM '(' BOTH expression FROM expression ')'                                          #gpTrimFunction
    | TO_CHAR '(' INTERVAL string ',' string ')'                                            #gpTocharFunction
    | AGE '(' TIMESTAMP string ',' TIMESTAMP string ')'                                     #gpAgeFunction
    | DATE_PART '(' string ',' TIMESTAMP string ')'                                         #gpdatePartFunction
    | EXTRACT '(' expression FROM TIMESTAMP string ')'                                      #gpExtractFunction
    | ISFINITE '(' INTERVAL string ')'                                                      #gpIsfiniteFunction
    | JUSTIFY_DAYS '(' INTERVAL string ')'                                                  #gpJustifyDaysFunction
    | JUSTIFY_HOURS '(' INTERVAL string ')'                                                 #gpJustifyHourFunction
    | JUSTIFY_INTERVAL '(' INTERVAL string ')'                                              #gpJustifyIntervalFunction
    ;

mysqlFunctionCalls
    : (TRIM|RTRIM|LTRIM) '(' (LEADING|BOTH|TRAILING) string FROM string ')'                  #mysqlTrimFunction
    | (DATE_ADD|ADDDATE|DATE_SUB) '(' string ',' INTERVAL (string|number) intervalField ')'  #mysqlDateFunction
    ;

oracleJoinType
    : {isOracleType()}? '(' '+' ')'
    ;

typeConstructor
    : (identifier | DOUBLE_PRECISION | number) string?
    ;

string
    : STRING                                #basicStringLiteral
    | UNICODE_STRING (UESCAPE STRING)?      #unicodeStringLiteral
    ;

timeZoneSpecifier
    : TIME ZONE interval  #timeZoneInterval
    | TIME ZONE string    #timeZoneString
    ;

comparisonOperator
    : EQ | NEQ | LT | LTE | GT | GTE | NULLEQ
    ;

comparisonQuantifier
    : ALL | SOME | ANY
    ;

booleanValue
    : TRUE | FALSE
    ;

interval
    : INTERVAL sign=(PLUS | MINUS)? string from=intervalField (TO to=intervalField)?
    ;

intervalField
    : YEAR | MONTH | DAY | HOUR | MINUTE | SECOND | YEAR_MONTH | DAY_HOUR | DAY_MINUTE | DAY_SECOND | HOUR_MINUTE | HOUR_SECOND | MINUTE_SECOND
    ;

normalForm
    : NFD | NFC | NFKD | NFKC
    ;

type
    : type ARRAY
    | STRUCT '<' identifier (',' identifier)* '>'
    | ARRAY '<' type '>'
    | MAP '<' type ',' type '>'
    | ROW '(' identifier type (',' identifier type)* ')'
    | baseType ('(' typeParameter (',' typeParameter)* ')')?
    | INTERVAL from=intervalField TO to=intervalField
    ;

typeParameter
    : INTEGER_VALUE | type
    ;

baseType
    : TIME_WITH_TIME_ZONE
    | TIMESTAMP_WITH_TIME_ZONE
    | DOUBLE_PRECISION
    | INT_UNSIGNED
    | identifier
    ;

whenClause
    : WHEN condition=expression THEN result=expression
    ;

filter
    : FILTER '(' WHERE booleanExpression ')'
    ;

over
    : OVER '('
        (PARTITION BY partition+=expression (',' partition+=expression)*)?
        (ORDER BY sortItem (',' sortItem)*)?
        windowFrame?
      ')'
    ;

windowFrame
    : frameType=RANGE start=frameBound
    | frameType=ROWS start=frameBound
    | frameType=RANGE BETWEEN start=frameBound AND end=frameBound
    | frameType=ROWS BETWEEN start=frameBound AND end=frameBound
    ;

frameBound
    : UNBOUNDED boundType=PRECEDING                 #unboundedFrame
    | UNBOUNDED boundType=FOLLOWING                 #unboundedFrame
    | CURRENT ROW                                   #currentRowBound
    | expression boundType=(PRECEDING | FOLLOWING)  #boundedFrame // expression should be unsignedLiteral
    ;


explainOption
    : FORMAT value=(TEXT | GRAPHVIZ | JSON)                 #explainFormat
    | TYPE value=(LOGICAL | DISTRIBUTED | VALIDATE | IO)    #explainType
    ;

transactionMode
    : ISOLATION LEVEL levelOfIsolation    #isolationLevel
    | READ accessMode=(ONLY | WRITE)      #transactionAccessMode
    ;

levelOfIsolation
    : READ UNCOMMITTED                    #readUncommitted
    | READ COMMITTED                      #readCommitted
    | REPEATABLE READ                     #repeatableRead
    | SERIALIZABLE                        #serializable
    ;

callArgument
    : expression                    #positionalArgument
    | identifier '=>' expression    #namedArgument
    ;

pathElement
    : identifier '.' identifier     #qualifiedArgument
    | identifier                    #unqualifiedArgument
    ;

pathSpecification
    : pathElement (',' pathElement)*
    ;

privilege
    : SELECT | DELETE | INSERT | identifier
    ;

qualifiedName
    : identifier ('.' identifier)*
    ;

identifier
    : IDENTIFIER ((':' IDENTIFIER)|('::' IDENTIFIER))?             #unquotedIdentifier
    | QUOTED_IDENTIFIER      #quotedIdentifier
    | nonReserved            #unquotedIdentifier
    | BACKQUOTED_IDENTIFIER  #backQuotedIdentifier
    | DIGIT_IDENTIFIER       #digitIdentifier
    | identifier  string ']' #mapidentifier
    ;


identifier_ext
    : IDENTIFIER
    | QUOTED_IDENTIFIER
    | nonReserved
    | reserved
    | BACKQUOTED_IDENTIFIER
    | DIGIT_IDENTIFIER
    ;


number
    : DECIMAL_VALUE  #decimalLiteral
    | DOUBLE_VALUE   #doubleLiteral
    | INTEGER_VALUE  #integerLiteral
    ;

reserved
    : OVERWRITE | NOT | CREATE | USING | END | ALTER | TABLE | SELECT | NULL | DELETE | FROM | WHERE
    | TRUE | FALSE | VALUES
    ;

nonReserved
    // IMPORTANT: this rule must only contain tokens. Nested rules are not supported. See SqlParser.exitNonReserved
    : JSONFILE |  EXCEPT | PARQUET
     | SERDEPROPERTIES | DISABLE | GROUPING | NOVALIDATE | DEFINED | ELSE | UESCAPE
     | RECURSIVE | WAIT | GROUP | INTERSECT | DESCRIBE | WITHIN | LOCATION | SKEWED | SERDE
     | CONSTRAINT | MINUSES | MODIFY | EXECUTE | PARTITIONED | LINES
     | CURRENT_PATH | AS | THEN | REJECT | NORMALIZE | EXISTS | LIKE
     | OUTER | BY | DELIMITED | OUTPUTFORMAT | ITEMS | HAVING | UNION | AVRO | DROP
     | INPUTFORMAT | WHEN | CONCURRENTLY | NATURAL | BETWEEN | CAST | CLUSTERED | EXTERNAL
     | PREPARE | STORED | CASE | DEALLOCATE | TERMINATED | FULL | ESCAPE
     | INTO | SORTED | UNIQUE | ROLLUP | ON | ORCFILE | LIST | CUBE | BITMAP
     | OR | CHECK | ESCAPED | FREEZE | DISTINCT | TEMPORARY | RCFILE | UNNEST
     | INNER | ORDER | BUCKETS | SPLIT | TEXTFILE | LISTAGG | UPDATE | FOR
     | AND | CROSS | ORC | INDEX | IN | REFERENCES | IS | COLLECTION
     | WITH | REINDEX | PERCENT | PURGE | FIELDS | JOIN
     | SEQUENCEFILE | TBLPROPERTIES | HASH | SUBPARTITIONS | NOLOGGING

    | ADD | ALL | ANALYZE | ANY | ARRAY | ASC | AT
    | BERNOULLI
    | CALL | CASCADE | CATALOGS | COLUMN | COLUMNS | COMMENT | COMMIT | COMMITTED | CURRENT
    | DATA | DATE | DAY | DESC | DISTRIBUTED
    | EXCLUDING | EXPLAIN
    | FILTER | FIRST | FOLLOWING | FORMAT | FUNCTIONS
    | GRANT | GRANTS | GRAPHVIZ
    | HOUR
    | IF | INCLUDING | INPUT | INTERVAL | IO | ISOLATION
    | JSON
    | LAST | LATERAL | LEVEL | LIMIT | LOGICAL
    | MAP | MINUTE | MONTH
    | NFC | NFD | NFKC | NFKD | NO | NULLIF | NULLS
    | ONLY | OPTION | ORDINALITY | OUTPUT | OVER
    | PARTITION | PARTITIONS | PATH | POSITION | PRECEDING | PRIVILEGES | PROPERTIES | PUBLIC
    | RANGE | READ | RENAME | REPEATABLE | REPLACE | RESET | RESTRICT | REVOKE | ROLLBACK | ROW | ROWS
    | SCHEMA | SCHEMAS | SECOND | SERIALIZABLE | SESSION | SET | SETS
    | SHOW | SOME | START | STATS | SUBSTRING | SYSTEM
    | TABLES | TABLESAMPLE | TEXT | TIME | TIMESTAMP | TO | TRANSACTION | TRY_CAST | TYPE
    | UNBOUNDED | UNCOMMITTED | USE
    | VALIDATE | VERBOSE | VIEW
    | WORK | WRITE
    | YEAR
    | ZONE | SEMI | SORT
    | OFFSET | DEFAULT | TABLESPACE | FORCE | MSCK | REPAIR | FETCH | DEFINITION | LESS | THAN | GLOBAL | PRESERVE
    | ENGINE | AUTO_INCREMENT | CHARSET
    | RIGHT | LEFT | INSERT | TRUNCATE
    //HIVE 非关键字
    | KEY | KEYS | FOREIGN | PRIMARY | RELY | EXCHANGE | RECOVER | ARCHIVE | IGNORE | PROTECTION | UNARCHIVE | TOUCH
    | ENABLE | NO_DROP | OFFLINE | COMPACT | CONCATENATE | CHANGE | AFTER | DEFERRED | REBUILD | IDXPROPERTIES | FUNCTION
    | JAR | FILE | DATABASE | DBPROPERTIES | OWNER | RELOAD | USER | ROLE | FILEFORMAT | LOAD | LOCAL | INPATH | DIRECTORY
    //gp关键字
    | VACUUM | SUBPARTITION | EVERY | INCLUSIVE | EXCLUSIVE | TEMPLATE | ENCODING | LOG | ERRORS | SEGMENT | WRITABLE
    | UNIQUE | CONCURRENTLY | BITMAP | SPLIT | REINDEX | STRUCT | LOCALTIMESTAMP | CURRENT_TIME | EXTRACT | CURRENT_DATE
    | CURRENT_TIMESTAMP | LOCALTIME | OVERLAY | PLACING | TRIM | BOTH | TO_CHAR | AGE | DATE_PART | ISFINITE
    // gbase关键字
    | COMPRESS | EXPRESS | REPLICATED | NOCOPIES | SPACE | SHRINK | KEY_BLOCK_SIZE | KEY_DC_SIZE | AUTOEXTEND
    | NEXT | OFF | MATCHED | MERGE | OUTFILE | OPTIONALLY | ENCLOSED | DOUBLE_ENCLOSED | NULL_VALUE | OUTFILEMODE | HDFS
    | WRITEMODE | NORMAL | OVERWRITES | FILECOUNT | FILESIZE | CHARACTER | GBK | UTF8 | HEAD | RMTSELECT | INFILE
    | DATA_FORMAT | SEPARATOR | BLANKS | AUTOFILL | LENGTH | TABLE_FIELDS | MAX_BAD_RECORDS | DATETIME | TRACE
    | TRACE_PATH | NOSPLIT | PARALLEL | MAX_DATA_PROCESSORS | MIN_CHUNK_SIZE | SKIP_BAD_FILE | NUM | FILE_FORMAT | UNKNOWN
    // db2关键字
    | IMMEDIATE | STARTING | ENDING | INITIALLY | REFRESH | ACTIVATE | LOGGED | EMPTY | SEQUENCE | INCREMENT
    | MINVALUE | MAXVALUE
    // mysql关键字
    | INT | UNSIGNED | REGEXP | BINARY | LEADING | TRAILING | RLIKE | CURRENT_USER | LTRIM | RTRIM | DATE_SUB | ADDDATE | DATE_ADD
    | YEAR_MONTH | DAY_HOUR | DAY_MINUTE | DAY_SECOND | HOUR_MINUTE | HOUR_SECOND | MINUTE_SECOND
    ;

ADD: [Aa][Dd][Dd];
ALL: [Aa][Ll][Ll];
ALTER: [Aa][Ll][Tt][Ee][Rr];
ANALYZE: [Aa][Nn][Aa][Ll][Yy][Zz][Ee];
AND: [Aa][Nn][Dd];
ANY: [Aa][Nn][Yy];
ARRAY: [Aa][Rr][Rr][Aa][Yy];
AS: [Aa][Ss];
ASC: [Aa][Ss][Cc];
AT: [Aa][Tt];
BERNOULLI: [Bb][Ee][Rr][Nn][Oo][Uu][Ll][Ll][Ii];
BETWEEN: [Bb][Ee][Tt][Ww][Ee][Ee][Nn];
BY: [Bb][Yy];
CALL: [Cc][Aa][Ll][Ll];
CASCADE: [Cc][Aa][Ss][Cc][Aa][Dd][Ee];
CASE: [Cc][Aa][Ss][Ee];
CAST: [Cc][Aa][Ss][Tt];
CATALOGS: [Cc][Aa][Tt][Aa][Ll][Oo][Gg][Ss];
COLUMN: [Cc][Oo][Ll][Uu][Mm][Nn];
COLUMNS: [Cc][Oo][Ll][Uu][Mm][Nn][Ss];
COMMENT: [Cc][Oo][Mm][Mm][Ee][Nn][Tt];
COMMIT: [Cc][Oo][Mm][Mm][Ii][Tt];
COMMITTED: [Cc][Oo][Mm][Mm][Ii][Tt][Tt][Ee][Dd];
CONSTRAINT: [Cc][Oo][Nn][Ss][Tt][Rr][Aa][Ii][Nn][Tt];
CREATE: [Cc][Rr][Ee][Aa][Tt][Ee];
CROSS: [Cc][Rr][Oo][Ss][Ss];
CUBE: [Cc][Uu][Bb][Ee];
CURRENT: [Cc][Uu][Rr][Rr][Ee][Nn][Tt];
CURRENT_DATE: [Cc][Uu][Rr][Rr][Ee][Nn][Tt]'_'[Dd][Aa][Tt][Ee];
CURRENT_PATH: [Cc][Uu][Rr][Rr][Ee][Nn][Tt]'_'[Pp][Aa][Tt][Hh];
CURRENT_TIME: [Cc][Uu][Rr][Rr][Ee][Nn][Tt]'_'[Tt][Ii][Mm][Ee];
CURRENT_TIMESTAMP: [Cc][Uu][Rr][Rr][Ee][Nn][Tt]'_'[Tt][Ii][Mm][Ee][Ss][Tt][Aa][Mm][Pp];
CURRENT_USER: [Cc][Uu][Rr][Rr][Ee][Nn][Tt]'_'[Uu][Ss][Ee][Rr];
DATA: [Dd][Aa][Tt][Aa];
DATE: [Dd][Aa][Tt][Ee];
DAY: [Dd][Aa][Yy];
DEALLOCATE: [Dd][Ee][Aa][Ll][Ll][Oo][Cc][Aa][Tt][Ee];
DELETE: [Dd][Ee][Ll][Ee][Tt][Ee];
DESC: [Dd][Ee][Ss][Cc];
DESCRIBE: [Dd][Ee][Ss][Cc][Rr][Ii][Bb][Ee];
DISTINCT: [Dd][Ii][Ss][Tt][Ii][Nn][Cc][Tt];
DISTRIBUTED: [Dd][Ii][Ss][Tt][Rr][Ii][Bb][Uu][Tt][Ee][Dd];
DROP: [Dd][Rr][Oo][Pp];
ELSE: [Ee][Ll][Ss][Ee];
END: [Ee][Nn][Dd];
ESCAPE: [Ee][Ss][Cc][Aa][Pp][Ee];
EXCEPT: [Ee][Xx][Cc][Ee][Pp][Tt];
EXCLUDING: [Ee][Xx][Cc][Ll][Uu][Dd][Ii][Nn][Gg];
EXECUTE: [Ee][Xx][Ee][Cc][Uu][Tt][Ee];
EXISTS: [Ee][Xx][Ii][Ss][Tt][Ss];
EXPLAIN: [Ee][Xx][Pp][Ll][Aa][Ii][Nn];
EXTRACT: [Ee][Xx][Tt][Rr][Aa][Cc][Tt];
FALSE: [Ff][Aa][Ll][Ss][Ee];
FILTER: [Ff][Ii][Ll][Tt][Ee][Rr];
FIRST: [Ff][Ii][Rr][Ss][Tt];
FOLLOWING: [Ff][Oo][Ll][Ll][Oo][Ww][Ii][Nn][Gg];
FOR: [Ff][Oo][Rr];
FORMAT: [Ff][Oo][Rr][Mm][Aa][Tt];
FROM: [Ff][Rr][Oo][Mm];
FULL: [Ff][Uu][Ll][Ll];
FUNCTIONS: [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn][Ss];
GRANT: [Gg][Rr][Aa][Nn][Tt];
GRANTS: [Gg][Rr][Aa][Nn][Tt][Ss];
GRAPHVIZ: [Gg][Rr][Aa][Pp][Hh][Vv][Ii][Zz];
GROUP: [Gg][Rr][Oo][Uu][Pp];
GROUPING: [Gg][Rr][Oo][Uu][Pp][Ii][Nn][Gg];
HAVING: [Hh][Aa][Vv][Ii][Nn][Gg];
HOUR: [Hh][Oo][Uu][Rr];
IF: [Ii][Ff];
IN: [Ii][Nn];
INCLUDING: [Ii][Nn][Cc][Ll][Uu][Dd][Ii][Nn][Gg];
INNER: [Ii][Nn][Nn][Ee][Rr];
INPUT: [Ii][Nn][Pp][Uu][Tt];
INSERT: [Ii][Nn][Ss][Ee][Rr][Tt];
INTERSECT: [Ii][Nn][Tt][Ee][Rr][Ss][Ee][Cc][Tt];
MINUSES: [Mm][Ii][Nn][Uu][Ss];
INTERVAL: [Ii][Nn][Tt][Ee][Rr][Vv][Aa][Ll];
INTO: [Ii][Nn][Tt][Oo];
IO: [Ii][Oo];
IS: [Ii][Ss];
ISOLATION: [Ii][Ss][Oo][Ll][Aa][Tt][Ii][Oo][Nn];
JSON: [Jj][Ss][Oo][Nn];
JOIN: [Jj][Oo][Ii][Nn];
LAST: [Ll][Aa][Ss][Tt];
LATERAL: [Ll][Aa][Tt][Ee][Rr][Aa][Ll];
LEFT: [Ll][Ee][Ff][Tt];
LEVEL: [Ll][Ee][Vv][Ee][Ll];
LIKE: [Ll][Ii][Kk][Ee];
LIMIT: [Ll][Ii][Mm][Ii][Tt];
LISTAGG: [Ll][Ii][Ss][Tt][Aa][Gg][Gg];
LOCALTIME: [Ll][Oo][Cc][Aa][Ll][Tt][Ii][Mm][Ee];
LOCALTIMESTAMP: [Ll][Oo][Cc][Aa][Ll][Tt][Ii][Mm][Ee][Ss][Tt][Aa][Mm][Pp];
LOGICAL: [Ll][Oo][Gg][Ii][Cc][Aa][Ll];
MAP: [Mm][Aa][Pp];
MINUTE: [Mm][Ii][Nn][Uu][Tt][Ee];
MONTH: [Mm][Oo][Nn][Tt][Hh];
MODIFY: [Mm][Oo][Dd][Ii][Ff][Yy];
NATURAL: [Nn][Aa][Tt][Uu][Rr][Aa][Ll];
NFC: [Nn][Ff][Cc];
NFD: [Nn][Ff][Dd];
NFKC: [Nn][Ff][Kk][Cc];
NFKD: [Nn][Ff][Kk][Dd];
NO: [Nn][Oo];
NORMALIZE: [Nn][Oo][Rr][Mm][Aa][Ll][Ii][Zz][Ee];
NOT: [Nn][Oo][Tt];
NULL: [Nn][Uu][Ll][Ll];
NULLIF: [Nn][Uu][Ll][Ll][Ii][Ff];
NULLS: [Nn][Uu][Ll][Ll][Ss];
ON: [Oo][Nn];
ONLY: [Oo][Nn][Ll][Yy];
OPTION: [Oo][Pp][Tt][Ii][Oo][Nn];
OR: [Oo][Rr];
ORDER: [Oo][Rr][Dd][Ee][Rr];
ORDINALITY: [Oo][Rr][Dd][Ii][Nn][Aa][Ll][Ii][Tt][Yy];
OUTER: [Oo][Uu][Tt][Ee][Rr];
OUTPUT: [Oo][Uu][Tt][Pp][Uu][Tt];
OVER: [Oo][Vv][Ee][Rr];
OVERWRITE: [Oo][Vv][Ee][Rr][Ww][Rr][Ii][Tt][Ee];
PARTITION: [Pp][Aa][Rr][Tt][Ii][Tt][Ii][Oo][Nn];
PARTITIONS: [Pp][Aa][Rr][Tt][Ii][Tt][Ii][Oo][Nn][Ss];
PATH: [Pp][Aa][Tt][Hh];
POSITION: [Pp][Oo][Ss][Ii][Tt][Ii][Oo][Nn];
PRECEDING: [Pp][Rr][Ee][Cc][Ee][Dd][Ii][Nn][Gg];
PREPARE: [Pp][Rr][Ee][Pp][Aa][Rr][Ee];
PRIVILEGES: [Pp][Rr][Ii][Vv][Ii][Ll][Ee][Gg][Ee][Ss];
PROPERTIES: [Pp][Rr][Oo][Pp][Ee][Rr][Tt][Ii][Ee][Ss];
PUBLIC: [Pp][Uu][Bb][Ll][Ii][Cc];
RANGE: [Rr][Aa][Nn][Gg][Ee];
READ: [Rr][Ee][Aa][Dd];
RECURSIVE: [Rr][Ee][Cc][Uu][Rr][Ss][Ii][Vv][Ee];
RENAME: [Rr][Ee][Nn][Aa][Mm][Ee];
REPEATABLE: [Rr][Ee][Pp][Ee][Aa][Tt][Aa][Bb][Ll][Ee];
REPLACE: [Rr][Ee][Pp][Ll][Aa][Cc][Ee];
RESET: [Rr][Ee][Ss][Ee][Tt];
RESTRICT: [Rr][Ee][Ss][Tt][Rr][Ii][Cc][Tt];
REVOKE: [Rr][Ee][Vv][Oo][Kk][Ee];
RIGHT: [Rr][Ii][Gg][Hh][Tt];
ROLLBACK: [Rr][Oo][Ll][Ll][Bb][Aa][Cc][Kk];
ROLLUP: [Rr][Oo][Ll][Ll][Uu][Pp];
ROW: [Rr][Oo][Ww];
ROWS: [Rr][Oo][Ww][Ss];
SCHEMA: [Ss][Cc][Hh][Ee][Mm][Aa];
SCHEMAS: [Ss][Cc][Hh][Ee][Mm][Aa][Ss];
SECOND: [Ss][Ee][Cc][Oo][Nn][Dd];
SELECT: [Ss][Ee][Ll][Ee][Cc][Tt];
SERIALIZABLE: [Ss][Ee][Rr][Ii][Aa][Ll][Ii][Zz][Aa][Bb][Ll][Ee];
SESSION: [Ss][Ee][Ss][Ss][Ii][Oo][Nn];
SET: [Ss][Ee][Tt];
SETS: [Ss][Ee][Tt][Ss];
SHOW: [Ss][Hh][Oo][Ww];
SOME: [Ss][Oo][Mm][Ee];
START: [Ss][Tt][Aa][Rr][Tt];
STATS: [Ss][Tt][Aa][Tt][Ss];
SUBSTRING: [Ss][Uu][Bb][Ss][Tt][Rr][Ii][Nn][Gg];
SYSTEM: [Ss][Yy][Ss][Tt][Ee][Mm];
TABLE: [Tt][Aa][Bb][Ll][Ee];
TABLES: [Tt][Aa][Bb][Ll][Ee][Ss];
TABLESAMPLE: [Tt][Aa][Bb][Ll][Ee][Ss][Aa][Mm][Pp][Ll][Ee];
TEXT: [Tt][Ee][Xx][Tt];
THEN: [Tt][Hh][Ee][Nn];
TIME: [Tt][Ii][Mm][Ee];
TIMESTAMP: [Tt][Ii][Mm][Ee][Ss][Tt][Aa][Mm][Pp];
TO: [Tt][Oo];
TRANSACTION: [Tt][Rr][Aa][Nn][Ss][Aa][Cc][Tt][Ii][Oo][Nn];
TRUE: [Tt][Rr][Uu][Ee];
TRY_CAST: [Tt][Rr][Yy]'_'[Cc][Aa][Ss][Tt];
TYPE: [Tt][Yy][Pp][Ee];
TRUNCATE: [Tt][Rr][Uu][Nn][Cc][Aa][Tt][Ee];
UPDATE: [Uu][Pp][Dd][Aa][Tt][Ee];
UESCAPE: [Uu][Ee][Ss][Cc][Aa][Pp][Ee];
UNBOUNDED: [Uu][Nn][Bb][Oo][Uu][Nn][Dd][Ee][Dd];
UNCOMMITTED: [Uu][Nn][Cc][Oo][Mm][Mm][Ii][Tt][Tt][Ee][Dd];
UNION: [Uu][Nn][Ii][Oo][Nn];
UNNEST: [Uu][Nn][Nn][Ee][Ss][Tt];
USE: [Uu][Ss][Ee];
USING: [Uu][Ss][Ii][Nn][Gg];
VALIDATE: [Vv][Aa][Ll][Ii][Dd][Aa][Tt][Ee];
VALUES: [Vv][Aa][Ll][Uu][Ee][Ss];
VERBOSE: [Vv][Ee][Rr][Bb][Oo][Ss][Ee];
VIEW: [Vv][Ii][Ee][Ww];
WHEN: [Ww][Hh][Ee][Nn];
WHERE: [Ww][Hh][Ee][Rr][Ee];
WITH: [Ww][Ii][Tt][Hh];
WITHIN: [Ww][Ii][Tt][Hh][Ii][Nn];
WORK: [Ww][Oo][Rr][Kk];
WRITE: [Ww][Rr][Ii][Tt][Ee];
YEAR: [Yy][Ee][Aa][Rr];
ZONE: [Zz][Oo][Nn][Ee];
SEMI: [Ss][Ee][Mm][Ii];
SORT: [Ss][Oo][Rr][Tt];
OFFSET: [Oo][Ff][Ff][Ss][Ee][Tt];
DEFAULT: [Dd][Ee][Ff][Aa][Uu][Ll][Tt];
TABLESPACE: [Tt][Aa][Bb][Ll][Ee][Ss][Pp][Aa][Cc][Ee];
EXTERNAL: [Ee][Xx][Tt][Ee][Rr][Nn][Aa][Ll];

PURGE: [Pp][Uu][Rr][Gg][Ee];
TEMPORARY: [Tt][Ee][Mm][Pp][Oo][Rr][Aa][Rr][Yy];
PRIMARY: [Pp][Rr][Ii][Mm][Aa][Rr][Yy];
KEY: [Kk][Ee][Yy];
DISABLE: [Dd][Ii][Ss][Aa][Bb][Ll][Ee];
NOVALIDATE: [Nn][Oo][Vv][Aa][Ll][Ii][Dd][Aa][Tt][Ee];
FOREIGN: [Ff][Oo][Rr][Ee][Ii][Gg][Nn];
REFERENCES: [Rr][Ee][Ff][Ee][Rr][Ee][Nn][Cc][Ee][Ss];
PARTITIONED: [Pp][Aa][Rr][Tt][Ii][Tt][Ii][Oo][Nn][Ee][Dd];
CLUSTERED: [Cc][Ll][Uu][Ss][Tt][Ee][Rr][Ee][Dd];
SORTED: [Ss][Oo][Rr][Tt][Ee][Dd];
BUCKETS: [Bb][Uu][Cc][Kk][Ee][Tt][Ss];
SKEWED: [Ss][Kk][Ee][Ww][Ee][Dd];
STORED: [Ss][Tt][Oo][Rr][Ee][Dd];
DELIMITED: [Dd][Ee][Ll][Ii][Mm][Ii][Tt][Ee][Dd];
FIELDS: [Ff][Ii][Ee][Ll][Dd][Ss];
TERMINATED: [Tt][Ee][Rr][Mm][Ii][Nn][Aa][Tt][Ee][Dd];
ESCAPED: [Ee][Ss][Cc][Aa][Pp][Ee][Dd];
COLLECTION: [Cc][Oo][Ll][Ll][Ee][Cc][Tt][Ii][Oo][Nn];
ITEMS: [Ii][Tt][Ee][Mm][Ss];
KEYS: [Kk][Ee][Yy][Ss];
LINES: [Ll][Ii][Nn][Ee][Ss];
DEFINED: [Dd][Ee][Ff][Ii][Nn][Ee][Dd];
SEQUENCEFILE: [Ss][Ee][Qq][Uu][Ee][Nn][Cc][Ee][Ff][Ii][Ll][Ee];
TEXTFILE: [Tt][Ee][Xx][Tt][Ff][Ii][Ll][Ee];
RCFILE: [Rr][Cc][Ff][Ii][Ll][Ee];
ORC: [Oo][Rr][Cc];
ORCFILE: [Oo][Rr][Cc][Ff][Ii][Ll][Ee];
PARQUET: [Pp][Aa][Rr][Qq][Uu][Ee][Tt];
AVRO: [Aa][Vv][Rr][Oo];
JSONFILE: [Jj][Ss][Oo][Nn][Ff][Ii][Ll][Ee];
INPUTFORMAT: [Ii][Nn][Pp][Uu][Tt][Ff][Oo][Rr][Mm][Aa][Tt];
OUTPUTFORMAT: [Oo][Uu][Tt][Pp][Uu][Tt][Ff][Oo][Rr][Mm][Aa][Tt];
SERDE: [Ss][Ee][Rr][Dd][Ee];
SERDEPROPERTIES: [Ss][Ee][Rr][Dd][Ee][Pp][Rr][Oo][Pp][Ee][Rr][Tt][Ii][Ee][Ss];
LOCATION: [Ll][Oo][Cc][Aa][Tt][Ii][Oo][Nn];
TBLPROPERTIES: [Tt][Bb][Ll][Pp][Rr][Oo][Pp][Ee][Rr][Tt][Ii][Ee][Ss];
RELY: [Rr][Ee][Ll][Yy];
EXCHANGE: [Ee][Xx][Cc][Hh][Aa][Nn][Gg][Ee];
RECOVER: [Rr][Ee][Cc][Oo][Vv][Ee][Rr];
ARCHIVE: [Aa][Rr][Cc][Hh][Ii][Vv][Ee];
UNARCHIVE: [Uu][Nn][Aa][Rr][Cc][Hh][Ii][Vv][Ee];
IGNORE: [Ii][Gg][Nn][Oo][Rr][Ee];
PROTECTION: [Pp][Rr][Oo][Tt][Ee][Cc][Tt][Ii][Oo][Nn];
TOUCH: [Tt][Oo][Uu][Cc][Hh];
ENABLE: [Ee][Nn][Aa][Bb][Ll][Ee];
NO_DROP: [Nn][Oo]'_'[Dd][Rr][Oo][Pp];
OFFLINE: [Oo][Ff][Ff][Ll][Ii][Nn][Ee];
COMPACT: [Cc][Oo][Mm][Pp][Aa][Cc][Tt];
WAIT: [Ww][Aa][Ii][Tt];
CONCATENATE: [Cc][Oo][Nn][Cc][Aa][Tt][Ee][Nn][Aa][Tt][Ee];
CHANGE: [Cc][Hh][Aa][Nn][Gg][Ee];
AFTER: [Aa][Ff][Tt][Ee][Rr];
DEFERRED: [Dd][Ee][Ff][Ee][Rr][Rr][Ee][Dd];
REBUILD: [Rr][Ee][Bb][Uu][Ii][Ll][Dd];
INDEX: [Ii][Nn][Dd][Ee][Xx];
IDXPROPERTIES: [Ii][Dd][Xx][Pp][Rr][Oo][Pp][Ee][Rr][Tt][Ii][Ee][Ss];
FUNCTION: [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn];
JAR: [Jj][Aa][Rr];
FILE: [Ff][Ii][Ll][Ee];
DATABASE: [Dd][Aa][Tt][Aa][Bb][Aa][Ss][Ee];
DBPROPERTIES: [Dd][Bb][Pp][Rr][Oo][Pp][Ee][Rr][Tt][Ii][Ee][Ss];
OWNER: [Oo][Ww][Nn][Ee][Rr];
RELOAD: [Rr][Ee][Ll][Oo][Aa][Dd];
USER: [Uu][Ss][Ee][Rr];
ROLE: [Rr][Oo][Ll][Ee];
FILEFORMAT: [Ff][Ii][Ll][Ee][Ff][Oo][Rr][Mm][Aa][Tt];
LOAD: [Ll][Oo][Aa][Dd];
LOCAL: [Ll][Oo][Cc][Aa][Ll];
INPATH: [Ii][Nn][Pp][Aa][Tt][Hh];
DIRECTORY: [Dd][Ii][Rr][Ee][Cc][Tt][Oo][Rr][Yy];
VACUUM: [Vv][Aa][Cc][Uu][Uu][Mm];
LIST: [Ll][Ii][Ss][Tt];
SUBPARTITION: [Ss][Uu][Bb][Pp][Aa][Rr][Tt][Ii][Tt][Ii][Oo][Nn];
SUBPARTITIONS: [Ss][Uu][Bb][Pp][Aa][Rr][Tt][Ii][Tt][Ii][Oo][Nn][Ss];
EVERY: [Ee][Vv][Ee][Rr][Yy];
INCLUSIVE: [Ii][Nn][Cc][Ll][Uu][Ss][Ii][Vv][Ee];
EXCLUSIVE: [Ee][Xx][Cc][Ll][Uu][Ss][Ii][Vv][Ee];
TEMPLATE: [Tt][Ee][Mm][Pp][Ll][Aa][Tt][Ee];
ENCODING: [Ee][Nn][Cc][Oo][Dd][Ii][Nn][Gg];
LOG: [Ll][Oo][Gg];
ERRORS: [Ee][Rr][Rr][Oo][Rr][Ss];
SEGMENT: [Ss][Ee][Gg][Mm][Ee][Nn][Tt];
REJECT: [Rr][Ee][Jj][Ee][Cc][Tt];
PERCENT:[Pp][Ee][Rr][Cc][Ee][Nn][Tt];
WRITABLE: [Ww][Rr][Ii][Tt][Aa][Bb][Ll][Ee];
CHECK: [Cc][Hh][Ee][Cc][Kk];
UNIQUE: [Uu][Nn][Ii][Qq][Uu][Ee];
CONCURRENTLY: [Cc][Oo][Nn][Cc][Uu][Rr][Rr][Ee][Nn][Tt][Ll][Yy];
BITMAP: [Bt][Ii][Tt][Mm][Aa][Pp];
FORCE: [Ff][Oo][Rr][Cc][Ee];
SPLIT: [Ss][Pp][Ll][Ii][Tt];
REINDEX: [Rr][Ee][Ii][Nn][Dd][Ee][Xx];
FREEZE: [Ff][Rr][Ee][Ee][Zz][Ee];
MSCK: [Mm][Ss][Cc][Kk];
REPAIR: [Rr][Ee][Pp][Aa][Ii][Rr];
FETCH: [Ff][Ee][Tt][Cc][Hh];
DEFINITION: [Dd][Ee][Ff][Ii][Nn][Ii][Tt][Ii][Oo][Nn];
LESS: [Ll][Ee][Ss][Ss];
THAN: [Tt][Hh][Aa][Nn];
HASH: [Hh][Aa][Ss][Hh];
STORE: [Ss][Tt][Oo][Rr][Ee];
NOLOGGING: [Nn][Oo][Ll][Oo][Gg][Gg][Ii][Nn][Gg];
STRUCT: [Ss][Tt][Rr][Uu][Cc][Tt];
GLOBAL: [Gg][Ll][Oo][Bb][Aa][Ll];
PRESERVE: [Pp][Rr][Ee][Ss][Ee][Rr][Vv][Ee];
AUTO_INCREMENT: [Aa][Uu][Tt][Oo][_][Ii][Nn][Cc][Rr][Ee][Mm][Ee][Nn][Tt];
ENGINE: [Ee][Nn][Gg][Ii][Nn][Ee];
CHARSET: [Cc][Hh][Aa][Rr][Ss][Ee][Tt];
COMPRESS: [Cc][Oo][Mm][Pp][Rr][Ee][Ss][Ss];
EXPRESS: [Ee][Xx][Pp][Rr][Ee][Ss][Ss];
REPLICATED: [Rr][Ee][Pp][Ll][Ii][Cc][Aa][Tt][Ee][Dd];
NOCOPIES: [Nn][Oo][Cc][Oo][Pp][Ii][Ee][Ss];
SPACE: [Ss][Pp][Aa][Cc][Ee];
SHRINK: [Ss][Hh][Rr][Ii][Nn][Kk];
KEY_BLOCK_SIZE: [Kk][Ee][Yy]'_'[Bb][Ll][Oo][Cc][Kk]'_'[Ss][Ii][Zz][Ee];
KEY_DC_SIZE: [Kk][Ee][Yy]'_'[Dd][Cc]'_'[Ss][Ii][Zz][Ee];
AUTOEXTEND: [Aa][Uu][Tt][Oo][Ee][Xx][Tt][Ee][Nn][Dd];
NEXT: [Nn][Ee][Xx][Tt];
OFF: [Oo][Ff][Ff];
MATCHED: [Mm][Aa][Tt][Cc][Hh][Ee][Dd];
MERGE: [Mm][Ee][Rr][Gg][Ee];
OUTFILE: [Oo][Uu][Tt][Ff][Ii][Ll][Ee];
OPTIONALLY: [Oo][Pp][Tt][Ii][Oo][Nn][Aa][Ll][Ll][Yy];
ENCLOSED: [Ee][Nn][Cc][Ll][Oo][Ss][Ee][Dd];
DOUBLE_ENCLOSED: [Dd][Oo][Uu][Bb][Ll][Ee]'_'[Ee][Nn][Cc][Ll][Oo][Ss][Ee][Dd];
NULL_VALUE: [Nn][Uu][Ll][Ll]'_'[Vv][Aa][Ll][Uu][Ee];
OUTFILEMODE: [Oo][Uu][Tt][Ff][Ii][Ll][Ee][Mm][Oo][Dd][Ee];
HDFS: [Hh][Dd][Ff][Ss];
WRITEMODE: [Ww][Rr][Ii][Tt][Ee][Mm][Oo][Dd][Ee];
NORMAL: [Nn][Oo][Rr][Mm][Aa][Ll];
OVERWRITES: [Oo][Vv][Ee][Rr][Ww][Rr][Ii][Tt][Ee][Ss];
FILECOUNT: [Ff][Ii][Ll][Ee][Cc][Oo][Uu][Nn][Tt];
FILESIZE: [Ff][Ii][Ll][Ee][Ss][Ii][Zz][Ee];
CHARACTER: [Cc][Hh][Aa][Rr][Aa][Cc][Tt][Ee][Rr];
GBK: [Gg][Bb][Kk];
UTF8: [Uu][Tt][Ff]'8';
HEAD: [Hh][Ee][Aa][Dd];
RMTSELECT: [Rr][Mm][Tt]':'[Ss][Ee][Ll][Ee][Cc][Tt];
INFILE: [Ii][Nn][Ff][Ii][Ll][Ee];
DATA_FORMAT: [Dd][Aa][Tt][Aa]'_'[Ff][Oo][Rr][Mm][Aa][Tt];
SEPARATOR: [Ss][Ee][Pp][Aa][Rr][Aa][Tt][Oo][Rr];
BLANKS: [Bb][Ll][Aa][Nn][Kk][Ss];
AUTOFILL: [Aa][Uu][Tt][Oo][Ff][Ii][Ll][Ll];
LENGTH: [Ll][Ee][Nn][Gg][Tt][Hh];
TABLE_FIELDS: [Tt][Aa][Bb][Ll][Ee]'_'[Ff][Ii][Ee][Ll][Dd][Ss];
MAX_BAD_RECORDS: [Mm][Aa][Xx]'_'[Bb][Aa][Dd]'_'[Rr][Ee][Cc][Oo][Rr][Dd][Ss];
DATETIME: [Dd][Aa][Tt][Ee][Tt][Ii][Mm][Ee];
TRACE: [Tt][Rr][Aa][Cc][Ee];
TRACE_PATH: [Tt][Rr][Aa][Cc][Ee]'_'[Pp][Aa][Tt][Hh];
NOSPLIT: [Nn][Oo][Ss][Pp][Ll][Ii][Tt];
PARALLEL: [Pp][Aa][Rr][Aa][Ll][Ll][Ee][Ll];
MAX_DATA_PROCESSORS: [Mm][Aa][Xx]'_'[Dd][Aa][Tt][Aa]'_'[Pp][Rr][Oo][Cc][Ee][Ss][Ss][Oo][Rr][Ss];
MIN_CHUNK_SIZE: [Mm][Ii][Nn]'_'[Cc][Hh][Uu][Nn][Kk]'_'[Ss][Ii][Zz][Ee];
SKIP_BAD_FILE: [Ss][Kk][Ii][Pp]'_'[Bb][Aa][Dd]'_'[Ff][Ii][Ll][Ee];
NUM: [Nn][Uu][Mm];
FILE_FORMAT: [Ff][Ii][Ll][Ee]'_'[Ff][Oo][Rr][Mm][Aa][Tt];
IMMEDIATE: [Ii][Mm][Mm][Ee][Dd][Ii][Aa][Tt][Ee];
STARTING: [Ss][Tt][Aa][Rr][Tt][Ii][Nn][Gg];
ENDING: [Ee][Nn][Dd][Ii][Nn][Gg];
INITIALLY: [Ii][Nn][Ii][Tt][Ii][Aa][Ll][Ll][Yy];
REFRESH: [Rr][Ee][Ff][Rr][Ee][Ss][Hh];
ACTIVATE: [Aa][Cc][Tt][Ii][Vv][Aa][Tt][Ee];
LOGGED: [Ll][Oo][Gg][Gg][Ee][Dd];
EMPTY: [Ee][Mm][Pp][Tt][Yy];
SEQUENCE: [Ss][Ee][Qq][Uu][Ee][Nn][Cc][Ee];
INCREMENT: [Ii][Nn][Cc][Rr][Ee][Mm][Ee][Nn][Tt];
MINVALUE: [Mm][Ii][Nn][Vv][Aa][Ll][Uu][Ee];
MAXVALUE: [Mm][Aa][Xx][Vv][Aa][Ll][Uu][Ee];
OVERLAY: [Oo][Vv][Ee][Rr][Ll][Aa][Yy];
PLACING: [Pp][Ll][Aa][Cc][Ii][Nn][Gg];
TRIM: [Tt][Rr][Ii][Mm];
LTRIM: [Ll][Tt][Rr][Ii][Mm];
RTRIM: [Rr][Tt][Rr][Ii][Mm];
BOTH: [Bb][Oo][Tt][Hh];
TO_CHAR: [Tt][Oo][_][Cc][Hh][Aa][Rr];
AGE: [Aa][Gg][Ee];
DATE_PART: [Dd][Aa][Tt][Ee][_][Pp][Aa][Rr][Tt];
ISFINITE: [Ii][Ss][Ff][Ii][Nn][Ii][Tt][Ee];
JUSTIFY_DAYS: [Jj][Uu][Ss][Tt][Ii][Ff][Yy][_][Dd][Aa][Yy][Ss];
JUSTIFY_HOURS: [Jj][Uu][Ss][Tt][Ii][Ff][Yy][_][Hh][Oo][Uu][Rr][Ss];
JUSTIFY_INTERVAL: [Jj][Uu][Ss][Tt][Ii][Ff][Yy][_][Ii][Nn][Tt][Ee][Rr][Vv][Aa][Ll];
INT: [Ii][Nn][Tt];
UNSIGNED: [Uu][Nn][Ss][Ii][Gg][Nn][Ee][Dd];
REGEXP: [Rr][Ee][Gg][Ee][Xx][Pp];
BINARY: [Bb][Ii][Nn][Aa][Rr][Yy];
LEADING: [Ll][Ee][Aa][Dd][Ii][Nn][Gg];
TRAILING: [Tt][Rr][Aa][Ii][Ll][Ii][Nn][Gg];
RLIKE: [Rr][Ll][Ii][Kk][Ee];
DATE_SUB: [Dd][Aa][Tt][Ee][_][Ss][Uu][Bb];
ADDDATE: [Aa][Dd][Dd][Dd][Aa][Tt][Ee];
DATE_ADD: [Dd][Aa][Tt][Ee][_][Aa][Dd][Dd];
YEAR_MONTH: [Yy][Ee][Aa][Rr][_][Mm][Oo][Nn][Tt][Hh];
DAY_HOUR: [Dd][Aa][Yy][_][Hh][Oo][Uu][Rr];
DAY_MINUTE: [Dd][Aa][Yy][_][Mm][Ii][Nn][Uu][Tt][Ee];
DAY_SECOND: [Dd][Aa][Yy][_][Ss][Ee][Cc][Oo][Nn][Dd];
HOUR_MINUTE: [Hh][Oo][Uu][Rr][_][Mm][Ii][Nn][Uu][Tt][Ee];
HOUR_SECOND:  [Hh][Oo][Uu][Rr][_][Ss][Ee][Cc][Oo][Nn][Dd];
MINUTE_SECOND: [Mm][Ii][Nn][Uu][Tt][Ee][_][Ss][Ee][Cc][Oo][Nn][Dd];
UNKNOWN: [Uu][Nn][Kk][Nn][Oo][Ww][Nn];

NULLEQ  : '<=>' ;
EQ  : '=' ;
NEQ : '<>' | '!=';
LT  : '<';
LTE : '<=';
GT  : '>';
GTE : '>=';

PLUS: '+';
MINUS: '-';
ASTERISK: '*';
SLASH: '/';
MATHPERCENT: '%';
MATHMOD: [Mm][Oo][Dd];
CONCAT: '||';
LOGICALAND: '&';
LOGICALOR: '|';
LOGICALEXCLUSIVEOR: '^';
LOGICALNOT: '!';
XOR: [Xx][Oo][Rr];



STRING
    : '\'' ( ~'\'' | '\'\'' )* '\''
    ;

UNICODE_STRING
    : 'U&\'' ( ~'\'' | '\'\'' )* '\''
    ;

// Note: we allow any character inside the binary literal and validate
// its a correct literal when the AST is being constructed. This
// allows us to provide more meaningful error messages to the user
BINARY_LITERAL
    :  'X\'' (~'\'')* '\''
    ;

INTEGER_VALUE
    : DIGIT+
    ;

DECIMAL_VALUE
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

DOUBLE_VALUE
    : DIGIT+ ('.' DIGIT*)? EXPONENT
    | '.' DIGIT+ EXPONENT
    ;

IDENTIFIER
    : ('$' | LETTER | '_') (LETTER | DIGIT | '_' | '@' | '$' | '{' | '}' | '[' | ']')*
    ;


DIGIT_IDENTIFIER
    : DIGIT (LETTER | DIGIT | '_' | '@' | ':')+
    ;

QUOTED_IDENTIFIER
    : '"' ( ~'"' | '""' )* '"'
    ;

BACKQUOTED_IDENTIFIER
    : '`' ( ~'`' | '``' )* '`'
    ;

TIME_WITH_TIME_ZONE
    : TIME WS WITH WS TIME WS ZONE
    ;

TIMESTAMP_WITH_TIME_ZONE
    : TIMESTAMP WS WITH WS TIME WS ZONE
    ;

INT_UNSIGNED
    : INT WS UNSIGNED
    ;

DOUBLE_PRECISION
    : [Dd][Oo][Uu][Bb][Ll][Ee] WS [Pp][Rr][Ee][Cc][Ii][Ss][Ii][Oo][Nn]
    ;

fragment EXPONENT
    : 'E' [+-]? DIGIT+
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Za-z]
    ;

SIMPLE_COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
    ;

BRACKETED_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer
UNRECOGNIZED
    : .
    ;

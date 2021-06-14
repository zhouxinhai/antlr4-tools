grammar GroovyExp;

tokens {
    DELIMITER
}

statement
    : valueExpression EOF
    ;

valueExpression
    : primaryExpression                                                                              #valueExpressionDefault
    | operator=(MINUS | PLUS | LOGICALNOT) valueExpression                                           #arithmeticUnary
    | left=valueExpression
        operator=(ASTERISK | SLASH | MATHPERCENT | MATHMOD |PLUS | MINUS | LOGICALAND | LOGICALOR | LOGICALEXCLUSIVEOR | XOR)
        right=valueExpression #arithmeticBinary
    ;

primaryExpression
    : NULL                                                                                #nullLiteral
    | number                                                                              #numericLiteral
    | booleanValue                                                                        #booleanLiteral
    | string                                                                              #stringLiteral
    | BINARY_LITERAL                                                                      #binaryLiteral
    | qualifiedName '(' ( valueExpression (',' valueExpression)*)? ')'                    #functionCall
    | identifier '->' valueExpression                                                     #lambda
    | '(' (identifier (',' identifier)*)? ')' '->' valueExpression                        #lambda
    | '(' valueExpression ')'                                                             #subqueryExpression
    | value=primaryExpression '[' index=valueExpression ']'                               #subscript
    | identifier                                                                          #columnReference
    | base=primaryExpression '.' fieldName=identifier                                     #dereference
    ;

comparisonOperator
    : EQ | NEQ | LT | LTE | GT | GTE | NULLEQ
    ;

string
    : STRING                                #basicStringLiteral
    | UNICODE_STRING (UESCAPE STRING)?      #unicodeStringLiteral
    ;


booleanValue
    : TRUE | FALSE
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

number
    : DECIMAL_VALUE  #decimalLiteral
    | DOUBLE_VALUE   #doubleLiteral
    | INTEGER_VALUE  #integerLiteral
    ;

reserved
    :
    ;

nonReserved
    // IMPORTANT: this rule must only contain tokens. Nested rules are not supported. See SqlParser.exitNonReserved
    :OVERWRITE | JSONFILE | NOT | EXCEPT | PARQUET | CREATE | USING
     | SERDEPROPERTIES | DISABLE | GROUPING | NOVALIDATE | DEFINED | ELSE | UESCAPE
     | RECURSIVE | WAIT | GROUP | INTERSECT | DESCRIBE | WITHIN | LOCATION | SKEWED | SERDE
     | END | CONSTRAINT | MINUSES | MODIFY | ALTER | EXECUTE | PARTITIONED | LINES
     | TABLE | CURRENT_PATH | AS | THEN | REJECT | NORMALIZE | EXISTS | LIKE
     | OUTER | BY | DELIMITED | OUTPUTFORMAT | ITEMS | HAVING | UNION | AVRO | DROP
     | INPUTFORMAT | WHEN | CONCURRENTLY | NATURAL | BETWEEN | CAST | CLUSTERED | EXTERNAL
     | PREPARE | STORED | CASE | DEALLOCATE | TERMINATED | FULL | ESCAPE
     | SELECT | INTO | SORTED | UNIQUE | ROLLUP | NULL | ON | ORCFILE | DELETE | LIST | CUBE | BITMAP
     | OR | CHECK | ESCAPED | FREEZE | FROM | FALSE | DISTINCT | TEMPORARY | RCFILE | UNNEST
     | WHERE | INNER | ORDER | BUCKETS | SPLIT | TEXTFILE | LISTAGG | UPDATE | FOR
     | AND | CROSS | ORC | INDEX | IN | REFERENCES | IS | COLLECTION
     | WITH | REINDEX | PERCENT | VALUES | PURGE | FIELDS | TRUE | JOIN
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
    : '//' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
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
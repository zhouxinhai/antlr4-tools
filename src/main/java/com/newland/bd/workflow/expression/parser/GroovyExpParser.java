// Generated from D:/code/java/learn/antlr4-tools/src/main/resources/antlr\GroovyExp.g4 by ANTLR 4.9.1
package com.newland.bd.workflow.expression.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GroovyExpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		ADD=10, ALL=11, ALTER=12, ANALYZE=13, AND=14, ANY=15, ARRAY=16, AS=17, 
		ASC=18, AT=19, BERNOULLI=20, BETWEEN=21, BY=22, CALL=23, CASCADE=24, CASE=25, 
		CAST=26, CATALOGS=27, COLUMN=28, COLUMNS=29, COMMENT=30, COMMIT=31, COMMITTED=32, 
		CONSTRAINT=33, CREATE=34, CROSS=35, CUBE=36, CURRENT=37, CURRENT_DATE=38, 
		CURRENT_PATH=39, CURRENT_TIME=40, CURRENT_TIMESTAMP=41, CURRENT_USER=42, 
		DATA=43, DATE=44, DAY=45, DEALLOCATE=46, DELETE=47, DESC=48, DESCRIBE=49, 
		DISTINCT=50, DISTRIBUTED=51, DROP=52, ELSE=53, END=54, ESCAPE=55, EXCEPT=56, 
		EXCLUDING=57, EXECUTE=58, EXISTS=59, EXPLAIN=60, EXTRACT=61, FALSE=62, 
		FILTER=63, FIRST=64, FOLLOWING=65, FOR=66, FORMAT=67, FROM=68, FULL=69, 
		FUNCTIONS=70, GRANT=71, GRANTS=72, GRAPHVIZ=73, GROUP=74, GROUPING=75, 
		HAVING=76, HOUR=77, IF=78, IN=79, INCLUDING=80, INNER=81, INPUT=82, INSERT=83, 
		INTERSECT=84, MINUSES=85, INTERVAL=86, INTO=87, IO=88, IS=89, ISOLATION=90, 
		JSON=91, JOIN=92, LAST=93, LATERAL=94, LEFT=95, LEVEL=96, LIKE=97, LIMIT=98, 
		LISTAGG=99, LOCALTIME=100, LOCALTIMESTAMP=101, LOGICAL=102, MAP=103, MINUTE=104, 
		MONTH=105, MODIFY=106, NATURAL=107, NFC=108, NFD=109, NFKC=110, NFKD=111, 
		NO=112, NORMALIZE=113, NOT=114, NULL=115, NULLIF=116, NULLS=117, ON=118, 
		ONLY=119, OPTION=120, OR=121, ORDER=122, ORDINALITY=123, OUTER=124, OUTPUT=125, 
		OVER=126, OVERWRITE=127, PARTITION=128, PARTITIONS=129, PATH=130, POSITION=131, 
		PRECEDING=132, PREPARE=133, PRIVILEGES=134, PROPERTIES=135, PUBLIC=136, 
		RANGE=137, READ=138, RECURSIVE=139, RENAME=140, REPEATABLE=141, REPLACE=142, 
		RESET=143, RESTRICT=144, REVOKE=145, RIGHT=146, ROLLBACK=147, ROLLUP=148, 
		ROW=149, ROWS=150, SCHEMA=151, SCHEMAS=152, SECOND=153, SELECT=154, SERIALIZABLE=155, 
		SESSION=156, SET=157, SETS=158, SHOW=159, SOME=160, START=161, STATS=162, 
		SUBSTRING=163, SYSTEM=164, TABLE=165, TABLES=166, TABLESAMPLE=167, TEXT=168, 
		THEN=169, TIME=170, TIMESTAMP=171, TO=172, TRANSACTION=173, TRUE=174, 
		TRY_CAST=175, TYPE=176, TRUNCATE=177, UPDATE=178, UESCAPE=179, UNBOUNDED=180, 
		UNCOMMITTED=181, UNION=182, UNNEST=183, USE=184, USING=185, VALIDATE=186, 
		VALUES=187, VERBOSE=188, VIEW=189, WHEN=190, WHERE=191, WITH=192, WITHIN=193, 
		WORK=194, WRITE=195, YEAR=196, ZONE=197, SEMI=198, SORT=199, OFFSET=200, 
		DEFAULT=201, TABLESPACE=202, EXTERNAL=203, PURGE=204, TEMPORARY=205, PRIMARY=206, 
		KEY=207, DISABLE=208, NOVALIDATE=209, FOREIGN=210, REFERENCES=211, PARTITIONED=212, 
		CLUSTERED=213, SORTED=214, BUCKETS=215, SKEWED=216, STORED=217, DELIMITED=218, 
		FIELDS=219, TERMINATED=220, ESCAPED=221, COLLECTION=222, ITEMS=223, KEYS=224, 
		LINES=225, DEFINED=226, SEQUENCEFILE=227, TEXTFILE=228, RCFILE=229, ORC=230, 
		ORCFILE=231, PARQUET=232, AVRO=233, JSONFILE=234, INPUTFORMAT=235, OUTPUTFORMAT=236, 
		SERDE=237, SERDEPROPERTIES=238, LOCATION=239, TBLPROPERTIES=240, RELY=241, 
		EXCHANGE=242, RECOVER=243, ARCHIVE=244, UNARCHIVE=245, IGNORE=246, PROTECTION=247, 
		TOUCH=248, ENABLE=249, NO_DROP=250, OFFLINE=251, COMPACT=252, WAIT=253, 
		CONCATENATE=254, CHANGE=255, AFTER=256, DEFERRED=257, REBUILD=258, INDEX=259, 
		IDXPROPERTIES=260, FUNCTION=261, JAR=262, FILE=263, DATABASE=264, DBPROPERTIES=265, 
		OWNER=266, RELOAD=267, USER=268, ROLE=269, FILEFORMAT=270, LOAD=271, LOCAL=272, 
		INPATH=273, DIRECTORY=274, VACUUM=275, LIST=276, SUBPARTITION=277, SUBPARTITIONS=278, 
		EVERY=279, INCLUSIVE=280, EXCLUSIVE=281, TEMPLATE=282, ENCODING=283, LOG=284, 
		ERRORS=285, SEGMENT=286, REJECT=287, PERCENT=288, WRITABLE=289, CHECK=290, 
		UNIQUE=291, CONCURRENTLY=292, BITMAP=293, FORCE=294, SPLIT=295, REINDEX=296, 
		FREEZE=297, MSCK=298, REPAIR=299, FETCH=300, DEFINITION=301, LESS=302, 
		THAN=303, HASH=304, STORE=305, NOLOGGING=306, STRUCT=307, GLOBAL=308, 
		PRESERVE=309, AUTO_INCREMENT=310, ENGINE=311, CHARSET=312, COMPRESS=313, 
		EXPRESS=314, REPLICATED=315, NOCOPIES=316, SPACE=317, SHRINK=318, KEY_BLOCK_SIZE=319, 
		KEY_DC_SIZE=320, AUTOEXTEND=321, NEXT=322, OFF=323, MATCHED=324, MERGE=325, 
		OUTFILE=326, OPTIONALLY=327, ENCLOSED=328, DOUBLE_ENCLOSED=329, NULL_VALUE=330, 
		OUTFILEMODE=331, HDFS=332, WRITEMODE=333, NORMAL=334, OVERWRITES=335, 
		FILECOUNT=336, FILESIZE=337, CHARACTER=338, GBK=339, UTF8=340, HEAD=341, 
		RMTSELECT=342, INFILE=343, DATA_FORMAT=344, SEPARATOR=345, BLANKS=346, 
		AUTOFILL=347, LENGTH=348, TABLE_FIELDS=349, MAX_BAD_RECORDS=350, DATETIME=351, 
		TRACE=352, TRACE_PATH=353, NOSPLIT=354, PARALLEL=355, MAX_DATA_PROCESSORS=356, 
		MIN_CHUNK_SIZE=357, SKIP_BAD_FILE=358, NUM=359, FILE_FORMAT=360, IMMEDIATE=361, 
		STARTING=362, ENDING=363, INITIALLY=364, REFRESH=365, ACTIVATE=366, LOGGED=367, 
		EMPTY=368, SEQUENCE=369, INCREMENT=370, MINVALUE=371, MAXVALUE=372, OVERLAY=373, 
		PLACING=374, TRIM=375, LTRIM=376, RTRIM=377, BOTH=378, TO_CHAR=379, AGE=380, 
		DATE_PART=381, ISFINITE=382, JUSTIFY_DAYS=383, JUSTIFY_HOURS=384, JUSTIFY_INTERVAL=385, 
		INT=386, UNSIGNED=387, REGEXP=388, BINARY=389, LEADING=390, TRAILING=391, 
		RLIKE=392, DATE_SUB=393, ADDDATE=394, DATE_ADD=395, YEAR_MONTH=396, DAY_HOUR=397, 
		DAY_MINUTE=398, DAY_SECOND=399, HOUR_MINUTE=400, HOUR_SECOND=401, MINUTE_SECOND=402, 
		UNKNOWN=403, NULLEQ=404, EQ=405, NEQ=406, LT=407, LTE=408, GT=409, GTE=410, 
		PLUS=411, MINUS=412, ASTERISK=413, SLASH=414, MATHPERCENT=415, MATHMOD=416, 
		CONCAT=417, LOGICALAND=418, LOGICALOR=419, LOGICALEXCLUSIVEOR=420, LOGICALNOT=421, 
		XOR=422, STRING=423, UNICODE_STRING=424, BINARY_LITERAL=425, INTEGER_VALUE=426, 
		DECIMAL_VALUE=427, DOUBLE_VALUE=428, IDENTIFIER=429, DIGIT_IDENTIFIER=430, 
		QUOTED_IDENTIFIER=431, BACKQUOTED_IDENTIFIER=432, TIME_WITH_TIME_ZONE=433, 
		TIMESTAMP_WITH_TIME_ZONE=434, INT_UNSIGNED=435, DOUBLE_PRECISION=436, 
		SIMPLE_COMMENT=437, BRACKETED_COMMENT=438, WS=439, UNRECOGNIZED=440, DELIMITER=441;
	public static final int
		RULE_statement = 0, RULE_valueExpression = 1, RULE_primaryExpression = 2, 
		RULE_comparisonOperator = 3, RULE_string = 4, RULE_booleanValue = 5, RULE_qualifiedName = 6, 
		RULE_identifier = 7, RULE_number = 8, RULE_reserved = 9, RULE_nonReserved = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "valueExpression", "primaryExpression", "comparisonOperator", 
			"string", "booleanValue", "qualifiedName", "identifier", "number", "reserved", 
			"nonReserved"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'->'", "'['", "']'", "'.'", "':'", "'::'", 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "'<=>'", 
			"'='", null, "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", "'/'", 
			"'%'", null, "'||'", "'&'", "'|'", "'^'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "ADD", "ALL", 
			"ALTER", "ANALYZE", "AND", "ANY", "ARRAY", "AS", "ASC", "AT", "BERNOULLI", 
			"BETWEEN", "BY", "CALL", "CASCADE", "CASE", "CAST", "CATALOGS", "COLUMN", 
			"COLUMNS", "COMMENT", "COMMIT", "COMMITTED", "CONSTRAINT", "CREATE", 
			"CROSS", "CUBE", "CURRENT", "CURRENT_DATE", "CURRENT_PATH", "CURRENT_TIME", 
			"CURRENT_TIMESTAMP", "CURRENT_USER", "DATA", "DATE", "DAY", "DEALLOCATE", 
			"DELETE", "DESC", "DESCRIBE", "DISTINCT", "DISTRIBUTED", "DROP", "ELSE", 
			"END", "ESCAPE", "EXCEPT", "EXCLUDING", "EXECUTE", "EXISTS", "EXPLAIN", 
			"EXTRACT", "FALSE", "FILTER", "FIRST", "FOLLOWING", "FOR", "FORMAT", 
			"FROM", "FULL", "FUNCTIONS", "GRANT", "GRANTS", "GRAPHVIZ", "GROUP", 
			"GROUPING", "HAVING", "HOUR", "IF", "IN", "INCLUDING", "INNER", "INPUT", 
			"INSERT", "INTERSECT", "MINUSES", "INTERVAL", "INTO", "IO", "IS", "ISOLATION", 
			"JSON", "JOIN", "LAST", "LATERAL", "LEFT", "LEVEL", "LIKE", "LIMIT", 
			"LISTAGG", "LOCALTIME", "LOCALTIMESTAMP", "LOGICAL", "MAP", "MINUTE", 
			"MONTH", "MODIFY", "NATURAL", "NFC", "NFD", "NFKC", "NFKD", "NO", "NORMALIZE", 
			"NOT", "NULL", "NULLIF", "NULLS", "ON", "ONLY", "OPTION", "OR", "ORDER", 
			"ORDINALITY", "OUTER", "OUTPUT", "OVER", "OVERWRITE", "PARTITION", "PARTITIONS", 
			"PATH", "POSITION", "PRECEDING", "PREPARE", "PRIVILEGES", "PROPERTIES", 
			"PUBLIC", "RANGE", "READ", "RECURSIVE", "RENAME", "REPEATABLE", "REPLACE", 
			"RESET", "RESTRICT", "REVOKE", "RIGHT", "ROLLBACK", "ROLLUP", "ROW", 
			"ROWS", "SCHEMA", "SCHEMAS", "SECOND", "SELECT", "SERIALIZABLE", "SESSION", 
			"SET", "SETS", "SHOW", "SOME", "START", "STATS", "SUBSTRING", "SYSTEM", 
			"TABLE", "TABLES", "TABLESAMPLE", "TEXT", "THEN", "TIME", "TIMESTAMP", 
			"TO", "TRANSACTION", "TRUE", "TRY_CAST", "TYPE", "TRUNCATE", "UPDATE", 
			"UESCAPE", "UNBOUNDED", "UNCOMMITTED", "UNION", "UNNEST", "USE", "USING", 
			"VALIDATE", "VALUES", "VERBOSE", "VIEW", "WHEN", "WHERE", "WITH", "WITHIN", 
			"WORK", "WRITE", "YEAR", "ZONE", "SEMI", "SORT", "OFFSET", "DEFAULT", 
			"TABLESPACE", "EXTERNAL", "PURGE", "TEMPORARY", "PRIMARY", "KEY", "DISABLE", 
			"NOVALIDATE", "FOREIGN", "REFERENCES", "PARTITIONED", "CLUSTERED", "SORTED", 
			"BUCKETS", "SKEWED", "STORED", "DELIMITED", "FIELDS", "TERMINATED", "ESCAPED", 
			"COLLECTION", "ITEMS", "KEYS", "LINES", "DEFINED", "SEQUENCEFILE", "TEXTFILE", 
			"RCFILE", "ORC", "ORCFILE", "PARQUET", "AVRO", "JSONFILE", "INPUTFORMAT", 
			"OUTPUTFORMAT", "SERDE", "SERDEPROPERTIES", "LOCATION", "TBLPROPERTIES", 
			"RELY", "EXCHANGE", "RECOVER", "ARCHIVE", "UNARCHIVE", "IGNORE", "PROTECTION", 
			"TOUCH", "ENABLE", "NO_DROP", "OFFLINE", "COMPACT", "WAIT", "CONCATENATE", 
			"CHANGE", "AFTER", "DEFERRED", "REBUILD", "INDEX", "IDXPROPERTIES", "FUNCTION", 
			"JAR", "FILE", "DATABASE", "DBPROPERTIES", "OWNER", "RELOAD", "USER", 
			"ROLE", "FILEFORMAT", "LOAD", "LOCAL", "INPATH", "DIRECTORY", "VACUUM", 
			"LIST", "SUBPARTITION", "SUBPARTITIONS", "EVERY", "INCLUSIVE", "EXCLUSIVE", 
			"TEMPLATE", "ENCODING", "LOG", "ERRORS", "SEGMENT", "REJECT", "PERCENT", 
			"WRITABLE", "CHECK", "UNIQUE", "CONCURRENTLY", "BITMAP", "FORCE", "SPLIT", 
			"REINDEX", "FREEZE", "MSCK", "REPAIR", "FETCH", "DEFINITION", "LESS", 
			"THAN", "HASH", "STORE", "NOLOGGING", "STRUCT", "GLOBAL", "PRESERVE", 
			"AUTO_INCREMENT", "ENGINE", "CHARSET", "COMPRESS", "EXPRESS", "REPLICATED", 
			"NOCOPIES", "SPACE", "SHRINK", "KEY_BLOCK_SIZE", "KEY_DC_SIZE", "AUTOEXTEND", 
			"NEXT", "OFF", "MATCHED", "MERGE", "OUTFILE", "OPTIONALLY", "ENCLOSED", 
			"DOUBLE_ENCLOSED", "NULL_VALUE", "OUTFILEMODE", "HDFS", "WRITEMODE", 
			"NORMAL", "OVERWRITES", "FILECOUNT", "FILESIZE", "CHARACTER", "GBK", 
			"UTF8", "HEAD", "RMTSELECT", "INFILE", "DATA_FORMAT", "SEPARATOR", "BLANKS", 
			"AUTOFILL", "LENGTH", "TABLE_FIELDS", "MAX_BAD_RECORDS", "DATETIME", 
			"TRACE", "TRACE_PATH", "NOSPLIT", "PARALLEL", "MAX_DATA_PROCESSORS", 
			"MIN_CHUNK_SIZE", "SKIP_BAD_FILE", "NUM", "FILE_FORMAT", "IMMEDIATE", 
			"STARTING", "ENDING", "INITIALLY", "REFRESH", "ACTIVATE", "LOGGED", "EMPTY", 
			"SEQUENCE", "INCREMENT", "MINVALUE", "MAXVALUE", "OVERLAY", "PLACING", 
			"TRIM", "LTRIM", "RTRIM", "BOTH", "TO_CHAR", "AGE", "DATE_PART", "ISFINITE", 
			"JUSTIFY_DAYS", "JUSTIFY_HOURS", "JUSTIFY_INTERVAL", "INT", "UNSIGNED", 
			"REGEXP", "BINARY", "LEADING", "TRAILING", "RLIKE", "DATE_SUB", "ADDDATE", 
			"DATE_ADD", "YEAR_MONTH", "DAY_HOUR", "DAY_MINUTE", "DAY_SECOND", "HOUR_MINUTE", 
			"HOUR_SECOND", "MINUTE_SECOND", "UNKNOWN", "NULLEQ", "EQ", "NEQ", "LT", 
			"LTE", "GT", "GTE", "PLUS", "MINUS", "ASTERISK", "SLASH", "MATHPERCENT", 
			"MATHMOD", "CONCAT", "LOGICALAND", "LOGICALOR", "LOGICALEXCLUSIVEOR", 
			"LOGICALNOT", "XOR", "STRING", "UNICODE_STRING", "BINARY_LITERAL", "INTEGER_VALUE", 
			"DECIMAL_VALUE", "DOUBLE_VALUE", "IDENTIFIER", "DIGIT_IDENTIFIER", "QUOTED_IDENTIFIER", 
			"BACKQUOTED_IDENTIFIER", "TIME_WITH_TIME_ZONE", "TIMESTAMP_WITH_TIME_ZONE", 
			"INT_UNSIGNED", "DOUBLE_PRECISION", "SIMPLE_COMMENT", "BRACKETED_COMMENT", 
			"WS", "UNRECOGNIZED", "DELIMITER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GroovyExp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GroovyExpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StatementContext extends ParserRuleContext {
		public ValueExpressionContext valueExpression() {
			return getRuleContext(ValueExpressionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GroovyExpParser.EOF, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			valueExpression(0);
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueExpressionContext extends ParserRuleContext {
		public ValueExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueExpression; }
	 
		public ValueExpressionContext() { }
		public void copyFrom(ValueExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ValueExpressionDefaultContext extends ValueExpressionContext {
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public ValueExpressionDefaultContext(ValueExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterValueExpressionDefault(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitValueExpressionDefault(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitValueExpressionDefault(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithmeticBinaryContext extends ValueExpressionContext {
		public ValueExpressionContext left;
		public Token operator;
		public ValueExpressionContext right;
		public List<ValueExpressionContext> valueExpression() {
			return getRuleContexts(ValueExpressionContext.class);
		}
		public ValueExpressionContext valueExpression(int i) {
			return getRuleContext(ValueExpressionContext.class,i);
		}
		public TerminalNode ASTERISK() { return getToken(GroovyExpParser.ASTERISK, 0); }
		public TerminalNode SLASH() { return getToken(GroovyExpParser.SLASH, 0); }
		public TerminalNode MATHPERCENT() { return getToken(GroovyExpParser.MATHPERCENT, 0); }
		public TerminalNode MATHMOD() { return getToken(GroovyExpParser.MATHMOD, 0); }
		public TerminalNode PLUS() { return getToken(GroovyExpParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(GroovyExpParser.MINUS, 0); }
		public TerminalNode LOGICALAND() { return getToken(GroovyExpParser.LOGICALAND, 0); }
		public TerminalNode LOGICALOR() { return getToken(GroovyExpParser.LOGICALOR, 0); }
		public TerminalNode LOGICALEXCLUSIVEOR() { return getToken(GroovyExpParser.LOGICALEXCLUSIVEOR, 0); }
		public TerminalNode XOR() { return getToken(GroovyExpParser.XOR, 0); }
		public ArithmeticBinaryContext(ValueExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterArithmeticBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitArithmeticBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitArithmeticBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithmeticUnaryContext extends ValueExpressionContext {
		public Token operator;
		public ValueExpressionContext valueExpression() {
			return getRuleContext(ValueExpressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(GroovyExpParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(GroovyExpParser.PLUS, 0); }
		public TerminalNode LOGICALNOT() { return getToken(GroovyExpParser.LOGICALNOT, 0); }
		public ArithmeticUnaryContext(ValueExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterArithmeticUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitArithmeticUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitArithmeticUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueExpressionContext valueExpression() throws RecognitionException {
		return valueExpression(0);
	}

	private ValueExpressionContext valueExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValueExpressionContext _localctx = new ValueExpressionContext(_ctx, _parentState);
		ValueExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_valueExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case ADD:
			case ALL:
			case ALTER:
			case ANALYZE:
			case AND:
			case ANY:
			case ARRAY:
			case AS:
			case ASC:
			case AT:
			case BERNOULLI:
			case BETWEEN:
			case BY:
			case CALL:
			case CASCADE:
			case CASE:
			case CAST:
			case CATALOGS:
			case COLUMN:
			case COLUMNS:
			case COMMENT:
			case COMMIT:
			case COMMITTED:
			case CONSTRAINT:
			case CREATE:
			case CROSS:
			case CUBE:
			case CURRENT:
			case CURRENT_DATE:
			case CURRENT_PATH:
			case CURRENT_TIME:
			case CURRENT_TIMESTAMP:
			case CURRENT_USER:
			case DATA:
			case DATE:
			case DAY:
			case DEALLOCATE:
			case DELETE:
			case DESC:
			case DESCRIBE:
			case DISTINCT:
			case DISTRIBUTED:
			case DROP:
			case ELSE:
			case END:
			case ESCAPE:
			case EXCEPT:
			case EXCLUDING:
			case EXECUTE:
			case EXISTS:
			case EXPLAIN:
			case EXTRACT:
			case FALSE:
			case FILTER:
			case FIRST:
			case FOLLOWING:
			case FOR:
			case FORMAT:
			case FROM:
			case FULL:
			case FUNCTIONS:
			case GRANT:
			case GRANTS:
			case GRAPHVIZ:
			case GROUP:
			case GROUPING:
			case HAVING:
			case HOUR:
			case IF:
			case IN:
			case INCLUDING:
			case INNER:
			case INPUT:
			case INSERT:
			case INTERSECT:
			case MINUSES:
			case INTERVAL:
			case INTO:
			case IO:
			case IS:
			case ISOLATION:
			case JSON:
			case JOIN:
			case LAST:
			case LATERAL:
			case LEFT:
			case LEVEL:
			case LIKE:
			case LIMIT:
			case LISTAGG:
			case LOCALTIME:
			case LOCALTIMESTAMP:
			case LOGICAL:
			case MAP:
			case MINUTE:
			case MONTH:
			case MODIFY:
			case NATURAL:
			case NFC:
			case NFD:
			case NFKC:
			case NFKD:
			case NO:
			case NORMALIZE:
			case NOT:
			case NULL:
			case NULLIF:
			case NULLS:
			case ON:
			case ONLY:
			case OPTION:
			case OR:
			case ORDER:
			case ORDINALITY:
			case OUTER:
			case OUTPUT:
			case OVER:
			case OVERWRITE:
			case PARTITION:
			case PARTITIONS:
			case PATH:
			case POSITION:
			case PRECEDING:
			case PREPARE:
			case PRIVILEGES:
			case PROPERTIES:
			case PUBLIC:
			case RANGE:
			case READ:
			case RECURSIVE:
			case RENAME:
			case REPEATABLE:
			case REPLACE:
			case RESET:
			case RESTRICT:
			case REVOKE:
			case RIGHT:
			case ROLLBACK:
			case ROLLUP:
			case ROW:
			case ROWS:
			case SCHEMA:
			case SCHEMAS:
			case SECOND:
			case SELECT:
			case SERIALIZABLE:
			case SESSION:
			case SET:
			case SETS:
			case SHOW:
			case SOME:
			case START:
			case STATS:
			case SUBSTRING:
			case SYSTEM:
			case TABLE:
			case TABLES:
			case TABLESAMPLE:
			case TEXT:
			case THEN:
			case TIME:
			case TIMESTAMP:
			case TO:
			case TRANSACTION:
			case TRUE:
			case TRY_CAST:
			case TYPE:
			case TRUNCATE:
			case UPDATE:
			case UESCAPE:
			case UNBOUNDED:
			case UNCOMMITTED:
			case UNION:
			case UNNEST:
			case USE:
			case USING:
			case VALIDATE:
			case VALUES:
			case VERBOSE:
			case VIEW:
			case WHEN:
			case WHERE:
			case WITH:
			case WITHIN:
			case WORK:
			case WRITE:
			case YEAR:
			case ZONE:
			case SEMI:
			case SORT:
			case OFFSET:
			case DEFAULT:
			case TABLESPACE:
			case EXTERNAL:
			case PURGE:
			case TEMPORARY:
			case PRIMARY:
			case KEY:
			case DISABLE:
			case NOVALIDATE:
			case FOREIGN:
			case REFERENCES:
			case PARTITIONED:
			case CLUSTERED:
			case SORTED:
			case BUCKETS:
			case SKEWED:
			case STORED:
			case DELIMITED:
			case FIELDS:
			case TERMINATED:
			case ESCAPED:
			case COLLECTION:
			case ITEMS:
			case KEYS:
			case LINES:
			case DEFINED:
			case SEQUENCEFILE:
			case TEXTFILE:
			case RCFILE:
			case ORC:
			case ORCFILE:
			case PARQUET:
			case AVRO:
			case JSONFILE:
			case INPUTFORMAT:
			case OUTPUTFORMAT:
			case SERDE:
			case SERDEPROPERTIES:
			case LOCATION:
			case TBLPROPERTIES:
			case RELY:
			case EXCHANGE:
			case RECOVER:
			case ARCHIVE:
			case UNARCHIVE:
			case IGNORE:
			case PROTECTION:
			case TOUCH:
			case ENABLE:
			case NO_DROP:
			case OFFLINE:
			case COMPACT:
			case WAIT:
			case CONCATENATE:
			case CHANGE:
			case AFTER:
			case DEFERRED:
			case REBUILD:
			case INDEX:
			case IDXPROPERTIES:
			case FUNCTION:
			case JAR:
			case FILE:
			case DATABASE:
			case DBPROPERTIES:
			case OWNER:
			case RELOAD:
			case USER:
			case ROLE:
			case FILEFORMAT:
			case LOAD:
			case LOCAL:
			case INPATH:
			case DIRECTORY:
			case VACUUM:
			case LIST:
			case SUBPARTITION:
			case SUBPARTITIONS:
			case EVERY:
			case INCLUSIVE:
			case EXCLUSIVE:
			case TEMPLATE:
			case ENCODING:
			case LOG:
			case ERRORS:
			case SEGMENT:
			case REJECT:
			case PERCENT:
			case WRITABLE:
			case CHECK:
			case UNIQUE:
			case CONCURRENTLY:
			case BITMAP:
			case FORCE:
			case SPLIT:
			case REINDEX:
			case FREEZE:
			case MSCK:
			case REPAIR:
			case FETCH:
			case DEFINITION:
			case LESS:
			case THAN:
			case HASH:
			case NOLOGGING:
			case STRUCT:
			case GLOBAL:
			case PRESERVE:
			case AUTO_INCREMENT:
			case ENGINE:
			case CHARSET:
			case COMPRESS:
			case EXPRESS:
			case REPLICATED:
			case NOCOPIES:
			case SPACE:
			case SHRINK:
			case KEY_BLOCK_SIZE:
			case KEY_DC_SIZE:
			case AUTOEXTEND:
			case NEXT:
			case OFF:
			case MATCHED:
			case MERGE:
			case OUTFILE:
			case OPTIONALLY:
			case ENCLOSED:
			case DOUBLE_ENCLOSED:
			case NULL_VALUE:
			case OUTFILEMODE:
			case HDFS:
			case WRITEMODE:
			case NORMAL:
			case OVERWRITES:
			case FILECOUNT:
			case FILESIZE:
			case CHARACTER:
			case GBK:
			case UTF8:
			case HEAD:
			case RMTSELECT:
			case INFILE:
			case DATA_FORMAT:
			case SEPARATOR:
			case BLANKS:
			case AUTOFILL:
			case LENGTH:
			case TABLE_FIELDS:
			case MAX_BAD_RECORDS:
			case DATETIME:
			case TRACE:
			case TRACE_PATH:
			case NOSPLIT:
			case PARALLEL:
			case MAX_DATA_PROCESSORS:
			case MIN_CHUNK_SIZE:
			case SKIP_BAD_FILE:
			case NUM:
			case FILE_FORMAT:
			case IMMEDIATE:
			case STARTING:
			case ENDING:
			case INITIALLY:
			case REFRESH:
			case ACTIVATE:
			case LOGGED:
			case EMPTY:
			case SEQUENCE:
			case INCREMENT:
			case MINVALUE:
			case MAXVALUE:
			case OVERLAY:
			case PLACING:
			case TRIM:
			case LTRIM:
			case RTRIM:
			case BOTH:
			case TO_CHAR:
			case AGE:
			case DATE_PART:
			case ISFINITE:
			case INT:
			case UNSIGNED:
			case REGEXP:
			case BINARY:
			case LEADING:
			case TRAILING:
			case RLIKE:
			case DATE_SUB:
			case ADDDATE:
			case DATE_ADD:
			case YEAR_MONTH:
			case DAY_HOUR:
			case DAY_MINUTE:
			case DAY_SECOND:
			case HOUR_MINUTE:
			case HOUR_SECOND:
			case MINUTE_SECOND:
			case UNKNOWN:
			case STRING:
			case UNICODE_STRING:
			case BINARY_LITERAL:
			case INTEGER_VALUE:
			case DECIMAL_VALUE:
			case DOUBLE_VALUE:
			case IDENTIFIER:
			case DIGIT_IDENTIFIER:
			case QUOTED_IDENTIFIER:
			case BACKQUOTED_IDENTIFIER:
				{
				_localctx = new ValueExpressionDefaultContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(26);
				primaryExpression(0);
				}
				break;
			case PLUS:
			case MINUS:
			case LOGICALNOT:
				{
				_localctx = new ArithmeticUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(27);
				((ArithmeticUnaryContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 411)) & ~0x3f) == 0 && ((1L << (_la - 411)) & ((1L << (PLUS - 411)) | (1L << (MINUS - 411)) | (1L << (LOGICALNOT - 411)))) != 0)) ) {
					((ArithmeticUnaryContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(28);
				valueExpression(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArithmeticBinaryContext(new ValueExpressionContext(_parentctx, _parentState));
					((ArithmeticBinaryContext)_localctx).left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_valueExpression);
					setState(31);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(32);
					((ArithmeticBinaryContext)_localctx).operator = _input.LT(1);
					_la = _input.LA(1);
					if ( !(((((_la - 411)) & ~0x3f) == 0 && ((1L << (_la - 411)) & ((1L << (PLUS - 411)) | (1L << (MINUS - 411)) | (1L << (ASTERISK - 411)) | (1L << (SLASH - 411)) | (1L << (MATHPERCENT - 411)) | (1L << (MATHMOD - 411)) | (1L << (LOGICALAND - 411)) | (1L << (LOGICALOR - 411)) | (1L << (LOGICALEXCLUSIVEOR - 411)) | (1L << (XOR - 411)))) != 0)) ) {
						((ArithmeticBinaryContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(33);
					((ArithmeticBinaryContext)_localctx).right = valueExpression(2);
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	 
		public PrimaryExpressionContext() { }
		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinaryLiteralContext extends PrimaryExpressionContext {
		public TerminalNode BINARY_LITERAL() { return getToken(GroovyExpParser.BINARY_LITERAL, 0); }
		public BinaryLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterBinaryLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitBinaryLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitBinaryLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DereferenceContext extends PrimaryExpressionContext {
		public PrimaryExpressionContext base;
		public IdentifierContext fieldName;
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DereferenceContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterDereference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitDereference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitDereference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaContext extends PrimaryExpressionContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public ValueExpressionContext valueExpression() {
			return getRuleContext(ValueExpressionContext.class,0);
		}
		public LambdaContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColumnReferenceContext extends PrimaryExpressionContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ColumnReferenceContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterColumnReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitColumnReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitColumnReference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullLiteralContext extends PrimaryExpressionContext {
		public TerminalNode NULL() { return getToken(GroovyExpParser.NULL, 0); }
		public NullLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterNullLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitNullLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitNullLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptContext extends PrimaryExpressionContext {
		public PrimaryExpressionContext value;
		public ValueExpressionContext index;
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public ValueExpressionContext valueExpression() {
			return getRuleContext(ValueExpressionContext.class,0);
		}
		public SubscriptContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterSubscript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitSubscript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitSubscript(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringLiteralContext extends PrimaryExpressionContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StringLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallContext extends PrimaryExpressionContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public List<ValueExpressionContext> valueExpression() {
			return getRuleContexts(ValueExpressionContext.class);
		}
		public ValueExpressionContext valueExpression(int i) {
			return getRuleContext(ValueExpressionContext.class,i);
		}
		public FunctionCallContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubqueryExpressionContext extends PrimaryExpressionContext {
		public ValueExpressionContext valueExpression() {
			return getRuleContext(ValueExpressionContext.class,0);
		}
		public SubqueryExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterSubqueryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitSubqueryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitSubqueryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumericLiteralContext extends PrimaryExpressionContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NumericLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitNumericLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitNumericLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanLiteralContext extends PrimaryExpressionContext {
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public BooleanLiteralContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitBooleanLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitBooleanLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		return primaryExpression(0);
	}

	private PrimaryExpressionContext primaryExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, _parentState);
		PrimaryExpressionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_primaryExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new NullLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(40);
				match(NULL);
				}
				break;
			case 2:
				{
				_localctx = new NumericLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				number();
				}
				break;
			case 3:
				{
				_localctx = new BooleanLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42);
				booleanValue();
				}
				break;
			case 4:
				{
				_localctx = new StringLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				string();
				}
				break;
			case 5:
				{
				_localctx = new BinaryLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(44);
				match(BINARY_LITERAL);
				}
				break;
			case 6:
				{
				_localctx = new FunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(45);
				qualifiedName();
				setState(46);
				match(T__0);
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << ADD) | (1L << ALL) | (1L << ALTER) | (1L << ANALYZE) | (1L << AND) | (1L << ANY) | (1L << ARRAY) | (1L << AS) | (1L << ASC) | (1L << AT) | (1L << BERNOULLI) | (1L << BETWEEN) | (1L << BY) | (1L << CALL) | (1L << CASCADE) | (1L << CASE) | (1L << CAST) | (1L << CATALOGS) | (1L << COLUMN) | (1L << COLUMNS) | (1L << COMMENT) | (1L << COMMIT) | (1L << COMMITTED) | (1L << CONSTRAINT) | (1L << CREATE) | (1L << CROSS) | (1L << CUBE) | (1L << CURRENT) | (1L << CURRENT_DATE) | (1L << CURRENT_PATH) | (1L << CURRENT_TIME) | (1L << CURRENT_TIMESTAMP) | (1L << CURRENT_USER) | (1L << DATA) | (1L << DATE) | (1L << DAY) | (1L << DEALLOCATE) | (1L << DELETE) | (1L << DESC) | (1L << DESCRIBE) | (1L << DISTINCT) | (1L << DISTRIBUTED) | (1L << DROP) | (1L << ELSE) | (1L << END) | (1L << ESCAPE) | (1L << EXCEPT) | (1L << EXCLUDING) | (1L << EXECUTE) | (1L << EXISTS) | (1L << EXPLAIN) | (1L << EXTRACT) | (1L << FALSE) | (1L << FILTER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FIRST - 64)) | (1L << (FOLLOWING - 64)) | (1L << (FOR - 64)) | (1L << (FORMAT - 64)) | (1L << (FROM - 64)) | (1L << (FULL - 64)) | (1L << (FUNCTIONS - 64)) | (1L << (GRANT - 64)) | (1L << (GRANTS - 64)) | (1L << (GRAPHVIZ - 64)) | (1L << (GROUP - 64)) | (1L << (GROUPING - 64)) | (1L << (HAVING - 64)) | (1L << (HOUR - 64)) | (1L << (IF - 64)) | (1L << (IN - 64)) | (1L << (INCLUDING - 64)) | (1L << (INNER - 64)) | (1L << (INPUT - 64)) | (1L << (INSERT - 64)) | (1L << (INTERSECT - 64)) | (1L << (MINUSES - 64)) | (1L << (INTERVAL - 64)) | (1L << (INTO - 64)) | (1L << (IO - 64)) | (1L << (IS - 64)) | (1L << (ISOLATION - 64)) | (1L << (JSON - 64)) | (1L << (JOIN - 64)) | (1L << (LAST - 64)) | (1L << (LATERAL - 64)) | (1L << (LEFT - 64)) | (1L << (LEVEL - 64)) | (1L << (LIKE - 64)) | (1L << (LIMIT - 64)) | (1L << (LISTAGG - 64)) | (1L << (LOCALTIME - 64)) | (1L << (LOCALTIMESTAMP - 64)) | (1L << (LOGICAL - 64)) | (1L << (MAP - 64)) | (1L << (MINUTE - 64)) | (1L << (MONTH - 64)) | (1L << (MODIFY - 64)) | (1L << (NATURAL - 64)) | (1L << (NFC - 64)) | (1L << (NFD - 64)) | (1L << (NFKC - 64)) | (1L << (NFKD - 64)) | (1L << (NO - 64)) | (1L << (NORMALIZE - 64)) | (1L << (NOT - 64)) | (1L << (NULL - 64)) | (1L << (NULLIF - 64)) | (1L << (NULLS - 64)) | (1L << (ON - 64)) | (1L << (ONLY - 64)) | (1L << (OPTION - 64)) | (1L << (OR - 64)) | (1L << (ORDER - 64)) | (1L << (ORDINALITY - 64)) | (1L << (OUTER - 64)) | (1L << (OUTPUT - 64)) | (1L << (OVER - 64)) | (1L << (OVERWRITE - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (PARTITION - 128)) | (1L << (PARTITIONS - 128)) | (1L << (PATH - 128)) | (1L << (POSITION - 128)) | (1L << (PRECEDING - 128)) | (1L << (PREPARE - 128)) | (1L << (PRIVILEGES - 128)) | (1L << (PROPERTIES - 128)) | (1L << (PUBLIC - 128)) | (1L << (RANGE - 128)) | (1L << (READ - 128)) | (1L << (RECURSIVE - 128)) | (1L << (RENAME - 128)) | (1L << (REPEATABLE - 128)) | (1L << (REPLACE - 128)) | (1L << (RESET - 128)) | (1L << (RESTRICT - 128)) | (1L << (REVOKE - 128)) | (1L << (RIGHT - 128)) | (1L << (ROLLBACK - 128)) | (1L << (ROLLUP - 128)) | (1L << (ROW - 128)) | (1L << (ROWS - 128)) | (1L << (SCHEMA - 128)) | (1L << (SCHEMAS - 128)) | (1L << (SECOND - 128)) | (1L << (SELECT - 128)) | (1L << (SERIALIZABLE - 128)) | (1L << (SESSION - 128)) | (1L << (SET - 128)) | (1L << (SETS - 128)) | (1L << (SHOW - 128)) | (1L << (SOME - 128)) | (1L << (START - 128)) | (1L << (STATS - 128)) | (1L << (SUBSTRING - 128)) | (1L << (SYSTEM - 128)) | (1L << (TABLE - 128)) | (1L << (TABLES - 128)) | (1L << (TABLESAMPLE - 128)) | (1L << (TEXT - 128)) | (1L << (THEN - 128)) | (1L << (TIME - 128)) | (1L << (TIMESTAMP - 128)) | (1L << (TO - 128)) | (1L << (TRANSACTION - 128)) | (1L << (TRUE - 128)) | (1L << (TRY_CAST - 128)) | (1L << (TYPE - 128)) | (1L << (TRUNCATE - 128)) | (1L << (UPDATE - 128)) | (1L << (UESCAPE - 128)) | (1L << (UNBOUNDED - 128)) | (1L << (UNCOMMITTED - 128)) | (1L << (UNION - 128)) | (1L << (UNNEST - 128)) | (1L << (USE - 128)) | (1L << (USING - 128)) | (1L << (VALIDATE - 128)) | (1L << (VALUES - 128)) | (1L << (VERBOSE - 128)) | (1L << (VIEW - 128)) | (1L << (WHEN - 128)) | (1L << (WHERE - 128)))) != 0) || ((((_la - 192)) & ~0x3f) == 0 && ((1L << (_la - 192)) & ((1L << (WITH - 192)) | (1L << (WITHIN - 192)) | (1L << (WORK - 192)) | (1L << (WRITE - 192)) | (1L << (YEAR - 192)) | (1L << (ZONE - 192)) | (1L << (SEMI - 192)) | (1L << (SORT - 192)) | (1L << (OFFSET - 192)) | (1L << (DEFAULT - 192)) | (1L << (TABLESPACE - 192)) | (1L << (EXTERNAL - 192)) | (1L << (PURGE - 192)) | (1L << (TEMPORARY - 192)) | (1L << (PRIMARY - 192)) | (1L << (KEY - 192)) | (1L << (DISABLE - 192)) | (1L << (NOVALIDATE - 192)) | (1L << (FOREIGN - 192)) | (1L << (REFERENCES - 192)) | (1L << (PARTITIONED - 192)) | (1L << (CLUSTERED - 192)) | (1L << (SORTED - 192)) | (1L << (BUCKETS - 192)) | (1L << (SKEWED - 192)) | (1L << (STORED - 192)) | (1L << (DELIMITED - 192)) | (1L << (FIELDS - 192)) | (1L << (TERMINATED - 192)) | (1L << (ESCAPED - 192)) | (1L << (COLLECTION - 192)) | (1L << (ITEMS - 192)) | (1L << (KEYS - 192)) | (1L << (LINES - 192)) | (1L << (DEFINED - 192)) | (1L << (SEQUENCEFILE - 192)) | (1L << (TEXTFILE - 192)) | (1L << (RCFILE - 192)) | (1L << (ORC - 192)) | (1L << (ORCFILE - 192)) | (1L << (PARQUET - 192)) | (1L << (AVRO - 192)) | (1L << (JSONFILE - 192)) | (1L << (INPUTFORMAT - 192)) | (1L << (OUTPUTFORMAT - 192)) | (1L << (SERDE - 192)) | (1L << (SERDEPROPERTIES - 192)) | (1L << (LOCATION - 192)) | (1L << (TBLPROPERTIES - 192)) | (1L << (RELY - 192)) | (1L << (EXCHANGE - 192)) | (1L << (RECOVER - 192)) | (1L << (ARCHIVE - 192)) | (1L << (UNARCHIVE - 192)) | (1L << (IGNORE - 192)) | (1L << (PROTECTION - 192)) | (1L << (TOUCH - 192)) | (1L << (ENABLE - 192)) | (1L << (NO_DROP - 192)) | (1L << (OFFLINE - 192)) | (1L << (COMPACT - 192)) | (1L << (WAIT - 192)) | (1L << (CONCATENATE - 192)) | (1L << (CHANGE - 192)))) != 0) || ((((_la - 256)) & ~0x3f) == 0 && ((1L << (_la - 256)) & ((1L << (AFTER - 256)) | (1L << (DEFERRED - 256)) | (1L << (REBUILD - 256)) | (1L << (INDEX - 256)) | (1L << (IDXPROPERTIES - 256)) | (1L << (FUNCTION - 256)) | (1L << (JAR - 256)) | (1L << (FILE - 256)) | (1L << (DATABASE - 256)) | (1L << (DBPROPERTIES - 256)) | (1L << (OWNER - 256)) | (1L << (RELOAD - 256)) | (1L << (USER - 256)) | (1L << (ROLE - 256)) | (1L << (FILEFORMAT - 256)) | (1L << (LOAD - 256)) | (1L << (LOCAL - 256)) | (1L << (INPATH - 256)) | (1L << (DIRECTORY - 256)) | (1L << (VACUUM - 256)) | (1L << (LIST - 256)) | (1L << (SUBPARTITION - 256)) | (1L << (SUBPARTITIONS - 256)) | (1L << (EVERY - 256)) | (1L << (INCLUSIVE - 256)) | (1L << (EXCLUSIVE - 256)) | (1L << (TEMPLATE - 256)) | (1L << (ENCODING - 256)) | (1L << (LOG - 256)) | (1L << (ERRORS - 256)) | (1L << (SEGMENT - 256)) | (1L << (REJECT - 256)) | (1L << (PERCENT - 256)) | (1L << (WRITABLE - 256)) | (1L << (CHECK - 256)) | (1L << (UNIQUE - 256)) | (1L << (CONCURRENTLY - 256)) | (1L << (BITMAP - 256)) | (1L << (FORCE - 256)) | (1L << (SPLIT - 256)) | (1L << (REINDEX - 256)) | (1L << (FREEZE - 256)) | (1L << (MSCK - 256)) | (1L << (REPAIR - 256)) | (1L << (FETCH - 256)) | (1L << (DEFINITION - 256)) | (1L << (LESS - 256)) | (1L << (THAN - 256)) | (1L << (HASH - 256)) | (1L << (NOLOGGING - 256)) | (1L << (STRUCT - 256)) | (1L << (GLOBAL - 256)) | (1L << (PRESERVE - 256)) | (1L << (AUTO_INCREMENT - 256)) | (1L << (ENGINE - 256)) | (1L << (CHARSET - 256)) | (1L << (COMPRESS - 256)) | (1L << (EXPRESS - 256)) | (1L << (REPLICATED - 256)) | (1L << (NOCOPIES - 256)) | (1L << (SPACE - 256)) | (1L << (SHRINK - 256)) | (1L << (KEY_BLOCK_SIZE - 256)))) != 0) || ((((_la - 320)) & ~0x3f) == 0 && ((1L << (_la - 320)) & ((1L << (KEY_DC_SIZE - 320)) | (1L << (AUTOEXTEND - 320)) | (1L << (NEXT - 320)) | (1L << (OFF - 320)) | (1L << (MATCHED - 320)) | (1L << (MERGE - 320)) | (1L << (OUTFILE - 320)) | (1L << (OPTIONALLY - 320)) | (1L << (ENCLOSED - 320)) | (1L << (DOUBLE_ENCLOSED - 320)) | (1L << (NULL_VALUE - 320)) | (1L << (OUTFILEMODE - 320)) | (1L << (HDFS - 320)) | (1L << (WRITEMODE - 320)) | (1L << (NORMAL - 320)) | (1L << (OVERWRITES - 320)) | (1L << (FILECOUNT - 320)) | (1L << (FILESIZE - 320)) | (1L << (CHARACTER - 320)) | (1L << (GBK - 320)) | (1L << (UTF8 - 320)) | (1L << (HEAD - 320)) | (1L << (RMTSELECT - 320)) | (1L << (INFILE - 320)) | (1L << (DATA_FORMAT - 320)) | (1L << (SEPARATOR - 320)) | (1L << (BLANKS - 320)) | (1L << (AUTOFILL - 320)) | (1L << (LENGTH - 320)) | (1L << (TABLE_FIELDS - 320)) | (1L << (MAX_BAD_RECORDS - 320)) | (1L << (DATETIME - 320)) | (1L << (TRACE - 320)) | (1L << (TRACE_PATH - 320)) | (1L << (NOSPLIT - 320)) | (1L << (PARALLEL - 320)) | (1L << (MAX_DATA_PROCESSORS - 320)) | (1L << (MIN_CHUNK_SIZE - 320)) | (1L << (SKIP_BAD_FILE - 320)) | (1L << (NUM - 320)) | (1L << (FILE_FORMAT - 320)) | (1L << (IMMEDIATE - 320)) | (1L << (STARTING - 320)) | (1L << (ENDING - 320)) | (1L << (INITIALLY - 320)) | (1L << (REFRESH - 320)) | (1L << (ACTIVATE - 320)) | (1L << (LOGGED - 320)) | (1L << (EMPTY - 320)) | (1L << (SEQUENCE - 320)) | (1L << (INCREMENT - 320)) | (1L << (MINVALUE - 320)) | (1L << (MAXVALUE - 320)) | (1L << (OVERLAY - 320)) | (1L << (PLACING - 320)) | (1L << (TRIM - 320)) | (1L << (LTRIM - 320)) | (1L << (RTRIM - 320)) | (1L << (BOTH - 320)) | (1L << (TO_CHAR - 320)) | (1L << (AGE - 320)) | (1L << (DATE_PART - 320)) | (1L << (ISFINITE - 320)))) != 0) || ((((_la - 386)) & ~0x3f) == 0 && ((1L << (_la - 386)) & ((1L << (INT - 386)) | (1L << (UNSIGNED - 386)) | (1L << (REGEXP - 386)) | (1L << (BINARY - 386)) | (1L << (LEADING - 386)) | (1L << (TRAILING - 386)) | (1L << (RLIKE - 386)) | (1L << (DATE_SUB - 386)) | (1L << (ADDDATE - 386)) | (1L << (DATE_ADD - 386)) | (1L << (YEAR_MONTH - 386)) | (1L << (DAY_HOUR - 386)) | (1L << (DAY_MINUTE - 386)) | (1L << (DAY_SECOND - 386)) | (1L << (HOUR_MINUTE - 386)) | (1L << (HOUR_SECOND - 386)) | (1L << (MINUTE_SECOND - 386)) | (1L << (UNKNOWN - 386)) | (1L << (PLUS - 386)) | (1L << (MINUS - 386)) | (1L << (LOGICALNOT - 386)) | (1L << (STRING - 386)) | (1L << (UNICODE_STRING - 386)) | (1L << (BINARY_LITERAL - 386)) | (1L << (INTEGER_VALUE - 386)) | (1L << (DECIMAL_VALUE - 386)) | (1L << (DOUBLE_VALUE - 386)) | (1L << (IDENTIFIER - 386)) | (1L << (DIGIT_IDENTIFIER - 386)) | (1L << (QUOTED_IDENTIFIER - 386)) | (1L << (BACKQUOTED_IDENTIFIER - 386)))) != 0)) {
					{
					setState(47);
					valueExpression(0);
					setState(52);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(48);
						match(T__1);
						setState(49);
						valueExpression(0);
						}
						}
						setState(54);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(57);
				match(T__2);
				}
				break;
			case 7:
				{
				_localctx = new LambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				identifier(0);
				setState(60);
				match(T__3);
				setState(61);
				valueExpression(0);
				}
				break;
			case 8:
				{
				_localctx = new LambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				match(T__0);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALL) | (1L << ALTER) | (1L << ANALYZE) | (1L << AND) | (1L << ANY) | (1L << ARRAY) | (1L << AS) | (1L << ASC) | (1L << AT) | (1L << BERNOULLI) | (1L << BETWEEN) | (1L << BY) | (1L << CALL) | (1L << CASCADE) | (1L << CASE) | (1L << CAST) | (1L << CATALOGS) | (1L << COLUMN) | (1L << COLUMNS) | (1L << COMMENT) | (1L << COMMIT) | (1L << COMMITTED) | (1L << CONSTRAINT) | (1L << CREATE) | (1L << CROSS) | (1L << CUBE) | (1L << CURRENT) | (1L << CURRENT_DATE) | (1L << CURRENT_PATH) | (1L << CURRENT_TIME) | (1L << CURRENT_TIMESTAMP) | (1L << CURRENT_USER) | (1L << DATA) | (1L << DATE) | (1L << DAY) | (1L << DEALLOCATE) | (1L << DELETE) | (1L << DESC) | (1L << DESCRIBE) | (1L << DISTINCT) | (1L << DISTRIBUTED) | (1L << DROP) | (1L << ELSE) | (1L << END) | (1L << ESCAPE) | (1L << EXCEPT) | (1L << EXCLUDING) | (1L << EXECUTE) | (1L << EXISTS) | (1L << EXPLAIN) | (1L << EXTRACT) | (1L << FALSE) | (1L << FILTER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FIRST - 64)) | (1L << (FOLLOWING - 64)) | (1L << (FOR - 64)) | (1L << (FORMAT - 64)) | (1L << (FROM - 64)) | (1L << (FULL - 64)) | (1L << (FUNCTIONS - 64)) | (1L << (GRANT - 64)) | (1L << (GRANTS - 64)) | (1L << (GRAPHVIZ - 64)) | (1L << (GROUP - 64)) | (1L << (GROUPING - 64)) | (1L << (HAVING - 64)) | (1L << (HOUR - 64)) | (1L << (IF - 64)) | (1L << (IN - 64)) | (1L << (INCLUDING - 64)) | (1L << (INNER - 64)) | (1L << (INPUT - 64)) | (1L << (INSERT - 64)) | (1L << (INTERSECT - 64)) | (1L << (MINUSES - 64)) | (1L << (INTERVAL - 64)) | (1L << (INTO - 64)) | (1L << (IO - 64)) | (1L << (IS - 64)) | (1L << (ISOLATION - 64)) | (1L << (JSON - 64)) | (1L << (JOIN - 64)) | (1L << (LAST - 64)) | (1L << (LATERAL - 64)) | (1L << (LEFT - 64)) | (1L << (LEVEL - 64)) | (1L << (LIKE - 64)) | (1L << (LIMIT - 64)) | (1L << (LISTAGG - 64)) | (1L << (LOCALTIME - 64)) | (1L << (LOCALTIMESTAMP - 64)) | (1L << (LOGICAL - 64)) | (1L << (MAP - 64)) | (1L << (MINUTE - 64)) | (1L << (MONTH - 64)) | (1L << (MODIFY - 64)) | (1L << (NATURAL - 64)) | (1L << (NFC - 64)) | (1L << (NFD - 64)) | (1L << (NFKC - 64)) | (1L << (NFKD - 64)) | (1L << (NO - 64)) | (1L << (NORMALIZE - 64)) | (1L << (NOT - 64)) | (1L << (NULL - 64)) | (1L << (NULLIF - 64)) | (1L << (NULLS - 64)) | (1L << (ON - 64)) | (1L << (ONLY - 64)) | (1L << (OPTION - 64)) | (1L << (OR - 64)) | (1L << (ORDER - 64)) | (1L << (ORDINALITY - 64)) | (1L << (OUTER - 64)) | (1L << (OUTPUT - 64)) | (1L << (OVER - 64)) | (1L << (OVERWRITE - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (PARTITION - 128)) | (1L << (PARTITIONS - 128)) | (1L << (PATH - 128)) | (1L << (POSITION - 128)) | (1L << (PRECEDING - 128)) | (1L << (PREPARE - 128)) | (1L << (PRIVILEGES - 128)) | (1L << (PROPERTIES - 128)) | (1L << (PUBLIC - 128)) | (1L << (RANGE - 128)) | (1L << (READ - 128)) | (1L << (RECURSIVE - 128)) | (1L << (RENAME - 128)) | (1L << (REPEATABLE - 128)) | (1L << (REPLACE - 128)) | (1L << (RESET - 128)) | (1L << (RESTRICT - 128)) | (1L << (REVOKE - 128)) | (1L << (RIGHT - 128)) | (1L << (ROLLBACK - 128)) | (1L << (ROLLUP - 128)) | (1L << (ROW - 128)) | (1L << (ROWS - 128)) | (1L << (SCHEMA - 128)) | (1L << (SCHEMAS - 128)) | (1L << (SECOND - 128)) | (1L << (SELECT - 128)) | (1L << (SERIALIZABLE - 128)) | (1L << (SESSION - 128)) | (1L << (SET - 128)) | (1L << (SETS - 128)) | (1L << (SHOW - 128)) | (1L << (SOME - 128)) | (1L << (START - 128)) | (1L << (STATS - 128)) | (1L << (SUBSTRING - 128)) | (1L << (SYSTEM - 128)) | (1L << (TABLE - 128)) | (1L << (TABLES - 128)) | (1L << (TABLESAMPLE - 128)) | (1L << (TEXT - 128)) | (1L << (THEN - 128)) | (1L << (TIME - 128)) | (1L << (TIMESTAMP - 128)) | (1L << (TO - 128)) | (1L << (TRANSACTION - 128)) | (1L << (TRUE - 128)) | (1L << (TRY_CAST - 128)) | (1L << (TYPE - 128)) | (1L << (TRUNCATE - 128)) | (1L << (UPDATE - 128)) | (1L << (UESCAPE - 128)) | (1L << (UNBOUNDED - 128)) | (1L << (UNCOMMITTED - 128)) | (1L << (UNION - 128)) | (1L << (UNNEST - 128)) | (1L << (USE - 128)) | (1L << (USING - 128)) | (1L << (VALIDATE - 128)) | (1L << (VALUES - 128)) | (1L << (VERBOSE - 128)) | (1L << (VIEW - 128)) | (1L << (WHEN - 128)) | (1L << (WHERE - 128)))) != 0) || ((((_la - 192)) & ~0x3f) == 0 && ((1L << (_la - 192)) & ((1L << (WITH - 192)) | (1L << (WITHIN - 192)) | (1L << (WORK - 192)) | (1L << (WRITE - 192)) | (1L << (YEAR - 192)) | (1L << (ZONE - 192)) | (1L << (SEMI - 192)) | (1L << (SORT - 192)) | (1L << (OFFSET - 192)) | (1L << (DEFAULT - 192)) | (1L << (TABLESPACE - 192)) | (1L << (EXTERNAL - 192)) | (1L << (PURGE - 192)) | (1L << (TEMPORARY - 192)) | (1L << (PRIMARY - 192)) | (1L << (KEY - 192)) | (1L << (DISABLE - 192)) | (1L << (NOVALIDATE - 192)) | (1L << (FOREIGN - 192)) | (1L << (REFERENCES - 192)) | (1L << (PARTITIONED - 192)) | (1L << (CLUSTERED - 192)) | (1L << (SORTED - 192)) | (1L << (BUCKETS - 192)) | (1L << (SKEWED - 192)) | (1L << (STORED - 192)) | (1L << (DELIMITED - 192)) | (1L << (FIELDS - 192)) | (1L << (TERMINATED - 192)) | (1L << (ESCAPED - 192)) | (1L << (COLLECTION - 192)) | (1L << (ITEMS - 192)) | (1L << (KEYS - 192)) | (1L << (LINES - 192)) | (1L << (DEFINED - 192)) | (1L << (SEQUENCEFILE - 192)) | (1L << (TEXTFILE - 192)) | (1L << (RCFILE - 192)) | (1L << (ORC - 192)) | (1L << (ORCFILE - 192)) | (1L << (PARQUET - 192)) | (1L << (AVRO - 192)) | (1L << (JSONFILE - 192)) | (1L << (INPUTFORMAT - 192)) | (1L << (OUTPUTFORMAT - 192)) | (1L << (SERDE - 192)) | (1L << (SERDEPROPERTIES - 192)) | (1L << (LOCATION - 192)) | (1L << (TBLPROPERTIES - 192)) | (1L << (RELY - 192)) | (1L << (EXCHANGE - 192)) | (1L << (RECOVER - 192)) | (1L << (ARCHIVE - 192)) | (1L << (UNARCHIVE - 192)) | (1L << (IGNORE - 192)) | (1L << (PROTECTION - 192)) | (1L << (TOUCH - 192)) | (1L << (ENABLE - 192)) | (1L << (NO_DROP - 192)) | (1L << (OFFLINE - 192)) | (1L << (COMPACT - 192)) | (1L << (WAIT - 192)) | (1L << (CONCATENATE - 192)) | (1L << (CHANGE - 192)))) != 0) || ((((_la - 256)) & ~0x3f) == 0 && ((1L << (_la - 256)) & ((1L << (AFTER - 256)) | (1L << (DEFERRED - 256)) | (1L << (REBUILD - 256)) | (1L << (INDEX - 256)) | (1L << (IDXPROPERTIES - 256)) | (1L << (FUNCTION - 256)) | (1L << (JAR - 256)) | (1L << (FILE - 256)) | (1L << (DATABASE - 256)) | (1L << (DBPROPERTIES - 256)) | (1L << (OWNER - 256)) | (1L << (RELOAD - 256)) | (1L << (USER - 256)) | (1L << (ROLE - 256)) | (1L << (FILEFORMAT - 256)) | (1L << (LOAD - 256)) | (1L << (LOCAL - 256)) | (1L << (INPATH - 256)) | (1L << (DIRECTORY - 256)) | (1L << (VACUUM - 256)) | (1L << (LIST - 256)) | (1L << (SUBPARTITION - 256)) | (1L << (SUBPARTITIONS - 256)) | (1L << (EVERY - 256)) | (1L << (INCLUSIVE - 256)) | (1L << (EXCLUSIVE - 256)) | (1L << (TEMPLATE - 256)) | (1L << (ENCODING - 256)) | (1L << (LOG - 256)) | (1L << (ERRORS - 256)) | (1L << (SEGMENT - 256)) | (1L << (REJECT - 256)) | (1L << (PERCENT - 256)) | (1L << (WRITABLE - 256)) | (1L << (CHECK - 256)) | (1L << (UNIQUE - 256)) | (1L << (CONCURRENTLY - 256)) | (1L << (BITMAP - 256)) | (1L << (FORCE - 256)) | (1L << (SPLIT - 256)) | (1L << (REINDEX - 256)) | (1L << (FREEZE - 256)) | (1L << (MSCK - 256)) | (1L << (REPAIR - 256)) | (1L << (FETCH - 256)) | (1L << (DEFINITION - 256)) | (1L << (LESS - 256)) | (1L << (THAN - 256)) | (1L << (HASH - 256)) | (1L << (NOLOGGING - 256)) | (1L << (STRUCT - 256)) | (1L << (GLOBAL - 256)) | (1L << (PRESERVE - 256)) | (1L << (AUTO_INCREMENT - 256)) | (1L << (ENGINE - 256)) | (1L << (CHARSET - 256)) | (1L << (COMPRESS - 256)) | (1L << (EXPRESS - 256)) | (1L << (REPLICATED - 256)) | (1L << (NOCOPIES - 256)) | (1L << (SPACE - 256)) | (1L << (SHRINK - 256)) | (1L << (KEY_BLOCK_SIZE - 256)))) != 0) || ((((_la - 320)) & ~0x3f) == 0 && ((1L << (_la - 320)) & ((1L << (KEY_DC_SIZE - 320)) | (1L << (AUTOEXTEND - 320)) | (1L << (NEXT - 320)) | (1L << (OFF - 320)) | (1L << (MATCHED - 320)) | (1L << (MERGE - 320)) | (1L << (OUTFILE - 320)) | (1L << (OPTIONALLY - 320)) | (1L << (ENCLOSED - 320)) | (1L << (DOUBLE_ENCLOSED - 320)) | (1L << (NULL_VALUE - 320)) | (1L << (OUTFILEMODE - 320)) | (1L << (HDFS - 320)) | (1L << (WRITEMODE - 320)) | (1L << (NORMAL - 320)) | (1L << (OVERWRITES - 320)) | (1L << (FILECOUNT - 320)) | (1L << (FILESIZE - 320)) | (1L << (CHARACTER - 320)) | (1L << (GBK - 320)) | (1L << (UTF8 - 320)) | (1L << (HEAD - 320)) | (1L << (RMTSELECT - 320)) | (1L << (INFILE - 320)) | (1L << (DATA_FORMAT - 320)) | (1L << (SEPARATOR - 320)) | (1L << (BLANKS - 320)) | (1L << (AUTOFILL - 320)) | (1L << (LENGTH - 320)) | (1L << (TABLE_FIELDS - 320)) | (1L << (MAX_BAD_RECORDS - 320)) | (1L << (DATETIME - 320)) | (1L << (TRACE - 320)) | (1L << (TRACE_PATH - 320)) | (1L << (NOSPLIT - 320)) | (1L << (PARALLEL - 320)) | (1L << (MAX_DATA_PROCESSORS - 320)) | (1L << (MIN_CHUNK_SIZE - 320)) | (1L << (SKIP_BAD_FILE - 320)) | (1L << (NUM - 320)) | (1L << (FILE_FORMAT - 320)) | (1L << (IMMEDIATE - 320)) | (1L << (STARTING - 320)) | (1L << (ENDING - 320)) | (1L << (INITIALLY - 320)) | (1L << (REFRESH - 320)) | (1L << (ACTIVATE - 320)) | (1L << (LOGGED - 320)) | (1L << (EMPTY - 320)) | (1L << (SEQUENCE - 320)) | (1L << (INCREMENT - 320)) | (1L << (MINVALUE - 320)) | (1L << (MAXVALUE - 320)) | (1L << (OVERLAY - 320)) | (1L << (PLACING - 320)) | (1L << (TRIM - 320)) | (1L << (LTRIM - 320)) | (1L << (RTRIM - 320)) | (1L << (BOTH - 320)) | (1L << (TO_CHAR - 320)) | (1L << (AGE - 320)) | (1L << (DATE_PART - 320)) | (1L << (ISFINITE - 320)))) != 0) || ((((_la - 386)) & ~0x3f) == 0 && ((1L << (_la - 386)) & ((1L << (INT - 386)) | (1L << (UNSIGNED - 386)) | (1L << (REGEXP - 386)) | (1L << (BINARY - 386)) | (1L << (LEADING - 386)) | (1L << (TRAILING - 386)) | (1L << (RLIKE - 386)) | (1L << (DATE_SUB - 386)) | (1L << (ADDDATE - 386)) | (1L << (DATE_ADD - 386)) | (1L << (YEAR_MONTH - 386)) | (1L << (DAY_HOUR - 386)) | (1L << (DAY_MINUTE - 386)) | (1L << (DAY_SECOND - 386)) | (1L << (HOUR_MINUTE - 386)) | (1L << (HOUR_SECOND - 386)) | (1L << (MINUTE_SECOND - 386)) | (1L << (UNKNOWN - 386)) | (1L << (IDENTIFIER - 386)) | (1L << (DIGIT_IDENTIFIER - 386)) | (1L << (QUOTED_IDENTIFIER - 386)) | (1L << (BACKQUOTED_IDENTIFIER - 386)))) != 0)) {
					{
					setState(64);
					identifier(0);
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(65);
						match(T__1);
						setState(66);
						identifier(0);
						}
						}
						setState(71);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(74);
				match(T__2);
				setState(75);
				match(T__3);
				setState(76);
				valueExpression(0);
				}
				break;
			case 9:
				{
				_localctx = new SubqueryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				match(T__0);
				setState(78);
				valueExpression(0);
				setState(79);
				match(T__2);
				}
				break;
			case 10:
				{
				_localctx = new ColumnReferenceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				identifier(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(92);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new SubscriptContext(new PrimaryExpressionContext(_parentctx, _parentState));
						((SubscriptContext)_localctx).value = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_primaryExpression);
						setState(84);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(85);
						match(T__4);
						setState(86);
						((SubscriptContext)_localctx).index = valueExpression(0);
						setState(87);
						match(T__5);
						}
						break;
					case 2:
						{
						_localctx = new DereferenceContext(new PrimaryExpressionContext(_parentctx, _parentState));
						((DereferenceContext)_localctx).base = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_primaryExpression);
						setState(89);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(90);
						match(T__6);
						setState(91);
						((DereferenceContext)_localctx).fieldName = identifier(0);
						}
						break;
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(GroovyExpParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(GroovyExpParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(GroovyExpParser.LT, 0); }
		public TerminalNode LTE() { return getToken(GroovyExpParser.LTE, 0); }
		public TerminalNode GT() { return getToken(GroovyExpParser.GT, 0); }
		public TerminalNode GTE() { return getToken(GroovyExpParser.GTE, 0); }
		public TerminalNode NULLEQ() { return getToken(GroovyExpParser.NULLEQ, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !(((((_la - 404)) & ~0x3f) == 0 && ((1L << (_la - 404)) & ((1L << (NULLEQ - 404)) | (1L << (EQ - 404)) | (1L << (NEQ - 404)) | (1L << (LT - 404)) | (1L << (LTE - 404)) | (1L << (GT - 404)) | (1L << (GTE - 404)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
	 
		public StringContext() { }
		public void copyFrom(StringContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnicodeStringLiteralContext extends StringContext {
		public TerminalNode UNICODE_STRING() { return getToken(GroovyExpParser.UNICODE_STRING, 0); }
		public TerminalNode UESCAPE() { return getToken(GroovyExpParser.UESCAPE, 0); }
		public TerminalNode STRING() { return getToken(GroovyExpParser.STRING, 0); }
		public UnicodeStringLiteralContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterUnicodeStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitUnicodeStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitUnicodeStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BasicStringLiteralContext extends StringContext {
		public TerminalNode STRING() { return getToken(GroovyExpParser.STRING, 0); }
		public BasicStringLiteralContext(StringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterBasicStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitBasicStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitBasicStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_string);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new BasicStringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(STRING);
				}
				break;
			case UNICODE_STRING:
				_localctx = new UnicodeStringLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(UNICODE_STRING);
				setState(103);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(101);
					match(UESCAPE);
					setState(102);
					match(STRING);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanValueContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(GroovyExpParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GroovyExpParser.FALSE, 0); }
		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitBooleanValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitBooleanValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_booleanValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			identifier(0);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(110);
				match(T__6);
				setState(111);
				identifier(0);
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
	 
		public IdentifierContext() { }
		public void copyFrom(IdentifierContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BackQuotedIdentifierContext extends IdentifierContext {
		public TerminalNode BACKQUOTED_IDENTIFIER() { return getToken(GroovyExpParser.BACKQUOTED_IDENTIFIER, 0); }
		public BackQuotedIdentifierContext(IdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterBackQuotedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitBackQuotedIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitBackQuotedIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MapidentifierContext extends IdentifierContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public MapidentifierContext(IdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterMapidentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitMapidentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitMapidentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class QuotedIdentifierContext extends IdentifierContext {
		public TerminalNode QUOTED_IDENTIFIER() { return getToken(GroovyExpParser.QUOTED_IDENTIFIER, 0); }
		public QuotedIdentifierContext(IdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterQuotedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitQuotedIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitQuotedIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DigitIdentifierContext extends IdentifierContext {
		public TerminalNode DIGIT_IDENTIFIER() { return getToken(GroovyExpParser.DIGIT_IDENTIFIER, 0); }
		public DigitIdentifierContext(IdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterDigitIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitDigitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitDigitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnquotedIdentifierContext extends IdentifierContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(GroovyExpParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(GroovyExpParser.IDENTIFIER, i);
		}
		public NonReservedContext nonReserved() {
			return getRuleContext(NonReservedContext.class,0);
		}
		public UnquotedIdentifierContext(IdentifierContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterUnquotedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitUnquotedIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitUnquotedIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		return identifier(0);
	}

	private IdentifierContext identifier(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IdentifierContext _localctx = new IdentifierContext(_ctx, _parentState);
		IdentifierContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_identifier, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				_localctx = new UnquotedIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(118);
				match(IDENTIFIER);
				setState(123);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					{
					setState(119);
					match(T__7);
					setState(120);
					match(IDENTIFIER);
					}
					}
					break;
				case 2:
					{
					{
					setState(121);
					match(T__8);
					setState(122);
					match(IDENTIFIER);
					}
					}
					break;
				}
				}
				break;
			case QUOTED_IDENTIFIER:
				{
				_localctx = new QuotedIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				match(QUOTED_IDENTIFIER);
				}
				break;
			case ADD:
			case ALL:
			case ALTER:
			case ANALYZE:
			case AND:
			case ANY:
			case ARRAY:
			case AS:
			case ASC:
			case AT:
			case BERNOULLI:
			case BETWEEN:
			case BY:
			case CALL:
			case CASCADE:
			case CASE:
			case CAST:
			case CATALOGS:
			case COLUMN:
			case COLUMNS:
			case COMMENT:
			case COMMIT:
			case COMMITTED:
			case CONSTRAINT:
			case CREATE:
			case CROSS:
			case CUBE:
			case CURRENT:
			case CURRENT_DATE:
			case CURRENT_PATH:
			case CURRENT_TIME:
			case CURRENT_TIMESTAMP:
			case CURRENT_USER:
			case DATA:
			case DATE:
			case DAY:
			case DEALLOCATE:
			case DELETE:
			case DESC:
			case DESCRIBE:
			case DISTINCT:
			case DISTRIBUTED:
			case DROP:
			case ELSE:
			case END:
			case ESCAPE:
			case EXCEPT:
			case EXCLUDING:
			case EXECUTE:
			case EXISTS:
			case EXPLAIN:
			case EXTRACT:
			case FALSE:
			case FILTER:
			case FIRST:
			case FOLLOWING:
			case FOR:
			case FORMAT:
			case FROM:
			case FULL:
			case FUNCTIONS:
			case GRANT:
			case GRANTS:
			case GRAPHVIZ:
			case GROUP:
			case GROUPING:
			case HAVING:
			case HOUR:
			case IF:
			case IN:
			case INCLUDING:
			case INNER:
			case INPUT:
			case INSERT:
			case INTERSECT:
			case MINUSES:
			case INTERVAL:
			case INTO:
			case IO:
			case IS:
			case ISOLATION:
			case JSON:
			case JOIN:
			case LAST:
			case LATERAL:
			case LEFT:
			case LEVEL:
			case LIKE:
			case LIMIT:
			case LISTAGG:
			case LOCALTIME:
			case LOCALTIMESTAMP:
			case LOGICAL:
			case MAP:
			case MINUTE:
			case MONTH:
			case MODIFY:
			case NATURAL:
			case NFC:
			case NFD:
			case NFKC:
			case NFKD:
			case NO:
			case NORMALIZE:
			case NOT:
			case NULL:
			case NULLIF:
			case NULLS:
			case ON:
			case ONLY:
			case OPTION:
			case OR:
			case ORDER:
			case ORDINALITY:
			case OUTER:
			case OUTPUT:
			case OVER:
			case OVERWRITE:
			case PARTITION:
			case PARTITIONS:
			case PATH:
			case POSITION:
			case PRECEDING:
			case PREPARE:
			case PRIVILEGES:
			case PROPERTIES:
			case PUBLIC:
			case RANGE:
			case READ:
			case RECURSIVE:
			case RENAME:
			case REPEATABLE:
			case REPLACE:
			case RESET:
			case RESTRICT:
			case REVOKE:
			case RIGHT:
			case ROLLBACK:
			case ROLLUP:
			case ROW:
			case ROWS:
			case SCHEMA:
			case SCHEMAS:
			case SECOND:
			case SELECT:
			case SERIALIZABLE:
			case SESSION:
			case SET:
			case SETS:
			case SHOW:
			case SOME:
			case START:
			case STATS:
			case SUBSTRING:
			case SYSTEM:
			case TABLE:
			case TABLES:
			case TABLESAMPLE:
			case TEXT:
			case THEN:
			case TIME:
			case TIMESTAMP:
			case TO:
			case TRANSACTION:
			case TRUE:
			case TRY_CAST:
			case TYPE:
			case TRUNCATE:
			case UPDATE:
			case UESCAPE:
			case UNBOUNDED:
			case UNCOMMITTED:
			case UNION:
			case UNNEST:
			case USE:
			case USING:
			case VALIDATE:
			case VALUES:
			case VERBOSE:
			case VIEW:
			case WHEN:
			case WHERE:
			case WITH:
			case WITHIN:
			case WORK:
			case WRITE:
			case YEAR:
			case ZONE:
			case SEMI:
			case SORT:
			case OFFSET:
			case DEFAULT:
			case TABLESPACE:
			case EXTERNAL:
			case PURGE:
			case TEMPORARY:
			case PRIMARY:
			case KEY:
			case DISABLE:
			case NOVALIDATE:
			case FOREIGN:
			case REFERENCES:
			case PARTITIONED:
			case CLUSTERED:
			case SORTED:
			case BUCKETS:
			case SKEWED:
			case STORED:
			case DELIMITED:
			case FIELDS:
			case TERMINATED:
			case ESCAPED:
			case COLLECTION:
			case ITEMS:
			case KEYS:
			case LINES:
			case DEFINED:
			case SEQUENCEFILE:
			case TEXTFILE:
			case RCFILE:
			case ORC:
			case ORCFILE:
			case PARQUET:
			case AVRO:
			case JSONFILE:
			case INPUTFORMAT:
			case OUTPUTFORMAT:
			case SERDE:
			case SERDEPROPERTIES:
			case LOCATION:
			case TBLPROPERTIES:
			case RELY:
			case EXCHANGE:
			case RECOVER:
			case ARCHIVE:
			case UNARCHIVE:
			case IGNORE:
			case PROTECTION:
			case TOUCH:
			case ENABLE:
			case NO_DROP:
			case OFFLINE:
			case COMPACT:
			case WAIT:
			case CONCATENATE:
			case CHANGE:
			case AFTER:
			case DEFERRED:
			case REBUILD:
			case INDEX:
			case IDXPROPERTIES:
			case FUNCTION:
			case JAR:
			case FILE:
			case DATABASE:
			case DBPROPERTIES:
			case OWNER:
			case RELOAD:
			case USER:
			case ROLE:
			case FILEFORMAT:
			case LOAD:
			case LOCAL:
			case INPATH:
			case DIRECTORY:
			case VACUUM:
			case LIST:
			case SUBPARTITION:
			case SUBPARTITIONS:
			case EVERY:
			case INCLUSIVE:
			case EXCLUSIVE:
			case TEMPLATE:
			case ENCODING:
			case LOG:
			case ERRORS:
			case SEGMENT:
			case REJECT:
			case PERCENT:
			case WRITABLE:
			case CHECK:
			case UNIQUE:
			case CONCURRENTLY:
			case BITMAP:
			case FORCE:
			case SPLIT:
			case REINDEX:
			case FREEZE:
			case MSCK:
			case REPAIR:
			case FETCH:
			case DEFINITION:
			case LESS:
			case THAN:
			case HASH:
			case NOLOGGING:
			case STRUCT:
			case GLOBAL:
			case PRESERVE:
			case AUTO_INCREMENT:
			case ENGINE:
			case CHARSET:
			case COMPRESS:
			case EXPRESS:
			case REPLICATED:
			case NOCOPIES:
			case SPACE:
			case SHRINK:
			case KEY_BLOCK_SIZE:
			case KEY_DC_SIZE:
			case AUTOEXTEND:
			case NEXT:
			case OFF:
			case MATCHED:
			case MERGE:
			case OUTFILE:
			case OPTIONALLY:
			case ENCLOSED:
			case DOUBLE_ENCLOSED:
			case NULL_VALUE:
			case OUTFILEMODE:
			case HDFS:
			case WRITEMODE:
			case NORMAL:
			case OVERWRITES:
			case FILECOUNT:
			case FILESIZE:
			case CHARACTER:
			case GBK:
			case UTF8:
			case HEAD:
			case RMTSELECT:
			case INFILE:
			case DATA_FORMAT:
			case SEPARATOR:
			case BLANKS:
			case AUTOFILL:
			case LENGTH:
			case TABLE_FIELDS:
			case MAX_BAD_RECORDS:
			case DATETIME:
			case TRACE:
			case TRACE_PATH:
			case NOSPLIT:
			case PARALLEL:
			case MAX_DATA_PROCESSORS:
			case MIN_CHUNK_SIZE:
			case SKIP_BAD_FILE:
			case NUM:
			case FILE_FORMAT:
			case IMMEDIATE:
			case STARTING:
			case ENDING:
			case INITIALLY:
			case REFRESH:
			case ACTIVATE:
			case LOGGED:
			case EMPTY:
			case SEQUENCE:
			case INCREMENT:
			case MINVALUE:
			case MAXVALUE:
			case OVERLAY:
			case PLACING:
			case TRIM:
			case LTRIM:
			case RTRIM:
			case BOTH:
			case TO_CHAR:
			case AGE:
			case DATE_PART:
			case ISFINITE:
			case INT:
			case UNSIGNED:
			case REGEXP:
			case BINARY:
			case LEADING:
			case TRAILING:
			case RLIKE:
			case DATE_SUB:
			case ADDDATE:
			case DATE_ADD:
			case YEAR_MONTH:
			case DAY_HOUR:
			case DAY_MINUTE:
			case DAY_SECOND:
			case HOUR_MINUTE:
			case HOUR_SECOND:
			case MINUTE_SECOND:
			case UNKNOWN:
				{
				_localctx = new UnquotedIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				nonReserved();
				}
				break;
			case BACKQUOTED_IDENTIFIER:
				{
				_localctx = new BackQuotedIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				match(BACKQUOTED_IDENTIFIER);
				}
				break;
			case DIGIT_IDENTIFIER:
				{
				_localctx = new DigitIdentifierContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(DIGIT_IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MapidentifierContext(new IdentifierContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_identifier);
					setState(131);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(132);
					string();
					setState(133);
					match(T__5);
					}
					} 
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	 
		public NumberContext() { }
		public void copyFrom(NumberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DecimalLiteralContext extends NumberContext {
		public TerminalNode DECIMAL_VALUE() { return getToken(GroovyExpParser.DECIMAL_VALUE, 0); }
		public DecimalLiteralContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterDecimalLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitDecimalLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitDecimalLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleLiteralContext extends NumberContext {
		public TerminalNode DOUBLE_VALUE() { return getToken(GroovyExpParser.DOUBLE_VALUE, 0); }
		public DoubleLiteralContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterDoubleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitDoubleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitDoubleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerLiteralContext extends NumberContext {
		public TerminalNode INTEGER_VALUE() { return getToken(GroovyExpParser.INTEGER_VALUE, 0); }
		public IntegerLiteralContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_number);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_VALUE:
				_localctx = new DecimalLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				match(DECIMAL_VALUE);
				}
				break;
			case DOUBLE_VALUE:
				_localctx = new DoubleLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(DOUBLE_VALUE);
				}
				break;
			case INTEGER_VALUE:
				_localctx = new IntegerLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				match(INTEGER_VALUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReservedContext extends ParserRuleContext {
		public ReservedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reserved; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterReserved(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitReserved(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitReserved(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReservedContext reserved() throws RecognitionException {
		ReservedContext _localctx = new ReservedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_reserved);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonReservedContext extends ParserRuleContext {
		public TerminalNode OVERWRITE() { return getToken(GroovyExpParser.OVERWRITE, 0); }
		public TerminalNode JSONFILE() { return getToken(GroovyExpParser.JSONFILE, 0); }
		public TerminalNode NOT() { return getToken(GroovyExpParser.NOT, 0); }
		public TerminalNode EXCEPT() { return getToken(GroovyExpParser.EXCEPT, 0); }
		public TerminalNode PARQUET() { return getToken(GroovyExpParser.PARQUET, 0); }
		public TerminalNode CREATE() { return getToken(GroovyExpParser.CREATE, 0); }
		public TerminalNode USING() { return getToken(GroovyExpParser.USING, 0); }
		public TerminalNode SERDEPROPERTIES() { return getToken(GroovyExpParser.SERDEPROPERTIES, 0); }
		public TerminalNode DISABLE() { return getToken(GroovyExpParser.DISABLE, 0); }
		public TerminalNode GROUPING() { return getToken(GroovyExpParser.GROUPING, 0); }
		public TerminalNode NOVALIDATE() { return getToken(GroovyExpParser.NOVALIDATE, 0); }
		public TerminalNode DEFINED() { return getToken(GroovyExpParser.DEFINED, 0); }
		public TerminalNode ELSE() { return getToken(GroovyExpParser.ELSE, 0); }
		public TerminalNode UESCAPE() { return getToken(GroovyExpParser.UESCAPE, 0); }
		public TerminalNode RECURSIVE() { return getToken(GroovyExpParser.RECURSIVE, 0); }
		public TerminalNode WAIT() { return getToken(GroovyExpParser.WAIT, 0); }
		public TerminalNode GROUP() { return getToken(GroovyExpParser.GROUP, 0); }
		public TerminalNode INTERSECT() { return getToken(GroovyExpParser.INTERSECT, 0); }
		public TerminalNode DESCRIBE() { return getToken(GroovyExpParser.DESCRIBE, 0); }
		public TerminalNode WITHIN() { return getToken(GroovyExpParser.WITHIN, 0); }
		public TerminalNode LOCATION() { return getToken(GroovyExpParser.LOCATION, 0); }
		public TerminalNode SKEWED() { return getToken(GroovyExpParser.SKEWED, 0); }
		public TerminalNode SERDE() { return getToken(GroovyExpParser.SERDE, 0); }
		public TerminalNode END() { return getToken(GroovyExpParser.END, 0); }
		public TerminalNode CONSTRAINT() { return getToken(GroovyExpParser.CONSTRAINT, 0); }
		public TerminalNode MINUSES() { return getToken(GroovyExpParser.MINUSES, 0); }
		public TerminalNode MODIFY() { return getToken(GroovyExpParser.MODIFY, 0); }
		public TerminalNode ALTER() { return getToken(GroovyExpParser.ALTER, 0); }
		public TerminalNode EXECUTE() { return getToken(GroovyExpParser.EXECUTE, 0); }
		public TerminalNode PARTITIONED() { return getToken(GroovyExpParser.PARTITIONED, 0); }
		public TerminalNode LINES() { return getToken(GroovyExpParser.LINES, 0); }
		public TerminalNode TABLE() { return getToken(GroovyExpParser.TABLE, 0); }
		public TerminalNode CURRENT_PATH() { return getToken(GroovyExpParser.CURRENT_PATH, 0); }
		public TerminalNode AS() { return getToken(GroovyExpParser.AS, 0); }
		public TerminalNode THEN() { return getToken(GroovyExpParser.THEN, 0); }
		public TerminalNode REJECT() { return getToken(GroovyExpParser.REJECT, 0); }
		public TerminalNode NORMALIZE() { return getToken(GroovyExpParser.NORMALIZE, 0); }
		public TerminalNode EXISTS() { return getToken(GroovyExpParser.EXISTS, 0); }
		public TerminalNode LIKE() { return getToken(GroovyExpParser.LIKE, 0); }
		public TerminalNode OUTER() { return getToken(GroovyExpParser.OUTER, 0); }
		public TerminalNode BY() { return getToken(GroovyExpParser.BY, 0); }
		public TerminalNode DELIMITED() { return getToken(GroovyExpParser.DELIMITED, 0); }
		public TerminalNode OUTPUTFORMAT() { return getToken(GroovyExpParser.OUTPUTFORMAT, 0); }
		public TerminalNode ITEMS() { return getToken(GroovyExpParser.ITEMS, 0); }
		public TerminalNode HAVING() { return getToken(GroovyExpParser.HAVING, 0); }
		public TerminalNode UNION() { return getToken(GroovyExpParser.UNION, 0); }
		public TerminalNode AVRO() { return getToken(GroovyExpParser.AVRO, 0); }
		public TerminalNode DROP() { return getToken(GroovyExpParser.DROP, 0); }
		public TerminalNode INPUTFORMAT() { return getToken(GroovyExpParser.INPUTFORMAT, 0); }
		public TerminalNode WHEN() { return getToken(GroovyExpParser.WHEN, 0); }
		public TerminalNode CONCURRENTLY() { return getToken(GroovyExpParser.CONCURRENTLY, 0); }
		public TerminalNode NATURAL() { return getToken(GroovyExpParser.NATURAL, 0); }
		public TerminalNode BETWEEN() { return getToken(GroovyExpParser.BETWEEN, 0); }
		public TerminalNode CAST() { return getToken(GroovyExpParser.CAST, 0); }
		public TerminalNode CLUSTERED() { return getToken(GroovyExpParser.CLUSTERED, 0); }
		public TerminalNode EXTERNAL() { return getToken(GroovyExpParser.EXTERNAL, 0); }
		public TerminalNode PREPARE() { return getToken(GroovyExpParser.PREPARE, 0); }
		public TerminalNode STORED() { return getToken(GroovyExpParser.STORED, 0); }
		public TerminalNode CASE() { return getToken(GroovyExpParser.CASE, 0); }
		public TerminalNode DEALLOCATE() { return getToken(GroovyExpParser.DEALLOCATE, 0); }
		public TerminalNode TERMINATED() { return getToken(GroovyExpParser.TERMINATED, 0); }
		public TerminalNode FULL() { return getToken(GroovyExpParser.FULL, 0); }
		public TerminalNode ESCAPE() { return getToken(GroovyExpParser.ESCAPE, 0); }
		public TerminalNode SELECT() { return getToken(GroovyExpParser.SELECT, 0); }
		public TerminalNode INTO() { return getToken(GroovyExpParser.INTO, 0); }
		public TerminalNode SORTED() { return getToken(GroovyExpParser.SORTED, 0); }
		public TerminalNode UNIQUE() { return getToken(GroovyExpParser.UNIQUE, 0); }
		public TerminalNode ROLLUP() { return getToken(GroovyExpParser.ROLLUP, 0); }
		public TerminalNode NULL() { return getToken(GroovyExpParser.NULL, 0); }
		public TerminalNode ON() { return getToken(GroovyExpParser.ON, 0); }
		public TerminalNode ORCFILE() { return getToken(GroovyExpParser.ORCFILE, 0); }
		public TerminalNode DELETE() { return getToken(GroovyExpParser.DELETE, 0); }
		public TerminalNode LIST() { return getToken(GroovyExpParser.LIST, 0); }
		public TerminalNode CUBE() { return getToken(GroovyExpParser.CUBE, 0); }
		public TerminalNode BITMAP() { return getToken(GroovyExpParser.BITMAP, 0); }
		public TerminalNode OR() { return getToken(GroovyExpParser.OR, 0); }
		public TerminalNode CHECK() { return getToken(GroovyExpParser.CHECK, 0); }
		public TerminalNode ESCAPED() { return getToken(GroovyExpParser.ESCAPED, 0); }
		public TerminalNode FREEZE() { return getToken(GroovyExpParser.FREEZE, 0); }
		public TerminalNode FROM() { return getToken(GroovyExpParser.FROM, 0); }
		public TerminalNode FALSE() { return getToken(GroovyExpParser.FALSE, 0); }
		public TerminalNode DISTINCT() { return getToken(GroovyExpParser.DISTINCT, 0); }
		public TerminalNode TEMPORARY() { return getToken(GroovyExpParser.TEMPORARY, 0); }
		public TerminalNode RCFILE() { return getToken(GroovyExpParser.RCFILE, 0); }
		public TerminalNode UNNEST() { return getToken(GroovyExpParser.UNNEST, 0); }
		public TerminalNode WHERE() { return getToken(GroovyExpParser.WHERE, 0); }
		public TerminalNode INNER() { return getToken(GroovyExpParser.INNER, 0); }
		public TerminalNode ORDER() { return getToken(GroovyExpParser.ORDER, 0); }
		public TerminalNode BUCKETS() { return getToken(GroovyExpParser.BUCKETS, 0); }
		public TerminalNode SPLIT() { return getToken(GroovyExpParser.SPLIT, 0); }
		public TerminalNode TEXTFILE() { return getToken(GroovyExpParser.TEXTFILE, 0); }
		public TerminalNode LISTAGG() { return getToken(GroovyExpParser.LISTAGG, 0); }
		public TerminalNode UPDATE() { return getToken(GroovyExpParser.UPDATE, 0); }
		public TerminalNode FOR() { return getToken(GroovyExpParser.FOR, 0); }
		public TerminalNode AND() { return getToken(GroovyExpParser.AND, 0); }
		public TerminalNode CROSS() { return getToken(GroovyExpParser.CROSS, 0); }
		public TerminalNode ORC() { return getToken(GroovyExpParser.ORC, 0); }
		public TerminalNode INDEX() { return getToken(GroovyExpParser.INDEX, 0); }
		public TerminalNode IN() { return getToken(GroovyExpParser.IN, 0); }
		public TerminalNode REFERENCES() { return getToken(GroovyExpParser.REFERENCES, 0); }
		public TerminalNode IS() { return getToken(GroovyExpParser.IS, 0); }
		public TerminalNode COLLECTION() { return getToken(GroovyExpParser.COLLECTION, 0); }
		public TerminalNode WITH() { return getToken(GroovyExpParser.WITH, 0); }
		public TerminalNode REINDEX() { return getToken(GroovyExpParser.REINDEX, 0); }
		public TerminalNode PERCENT() { return getToken(GroovyExpParser.PERCENT, 0); }
		public TerminalNode VALUES() { return getToken(GroovyExpParser.VALUES, 0); }
		public TerminalNode PURGE() { return getToken(GroovyExpParser.PURGE, 0); }
		public TerminalNode FIELDS() { return getToken(GroovyExpParser.FIELDS, 0); }
		public TerminalNode TRUE() { return getToken(GroovyExpParser.TRUE, 0); }
		public TerminalNode JOIN() { return getToken(GroovyExpParser.JOIN, 0); }
		public TerminalNode SEQUENCEFILE() { return getToken(GroovyExpParser.SEQUENCEFILE, 0); }
		public TerminalNode TBLPROPERTIES() { return getToken(GroovyExpParser.TBLPROPERTIES, 0); }
		public TerminalNode HASH() { return getToken(GroovyExpParser.HASH, 0); }
		public TerminalNode SUBPARTITIONS() { return getToken(GroovyExpParser.SUBPARTITIONS, 0); }
		public TerminalNode NOLOGGING() { return getToken(GroovyExpParser.NOLOGGING, 0); }
		public TerminalNode ADD() { return getToken(GroovyExpParser.ADD, 0); }
		public TerminalNode ALL() { return getToken(GroovyExpParser.ALL, 0); }
		public TerminalNode ANALYZE() { return getToken(GroovyExpParser.ANALYZE, 0); }
		public TerminalNode ANY() { return getToken(GroovyExpParser.ANY, 0); }
		public TerminalNode ARRAY() { return getToken(GroovyExpParser.ARRAY, 0); }
		public TerminalNode ASC() { return getToken(GroovyExpParser.ASC, 0); }
		public TerminalNode AT() { return getToken(GroovyExpParser.AT, 0); }
		public TerminalNode BERNOULLI() { return getToken(GroovyExpParser.BERNOULLI, 0); }
		public TerminalNode CALL() { return getToken(GroovyExpParser.CALL, 0); }
		public TerminalNode CASCADE() { return getToken(GroovyExpParser.CASCADE, 0); }
		public TerminalNode CATALOGS() { return getToken(GroovyExpParser.CATALOGS, 0); }
		public TerminalNode COLUMN() { return getToken(GroovyExpParser.COLUMN, 0); }
		public TerminalNode COLUMNS() { return getToken(GroovyExpParser.COLUMNS, 0); }
		public TerminalNode COMMENT() { return getToken(GroovyExpParser.COMMENT, 0); }
		public TerminalNode COMMIT() { return getToken(GroovyExpParser.COMMIT, 0); }
		public TerminalNode COMMITTED() { return getToken(GroovyExpParser.COMMITTED, 0); }
		public TerminalNode CURRENT() { return getToken(GroovyExpParser.CURRENT, 0); }
		public TerminalNode DATA() { return getToken(GroovyExpParser.DATA, 0); }
		public TerminalNode DATE() { return getToken(GroovyExpParser.DATE, 0); }
		public TerminalNode DAY() { return getToken(GroovyExpParser.DAY, 0); }
		public TerminalNode DESC() { return getToken(GroovyExpParser.DESC, 0); }
		public TerminalNode DISTRIBUTED() { return getToken(GroovyExpParser.DISTRIBUTED, 0); }
		public TerminalNode EXCLUDING() { return getToken(GroovyExpParser.EXCLUDING, 0); }
		public TerminalNode EXPLAIN() { return getToken(GroovyExpParser.EXPLAIN, 0); }
		public TerminalNode FILTER() { return getToken(GroovyExpParser.FILTER, 0); }
		public TerminalNode FIRST() { return getToken(GroovyExpParser.FIRST, 0); }
		public TerminalNode FOLLOWING() { return getToken(GroovyExpParser.FOLLOWING, 0); }
		public TerminalNode FORMAT() { return getToken(GroovyExpParser.FORMAT, 0); }
		public TerminalNode FUNCTIONS() { return getToken(GroovyExpParser.FUNCTIONS, 0); }
		public TerminalNode GRANT() { return getToken(GroovyExpParser.GRANT, 0); }
		public TerminalNode GRANTS() { return getToken(GroovyExpParser.GRANTS, 0); }
		public TerminalNode GRAPHVIZ() { return getToken(GroovyExpParser.GRAPHVIZ, 0); }
		public TerminalNode HOUR() { return getToken(GroovyExpParser.HOUR, 0); }
		public TerminalNode IF() { return getToken(GroovyExpParser.IF, 0); }
		public TerminalNode INCLUDING() { return getToken(GroovyExpParser.INCLUDING, 0); }
		public TerminalNode INPUT() { return getToken(GroovyExpParser.INPUT, 0); }
		public TerminalNode INTERVAL() { return getToken(GroovyExpParser.INTERVAL, 0); }
		public TerminalNode IO() { return getToken(GroovyExpParser.IO, 0); }
		public TerminalNode ISOLATION() { return getToken(GroovyExpParser.ISOLATION, 0); }
		public TerminalNode JSON() { return getToken(GroovyExpParser.JSON, 0); }
		public TerminalNode LAST() { return getToken(GroovyExpParser.LAST, 0); }
		public TerminalNode LATERAL() { return getToken(GroovyExpParser.LATERAL, 0); }
		public TerminalNode LEVEL() { return getToken(GroovyExpParser.LEVEL, 0); }
		public TerminalNode LIMIT() { return getToken(GroovyExpParser.LIMIT, 0); }
		public TerminalNode LOGICAL() { return getToken(GroovyExpParser.LOGICAL, 0); }
		public TerminalNode MAP() { return getToken(GroovyExpParser.MAP, 0); }
		public TerminalNode MINUTE() { return getToken(GroovyExpParser.MINUTE, 0); }
		public TerminalNode MONTH() { return getToken(GroovyExpParser.MONTH, 0); }
		public TerminalNode NFC() { return getToken(GroovyExpParser.NFC, 0); }
		public TerminalNode NFD() { return getToken(GroovyExpParser.NFD, 0); }
		public TerminalNode NFKC() { return getToken(GroovyExpParser.NFKC, 0); }
		public TerminalNode NFKD() { return getToken(GroovyExpParser.NFKD, 0); }
		public TerminalNode NO() { return getToken(GroovyExpParser.NO, 0); }
		public TerminalNode NULLIF() { return getToken(GroovyExpParser.NULLIF, 0); }
		public TerminalNode NULLS() { return getToken(GroovyExpParser.NULLS, 0); }
		public TerminalNode ONLY() { return getToken(GroovyExpParser.ONLY, 0); }
		public TerminalNode OPTION() { return getToken(GroovyExpParser.OPTION, 0); }
		public TerminalNode ORDINALITY() { return getToken(GroovyExpParser.ORDINALITY, 0); }
		public TerminalNode OUTPUT() { return getToken(GroovyExpParser.OUTPUT, 0); }
		public TerminalNode OVER() { return getToken(GroovyExpParser.OVER, 0); }
		public TerminalNode PARTITION() { return getToken(GroovyExpParser.PARTITION, 0); }
		public TerminalNode PARTITIONS() { return getToken(GroovyExpParser.PARTITIONS, 0); }
		public TerminalNode PATH() { return getToken(GroovyExpParser.PATH, 0); }
		public TerminalNode POSITION() { return getToken(GroovyExpParser.POSITION, 0); }
		public TerminalNode PRECEDING() { return getToken(GroovyExpParser.PRECEDING, 0); }
		public TerminalNode PRIVILEGES() { return getToken(GroovyExpParser.PRIVILEGES, 0); }
		public TerminalNode PROPERTIES() { return getToken(GroovyExpParser.PROPERTIES, 0); }
		public TerminalNode PUBLIC() { return getToken(GroovyExpParser.PUBLIC, 0); }
		public TerminalNode RANGE() { return getToken(GroovyExpParser.RANGE, 0); }
		public TerminalNode READ() { return getToken(GroovyExpParser.READ, 0); }
		public TerminalNode RENAME() { return getToken(GroovyExpParser.RENAME, 0); }
		public TerminalNode REPEATABLE() { return getToken(GroovyExpParser.REPEATABLE, 0); }
		public TerminalNode REPLACE() { return getToken(GroovyExpParser.REPLACE, 0); }
		public TerminalNode RESET() { return getToken(GroovyExpParser.RESET, 0); }
		public TerminalNode RESTRICT() { return getToken(GroovyExpParser.RESTRICT, 0); }
		public TerminalNode REVOKE() { return getToken(GroovyExpParser.REVOKE, 0); }
		public TerminalNode ROLLBACK() { return getToken(GroovyExpParser.ROLLBACK, 0); }
		public TerminalNode ROW() { return getToken(GroovyExpParser.ROW, 0); }
		public TerminalNode ROWS() { return getToken(GroovyExpParser.ROWS, 0); }
		public TerminalNode SCHEMA() { return getToken(GroovyExpParser.SCHEMA, 0); }
		public TerminalNode SCHEMAS() { return getToken(GroovyExpParser.SCHEMAS, 0); }
		public TerminalNode SECOND() { return getToken(GroovyExpParser.SECOND, 0); }
		public TerminalNode SERIALIZABLE() { return getToken(GroovyExpParser.SERIALIZABLE, 0); }
		public TerminalNode SESSION() { return getToken(GroovyExpParser.SESSION, 0); }
		public TerminalNode SET() { return getToken(GroovyExpParser.SET, 0); }
		public TerminalNode SETS() { return getToken(GroovyExpParser.SETS, 0); }
		public TerminalNode SHOW() { return getToken(GroovyExpParser.SHOW, 0); }
		public TerminalNode SOME() { return getToken(GroovyExpParser.SOME, 0); }
		public TerminalNode START() { return getToken(GroovyExpParser.START, 0); }
		public TerminalNode STATS() { return getToken(GroovyExpParser.STATS, 0); }
		public TerminalNode SUBSTRING() { return getToken(GroovyExpParser.SUBSTRING, 0); }
		public TerminalNode SYSTEM() { return getToken(GroovyExpParser.SYSTEM, 0); }
		public TerminalNode TABLES() { return getToken(GroovyExpParser.TABLES, 0); }
		public TerminalNode TABLESAMPLE() { return getToken(GroovyExpParser.TABLESAMPLE, 0); }
		public TerminalNode TEXT() { return getToken(GroovyExpParser.TEXT, 0); }
		public TerminalNode TIME() { return getToken(GroovyExpParser.TIME, 0); }
		public TerminalNode TIMESTAMP() { return getToken(GroovyExpParser.TIMESTAMP, 0); }
		public TerminalNode TO() { return getToken(GroovyExpParser.TO, 0); }
		public TerminalNode TRANSACTION() { return getToken(GroovyExpParser.TRANSACTION, 0); }
		public TerminalNode TRY_CAST() { return getToken(GroovyExpParser.TRY_CAST, 0); }
		public TerminalNode TYPE() { return getToken(GroovyExpParser.TYPE, 0); }
		public TerminalNode UNBOUNDED() { return getToken(GroovyExpParser.UNBOUNDED, 0); }
		public TerminalNode UNCOMMITTED() { return getToken(GroovyExpParser.UNCOMMITTED, 0); }
		public TerminalNode USE() { return getToken(GroovyExpParser.USE, 0); }
		public TerminalNode VALIDATE() { return getToken(GroovyExpParser.VALIDATE, 0); }
		public TerminalNode VERBOSE() { return getToken(GroovyExpParser.VERBOSE, 0); }
		public TerminalNode VIEW() { return getToken(GroovyExpParser.VIEW, 0); }
		public TerminalNode WORK() { return getToken(GroovyExpParser.WORK, 0); }
		public TerminalNode WRITE() { return getToken(GroovyExpParser.WRITE, 0); }
		public TerminalNode YEAR() { return getToken(GroovyExpParser.YEAR, 0); }
		public TerminalNode ZONE() { return getToken(GroovyExpParser.ZONE, 0); }
		public TerminalNode SEMI() { return getToken(GroovyExpParser.SEMI, 0); }
		public TerminalNode SORT() { return getToken(GroovyExpParser.SORT, 0); }
		public TerminalNode OFFSET() { return getToken(GroovyExpParser.OFFSET, 0); }
		public TerminalNode DEFAULT() { return getToken(GroovyExpParser.DEFAULT, 0); }
		public TerminalNode TABLESPACE() { return getToken(GroovyExpParser.TABLESPACE, 0); }
		public TerminalNode FORCE() { return getToken(GroovyExpParser.FORCE, 0); }
		public TerminalNode MSCK() { return getToken(GroovyExpParser.MSCK, 0); }
		public TerminalNode REPAIR() { return getToken(GroovyExpParser.REPAIR, 0); }
		public TerminalNode FETCH() { return getToken(GroovyExpParser.FETCH, 0); }
		public TerminalNode DEFINITION() { return getToken(GroovyExpParser.DEFINITION, 0); }
		public TerminalNode LESS() { return getToken(GroovyExpParser.LESS, 0); }
		public TerminalNode THAN() { return getToken(GroovyExpParser.THAN, 0); }
		public TerminalNode GLOBAL() { return getToken(GroovyExpParser.GLOBAL, 0); }
		public TerminalNode PRESERVE() { return getToken(GroovyExpParser.PRESERVE, 0); }
		public TerminalNode ENGINE() { return getToken(GroovyExpParser.ENGINE, 0); }
		public TerminalNode AUTO_INCREMENT() { return getToken(GroovyExpParser.AUTO_INCREMENT, 0); }
		public TerminalNode CHARSET() { return getToken(GroovyExpParser.CHARSET, 0); }
		public TerminalNode RIGHT() { return getToken(GroovyExpParser.RIGHT, 0); }
		public TerminalNode LEFT() { return getToken(GroovyExpParser.LEFT, 0); }
		public TerminalNode INSERT() { return getToken(GroovyExpParser.INSERT, 0); }
		public TerminalNode TRUNCATE() { return getToken(GroovyExpParser.TRUNCATE, 0); }
		public TerminalNode KEY() { return getToken(GroovyExpParser.KEY, 0); }
		public TerminalNode KEYS() { return getToken(GroovyExpParser.KEYS, 0); }
		public TerminalNode FOREIGN() { return getToken(GroovyExpParser.FOREIGN, 0); }
		public TerminalNode PRIMARY() { return getToken(GroovyExpParser.PRIMARY, 0); }
		public TerminalNode RELY() { return getToken(GroovyExpParser.RELY, 0); }
		public TerminalNode EXCHANGE() { return getToken(GroovyExpParser.EXCHANGE, 0); }
		public TerminalNode RECOVER() { return getToken(GroovyExpParser.RECOVER, 0); }
		public TerminalNode ARCHIVE() { return getToken(GroovyExpParser.ARCHIVE, 0); }
		public TerminalNode IGNORE() { return getToken(GroovyExpParser.IGNORE, 0); }
		public TerminalNode PROTECTION() { return getToken(GroovyExpParser.PROTECTION, 0); }
		public TerminalNode UNARCHIVE() { return getToken(GroovyExpParser.UNARCHIVE, 0); }
		public TerminalNode TOUCH() { return getToken(GroovyExpParser.TOUCH, 0); }
		public TerminalNode ENABLE() { return getToken(GroovyExpParser.ENABLE, 0); }
		public TerminalNode NO_DROP() { return getToken(GroovyExpParser.NO_DROP, 0); }
		public TerminalNode OFFLINE() { return getToken(GroovyExpParser.OFFLINE, 0); }
		public TerminalNode COMPACT() { return getToken(GroovyExpParser.COMPACT, 0); }
		public TerminalNode CONCATENATE() { return getToken(GroovyExpParser.CONCATENATE, 0); }
		public TerminalNode CHANGE() { return getToken(GroovyExpParser.CHANGE, 0); }
		public TerminalNode AFTER() { return getToken(GroovyExpParser.AFTER, 0); }
		public TerminalNode DEFERRED() { return getToken(GroovyExpParser.DEFERRED, 0); }
		public TerminalNode REBUILD() { return getToken(GroovyExpParser.REBUILD, 0); }
		public TerminalNode IDXPROPERTIES() { return getToken(GroovyExpParser.IDXPROPERTIES, 0); }
		public TerminalNode FUNCTION() { return getToken(GroovyExpParser.FUNCTION, 0); }
		public TerminalNode JAR() { return getToken(GroovyExpParser.JAR, 0); }
		public TerminalNode FILE() { return getToken(GroovyExpParser.FILE, 0); }
		public TerminalNode DATABASE() { return getToken(GroovyExpParser.DATABASE, 0); }
		public TerminalNode DBPROPERTIES() { return getToken(GroovyExpParser.DBPROPERTIES, 0); }
		public TerminalNode OWNER() { return getToken(GroovyExpParser.OWNER, 0); }
		public TerminalNode RELOAD() { return getToken(GroovyExpParser.RELOAD, 0); }
		public TerminalNode USER() { return getToken(GroovyExpParser.USER, 0); }
		public TerminalNode ROLE() { return getToken(GroovyExpParser.ROLE, 0); }
		public TerminalNode FILEFORMAT() { return getToken(GroovyExpParser.FILEFORMAT, 0); }
		public TerminalNode LOAD() { return getToken(GroovyExpParser.LOAD, 0); }
		public TerminalNode LOCAL() { return getToken(GroovyExpParser.LOCAL, 0); }
		public TerminalNode INPATH() { return getToken(GroovyExpParser.INPATH, 0); }
		public TerminalNode DIRECTORY() { return getToken(GroovyExpParser.DIRECTORY, 0); }
		public TerminalNode VACUUM() { return getToken(GroovyExpParser.VACUUM, 0); }
		public TerminalNode SUBPARTITION() { return getToken(GroovyExpParser.SUBPARTITION, 0); }
		public TerminalNode EVERY() { return getToken(GroovyExpParser.EVERY, 0); }
		public TerminalNode INCLUSIVE() { return getToken(GroovyExpParser.INCLUSIVE, 0); }
		public TerminalNode EXCLUSIVE() { return getToken(GroovyExpParser.EXCLUSIVE, 0); }
		public TerminalNode TEMPLATE() { return getToken(GroovyExpParser.TEMPLATE, 0); }
		public TerminalNode ENCODING() { return getToken(GroovyExpParser.ENCODING, 0); }
		public TerminalNode LOG() { return getToken(GroovyExpParser.LOG, 0); }
		public TerminalNode ERRORS() { return getToken(GroovyExpParser.ERRORS, 0); }
		public TerminalNode SEGMENT() { return getToken(GroovyExpParser.SEGMENT, 0); }
		public TerminalNode WRITABLE() { return getToken(GroovyExpParser.WRITABLE, 0); }
		public TerminalNode STRUCT() { return getToken(GroovyExpParser.STRUCT, 0); }
		public TerminalNode LOCALTIMESTAMP() { return getToken(GroovyExpParser.LOCALTIMESTAMP, 0); }
		public TerminalNode CURRENT_TIME() { return getToken(GroovyExpParser.CURRENT_TIME, 0); }
		public TerminalNode EXTRACT() { return getToken(GroovyExpParser.EXTRACT, 0); }
		public TerminalNode CURRENT_DATE() { return getToken(GroovyExpParser.CURRENT_DATE, 0); }
		public TerminalNode CURRENT_TIMESTAMP() { return getToken(GroovyExpParser.CURRENT_TIMESTAMP, 0); }
		public TerminalNode LOCALTIME() { return getToken(GroovyExpParser.LOCALTIME, 0); }
		public TerminalNode OVERLAY() { return getToken(GroovyExpParser.OVERLAY, 0); }
		public TerminalNode PLACING() { return getToken(GroovyExpParser.PLACING, 0); }
		public TerminalNode TRIM() { return getToken(GroovyExpParser.TRIM, 0); }
		public TerminalNode BOTH() { return getToken(GroovyExpParser.BOTH, 0); }
		public TerminalNode TO_CHAR() { return getToken(GroovyExpParser.TO_CHAR, 0); }
		public TerminalNode AGE() { return getToken(GroovyExpParser.AGE, 0); }
		public TerminalNode DATE_PART() { return getToken(GroovyExpParser.DATE_PART, 0); }
		public TerminalNode ISFINITE() { return getToken(GroovyExpParser.ISFINITE, 0); }
		public TerminalNode COMPRESS() { return getToken(GroovyExpParser.COMPRESS, 0); }
		public TerminalNode EXPRESS() { return getToken(GroovyExpParser.EXPRESS, 0); }
		public TerminalNode REPLICATED() { return getToken(GroovyExpParser.REPLICATED, 0); }
		public TerminalNode NOCOPIES() { return getToken(GroovyExpParser.NOCOPIES, 0); }
		public TerminalNode SPACE() { return getToken(GroovyExpParser.SPACE, 0); }
		public TerminalNode SHRINK() { return getToken(GroovyExpParser.SHRINK, 0); }
		public TerminalNode KEY_BLOCK_SIZE() { return getToken(GroovyExpParser.KEY_BLOCK_SIZE, 0); }
		public TerminalNode KEY_DC_SIZE() { return getToken(GroovyExpParser.KEY_DC_SIZE, 0); }
		public TerminalNode AUTOEXTEND() { return getToken(GroovyExpParser.AUTOEXTEND, 0); }
		public TerminalNode NEXT() { return getToken(GroovyExpParser.NEXT, 0); }
		public TerminalNode OFF() { return getToken(GroovyExpParser.OFF, 0); }
		public TerminalNode MATCHED() { return getToken(GroovyExpParser.MATCHED, 0); }
		public TerminalNode MERGE() { return getToken(GroovyExpParser.MERGE, 0); }
		public TerminalNode OUTFILE() { return getToken(GroovyExpParser.OUTFILE, 0); }
		public TerminalNode OPTIONALLY() { return getToken(GroovyExpParser.OPTIONALLY, 0); }
		public TerminalNode ENCLOSED() { return getToken(GroovyExpParser.ENCLOSED, 0); }
		public TerminalNode DOUBLE_ENCLOSED() { return getToken(GroovyExpParser.DOUBLE_ENCLOSED, 0); }
		public TerminalNode NULL_VALUE() { return getToken(GroovyExpParser.NULL_VALUE, 0); }
		public TerminalNode OUTFILEMODE() { return getToken(GroovyExpParser.OUTFILEMODE, 0); }
		public TerminalNode HDFS() { return getToken(GroovyExpParser.HDFS, 0); }
		public TerminalNode WRITEMODE() { return getToken(GroovyExpParser.WRITEMODE, 0); }
		public TerminalNode NORMAL() { return getToken(GroovyExpParser.NORMAL, 0); }
		public TerminalNode OVERWRITES() { return getToken(GroovyExpParser.OVERWRITES, 0); }
		public TerminalNode FILECOUNT() { return getToken(GroovyExpParser.FILECOUNT, 0); }
		public TerminalNode FILESIZE() { return getToken(GroovyExpParser.FILESIZE, 0); }
		public TerminalNode CHARACTER() { return getToken(GroovyExpParser.CHARACTER, 0); }
		public TerminalNode GBK() { return getToken(GroovyExpParser.GBK, 0); }
		public TerminalNode UTF8() { return getToken(GroovyExpParser.UTF8, 0); }
		public TerminalNode HEAD() { return getToken(GroovyExpParser.HEAD, 0); }
		public TerminalNode RMTSELECT() { return getToken(GroovyExpParser.RMTSELECT, 0); }
		public TerminalNode INFILE() { return getToken(GroovyExpParser.INFILE, 0); }
		public TerminalNode DATA_FORMAT() { return getToken(GroovyExpParser.DATA_FORMAT, 0); }
		public TerminalNode SEPARATOR() { return getToken(GroovyExpParser.SEPARATOR, 0); }
		public TerminalNode BLANKS() { return getToken(GroovyExpParser.BLANKS, 0); }
		public TerminalNode AUTOFILL() { return getToken(GroovyExpParser.AUTOFILL, 0); }
		public TerminalNode LENGTH() { return getToken(GroovyExpParser.LENGTH, 0); }
		public TerminalNode TABLE_FIELDS() { return getToken(GroovyExpParser.TABLE_FIELDS, 0); }
		public TerminalNode MAX_BAD_RECORDS() { return getToken(GroovyExpParser.MAX_BAD_RECORDS, 0); }
		public TerminalNode DATETIME() { return getToken(GroovyExpParser.DATETIME, 0); }
		public TerminalNode TRACE() { return getToken(GroovyExpParser.TRACE, 0); }
		public TerminalNode TRACE_PATH() { return getToken(GroovyExpParser.TRACE_PATH, 0); }
		public TerminalNode NOSPLIT() { return getToken(GroovyExpParser.NOSPLIT, 0); }
		public TerminalNode PARALLEL() { return getToken(GroovyExpParser.PARALLEL, 0); }
		public TerminalNode MAX_DATA_PROCESSORS() { return getToken(GroovyExpParser.MAX_DATA_PROCESSORS, 0); }
		public TerminalNode MIN_CHUNK_SIZE() { return getToken(GroovyExpParser.MIN_CHUNK_SIZE, 0); }
		public TerminalNode SKIP_BAD_FILE() { return getToken(GroovyExpParser.SKIP_BAD_FILE, 0); }
		public TerminalNode NUM() { return getToken(GroovyExpParser.NUM, 0); }
		public TerminalNode FILE_FORMAT() { return getToken(GroovyExpParser.FILE_FORMAT, 0); }
		public TerminalNode UNKNOWN() { return getToken(GroovyExpParser.UNKNOWN, 0); }
		public TerminalNode IMMEDIATE() { return getToken(GroovyExpParser.IMMEDIATE, 0); }
		public TerminalNode STARTING() { return getToken(GroovyExpParser.STARTING, 0); }
		public TerminalNode ENDING() { return getToken(GroovyExpParser.ENDING, 0); }
		public TerminalNode INITIALLY() { return getToken(GroovyExpParser.INITIALLY, 0); }
		public TerminalNode REFRESH() { return getToken(GroovyExpParser.REFRESH, 0); }
		public TerminalNode ACTIVATE() { return getToken(GroovyExpParser.ACTIVATE, 0); }
		public TerminalNode LOGGED() { return getToken(GroovyExpParser.LOGGED, 0); }
		public TerminalNode EMPTY() { return getToken(GroovyExpParser.EMPTY, 0); }
		public TerminalNode SEQUENCE() { return getToken(GroovyExpParser.SEQUENCE, 0); }
		public TerminalNode INCREMENT() { return getToken(GroovyExpParser.INCREMENT, 0); }
		public TerminalNode MINVALUE() { return getToken(GroovyExpParser.MINVALUE, 0); }
		public TerminalNode MAXVALUE() { return getToken(GroovyExpParser.MAXVALUE, 0); }
		public TerminalNode INT() { return getToken(GroovyExpParser.INT, 0); }
		public TerminalNode UNSIGNED() { return getToken(GroovyExpParser.UNSIGNED, 0); }
		public TerminalNode REGEXP() { return getToken(GroovyExpParser.REGEXP, 0); }
		public TerminalNode BINARY() { return getToken(GroovyExpParser.BINARY, 0); }
		public TerminalNode LEADING() { return getToken(GroovyExpParser.LEADING, 0); }
		public TerminalNode TRAILING() { return getToken(GroovyExpParser.TRAILING, 0); }
		public TerminalNode RLIKE() { return getToken(GroovyExpParser.RLIKE, 0); }
		public TerminalNode CURRENT_USER() { return getToken(GroovyExpParser.CURRENT_USER, 0); }
		public TerminalNode LTRIM() { return getToken(GroovyExpParser.LTRIM, 0); }
		public TerminalNode RTRIM() { return getToken(GroovyExpParser.RTRIM, 0); }
		public TerminalNode DATE_SUB() { return getToken(GroovyExpParser.DATE_SUB, 0); }
		public TerminalNode ADDDATE() { return getToken(GroovyExpParser.ADDDATE, 0); }
		public TerminalNode DATE_ADD() { return getToken(GroovyExpParser.DATE_ADD, 0); }
		public TerminalNode YEAR_MONTH() { return getToken(GroovyExpParser.YEAR_MONTH, 0); }
		public TerminalNode DAY_HOUR() { return getToken(GroovyExpParser.DAY_HOUR, 0); }
		public TerminalNode DAY_MINUTE() { return getToken(GroovyExpParser.DAY_MINUTE, 0); }
		public TerminalNode DAY_SECOND() { return getToken(GroovyExpParser.DAY_SECOND, 0); }
		public TerminalNode HOUR_MINUTE() { return getToken(GroovyExpParser.HOUR_MINUTE, 0); }
		public TerminalNode HOUR_SECOND() { return getToken(GroovyExpParser.HOUR_SECOND, 0); }
		public TerminalNode MINUTE_SECOND() { return getToken(GroovyExpParser.MINUTE_SECOND, 0); }
		public NonReservedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonReserved; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).enterNonReserved(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GroovyExpListener ) ((GroovyExpListener)listener).exitNonReserved(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GroovyExpVisitor ) return ((GroovyExpVisitor<? extends T>)visitor).visitNonReserved(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonReservedContext nonReserved() throws RecognitionException {
		NonReservedContext _localctx = new NonReservedContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_nonReserved);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALL) | (1L << ALTER) | (1L << ANALYZE) | (1L << AND) | (1L << ANY) | (1L << ARRAY) | (1L << AS) | (1L << ASC) | (1L << AT) | (1L << BERNOULLI) | (1L << BETWEEN) | (1L << BY) | (1L << CALL) | (1L << CASCADE) | (1L << CASE) | (1L << CAST) | (1L << CATALOGS) | (1L << COLUMN) | (1L << COLUMNS) | (1L << COMMENT) | (1L << COMMIT) | (1L << COMMITTED) | (1L << CONSTRAINT) | (1L << CREATE) | (1L << CROSS) | (1L << CUBE) | (1L << CURRENT) | (1L << CURRENT_DATE) | (1L << CURRENT_PATH) | (1L << CURRENT_TIME) | (1L << CURRENT_TIMESTAMP) | (1L << CURRENT_USER) | (1L << DATA) | (1L << DATE) | (1L << DAY) | (1L << DEALLOCATE) | (1L << DELETE) | (1L << DESC) | (1L << DESCRIBE) | (1L << DISTINCT) | (1L << DISTRIBUTED) | (1L << DROP) | (1L << ELSE) | (1L << END) | (1L << ESCAPE) | (1L << EXCEPT) | (1L << EXCLUDING) | (1L << EXECUTE) | (1L << EXISTS) | (1L << EXPLAIN) | (1L << EXTRACT) | (1L << FALSE) | (1L << FILTER))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (FIRST - 64)) | (1L << (FOLLOWING - 64)) | (1L << (FOR - 64)) | (1L << (FORMAT - 64)) | (1L << (FROM - 64)) | (1L << (FULL - 64)) | (1L << (FUNCTIONS - 64)) | (1L << (GRANT - 64)) | (1L << (GRANTS - 64)) | (1L << (GRAPHVIZ - 64)) | (1L << (GROUP - 64)) | (1L << (GROUPING - 64)) | (1L << (HAVING - 64)) | (1L << (HOUR - 64)) | (1L << (IF - 64)) | (1L << (IN - 64)) | (1L << (INCLUDING - 64)) | (1L << (INNER - 64)) | (1L << (INPUT - 64)) | (1L << (INSERT - 64)) | (1L << (INTERSECT - 64)) | (1L << (MINUSES - 64)) | (1L << (INTERVAL - 64)) | (1L << (INTO - 64)) | (1L << (IO - 64)) | (1L << (IS - 64)) | (1L << (ISOLATION - 64)) | (1L << (JSON - 64)) | (1L << (JOIN - 64)) | (1L << (LAST - 64)) | (1L << (LATERAL - 64)) | (1L << (LEFT - 64)) | (1L << (LEVEL - 64)) | (1L << (LIKE - 64)) | (1L << (LIMIT - 64)) | (1L << (LISTAGG - 64)) | (1L << (LOCALTIME - 64)) | (1L << (LOCALTIMESTAMP - 64)) | (1L << (LOGICAL - 64)) | (1L << (MAP - 64)) | (1L << (MINUTE - 64)) | (1L << (MONTH - 64)) | (1L << (MODIFY - 64)) | (1L << (NATURAL - 64)) | (1L << (NFC - 64)) | (1L << (NFD - 64)) | (1L << (NFKC - 64)) | (1L << (NFKD - 64)) | (1L << (NO - 64)) | (1L << (NORMALIZE - 64)) | (1L << (NOT - 64)) | (1L << (NULL - 64)) | (1L << (NULLIF - 64)) | (1L << (NULLS - 64)) | (1L << (ON - 64)) | (1L << (ONLY - 64)) | (1L << (OPTION - 64)) | (1L << (OR - 64)) | (1L << (ORDER - 64)) | (1L << (ORDINALITY - 64)) | (1L << (OUTER - 64)) | (1L << (OUTPUT - 64)) | (1L << (OVER - 64)) | (1L << (OVERWRITE - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (PARTITION - 128)) | (1L << (PARTITIONS - 128)) | (1L << (PATH - 128)) | (1L << (POSITION - 128)) | (1L << (PRECEDING - 128)) | (1L << (PREPARE - 128)) | (1L << (PRIVILEGES - 128)) | (1L << (PROPERTIES - 128)) | (1L << (PUBLIC - 128)) | (1L << (RANGE - 128)) | (1L << (READ - 128)) | (1L << (RECURSIVE - 128)) | (1L << (RENAME - 128)) | (1L << (REPEATABLE - 128)) | (1L << (REPLACE - 128)) | (1L << (RESET - 128)) | (1L << (RESTRICT - 128)) | (1L << (REVOKE - 128)) | (1L << (RIGHT - 128)) | (1L << (ROLLBACK - 128)) | (1L << (ROLLUP - 128)) | (1L << (ROW - 128)) | (1L << (ROWS - 128)) | (1L << (SCHEMA - 128)) | (1L << (SCHEMAS - 128)) | (1L << (SECOND - 128)) | (1L << (SELECT - 128)) | (1L << (SERIALIZABLE - 128)) | (1L << (SESSION - 128)) | (1L << (SET - 128)) | (1L << (SETS - 128)) | (1L << (SHOW - 128)) | (1L << (SOME - 128)) | (1L << (START - 128)) | (1L << (STATS - 128)) | (1L << (SUBSTRING - 128)) | (1L << (SYSTEM - 128)) | (1L << (TABLE - 128)) | (1L << (TABLES - 128)) | (1L << (TABLESAMPLE - 128)) | (1L << (TEXT - 128)) | (1L << (THEN - 128)) | (1L << (TIME - 128)) | (1L << (TIMESTAMP - 128)) | (1L << (TO - 128)) | (1L << (TRANSACTION - 128)) | (1L << (TRUE - 128)) | (1L << (TRY_CAST - 128)) | (1L << (TYPE - 128)) | (1L << (TRUNCATE - 128)) | (1L << (UPDATE - 128)) | (1L << (UESCAPE - 128)) | (1L << (UNBOUNDED - 128)) | (1L << (UNCOMMITTED - 128)) | (1L << (UNION - 128)) | (1L << (UNNEST - 128)) | (1L << (USE - 128)) | (1L << (USING - 128)) | (1L << (VALIDATE - 128)) | (1L << (VALUES - 128)) | (1L << (VERBOSE - 128)) | (1L << (VIEW - 128)) | (1L << (WHEN - 128)) | (1L << (WHERE - 128)))) != 0) || ((((_la - 192)) & ~0x3f) == 0 && ((1L << (_la - 192)) & ((1L << (WITH - 192)) | (1L << (WITHIN - 192)) | (1L << (WORK - 192)) | (1L << (WRITE - 192)) | (1L << (YEAR - 192)) | (1L << (ZONE - 192)) | (1L << (SEMI - 192)) | (1L << (SORT - 192)) | (1L << (OFFSET - 192)) | (1L << (DEFAULT - 192)) | (1L << (TABLESPACE - 192)) | (1L << (EXTERNAL - 192)) | (1L << (PURGE - 192)) | (1L << (TEMPORARY - 192)) | (1L << (PRIMARY - 192)) | (1L << (KEY - 192)) | (1L << (DISABLE - 192)) | (1L << (NOVALIDATE - 192)) | (1L << (FOREIGN - 192)) | (1L << (REFERENCES - 192)) | (1L << (PARTITIONED - 192)) | (1L << (CLUSTERED - 192)) | (1L << (SORTED - 192)) | (1L << (BUCKETS - 192)) | (1L << (SKEWED - 192)) | (1L << (STORED - 192)) | (1L << (DELIMITED - 192)) | (1L << (FIELDS - 192)) | (1L << (TERMINATED - 192)) | (1L << (ESCAPED - 192)) | (1L << (COLLECTION - 192)) | (1L << (ITEMS - 192)) | (1L << (KEYS - 192)) | (1L << (LINES - 192)) | (1L << (DEFINED - 192)) | (1L << (SEQUENCEFILE - 192)) | (1L << (TEXTFILE - 192)) | (1L << (RCFILE - 192)) | (1L << (ORC - 192)) | (1L << (ORCFILE - 192)) | (1L << (PARQUET - 192)) | (1L << (AVRO - 192)) | (1L << (JSONFILE - 192)) | (1L << (INPUTFORMAT - 192)) | (1L << (OUTPUTFORMAT - 192)) | (1L << (SERDE - 192)) | (1L << (SERDEPROPERTIES - 192)) | (1L << (LOCATION - 192)) | (1L << (TBLPROPERTIES - 192)) | (1L << (RELY - 192)) | (1L << (EXCHANGE - 192)) | (1L << (RECOVER - 192)) | (1L << (ARCHIVE - 192)) | (1L << (UNARCHIVE - 192)) | (1L << (IGNORE - 192)) | (1L << (PROTECTION - 192)) | (1L << (TOUCH - 192)) | (1L << (ENABLE - 192)) | (1L << (NO_DROP - 192)) | (1L << (OFFLINE - 192)) | (1L << (COMPACT - 192)) | (1L << (WAIT - 192)) | (1L << (CONCATENATE - 192)) | (1L << (CHANGE - 192)))) != 0) || ((((_la - 256)) & ~0x3f) == 0 && ((1L << (_la - 256)) & ((1L << (AFTER - 256)) | (1L << (DEFERRED - 256)) | (1L << (REBUILD - 256)) | (1L << (INDEX - 256)) | (1L << (IDXPROPERTIES - 256)) | (1L << (FUNCTION - 256)) | (1L << (JAR - 256)) | (1L << (FILE - 256)) | (1L << (DATABASE - 256)) | (1L << (DBPROPERTIES - 256)) | (1L << (OWNER - 256)) | (1L << (RELOAD - 256)) | (1L << (USER - 256)) | (1L << (ROLE - 256)) | (1L << (FILEFORMAT - 256)) | (1L << (LOAD - 256)) | (1L << (LOCAL - 256)) | (1L << (INPATH - 256)) | (1L << (DIRECTORY - 256)) | (1L << (VACUUM - 256)) | (1L << (LIST - 256)) | (1L << (SUBPARTITION - 256)) | (1L << (SUBPARTITIONS - 256)) | (1L << (EVERY - 256)) | (1L << (INCLUSIVE - 256)) | (1L << (EXCLUSIVE - 256)) | (1L << (TEMPLATE - 256)) | (1L << (ENCODING - 256)) | (1L << (LOG - 256)) | (1L << (ERRORS - 256)) | (1L << (SEGMENT - 256)) | (1L << (REJECT - 256)) | (1L << (PERCENT - 256)) | (1L << (WRITABLE - 256)) | (1L << (CHECK - 256)) | (1L << (UNIQUE - 256)) | (1L << (CONCURRENTLY - 256)) | (1L << (BITMAP - 256)) | (1L << (FORCE - 256)) | (1L << (SPLIT - 256)) | (1L << (REINDEX - 256)) | (1L << (FREEZE - 256)) | (1L << (MSCK - 256)) | (1L << (REPAIR - 256)) | (1L << (FETCH - 256)) | (1L << (DEFINITION - 256)) | (1L << (LESS - 256)) | (1L << (THAN - 256)) | (1L << (HASH - 256)) | (1L << (NOLOGGING - 256)) | (1L << (STRUCT - 256)) | (1L << (GLOBAL - 256)) | (1L << (PRESERVE - 256)) | (1L << (AUTO_INCREMENT - 256)) | (1L << (ENGINE - 256)) | (1L << (CHARSET - 256)) | (1L << (COMPRESS - 256)) | (1L << (EXPRESS - 256)) | (1L << (REPLICATED - 256)) | (1L << (NOCOPIES - 256)) | (1L << (SPACE - 256)) | (1L << (SHRINK - 256)) | (1L << (KEY_BLOCK_SIZE - 256)))) != 0) || ((((_la - 320)) & ~0x3f) == 0 && ((1L << (_la - 320)) & ((1L << (KEY_DC_SIZE - 320)) | (1L << (AUTOEXTEND - 320)) | (1L << (NEXT - 320)) | (1L << (OFF - 320)) | (1L << (MATCHED - 320)) | (1L << (MERGE - 320)) | (1L << (OUTFILE - 320)) | (1L << (OPTIONALLY - 320)) | (1L << (ENCLOSED - 320)) | (1L << (DOUBLE_ENCLOSED - 320)) | (1L << (NULL_VALUE - 320)) | (1L << (OUTFILEMODE - 320)) | (1L << (HDFS - 320)) | (1L << (WRITEMODE - 320)) | (1L << (NORMAL - 320)) | (1L << (OVERWRITES - 320)) | (1L << (FILECOUNT - 320)) | (1L << (FILESIZE - 320)) | (1L << (CHARACTER - 320)) | (1L << (GBK - 320)) | (1L << (UTF8 - 320)) | (1L << (HEAD - 320)) | (1L << (RMTSELECT - 320)) | (1L << (INFILE - 320)) | (1L << (DATA_FORMAT - 320)) | (1L << (SEPARATOR - 320)) | (1L << (BLANKS - 320)) | (1L << (AUTOFILL - 320)) | (1L << (LENGTH - 320)) | (1L << (TABLE_FIELDS - 320)) | (1L << (MAX_BAD_RECORDS - 320)) | (1L << (DATETIME - 320)) | (1L << (TRACE - 320)) | (1L << (TRACE_PATH - 320)) | (1L << (NOSPLIT - 320)) | (1L << (PARALLEL - 320)) | (1L << (MAX_DATA_PROCESSORS - 320)) | (1L << (MIN_CHUNK_SIZE - 320)) | (1L << (SKIP_BAD_FILE - 320)) | (1L << (NUM - 320)) | (1L << (FILE_FORMAT - 320)) | (1L << (IMMEDIATE - 320)) | (1L << (STARTING - 320)) | (1L << (ENDING - 320)) | (1L << (INITIALLY - 320)) | (1L << (REFRESH - 320)) | (1L << (ACTIVATE - 320)) | (1L << (LOGGED - 320)) | (1L << (EMPTY - 320)) | (1L << (SEQUENCE - 320)) | (1L << (INCREMENT - 320)) | (1L << (MINVALUE - 320)) | (1L << (MAXVALUE - 320)) | (1L << (OVERLAY - 320)) | (1L << (PLACING - 320)) | (1L << (TRIM - 320)) | (1L << (LTRIM - 320)) | (1L << (RTRIM - 320)) | (1L << (BOTH - 320)) | (1L << (TO_CHAR - 320)) | (1L << (AGE - 320)) | (1L << (DATE_PART - 320)) | (1L << (ISFINITE - 320)))) != 0) || ((((_la - 386)) & ~0x3f) == 0 && ((1L << (_la - 386)) & ((1L << (INT - 386)) | (1L << (UNSIGNED - 386)) | (1L << (REGEXP - 386)) | (1L << (BINARY - 386)) | (1L << (LEADING - 386)) | (1L << (TRAILING - 386)) | (1L << (RLIKE - 386)) | (1L << (DATE_SUB - 386)) | (1L << (ADDDATE - 386)) | (1L << (DATE_ADD - 386)) | (1L << (YEAR_MONTH - 386)) | (1L << (DAY_HOUR - 386)) | (1L << (DAY_MINUTE - 386)) | (1L << (DAY_SECOND - 386)) | (1L << (HOUR_MINUTE - 386)) | (1L << (HOUR_SECOND - 386)) | (1L << (MINUTE_SECOND - 386)) | (1L << (UNKNOWN - 386)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return valueExpression_sempred((ValueExpressionContext)_localctx, predIndex);
		case 2:
			return primaryExpression_sempred((PrimaryExpressionContext)_localctx, predIndex);
		case 7:
			return identifier_sempred((IdentifierContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean valueExpression_sempred(ValueExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean primaryExpression_sempred(PrimaryExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean identifier_sempred(IdentifierContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u01bb\u0098\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3 \n\3\3\3\3\3\3\3\7\3%\n"+
		"\3\f\3\16\3(\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\65\n"+
		"\4\f\4\16\48\13\4\5\4:\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"F\n\4\f\4\16\4I\13\4\5\4K\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4U\n\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4_\n\4\f\4\16\4b\13\4\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\5\6j\n\6\5\6l\n\6\3\7\3\7\3\b\3\b\3\b\7\bs\n\b\f\b\16\bv\13"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t~\n\t\3\t\3\t\3\t\3\t\5\t\u0084\n\t\3\t"+
		"\3\t\3\t\3\t\7\t\u008a\n\t\f\t\16\t\u008d\13\t\3\n\3\n\3\n\5\n\u0092\n"+
		"\n\3\13\3\13\3\f\3\f\3\f\2\5\4\6\20\r\2\4\6\b\n\f\16\20\22\24\26\2\7\4"+
		"\2\u019d\u019e\u01a7\u01a7\5\2\u019d\u01a2\u01a4\u01a6\u01a8\u01a8\3\2"+
		"\u0196\u019c\4\2@@\u00b0\u00b0\5\2\f\u0132\u0134\u0180\u0184\u0195\2\u00a9"+
		"\2\30\3\2\2\2\4\37\3\2\2\2\6T\3\2\2\2\bc\3\2\2\2\nk\3\2\2\2\fm\3\2\2\2"+
		"\16o\3\2\2\2\20\u0083\3\2\2\2\22\u0091\3\2\2\2\24\u0093\3\2\2\2\26\u0095"+
		"\3\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\b\3\1\2\34 \5"+
		"\6\4\2\35\36\t\2\2\2\36 \5\4\3\4\37\33\3\2\2\2\37\35\3\2\2\2 &\3\2\2\2"+
		"!\"\f\3\2\2\"#\t\3\2\2#%\5\4\3\4$!\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2"+
		"\2\2\'\5\3\2\2\2(&\3\2\2\2)*\b\4\1\2*U\7u\2\2+U\5\22\n\2,U\5\f\7\2-U\5"+
		"\n\6\2.U\7\u01ab\2\2/\60\5\16\b\2\609\7\3\2\2\61\66\5\4\3\2\62\63\7\4"+
		"\2\2\63\65\5\4\3\2\64\62\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2"+
		"\2\67:\3\2\2\28\66\3\2\2\29\61\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\7\5\2\2<"+
		"U\3\2\2\2=>\5\20\t\2>?\7\6\2\2?@\5\4\3\2@U\3\2\2\2AJ\7\3\2\2BG\5\20\t"+
		"\2CD\7\4\2\2DF\5\20\t\2EC\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HK\3\2"+
		"\2\2IG\3\2\2\2JB\3\2\2\2JK\3\2\2\2KL\3\2\2\2LM\7\5\2\2MN\7\6\2\2NU\5\4"+
		"\3\2OP\7\3\2\2PQ\5\4\3\2QR\7\5\2\2RU\3\2\2\2SU\5\20\t\2T)\3\2\2\2T+\3"+
		"\2\2\2T,\3\2\2\2T-\3\2\2\2T.\3\2\2\2T/\3\2\2\2T=\3\2\2\2TA\3\2\2\2TO\3"+
		"\2\2\2TS\3\2\2\2U`\3\2\2\2VW\f\5\2\2WX\7\7\2\2XY\5\4\3\2YZ\7\b\2\2Z_\3"+
		"\2\2\2[\\\f\3\2\2\\]\7\t\2\2]_\5\20\t\2^V\3\2\2\2^[\3\2\2\2_b\3\2\2\2"+
		"`^\3\2\2\2`a\3\2\2\2a\7\3\2\2\2b`\3\2\2\2cd\t\4\2\2d\t\3\2\2\2el\7\u01a9"+
		"\2\2fi\7\u01aa\2\2gh\7\u00b5\2\2hj\7\u01a9\2\2ig\3\2\2\2ij\3\2\2\2jl\3"+
		"\2\2\2ke\3\2\2\2kf\3\2\2\2l\13\3\2\2\2mn\t\5\2\2n\r\3\2\2\2ot\5\20\t\2"+
		"pq\7\t\2\2qs\5\20\t\2rp\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\17\3\2"+
		"\2\2vt\3\2\2\2wx\b\t\1\2x}\7\u01af\2\2yz\7\n\2\2z~\7\u01af\2\2{|\7\13"+
		"\2\2|~\7\u01af\2\2}y\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0084\3\2\2\2\177\u0084"+
		"\7\u01b1\2\2\u0080\u0084\5\26\f\2\u0081\u0084\7\u01b2\2\2\u0082\u0084"+
		"\7\u01b0\2\2\u0083w\3\2\2\2\u0083\177\3\2\2\2\u0083\u0080\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0082\3\2\2\2\u0084\u008b\3\2\2\2\u0085\u0086\f\3"+
		"\2\2\u0086\u0087\5\n\6\2\u0087\u0088\7\b\2\2\u0088\u008a\3\2\2\2\u0089"+
		"\u0085\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\21\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u0092\7\u01ad\2\2\u008f"+
		"\u0092\7\u01ae\2\2\u0090\u0092\7\u01ac\2\2\u0091\u008e\3\2\2\2\u0091\u008f"+
		"\3\2\2\2\u0091\u0090\3\2\2\2\u0092\23\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\25\3\2\2\2\u0095\u0096\t\6\2\2\u0096\27\3\2\2\2\22\37&\669GJT^`ikt}\u0083"+
		"\u008b\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
package com.newland.bd.workflow.sql.bean;

import org.omg.CORBA.UNKNOWN;

/**
 * Create by Howard on 2018/10/7
 */
public enum TableOpt{
    UNKNOWN(DbOptAuthority.UNKNOWN),
    SHOW(DbOptAuthority.DQL),
    SELECT(DbOptAuthority.DQL),
    UPDATE(DbOptAuthority.DML),
    DELETE(DbOptAuthority.DML),
    INSERT(DbOptAuthority.DML),
    LOAD_DATA(DbOptAuthority.DML),
    WRITE_DATA(DbOptAuthority.DML),
    TRUNCATE(DbOptAuthority.DML),
    CREATE_DATABASE(DbOptAuthority.DDL),
    DROP_DATABASE(DbOptAuthority.DDL),
    ALTER_MODIFY_DATABASE(DbOptAuthority.DDL),
    CREATE_TABLE(DbOptAuthority.DDL),
    DROP_TABLE(DbOptAuthority.DDL),
    CREATE_VIEW(DbOptAuthority.DDL),
    DROP_VIEW(DbOptAuthority.DDL),
    CREATE_SCHEMA(DbOptAuthority.DDL),
    DROP_SCHEMA(DbOptAuthority.DDL),
    CREATE_INDEX(DbOptAuthority.DDL),
    DROP_INDEX(DbOptAuthority.DDL),
    CREATE_FUNCTION(DbOptAuthority.DDL),
    DROP_FUNCTION(DbOptAuthority.DDL),
    RELOAD_FUNCTION(DbOptAuthority.DDL),
    ADD_COLUMN(DbOptAuthority.DDL),
    DROP_COLUMN(DbOptAuthority.DDL),
    ALTER(DbOptAuthority.DDL),
    ALTER_RENAME_SCHEMA(DbOptAuthority.DDL),
    ALTER_RENAME_FROMTABLE(DbOptAuthority.DDL),
    ALTER_RENAME_TOTABLE(DbOptAuthority.DDL),
    ALTER_MODIFY_COLUMNTYPE(DbOptAuthority.DDL),
    ALTER_RENAME_COLUMN(DbOptAuthority.DDL),
    ALTER_DROP_COLUMN(DbOptAuthority.DDL),
    ALTER_ADD_COLUMN(DbOptAuthority.DDL),
    ALTER_MODIFY_COLUMN(DbOptAuthority.DDL),
    ALTER_SET_TBLPROPERTIES(DbOptAuthority.DDL),
    ALTER_SET_TBLCONSTRAINT(DbOptAuthority.DDL),
    ALTER_SET_PARTITION(DbOptAuthority.DML),
    ALTER_ARCHIVE_PARTITION(DbOptAuthority.DDL),
    ALTER_MODIFY_VIEW(DbOptAuthority.DDL),
    ALTER_REBUILD_INDEX(DbOptAuthority.DDL),
    CALL(DbOptAuthority.DDL),
    GRANT(DbOptAuthority.DCL),
    REVOKE(DbOptAuthority.DCL),
    COMMIT(DbOptAuthority.TCL),
    ROLLBACK(DbOptAuthority.TCL),
    OPTIMIZE(DbOptAuthority.DOL),
    ;
    private DbOptAuthority dbOptAuthority;
    private TableOpt(DbOptAuthority dbOptAuthority){
        this.dbOptAuthority =dbOptAuthority;
    }

    public boolean isDDL(){
        return this.dbOptAuthority.equals(DbOptAuthority.DDL);
    }

    public boolean isDML(){
        return this.dbOptAuthority.equals(DbOptAuthority.DML);
    }

    public boolean isDQL(){
        return this.dbOptAuthority.equals(DbOptAuthority.DQL);
    }

    public boolean isDCL(){
        return this.dbOptAuthority.equals(DbOptAuthority.DCL);
    }

    public boolean isTCL(){
        return this.dbOptAuthority.equals(DbOptAuthority.TCL);
    }

    public boolean isDOL(){
        return this.dbOptAuthority.equals(DbOptAuthority.DOL);
    }

    public boolean isUNKNOWN(){
        return this.dbOptAuthority.equals(DbOptAuthority.UNKNOWN);
    }
}

enum DbOptAuthority{
    UNKNOWN,
    DML,
    DDL,
    DQL,
    DCL,
    TCL,
    DOL,    //优化分析性的语句
    ;
}
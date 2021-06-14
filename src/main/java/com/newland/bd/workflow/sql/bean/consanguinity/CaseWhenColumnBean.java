package com.newland.bd.workflow.sql.bean.consanguinity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Howard on 2018/10/24
 */
public class CaseWhenColumnBean {
    public ColumnBean caseExpression;
    public List<ColumnBean> whenClauses = new ArrayList<>();
    public ColumnBean elseExpression;

    public String toConsanguinityFormat(){
        String caseExpression = this.caseExpression==null ? "": this.caseExpression.toConsanguinityFormat();

        String strWhenClauses = "";
        for(ColumnBean whenClauses :this.whenClauses){
            strWhenClauses+= whenClauses.toConsanguinityFormat();
            strWhenClauses+=" ";
        }

        String elseExpression = this.elseExpression==null ? "":"ELSE "+ this.elseExpression.toConsanguinityFormat()+" ";

        return  "(CASE "+ caseExpression
                + " " + strWhenClauses
                + elseExpression
                + "END)";
    }
}

package com.zhouxh.antlr4.tool.sql.parserExt.consanguinity;

import com.zhouxh.antlr4.tool.sql.bean.consanguinity.ColumnBean;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.ColumnType;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.DstTableDetailBean;
import com.zhouxh.antlr4.tool.sql.bean.consanguinity.DstTableDetailBeanList;

import java.util.List;
import java.util.Set;

/**
 * Create by Howard on 2018/11/12
 */
public class MultiSqlConsanguinityAnalyseUtil {

    /**
     * 多个DstTableDetailBean对象之间，进行血缘溯源
     * @param dstTableDetailBeanList 有多条sql语句，每条sql语句会输出一个DstTableDetailBean结构，从而组成了List<DstTableDetailBean>
     * @return  返回的List<DstTableDetailBean> 中的原子columnBean，是经过List相互间血缘解析的
     */

    public static DstTableDetailBeanList multiSqlColumnBeanTrace(DstTableDetailBeanList dstTableDetailBeanList){
        return multiSqlColumnBeanTrace(dstTableDetailBeanList,null);
    }

    public static DstTableDetailBeanList multiSqlColumnBeanTrace(DstTableDetailBeanList dstTableDetailBeanList, Set<String> temporaryTableSet){
        for(int i = 0; i< dstTableDetailBeanList.size(); i++){
            //设置正在调整的DstTableDetailBean
            DstTableDetailBean adjustDstTableDetailBean = dstTableDetailBeanList.get(i);

            if(adjustDstTableDetailBean.sourceColumnBeanList == null){
                continue;
            }

            //遍历adjustDstTableDetailBean所有的源字段
            for(ColumnBean curSrcColumnBean:adjustDstTableDetailBean.sourceColumnBeanList){
                List<ColumnBean> atomcolumnBeanList = ConsanguinityAnalyseUtil.columnBeanToAtomColumnBeansByNoRemoveRepetition(curSrcColumnBean);
                for(ColumnBean atomColumnBean:atomcolumnBeanList){//对每一个atomColumnBean进行DstTableDetailBean维度的二次溯源

                    for(int j = 0; j< i; j++){
                        //设置参考的DstTableDetailBean
                        DstTableDetailBean  referToDstTableDetailBean = dstTableDetailBeanList.get(j);
                        if(columnBeanTrace(atomColumnBean,referToDstTableDetailBean,temporaryTableSet)){
                            break;
                        }
                    }
                }
            }

        }
        return dstTableDetailBeanList;
    }

    /**
     * 仅仅供multiSqlColumnBeanTrace内部调用，不对外开放
     * @param atomColumnBean
     * @param referToDstTableDetailBean
     * @return
     */
    private static boolean columnBeanTrace(ColumnBean atomColumnBean,DstTableDetailBean  referToDstTableDetailBean,Set<String> temporaryTableSet){
        if(atomColumnBean.columnType != ColumnType.NORMAL){
            return true;
        }
        if(referToDstTableDetailBean.sourceColumnBeanList == null){
            return false;
        }
        int i = 0;
        for(String dstColumnName:referToDstTableDetailBean.dstColumnNameList){
            if(temporaryTableSet == null || temporaryTableSet.contains(atomColumnBean.belongToTableName.toUpperCase())){
                //两种情况下需要进行溯源。
                // 1：temporaryTableSet== null，这个时候不论是否为临时表，都进行溯源
                // 2：temporaryTableSet不为空，这个时候只对临时表进行溯源
                if(atomColumnBean.columnName.equalsIgnoreCase(dstColumnName) && atomColumnBean.belongToTableName.equalsIgnoreCase(referToDstTableDetailBean.tablename)){

                    ColumnBean replaceColumnBean = null;
                    try {
                        replaceColumnBean = referToDstTableDetailBean.sourceColumnBeanList.get(i);
                    }catch(IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }


                    if(replaceColumnBean != null){
                        atomColumnBean.copy(replaceColumnBean);
                        return true;
                    }else{
                        return false;
                    }

                }
            }

            i++;
        }

        return false;
    }
}

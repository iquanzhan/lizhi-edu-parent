package com.chengxiaoxiao.lizhiedu.file.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel读取监听
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/21 9:03
 * @Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<List<Object>> {

    private final List<Object> rows = new ArrayList<>();
    private final Integer limit;

    public ExcelListener(Integer limit) {
        super();
        this.limit = limit;
    }


    @Override
    public void invoke(List<Object> objects, AnalysisContext analysisContext) {
        if (limit != null && limit > 0 && rows.size() > limit) {
            return;
        }
        rows.add(objects);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<Object> getRows() {
        return rows;
    }

}

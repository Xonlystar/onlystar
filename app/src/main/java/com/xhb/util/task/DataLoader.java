package com.xhb.util.task;

/**
 * Created by onlystar on 2016/8/1.
 */

public interface DataLoader<Result> {
    public Result loadData();;
    public void updateUi(Result result);

}

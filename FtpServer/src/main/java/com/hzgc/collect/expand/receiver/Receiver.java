package com.hzgc.collect.expand.receiver;

import com.hzgc.collect.expand.log.LogEvent;

import java.util.concurrent.BlockingQueue;

public interface Receiver {
    /**
     * 此方法可将数据插入当前Recvicer的队列列
     *
     * @param data 数据对象
     */
    public void putData(LogEvent data);

    /**
     * 获取当前队列
     */
    public BlockingQueue<LogEvent> getQueue();

    /**
     * 获取当前队列ID
     */
    public String getQueueID();

}

package com.hzgc.collect.expand.receiver;

import com.hzgc.collect.expand.conf.CommonConf;
import com.hzgc.collect.expand.log.DataReceiveLogWriter;
import com.hzgc.collect.expand.log.LogEvent;
import com.hzgc.collect.expand.log.LogWriter;
import com.hzgc.collect.expand.processer.ProcessThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ReceiverImpl implements Receiver {
    private BlockingQueue<LogEvent> queue;
    private LogWriter receiveWriter;
    private String queueID;
    private ProcessThread processThread;

    public ReceiverImpl(CommonConf conf, String queueID, long receiveCount) {
        this.queueID = queueID;
        this.queue = new ArrayBlockingQueue<>(conf.getCapacity());
        this.receiveWriter = new DataReceiveLogWriter(conf, queueID, receiveCount);
    }

    @Override
    public void putData(LogEvent event) {
        receiveWriter.writeEvent(event);
        if (event != null) {
            try {
                queue.put(new LogEvent());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BlockingQueue<LogEvent> getQueue() {
        return this.queue;
    }

    @Override
    public String getQueueID() {
        return this.queueID;
    }


}

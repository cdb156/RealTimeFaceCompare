package com.hzgc.collect.expand.processer;


import com.hzgc.collect.expand.conf.CommonConf;
import com.hzgc.collect.expand.log.DataProcessLogWriter;
import com.hzgc.collect.expand.log.LogWriter;
import com.hzgc.collect.expand.log.LogEvent;

import java.util.concurrent.BlockingQueue;

public class ProcessThread implements Runnable {
    private CommonConf conf;
    private BlockingQueue<LogEvent> queue;
    private LogWriter writer;
    public ProcessThread(CommonConf conf, BlockingQueue<LogEvent> queue, String queueID, long count) {
        this.conf = conf;
        this.queue = queue;
        this.writer = new DataProcessLogWriter(conf, queueID, count);
    }
    @Override
    public void run() {
//        while (queue.remove())
//        writer.writeEvent();
            while (true) {
                try {
                    LogEvent event = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

    }

    public BlockingQueue<LogEvent> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<LogEvent> queue) {
        this.queue = queue;
    }
}

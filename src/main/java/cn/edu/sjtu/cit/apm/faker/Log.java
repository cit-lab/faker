package cn.edu.sjtu.cit.apm.faker;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gpl on 15/9/24.
 */
public class Log {
    private String level;
    private Integer interval;
    private List<String> messages;
    private List<String> exceptions;

    private Timer timer;
    private TimerTask task;

    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);

    public Log(HierarchicalConfiguration configuration) {
        LOGGER.info("add new fake log");
        level = configuration.getString("level");
        interval = configuration.getInt("interval");
        if (level.equals("error")) {
            exceptions = Util.readStrings(configuration.configurationsAt("exceptions.exception"));
        } else {
            messages = Util.readStrings(configuration.configurationsAt("messages.message"));
        }
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (level.equals("error")) {
                    String ex = Util.randomString(exceptions);
                    if (ex.equals("exception")) {
                        LOGGER.error("error occurred", new Exception("a dummy exception"));
                    } else if (ex.equals("io")) {
                        LOGGER.error("io error", new IOException("a dummy io exception"));
                    } else if (ex.equals("null")) {
                        LOGGER.error("null pointer error", new NullPointerException("a dummy null pointer exception"));
                    }
                } else {
                    String msg = Util.randomString(messages);
                    if (level.equals("info")) {
                        LOGGER.info(msg);
                    }
                    if (level.equals("warn")) {
                        LOGGER.warn(msg);
                    }
                    if (level.equals("debug")) {
                        LOGGER.debug(msg);
                    }
                }
            }
        };
        timer.schedule(task, interval, interval);
    }
}

package com.cleaner.main;

/**
 * Created by yangfeng on 16-6-28.
 */
public class ExitingTrigger {
    private static final long DEFAULT_INTERVAL = 3000; // 3 seconds

    private long mExitTime;
    private final long INTERVAL;

    public ExitingTrigger() {
        this(DEFAULT_INTERVAL);
    }

    public ExitingTrigger(long interval) {
        INTERVAL = interval;
    }

    public boolean testExpired(long timeStamp) {
        if (timeStamp - mExitTime > INTERVAL) {
            mExitTime = timeStamp;
            return true;
        } else {
            return false;
        }
    }
}

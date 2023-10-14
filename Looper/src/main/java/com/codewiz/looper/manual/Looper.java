package com.codewiz.looper.manual;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;

import com.codewiz.looper.auto.LooperRunnable;

/**
 * Looper is a utility class that enables the execution of a LooperRunnable on a separate HandlerThread with control over its lifecycle.
 * It provides methods to start, pause, resume, stop, and destroy the looper execution, as well as setting the time period between loop iterations.
 */
public class Looper {
    private HandlerThread handlerThread;
    private Handler handler;
    private LoopRunnable loopRunnable;
    private static Handler main;
    private static Looper looper;

    /**
     * Creates a new instance of Looper with the specified name.
     *
     * @param name The name of the Looper instance.
     */
    public Looper(String name) {
        handlerThread = new HandlerThread(name);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        loopRunnable = new LoopRunnable();
        main = new Handler(android.os.Looper.getMainLooper());
    }

    /**
     * Returns a singleton instance of Looper with the specified name. If the instance does not exist, it will be created.
     *
     * @param name The name of the Looper instance.
     * @return The Looper instance.
     */
    public static Looper getInstance(String name) {
        if (looper == null) {
            looper = new Looper(name);
        }
        return looper;
    }

    /**
     * Sets the LooperRunnable to be executed in the loop.
     *
     * @param runnable The LooperRunnable to be executed.
     * @return The Looper instance.
     */
    public Looper run(LooperRunnable runnable) {
        loopRunnable.setRunnable(runnable);
        return this;
    }

    /**
     * Starts the execution of the looper loop.
     */
    public void start() {
        handler.post(loopRunnable);
        loopRunnable.setLoop(true);
    }

    /**
     * Sets the time period between loop iterations.
     *
     * @param time The time period in milliseconds.
     */
    public void setTime(int time) {
        loopRunnable.setPeriod(time);
    }

    /**
     * Resumes the execution of the looper loop.
     */
    public void resume() {
        handler.post(loopRunnable);
        loopRunnable.setLoop(true);
    }

    /**
     * Pauses the execution of the looper loop.
     */
    public void pause() {
        loopRunnable.setLoop(false);
        handler.removeCallbacks(loopRunnable);
    }

    /**
     * Stops the execution of the looper loop.
     */
    public void stop() {
        loopRunnable.setLoop(false);
        handler.removeCallbacks(loopRunnable);
    }

    /**
     * Destroys the Looper instance and cleans up resources.
     */
    public void destroy() {
        if (handlerThread.isAlive()) {
            handlerThread.quit();
            handlerThread.quitSafely();
            handler = null;
            handlerThread = null;
            loopRunnable = null;
        }
    }


    /**
     * The Runnable implementation that executes the looper loop.
     */
    private static class LoopRunnable implements Runnable {
        private LooperRunnable runnable;
        private boolean loop = true;
        private int period = 1000;

        /**
         * Sets the LooperRunnable to be executed within the loop.
         *
         * @param runnable The LooperRunnable to be executed.
         */
        public void setRunnable(final LooperRunnable runnable) {
            this.runnable = runnable;

        }

        /**
         * Sets the loop flag to control the execution loop.
         *
         * @param loop Flag indicating whether to continue looping.
         */
        public void setLoop(boolean loop) {
            this.loop = loop;
        }

        /**
         * Sets the period of delay between loop iterations.
         *
         * @param period The delay period in milliseconds.
         */
        public void setPeriod(int period) {
            this.period = period;
        }

        /**
         * Executes the looper loop by repeatedly running the LooperRunnable, sleeping for the specified period,
         * and posting the onUiThread Runnable to the main thread.
         */
        @Override
        public void run() {
            while (loop) {
                runnable.run();
                SystemClock.sleep(period);
            }
        }
    }
}

package com.codewiz.looper.manual;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;

import com.codewiz.looper.auto.AutoLooperRunnable;

/**
 * AutoLooper is a utility class that enables the execution of a AutoLooperRunnable on a separate HandlerThread with control over its lifecycle.
 * It provides methods to start, pause, resume, stop, and destroy the manualLooper execution, as well as setting the time period between loop iterations.
 */
public class ManualLooper {
    private HandlerThread handlerThread;
    private Handler handler;
    private LoopRunnable loopRunnable;
    private static Handler main;
    private static ManualLooper manualLooper;

    /**
     * Creates a new instance of AutoLooper with the specified name.
     *
     * @param name The name of the AutoLooper instance.
     */
    public ManualLooper(String name) {
        handlerThread = new HandlerThread(name);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        loopRunnable = new LoopRunnable();
        main = new Handler(android.os.Looper.getMainLooper());
    }

    /**
     * Returns a singleton instance of AutoLooper with the specified name. If the instance does not exist, it will be created.
     *
     * @param name The name of the AutoLooper instance.
     * @return The AutoLooper instance.
     */
    public static ManualLooper getInstance(String name) {
        if (manualLooper == null) {
            manualLooper = new ManualLooper(name);
        }
        return manualLooper;
    }

    /**
     * Sets the AutoLooperRunnable to be executed in the loop.
     *
     * @param runnable The AutoLooperRunnable to be executed.
     * @return The AutoLooper instance.
     */
    public ManualLooper run(AutoLooperRunnable runnable) {
        loopRunnable.setRunnable(runnable);
        return this;
    }

    /**
     * Starts the execution of the manualLooper loop.
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
     * Resumes the execution of the manualLooper loop.
     */
    public void resume() {
        handler.post(loopRunnable);
        loopRunnable.setLoop(true);
    }

    /**
     * Pauses the execution of the manualLooper loop.
     */
    public void pause() {
        loopRunnable.setLoop(false);
        handler.removeCallbacks(loopRunnable);
    }

    /**
     * Stops the execution of the manualLooper loop.
     */
    public void stop() {
        loopRunnable.setLoop(false);
        handler.removeCallbacks(loopRunnable);
    }

    /**
     * Destroys the AutoLooper instance and cleans up resources.
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
     * The Runnable implementation that executes the manualLooper loop.
     */
    private static class LoopRunnable implements Runnable {
        private AutoLooperRunnable runnable;
        private boolean loop = true;
        private int period = 1000;

        /**
         * Sets the AutoLooperRunnable to be executed within the loop.
         *
         * @param runnable The AutoLooperRunnable to be executed.
         */
        public void setRunnable(final AutoLooperRunnable runnable) {
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
         * Executes the manualLooper loop by repeatedly running the AutoLooperRunnable, sleeping for the specified period,
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

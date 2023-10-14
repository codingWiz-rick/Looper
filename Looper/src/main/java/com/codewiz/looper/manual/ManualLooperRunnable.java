package com.codewiz.looper.manual;
/**
 * The AutoLooperRunnable interface extends the Runnable interface and provides an additional method to obtain a Runnable
 * to be executed on the UI (main) thread.
 */
public interface ManualLooperRunnable extends Runnable {

    /**
     * Retrieves a Runnable to be executed on the UI (main) thread.
     *
     * @return The Runnable to be executed on the UI thread.
     */
}

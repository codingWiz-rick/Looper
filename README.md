# Looper: A Flexible Background Task Execution Library for Android

Looper is a versatile library for Android that simplifies background task execution based on the lifecycle events of a `LifecycleOwner`. It allows you to execute tasks in a loop and control their execution easily.

## Features

- Convenient task execution based on Android Lifecycle events.
- Flexible looping execution of tasks.
- Control the loop execution, pausing, resuming, and stopping tasks.
- Set the time interval between task executions.

## Getting Started With the Auto Looper

To integrate `Looper` into your Android project, follow these steps:

1. **Initialize a Looper instance**:
```
AutoLooper looper = AutoLooper.getInstance("MyLooper");
```
2. **Attach it to a LifecycleOwner**
```
lifecycle.addObserver(looper);

```
3. **Post a task to execute in a loop**
```
looper.post(new AutoLooperRunnable() {
            @Override
            public void run() {
                // Task to be done repeatedly
            }
        });
```
4. **Set the Time in between pauses**
```
 looper.setTime(1000); // in Miliseconds

```
**Volllay you are good to go**

## Getting Started With the Manual Looper


1. **Initialize a Looper instance**:
```
ManualLooper looper = ManualLooper.getInstance("MyLooper");
```
2. **Attach it to a LifecycleOwner**
```
lifecycle.addObserver(looper);

```
3. **Post a task to execute in a loop**
```
looper.post(new ManualLooperRunnable() {
            @Override
            public void run() {
                // Task to be done repeatedly
            }
        });
```
4. **Set the Time in between pauses**
```
 looper.setTime(1000); // in Miliseconds

```
5. **Pause the Loop Manually**
```
looper.pause();
```
5. **Stop the Loop Manually**
```
looper.stop();

```
5. **When done, destroy the Looper Manually**

```
looper.destroy();
```
# License
This project is licensed under the MIT License.

# Contact
If you have any questions or need assistance, feel free to contact us at [chakrabortyshubham66@gmail.com].

Enjoy using the Looper class for simplified background task execution in your Android projects! We look forward to your feedback and contributions.

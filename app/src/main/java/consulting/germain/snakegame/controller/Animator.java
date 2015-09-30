/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import java.util.concurrent.TimeUnit;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.model.Settings;
import consulting.germain.snakegame.model.Snake;
import consulting.germain.snakegame.model.SnakeStateFactory;

/**
 * Created by mark_local on 22/09/2015.
 * Each time tick advances the animation one move
 */
public class Animator implements Runnable {
    /**
     * the time source to use
     */
    private TimeSource timeSource;
    /**
     * snake being animated
     */
    private Snake      snake;
    /**
     * flag
     */
    private boolean runningRequested = true;
    /**
     * flag
     */
    private boolean started          = false;
    /**
     * flag
     */
    private boolean finished         = false;
    /**
     * last recorded animation time
     */
    private long lastStepTime;
    /**
     * minimum time between starting animation steps
     */
    private long stepTimeIntervalMillis;
    /**
     * number of steps that have been animated
     */
    private int stepCount = 0;
    /**
     * requested control direction
     */
    private SnakeDirection snakeDirectionControl;

    public Animator(TimeSource timeSource) {
        this.timeSource = timeSource;
        this.stepTimeIntervalMillis = Settings.timebaseStartMillis;
        this.snakeDirectionControl = SnakeStateFactory.getDefaultSnakeDirectionControl();
        this.lastStepTime = timeSource.currentTimeMillis();
    }

    /**
     * Starts executing the active part of the class' code. This method is
     * called when a thread is started that has been created with a class which
     * implements {@code Runnable}.
     */
    @Override
    public void run() {
        try {
            lastStepTime = initialiseAnimation();
            while (runningRequested) {
                if (timeSource.timeSince(lastStepTime) > stepTimeIntervalMillis) {
                    lastStepTime = advanceAnimation();
                } else {
                    TimeUnit.MILLISECONDS.sleep(stepTimeIntervalMillis / 2);
                }
            }
        } catch (InterruptedException e) {
            // deliberately empty
        } catch (Exception e) {
            e.printStackTrace();
        }

        finished = true;
    }

    /**
     * advance the animation by one step
     *
     * @return time at the end of the animation
     */
    synchronized
    private long advanceAnimation() throws Exception {
        long stepTime = timeSource.currentTimeMillis();
        ++stepCount;
        snake.move();
        // TODO: collision detection, prize detection, and lots more
        return stepTime;
    }

    /**
     * alter requested snake direction, will validate against current snake direction
     *
     * @param snakeDirectionRequest requested direction
     * @return resulting direction, may be different if we can't allow the new direction
     */
    synchronized
    public SnakeDirection setSnakeDirectionControl(SnakeDirection snakeDirectionRequest) {
        this.snakeDirectionControl = snakeDirectionRequest;
        return snakeDirectionControl;
    }

    /**
     * @return snake
     */
    synchronized // so we can't request snake while it is being animated
    public Snake getSnake() {
        return snake;
    }

    /**
     * initialise the animation
     *
     * @return lastStepTime
     */
    synchronized
    private long initialiseAnimation() {
        this.snake = new Snake(SnakeStateFactory.createDefault());
        lastStepTime = timeSource.currentTimeMillis();
        started = true;
        return lastStepTime;
    }

    /**
     * @return state of runningRequested flag
     */
    public boolean isRunningRequested() {
        return runningRequested;
    }

    /**
     * clear the runningRequested flag
     */
    public void requestStop() {
        this.runningRequested = false;
    }

    /**
     * @return last step time, in milliseconds since java epoch
     */
    public long getLastStepTime() {
        return lastStepTime;
    }

    /**
     * @return current step time interval, in milliseconds
     */
    public long getStepTimeIntervalMillis() {
        return stepTimeIntervalMillis;
    }

    /**
     * set the step time interval
     * @param stepTimeIntervalMillis time in milli seconds between animation steps
     * @return the old step time
     */
    public long setStepTimeIntervalMillis(long stepTimeIntervalMillis) {
        long oldSteptime = this.stepTimeIntervalMillis;
        if (stepTimeIntervalMillis > 0) {
            this.stepTimeIntervalMillis = stepTimeIntervalMillis;
        }
        return oldSteptime;
    }

    /**
     * @return true if animation is finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @return current time source, test method
     */
    public TimeSource getTimeSource() {
        return timeSource;
    }

    /**
     * @return current step count, test method
     */
    int getStepCount() {
        return stepCount;
    }

    /**
     * @return true if animation has started, test method
     */
    boolean isStarted() {
        return started;
    }

    /**
     * @return snakeDirection, test method
     */
    SnakeDirection getSnakeDirection() {
        return snake.getHeadDirection();
    }

    /**
     * @return current control diretion, test method
     */
    SnakeDirection getSnakeDirectionControl() {
        return snakeDirectionControl;
    }

    /**
     * provide test access to initialisation
     *
     * @return last step time
     */
    long testAnimationInit() {
        return initialiseAnimation();
    }
}

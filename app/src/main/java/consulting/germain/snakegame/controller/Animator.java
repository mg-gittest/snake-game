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
     * flag
     */
    private boolean    runningRequested;
    /**
     * flag
     */
    private boolean hasFinished = false;
    /**
     * last recorded animation time
     */
    private long           lastStepTime;
    /**
     * minimum time between starting animation steps
     */
    private long           stepTimeIntervalMillis;
    /**
     * number of steps that have been animated
     */
    private int            stepCount;
    /**
     * snake being animated
     */
    private Snake          snake;
    /**
     * requested control direction
     */
    private SnakeDirection snakeDirectionControl;

    public Animator(TimeSource timeSource) {
        this.timeSource = timeSource;
        this.runningRequested = true;
        this.stepTimeIntervalMillis = Settings.timebaseStartMillis;
        this.stepCount = 0;
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
                }
                TimeUnit.MILLISECONDS.sleep(stepTimeIntervalMillis / 2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        hasFinished = true;
    }

    /**
     * advance the animation by one step
     *
     * @return time at the end of the animation
     */
    synchronized
    private long advanceAnimation() throws Exception {
        lastStepTime = timeSource.currentTimeMillis();
        snake.move(snakeDirectionControl);
        // TODO: collision detection, prize detection, and lots more
        return lastStepTime;
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
     * @return
     */
    synchronized
    private long initialiseAnimation() {
        this.stepCount = 0;
        lastStepTime = timeSource.currentTimeMillis();
        this.snake = new Snake(SnakeStateFactory.createDefault());
        return lastStepTime;
    }

    /**
     * @return current step count, test method
     */
    int getStepCount() {
        return stepCount;
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
     * @return current time source, test method
     */
    public TimeSource getTimeSource() {
        return timeSource;
    }
}

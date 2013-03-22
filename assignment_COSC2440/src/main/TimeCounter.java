package main;

/**
 *
 *
 */
public class TimeCounter {
    
    public static final int DURATION = 30;
        
    private Thread thread;
    private int time;
    private boolean running;
    
    public TimeCounter() {
        
    }
    
    private Thread makeThreadCounter() {
        return new Thread() {
            @Override
            public void run() {
                while(running) {
                    try {
                        sleep(1000);
                    } catch(Exception ex) {
                        
                    }
                    
                    if(time > 0) {
                        time--;
                    } else {
                        time = DURATION;
                    }
                    
                    System.out.println(time);
                }
            }            
        };
    }
    
    public void startCounting() {
        running = true;
        time = DURATION;
        thread = makeThreadCounter();
        thread.start();
    }
    
    public void stopCounting() {
        System.out.println("STOP");
        running = false;
    }

    public int getTime() {
        return time;
    }
    
}

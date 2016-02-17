package simulation;

public abstract class AbstractEvent implements Event {

    /**
     * Constructor for AbstractEvent.
     */
    protected double t;
    
    public AbstractEvent(double time) {
        // TODO implement the constructor
        this.t=time;
    }

    /**
     * Returns the time (in ticks) at which this event will occur.
     */
    @Override
    public double time() {
        // TODO implement this method
        return this.t;
    }

    /**
     * Compares this object with the specified Event.
     */
    @Override
    public int compareTo(Event that) {
        // TODO implement this method
        if(this.t>that.time()){
            return 1;
        }else if(this.t<that.time()){
            return -1;
        }
        return 0;
    }

}

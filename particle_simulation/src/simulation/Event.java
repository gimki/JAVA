package simulation;

public interface Event extends Comparable<Event>{

    public double time();

    public void happen(ParticleEventHandler h) throws InterruptedException;

    public boolean isValid();

}

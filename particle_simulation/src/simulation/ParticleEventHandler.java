package simulation;

public interface ParticleEventHandler {

    public void reactTo(Tick tick) throws InterruptedException;

    public void reactTo(Collision c);

}

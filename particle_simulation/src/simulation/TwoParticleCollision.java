package simulation;

public class TwoParticleCollision extends Collision{
    
    //t is created in Abstract Event
    //so as time() and compareTo()
    
    Particle p1;
    Particle p2;
    
    /**
     * Constructor for TwoParticleCollision
     TwoParticleCollision(p1, p2, now + t);
     */
    public TwoParticleCollision(Particle particle1, Particle particle2, double time){
        
        super(time,particle1,particle2);
        this.p1=particle1;
        this.p2=particle2;
        
    }
    public void happen(ParticleEventHandler h){
        
        Particle.collide(p1,p2);
        h.reactTo(this);
    }
    
    
}

package simulation;

public class ParticleWallCollision extends Collision{
    
    //t is created in Abstract Event
    //so as time() and compareTo()
    
    protected Particle p;
    protected Wall w;
    
    /**
     * Constructor for Collision
     */
    public ParticleWallCollision(Particle particle,Wall wall, double time){

        super(time,particle);
        
        this.p=particle;
        this.w=wall;
        
    }
    public void happen(ParticleEventHandler h){
        
        Particle.collide(this.p,this.w);
        h.reactTo(this);
    }
}
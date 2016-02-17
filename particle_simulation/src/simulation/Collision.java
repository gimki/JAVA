package simulation;

public abstract class Collision extends AbstractEvent{
    
    //t is created in Abstract Event
    //so as time() and compareTo()
    
    
    
    protected Particle[] particles;
    int[] initialHits;
    
    
    /**
     * Constructor for Collision
     */
    public Collision(double time, Particle[] ps){
        
        super(time);
        // TODO implement constructor
        this.particles=ps;
        this.initialHits= new int[ps.length];
        
        for(int j=0; j<particles.length;j++){
            this.initialHits[j]=particles[j].collisions();
        }
        
    }
    
    public Collision(double time, Particle p1, Particle p2){
        
        super(time);
        // TODO implement constructor
        this.particles=new Particle[2];
        this.initialHits= new int[2];
        
        
        this.particles[0]=null;
        this.particles[1]=null;
        
        if(p1!=null){
            this.particles[0]=p1;
            this.initialHits[0]=particles[0].collisions();
        }
        
        if(p2!=null){
            this.particles[1]=p2;
            this.initialHits[1]=particles[1].collisions();
        }
        
        
    }
    
    public Collision(double time, Particle p1){
        
        super(time);
        // TODO implement constructor
        this.particles=new Particle[1];
        this.initialHits= new int[1];
        
        
        this.particles[0]=null;
        
        if(p1!=null){
            this.particles[0]=p1;
            this.initialHits[0]=particles[0].collisions();
        }
        
    }
    

    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
    public boolean isValid() {
        // TODO implement his method
        for(int i=0; i<particles.length;i++){
            if(particles[i]==null){
            continue;
            }
            if(particles[i].collisions()!=initialHits[i]){
                return false;
            }
        }
        
        return true;
    }

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        // TODO implement this method
        return particles;
    }
}

package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    
    double clock;
    MinPriorityQueue<Event> queue;
    
    
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
        // TODO implement constructor
        
        this.model=m;
        this.screen= new ParticlesView(name,m);
        this.clock=0.0; //initiate the clock
        queue=new MinPriorityQueue<Event>();
        Tick tick=new Tick(1);//not sure tick(1) or tick(0)??
        
        //initiate by adding a single Tick event
        queue.add(tick);
        
        
        //loading the list of collision from the model and add to the queue
        Iterable<Collision> listOfEvents = model.predictAllCollisions(clock);
        for(Collision c : listOfEvents){
            queue.add(c);
        }
        
    }

    /**
     * Runs the simulation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeAndWait(screen);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // TODO complete implementing this method
        while(queue.size()>0){

            Event event=queue.remove();
            
            if(event.isValid()){
                double elapsed=event.time()-clock;
                
                model.moveParticles(elapsed);
                
                try{
                    event.happen(this);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                clock=event.time();
            }
        }
    }
    
    
    public void reactTo(Tick tick) throws InterruptedException{
        Thread.sleep(FRAME_INTERVAL_MILLIS);
        screen.update();
        //add new tick to queue
        Tick tick1 = new Tick(tick.time() + 1);
        queue.add(tick1);
    }
    
    public void reactTo(Collision c){
        
        //update the particles collision events after a collision
        Particle[] particlesCollide=c.getParticles();
        
        //update for each particle involved in the collision one by one due to the supplied function in model with argument (particle p,double now)
        for(int i=0; i<particlesCollide.length;i++){
            Iterable<Collision> newListOfEvents=model.predictCollisions(particlesCollide[i],c.time());
            for(Collision newC: newListOfEvents){
                queue.add(newC);
            }
        }
    }
}

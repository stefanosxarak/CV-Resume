package uobspe.stonks;

import java.util.ArrayList;
import java.util.List;

public class Stonk {

    private final String name;
    private Float value;                                // The present value of the stonk
    private List<Float> history;                        // The list with the precedent values of the stonk
    private List<Stonk_Change> activeChanges;           // a list with all the changes that should be made in the future

    public Stonk(String name, Float value) {
        this.name = name;
        this.value = value;
        activeChanges = new ArrayList<Stonk_Change>();
        history = new ArrayList<Float>();
    }

    @Override
    public String toString() {
        return "Stonk{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    //A small class used to model requested changes to a Stonk
    public class Stonk_Change{
        //How long the Stonk this belongs to will affect it
        private final int lifespan;
        //How long has this change existed for. Will be deleted by its owner Stonk when it reaches lifespan
        private int age;

        //The amount of change this will affect each time it is called to effect the Stonk
        private final float partialChange;


        //Returns how much the owning Stonk's price should change by
        public Float getChange(){
            return this.partialChange;
        }

        //Tells the change to "age" by 1. When it reaches the end of its lifespan, the change is finished
        public void incrementAge(){
            this.age = this.age + 1;
        }
        //Returns true if the change has reached its end (its "age" is equal to its lifespan)
        public boolean hasReachedEnd(){
            return this.age >= this.lifespan;
        }

        Stonk_Change(int lengthOfTime, float totalChange){
            this.lifespan = lengthOfTime;
            this.age = 0;

            this.partialChange = totalChange / lengthOfTime;
        }
    }

    public String getName() {
        return name;
    }

    public Float getValue() {
        return value;
    }

    public List<Float> getHistory() {
        return history;
    }

    public void setValue(float x){
        this.value = x;
    }

    // Applies one given change (the value of a stonk cannot be negative, but can reach 0)
    private void applyChange(Stonk_Change change){
        change.incrementAge();
        this.value = this.value + change.partialChange;
        if(this.value < 0)
            this.value = (float)0;
    }

    // If there are changes to be made, make the first one and delete it from the list.
    public void applyChanges(){
        if(!activeChanges.isEmpty())
            applyChange(activeChanges.get(0));
        else return;
        if(activeChanges.get(0).hasReachedEnd())
            activeChanges.remove(0);

        this.history.add(this.value);
    }

    // Adds a change, to the queue, with a given length and total value
    public void queueChange(int lengthOfTime, float totalChange){
        activeChanges.add(new Stonk_Change(lengthOfTime, totalChange));
    }


}

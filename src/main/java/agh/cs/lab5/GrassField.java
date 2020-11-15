package agh.cs.lab5;

import java.util.*;

import static java.lang.StrictMath.round;
import static java.lang.StrictMath.sqrt;

public class GrassField extends AbstractWorldMap {
    private int n=0;
    //List<Grass> Grasses = new ArrayList<>();
    Map<Vector2d,Grass> grasses = new HashMap<>();
    Random generator = new Random();
    public GrassField(int n,int width,int height){
        super(width,height);
        this.n=n;
        for(int i=0;i<this.n;i++){
            Grass g = new Grass(new Vector2d(generator.nextInt((int) sqrt(this.n*10)),generator.nextInt((int)sqrt(this.n*10))),this);
            if (this.isOccupied(g.getPosition())){
                --i;
            }
            else {
                //Grasses.add(g);
                grasses.put(g.getPosition(),g);
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(super.canMoveTo(position)){
            return true;
        }
        else {
            if(animals.get(position)==null){
                grasses.remove(position);
                for(int i=0;i<1;i++){
                    Grass g = new Grass(new Vector2d(generator.nextInt((int) sqrt(this.n*10)),generator.nextInt((int)sqrt(this.n*10))),this);
                    if (this.isOccupied(g.getPosition())){
                        --i;
                    }
                    else {
                        //Grasses.add(g);
                        grasses.put(g.getPosition(),g);
                    }
                }
                //grasses.put()
                return true;
            }
            else{
                return false;
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)){
            return true;
        }
        else if(grasses.get(position)==null){
            return false;
        }
        else {
            return true;
        }
        /*else{
            for (Grass grass : Grasses) {
                if (position.equals(grass.getPosition())) {
                    return true;
                }
            }
            return false;
        }*/
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(super.objectAt(position)==null){
            return grasses.get(position);
        }
        else {
            return super.objectAt(position);
        }
    }
}

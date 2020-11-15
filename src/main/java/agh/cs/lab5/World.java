package agh.cs.lab5;

import java.awt.EventQueue;

public class World {
    public static void main(String[] args) {
        try{
       IWorldMap map = new GrassField(10, 10,5);
       /* map.place(new Animal(map));
        //Grass g = new Grass(new Vector2d(2,2));
        System.out.println(map.toString());*/
        String x="f b r l f f r r f f f f f f f f";
        String[] a=x.split(" ");
        MoveDirection[] directions = new OptionParser().parse(a);
       // IWorldMap map = new RectangularMap(10, 5);
        //Animal dog =new Animal();
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        //map.place(new Animal(map,new Vector2d(2,2)));
        System.out.println(map.toString());
        //dog.move(MoveDirection.FORWARD);
        map.run(directions);
        System.out.println(map.toString());
        }catch(IllegalArgumentException e){
            System.out.println(e.toString());
        }

    }
}

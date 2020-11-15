package agh.cs.lab5;

import junit.framework.TestCase;
import org.junit.Assert;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GrassFieldTest extends TestCase {
    int width=10;
    int height=5;
    IWorldMap map1 = new GrassField(10, width, height);
    public void testCanMoveTo() {
        map1.place(new Animal(map1));
        map1.place(new Animal(map1,new Vector2d(3,4)));
        Assert.assertFalse(map1.canMoveTo(new Vector2d(3,4)));
        Assert.assertFalse(map1.canMoveTo(new Vector2d(2,2)));
        Assert.assertTrue(map1.canMoveTo(new Vector2d(2,3)));
        Vector2d[] v = tufts();
        for(int i=0;i<v.length;i++) {
            Assert.assertTrue(map1.canMoveTo(v[i]));
        }
    }
    Vector2d[]tufts() { //funkcja sprawdza możliwość ruchu/postawienia zwierzęcia na polu na którym rośnie trawa
        List<Vector2d> v = new ArrayList<>();
        for (int i = 0; i < map1.getWidth(); i++) {
            for (int j = 0; j < map1.getHeight(); j++) {
                if (map1.isOccupied(new Vector2d(i, j))&&((i!=2)||(j!=2))&&((i!=3)||(j!=4))){
                       v.add(new Vector2d(i, j));  //zwraca położenie znalezionej kępki trawy
                }
            }
        }
        Vector2d[] Vectortable = new Vector2d[v.size()];
        for(int i=0;i<v.size();i++){
            Vectortable[i]=v.get(i);
        }
        return Vectortable;
    }
    public void testPlace() {
        Assert.assertTrue(map1.place(new Animal(map1)));
        Assert.assertTrue(map1.place(new Animal(map1,new Vector2d(3,4))));
        Vector2d[] v = tufts();
        for(int i=0;i<v.length;i++) {
            Assert.assertTrue(map1.place(new Animal(map1,v[i])));
        }

    }

    public void testTestRun() {
        String x="f b r l f f r r f f f f f f f f";
        String[] a=x.split(" ");
        MoveDirection[] directions = new OptionParser().parse(a);
        Animal dog = new Animal(map1);
        Animal cat = new Animal(map1,new Vector2d(3,4));
        map1.place(dog);
        map1.place(cat);
        map1.run(directions);
        String x1="f b r l r r f f f f f f f f";
        String[] a1=x1.split(" ");
        MoveDirection[] directions1 = new OptionParser().parse(a1);
        Animal dog1 = new Animal();
        Animal dog2 = new Animal();
        Animal cat1 = new Animal();
        Animal cat2 = new Animal();
        MoveDirection[] setcat = {MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.FORWARD};
        for (int i=0;i< setcat.length;i++){
            cat1.move(setcat[i]);
            cat2.move(setcat[i]);
        }
        for (int i=0;i< a1.length;i++){
            dog1.move(directions1[i]);
            cat1.move(directions1[++i]);
        }
        for (int i=0;i< a.length;i++){
            dog2.move(directions[i]);
            cat2.move(directions[++i]);
        }
        /*Ruchy dog1 i cat1 są odzwierciedleniem ruchów dog i cat - tablica directions1 nie zawiera elementów f
        dla których dog i cat nie powinny wykonać ruchu z powodu zajętości miejsca docelowego*/
        Assert.assertEquals(dog1.toString(),dog.toString());
        Assert.assertEquals(dog1.getPosition(),dog.getPosition());
        Assert.assertEquals(cat1.toString(),cat.toString());
        Assert.assertEquals(cat1.getPosition(),cat.getPosition());
        /* Dog2 i cat2 poruszają sie zgodnie z tablicą directions, więc wykonają ruch nawet dla tych f dla których
        nie wykonają ich dog i cat. Więc finalnie będą mieć ten sam zwrot co obiekty główne, ale będą się znajdować na
        innych pozycjach*/
        Assert.assertEquals(dog2.toString(),dog.toString());
        Assert.assertNotEquals(dog2.getPosition(),dog.getPosition());
        Assert.assertEquals(cat2.toString(),cat.toString());
        Assert.assertNotEquals(cat2.getPosition(),cat.getPosition());
    }

    public void testIsOccupied() {
        Animal dog = new Animal(map1);
        map1.place(dog);
        Animal cat = new Animal(map1,new Vector2d(3,4));
        map1.place(cat);
        Vector2d[] v =tufts();
        for(int i=0;i< map1.getWidth();i++){
            for(int j=0;j< map1.getHeight();j++){
                if((new Vector2d(i,j).equals(dog.getPosition()))||(new Vector2d(i,j).equals(cat.getPosition()))){
                        Assert.assertTrue(map1.isOccupied(new Vector2d(i,j)));
                }
                else if(tuft(v,i,j)){
                    Assert.assertTrue(map1.isOccupied(new Vector2d(i,j)));
                }
                else{
                    Assert.assertFalse(map1.isOccupied(new Vector2d(i,j)));
                }
            }
        }
    }
    boolean tuft(Vector2d[] v,int i, int j) { //funkcja prawdę jeśli na danych współrzędnych znajduje się kępka trawy
        for(int k=0;k< v.length;k++){
            if(new Vector2d(i,j).equals(v[k])){
                return true;
            }

        }
        return false;
    }
    public void testObjectAt() {
        Animal dog = new Animal(map1);
        map1.place(dog);
        Animal cat = new Animal(map1,new Vector2d(3,4));
        map1.place(cat);
        Vector2d[] v =tufts();
        Assert.assertEquals(dog, map1.objectAt(new Vector2d(2,2)));
        Assert.assertNotEquals(new Animal(map1), map1.objectAt(new Vector2d(2,2)));
        for(int i=0;i< map1.getWidth();i++){
            for(int j=0;j< map1.getHeight();j++){
                if((new Vector2d(i,j).equals(dog.getPosition()))||(new Vector2d(i,j).equals(cat.getPosition()))){
                    Assert.assertEquals(dog, map1.objectAt(new Vector2d(2,2)));
                    Assert.assertNotEquals(new Animal(map1), map1.objectAt(new Vector2d(2,2)));
                    Assert.assertNotNull(map1.objectAt(new Vector2d(i,j)));
                }
                else if(tuft(v,i,j)){
                    Assert.assertEquals("*",map1.objectAt(new Vector2d(i,j)).toString());
                    Assert.assertNotNull(map1.objectAt(new Vector2d(i,j)));
                }
                else{
                    Assert.assertNull(map1.objectAt(new Vector2d(i,j)));
                }
            }
        }
    }

    public void testGetHeight() {
        Assert.assertEquals(height,map1.getHeight());
    }

    public void testGetWidth() {
        Assert.assertEquals(width,map1.getWidth());
    }

}
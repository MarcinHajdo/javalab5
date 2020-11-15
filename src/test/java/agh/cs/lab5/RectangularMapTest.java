package agh.cs.lab5;

import junit.framework.TestCase;
import org.junit.Assert;

public class RectangularMapTest extends TestCase {
    int width=10;
    int height=5;
    IWorldMap map1 = new RectangularMap(width, height);
    public void testCanMoveTo() {
        map1.place(new Animal(map1));
        map1.place(new Animal(map1,new Vector2d(3,4)));
        Assert.assertFalse(map1.canMoveTo(new Vector2d(3,4)));
        Assert.assertFalse(map1.canMoveTo(new Vector2d(2,2)));
        Assert.assertTrue(map1.canMoveTo(new Vector2d(2,3)));
    }

    public void testPlace() {
        Assert.assertTrue(map1.place(new Animal(map1)));
        Assert.assertTrue(map1.place(new Animal(map1,new Vector2d(3,4))));
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
        //Assert.assertEquals(dog1.toString(),dog.toString());
        Assert.assertEquals(dog1.getPosition(),dog.getPosition());
        //Assert.assertEquals(cat1.toString(),cat.toString());
        //Assert.assertEquals(cat1.getPosition(),cat.getPosition());
        /* Dog2 i cat2 poruszają sie zgodnie z tablicą directions, więc wykonają ruch nawet dla tych f dla których
        nie wykonają ich dog i cat. Więc finalnie będą mieć ten sam zwrot co obiekty główne, ale będą się znajdować na
        innych pozycjach*/
        //Assert.assertEquals(dog2.toString(),dog.toString());
        //Assert.assertNotEquals(dog2.getPosition(),dog.getPosition());
        //Assert.assertEquals(cat2.toString(),cat.toString());
        //Assert.assertNotEquals(cat2.getPosition(),cat.getPosition());
    }

    public void testIsOccupied() {
        map1.place(new Animal(map1));
        map1.place(new Animal(map1,new Vector2d(3,4)));
        Assert.assertTrue(map1.isOccupied(new Vector2d(2,2)));
        Assert.assertTrue(map1.isOccupied(new Vector2d(3,4)));
        Assert.assertFalse(map1.isOccupied(new Vector2d(2,3)));
    }

    public void testObjectAt() {
        Animal dog = new Animal(map1);
        map1.place(dog);
        map1.place(new Animal(map1,new Vector2d(3,4)));
        Assert.assertEquals(dog, map1.objectAt(new Vector2d(2,2)));
        Assert.assertNotEquals(new Animal(map1), map1.objectAt(new Vector2d(2,2)));
        Assert.assertNull(map1.objectAt(new Vector2d(2,3)));
        Assert.assertNotNull(map1.objectAt(new Vector2d(3,4)));
    }
    public void testGetHeight() {
        Assert.assertEquals(height,map1.getHeight());
    }

    public void testGetWidth() {
        Assert.assertEquals(width,map1.getWidth());
    }
}
package agh.cs.lab5;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private IWorldMap map = null;
    private int width = 5;
    private int height = 5;
    public Animal(){}
    public Animal(IWorldMap map){
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = border(initialPosition);
    }
    public MapDirection getOrientation() {
        return this.orientation;
    }
    public Vector2d getPosition() {
        return this.position;
    }
    private Vector2d border(Vector2d p){
        if (this.map != null){
            this.width = this.map.getWidth();
            this.height = this.map.getHeight();
        }
        if (p.getY()>=this.height)
        {
            p= p.subtract(new Vector2d(0,this.height));
        }
        if (p.getX()>=this.width)
        {
            p= p.subtract(new Vector2d(this.width,0));
        }
        if (p.getY()<0)
        {
            p= p.add(new Vector2d(0,this.height));
        }
        if (p.getX()<0)
        {
            p= p.add(new Vector2d(this.width,0));
        }
        return p;
    }
    public void move(MoveDirection direction){
        switch(direction) {
            case RIGHT:
                this.orientation=this.orientation.next();
                break;
            case LEFT:
                this.orientation=this.orientation.previous();
                break;
            case FORWARD:
                if(this.map!=null){
                    if (this.map.canMoveTo(border(this.position.add(this.orientation.toUnitVector())))){
                        this.position=border(this.position.add(this.orientation.toUnitVector()));
                    }
                }
                else {
                    this.position=border(this.position.add(this.orientation.toUnitVector()));
                }
                break;
            case BACKWARD:
                if(this.map!=null){
                    if (this.map.canMoveTo(border(this.position.subtract(this.orientation.toUnitVector())))){
                        this.position=border(this.position.subtract(this.orientation.toUnitVector()));
                    }
                }
                else {
                    this.position=border(this.position.subtract(this.orientation.toUnitVector()));
                }

                break;
        }

    }
    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case WEST -> "W";
            case SOUTH -> "S";
        };

    }
    public String toString1() {
        return "Animal{" +
                "orientation=" + orientation +
                ", position=" + position +
                '}';
    }
}

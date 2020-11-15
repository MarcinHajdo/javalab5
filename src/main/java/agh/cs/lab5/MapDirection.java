package agh.cs.lab5;

enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public String toString(){
        switch(this) {
            case NORTH: return "Polnoc";
            case SOUTH: return "Poludnie";
            case EAST:  return "Wschod";
            case WEST:  return "Zachod";
            default:    return "Error";
        }
    }
    public MapDirection next(){
        switch(this) {
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case EAST:  return SOUTH;
            case WEST:  return NORTH;
            default:    return null;
        }
    }
    public MapDirection previous(){
        switch(this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST:  return SOUTH;
            case EAST:  return NORTH;
            default:    return null;
        }

    }
    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case EAST: return new Vector2d(1,0);
        }
        return new Vector2d(0,0);
    }
    public String toUnitedVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1).toString();
            case SOUTH: return new Vector2d(0,-1).toString();
            case WEST: return new Vector2d(-1,0).toString();
            case EAST: return new Vector2d(1,0).toString();
        }
        return new Vector2d(0,0).toString();
    }
}

package anadis.snakegame.domain;

/**
 * Class describes a unit pixel on the game grid.
 * 
 */

public class Block {

    private int x;
    private int y;

    /**
     * Init a unit pixel on the game grid with X and Y coordinates
     * 
     * @param x coordinate for X
     * @param y coordinate for Y
     */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return X coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return Y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @param x new X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y new Y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves the unit pixel by one unit in the given direction by resetting the
     * coordinates
     * 
     * @param direction the direction to move
     */
    public void setDirection(Direction direction) {
        switch (direction) {
            case UP:
                this.y--;
                break;
            case RIGHT:
                this.x++;
                break;
            case DOWN:
                this.y++;
                break;
            case LEFT:
                this.x--;
                break;
        }
    }

    /**
     * Checks if two pixel units have the same coordinates on the game plane
     * 
     * @param Block the Block object to be compared to
     * @return true if the coordinates are equal, otherwise false
     */
    public boolean equals(Block other) {
        return (this.x == other.getX() && this.y == other.getY());
    }
}

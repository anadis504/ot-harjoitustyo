/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anadis.snakegame.domain;

/**
 *
 * @author anadis
 */
public class Block {

    private int x;
    private int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

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

    public boolean equals(Block other) {
        return (this.x == other.getX() && this.y == other.getY());
    }
}

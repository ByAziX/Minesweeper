package fr.yncrea.cin3.minesweeper.domain;


import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "\"minefield\"")
public class Minefield {
    @Id
    @GeneratedValue
    private UUID id;

    private long width;
    private long height;
    private long count;
    private long state;
    private long count_discover;

    @Lob
    private int[][] minefield;

    public Minefield() {
    }

    public Minefield(long width, long height) {
        this.width = width;
        this.height = height;
        this.state = 1;
        this.minefield = new int[(int) width][(int) height];
        this.count_discover = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.minefield[i][j] = 10;
            }
        }
    }

    public Minefield(long width, long height, long count) {
        this.width = width;
        this.height = height;
        this.count = count;
        this.state = 1;
        this.minefield = new int[(int) width][(int) height];
        this.count_discover = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.minefield[i][j] = 10;
            }
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int[][] getMinefield() {
        return minefield;
    }

    public void setMinefield(int[][] minefield) {
        this.minefield = minefield;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getCount_discover() {
        return count_discover;
    }

    public void setCount_discover(long count_discover) {
        this.count_discover = count_discover;
    }
}

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

    public Minefield() {}

    public Minefield(long width, long height) {
        this.width = width;
        this.height = height;
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
}

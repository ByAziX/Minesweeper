package fr.yncrea.cin3.minesweeper.service;

import fr.yncrea.cin3.minesweeper.domain.Minefield;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@Service
public class MinesweeperEngineService {
    /**
     * Create a new minefield
     *
     * @param width
     * @param height
     * @param count
     * @return
     */
    public Minefield create(long width, long height, long count) {

        return new Minefield(width, height);
    }

    /**
     * Discover a new cell, and update the game (win/loss detection, ...)
     *
     * @param minefield
     * @param x
     * @param y
     */
    public void play(Minefield minefield, long x, long y) {
        addMine(minefield,x,y);
    }

    /**
     * Add a mine on the field
     * @param minefield
     * @param x
     * @param y
     */
    public void addMine(Minefield minefield, long x, long y) {
       /* long random_x = (long) (Math.random() * x);
        long random_y = (long) (Math.random() * y);
        int[][] mine = new int[0][];
        for (int i = 0; i < minefield.getCount(); i++) {
            mine[(int) x][(int) y] = 1;
            System.out.println(mine[(int) x][(int) y]);
        }
        minefield.setMinefield(mine);*/
    }

    /**
     * Returns the mine count near a cell
     *
     * @param minefield
     * @param x
     * @param y
     * @return
     */
    public long getMineCountNear(Minefield minefield, long x, long y) {
        return 0;
    }

    /**
     * Returns true is the cell contains a mine
     *
     * @param minefield
     * @param x
     * @param y
     * @return
     */
    public boolean hasMine(Minefield minefield, long x, long y) {
        return false;
    }

    /**
     * Returns true is the cell is already discovered
     *
     * @param minefield
     * @param x
     * @param y
     * @return
     */
    public boolean isDiscovered(Minefield minefield, long x, long y) {
        return false;
    }
}

package fr.yncrea.cin3.minesweeper.service;

import fr.yncrea.cin3.minesweeper.domain.Minefield;
import fr.yncrea.cin3.minesweeper.exception.MinesweeperException;
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
       Minefield m = new Minefield(width, height, count);
        while (m.getCount() > count) {
            try {
                int random_x = (int) (Math.random() * m.getWidth());
                int random_y = (int) (Math.random() * m.getHeight());
                addMine(m, random_x, random_y);
                count++;
            } catch (MinesweeperException e) {
                System.out.println(e.getMessage());
            }
        }
        for (int row = 0; row < m.getWidth(); row++) {
            System.out.println();
            for (int col = 0; col < m.getHeight(); col++) {

                System.out.print(m.getMinefield()[row][col]);
            }
        }
        return m;
    }

    /**
     * Discover a new cell, and update the game (win/loss detection, ...)
     *
     * @param minefield
     * @param x
     * @param y
     */
    public void play(Minefield minefield, long x, long y) {

    }

    /**
     * Add a mine on the field
     *
     * @param minefield
     * @param x
     * @param y
     */
    public void addMine(Minefield minefield, long x, long y) {
        int[][] minefield_tab = minefield.getMinefield();


            if (minefield_tab[(int) x][(int) y] != 1) {
                minefield_tab[(int) x][(int) y] = 1;
        }else{
                throw new MinesweeperException("il y déjà une mine");
            }

        minefield.setMinefield(minefield_tab);
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

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
        int tour = 0;
        while (m.getCount() > tour) {
            try {
                int random_x = (int) (Math.random() * m.getWidth());
                int random_y = (int) (Math.random() * m.getHeight());
                addMine(m, random_x, random_y);
                tour++;
            } catch (MinesweeperException e) {
                System.out.println(e.getMessage());
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
        } else {
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
        int count_bomb;
        int case_tab_x_min = 0;
        int case_tab_y_min = 0;
        int case_tab_x_max = 0;
        int case_tab_y_max = 0;

        if (minefield.getMinefield()[(int) x][(int) y] != 1) {
            count_bomb = 0;

            case_tab_x_min = x > 0 ? (int) (x - 1) : (int) x;
            case_tab_y_min = y > 0 ? (int) (y - 1) : (int) y;
            case_tab_x_max = x < minefield.getWidth()-1 ? (int) (x+1) : (int) (x);
            case_tab_y_max = y < minefield.getHeight()-1 ? (int) (y+1) : (int) (y);

            for (int row_case = case_tab_x_min; row_case <= case_tab_x_max; row_case++) {

                System.out.print(row_case);
                for (int col_case = case_tab_y_min; col_case <= case_tab_y_max; col_case++) {
                    if (minefield.getMinefield()[row_case][col_case] == 1) {
                        System.out.println(col_case);
                        count_bomb++;
                    }
                }

            }
            return count_bomb;
        }
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
        return minefield.getMinefield()[(int) x][(int) y] == 1;
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

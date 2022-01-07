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
        if (count < 0 || count > height * width) {
            throw new MinesweeperException("Nombre de bomb invalid");
        }
        if (width < 2 || width > 20 || height > 20 || height < 2) {
            throw new MinesweeperException("minefield invalid size");
        }
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
        int[][] minefield_tab = minefield.getMinefield();


        if ((!(x < 0 || x > minefield.getWidth() - 1 || y < 0 || y > minefield.getHeight() - 1))) {
            if (!isDiscovered(minefield, x, y)) {
                if (minefield.getState() == 1) {
                    if (minefield.getMinefield()[(int) y][(int) x] == 10) {
                        try {

                            fool_fill(minefield, x, y);

                        } catch (MinesweeperException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (hasMine(minefield, x, y)) {
                        minefield.setState(0);
                        for (int row = 0; row < minefield.getWidth()-1; row++) {
                            System.out.println();
                            for (int col = 0; col < minefield.getHeight()-1; col++) {
                                if (minefield_tab[row][col] == 9) {
                                    minefield_tab[row][col] = -1;
                                    minefield.setMinefield(minefield_tab);
                                }
                            }
                        }

                    }
                } else {
                    throw new MinesweeperException("Game fini vous ne pouvez pas jouer");
                }
                minefield.setMinefield(minefield_tab);

            } else {
                throw new MinesweeperException("Vous avez déjà joué ici");
            }

        } else {
            throw new MinesweeperException("En dehors du tableau");
        }
    }


    public void fool_fill(Minefield minefield, long x, long y) {
        int[][] minefield_tab = minefield.getMinefield();
        if ((!(x < 0 || x > minefield.getWidth() - 1 || y < 0 || y > minefield.getHeight() - 1))) {

            if (minefield.getMinefield()[(int) y][(int) x] == 10) {

                minefield.getMinefield()[(int) y][(int) x] = (int) getMineCountNear(minefield, x, y);
                minefield.setCount_discover(minefield.getCount_discover() + 1);
                if (minefield.getCount_discover() != (minefield.getWidth() * minefield.getHeight() - minefield.getCount())) {
                    if (minefield.getMinefield()[(int) y][(int) x] == 0) {
                        fool_fill(minefield, x - 1, y);
                        fool_fill(minefield, x, y + 1);
                        fool_fill(minefield, x + 1, y);
                        fool_fill(minefield, x, y - 1);
                        fool_fill(minefield, x + 1, y + 1);
                        fool_fill(minefield, x - 1, y - 1);
                    }
                } else {
                    minefield.setState(2);
                    for (int row = 0; row < minefield.getWidth()-1; row++) {
                        System.out.println();
                        for (int col = 0; col < minefield.getHeight()-1; col++) {
                            if (minefield_tab[row][col] == 9) {
                                minefield_tab[row][col] = -1;
                                minefield.setMinefield(minefield_tab);
                            }
                        }
                    }
                }
            }
        }
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
        if (x < 0 || x > minefield.getWidth() - 1 || y > minefield.getHeight() - 1 || y < 0) {
            throw new MinesweeperException("mine out of bound");
        }
        if (minefield_tab[(int) y][(int) x] != 9) {
            minefield_tab[(int) y][(int) x] = 9;
        } else {
            throw new MinesweeperException("il y déjà une mine x=" + x + " y=" + y + "");
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
        int case_tab_x_min, case_tab_y_min, case_tab_x_max, case_tab_y_max = 0;

        if (minefield.getMinefield()[(int) y][(int) x] != 9) {
            count_bomb = 0;

            case_tab_x_min = x > 0 ? (int) (x - 1) : (int) x;
            case_tab_y_min = y > 0 ? (int) (y - 1) : (int) y;
            case_tab_x_max = x < minefield.getWidth() - 1 ? (int) (x + 1) : (int) (x);
            case_tab_y_max = y < minefield.getHeight() - 1 ? (int) (y + 1) : (int) (y);

            for (int row_case = case_tab_x_min; row_case <= case_tab_x_max; row_case++) {
                for (int col_case = case_tab_y_min; col_case <= case_tab_y_max; col_case++) {
                    if (minefield.getMinefield()[col_case][row_case] == 9) {
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
        return minefield.getMinefield()[(int) y][(int) x] == 9;
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

        if (minefield.getMinefield()[(int) y][(int) x] == 10 || hasMine(minefield, x, y)) {
            return false;
        } else {
            return true;
        }

    }
}

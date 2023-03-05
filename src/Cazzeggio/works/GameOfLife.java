package Cazzeggio.works;

import java.util.Stack;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameOfLife extends CellularAutomaton {

    int board[][];
    int dim;
    Stack<Integer> stackCells;
    HashMap<Integer, Integer> hmapCells;

    public GameOfLife(int d, Stack<Integer> s){
        board = new int[d][d];
        dim = d;
        stackCells = s;
        hmapCells = new HashMap<>();
    }

    public boolean rules(int num){
        return num == 3 || num == 12 || num == 13;
    }

    private void birth() {
        Iterator<Map.Entry<Integer,Integer>> it=hmapCells.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer,Integer> pair = it.next();
            int key = pair.getKey();

            if(rules(pair.getValue())){
                stackCells.add(key);
            }

            board[key/dim][key%dim] = 0;
            it.remove();
        }
    }

    private void insertAlive() {
        while(!stackCells.isEmpty()) {
            int cell = stackCells.pop();

            int x = cell / dim;
            int y = cell % dim;
            int startX = (x <= 0) ? 0 : x - 1;
            int startY = (y <= 0) ? 0 : y - 1;
            int endX = (x >= dim - 1) ? x + 1 : x + 2;
            int endY = (y >= dim - 1) ? y + 1 : y + 2;

            for(int i = startX; i < endX; ++i) {
                for(int j = startY; j < endY; ++j) {
                    hmapCells.put(i * dim + j, ++board[i][j]);
                }
            }
            hmapCells.put(cell, board[x][y] += 9);
        }
    }

    private String printBoard() {
        StringBuilder s = new StringBuilder();

        for(int elements[] : board) {
            for(int element : elements) {
                if(element >= 10){
                    s.append("* ");
                }
                else {
                    s.append("  ");
                }
            }
            s.append("\n");
        }

        return s.toString();
    }

    public String lifeCycle() {
        birth();
        insertAlive();
        return printBoard();
    }
}
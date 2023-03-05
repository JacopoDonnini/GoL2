package Cazzeggio.MyGameOfLife;

import java.util.*;

public class GameGrid {
    private final int size;
    private List<List<GameCell>> grid;

    public GameGrid(int size) {
        this.size = size;
        grid = new ArrayList<List<GameCell>>();
        for (int i = 0; i < size; i++) {
            grid.add(new ArrayList<>());
            for(int j = 0; j < size; j++){
                grid.get(i).add(new GameCell(Math.random()<0.5,i,j));
            }
        }
    }

    public List<List<GameCell>> getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public GameCell get(int x, int y){
        return grid.get(x).get(y);
    }

    public GameCell getUp(GameCell curr) {
        int x = curr.getPositionX();
        int y = curr.getPositionY();
        if (x == 0) return grid.get(size-1).get(y);
        else return grid.get(x-1).get(y);
    }

    public GameCell getDown(GameCell curr) {
        int x = curr.getPositionX();
        int y = curr.getPositionY();
        if (x == size-1) return grid.get(0).get(y);
        else return grid.get(x+1).get(y);
    }

    public GameCell getSx(GameCell curr) {
        int x = curr.getPositionX();
        int y = curr.getPositionY();
        if (y == 0) return grid.get(x).get(size-1);
        else return grid.get(x).get(y-1);
    }

    public GameCell getDx(GameCell curr) {
        int x = curr.getPositionX();
        int y = curr.getPositionY();
        if (y == size-1) return grid.get(x).get(0);
        else return grid.get(x).get(y+1);
    }

    public int countAliveNeighbours(GameCell curr){
        int res=0;
        if (getUp(getSx(curr)).isAlive())    res++;
        if (getUp(curr).isAlive())           res++;
        if (getUp(getDx(curr)).isAlive())    res++;
        if (getDx(curr).isAlive())           res++;
        if (getDown(getDx(curr)).isAlive())  res++;
        if (getDown(curr).isAlive())         res++;
        if (getDown(getSx(curr)).isAlive())  res++;
        if (getSx(curr).isAlive())           res++;
        return res;
    }

    public void nextGeneration(){
        GameCell tmp;
        List<List<GameCell>> newgen = new ArrayList<>();
        for(int i=0;i<size;i++){
            newgen.add(new ArrayList<>());
            for(int j=0;j<size;j++){
                tmp=grid.get(i).get(j);
                if(!tmp.isAlive()){
                    if(countAliveNeighbours(tmp)==3){
                        //nascita
                        newgen.get(i).add(new GameCell(true,i,j));
                    }else{
                        newgen.get(i).add(new GameCell(false,i,j));
                    }
                }else{
                    if(countAliveNeighbours(tmp)<=1||countAliveNeighbours(tmp)>=4){
                        //morte
                        newgen.get(i).add(new GameCell(false,i,j));
                    }else{
                        //sopravvivenza
                        newgen.get(i).add(new GameCell(true,i,j));
                    }
                }
            }
        }
        grid=newgen;
    }

    public ArrayList<Coordinates> getAlivesIndex(){
        ArrayList<Coordinates> res = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid.get(i).get(j).isAlive()) {
                    res.add(new Coordinates(i,j));
                }
            }
        }
        return res;
    }

    public void emptyGrid(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid.get(i).get(j).setAlive(false);
            }
        }
    }

    public void viewGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid.get(i).get(j).isAlive()) System.out.print(" ■");
                else System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

    public void randomize(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.get(i).get(j).setAlive(Math.random()<0.5);
            }
        }
    }
    public void createPixel(int x, int y){
        grid.get(x).get(y).setAlive(true);
    }
    public void createBrush(int x, int y){
        grid.get(x).get(y).setAlive(true);
        if(x<size)  grid.get(x+1).get(y).setAlive(true);
        if(x>0)     grid.get(x-1).get(y).setAlive(true);
        if(y<size)  grid.get(x).get(y+1).setAlive(true);
        if(y>0)     grid.get(x).get(y-1).setAlive(true);

    }

    public void createBigBrush(int x, int y){
        //grid.get(x).get(y).setAlive(true);
        if(x<size)  grid.get(x+1).get(y).setAlive(true);
        if(x<size-1)  grid.get(x+2).get(y).setAlive(true);
        if(x>0)     grid.get(x-1).get(y).setAlive(true);
        if(x>1)     grid.get(x-2).get(y).setAlive(true);
        if(y<size)  grid.get(x).get(y+1).setAlive(true);
        if(y<size-1)  grid.get(x).get(y+2).setAlive(true);
        if(y>0)     grid.get(x).get(y-1).setAlive(true);
        if(y>1)     grid.get(x).get(y-2).setAlive(true);

    }

    public void createGliderGun(int x, int y){
        grid.get(x).get(y+4).setAlive(true);
        grid.get(x).get(y+5).setAlive(true);
        grid.get(x+1).get(y+4).setAlive(true);
        grid.get(x+1).get(y+5).setAlive(true);

        grid.get(x+10).get(y+4).setAlive(true);
        grid.get(x+10).get(y+5).setAlive(true);
        grid.get(x+10).get(y+6).setAlive(true);

        grid.get(x+11).get(y+3).setAlive(true);
        grid.get(x+11).get(y+7).setAlive(true);

        grid.get(x+12).get(y+2).setAlive(true);
        grid.get(x+12).get(y+8).setAlive(true);

        grid.get(x+13).get(y+2).setAlive(true);
        grid.get(x+13).get(y+8).setAlive(true);

        grid.get(x+14).get(y+5).setAlive(true);

        grid.get(x+15).get(y+3).setAlive(true);
        grid.get(x+15).get(y+7).setAlive(true);

        grid.get(x+16).get(y+4).setAlive(true);
        grid.get(x+16).get(y+5).setAlive(true);
        grid.get(x+16).get(y+6).setAlive(true);

        grid.get(x+17).get(y+5).setAlive(true);

        grid.get(x+20).get(y+2).setAlive(true);
        grid.get(x+20).get(y+3).setAlive(true);
        grid.get(x+20).get(y+4).setAlive(true);

        grid.get(x+21).get(y+2).setAlive(true);
        grid.get(x+21).get(y+3).setAlive(true);
        grid.get(x+21).get(y+4).setAlive(true);

        grid.get(x+22).get(y+1).setAlive(true);
        grid.get(x+22).get(y+5).setAlive(true);

        grid.get(x+24).get(y).setAlive(true);
        grid.get(x+24).get(y+1).setAlive(true);
        grid.get(x+24).get(y+5).setAlive(true);
        grid.get(x+24).get(y+6).setAlive(true);

        grid.get(x+34).get(y+2).setAlive(true);
        grid.get(x+34).get(y+3).setAlive(true);

        grid.get(x+35).get(y+2).setAlive(true);
        grid.get(x+35).get(y+3).setAlive(true);
    }

    public void createGlider(int x, int y){
        grid.get(x+1).get(y).setAlive(true);
        grid.get(x+2).get(y+1).setAlive(true);
        grid.get(x).get(y+2).setAlive(true);
        grid.get(x+1).get(y+2).setAlive(true);
        grid.get(x+2).get(y+2).setAlive(true);
    }
}

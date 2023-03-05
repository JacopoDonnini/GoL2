package Cazzeggio.works;

import java.util.Stack;

public class Simulation {
    public static void main(String args[]) throws InterruptedException{
        int dim = 70;
        Stack<Integer> init = new Stack<>();

        //all vals pushed to init is of the form: xPos * dim + yPos
        init.push(351);
        init.push(352);
        init.push(421);
        init.push(422);

        init.push(245);
        init.push(246);
        init.push(315);
        init.push(316);

        init.push(361);
        init.push(431);
        init.push(501);
        init.push(292);
        init.push(572);
        init.push(223);
        init.push(643);
        init.push(224);
        init.push(644);
        init.push(435);
        init.push(296);
        init.push(576);
        init.push(367);
        init.push(437);
        init.push(507);
        init.push(438);

        init.push(231);
        init.push(301);
        init.push(371);
        init.push(232);
        init.push(302);
        init.push(372);
        init.push(163);
        init.push(443);
        init.push(165);
        init.push(445);
        init.push(95);
        init.push(515);

        GameOfLife gOL = new GameOfLife(dim, init);

        while(true) {
            System.out.print(gOL.lifeCycle());
            Thread.sleep(100);
            System.out.print("\033[H\033[2J");
        }
    }
}
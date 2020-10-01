import java.util.Random;

public class Sim {

    private int width;
    private int height;
    private int[][] map;

    public Sim(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new int[width][height];
    }

    public void printMap() {

        for (int y = 0; y < this.height ; y++) {
            String line = "";
            for (int x = 0; x < this.width; x++) {
                if (this.map[x][y] == 1) {
                    line+= "^";
                } else
                line += "*";
            }
            System.out.println(line);
        }
        System.out.println("\n");
    }

    private void setAlive(int x, int y) {
        this.map[x][y] = 1;
    }

    private void setDead(int x, int y) {
        this.map[x][y] = 0;
    }

    private int getState(int x, int y) {

        if (x >= width || x < 0) return 0;
        if (y < 0 || y >= height) return 0;

        return this.map[x][y];
    }

    private void randomChooseOfLiveCells() {
        Random random = new Random();

        for (int i = 0; i < random.nextInt(10) + 10; i++) {
            setAlive(random.nextInt(width), random.nextInt(height));
        }
    }




    public int countAliveNeighbours(int x, int y) {
        int counter = 0;

        counter += getState((x - 1),(y+1));
        counter += getState(x,(y+1));
        counter += getState(x + 1,(y+1));

        counter += getState(x - 1,(y));
        counter += getState(x + 1,(y));

        counter += getState(x - 1,(y - 1));
        counter += getState(x,(y - 1));
        counter += getState(x + 1,y - 1);

        return counter;
    }
    public void stepForward() {
        int newStepMap[][] = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int aliveCellsAround = countAliveNeighbours(x, y);

                if (getState(x, y) == 1) {
                    if (aliveCellsAround < 2 || aliveCellsAround > 3) {
                        newStepMap[x][y] = 0;
                    } else {
                        newStepMap[x][y] = 1;
                    }


                } else {
                    if (aliveCellsAround == 3) {
                        newStepMap[x][y] = 1;
                    }
                }
            }
        }





        map = newStepMap;
    }

    public void checkBoundaries() {

    }

    public static void main(String[] args) {
        Sim sim = new Sim(20, 7);

        sim.printMap();
        sim.setAlive(3, 6);
        sim.setAlive(3, 5);
        sim.setAlive(3, 4);
//        sim.randomChooseOfLiveCells();


        for (int i = 0; i < 5; i++) {
            sim.printMap();
            sim.stepForward();
        }
    }


}

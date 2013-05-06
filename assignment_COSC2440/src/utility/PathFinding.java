package utility;

import model.pokemon.TypeUtils;
import view.map.MapUtil;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: CHINHNHAN
 * Date: 4/28/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class PathFinding {

    public static final int FIRE = 0;
    public static final int WATER = 1;
    public static final int GRASS = 2;
    public static final int ELECTRICITY = 3;
    public static final int ROCK = 4;
    public static final int BUG = 5;
    public static final int FLYING = 6;
    public static final int NORMAL = 7;

    public static void main(String[] args) {
        PathFinding pathFinding = new PathFinding();

        Coordinate coordinate = new Coordinate(6, 3);

        int[][] src = {{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 34},
                        {2, 2, 12, 28, 4, 12, 4, 4, 28, 2, 2},
                        {2, 11, 1, 1, 1, 20, 4, 2, 2, 28, 2},
                        {2, 23, 1, 1, 13, 2, 2, 2, 2, 4, 2},
                        {2, 3, 3, 11, 2, 2, 2, 12, 4, 4, 2},
                        {2, 3, 2, 2, 2, 2, 13, 1, 1, 28, 2},
                        {2, 23, 2, 2, 3, 19, 1, 1, 1, 12, 2},
                        {2, 2, 23, 3, 3, 11, 3, 23, 11, 2, 2},
                        {30, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};

        ArrayList<Coordinate> result = pathFinding.findPath(coordinate, src, 3, FLYING);

        Set<Coordinate> finalResult = new HashSet<Coordinate>(result);


        pathFinding.printResult(new ArrayList<Coordinate>(finalResult), src);


    }

    public void printResult(ArrayList<Coordinate> result, int[][] src) {
        for (int i = 0; i < result.size(); i++) {
            int x = result.get(i).i;
            int y = result.get(i).j;
            src[x][y] = 0;
        }
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                System.out.print(src[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    public static ArrayList<Coordinate> findPath(Coordinate cor, int[][] src, int numSteps, int pokemonType) {

        ArrayList<Integer> obstacle = null;
        if (pokemonType == WATER) {
            Integer[] tempObstacle = {11, 12, 13, 19, 20, 23, 28, 30, 34};
            obstacle = new ArrayList<Integer>(Arrays.asList(tempObstacle));
        } else if (pokemonType == FLYING) {
            Integer[] tempObstacle = {};
            obstacle = new ArrayList<Integer>(Arrays.asList(tempObstacle));
        } else {
            Integer[] tempObstacle = {1, 11, 12, 13, 19, 20, 23, 28, 30, 34};
            obstacle = new ArrayList<Integer>(Arrays.asList(tempObstacle));
        }

        if (numSteps == 1) {
            return new ArrayList<Coordinate>(findOneStep(cor, src, obstacle));
        } else if (numSteps == 2) {
            return new ArrayList<Coordinate>(findMoreSteps(src, obstacle, findOneStep(cor, src, obstacle)));
        } else {
            return new ArrayList<Coordinate>(findMoreSteps(src, obstacle, findMoreSteps(src, obstacle, findOneStep(cor, src, obstacle))));

        }
    }

    public static Set<Coordinate> findMoreSteps(int[][] src, ArrayList<Integer> obstacle, Set<Coordinate> resultOneStep) {
        Set<Coordinate> finalResult = new HashSet<Coordinate>();

        finalResult.addAll(resultOneStep);

        Iterator iterator = resultOneStep.iterator();
        while (iterator.hasNext()) {
            Coordinate tempCoordinate = (Coordinate) iterator.next();
//            System.out.println("temp Cor " + tempCoordinate.i + "\t" + tempCoordinate.j);
//            System.out.println(findOneStep(tempCoordinate, src, obstacle).toString());
//            System.out.println("********************************");
            finalResult.addAll(findOneStep(tempCoordinate, src, obstacle));
        }
        return finalResult;
    }

    public static Set<Coordinate> findOneStep(Coordinate cor, int[][] src, ArrayList<Integer> obstacle) {
        int xMax = src.length - 1;
        int yMax = src[0].length - 1;

//        System.out.println("xMax "+xMax);
//        System.out.println("yMax "+yMax);

        Set<Coordinate> result = new HashSet<Coordinate>();
        int plusX = Math.min(cor.i + 1, xMax);
        int plusY = Math.min(cor.j + 1, yMax);

        int minusX = Math.max(cor.i - 1, 0);
        int minusY = Math.max(cor.j - 1, 0);

//        System.out.println(plusX);
//        System.out.println(plusY);
//
//        System.out.println(minusX);
//        System.out.println(minusY);

//        System.out.println("Plus i, cor j " + plusX + " , " + cor.j);
//        System.out.println("minusX, cor j " + minusX + " , " + cor.j);
//
//        System.out.println("cor i, plus j " + cor.i + " , " + plusY);
//        System.out.println("cor i, minus j " + cor.i + " , " + minusY);


        if (!checkObstacle(src[plusX][cor.j], obstacle)) {
            result.add(new Coordinate(plusX, cor.j));
        }
        if (!checkObstacle(src[minusX][cor.j], obstacle)) {
            result.add(new Coordinate(minusX, cor.j));
        }

        if (!checkObstacle(src[cor.i][plusY], obstacle)) {
            result.add(new Coordinate(cor.i, plusY));
        }
        if (!checkObstacle(src[cor.i][minusY], obstacle)) {
            result.add(new Coordinate(cor.i, minusY));
        }
        return result;
    }

    public static boolean checkObstacle(int num, ArrayList<Integer> obstacle) {
        for (int i = 0; i < obstacle.size(); i++) {
            if (num == obstacle.get(i)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Coordinate> findValidSteps(int curI, int curJ, int[][] mapArr, int pokeType) {
        Coordinate[] coords = getDefaultSteps(curI, curJ);
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

        for(Coordinate c : coords) {
            if(pokeType == TypeUtils.FLYING) {
                coordinates.add(c);
            } else if(pokeType == TypeUtils.WATER) {
                try {
                    if(mapArr[c.getI()][c.getJ()] <= 4) {
                        coordinates.add(c);
                    }
                } catch(Exception ex) {
                    continue;
                }
            } else {
                try {
                    if(mapArr[c.getI()][c.getJ()]<=4 && mapArr[c.getI()][c.getJ()]>1) {
                        coordinates.add(c);
                    }
                } catch(Exception ex) {
                    continue;
                }
            }
        }

        return coordinates;
    }

    private static Coordinate[] getDefaultSteps(int curI, int curJ) {
        return new Coordinate[] {
                new Coordinate(curI-3, curJ),
                new Coordinate(curI-2, curJ-1), new Coordinate(curI-2, curJ), new Coordinate(curI-2, curJ+1),
                new Coordinate(curI-1, curJ-2), new Coordinate(curI-1, curJ-1), new Coordinate(curI-1, curJ),
                    new Coordinate(curI-1, curJ+1), new Coordinate(curI-1, curJ+2),
                new Coordinate(curI, curJ-3), new Coordinate(curI, curJ-2), new Coordinate(curI, curJ-1),
                    new Coordinate(curI, curJ), new Coordinate(curI, curJ+1), new Coordinate(curI, curJ+2),
                    new Coordinate(curI, curJ+3),
                new Coordinate(curI+1, curJ-2), new Coordinate(curI+1, curJ-1), new Coordinate(curI+1, curJ),
                    new Coordinate(curI+1, curJ-1), new Coordinate(curI+1, curJ-2),
                new Coordinate(curI+2, curJ-1), new Coordinate(curI+2, curJ), new Coordinate(curI+2, curJ+1),
                new Coordinate(curI+3, curJ),
        };
    }

    public static class Coordinate implements Comparable<Coordinate> {
        private int i;
        private int j;
        private int x;
        private int y;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
            x = i * MapUtil.TILE_SIZE;
            y = j * MapUtil.TILE_SIZE;
        }

        @Override
        public String toString() {
            return "(" + i + "," + j + ")";
        }

        @Override
        public int compareTo(Coordinate o) {
            return o.toString().compareTo(this.toString());
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

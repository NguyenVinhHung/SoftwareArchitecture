package utility;

import view.map.MapUtil;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: s3342128
 * Date: 5/04/13
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveUtil {

    private static final int MAP_ARRS[][] = MapUtil.MAP_ARRS[0];

    public static final Move[] START_POSITIONS_T1 = {
            new Move(5, 0), new Move(5, 1), new Move(6, 2), new Move(7, 3), new Move(8, 3)
    };

    public static final Move[] START_POSITIONS_T2 = {
            new Move(0, 7), new Move(1, 7), new Move(2, 8), new Move(3, 9), new Move(3, 10)
    };

    public static Move[] getPossibleMove(int curI, int curJ) {
        ArrayList<Move> list = new ArrayList<Move>();

        for(int i=curI-3, jn=0; i<=curI; i++) {
            for(int j=curJ-jn; j<=curJ+jn; j++) {
                if(isValidTile(i, j)) {
                    list.add(new Move(i, j));
                }
            }
            jn++;
        }

        return (Move[])list.toArray();
    }

    private static boolean isValidTile(int i, int j) {
        return !(i<0 || j<0 || i>=MAP_ARRS.length || j>=MAP_ARRS[0].length || MAP_ARRS[i][j]<2 || MAP_ARRS[i][j]>4);
    }
}

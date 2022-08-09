package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author hu
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;
    //测试使用
    static Board a;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(side);
        for (int c = 0; c < board.size(); c += 1) {
//        处理一行的函数
            //从一个col的最上面的第二个tile开始循环
            int Size = board.size();
            boolean isMerge[][] ;
            isMerge = new boolean[Size][Size];
            for (int r = board.size()-2;r>=0;r=r-1) {
                Tile t = board.tile(c, r);

                int up = 0;

                if (t != null) {

                    for (int row = r+1;row < board.size();row++){
                        if(board.tile(c,row) == null){
                            //如果上面是空的向上移动一步
                            up = up+1;
                        } else {
                            //如果上面有值并且没有被合并过就判断是否相等
                            if(!isMerge[c][row]){
                        if(t.value()== board.tile(c,row).value()){
                            //上面一格不是空的就比较大小，相同就向上移动一步
                           up = up+1;
                            score += t.value()*2;
                        }}
                        }}}
//               最终决定向上移动几步
                if(up != 0){
                    //在原有的基础上向上移动几格，并记录该格已经被合并过了
                isMerge[c][up+r]=board.move(c,r+up,t);
                changed = true;

                }
            }

        }


        //每次计算结束后记得更新

        //结束我的代码
        checkGameOver();
        if (changed) {
            setChanged();
        }
        board.setViewingPerspective(Side.NORTH);
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        boolean existEmpty = false;
//        System.out.println(b.size());
//        System.out.println(b.tile(1,0));
       // 遍历整个board看看是否有0的方块
        for (int i = 0;i < b.size();i++)
        {
            for (int j = 0;j < b.size();j++){
               if(b.tile(i,j) == null) {
                    existEmpty = true ;
               }
            }

        }

        //测试

//        int[][] rawVals = new int[][] {
//                {13, 14, 15, 16},
//                {9, 10, 11, 12},
//                {5, 6, 7, 8},
//                {1, 2, 3, 4},
//        };
//
//        a = new Board(rawVals, 0);
//        System.out.println(a.tile(1,0));
        return existEmpty;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        boolean maxTileExists = false ;
        for (int i = 0;i < b.size();i++)
        {
            for (int j = 0;j < b.size();j++){
                if (b.tile(i,j) == null){

                }
                else if (b.tile(i,j).value() == MAX_PIECE) {
                    maxTileExists = true ;
                }

            }

        }

//        System.out.println(b.tile(0,3).value());
        return maxTileExists;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        boolean validMove = false;
        //针对有空位和值可以合并两种来判断
        if(emptySpaceExists(b)){
            //既然有空的tile存在就可以直接返回可以移动了，不用再做接下来的循环，也避免了将一个有值的方块与无值的对比，结果不能移动
            return true ;
        }
        // 将每个tile和上下左右的值进行对比，如果有相同的就修改
        for (int i = 0;i < b.size();i++)
        {
            for (int j = 0;j < b.size();j++){
                //先判断tile有没有上下左右的方块 再通过if来验证里面的值

                if(i<b.size()-1){
                    if(b.tile(i,j).value() == b.tile(i+1,j).value()){validMove = true;}
                }
                if(i>0){
                    if(b.tile(i,j).value() == b.tile(i-1,j).value()){validMove = true;}
                }
                if(j<b.size()-1){
                    if(b.tile(i,j).value() == b.tile(i,j+1).value()){validMove = true;}
                }
                if(j>0){
                    if(b.tile(i,j).value() == b.tile(i,j-1).value()){validMove = true;}
                }
            }

        }
        return validMove;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}

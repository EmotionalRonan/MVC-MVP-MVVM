package tk.iovr.mvctest.model;


public class Board {

    private Cell[][] cells = new Cell[3][3];

    private Player winner;
    private GameState gameState;
    private Player currentTurn;

    private  enum GameState{
        IN_PROGRESS,FINISHED
    }

    public Board (){
        restart();
    }

    public void restart() {
        clearCells();
        winner =null;
        currentTurn = Player.X;
        gameState = GameState.IN_PROGRESS;


    }



    public Player mark(int row,int col){
            Player playerThatMoved = null;

            if (isValid(row,col)){
                cells[row][col].setValue(currentTurn);
                playerThatMoved = currentTurn;
                if (isWinnerMoveByPlayer(currentTurn,row,col)){
                    gameState = GameState.FINISHED;
                    winner = currentTurn;
                }else {
                    flipCurrentTurn();
                }
            }
            return playerThatMoved;
    }

    private void flipCurrentTurn() {

        currentTurn = currentTurn == Player.X ? Player.O : Player.X;

    }

    private boolean isWinnerMoveByPlayer(Player currentTurn, int row, int col) {
        return (cells[row][0].getValue() == currentTurn         // 3-in-the-row
                && cells[row][1].getValue() == currentTurn
                && cells[row][2].getValue() == currentTurn
                || cells[0][row].getValue() == currentTurn      // 3-in-the-column
                && cells[1][row].getValue() == currentTurn
                && cells[2][row].getValue() == currentTurn
                || row == col            // 3-in-the-diagonal
                && cells[0][0].getValue() == currentTurn
                && cells[1][1].getValue() == currentTurn
                && cells[2][2].getValue() == currentTurn
                || row + col == 2    // 3-in-the-opposite-diagonal
                && cells[0][2].getValue() == currentTurn
                && cells[1][1].getValue() == currentTurn
                && cells[2][0].getValue() == currentTurn);
    }

    private boolean isValid(int row, int col) {
        
        if (gameState == GameState.FINISHED){
            return false;
        }else  if (isOutOfBound(row)||isOutOfBound(col)){
            return false;
        }else if (isCellValueAlreadySet(row,col)){
            return false;
        }else {
            return true;
        }

    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }

    private boolean isOutOfBound(int idx) {
        return idx < 0 |idx > 2;
    }

    public Player getWinner(){
        return winner;
    }

    private void clearCells() {
        for (int i =0;i<3;i++){
            for (int j=0 ;j<3;j++){
                cells[i][j]= new Cell();

            }
        }
    }

}

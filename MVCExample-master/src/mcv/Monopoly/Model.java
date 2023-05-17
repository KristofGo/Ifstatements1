package mcv.Monopoly;

import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 *
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

    // Messaging system for the MVC
    private final Messenger mvcMessaging;

    // Model's data variables
    private boolean whoseMove;
    private boolean gameOver;
    private Card[][] board;

    private void newGame() {
        // patern is: cost of property, cost of house, no house, 1 house, 2 house, 3 house, hotel
        Card MA = new Card(60, 50,2,4,10,30,90,160,250);
        board[10][9] = MA;
        Card BA = new Card(60, 50,3,8,20,60,180,320,450);
        board[10][7] = BA;
        Card OA = new Card(100,50,6,12,30,90,270,400,550);
        board[10][4] = OA;
        Card VA = new Card(100,50,6,12,30,90,270,400,550);
        board[10][2] = VA;
        Card CA = new Card(120,50,8,16,40,100,300,450,600);
        board[10][1] = CA;
        Card SCP = new Card(140,100,10,20,50,150,450,625,750);
        board[9][0] = SCP;
        Card SA = new Card(140,100,10,20,50,150,450,625,750);
        board[7][0] = SA;
        Card ViA = new Card(160,100,12,24,60,180,500,700,900);
        board[6][0] = ViA;
        this.mvcMessaging.notify("boardChange", this.board);
    }

    public Model(Messenger messages) {
        mvcMessaging = messages;
    }

    /**
     * Initialize the model here and subscribe to any required messages
     */
    public void init() {
        this.board = new Card[10][10];
        this.newGame();
        this.mvcMessaging.subscribe("playerMove", this);
        this.mvcMessaging.subscribe("newGame", this);

    }

    @Override
    public void messageHandler(String messageName, Object messagePayload) {
        // Display the message to the console for debugging
        if (messagePayload != null) {
            System.out.println("MSG: received by model: " + messageName + " | " + messagePayload.toString());
        } else {
            System.out.println("MSG: received by model: " + messageName + " | No data sent");
        }
        if (messageName.equals("newGame")) {
            newGame();
        }
        // playerMove message handler
        if (messageName.equals("playerMove")) {
            // Get the position string and convert to row and col
            System.out.print("test2");
            String position = (String) messagePayload;
            Integer row = Integer.valueOf(position.substring(0, 1));
            Integer col = Integer.valueOf(position.substring(1, 2));
            // If square is blank...
            if ("".equals(board[row][col])) {
                if (this.whoseMove) {
                    this.redMove(row, col);
                    
                } else { 
                    this.blueMove(row, col);
                }
                System.out.print("test3");
                this.mvcMessaging.notify("boardChange", this.board);
            }
System.out.print("test4");
            // newGame message handler
        } else if (messageName.equals("newGame")) {
            // Reset the app state
            
            this.newGame();
            // Send the boardChange message along with the new board 
            this.mvcMessaging.notify("boardChange", this.board);
        }
    }

    public void redMove(int row, int col) {
        System.out.print("test5");
        if (row > 2) { // up 
            int g =0;
            boolean f = false;
            for (int i = 1; row - i > -1; i++) {
                if ("blue".equals(board[row-i][col])) {
                    g++;
                } else if ("red".equals(board[row-i][col])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row-i][col]) || "".equals(board[row-i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col] = "red";
                }
            }
        }
        if (row < 5) { // down
            int g =0;
            boolean f = false;
            for (int i = 1; row + i < 8; i++) {
                if ("blue".equals(board[row+i][col])) {
                    g++;
                } else if ("red".equals(board[row+i][col])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row+i][col]) || "".equals(board[row+i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col] = "red";
                }
            }
        }
        if (col < 5) { //left
            int g =0;
            boolean f = false;
            for (int i = 1; col + i < 8; i++) {
                if ("blue".equals(board[row][col+i])) {
                    g++;
                } else if ("red".equals(board[row][col+i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row][col+i]) || "".equals(board[row][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col+j] = "red";
                }
            }
        }
        if (col > 2) { //right
            int g =0;
            boolean f = false;
            for (int i = 1; col - i > -1; i++) {
                if ("blue".equals(board[row][col-i])) {
                    g++;
                } else if ("red".equals(board[row][col-i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            } else if ("red".equals(board[row][col-i]) || "".equals(board[row][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col-j] = "red";
                }
            }
        }
        if (col > 2 && row > 2) { //top left
            int g =0;
            boolean f = false;
            for (int i = 1; col - i > -1 && row - i > -1 ; i++) {
                if ("blue".equals(board[row-i][col-i])) {
                    g++;
                } else if ("red".equals(board[row-i][col-i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row-i][col-i]) || "".equals(board[row-i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col-j] = "red";
                }
            }
        } 
        if (col < 5 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 1; col + i < 8 && row + i < 8 ; i++) {
                if ("blue".equals(board[row+i][col+i])) {
                    g++;
                } else if ("red".equals(board[row+i][col+i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row+i][col+i]) || "".equals(board[row+i][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col+j] = "red";
                }
            }
        } 
        if (col > 2 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 1; col - i > -1 && row + i < 8 ; i++) {
                if ("blue".equals(board[row+i][col-i])) {
                    g++;
                } else if ("red".equals(board[row+i][col-i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row+i][col-i]) || "".equals(board[row+i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col-j] = "red";
                }
            }
        }
        if (col < 5 && row > 2) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 1; col + i < 8 && row - i > -1 ; i++) {
                if ("blue".equals(board[row-i][col+i])) {
                    g++;
                } else if ("red".equals(board[row-i][col+i])&& g > 0) {
                board[row][col] = "red";
                f = true;
                i = 1000;
                this.whoseMove = false;
            }else if ("red".equals(board[row-i][col+i]) || "".equals(board[row-i][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col+j] = "red";
                }
            }
        } 
         
    }
    public void blueMove(int row, int col) {
        System.out.print("test5");
        if (row > 2) { // up 
            int g =0;
            boolean f = false;
            for (int i = 1; row - i > -1; i++) {
                if ("red".equals(board[row-i][col])) {
                    g++;
                } else if ("blue".equals(board[row-i][col])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            } else if ("blue".equals(board[row-i][col]) || "".equals(board[row-i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col] = "blue";
                }
            }
        }
        if (row < 5) { // down
            int g =0;
            boolean f = false;
            for (int i = 1; row + i < 8; i++) {
                if ("red".equals(board[row+i][col])) {
                    g++;
                } else if ("blue".equals(board[row+i][col])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            } else if ("blue".equals(board[row+i][col]) || "".equals(board[row+i][col])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col] = "blue";
                }
            }
        }
        if (col < 5) { //left
            int g =0;
            boolean f = false;
            for (int i = 1; col + i < 8; i++) {
                if ("red".equals(board[row][col+i])) {
                    g++;
                } else if ("blue".equals(board[row][col+i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            } else if ("blue".equals(board[row][col+i]) || "".equals(board[row][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col+j] = "blue";
                }
            }
        }
        if (col > 2) { //right
            int g =0;
            boolean f = false;
            for (int i = 1; col - i > -1; i++) {
                if ("red".equals(board[row][col-i])) {
                    g++;
                } else if ("blue".equals(board[row][col-i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row][col-i]) || "".equals(board[row][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row][col-j] = "blue";
                }
            }
        }
        if (col > 2 && row > 2) { //top left
            int g =0;
            boolean f = false;
            for (int i = 1; col - i > -1 && row - i > -1 ; i++) {
                if ("red".equals(board[row-i][col-i])) {
                    g++;
                } else if ("blue".equals(board[row-i][col-i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row-i][col-i]) || "".equals(board[row-i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col-j] = "blue";
                }
            }
        } 
        if (col < 5 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 1; col + i < 8 && row + i < 8 ; i++) {
                if ("red".equals(board[row+i][col+i])) {
                    g++;
                } else if ("blue".equals(board[row+i][col+i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row+i][col+i]) || "".equals(board[row+i][col+i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col+j] = "blue";
                }
            }
        } 
        if (col > 2 && row < 5) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 1; col - i > -1 && row + i < 8 ; i++) {
                if ("red".equals(board[row+i][col-i])) {
                    g++;
                } else if ("blue".equals(board[row+i][col-i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row+i][col-1]) || "".equals(board[row+i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row+j][col-j] = "blue";
                }
            }
        }
        if (col < 5 && row > 2) { //bottom right
            int g =0;
            boolean f = false;
            for (int i = 1; col + i < 8 && row - i > -1 ; i++) {
                if ("red".equals(board[row-i][col+i])) {
                    g++;
                } else if ("blue".equals(board[row-i][col+i])&& g > 0) {
                board[row][col] = "blue";
                f = true;
                i = 1000;
                this.whoseMove = true;
            }else if ("blue".equals(board[row-i][col+1]) || "".equals(board[row-i][col-i])) {
                i = 1000;
            }
            }
            if (f) {
                for (int j = g; j >0; j-- ) {
                    board[row-j][col+j] = "blue";
                }
            }
        } 
        
         
    }
    
}

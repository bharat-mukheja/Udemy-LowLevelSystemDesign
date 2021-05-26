import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


class Snake{
    int start;
    int end;
    public Snake(int start, int end) {
        if(start<end){
            // Snakes have to start higher than end
            int temp = start;
            start = end;
            end = temp;
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
class Ladder{
    int start;
    int end;
    public Ladder(int start, int end) {
        if(start>end){
            // Ladders have to start lower than end
            int temp = start;
            start = end;
            end = temp;
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

class Player{
    int id;

    public String getName() {
        return name;
    }

    String name;
    int currentPosition;

    public int getCurrentPosition() {
        return currentPosition;
    }

    static int playerId = 0;
    int getUniqueId(){
        return playerId++;
    }

    public Player(String name) {
        this.name = name;
        this.id = getUniqueId();
        this.currentPosition = 0;
    }
}
class Game{
    List<Player> players;
    int currentTurn;
    Player winner;
    HashMap<Integer,Integer> snakes_and_ladders;

    public List<Player> getPlayers() {
        return players;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean roll(Player player, int value){
        if (this.winner!= null || value<1 || value>6 || players.get(currentTurn) != player){
            return false;
        }
        int newPosition = player.currentPosition + value;
        if(newPosition<=100) {

            if (newPosition == 100) {
                winner = player;
            }
            else if (snakes_and_ladders.get(newPosition) != null) {
                newPosition = snakes_and_ladders.get(newPosition);
            }
            player.currentPosition = newPosition;
        }
        currentTurn = (currentTurn+1)%players.size();
        return true;
    };

    public Game(List<Snake> snakes, List<Ladder> ladders, List<Player> players) {
        this.players = players;
        this.snakes_and_ladders = new HashMap<Integer,Integer>();
        for (Snake snake : snakes) {
            snakes_and_ladders.put(snake.getStart(), snake.getEnd());
        }
        for (Ladder ladder : ladders) {
            snakes_and_ladders.put(ladder.getStart(), ladder.getEnd());
        }
        currentTurn = 0;
        winner = null;
    }

    public int getDiceValue(){
        return (int)Math.floor(Math.random()*6);
    }

    public Player getNextPlayer(){
        return players.get(currentTurn);
    };

}

public class SnakesAndLadders {
    public static void main(String[] args){
        Player p1 = new Player("Robert");
        Player p2 = new Player("Stannis");
        Player p3 = new Player("Renly");

        Snake s1 = new Snake(17,7);
        Snake s2 = new Snake(54,34);
        Snake s3 = new Snake(62,19);
        Snake s4 = new Snake(64,60);
        Snake s5 = new Snake(87,36);
        Snake s6 = new Snake(92,73);
        Snake s7 = new Snake(95,75);
        Snake s8 = new Snake(98,79);

        Ladder l1 = new Ladder(1,38);
        Ladder l2 = new Ladder(4,14);
        Ladder l3 = new Ladder(9,31);
        Ladder l4 = new Ladder(21,42);
        Ladder l5 = new Ladder(28,84);
        Ladder l6 = new Ladder(51,67);
        Ladder l7 = new Ladder(72,91);
        Ladder l8 = new Ladder(80,99);

        List<Snake> snakes = List.of(s1,s2,s3,s4,s5,s6,s7,s8);
        List<Ladder> ladders = List.of(l1,l2,l3,l4,l5,l6,l7,l8);
        List<Player> players = List.of(p1,p2,p3);
        Game game = new Game(snakes,ladders,players);

        Player currentPlayer;
        int diceVal;
        while (game.getWinner()==null){
            diceVal = game.getDiceValue();
            currentPlayer = game.getNextPlayer();
            game.roll(currentPlayer,diceVal);
        }
        System.out.println("Winner of the game is = "+game.getWinner().getName() + "\n");
        System.out.println("All Scores: ");
        for(Player p: game.getPlayers()){
            System.out.println(p.getName() + " is at " + p.getCurrentPosition());
        }
    }
}
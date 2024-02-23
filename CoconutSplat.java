import java.util.*;
class PlayerState {//create a class to store the index of the player and his / her state
    int player;
    String state;
    public PlayerState(int player, String state) {
        this.player = player;
        this.state = state;
    }
}
public class CoconutSplat {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int syllables = sc.nextInt();//read in the number of syllables each round of game
        int players = sc.nextInt();//read in the number of players
        sc.close();
        ArrayList<PlayerState> game = new ArrayList<>();
        for (int i = 1; i <= players; i++) {
            game.add(new PlayerState(i, "folded"));//create a linkedlist to store all the players and their state, we initialize their states as folded first
        }
        int touched = (syllables - 1) % players;//initialize the index of the first player to be touched
        while (game.size() > 1) {
            PlayerState touchedPlayer = game.get(touched);//get the index and state of the player being touched at this round of game
            if (touchedPlayer.state == "folded") {
                touchedPlayer.state = "fist";//if the player is folded, change his / her state to be two fists
                game.add(touched + 1, new PlayerState(touchedPlayer.player, "fist"));//add one fist into the players list to act as a new player
            } else if (touchedPlayer.state == "fist") {
                touchedPlayer.state = "palmdown";//if the player is fist, change the state to palmdown
                touched ++;//next round of game starts with the next player, hence increase the starting index
            } else {
                game.remove(touchedPlayer);//if the player is palmdown, remove him / her from the game
            }
            touched = (touched + syllables - 1) % game.size();//calculate the next player being touched
        }
        System.out.println(game.get(0).player);//print out the final winner
    }
}
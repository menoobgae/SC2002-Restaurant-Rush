package restaurantrush;

/** Owner: P2. Starts the game. Pass a script file to replay a run: java restaurantrush.Main run.txt */
public class Main {
    public static void main(String[] args) {
        ManagerInput input = args.length > 0 ? new ScriptedInput(args[0]) : new RestaurantCLI();
        new GameController(input).run();
    }
}

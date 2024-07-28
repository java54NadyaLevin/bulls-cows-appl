package telran.bullscows;

import java.util.ArrayList;
import java.util.List;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class BullsCowsApplItems {
	static BullsCowsService bullsCows;
	static long gameId;

	public static List<Item> getItems(BullsCowsService bullsCows) {
		BullsCowsApplItems.bullsCows = bullsCows;

		Item[] items = { Item.of("Start new game", io -> {
			gameId = bullsCows.createNewGame();
			startGame(io, bullsCows);
		}), Item.ofExit(), };
		return new ArrayList<>(List.of(items));
	}

	private static void startGame(InputOutput io, BullsCowsService bullsCows) {

		Menu menu = new Menu("Game started", getPlayGameItems());
		menu.perform(io);
		io.writeLine("=".repeat(40));
	}

	private static Item[] getPlayGameItems() {
		Item[] items = { Item.of("Make new move (enter 4 digits sequence)", BullsCowsApplItems::makeNewMove),
				Item.ofExit(), };
		return items;
	}

	private static void makeNewMove(InputOutput io) {
		String move = io.readStringPredicate("Enter 4 digits sequence", "string is not correct",
				str -> str.matches("^(?!.*(\\d).*\\1)\\d{4}$"));
		List<MoveResult> results = bullsCows.getMoveResults(gameId, new Move(gameId, move));
			results.forEach(r -> io.writeLine(r));
			if(bullsCows.isGameOver(gameId)) {
				io.writeLine("You wone the game!");
			}

	}
}

package telran.bullscows;

import java.util.List;

import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class BullsCowsAppl {

	public static void main(String[] args) {
		BullsCowsService bullsCows = new BullsCowsMapImpl();
		
		List<Item> bullsCowsItems =
				BullsCowsApplItems.getItems(bullsCows);
		Menu menu = new Menu("BullsCows Game",
				bullsCowsItems.toArray(Item[]::new));
		menu.perform(new SystemInputOutput());

	}
	}



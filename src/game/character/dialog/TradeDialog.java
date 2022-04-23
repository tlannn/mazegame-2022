package game.character.dialog;

import game.character.Player;
import game.character.Trader;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

public class TradeDialog extends InteractiveDialog {
    private Trader trader;

    public TradeDialog(GraphicsSystem graphicsSystem, InputSystem inputSystem, Trader trader) {
        super(graphicsSystem, inputSystem);
        this.trader = trader;
    }

    public void start(Player player) {
        this.graphics.displayText("Souhaitez-vous acheter quelque chose ?");

        /*// Display items to sell
        for (int i = 0; i < this.trader.getParchments().size(); ++i)
            this.graphics.displayText(i + " - " + this.trader.getParchment(i));

        int choiceIndex = this.input.getInteger();

        if (choiceIndex > 0 && choiceIndex < this.trader.getParchments().size())*/
    }
}

package game.character.dialog;

import game.character.Player;
import game.character.Trader;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;

public class TraderDialog extends InteractiveDialog {
    private Trader trader;

    public TraderDialog(GraphicsSystem graphicsSystem, InputSystem inputSystem, Trader trader) {
        super(graphicsSystem, inputSystem);
        this.trader = trader;
    }

    public void start(Player player) {
        this.graphics.displayText("En échange de la modique somme de " +trader.getParchmentCost() + " galons d'or, souhaitez-vous acquérir ce parchemin ? [o/n]");
        char rep = this.input.getLetter();

        this.graphics.displayText("vous avez dit " + rep);

// on ne rentre pas dans le if
        if (Character.toString(rep).equals("O")){
          this.graphics.displayText("vous avez dit OUI. Vive les mariés");
        }

        // System.out.println("En échange de la modique somme de " + this.parchmentCost + " galons d'or, souhaitez-vous acquérir ce parchemin ?");
        // Scanner scan= new Scanner(System.in);
        // String text= scan.nextLine();
        // if(text.equals("o") ){
        // 	player.getInventory().addItem(parchments.get(0));
        // 	this.removeParchment(parchments.get(0));
        // 	this.increaseParchmentCost();
        // }
        // scan.close();

        /*// Display items to sell
        for (int i = 0; i < this.trader.getParchments().size(); ++i)
            this.graphics.displayText(i + " - " + this.trader.getParchment(i));

        int choiceIndex = this.input.getInteger();

        if (choiceIndex > 0 && choiceIndex < this.trader.getParchments().size())*/
    }
}

package game.character.dialog;

import game.character.Player;
import game.character.Trader;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.character.NotEnoughGoldException;

public class TraderDialog extends InteractiveDialog {
    private Trader trader;

    public TraderDialog(GraphicsSystem graphicsSystem, InputSystem inputSystem, Trader trader) {
        super(graphicsSystem, inputSystem);
        this.trader = trader;
    }

    public void start(Player player) {
        if(! this.trader.getParchments().isEmpty()){

            this.graphics.displayText("En échange de la modique somme de " +this.trader.getParchmentCost() + " galons d'or, souhaitez-vous acquérir un parchemin ? [O/N]");
            char rep = this.input.getLetter();

            if (Character.toString(rep).equals("O")){
                    try{
                        player.removeGold(this.trader.getParchmentCost());
                        player.getInventory().addItem(this.trader.getParchments().get(0));
                        this.trader.removeParchment(this.trader.getParchments().get(0));
                        this.trader.increaseParchmentCost();
                        this.graphics.displayText("Vous avez acheté un parchemin.");
                    }
                    catch(NotEnoughGoldException e){
                        this.graphics.displayText(e.getMessage());
                        this.graphics.displayText("Vous n'avez pas assez de galons d'or. Revenez plus tard.");
                    }
            }
            else{
                this.graphics.displayText("Tant pis, ce sera peut-être pour une prochaine fois.");
            }
        }

        else{
            this.graphics.displayText("Tu as déjà acheté tous mes indices.");
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

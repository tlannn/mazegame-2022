package game.character.dialog;

import game.Game;
import game.character.Player;
import game.character.Trader;
import game.system.SpeechPauseSystem;
import game.system.input.InputSystem;
import game.system.graphics.GraphicsSystem;
import game.character.NotEnoughGoldException;

/**
 * A dialog where a trader tries to sell a parchment to a player
 */
public class TraderDialog extends Dialog {
    private Trader trader;

    /**
     * Class constructor
     * @param trader the trader selling items
     */
    public TraderDialog(Trader trader) {
        super();
        this.trader = trader;
    }

    @Override
    public void start(Player player) {
        InputSystem input = Game.getInputSystem();
        GraphicsSystem graphics = Game.getGraphicsSystem();

        if(! this.trader.getParchments().isEmpty()){
            graphics.displayText("Bonjour " + player + ", je suis Bernard le marchand"+ SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + "." + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + ".");
            graphics.displayText("En échange de la modique somme de " +this.trader.getParchmentCost() + " galons d'or," + SpeechPauseSystem.SLOW_PAUSE_DELAY_TAG + " souhaites-tu acquérir un parchemin ? [O/N]");
            char rep = input.getLetter();

            if (Character.toString(rep).equals("O")){
                    try{
                        player.removeGold(this.trader.getParchmentCost());
                        player.getInventory().addItem(this.trader.getParchments().get(0));
                        this.trader.removeParchment(this.trader.getParchments().get(0));
                        this.trader.increaseParchmentCost();
                        graphics.displayText("Tu as acheté un parchemin." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                    }
                    catch(NotEnoughGoldException e){
//                        this.graphics.displayText(e.getMessage());
                        graphics.displayText("Tu n'as pas assez de galons d'or." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + " Reviens plus tard." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
                    }
            }
            else{
                graphics.displayText("Tant pis, ce sera peut-être pour une prochaine fois." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
            }
        }

        else{
            graphics.displayText("Désolé " + player + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG + ", tu as déjà acheté tous mes parchemins." + SpeechPauseSystem.LONG_PAUSE_DELAY_TAG);
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

public class Game{
    pulic game(){

    }

    public boolean playTurn(Character player){
        Scanner scan= new Scanner(System.in);
        String text= scan.nextLine();
        System.out.println("Pour avancer d'une case appuyé sur a ");
        System.out.println("Pour regarder autour de vous appuyer sur r ");
        if text.equals("a"){
            System.out.println("appuyer sur z pour avancer vers le nord, sur d pour aller vers l'est, sur q pour aller vers l'ouest sur s pour aller vers le sud");
            this.move()
        }
    }

    public boolean move(Character player){

    }
}

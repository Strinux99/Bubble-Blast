import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);

		Gioco gioco=null, salvagioco=null;
		boolean valid=false, restart=false;
		int r,c;

		do {
			System.out.println("inserisci numero di righe e colonne");
			try {
				gioco=new Gioco(input.nextInt(),input.nextInt());
				salvagioco=new Gioco(gioco.getCampo());
				valid=true;
			} catch(NegativeArraySizeException e){
				System.out.println("non hai inserito un numero di righe o di colonne valido");
				System.out.println();
			}catch (InputMismatchException e) {
				System.out.println("hai inserito un carattere!");
				System.out.println("Programma terminato");
			    return;
			}
		} while(!valid);

		do {
			do {
				
					System.out.println("mosse rimanenti:"+(gioco.getCampo().getMinMosse()-gioco.getTurno()));
					System.out.println("seleziona riga e colonna");
					gioco.getCampo().StampaGriglia();
					try {
						gioco.Incrementa(input.nextInt(),input.nextInt());
					}catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("non hai inserito una riga o una colonna validi!!");
						System.out.println();
					}catch(InputMismatchException e) {
						System.out.println("hai inserito un carattere!");
						System.out.println("Programma terminato");
						return;
					}
				
				
			} while(!gioco.isWon() && !gioco.isLost());

			gioco.getCampo().StampaGriglia();
			if(gioco.isWon()) {
				System.out.println("hai vinto!");
			} else {
				System.out.println("hai perso!");
				System.out.println("vuoi rigiocare? y/n");
				if(input.next().charAt(0)=='y') {
					gioco=new Gioco(salvagioco.getCampo());
					restart=true;
				}
			}

		} while(restart);
		input.close();
	}

}

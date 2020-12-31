
public class Gioco {
	private CampodiGioco campo;
	private int turno;
	
	public Gioco(int i, int j){
		campo=new CampodiGioco(i,j);
		turno=0;
	
	}
	
	public Gioco(CampodiGioco c){
		campo=new CampodiGioco(c);
		turno=0;
	
	}
	
	public CampodiGioco getCampo() {
		return this.campo;
	}
	
	public int getTurno() {
		return this.turno;
	}
	
	
	public void Incrementa(int i,int j){
		if(campo.getCella(i, j)!=Bolla.none) {
			turno++;
			}
		campo.incCella(i,j);
	}
	
	
	public boolean isLost() {
		return ((turno>=campo.getMinMosse()) && !campo.isZeroFilled());	
	}
	
	
	public boolean isWon() {
		return ((turno<=campo.getMinMosse()) && campo.isZeroFilled());	
	}

	


}

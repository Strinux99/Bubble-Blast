

public class CampodiGioco {
	private Bolla[][]mat;
	private int righe,colonne,minmosse;
	
	
	public CampodiGioco(int ri,int co)	{	//inizializza campo di gioco random
		int i,j;
		righe=ri;
		colonne=co;
		mat=new Bolla[ri][co];
		do {
		for(i=0;i<ri;i++) {
			for(j=0;j<co;j++) {
					mat[i][j]=Bolla.getRandomValid();
			}
		}
		}while(this.isZeroFilled());
		CampodiGioco campoideale =new CampodiGioco(righe,colonne,Bolla.piena);
		minmosse=campoideale.DifferenzaCampo(this)+righe;//caso peggiore possibile: devo mettere tutte le bolle a gonfie e incrementare una bolla per riga
		this.mosseMin(0,new CampodiGioco(this));
	}
	
	public CampodiGioco(int ri,int co,Bolla bo)	{	//mi genera un campo con bolle nello stato che gli passo
		int i,j;
		righe=ri;
		colonne=co;
		mat=new Bolla[ri][co];
		for(i=0;i<ri;i++) {
			for(j=0;j<co;j++) {
					mat[i][j]=bo;
			}
		}
		switch(bo) {
		case sgonfia:
			minmosse=2*(righe+colonne-2);
			break;
		case meta:
			minmosse=righe+colonne-2;
			break;
		case piena:
			minmosse=1;
			break;
		default:
			minmosse=0;
			break;
		}
	}
	
	
	public CampodiGioco(CampodiGioco c)	{	//dato un campo di gioco c inizializza un campo di gioco uguale a c.
		int i,j;		
		this.minmosse=c.getMinMosse();
		this.righe=c.getRighe();
		this.colonne=c.getColonne();
		mat=new Bolla[c.getRighe()][c.getColonne()];
		for(i=0;i<righe;i++) {
			for(j=0;j<colonne;j++) {
					mat[i][j]=c.getCella(i, j);
			}
		}
	}
	
	
	public int getRighe() {
		return righe;
	}
	
	
	public int getColonne() {
		return colonne;
	}
	
	public int getMinMosse() {
		return this.minmosse;
	}
	
	public void incCella(int i,int j) {
		mat[i][j]=mat[i][j].getNextState();
		this.propagaEsplosione();
	}
	
	public void decCella(int i,int j) {
		mat[i][j]=mat[i][j].getPrevState();
	}
	
	public void setNoneCella(int i,int j) {
		mat[i][j]=Bolla.none;
	}
	
	public Bolla getCella(int i,int j) {
		return mat[i][j];
	}
	
	public void StampaGriglia() {
		int i,j;
		for(i=0;i<this.righe;i++) {
			for(j=0;j<this.colonne;j++) {	
				System.out.print(mat[i][j].toString()+" ");
			}
			System.out.print('\n');
		}

	}
	

	
	
	
	public void propagaEsplosione(){
		int i,j,temp1;
		
		for(i=0;i<this.righe;i++) {
		for(j=0;j<this.colonne;j++) {	
		
		if(mat[i][j]==Bolla.scoppiata) {
			this.setNoneCella(i,j);
			temp1=i-1;						//propago verso l'alto
			while((temp1)>=0 && (mat[temp1][j]==Bolla.none||mat[temp1][j]==Bolla.scoppiata)) {	
				temp1--;
			}
			if(temp1>=0) {
				this.incCella(temp1, j);
			}

			temp1=i+1;						//propago verso il basso			
			while((temp1)<righe && (mat[temp1][j]==Bolla.none||mat[temp1][j]==Bolla.scoppiata)) {
				temp1++;
			}
			if(temp1<righe) {
				this.incCella(temp1, j);
			}

			temp1=j-1;						//propago verso sinistra
			while((temp1)>=0 && (mat[i][temp1]==Bolla.none||mat[i][temp1]==Bolla.scoppiata)) {	
				temp1--;
			}
			if(temp1>=0) {
				this.incCella(i, temp1);
			}

			temp1=j+1;						//propago verso destra			
			while((temp1)<colonne && (mat[i][temp1]==Bolla.none||mat[i][temp1]==Bolla.scoppiata)) {
				temp1++;
			}
			if(temp1<colonne) {
				this.incCella(i, temp1);
			}
		}
		}
		}
		
	}
	
	
	
	public int DifferenzaCampo(CampodiGioco c) {	//dato un campo restituisce il numero di stati di differenza tra lui e quello dato
		int i,j,cont;
		if(this.righe!=c.righe || this.colonne!=c.colonne) {
			return -1;
		}
		cont=0;
		for(i=0;i<this.righe;i++) {
			for(j=0;j<this.colonne;j++) {
				if(this.mat[i][j]!=Bolla.none && c.getCella(i,j)!=Bolla.none ) {
					cont+=Math.abs(mat[i][j].toInteger()-c.getCella(i, j).toInteger());
				}else if(this.mat[i][j]==Bolla.none && c.getCella(i,j)!=Bolla.none) {
					cont+=c.getCella(i, j).toInteger();
				}else if(c.getCella(i,j)==Bolla.none && this.mat[i][j]!=Bolla.none){
					cont+=mat[i][j].toInteger();
				}
			}
		}
		return cont;
	}

	public boolean isZeroFilled() {
		int i,j;
		boolean fin;
		fin=true;
		for(i=0;i<righe;i++) {
			for(j=0;j<colonne;j++) {
				if(mat[i][j]!=Bolla.none) {
					fin=false;	
				}
			}
		}
		return fin;
	}
	
	
	
	public void mosseMin(int mosse,CampodiGioco ca){
		int i,j;
		if( ca.isZeroFilled()) {
			if(mosse<minmosse) {
			minmosse=mosse;
		}
		}else {
			for(i=(ca.getRighe()-1);i>=0;i--) {
				for(j=(ca.getColonne()-1);j>=0;j--) {
					if(ca.getCella(i, j)!=Bolla.none && mosse<this.minmosse) {
						CampodiGioco c= new CampodiGioco(ca);
						c.incCella(i, j);
						mosse++;
						mosseMin(mosse,c);
						mosse--;
					}
				}
			}
		}
		return;
	}
}

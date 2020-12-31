import java.util.Random;

public enum Bolla {
none,sgonfia,meta,piena,scoppiata;

	
	
	public Bolla getNextState(){
		switch(this) {
		case sgonfia:
			return meta;
		case meta:
			return piena;
		case piena:
			return scoppiata;
		default:
			return this;
		}
	}
	
	public String toString() {
		switch(this) {
		case sgonfia:
			return "1";
		case meta:
			return "2";
		case piena:
			return "3";
		case scoppiata:
			return "4";
		default:
			return "0";
		}
	}
	
	
	public Bolla getPrevState() {
		switch(this) {
		case meta:
			return sgonfia;
		case piena:
			return meta;
		default:
			return this;
		}
	}
	
	public static Bolla getRandomValid() {
		Random r = new Random();
		return values()[r.nextInt(4)];//values ti restituisce tutti i valori che una enum può valere
	}
	
	public int toInteger() {
		
			switch(this) {
			case sgonfia:
				return 1;
			case meta:
				return 2;
			case piena:
				return 3;
			case scoppiata:
				return 4;
			default:
				return 0;
			}
		
		
	}

}

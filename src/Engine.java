import java.util.Random;

import javax.swing.ImageIcon;




public class Engine {
Random rand = new Random();
int poeni = 0;
int bekapPoena = 0;
Polje[][] polja;
int niz[] = new int[4];
Polje[][] bekap = new Polje[4][4];

	
	public Engine() {
		novaIgra();
	}
	
	
	public void novaIgra() {
		
		poeni = 0;
		polja = new Polje[4][4];
		novoPolje();
	
	}
	
	
	public void igrajGore() {
	
		for(int j = 0 ; j < 4 ; j++) {
			for(int i = 0 ; i < 4 ; i++) 
				if(polja[i][j]==null) niz[i]=0;
				else			      niz[i]=polja[i][j].val;
				
			odigrajRed();
			
			
			
			for(int i = 0 ; i < 4 ; i++) 
				if(niz[i]==0) polja[i][j]=null;
				else	   polja[i][j]= new Polje(niz[i]);
			
			
			
		}
	}
	
	public void igrajDole() {
	int k = 0;
		for(int j = 0 ; j < 4 ; j++) {
			for(int i = 3 ; i >=0 ; i--) { 
				if(polja[i][j]==null) niz[k]=0;
				else			      niz[k]=polja[i][j].val;
				k++;
				}
			k=0;
			odigrajRed();
			
			for(int i = 3 ; i >=0 ; i--) {
				if(niz[k]==0) polja[i][j]=null;
				else	   polja[i][j]= new Polje(niz[k]);
				k++;
			}
			k=0;
		}	
	}
	
	public void igrajDesno() {
		int k = 0;
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 3 ; j >=0 ; j--) { 
				if(polja[i][j]==null) niz[k]=0;
				else			      niz[k]=polja[i][j].val;
				k++;
				}
			k=0;
			odigrajRed();
			
			for(int j = 3 ; j >=0 ; j--) {
				if(niz[k]==0) polja[i][j]=null;
				else	   polja[i][j]= new Polje(niz[k]);
				k++;
			}
			k=0;
		}	
	}
	
	public void igrajLevo() {

		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 4 ; j++) 
				if(polja[i][j]==null) niz[j]=0;
				else			      niz[j]=polja[i][j].val;
				
			odigrajRed();
			
			
			
			for(int j = 0 ; j < 4 ; j++) 
				if(niz[j]==0) polja[i][j]=null;
				else	   polja[i][j]= new Polje(niz[j]);
			
			
			
		}
	}
	
	public ImageIcon dajIkonicu(int i,int j) {
		ImageIcon ikonica= new ImageIcon();
		 
		if(polja[i][j]!=null) {
			String nazivSlike = polja[i][j].val + ".png"; 
			String putanja = "bin/images/" + nazivSlike;
			
			ikonica = new ImageIcon(putanja);
		}
		else return null;
		
		return ikonica;
	}
	
	public void novoPolje() {
		int vred = rand.nextInt(100);
		int i = rand.nextInt(4);
		int j = rand.nextInt(4);
			while(polja[i][j]!=null) {
				i = rand.nextInt(4);
				j = rand.nextInt(4);
			}
			
			if(vred > 50)
				polja[i][j]= new Polje(1);
				else
				polja[i][j]= new Polje(2);
				
	}
	
	public void spoji() {
		for(int i = 0 ; i < 3; i++) 
		for(int j = i+1 ; j < 4; j++)
			if(niz[i]==0 && niz[j]!=0) {
				niz[i]=niz[j];
				niz[j]=0;
				break;
			}
	}
	
	public void odigrajRed() {
	int i = 0;
	int j;
	spoji();
	while(i<3)
	{
		j=i+1;
		if(niz[i]==niz[j])
		{
			niz[i]= niz[i]*2;
			poeni +=niz[i];
			i+=1;
			niz[j]=0;
		}
		i++;
	}
	spoji();
	}
	public boolean popunjenost() {
		int pop = 0;
		for(int i = 0 ; i < 4 ; i ++)
		for(int j = 0 ; j < 4 ; j ++)
			if(polja[i][j]!=null) pop ++;
		
		if(pop==16) return true;
		else return false;
	}
	public void bekapuj() {
		for(int i = 0 ; i < 4 ; i++)
		for(int j = 0 ; j < 4 ; j++)
			bekap[i][j]=polja[i][j];
		
		bekapPoena = poeni;
	}
	public void povrati() {
		for(int i = 0 ; i < 4 ; i++)
		for(int j = 0 ; j < 4 ; j++)
			polja[i][j]=bekap[i][j];
		
		poeni = bekapPoena;
	}
	
	
	public String toString() {
		String sum = "";
			for(int i = 0 ; i < 4; i++) { 
		    	for(int j = 0 ; j < 4; j++)
				if(polja[i][j]==null) sum+=" 0 ";
				else				  sum+=" "+polja[i][j].val+" ";
			sum+="\n";		
			}
			return sum;
	}
}

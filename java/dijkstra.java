public class dijkstra {

	public static void main(String[] args) {
			
			
			int[][] graf = {{ 0, 0, 0, 1, 90, 35, 89, 2, 0, 28},{ 0, 0, 1, 19, 0, 0, 0, 0, 2, 85},{ 0, 1, 0, 0, 7, 0, 0, 0, 0, 0},{ 1, 19, 0, 0, 0, 0, 91, 0, 0, 0},{ 90, 0, 7, 0, 0, 0, 78, 0, 15, 61},{ 35, 0, 0, 0, 0, 0, 0, 0, 81, 0},{ 89, 0, 0, 91, 78, 0, 0, 0, 0, 0},{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 3},{ 0, 2, 0, 0, 15, 81, 0, 0, 0, 0},{ 28, 85, 0, 0, 61, 0, 0, 3, 0, 0}};


			int[][] wynik = algorytm(1,2,graf); 
			
			System.out.println(wynik[4][2]);
		
	}
	
	
	static int[][] algorytm( int punkt_start, int punkt_koniec, int[][] graf)
	{

		long startTime = System.currentTimeMillis();
		int wielkosc = graf.length;

		int[] dystans = new int[wielkosc]; 
		boolean[] tab_wizytacji = new boolean[wielkosc]; 
		
		int[][] wynik = new int[wielkosc][wielkosc]; 

		for (int g = 0; g<wielkosc; ++g) {
			for (int k = 0; k<wielkosc; ++k) {
				wynik[k][g] = 0;
			}
		}
		
		int max = 999;
		for(int i = 0; i < wielkosc; ++i){

	        for(int j = 0; j < wielkosc; ++j)
	        {
	            if(graf[i][j] == 0){
	                graf[i][j] = max;
	            }
	        } 
	    }
		for (int g = 0; g<wielkosc; ++g) {
			punkt_start = g;
			for (int k = 0; k<wielkosc; ++k) {
				punkt_koniec = k;
		
				dystans = new int[wielkosc]; 
				tab_wizytacji = new boolean[wielkosc]; 
		
				
		
				for(int i = 0; i < wielkosc; ++i) {
				    dystans[i] = max; // wielkosc maksymalna
				}
				dystans[punkt_start] = 0;
		
				for(int i = 0; i < wielkosc; ++i) {
				    tab_wizytacji[i] = false;
				}
		
				for(int i = 0; i < wielkosc; ++i){
			        int aktualny = -1;
			        for(int j = 0; j < wielkosc; ++j){
			            if(tab_wizytacji[j])
			            {
			                continue;
			            }
			            if(aktualny == -1 || dystans[j] < dystans[aktualny])
			            {
			                aktualny = j;
			            }
			        }
			        
			        tab_wizytacji[aktualny] = true;
		
			        for(int j = 0; j < wielkosc; ++j)
			        {
			            int droga = dystans[aktualny] + graf[aktualny][j];
			            if(droga < dystans[j]){
			                dystans[j] = droga;
			            }
			        }  
			    }
				
				wynik[k][g] = dystans[k];
				wynik[g][k] = dystans[k];
		
			}
	}


		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Czas " + estimatedTime + " ms.");  

		return wynik;
		
	}

}

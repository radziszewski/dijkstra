package dijkstra;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Dijkstra {

	public static void main(String[] args) {
		
		int size = 10;

		switch(args[0]) {
			case "10": size=10; break;
			case "100": size=100; break;
			case "200": size=200; break;
			case "300": size=300; break;
		}

		String name= size + "x" + size;
		String path = "../grafy/" + name + "/graf_" + name + ".json";
		
		JSONArray jsonArray = new JSONArray();
		try {
			 jsonArray = parseJSONFile(path);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
			
		int len = jsonArray.length();
		int[][] graf = new int[len][len];
		
		try {
			for(int i = 0; i < len; ++i) {
				JSONArray row = jsonArray.getJSONArray(i);
				for(int j = 0; j < len; ++j) {
					graf[i][j] = row.getInt(j);
				}
			}		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	/*	for(int i = 0; i < len; ++i) {
			for(int j = 0; j < len; ++j) {
				System.out.println(graf[i][j] + ", ");
			}
			System.out.println("");
		} */	
			
		int[][] wynik = algorytm(1,2,graf); 	
		//System.out.println(wynik[4][2]);
		
	}
	
	static JSONArray parseJSONFile(String filename) throws  IOException, JSONException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONArray(content);
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
		
		int max = 99999;
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
		System.out.println("" + estimatedTime + "");  

		return wynik;
		
	}

}

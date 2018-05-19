import sys
import time
import json


def dijkstra( punkt_start, punkt_koniec, graf):

    start_time = time.time();
    
    max = 99999;
    wielkosc = len(graf);
    wynik = [];

    dystans=[];
    for i in range(wielkosc):
      dystans.append(max);
    
    dystans[punkt_start] = 0;
      
    tab_wizytacji=[];    
    for i in range(wielkosc):
      tab_wizytacji.append(False);
    
    for i in range(wielkosc):
      for j in range(wielkosc):
        if graf[i][j] == 0:
          graf[i][j] = max;
          
    for g in range(wielkosc):
      wynik.append([]);
      for k in range(wielkosc):
        wynik[g].append(0);
        
    for g in range(wielkosc):
      punkt_start = g;
      for k in range(wielkosc): 
        punkt_koniec = k;
        
        for i in range(wielkosc):
          dystans[i] = max;
        
        dystans[punkt_start] = 0;
             
        for i in range(wielkosc):
          tab_wizytacji[i] = False;
      
        for i in range(wielkosc):      
          aktualny = -1;
          for j in range(wielkosc): 
            if tab_wizytacji[j]:
                continue;
            elif aktualny == -1 or dystans[j] < dystans[aktualny]:
              aktualny = j;
          
          tab_wizytacji[aktualny] = True;
          
          for j in range(wielkosc): 
            droga = dystans[aktualny] + graf[aktualny][j];
            if droga < dystans[j]:
              dystans[j] = droga;
              
        wynik[k][g]=dystans[k];
        wynik[g][k]=dystans[k];
		
    elapsed_time = time.time() - start_time;
    elapsed_time *= 1000;
    print("%.0f" % (elapsed_time));
    return wynik;


size = 10;
arg = sys.argv[1];

if arg == "10":
    size=10;
elif arg == "100":
    size=100;
elif arg == "200":
    size=200;
elif arg == "300":
    size=300;

name= '%sx%s' % (size, size)
path = "../grafy/" + name + "/graf_" + name + ".json";


with open(path) as f:
    data = json.load(f) # zwraca liste

graf = data;
	
#print(graf)

wynik = dijkstra(1,1,graf)
#print(wynik[4][3]);
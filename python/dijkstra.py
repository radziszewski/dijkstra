import time

def dijkstra( punkt_start, punkt_koniec, graf):

    start_time = time.time();
    
    max = 999;
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
    print("Czas trwania: %.3f s" % (elapsed_time));
    return wynik;



graf = [
[ 0, 0, 0, 1, 90, 35, 89, 2, 0, 28],
[ 0, 0, 1, 19, 0, 0, 0, 0, 2, 85],
[ 0, 1, 0, 0, 7, 0, 0, 0, 0, 0],
[ 1, 19, 0, 0, 0, 0, 91, 0, 0, 0],
[ 90, 0, 7, 0, 0, 0, 78, 0, 15, 61],
[ 35, 0, 0, 0, 0, 0, 0, 0, 81, 0],
[ 89, 0, 0, 91, 78, 0, 0, 0, 0, 0],
[ 2, 0, 0, 0, 0, 0, 0, 0, 0, 3],
[ 0, 2, 0, 0, 15, 81, 0, 0, 0, 0],
[ 28, 85, 0, 0, 61, 0, 0, 3, 0, 0]
];

wynik = dijkstra(1,1,graf)
print(wynik[4][3]);
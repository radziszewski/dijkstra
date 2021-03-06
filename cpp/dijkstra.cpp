//#include "stdafx.h"
#include <iostream>
#include <string>
#include <sys\timeb.h> 

using namespace std;

int** algorytm(int graf[10][10]) 
{
	struct timeb start, end;
	int diff;
	ftime(&start);

	int wielkosc = sizeof(graf);

	int* dystans = new int[wielkosc];
	int* tab_wizytacji = new int[wielkosc];

	int** wynik = new int*[wielkosc];
	for (int i = 0; i < wielkosc; i++) {
		wynik[i] = new int[wielkosc];
	}

	for (int g = 0; g<wielkosc; ++g) {
		for (int k = 0; k<wielkosc; ++k) {
			wynik[k][g] = 0;
		}
	}

	int max = 99999;
	for (int i = 0; i < wielkosc; ++i) {

		for (int j = 0; j < wielkosc; ++j)
		{
			if (graf[i][j] == 0) {
				graf[i][j] = max;
			}
		}
	}

	int punkt_start = 0;
	int punkt_koniec = 0;

	for (int g = 0; g<wielkosc; ++g) {
		punkt_start = g;
		for (int k = 0; k<wielkosc; ++k) {
			punkt_koniec = k;

			for (int i = 0; i < wielkosc; ++i) {
				dystans[i] = max; // wielkosc maksymalna
			}
			dystans[punkt_start] = 0;

			for (int i = 0; i < wielkosc; ++i) {
				tab_wizytacji[i] = 0;
			}

			for (int i = 0; i < wielkosc; ++i) {
				int aktualny = -1;
				for (int j = 0; j < wielkosc; ++j) {
					if (tab_wizytacji[j] == 1)
					{
						continue;
					}
					if (aktualny == -1 || dystans[j] < dystans[aktualny])
					{
						aktualny = j;
					}
				}


				tab_wizytacji[aktualny] = 1;

				for (int j = 0; j < wielkosc; ++j)
				{
					int droga = dystans[aktualny] + graf[aktualny][j];
					if (droga < dystans[j]) {
						dystans[j] = droga;
					}
				}
			}

			wynik[k][g] = dystans[k];
			wynik[g][k] = dystans[k];

		}
	}
	ftime(&end);
	diff = (int)(1000.0 * (end.time - start.time)
		+ (end.millitm - start.millitm));

	cout << diff;

	return wynik;
}


int main(int argc, char *argv[])
{
	int size = 10;
	// argument - wielksoc grafu
	if (argv[1])
		size = strtol(argv[1], NULL, 10);


	// trzeba zrobic jsona ://
	
	int graf[10][10] = { { 0, 0, 0, 1, 90, 35, 89, 2, 0, 28 }, { 0, 0, 1, 19, 0, 0, 0, 0, 2, 85 }, { 0, 1, 0, 0, 7, 0, 0, 0, 0, 0 }, { 1, 19, 0, 0, 0, 0, 91, 0, 0, 0 }, { 90, 0, 7, 0, 0, 0, 78, 0, 15, 61 }, { 35, 0, 0, 0, 0, 0, 0, 0, 81, 0 }, { 89, 0, 0, 91, 78, 0, 0, 0, 0, 0 }, { 2, 0, 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 2, 0, 0, 15, 81, 0, 0, 0, 0 }, { 28, 85, 0, 0, 61, 0, 0, 3, 0, 0 } };
	;

	int** wynik = algorytm(graf);

	cout << endl;
	cout << wynik[2][3];
		
	getchar();
	return 0;
}


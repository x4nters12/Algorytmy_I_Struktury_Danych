package App;

import java.util.Random;

public class Main {
    final static int N = 6; // LICZBA PRZEDMIOTÓW
    final static int MAX_CAPACITY = 10; // MAX OBJĘTOŚĆ PLECAKA
    final static int ATTEMPTS = 10; // LICZBA LOSOWAŃ DO PRÓBKI

    final static int[] CAPACITY = {6,  2,  3,  2,  3,  1}; // TABLICA OBJĘTOŚCI PRZEDMIOTÓW
    final static int[] VALUE    = {6,  4,  5,  7, 10,  2}; // TABLICA WARTOŚCI PRZEDMIOTÓW

    // MAIN
    public static void main(String[] args){
        knapsackMonteCarlo();
    }

    // METODA MONTE CARLO
    private static void knapsackMonteCarlo(){
        String ANSWER = new String(); // ŁAŃCUCH STRING Z ODPOWIEDZIĄ / RÓWNIE DOBRZE MOŻNA UŻYĆ TABLICY INT[N]
        int BEST_CAPACITY = 0; // ZMIENNA PRZECHOWUJĄCA KOŃCOWĄ SUMĘ OBJĘTOŚCI PAKOWANYCH PRZEDMIOTÓW
        int BEST_VALUE = 0; // ZMIENNA PRZECHOWUJĄCA KOŃCOWĄ SUMĘ WARTOŚCI PAKOWANYCH PRZEDMIOTÓW

        Random RANDOM = new Random(); // UTWORZENIE GENERATORA LICZB PSEUDOLOSOWYCH DO GENEROWANIA STANÓW

        for (int i=0; i<ATTEMPTS; i++){ // PĘTLA DO GENEROWANIA STANÓW 'ATTEMPTS'-RAZY
            int[] SETITEM = new int[N]; // TABLICA Z UKŁADEM PRZEDMIOTÓW
            for (int j=0; j<N; j++) SETITEM[j] = j; // WYGENEROWANIE WSTĘPNEGO USTAWIENIA PRZEDMIOTÓW

            for (int j=0; j<N; j++){ // PĘTLA ZAMIENIAJĄCA KOLEJNOŚĆ PRZEDMIOTÓW W SPOSÓB LOSOWY
                int GENINDEX = RANDOM.nextInt(N); // GENEROWANIE INDEKSU Z ZAKRESU 0-N
                if (GENINDEX != j){ // WARUNEK ZAPOBIEGAJĄCY POWTARZANIU SIĘ UKŁADU ELEMENTÓW
                    int TEMP = SETITEM[j];          //# ZAMIANA PRZEDMIOTÓW
                    SETITEM[j] = SETITEM[GENINDEX]; //# WYKORZYSTUJĄCA ZMIENNĄ
                    SETITEM[GENINDEX] = TEMP;       //# TYMCZASOWĄ 'TEMP' DO PRZECHOWANIA WARTOŚCI
                }
            }

            String TEMPQUEUE = new String(); // ZMIENNA TYMCZASOWA -> PRZECHOWUJE POTENCJALNE ROZWIĄZANIE WYLICZANE NIŻEJ
            int SUM_CAPACITY = 0; // SUMA AKTUALNYCH OBJĘTOŚCI PAKOWANYCH PRZEDMIOTÓW
            int SUM_VALUE = 0; // SUMA AKTUALNYCH WARTOŚCI PAKOWANYCH PRZEDMIOTÓW
            int INDEX = 0; // ZMIENNA DEFINUJĄCA INDEKS ROZPATRYWANEGO ELEMENTU TABLICY Z PRZEDMIOTAMI DO ZAPAKOWANIA

            while ((INDEX < N) && (SUM_CAPACITY + CAPACITY[SETITEM[INDEX]] <= MAX_CAPACITY)){ // DOPÓKI NIE SKOŃCZYŁA SIĘ TABLICA Z PRZEDMIOTAMI I DANY PRZEDMIOT ZMIEŚCI SIĘ W PLECAKU -> WYKONAJ
                SUM_CAPACITY = SUM_CAPACITY + CAPACITY[SETITEM[INDEX]]; // SUMOWANIE OBJĘTOŚCI -> SUMA AKTUALNA + OBJĘTOŚĆ PRZEDMIOTU PAKOWANEGO
                SUM_VALUE = SUM_VALUE + VALUE[SETITEM[INDEX]]; // SUMOWANIE WARTOŚCI -> SUMA AKTUALNA + WARTOŚĆ PAKOWANEGO PRZEDMIOTU
                TEMPQUEUE = TEMPQUEUE + " " + SETITEM[INDEX]; // DODANIE PRZEDMIOTU DO POTENCJALNEJ ODPOWIEDZI
                INDEX++; // INKREMENTACJA INDEKSU
            }

            if (SUM_VALUE > BEST_VALUE){ // JEŚLI WYŻEJ OBLICZONA KONFIGURACJA JEST CENNIEJSZA OD POPRZEDNIEJ -> ZAPISZ JĄ DO ODPOWIEDZI
                BEST_CAPACITY = SUM_CAPACITY; // PRZYPISANIE DO OPTYMALNEJ SUMY AKTUALNEJ SUMY OBJĘTOŚCI
                BEST_VALUE = SUM_VALUE; // PRZYPISANIE DO OPTYMALNEJ SUMY AKTUALNEJ SUMY WARTOŚCI
                ANSWER = TEMPQUEUE; // PRZYPISYWANIE DO ODPOWIEDZI AKTUALNEJ KONFIGURACJI
            }
        }

        System.out.println("# WYZNACZONE OPTYMALNE USTAWIENIE PRZEDMIOTÓW -> " + ANSWER); // WYPISANIE ODPOWIEDZI
        System.out.println("# OBJĘTOŚĆ PAKOWANYCH PRZEDMIOTÓW -> " + BEST_CAPACITY); // WYPISANIE SUMY OBJĘTOŚCI
        System.out.println("# WARTOŚĆ PAKOWANYCH PRZEDMIOTÓW -> " + BEST_VALUE); // WYPISANIE ŁĄCZNEJ WARTOŚCI
    }
}
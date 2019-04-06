package App;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int zl, gr, reszta, i=0;

        final int[] M = { 500, 200, 100, 50, 20, 10,  5,  2,  1 };
              int[] C = {  10,   0,   1,  1,  1,  1, 12, 15, 30 };

        System.out.println("Podaj resztę do wydania:");
        System.out.print("ZŁOTYCH -> ");
        zl = scanner.nextInt();
        System.out.print("GROSZY -> ");
        gr = scanner.nextInt();

        reszta = zl * 100 + gr;

        while(reszta > 0){
            if (i<M.length) {
                if (C[0] == 0 && C[1] == 0 && C[2] == 0 && C[3] == 0 && C[4] == 0 && C[5] == 0 && C[6] == 0 && C[7] == 0 && C[8] == 0) {
                    System.out.println("\n#########################\n# Brak monet, nie można wydać!");
                    reszta = 0;
                } else {
                    if (reszta >= M[i] && C[i] != 0) {
                        System.out.print(M[i] / 100.00 + "\t");
                        reszta = reszta - M[i];
                        C[i]--;
                    } else i++;
                }
            }
        }

        System.out.print("\n\n# Pozostałe monety ->");
        System.out.print("5zł->" + C[0] + "\t2zł->" + C[1] + "\t1zł->" + C[2] + "\t 0,50zł->" + C[3] + "\t0,20zł->" + C[4] + "\t0,10zł->" + C[5] + "\t0,05zł->" + C[6] + "\t0,02zł->" + C[7] + "\t0,01zł->" + C[8]);
    }
}

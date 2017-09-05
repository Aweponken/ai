/* 
 * Created by Arvid Viderberg
 */

import java.util.Scanner;
import java.util.Locale;
 
class main{

    public static void main (String[] args){
        /* Read matrix */
        Matrix m = new Matrix();
        double[][] transM = m.readMatrix();
        double[][] emissM = m.readMatrix();
        double[][] initialState = m.readMatrix();

        /* Get emission probability distribution */
        double[][] probDist = m.multiplyByMatrix(initialState, m.multiplyByMatrix(transM, emissM));
        
        /* Outprint */
        System.out.print(probDist.length + " " + probDist[0].length + " ");
        for(int i = 0; i < probDist.length; i++){
            for(int j = 0; j < probDist[i].length; j++)
                System.out.print(probDist[i][j] + " ");
        }
        System.out.println();
    }
}
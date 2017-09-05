/* 
 * Created by Arvid Viderberg
 */

import java.util.Scanner;
import java.util.Locale;
 
class Matrix{
    
    Scanner sc = new Scanner(System.in).useLocale(Locale.US);;
    
    public Matrix(){}

    public double[][] readMatrix(){
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        double[][] matrix = new double[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++)
                matrix[i][j] = sc.nextDouble();
        }
        return matrix;
    }

    public double[][] multiplyByMatrix(double[][] firstarray, double[][] secondarray) {
        double [][] result = new double[firstarray.length][secondarray[0].length];
        
        for (int i = 0; i < firstarray.length; i++) { 
            for (int j = 0; j < secondarray[0].length; j++) { 
                for (int k = 0; k < firstarray[0].length; k++) { 
                    result[i][j] += firstarray[i][k] * secondarray[k][j];
                }
            }
        }
        return result;
    }
}
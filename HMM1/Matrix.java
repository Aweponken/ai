/* 
 * Created by Arvid Viderberg
 */

import java.util.Scanner;
import java.util.Locale;
 
class Matrix{
    
    private Scanner sc = new Scanner(System.in).useLocale(Locale.US);;
    
    public Matrix(){}

    public double[][] readMatrix(){
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        double[][] matrix = new double[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = sc.nextDouble();
                //System.out.print(matrix[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println();
        return matrix;
    }

    public int[] readSingleRowMatrix(){
        int cols = sc.nextInt();
        int[] matrix = new int[cols];
        for(int i = 0; i < cols; i++)
                matrix[i] = sc.nextInt();
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

    public double getProb(double[][] a, double[][] b, double[][] pi, int[] o){
        double[][] f = forwardAlg(a, b, pi, o);
        double prob = 0;
        for (int i = 0; i < f.length; i++) { 
            prob += f[i][f[i].length - 1]; 
        }
        return prob;
    }

    private double[][] forwardAlg(double[][] a, double[][] b, double[][] pi, int[] o) { 
        double[][] f = new double[a[0].length][o.length]; 
        for (int l = 0; l < f.length; l++) { 
            f[l][0] = pi[0][l] * b[l][o[0]]; 
        } 
        for (int i = 1; i < o.length; i++) { 
            for (int k = 0; k < f.length; k++) { 
                double sum = 0; 
                for (int l = 0; l < a[0].length; l++) { 
                    sum += f[l][i-1] * a[l][k]; 
                } 
                f[k][i] = sum * b[k][o[i]]; 
            } 
        } 
        return f;
    }
}
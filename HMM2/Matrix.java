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

    public int[] viterbi(int[] obs, int[] states, double[] initialState, double[][] a, double[][] b)
    {
        double[][] V = new double[obs.length][states.length];
        int[][] path = new int[states.length][obs.length];

        for (int y : states){
            V[0][y] = initialState[y] * b[y][obs[0]];
            path[y][0] = y;
        }

        for (int t = 1; t < obs.length; ++t){
            int[][] newpath = new int[states.length][obs.length];

            for (int y : states){
                double prob = -1;
                int state;
                for (int y0 : states){
                    double nprob = V[t - 1][y0] * a[y0][y] * b[y][obs[t]];
                    if (nprob > prob){
                        prob = nprob;
                        state = y0;
                        // Note max probability
                        V[t][y] = prob;
                        // Record the map
                        System.arraycopy(path[state], 0, newpath[y], 0, t);
                        newpath[y][t] = y;
                    }
                }
            }
            path = newpath;
        }

        double prob = -1;
        int state = 0;
        for (int y : states){
            if (V[obs.length - 1][y] > prob){
                prob = V[obs.length - 1][y];
                state = y;
            }
        }
        return path[state];
    }
}




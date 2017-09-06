/* 
 * Created by Arvid Viderberg
 */

class main{

    public static void main (String[] args){
        /* Read matrix */
        Matrix m = new Matrix();
        double[][] transM = m.readMatrix();
        double[][] emissM = m.readMatrix();
        double[][] initialState = m.readMatrix();
        int[] sequence = m.readSingleRowMatrix();

        /* Probability of the given sequence as a single scalar */
        double probSeq = m.getProb(transM, emissM, initialState, sequence);

        /* Outprint */
        System.out.println(probSeq);
    }
}
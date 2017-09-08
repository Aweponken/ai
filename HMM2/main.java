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

        int[] states = new int[transM.length];
        for(int i = 0; i < states.length; i++)
            states[i] = i;

        /* Most probable sequence of states as zero-based indices */
        int [] ret = m.viterbi(sequence, states, initialState[0], transM, emissM);

        /* Outprint */
        for(int i = 0; i < ret.length; i++)
            System.out.print(ret[i] + " ");
    }
}
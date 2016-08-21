/*
ID: ichangs1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {

  public static void main (String [] args) throws IOException {
      int type = 7;
      // Use BufferedReader rather than RandomAccessFile; it's much faster
      BufferedReader f = new BufferedReader(new FileReader("transform.in"));
      // input file name goes above
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
      int dimensions = Integer.parseInt(f.readLine());
      char[][] matrix = new char[dimensions][dimensions];
      char[][] tranmatrix = new char[dimensions][dimensions];

      for (int i = 0; i < dimensions; i++) {
          String nextLine = f.readLine();
          for (int j = 0; j < dimensions; j++) {
              matrix[i][j] = nextLine.charAt(j);
          }
      }

      for (int i = 0; i < dimensions; i++) {
          String nextLine = f.readLine();
          for (int j = 0; j < dimensions; j++) {
              tranmatrix[i][j] = nextLine.charAt(j);
          }
      }
      
      if(Arrays.deepEquals(rotate(matrix), tranmatrix)) // WHY else if? Because we are determining the LEAST possible transformation. In some cases, the 180 degree rotation (which is less steps) will be equal to the reflection + 180 degree rotation, which is unwanted, so we choose the less steps first.
        type = 1;
      else if(Arrays.deepEquals(rotate(rotate(matrix)), tranmatrix))
        type = 2;
      else if(Arrays.deepEquals(rotate(rotate(rotate(matrix))), tranmatrix))
        type = 3;
      else if(Arrays.deepEquals(reflect(matrix), tranmatrix))
        type = 4;
      else if(Arrays.deepEquals(rotate(reflect(matrix)), tranmatrix) || Arrays.deepEquals(rotate(rotate(reflect(matrix))), tranmatrix) || Arrays.deepEquals(rotate(rotate(rotate(reflect(matrix)))), tranmatrix))
        type = 5;
      else if(Arrays.deepEquals(matrix, tranmatrix))
        type = 6;
        
    //System.out.println(Arrays.deepToString(rotate(rotate(matrix))); // Print matrix syntax
    //System.out.println(Arrays.deepToString(tranmatrix)); // Print matrix syntax
        
    out.println(type);                           // output result
    out.close();                                  // close the output file
    }

    static char[][] rotate(char[][] matrix){ //WARNING: do matrix.length - 1, as matrix.length returns the ACTUAL VALUE NOT starting from 0!
        char[][] rotate = new char[matrix.length][matrix.length];
        for( int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate.length; j++){
                rotate[i][j] = matrix[matrix.length - 1 - j][i]; // To determine this, I literally drew out matrices with indexes as coordinates, and determined this formula out of observation and trial-and-error
            }
        }

        return rotate;
    }

    static char[][] reflect(char[][] matrix){
        char[][] reflect = new char[matrix.length][matrix.length];
        for( int i = 0; i < reflect.length; i++ ){
            for( int j = 0; j < reflect.length; j++){
                reflect[i][j] = matrix[i][reflect.length - 1 - j];

            }
        }
        return reflect;
    }
}

package matrix;
import java.util.Random;

public class Matrix {
	private int matrix_rows=2;
	private int matrix_columns=2;
	public int maximum=100, minimum=0;
	
	public int data[][];
	
	public Matrix(){		
		data = new int[matrix_rows][matrix_columns];	
	}
	
	public Matrix(int[][] AnyArray){		
		data = AnyArray;	
	}
	
	public Matrix(int row, int col){
		matrix_rows = row;
		matrix_columns = col;
		
		data = new int[matrix_rows][matrix_columns];

		for(int i=0; i<matrix_rows; i++){
			for(int j=0; j<matrix_columns; j++){
				data[i][j] = random_Number();
			}
		}	
	}
	
	public void initialize_to_zero(){
		data = new int[matrix_rows][matrix_columns];

		for(int i=0; i<matrix_rows; i++){
			for(int j=0; j<matrix_columns; j++){
				data[i][j] = 0;
			}
		}
	}
	
	private int random_Number(){
		Random randomNum = new Random();
		int random_Number = randomNum.nextInt((maximum-minimum)+1)+ minimum;
		return random_Number;
	}
	
	public Matrix iteration(Matrix another){
		int n = matrix_rows, m = matrix_columns, p = another.matrix_columns;
		Matrix result = new Matrix(m,p);
		result.initialize_to_zero();
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				for(int k=0; k<m; k++){
					result.data[i][j] += data[i][k]*another.data[k][j];
				}
			}
		}
		
		return result;
	}
	
	public Matrix Strassen(int[][] A, int[][] B)
    {        
        int n = A.length;
        int[][] R = new int[n][n];
        
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        else
        {
            int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];
 
            /** Dividing matrix A into 4 halves **/
            slpiting(A, A11, 0 , 0);
            slpiting(A, A12, 0 , n/2);
            slpiting(A, A21, n/2, 0);
            slpiting(A, A22, n/2, n/2);
            /** Dividing matrix B into 4 halves **/
            slpiting(B, B11, 0 , 0);
            slpiting(B, B12, 0 , n/2);
            slpiting(B, B21, n/2, 0);
            slpiting(B, B22, n/2, n/2);
 
            int [][] M1 = Strassen(addition(A11, A22), addition(B11, B22)).data;
            int [][] M2 = Strassen(addition(A21, A22), B11).data;
            int [][] M3 = Strassen(A11, subtraction(B12, B22)).data;
            int [][] M4 = Strassen(A22, subtraction(B21, B11)).data;
            int [][] M5 = Strassen(addition(A11, A12), B22).data;
            int [][] M6 = Strassen(subtraction(A21, A11), addition(B11, B12)).data;
            int [][] M7 = Strassen(subtraction(A12, A22), addition(B21, B22)).data;
 
            int [][] C11 = addition(subtraction(addition(M1, M4), M5), M7);
            int [][] C12 = addition(M3, M5);
            int [][] C21 = addition(M2, M4);
            int [][] C22 = addition(subtraction(addition(M1, M3), M2), M6);
 
            combine(C11, R, 0 , 0);
            combine(C12, R, 0 , n/2);
            combine(C21, R, n/2, 0);
            combine(C22, R, n/2, n/2);
        }
    
        Matrix result = new Matrix(R);
        return result;
    }

	public int[][] addition(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
	
	public int[][] subtraction(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    public void slpiting(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
    
    public void combine(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    } 
	
	public void print(){
		System.out.println("[");
		for(int i=0; i<matrix_rows; i++){
			for(int j=0; j<matrix_columns; j++){
				System.out.print(data[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args){
		Matrix A = new Matrix(2,2);
		Matrix B = new Matrix(2,2);
		
		A.print();
		System.out.println("\n");
		B.print();
		System.out.println("\n");
		
		Matrix C = A.iteration(B);
		C.print();
		
		Matrix D = new Matrix(2,2);
		D = D.Strassen(A.data, B.data);
		D.print();
		
		
	}
}

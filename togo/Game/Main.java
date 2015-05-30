import java.util.Scanner;

public class Main {
	int m, n;
	int value[][] = null;
	long w[][] = null;
	static Main snake = new Main();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		snake.input();
		long result = snake.solutionSnake();
		System.out.println(result);
	}
	// [c][r] 格式
	public long solutionSnake() {
		// init first colum optimalWay
		if(m==0||n==0)
			return 0;
		columSearch(0);
		for(int c=1; c<m; c++){
			if(!columSearch(c))
				return 0;
		}
		long max = Integer.MIN_VALUE;
		for(int row=0; row<n; row++){
			max = Math.max(w[0][row], max);
		}
		return max;
	}
	private boolean columSearch(int c){
		boolean isOK = false;
		for(int r=0; r<n; r++){
			fromLeft(c, r);
		}
		for(int r=0; r<n; r++){
			fromRow(c, r, 2);
		}
		for(int r=n-1; r>=0; r--){
			fromRow(c, r, 3);
			w[0][r] = Math.max(w[1][r],Math.max(w[2][r], w[3][r]));
			if(w[0][r]!=-1) isOK = true;
		}
		return isOK;
	}
	private void fromLeft(int col, int row) {
		if (value[col][row] == -1) {
			w[1][row] = -1;
			w[2][row] = -1;
			w[3][row] = -1;
			w[0][row] = -1;
		} else {
			if(col==0){
				w[1][row] = value[col][row];
				w[2][row] = 0;
				w[3][row] = 0;
				return ;
			}
			// 来自col-1 的最大值
			if (w[0][row] != -1)
				w[1][row] = value[col][row] + w[0][row];
			else {
				w[1][row] = -1;
			}
		}
	}
	//edge == n-1 or 0
	private void fromRow(int col, int row, int dir) {
		if(value[col][row]==-1)return;
		int edge = n-1, alpha=1, oppo = 0;
		if(dir==2){edge = 0; alpha =-1; oppo = n-1;}
		if (row == edge) {
			if(-1 != value[col][oppo])
				w[dir][row] = value[col][row];
		} 
		else  {
			long preValue = Math.max(w[dir][row + alpha], w[1][row + alpha]);
			if (preValue != -1) {
				w[dir][row] = preValue + value[col][row];
			}
			else {
				w[dir][row] = -1;
			}
		}
	}
	public void input() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		value = new int[m][n];
		w = new long[4][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				// 行和列改变
				value[j][i] = scan.nextInt();
		}
		scan.close();
	}
}

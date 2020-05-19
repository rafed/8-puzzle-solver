
public class Node implements Comparable<Node>{

	int matrix[][], size;
	Node parent, child;
	
	private int moves;
	
	public Node(int[][] matrix, int moves, Node parent){
		this.matrix = matrix;
		this.moves = moves;
		this.parent = parent;
		this.size = matrix.length;
	}
	
	public boolean isGoal(){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix.length; j++){
				if(i== matrix.length-1 && j==matrix.length-1){
					if(matrix[i][j] != 0)
						return false;
				}
				
				else if(matrix[i][j] != i*matrix.length + j+1){
					return false;
				}
			}
		}
		return true;
	}
	
	private int h(){
		int value = 0;
		
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				
				int number = i*size+ j+1;
				
				if(matrix[i][j] != number){
					for(int m=0; m<size; m++){
						for(int n=0; n<size; n++){
							if(matrix[m][n] == number){
								value += Math.abs(m-i) + Math.abs(n-j);
							}
						}
					}
				}
			}
		}
		
		return value;
	}
	
	public int g(){
		return moves;
	}
	
	public int f(){ 	//heuristic function
		return g() + h();
	}

	public int compareTo(Node n) {
		return Integer.compare(this.f(), n.f());
	}
}

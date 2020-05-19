import java.util.PriorityQueue;

public class Solver {

	private int size;
	int[][] matrix;
	
	public Solver(int[][] matrix){
		this.size = matrix.length;
		this.matrix = matrix;
	}
	
	public boolean isSolveable(){
		int inversions = 0;
		
		int arr[] = new int[size * size];
		
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				arr[i * size + j] = matrix[i][j];
			}
		}
		
		for(int i=0; i<arr.length-1; i++){
			for(int j=i+1; j<arr.length; j++){
				if(arr[i] != 0 && arr[j] != 0){
					if(arr[i] > arr[j])
						inversions++;
				}
			}
		}
		
		if(inversions % 2 == 1)
			return false;
		
		System.out.println("It is solveable!!");
		return true;
	}
	
	public Node solve(){
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		
		queue.add(new Node(matrix, 0, null));
		
		Node solvedNode = null;
		
		while(!queue.isEmpty()){
			Node currentNode = queue.peek();
			queue.remove();
			
			if(currentNode.isGoal()){
				System.out.println("solved in " + currentNode.g() + " move!!");
				solvedNode = currentNode;
				break;
			}
			
			// search for the zero
			int i = 0 , j = 0;
			boolean found = false;
			
			for(i=0; i<size; i++){
				for(j=0; j<size; j++){
					if(currentNode.matrix[i][j] == 0){
						found = true;
						break;
					}
				}
				if(found)
					break;
			}
			
			if(i != 0){
				int[][] tempMatrix = copyArray(currentNode.matrix);
				tempMatrix[i][j] = tempMatrix[i-1][j];
				tempMatrix[i-1][j] = 0;
				
				Node newNode = new Node(tempMatrix, currentNode.g()+1, currentNode);
				queue.add(newNode);
			}
			
			if(i != size-1){
				int[][] tempMatrix = copyArray(currentNode.matrix);
				tempMatrix[i][j] = tempMatrix[i+1][j];
				tempMatrix[i+1][j] = 0;
				
				Node newNode = new Node(tempMatrix, currentNode.g()+1, currentNode);
				queue.add(newNode);
			}
			
			if(j != 0){
				int[][] tempMatrix = copyArray(currentNode.matrix);
				tempMatrix[i][j] = tempMatrix[i][j-1];
				tempMatrix[i][j-1] = 0;
				
				Node newNode = new Node(tempMatrix, currentNode.g()+1, currentNode);
				queue.add(newNode);
			}
			
			if(j != size-1){
				int[][] tempMatrix = copyArray(currentNode.matrix);
				tempMatrix[i][j] = tempMatrix[i][j+1];
				tempMatrix[i][j+1] = 0;
				
				Node newNode = new Node(tempMatrix, currentNode.g()+1, currentNode);
				queue.add(newNode);
			}
		}
		
		return reverse(solvedNode);
	}
	
	private Node reverse(Node node){
		while(node.parent != null){
			node.parent.child = node;
			node = node.parent;
		}
		
		return node;
	}
	
	private int[][] copyArray(int[][] mat){
		int arr[][] = new int[size][];
		
		for(int i=0; i<size; i++){
			arr[i] = new int[size];
			
			for(int j=0; j<size; j++){
				arr[i][j] = mat[i][j];
			}
		}
		
		return arr;
	}
}

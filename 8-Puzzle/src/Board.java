import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Board extends JPanel {

	int grid;
	JButton arr[];
	Node startNode;

	public Board(Node node) {		
		this.startNode = node;
		this.grid = node.matrix.length;
		arr = new JButton[grid * grid];
		
		setLayout(new GridLayout(grid, grid));
				
		for(int i=0; i < grid; i++){
			for(int j=0; j < grid; j++){
				JButton btn = new JButton();
				arr[i * grid + j] = btn;
				add(btn);
			}
		}
		
		setSquares(node);
	}
	
	public void animate(){
		new Thread(new Runnable() {
			public void run() {
				Node node = startNode;
				
				while(node.child != null){
					node = node.child;
					setSquares(node);
					
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void step(){
		if(startNode.child == null)
			return;
		
		startNode = startNode.child;
		setSquares(startNode);
	}

	private void setSquares(Node node){
		for(int i=0; i < grid; i++){
			for(int j=0; j < grid; j++){
				int value = node.matrix[i][j];
				
				if(value == 0){
					arr[i * grid + j].setBackground(Color.GRAY);
					arr[i * grid + j].setText("");
				}
				else{
					arr[i * grid + j].setBackground((Color.WHITE));
					arr[i * grid + j].setText(Integer.toString(value));
				}
				
				arr[i * grid + j].setFont(new Font("Dialog", Font.BOLD, 24));
			}
		}
	}
}

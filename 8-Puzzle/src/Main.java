import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
/*		Sample inputs
		{{6, 4, 7},
		{2, 8, 0},
		{5, 1, 3}};
		
		{{3, 5, 2},
		{8, 7, 6},
		{0, 4, 1}};
		
		{{6, 5, 8},
		{7, 3, 4},
		{1, 0, 2}};
		
		{{8, 2, 0},
		{5, 1, 6},
		{3, 7, 4}};
 */
		
		int[][] input = {{8, 2, 0},
						{5, 1, 6},
						{3, 7, 4}};
		
		Solver solver = new Solver(input);
		
		if(!solver.isSolveable()){
			System.out.println("Sorry, this is not solveable.");
			System.exit(0);
		}
		
		Node startPoint = solver.solve();
		
		Board b = new Board(startPoint);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame f = new Frame(b);
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}

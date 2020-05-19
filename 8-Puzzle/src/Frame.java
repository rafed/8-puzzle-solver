
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// All gui stuff here :3
public class Frame extends JFrame {

	private JPanel contentPane;

	Board board;
	
	public Frame(Board board) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("8-puzzle");
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.animate();
			}
		});
		btnSolve.setBounds(236, 375, 117, 25);
		contentPane.add(btnSolve);
		
		JButton btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				board.step();
			}
		});
		btnStep.setBounds(64, 375, 117, 25);
		contentPane.add(btnStep);
		
		this.board = board;
		board.setBounds(25, 12, 381, 327);
		contentPane.add(board);
	}
}

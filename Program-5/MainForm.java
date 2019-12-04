/* ====================================================================
 * @author: Yujin Yoshimura
 * CMPS 4143 Contemporary Programming Language: Java-Python
 * Dr. Tina Johnson
 * Program 5
 * 
 * This program is a game called Knight's Journey.
 * The purpose of this game is to visit all the grids on the chess
 * board without visiting the same grid more than once, with the
 * movement of the knight in chess.
 * Available dimensions of the chess boards are from 4x4 to 8x8.
 * Player may choose any initial position to start with.
 * If a player moves the knight to the grid that has already been
 * visited, the player loses. If a player moves the knight to all the
 * grids without repeating, the player wins.
 * ================================================================= */

package knights_journey;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.net.URL;

public class MainForm extends JFrame {

	public static int size = 8;

	/**
	 * Launch the application.
	 */
	public static void Open(int s) {
		size = s;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					URL url = StartUp.class.getResource("/knight.png");
					knight = new ImageIcon(url);
					MainForm frame = new MainForm();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Resets the game.
	 */
	public void new_game() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j].unvisit();
			}
		}
		update();
		valid = true;
		count = 0;
		System.out.println("New Game");
	}
	
	/**
	 * Shows where the current location of the knight is.
	 */
	public void update() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (buttons[i][j].is_knight()) {
					buttons[i][j].setIcon(knight);
				}
				else {
					buttons[i][j].setIcon(null);
				}
			}
		}		
	}
	
	/**
	 * Visits selected grid if possible.
	 */
	public void visit(int r, int c) {
		if (buttons[r][c].is_visited()) {
			JOptionPane.showMessageDialog(this, "You Lose!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("You Lose! You had " + count + " moves.");
		}
		else {
			if (r - 2 >= 0 && c - 1 >= 0 && buttons[r - 2][c - 1].is_knight()) {
				valid = true;
				buttons[r - 2][c - 1].leave();
			}
			if (r - 2 >= 0 && c + 1 < size && buttons[r - 2][c + 1].is_knight()) {
				valid = true;
				buttons[r - 2][c + 1].leave();
			}
			if (r + 2 < size && c - 1 >= 0 && buttons[r + 2][c - 1].is_knight()) {
				valid = true;
				buttons[r + 2][c - 1].leave();
			}
			if (r + 2 < size && c + 1 < size && buttons[r + 2][c + 1].is_knight()) {
				valid = true;
				buttons[r + 2][c + 1].leave();
			}
			if (r - 1 >= 0 && c - 2 >= 0 && buttons[r - 1][c - 2].is_knight()) {
				valid = true;
				buttons[r - 1][c - 2].leave();
			}
			if (r - 1 >= 0 && c + 2 < size && buttons[r - 1][c + 2].is_knight()) {
				valid = true;
				buttons[r - 1][c + 2].leave();
			}
			if (r + 1 < size && c - 2 >= 0 && buttons[r + 1][c - 2].is_knight()) {
				valid = true;
				buttons[r + 1][c - 2].leave();
			}
			if (r + 1 < size && c + 2 < size && buttons[r + 1][c + 2].is_knight()) {
				valid = true;
				buttons[r + 1][c + 2].leave();
			}
			if (valid) {
				buttons[r][c].visit();
				valid = false;
				count++;
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid move!", "INVALID", JOptionPane.INFORMATION_MESSAGE);				
			}
		}
		update();
		if (count == size * size) {
			JOptionPane.showMessageDialog(this, "You Win!", "CONGRATULATIONS", JOptionPane.INFORMATION_MESSAGE);			
			System.out.println("You Win! You had " + count + " moves.");
		}
	}
	
	public void form() {
		
	}
	
	/**
	 * Create the frame.
	 */
	public MainForm() {
		
		setTitle("Knight's Journey");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, size * 90 + 10, size * 90 + 90);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblKnightsJourney = new JLabel("Knight's Journey");
		lblKnightsJourney.setHorizontalAlignment(SwingConstants.CENTER);
		lblKnightsJourney.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
		contentPane.add(lblKnightsJourney, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j] = new Grid(i, j);
				buttons[i][j].setPreferredSize(new Dimension(80, 80));
				if ((i + j) % 2 == 0) {
					buttons[i][j].setBackground(beige);
				}
				else {
					buttons[i][j].setBackground(brown);				
				}
				panel.add(buttons[i][j]);
				buttons[i][j].addActionListener(new Listener(i, j));
			}
		}
		
		new_game();
				
		JPanel panel_bottom = new JPanel();
		contentPane.add(panel_bottom, BorderLayout.SOUTH);
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new_game();
			}
		});
		panel_bottom.add(btnNewGame);
	}

	/**
	 * A class inherited from ActionListener that holds coordinate data.
	 */
	public class Listener implements ActionListener {
		Listener(int a, int b) {
			i = a;
			j = b;
		}
		public void actionPerformed(ActionEvent e) {
			visit(i, j);
		}
		private int i;
		private int j;
	};

	/**
	 * A class inherited from JButton that holds coordinate data.
	 */
	public class Grid extends JButton {
		
		Grid(int r, int c) {
			super("");
			visited = false;
			knight = false;
			row = r;
			col = c;
		}
		
		public void unvisit() {
			visited = false;
			knight = false;
		}

		public void visit() {
			visited = true;
			knight = true;
		}
		
		public void leave() {
			knight = false;
		}
		
		public Boolean is_visited() {
			return visited;
		}
		
		public Boolean is_knight() {
			return knight;
		}
		
		public int r() {
			return row;
		}
		
		public int c() {
			return col;
		}
		
		private Boolean visited;
		private Boolean knight;
		private int row;
		private int col;
	}
	
	private Boolean valid;
	private int count;
	private Grid[][] buttons = new Grid[size][size];
	private Color beige = new Color(255, 255, 204);
	private Color brown = new Color(204, 153, 102);	
	private static ImageIcon knight;
	private JPanel contentPane;

}
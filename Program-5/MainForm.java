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
import java.awt.Toolkit;
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

	private static int size = 8;
	private static Boolean showVisited = false;
	
	/**
	 * Launch the application.
	 */
	public static void Open(int s, Boolean v) {
		size = s;
		showVisited = v;
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
	public void newGame() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j].setEnabled(true);
				buttons[i][j].unvisit();
			}
		}
		update();
		valid = true;
		count = 0;
		System.out.println("New Game");
	}
	
	/**
	 * Visits selected grid if possible.
	 */
	private void visit(int r, int c) {
		seeKnightIn(r - 2, c - 1);
		seeKnightIn(r - 2, c + 1);
		seeKnightIn(r + 2, c - 1);
		seeKnightIn(r + 2, c + 1);
		seeKnightIn(r - 1, c - 2);
		seeKnightIn(r - 1, c + 2);
		seeKnightIn(r + 1, c - 2);
		seeKnightIn(r + 1, c + 2);
		if (!valid) {
			Toolkit.getDefaultToolkit().beep();			
		}
		else if (buttons[r][c].is_visited()) {
			lose();
		}
		else {
			buttons[r][c].visit();
			valid = false;
			count++;
			update();
			if (count == size * size) {
				win();
			}
		}
	}
	
	/**
	 * see whether the knight is in a given grid.
	 */
	private void seeKnightIn(int r, int c) {
		if (r >= 0 && c >= 0 && r < size && c < size && buttons[r][c].is_knight()) {
			valid = true;
			buttons[r][c].leave();
		}
	}

	/**
	 * Shows where the current location of the knight is.
	 */
	private void update() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (buttons[i][j].is_knight()) {
					buttons[i][j].setIcon(knight);
				}
				else {
					buttons[i][j].setIcon(null);
				}
				if (showVisited) {
					if (buttons[i][j].is_visited() && !buttons[i][j].is_knight()) {
						buttons[i][j].setBackground(gray);					
					}
					else {
						if ((i + j) % 2 == 0) {
							buttons[i][j].setBackground(beige);
						}
						else {
							buttons[i][j].setBackground(brown);				
						}
					}
				}
			}
		}		
	}

	/**
	 * Ends the game with lose.
	 */
	private void lose() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, "You Lose!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("You Lose! You had " + count + " moves.");
		gameOver();
	}
	
	/**
	 * Ends the game with win.
	 */
	private void win() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, "You Win!", "CONGRATULATIONS", JOptionPane.INFORMATION_MESSAGE);			
		System.out.println("You Win! You had " + count + " moves.");
		gameOver();		
	}
	
	/**
	 * Ends the game.
	 */
	private void gameOver() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j].setEnabled(false);
			}
		}		
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
		
		newGame();
				
		JPanel panel_bottom = new JPanel();
		contentPane.add(panel_bottom, BorderLayout.SOUTH);
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Go back to home");				
				dispose();
			}
		});
		panel_bottom.add(btnNewGame);		
		panel_bottom.add(btnHome);
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
	private Color gray = new Color(204, 204, 204);
	private JPanel contentPane;
	private static ImageIcon knight;

}
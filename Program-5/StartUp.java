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
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.net.URL;

public class StartUp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					URL url = StartUp.class.getResource("/knight.png");
					ImageIcon knight = new ImageIcon(url);
					StartUp frame = new StartUp();
					frame.add(new JLabel(knight));
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
	 * Create the frame.
	 */
	public StartUp() {
		setTitle("Knight's Journey");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 240);
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
		String[] comboData = {"4", "5", "6", "7", "8"};
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_south = new JPanel();
		contentPane.add(panel_south, BorderLayout.SOUTH);
		panel_south.setPreferredSize(new Dimension(200, 64));
		
		JLabel lblDimension = new JLabel("Dimension:");
		comboBox = new JComboBox(comboData);		
		JButton btnNewGame = new JButton("New Game");
		panel_south.add(lblDimension);
		panel_south.add(comboBox);
		panel_south.add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size = Integer.parseInt((String)comboBox.getSelectedItem());
		        form = new MainForm();
		        form.Open(size);
		        System.out.println("Starts the " + size + "x" + size + " game.");
			}
		});
	}
	
	private MainForm form;
	private JComboBox comboBox;
}

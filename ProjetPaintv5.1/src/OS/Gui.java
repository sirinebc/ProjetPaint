package OS;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class Gui extends JFrame{
	
	public static Character mode = 'N';
	public static Character shapemode = 'R';
	private String file_name = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Gui() {
		initialize();
	}

	private void initialize() {
		this.setBounds(100, 100,1100, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame ask_wind = new JFrame();
		JLabel ask_label = new JLabel("Please enter the path to the file ");
		JLabel ask_label2 = new JLabel("And the ip address of the server");
		JTextField answer = new JTextField();
		JButton done = new JButton("Done");
		JTextField ipaddr =  new JTextField();
		
		
		ask_wind.setLayout(null);
		ask_wind.setBounds(700, 300, 500, 200);
		ask_label.setBounds(10, 10, 400, 30);
		ask_wind.add(ask_label);
		ask_label2.setBounds(10, 40, 400, 30);
		ask_wind.add(ask_label2);
		answer.setBounds(10, 70, 400, 30);
		ask_wind.add(answer);
		done.setBounds(10, 130, 60, 30);
		ask_wind.add(done);
		ipaddr.setBounds(10, 100, 400, 30);
		ask_wind.add(ipaddr);
		ipaddr.setVisible(false);
		ask_label2.setVisible(false);
		
		JPanel panel_2 = new OS();
		this.getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(68, 69, 69));
		this.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(200,30));
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 'A';
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorderPainted(false);
		panel.add(btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_2 = new JButton("Select");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 'S';
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBorderPainted(false);
		panel.add(btnNewButton_2);
		btnNewButton_2.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_1 = new JButton("Move");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 'M';
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBorderPainted(false);
		panel.add(btnNewButton_1);
		btnNewButton_1.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_5 = new JButton("Resize");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = 'D';
			}
		});
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setBorderPainted(false);
		panel.add(btnNewButton_5);
		btnNewButton_5.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_8 = new JButton("Save L");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						file_name = answer.getText();
						((OS) panel_2).save_local(file_name);
						ask_wind.setVisible(false);
					}
				});
				ask_wind.setVisible(true);
			}
		});
		btnNewButton_8.setForeground(new Color(255, 255, 255));
		btnNewButton_8.setBorderPainted(false);
		panel.add(btnNewButton_8);
		btnNewButton_8.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_9 = new JButton("Open L");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						file_name = answer.getText();
						((OS) panel_2).load_local(file_name);
						ask_wind.setVisible(false);
					}
				});
				ask_wind.setVisible(true);
			}
		});
		btnNewButton_9.setForeground(new Color(255, 255, 255));
		btnNewButton_9.setBorderPainted(false);
		panel.add(btnNewButton_9);
		btnNewButton_9.setPreferredSize(new Dimension(100,20));
		
		JButton button_1 = new JButton("Save S");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ipaddr.setVisible(true);
				ask_label2.setVisible(true);
				done.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						file_name = answer.getText();
						((OS) panel_2).save_server(file_name,ipaddr.getText());
						ask_wind.setVisible(false);
						ipaddr.setVisible(false);
						ask_label2.setVisible(false);
					}
				});
				ask_wind.setVisible(true);
			}
		});
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBorderPainted(false);
		panel.add(button_1);
		button_1.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_10 = new JButton("Open S");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ipaddr.setVisible(true);
				ask_label2.setVisible(true);
				done.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						file_name = answer.getText();
						((OS) panel_2).load_server(file_name,ipaddr.getText());
						ask_wind.setVisible(false);
						ipaddr.setVisible(false);
						ask_label2.setVisible(false);
					}
				});
				ask_wind.setVisible(true);
			}
		});
		btnNewButton_10.setForeground(new Color(255, 255, 255));
		btnNewButton_10.setBorderPainted(false);
		panel.add(btnNewButton_10);
		btnNewButton_10.setPreferredSize(new Dimension(100,20));
		
		JButton btnNewButton_11 = new JButton("Run");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server server;
				Registry registry;
				try {
					server = new Server();
					registry = LocateRegistry.createRegistry(1099);
					registry.rebind("ServerDrawing", server);
					System.out.println("Server is running...");
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_11.setForeground(new Color(255, 255, 255));
		btnNewButton_11.setBorderPainted(false);
		panel.add(btnNewButton_11);
		btnNewButton_11.setPreferredSize(new Dimension(100,20));
		
		
		JButton btnNewButton_12 = new JButton("New");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((OS) panel_2).clear_all();
			}
		});
		btnNewButton_12.setForeground(new Color(255, 255, 255));
		btnNewButton_12.setBorderPainted(false);
		panel.add(btnNewButton_12);
		btnNewButton_12.setPreferredSize(new Dimension(100,20));
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(68, 69, 69));
		this.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setPreferredSize(new Dimension(70,200));
		
		JButton btnNewButton_4 = new JButton("𔓘");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapemode = 'R';
			}
		});
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBorderPainted(false);
		panel_1.add(btnNewButton_4);
		btnNewButton_4.setPreferredSize(new Dimension(60,40));
		
		JButton btnNewButton_3 = new JButton("O");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapemode = 'C';
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBorderPainted(false);
		panel_1.add(btnNewButton_3);
		btnNewButton_3.setPreferredSize(new Dimension(60,40));
		
		JButton button = new JButton("✏");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapemode = 'P';
			}
		});
		button.setForeground(new Color(255, 255, 255));
		button.setBorderPainted(false);
		panel_1.add(button);
		button.setPreferredSize(new Dimension(60,40));
		
		
		JButton btnNewButton_6 = new JButton("U");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((OS) panel_2).union();
			}
		});
		btnNewButton_6.setForeground(new Color(255, 255, 255));
		btnNewButton_6.setBorderPainted(false);
		panel_1.add(btnNewButton_6);
		btnNewButton_6.setPreferredSize(new Dimension(60,40));
		
		JButton btnNewButton_7 = new JButton("∩");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((OS) panel_2).inter();
			}
		});
		btnNewButton_7.setForeground(new Color(255, 255, 255));
		btnNewButton_7.setBorderPainted(false);
		panel_1.add(btnNewButton_7);
		btnNewButton_7.setPreferredSize(new Dimension(60,40));
	}

}

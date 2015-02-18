package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;

public class Acerca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static void main(String[] args) {
		try {
			Acerca dialog = new Acerca();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Acerca() {
		setTitle("ACERCA DE");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 51, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setBounds(180, 219, 89, 32);
		contentPanel.add(btnCerrar);
		
		JLabel lblSogesproBeta = new JLabel("geSTOCK 1.1.1");
		lblSogesproBeta.setBackground(new Color(240, 240, 240));
		lblSogesproBeta.setForeground(new Color(255, 255, 255));
		lblSogesproBeta.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSogesproBeta.setBounds(112, 11, 230, 32);
		contentPanel.add(lblSogesproBeta);
		
		JTextArea txtrSogesproFinal = new JTextArea();
		txtrSogesproFinal.setBackground(new Color(0, 51, 51));
		txtrSogesproFinal.setForeground(new Color(255, 255, 255));
		txtrSogesproFinal.setText("GESTOCK 1.1.1\r\nSOFTWARE PARA LA GESTION DE INVENTARIOS\r\n\r\nDESARROLLADO EN JAVA\r\nHERRAMIENTAS ADICIONES USADAS:\r\n-ECLIPSE IDE\r\n-GESTOR DE BD SQLITE");
		txtrSogesproFinal.setEditable(false);
		txtrSogesproFinal.setBounds(10, 46, 414, 162);
		contentPanel.add(txtrSogesproFinal);
	}
}

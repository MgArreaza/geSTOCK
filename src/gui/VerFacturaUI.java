package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import bd.FacturaQuery;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerFacturaUI extends JInternalFrame {
	private JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerFacturaUI frame = new VerFacturaUI();
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
	public VerFacturaUI() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 650, 550);
		getContentPane().setLayout(null);
		
		JLabel lblFacturas = new JLabel("FACTURAS:");
		lblFacturas.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblFacturas.setBounds(10, 11, 227, 52);
		getContentPane().add(lblFacturas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 614, 378);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		JButton btnMostrarFacturas = new JButton("Mostrar Facturas");
		btnMostrarFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTabla();
			}
		});
		btnMostrarFacturas.setBounds(468, 11, 156, 35);
		getContentPane().add(btnMostrarFacturas);
		
		JButton btnMostrarVentas = new JButton("Mostrar Ventas");
		btnMostrarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaV();
			}
		});
		btnMostrarVentas.setBounds(345, 11, 113, 35);
		getContentPane().add(btnMostrarVentas);
		cargarTabla();
	}
	
	public void cargarTabla(){
		FacturaQuery FQ = new FacturaQuery();
		try{
			tabla.setModel(FQ.mostrar());
		}catch(Exception E){
			E.printStackTrace();
		}
	}
	
	public void cargarTablaV(){
		FacturaQuery FQ = new FacturaQuery();
		try{
			tabla.setModel(FQ.mostrarV());
		}catch(Exception E){
			E.printStackTrace();
		}
	}
}

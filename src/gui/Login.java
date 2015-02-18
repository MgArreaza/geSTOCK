package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import bd.ConexionSQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Trabajador;
import bd.TrabajadorQuery;

import javax.swing.UIManager;

import java.awt.SystemColor;

public class Login {

	private JFrame frmLogin;
	private JPasswordField cajaClave;
	private final JComboBox<String> cboUsuario = new JComboBox<String>();
	private ConexionSQL con = new ConexionSQL();

	public static void main(String[]args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("LOGIN");
		frmLogin.getContentPane().setBackground(new Color(51, 51, 153));
		frmLogin.setForeground(new Color(51, 102, 153));
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setForeground(Color.ORANGE);
		frmLogin.setBounds(100, 100, 500, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setIcon(new ImageIcon("Imagenes/attitude.png"));
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblLogin.setBounds(81, 11, 349, 121);
		frmLogin.getContentPane().add(lblLogin);
		
		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setForeground(SystemColor.text);
		lblUsuario.setBounds(70, 143, 82, 14);
		frmLogin.getContentPane().add(lblUsuario);
		
		JLabel lblClave = new JLabel("CLAVE:");
		lblClave.setForeground(SystemColor.text);
		lblClave.setBounds(70, 184, 82, 14);
		frmLogin.getContentPane().add(lblClave);
		
		cajaClave = new JPasswordField();
		cajaClave.setForeground(new Color(255, 255, 255));
		cajaClave.setBackground(new Color(51, 102, 153));
		cajaClave.setBounds(188, 179, 216, 25);
		frmLogin.getContentPane().add(cajaClave);
		
		
		cboUsuario.setBackground(new Color(51, 102, 153));
		cboUsuario.setBounds(188, 143, 216, 25);
		frmLogin.getContentPane().add(cboUsuario);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBackground(new Color(0, 128, 128));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					TrabajadorQuery TQ = new TrabajadorQuery();
					Trabajador T = new Trabajador();
					T.setUsuario(cboUsuario.getSelectedItem().toString());
					T.setClave(cajaClave.getText().toString());
					TQ.login(T);

					if(TQ.getContador()>0){
						if(T.getEstado()==2){
							JOptionPane.showMessageDialog(null, "TU CUENTA HA SIDO DESHABILITADA",null, JOptionPane.INFORMATION_MESSAGE);
							cajaClave.setText(null);
						}
						else{
							cajaClave.setText(null);
							frmLogin.dispose();
							Menu Principal = new Menu();
							Principal.mostrarEtiquetas(T.getNombre(), T.getApellido(), T.getRango());
						}
					}
					else{
						cajaClave.setText(null);
						JOptionPane.showMessageDialog(null, "Acceso Denegado","Acceso al Sistema",JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnEntrar.setBounds(304, 223, 110, 38);
		frmLogin.getContentPane().add(btnEntrar);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setForeground(new Color(0, 0, 0));
		btnSalir.setBackground(new Color(0, 128, 128));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLogin.dispose();
			}
		});
		btnSalir.setBounds(65, 223, 110, 38);
		frmLogin.getContentPane().add(btnSalir);
		
		cargarCombo(cboUsuario);
		frmLogin.setVisible(true);
	}
	
	private void cargarCombo(JComboBox cboUsuario){
		String consulta = "SELECT usuario FROM trabajador";
		con.conectar();
		try{
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while(con.rs.next()){
				cboUsuario.addItem(con.rs.getString("usuario"));
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
}

package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import java.awt.Rectangle;

import javax.swing.JTable;

import bd.ConexionSQL;
import bd.TrabajadorQuery;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Trabajador;

public class UsuarioUI extends JInternalFrame {
	ConexionSQL con = new ConexionSQL();
	private JTable tabla;
	private TrabajadorQuery TQ = new TrabajadorQuery();
	private Trabajador T = new Trabajador();
	private JTextField txfID;
	private JTextField txfNombre;
	private JTextField txfApellido;
	private JTextField txfEmail;
	private JTextField txfTelefono;
	private JTextField txfUsuario;
	private JTextField txfClave;
	private JLabel lblAgregarUsuario;
	private JButton btnAgregar = new JButton("AGREGAR");
	private JButton btnModificar = new JButton("MODIFICAR");
	private JButton btnEliminar = new JButton("ELIMINAR");
	private JComboBox<String> comboEstado = new JComboBox<String>();
	private JComboBox<String> cboRango = new JComboBox<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioUI frame = new UsuarioUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UsuarioUI() {
		setForeground(new Color(119, 136, 153));
		setMaximizable(false);
		setNormalBounds(new Rectangle(0, 0, 600, 500));
		setIconifiable(true);
		setTitle("USUARIOS");
		setClosable(true);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(12, 267, 766, 189);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		tabla.setFillsViewportHeight(true);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tabla.getSelectedRow();
				try{
					txfID.setText(tabla.getModel().getValueAt(row, 0).toString());
					txfUsuario.setText(tabla.getModel().getValueAt(row, 1).toString());
					txfNombre.setText(tabla.getModel().getValueAt(row, 2).toString());
					txfApellido.setText(tabla.getModel().getValueAt(row, 3).toString());
					txfEmail.setText(tabla.getModel().getValueAt(row, 4).toString());
					txfTelefono.setText(tabla.getModel().getValueAt(row, 5).toString());
					cboRango.setSelectedItem(tabla.getModel().getValueAt(row, 6));
					comboEstado.setSelectedItem(tabla.getModel().getValueAt(row, 7));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setViewportView(tabla);
		cargarTabla();
		setLblAgregarUsuario(new JLabel("AGREGAR USUARIO"));
		getLblAgregarUsuario().setBounds(19, 12, 141, 15);
		getContentPane().add(getLblAgregarUsuario());
		
		txfNombre = new JTextField();
		txfNombre.setBounds(100, 100, 225, 26);
		getContentPane().add(txfNombre);
		txfNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 105, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(12, 143, 70, 15);
		getContentPane().add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 181, 70, 15);
		getContentPane().add(lblEmail);
		
		txfApellido = new JTextField();
		txfApellido.setBounds(100, 138, 225, 26);
		getContentPane().add(txfApellido);
		txfApellido.setColumns(10);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(100, 176, 225, 26);
		getContentPane().add(txfEmail);
		txfEmail.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(12, 219, 70, 15);
		getContentPane().add(lblTelefono);
		
		txfTelefono = new JTextField();
		txfTelefono.setBounds(100, 214, 225, 26);
		getContentPane().add(txfTelefono);
		txfTelefono.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(450, 105, 70, 15);
		getContentPane().add(lblUsuario);
		
		txfUsuario = new JTextField();
		txfUsuario.setBounds(545, 101, 225, 25);
		getContentPane().add(txfUsuario);
		txfUsuario.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(450, 143, 70, 15);
		getContentPane().add(lblClave);
		
		txfClave = new JPasswordField();
		txfClave.setBounds(545, 138, 225, 25);
		getContentPane().add(txfClave);
		txfClave.setColumns(10);
		
		cboRango.setBounds(545, 177, 225, 24);
		getContentPane().add(cboRango);
		
		JLabel lblRango = new JLabel("Rango");
		lblRango.setBounds(450, 181, 70, 15);
		getContentPane().add(lblRango);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(450, 219, 70, 15);
		getContentPane().add(lblEstado);
		
		
		comboEstado.setBounds(545, 216, 225, 24);
		getContentPane().add(comboEstado);
		llenarCombo(cboRango,"Tipo","SELECT rango.Tipo FROM rango");
		llenarCombo(comboEstado,"Descripcion","SELECT estado.Descripcion FROM estado");
		
		JLabel lblID = new JLabel("Id:");
		lblID.setBounds(450, 69, 60, 15);
		getContentPane().add(lblID);
		
		txfID = new JTextField();
		txfID.setBounds(545, 64, 225, 25);
		getContentPane().add(txfID);
		txfID.setColumns(10);
		JButton btnAT = new JButton(new ImageIcon("Imagenes/recarga.png"));
		btnAT.setBounds(738, 12, 36, 36);
		getContentPane().add(btnAT);
		
		btnAT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try{
						cargarTabla();
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}
		});

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txfUsuario.getText().length()==0 || txfNombre.getText().length()==0 || txfApellido.getText().length()==0 || txfEmail.getText().length()==0 || txfTelefono.getText().length()==0 || txfClave.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Dejaste Campos Vacios");
				}
				else{
					try{
						T.setUsuario(txfUsuario.getText());
						T.setNombre(txfNombre.getText());;
						T.setApellido(txfApellido.getText());;
						T.setEmail(txfEmail.getText());
						T.setTelefono(txfTelefono.getText());
						T.setClave(txfClave.getText().toString());
						T.setRango((cboRango.getSelectedIndex()+1));
						T.setEstado((comboEstado.getSelectedIndex()+1));
						if(!TQ.verificar(T.getUsuario())){
							TQ.insertar(T);
							cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null, "EL USUARIO YA EXISTE");
						}
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}
			}
		});
		
		btnModificar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(txfID.getText().length()==0 || txfUsuario.getText().length()==0 || txfNombre.getText().length()==0 || txfApellido.getText().length()==0 || txfEmail.getText().length()==0 || txfTelefono.getText().length()==0 || txfClave.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Dejaste Campos Vacios");
				}
				else{
					try{
						T.setIdpersona(Integer.parseInt(txfID.getText()));
						T.setUsuario(txfUsuario.getText());
						T.setNombre(txfNombre.getText());;
						T.setApellido(txfApellido.getText());;
						T.setEmail(txfEmail.getText());
						T.setTelefono(txfTelefono.getText());
						T.setClave(txfClave.getText().toString());
						T.setRango((cboRango.getSelectedIndex()+1));
						T.setEstado((comboEstado.getSelectedIndex()+1));
						if(TQ.verificar(T.getUsuario())){
							TQ.MOD(T);
							cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null, "EL USUARIO NO EXISTE");
						}
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(txfID.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Dejaste Campos Vacios");
				}
				else{
					try{
						T.setIdpersona(Integer.parseInt(txfID.getText()));
						T.setUsuario(txfUsuario.getText());
						T.setNombre(txfNombre.getText());;
						T.setApellido(txfApellido.getText());;
						T.setEmail(txfEmail.getText());
						T.setTelefono(txfTelefono.getText());
						T.setClave(txfClave.getText().toString());
						T.setRango((cboRango.getSelectedIndex()+1));
						T.setEstado((comboEstado.getSelectedIndex()+1));
						if(TQ.verificar(T.getUsuario())){
							TQ.del(T);
							cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null, "EL USUARIO NO EXISTE");
						}
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}
			}
		});
		
	}
	
	private void cargarTabla(){
		try{
			tabla.setModel(TQ.mostrar());	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void bAgregar(){
		btnAgregar.setBounds(12, 43, 117, 45);
		getContentPane().add(btnAgregar);
	}
	
	public void bMOD(){
		btnModificar.setBounds(149, 43, 134, 45);
		getContentPane().add(btnModificar);
	}

	public void bBorrar(){
		btnEliminar.setBounds(306, 43, 117, 45);
		getContentPane().add(btnEliminar);
	}
	
	public void DisableID(){
		txfID.setEnabled(false);
	}
	
	public void EnableID(){
		txfID.setEnabled(true);
	}
	
	public void habilitar(){
		txfNombre.setEnabled(true);
		txfApellido.setEnabled(true);
		txfEmail.setEnabled(true);
		txfTelefono.setEnabled(true);
		txfUsuario.setEnabled(true);
		txfClave.setEnabled(true);
		txfID.setEnabled(true);
	}
	
	public void inhabilitar(){
		txfNombre.setEnabled(false);
		txfApellido.setEnabled(false);
		txfEmail.setEnabled(false);
		txfTelefono.setEnabled(false);
		txfUsuario.setEnabled(false);
		txfClave.setEnabled(false);
		txfID.setEnabled(false);
	}
	
	private void llenarCombo(JComboBox<String> combo,String campo,String consulta){
		ConexionSQL con = new ConexionSQL();
		con.conectar();
		try{
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while(con.rs.next()){
				combo.addItem(con.rs.getString(campo));
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public JLabel getLblAgregarUsuario() {
		return lblAgregarUsuario;
	}

	public void setLblAgregarUsuario(JLabel lblAgregarUsuario) {
		this.lblAgregarUsuario = lblAgregarUsuario;
	}
}

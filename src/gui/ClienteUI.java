package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bd.ClienteQuery;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import logica.Cliente;

public class ClienteUI extends JInternalFrame {

	private ClienteQuery CQ = new ClienteQuery();
	private JTable tabla;
	private JTextField tfCedula = new JTextField();
	private JTextField tfNombre = new JTextField();
	private JTextField tfApellido = new JTextField();
	private JTextField tfEdad = new JTextField();
	private JTextField tfCorreo = new JTextField();
	private JTextField tfTelefono = new JTextField();
	private JTextField tfDir = new JTextField();
	private JButton btnEliminar = new JButton("Eliminar");
	private JButton btnAgregar = new JButton("Agregar");
	private JButton btnModificar = new JButton("Modificar");
	private JComboBox<String> comboSexo = new JComboBox<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteUI frame = new ClienteUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClienteUI() {
		setTitle("CLIENTES");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 764, 286);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = tabla.getSelectedRow();
				tfCedula.setText(tabla.getModel().getValueAt(fila, 0).toString());
				tfNombre.setText(tabla.getModel().getValueAt(fila, 1).toString());
				tfApellido.setText(tabla.getModel().getValueAt(fila, 2).toString());
				tfEdad.setText(tabla.getModel().getValueAt(fila, 3).toString());
				tfCorreo.setText(tabla.getModel().getValueAt(fila, 5).toString());
				tfTelefono.setText(tabla.getModel().getValueAt(fila, 6).toString());
				tfDir.setText(tabla.getModel().getValueAt(fila, 7).toString());	
				comboSexo.setSelectedItem(String.valueOf(tabla.getModel().getValueAt(fila, 4)));
			}
		});
		scrollPane.setViewportView(tabla);
		
		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblClientes.setBounds(10, 11, 220, 38);
		getContentPane().add(lblClientes);
		
		JLabel lblRellenaLosCorrespondientes = new JLabel("Rellena los Correspondientes Campos");
		lblRellenaLosCorrespondientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRellenaLosCorrespondientes.setBounds(10, 60, 250, 14);
		getContentPane().add(lblRellenaLosCorrespondientes);
		
		JButton btnRecargar = new JButton(new ImageIcon("Imagenes/recarga.png"));
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					cargarTabla();
				}catch(Exception E){
					E.printStackTrace();
				}
			}
		});
		btnRecargar.setBounds(741, 11, 36, 34);
		getContentPane().add(btnRecargar);
		
		tfCedula = new JTextField();
		tfCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				if (Character.isAlphabetic(e.getKeyChar())) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		tfCedula.setBounds(76, 103, 250, 20);
		getContentPane().add(tfCedula);
		tfCedula.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(10, 105, 56, 17);
		getContentPane().add(lblCedula);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 137, 56, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 168, 46, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 197, 46, 14);
		getContentPane().add(lblEdad);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(76, 134, 250, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(76, 165, 250, 20);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		tfEdad = new JTextField();
		tfEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				if (Character.isAlphabetic(e.getKeyChar())) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		tfEdad.setBounds(76, 193, 250, 20);
		getContentPane().add(tfEdad);
		tfEdad.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(417, 106, 56, 14);
		getContentPane().add(lblSexo);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(417, 137, 56, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(417, 168, 56, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(417, 197, 56, 14);
		getContentPane().add(lblDireccion);
		
		comboSexo.setBounds(483, 103, 250, 20);
		getContentPane().add(comboSexo);
		
		tfCorreo.setBounds(483, 134, 250, 20);
		getContentPane().add(tfCorreo);
		tfCorreo.setColumns(10);
		
		tfTelefono.setBounds(483, 165, 250, 20);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);
		
		tfDir.setBounds(483, 193, 250, 20);
		getContentPane().add(tfDir);
		tfDir.setColumns(10);
		
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				if(tfCedula.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la Cedula");
				}
				else if(Integer.parseInt(tfEdad.getText())<1){
					JOptionPane.showMessageDialog(null,"La Cedula/Codigo debe ser mayor a 0");
				}
				else{
					Cliente C = new Cliente();
					C.setIdpersona(Integer.parseInt(tfCedula.getText()));
					int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMINAR EL CLIENTE N# "+C.getIdpersona()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
						if(CQ.verificar(C.getIdpersona())){
							CQ.borrar(C);
							cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null,"EL CLIENTE NO EXISTE");
						}
						
					}
					else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL CLIENTE NO SE ELIMINO");}
					else {JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE LOGRO ELIMINAR.");}
				}
			}
		});

		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Nombre del Cliente");
				}
				else if(tfDir.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la Direccion del Cliente");
				}
				else if(tfApellido.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Apellido del Cliente");
				}
				else if(tfCedula.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Selecciona un Cliente de la Tabla");				
				}
				else if(Integer.parseInt(tfCedula.getText())<1){
					JOptionPane.showMessageDialog(null,"La Cedula debe ser mayor a 0");
				}
				else if(tfTelefono.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Telefono del Cliente");				
				}
				else if(tfCorreo.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Correo del Cliente");				
				}
				else if(tfEdad.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la Eda  del Cliente");				
				}
				else if(Integer.parseInt(tfEdad.getText())<1){
					JOptionPane.showMessageDialog(null,"La Edad debe ser mayor a 0");
				}
				else{
					int dia=0,mes=0,year=0;
					Calendar cal = Calendar.getInstance();
					dia = cal.get(Calendar.DAY_OF_MONTH);
					mes = cal.get(Calendar.MONTH);
					year = cal.get(Calendar.YEAR);
					String fecha = dia+"/"+mes+"/"+year;
					Cliente c = new Cliente();
					c.setFechaAgregado(fecha);
					c.setNombre(tfNombre.getText());
					c.setApellido(tfApellido.getText());
					c.setIdpersona(Integer.parseInt(tfCedula.getText()));
					c.setDir(tfDir.getText());
					c.setEdad(Integer.parseInt(tfEdad.getText()));
					c.setEmail(tfCorreo.getText());
					c.setTelefono(tfTelefono.getText());
					c.setSexo(comboSexo.getSelectedIndex()+1);
					int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA EDITAR EL CLIENTE "+c.getNombre()+" "+c.getApellido()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
							if(CQ.verificar(c.getIdpersona())){
								CQ.actualizar(c);cargarTabla();
							}
							else{
								JOptionPane.showMessageDialog(null, "EL CLIENTE INDICADO NO EXISTE");
							}
					}
					else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL CLIENTE NO SE EDITO");}
					else {JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE EDITO.");}
				  }
			}
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent g) {
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Nombre del Cliente");
				}
				else if(tfDir.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la Direccion del Cliente");
				}
				else if(tfApellido.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Apellido del Cliente");
				}
				else if(tfCedula.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la Cedula o Codigo del Cliente");				
				}
				else if(Integer.parseInt(tfCedula.getText())<1){
					JOptionPane.showMessageDialog(null,"La Cedula/Codigo debe ser mayor a 0");
				}
				else if(tfTelefono.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Telefono del Cliente");				
				}
				else if(tfCorreo.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Correo del Cliente");				
				}
				else if(tfEdad.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la Eda  del Cliente");				
				}
				else if(Integer.parseInt(tfEdad.getText())<1){
					JOptionPane.showMessageDialog(null,"La Edad debe ser mayor a 0");
				}
				else{
					int dia=0,mes=0,year=0;
					Calendar cal = Calendar.getInstance();
					dia = cal.get(Calendar.DAY_OF_MONTH);
					mes = cal.get(Calendar.MONTH);
					year = cal.get(Calendar.YEAR);
					String fecha = dia+"/"+mes+"/"+year;
					Cliente c = new Cliente();
					c.setFechaAgregado(fecha);
					c.setNombre(tfNombre.getText());
					c.setApellido(tfApellido.getText());
					c.setIdpersona(Integer.parseInt(tfCedula.getText()));
					c.setDir(tfDir.getText());
					c.setEdad(Integer.parseInt(tfEdad.getText()));
					c.setEmail(tfCorreo.getText());
					c.setTelefono(tfTelefono.getText());
					c.setSexo(comboSexo.getSelectedIndex()+1);
					int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA INTRODUCIR EL CLIENTE "+c.getNombre()+" "+c.getApellido()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
						if(!CQ.verificar(c.getIdpersona())){
							CQ.insertar(c);cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null, "EL CLIENTE YA EXISTE");
						}
					}
					else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL CLIENTE NO SE AGREGO");}
					else {JOptionPane.showMessageDialog(null, "EL CLIENTE NO SE AGREGO.");}
				  }
			}
		});
		
		cargarTabla();
		CQ.cargarCombo(comboSexo);
	}
	
	public void cargarTabla(){
		try{
			tabla.setModel(CQ.mostrar());
		}catch(Exception E){
			E.printStackTrace();
		}
	}
	
	public void bAgregar(){
		getContentPane().add(btnAgregar);
		btnAgregar.setBounds(531, 3, 89, 38);
		btnAgregar.setVisible(true);
	}
	
	public void bMOD(){
		getContentPane().add(btnModificar);
		btnModificar.setBounds(630, 3, 89, 38);
		btnModificar.setVisible(true);
		tfCedula.setEnabled(false);
	}
	
	public void bBorrar(){
		getContentPane().add(btnEliminar);
		btnEliminar.setBounds(685, 48, 89, 38);
		btnEliminar.setVisible(true);
		tfNombre.setEnabled(false);
		tfApellido.setEnabled(false);
		tfEdad.setEnabled(false);
		tfCorreo.setEnabled(false);
		tfTelefono.setEnabled(false);
		tfDir.setEnabled(false);
		comboSexo.setEnabled(false);
	}

}

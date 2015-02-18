package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import bd.ProveedorQuery;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import logica.Proveedor;

public class ProveedorUI extends JInternalFrame {
	private JTable tabla;
	private JTextField tfNombre;
	private JTextField tfDir;
	private JTextField tfEmail;
	private JTextField tfDes;
	private JTextField tfTelefono;
	private JComboBox<String> comboTipo = new JComboBox<String>();
	private JButton btnModificar = new JButton("MODIFICAR");
	private JButton btnEliminar = new JButton("ELIMINAR");
	private JButton btnAgregar = new JButton("AGREGAR");
	private JTextField tfID = new JTextField();
	private JLabel lblCodigo = new JLabel("Codigo:");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProveedorUI frame = new ProveedorUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProveedorUI() {
		setTitle("PRODUCTOS");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		
		JLabel lblProveedores = new JLabel("PROVEEDORES");
		lblProveedores.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblProveedores.setBounds(10, 11, 307, 50);
		getContentPane().add(lblProveedores);
		
		JLabel lblRelleneLosCorrespondientes = new JLabel("Rellene los correspondientes campos");
		lblRelleneLosCorrespondientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelleneLosCorrespondientes.setBounds(20, 72, 239, 14);
		getContentPane().add(lblRelleneLosCorrespondientes);
		
		JButton btnRecarga = new JButton(new ImageIcon("Imagenes/recarga.png"));
		btnRecarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnRecarga.setBounds(741, 11, 36, 36);
		getContentPane().add(btnRecarga);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 242, 764, 318);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int fila = tabla.getSelectedRow();
				tfNombre.setText(tabla.getModel().getValueAt(fila, 1).toString());
				tfDir.setText(tabla.getModel().getValueAt(fila, 2).toString());
				tfEmail.setText(tabla.getModel().getValueAt(fila, 3).toString());
				tfDes.setText(tabla.getModel().getValueAt(fila, 4).toString());
				tfTelefono.setText(tabla.getModel().getValueAt(fila, 7).toString());
				comboTipo.setSelectedItem(String.valueOf(tabla.getModel().getValueAt(fila, 6)));;
				tfID.setText(tabla.getModel().getValueAt(fila, 0).toString());
			}
		});
		scrollPane.setViewportView(tabla);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 120, 65, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(20, 160, 65, 14);
		getContentPane().add(lblDireccion);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 196, 46, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(340, 120, 65, 14);
		getContentPane().add(lblDescripcion);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(340, 160, 46, 14);
		getContentPane().add(lblTipo);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(340, 196, 46, 14);
		getContentPane().add(lblTelefono);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(95, 117, 210, 24);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfDir = new JTextField();
		tfDir.setBounds(95, 155, 210, 24);
		getContentPane().add(tfDir);
		tfDir.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(95, 192, 210, 24);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfDes = new JTextField();
		tfDes.setBounds(420, 117, 210, 24);
		getContentPane().add(tfDes);
		tfDes.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setBounds(420, 192, 210, 24);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);
		
		comboTipo.setBounds(420, 155, 210, 24);
		getContentPane().add(comboTipo);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Nombre del Proveedor");
				}
				else if(tfDir.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese la direccion del Proveedor");
				}
				else if(tfEmail.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Email del Proveedor");
				}
				else if(tfDes.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese una Descripcion acerca del Proveedor");				
				}
				else if(tfTelefono.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Telefono del Proveedor");				
				}
				else{
					ProveedorQuery pq = new ProveedorQuery();
					Proveedor p = new Proveedor();
					int dia=0,mes=0,year=0;
					Calendar cal = Calendar.getInstance();
					dia = cal.get(Calendar.DAY_OF_MONTH);
					mes = cal.get(Calendar.MONTH);
					year = cal.get(Calendar.YEAR);
					String fecha = dia+"/"+mes+"/"+year;
					p.setNombre(tfNombre.getText());
					p.setTipo(comboTipo.getSelectedIndex()+1);
					p.setDescripcion(tfDes.getText());
					p.setEmail(tfEmail.getText());
					p.setDireccion(tfDir.getText());
					p.setTelefono(tfTelefono.getText());
					p.setFecha(fecha);
					int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA INTRODUCIR EL PROVEEDOR "+p.getNombre()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
						if(!pq.verificar(p.getNombre())){
							pq.insertar(p);cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null,"EL PROVEEDOR YA EXISTE");
						}
					}
					else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL PROVEEDOR NO SE AGREGO");}
					else {JOptionPane.showMessageDialog(null, "EL PROVEEDOR NO SE AGREGO.");}
				  }
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent E) {
				if(tfID.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Codigo del Proveedor, o Seleccionelo de la tabla");
				}
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Nombre del Proveedor");
				}
				else if(tfDir.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese la direccion del Proveedor");
				}
				else if(tfEmail.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Email del Proveedor");
				}
				else if(tfDes.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese una Descripcion acerca del Proveedor");				
				}
				else if(tfTelefono.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingrese el Telefono del Proveedor");				
				}
				else{
					ProveedorQuery pq = new ProveedorQuery();
					int dia=0,mes=0,year=0;
					Calendar cal = Calendar.getInstance();
					dia = cal.get(Calendar.DAY_OF_MONTH);
					mes = cal.get(Calendar.MONTH);
					year = cal.get(Calendar.YEAR);
					String fecha = dia+"/"+mes+"/"+year;
					Proveedor p = new Proveedor();
					p.setId(Integer.parseInt(tfID.getText()));
					p.setNombre(tfNombre.getText());
					p.setTipo(comboTipo.getSelectedIndex()+1);
					p.setDescripcion(tfDes.getText());
					p.setEmail(tfEmail.getText());
					p.setDireccion(tfDir.getText());
					p.setTelefono(tfTelefono.getText());
					int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA INTRODUCIR EL PROVEEDOR "+p.getNombre()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION){
						if(pq.verificar(p.getNombre())){
							pq.actualizar(p);cargarTabla();
						}
						else{
							JOptionPane.showMessageDialog(null,"EL PROVEEDOR NO EXISTE");
						}
					}
					else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL PROVEEDOR NO SE AGREGO");}
					else {JOptionPane.showMessageDialog(null, "EL PROVEEDOR NO SE AGREGO.");}
				  }				
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				if(tfID.getText().length()==0 || tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Seleccione un Proveedor de la Tabla");
				}
				else{
					Proveedor p = new Proveedor();
					ProveedorQuery pq = new ProveedorQuery();
					p.setNombre(tfNombre.getText());
					p.setId(Integer.parseInt(tfID.getText()));
					if(pq.verificar(p.getNombre())){
						pq.borrar(p);cargarTabla();
					}
					else{
						JOptionPane.showMessageDialog(null,"EL PROVEEDOR NO EXISTE");
					}
					
				}
				
			}
		});
		cargarTabla();
		cargarCombo();
	}
	
	public void cargarTabla(){
		try{
			ProveedorQuery PQ = new ProveedorQuery();
			tabla.setModel(PQ.mostrar());
			}
		catch(Exception E){
			E.printStackTrace();
		}
	}
	
	public void bAgregar(){
		btnAgregar.setBounds(663, 67, 100, 42);
		getContentPane().add(btnAgregar);
		tfID.setVisible(true);
		
	}
	public void bMOD(){
		btnModificar.setBounds(663, 123, 100, 42);
		getContentPane().add(btnModificar);
		tfID.setBounds(420, 75, 211, 24);
		tfID.setEnabled(false);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		lblCodigo.setBounds(340, 80, 46, 14);
		getContentPane().add(lblCodigo);
	}
	
	public void bBorrar(){
		lblCodigo.setBounds(340, 80, 46, 14);
		getContentPane().add(lblCodigo);
		btnEliminar.setBounds(663, 180, 100, 42);
		getContentPane().add(btnEliminar);
		tfNombre.setEnabled(false);
		tfDir.setEnabled(false);
		tfEmail.setEnabled(false);
		tfDes.setEnabled(false);
		tfTelefono.setEnabled(false);
		comboTipo.setEnabled(false);
		tfID.setBounds(420, 75, 211, 24);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		tfID.setEnabled(false);
	}
	
	public void cargarCombo(){
		ProveedorQuery pq = new ProveedorQuery();
		try{
			pq.cargarCombo(comboTipo);
		}catch(Exception Error){
			Error.printStackTrace();
		}
	}
}

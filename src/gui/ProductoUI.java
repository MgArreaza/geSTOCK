package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import bd.ConexionSQL;
import bd.ProductoQuery;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import logica.Producto;

import javax.swing.JFormattedTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;

public class ProductoUI extends JInternalFrame {

	private ProductoQuery PQ = new ProductoQuery();
	private JTable tabla;
	private JTextField tfNombre;
	private JTextField tfPU;
	private JTextField tfPM;
	private JComboBox<String> comboProv = new JComboBox<String>();
	private JComboBox<String> comboCategoria = new JComboBox<String>();
	private JButton btnModificar = new JButton("MODIFICAR");
	private JButton btnRegistrar = new JButton("REGISTRAR");
	private JButton btnEliminar = new JButton("ELIMINAR");
	private JButton btnRecargar = new JButton(new ImageIcon("Imagenes/recarga.png"));
	private JTextField tfDes = new JTextField();
	private JButton btnNewButton = new JButton("AgregarStock");
	private JTextField tfCantidad = new JTextField();
	private JLabel lblCantidad = new JLabel("Cantidad: ");
	
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

	public ProductoUI() {
		setTitle("PRODUCTOS");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 800, 550);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 184, 764, 326);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					int fila = tabla.getSelectedRow();
					tfNombre.setText(tabla.getModel().getValueAt(fila, 1).toString());
					tfPU.setText(tabla.getModel().getValueAt(fila, 4).toString());
					tfPM.setText(tabla.getModel().getValueAt(fila, 5).toString());
					comboProv.setSelectedItem(tabla.getModel().getValueAt(fila, 3));
					comboCategoria.setSelectedItem(tabla.getModel().getValueAt(fila, 7));
					tfDes.setText(tabla.getModel().getValueAt(fila, 8).toString());
			}
		});
		scrollPane.setViewportView(tabla);
		
		
		JLabel lblProductos = new JLabel("PRODUCTOS");
		lblProductos.setBounds(10, 11, 221, 46);
		lblProductos.setFont(new Font("Tahoma", Font.PLAIN, 37));
		getContentPane().add(lblProductos);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Nombre del Producto");
				}
				else if(tfPU.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Precio del Producto por Unidad");
				}
				else if(tfPM.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Precio del Producto al Mayor");
				}
				else if(tfDes.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa una Descripcion");				
				}
				else{
					int dia=0,mes=0,year=0;
					Calendar cal = Calendar.getInstance();
					dia = cal.get(Calendar.DAY_OF_MONTH);
					mes = cal.get(Calendar.MONTH);
					year = cal.get(Calendar.YEAR);
					String fecha = dia+"/"+mes+"/"+year;
					Producto P = new Producto();
					P.setFecha(fecha);
					P.setNombre(tfNombre.getText());
					P.setPrecio(Double.parseDouble(tfPU.getText()));
					P.setPrecioM(Double.parseDouble(tfPM.getText()));
					String proveedor = String.valueOf(comboProv.getSelectedItem());
					String[] partes = proveedor.split(" ");
					P.setProveedor(Integer.parseInt(partes[0]));
					P.setTipo(comboCategoria.getSelectedIndex()+1);
					P.setDescripcion(tfDes.getText());
					if(P.getPrecio()<P.getPrecioM()){
						JOptionPane.showMessageDialog(null,"EL PRECIO UNITARIO NO PUEDE SER MENOR AL MAYOR");
					}
					else{
						int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA INTRODUCIR EL PRODUCTO "+P.getNombre()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
						if(opcion == JOptionPane.YES_OPTION){
							if(!PQ.verificar(P.getNombre())){
								PQ.insertar(P);cargarTabla();
							}
							else{
								JOptionPane.showMessageDialog(null,"EL PRODUCTO YA EXISTE");
							}
						}
						else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL PRODUCTO NO SE AGREGADO");}
						else {JOptionPane.showMessageDialog(null, "EL PRODUCTO NO SE AGREGO.");}
					}
				  }
			}
		});
		
		
		
		
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					cargarTabla();
				   }
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		
		btnRecargar.setBounds(743, 11, 36, 36);
		getContentPane().add(btnRecargar);
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Nombre del Producto");
				}
				else if(tfPU.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Precio del Producto por Unidad");
				}
				else if(tfPM.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Precio del Producto al Mayor");
				}
				else if(tfDes.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa una Descripcion");				
				}
				else{
					Producto P = new Producto();
					P.setNombre(tfNombre.getText());
					P.setPrecio(Double.parseDouble(tfPU.getText()));
					P.setPrecioM(Double.parseDouble(tfPM.getText()));
					String proveedor = String.valueOf(comboProv.getSelectedItem());
					String[] partes = proveedor.split(" ");
					P.setProveedor(Integer.parseInt(partes[0]));
					P.setTipo(comboCategoria.getSelectedIndex()+1);
					P.setDescripcion(tfDes.getText());
					if(P.getPrecio()<P.getPrecioM()){
						JOptionPane.showMessageDialog(null,"EL PRECIO UNITARIO NO PUEDE SER MENOR AL MAYOR");
					}
					else{
						int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA PROCEDER A EDITAR EL PRODUCTO "+P.getNombre()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
						if(opcion == JOptionPane.YES_OPTION){
							if(PQ.verificar(P.getNombre())){
								PQ.actualizar(P);cargarTabla();
							}
							else{
								JOptionPane.showMessageDialog(null,"EL PRODUCTO NO EXISTE");
							}
						}
						else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL PRODUCTO NO SE AGREGADO");}
						else {JOptionPane.showMessageDialog(null, "EL PRODUCTO NO SE AGREGO.");}
					}
				  }
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa el Nombre del Producto");
				}
				Producto P = new Producto();
				P.setNombre(tfNombre.getText());
				int opcion = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMINAR EL PRODUCTO "+P.getNombre()+ " ?", "CONFIRME PARA CONTINUAR", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					if(PQ.verificar(P.getNombre())){
						PQ.borrar(P);cargarTabla();
					}
					else{
						JOptionPane.showMessageDialog(null,"EL PRODUCTO NO EXISTE");
					}
				}
				else if(opcion == JOptionPane.NO_OPTION){JOptionPane.showMessageDialog(null,"EL PRODUCTO NO SE AGREGADO");}
				else {JOptionPane.showMessageDialog(null, "EL PRODUCTO NO SE AGREGO.");}
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(17, 107, 71, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(17, 159, 73, 14);
		getContentPane().add(lblProveedor);
		
		JLabel lblPrecioUnitario = new JLabel("Precio Unitario:");
		lblPrecioUnitario.setBounds(17, 132, 89, 14);
		getContentPane().add(lblPrecioUnitario);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(105, 104, 200, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblPrecioAlMayor = new JLabel("Precio al Mayor:");
		lblPrecioAlMayor.setBounds(346, 132, 80, 14);
		getContentPane().add(lblPrecioAlMayor);
		
		tfPU = new JTextField();
		tfPU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.') {
					e.consume();
				}
				if (e.getKeyChar() == '.' && tfPU.getText().contains(".")) {
					e.consume();
				}
				if (Character.isAlphabetic(e.getKeyChar())) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		tfPU.setBounds(105, 129, 200, 20);
		getContentPane().add(tfPU);
		tfPU.setColumns(10);
		
		tfPM = new JTextField();
		tfPM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
					evt.consume();
				}
				if (evt.getKeyChar() == '.' && tfPM.getText().contains(".")) {
					evt.consume();
				}
				if (Character.isAlphabetic(evt.getKeyChar())) {
					getToolkit().beep();
					evt.consume();
				}
			}
		});
		tfPM.setBounds(440, 129, 200, 20);
		getContentPane().add(tfPM);
		tfPM.setColumns(10);
		
		comboProv.setBounds(105, 156, 200, 20);
		getContentPane().add(comboProv);
		
		JLabel lblCaterogoria = new JLabel("Categoria:");
		lblCaterogoria.setBounds(346, 159, 73, 14);
		getContentPane().add(lblCaterogoria);
		
		comboCategoria.setBounds(440, 156, 200, 20);
		getContentPane().add(comboCategoria);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(346, 107, 71, 14);
		getContentPane().add(lblDescripcion);
		
		tfDes.setBounds(440, 104, 200, 20);
		getContentPane().add(tfDes);
		tfDes.setColumns(10);
		
		JLabel lblRelleneLosCorrespondientes = new JLabel("Rellene los Correspondientes Campos");
		lblRelleneLosCorrespondientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelleneLosCorrespondientes.setBounds(10, 68, 271, 14);
		getContentPane().add(lblRelleneLosCorrespondientes);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfCantidad.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Ingresa la cantidad Agregar");
				}
				else if(Integer.parseInt(tfCantidad.getText())<=0){
					JOptionPane.showMessageDialog(null,"La cantidad Agregar debe ser mayor a 0");
				}
				else if(tfNombre.getText().length()==0){
					JOptionPane.showMessageDialog(null,"Selecciona un producto de la tabla");
				}
				else{
					PQ.AgregarStock(Integer.parseInt(tfCantidad.getText()), tfNombre.getText());cargarTabla();
				}
			}
		});
		
		cargarTabla();
		cargarCombos();
	}
	
	private void cargarTabla(){
		try{
			tabla.setModel(PQ.mostrar());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga de la tabla");
		}
	}
	
	private void cargarCombos(){
		PQ.cargarCombo(comboCategoria,"Descripcion","SELECT Descripcion From categoria");
		PQ.cargarCombo(comboProv);
	}
	
	public void bAgregar(){
		btnRegistrar.setBounds(677, 46, 97, 32);
		getContentPane().add(btnRegistrar);
	}
	
	public void bMOD(){
		btnModificar.setBounds(677, 89, 97, 32);
		getContentPane().add(btnModificar);
		
	}
	public void bBorrar(){
		btnEliminar.setBounds(677, 132, 97, 32);
		getContentPane().add(btnEliminar);
		inhabilitar();
	}
	
	public void inhabilitar(){
		tfPU.setEnabled(false);
		tfPM.setEnabled(false);
		tfDes.setEnabled(false);
		tfNombre.setEnabled(false);
		comboProv.setEnabled(false);
		comboCategoria.setEnabled(false);
	}
	
	public void bStock(){
		inhabilitar();
		tfCantidad.setBounds(440, 73, 200, 20);
		getContentPane().add(tfCantidad);
		tfCantidad.setColumns(10);
		lblCantidad.setBounds(346, 76, 71, 14);
		getContentPane().add(lblCantidad);
		btnNewButton.setBounds(334, 11, 113, 35);
		getContentPane().add(btnNewButton);
	}
}

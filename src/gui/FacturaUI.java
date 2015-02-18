package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import bd.FacturaQuery;
import bd.ProductoQuery;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

import logica.Factura;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class FacturaUI extends JInternalFrame {;
	private JTextField tfSub;
	private JTextField tfIVA;
	private JTextField tfTotal;
	private ProductoQuery pr = new ProductoQuery();
	private JComboBox<String> comboCliente = new JComboBox<String>();
	private JComboBox<String> comboP = new JComboBox<String>();
	private JButton btnRecargar = new JButton(new ImageIcon("Imagenes/recarga.png"));
	private int registros;
	private JButton btnCargar = new JButton("Cargar Producto");
	private JTextField tfCantidad;
	private String[] columnas = {"COD","Producto","Cantidad","Precio","Monto"};
	private JTable tabla;
	private DefaultTableModel modelo;
	private double total;
	private JButton btnFacturarProducto = new JButton("FACTURAR PRODUCTO");
	private FacturaQuery FQ = new FacturaQuery();
	private Factura F = new Factura();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacturaUI frame = new FacturaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FacturaUI() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 800, 580);
		getContentPane().setLayout(null);
		
		JLabel lblFacturarProducto = new JLabel("FACTURAR PRODUCTO");
		lblFacturarProducto.setFont(new Font("Tahoma", Font.PLAIN, 39));
		lblFacturarProducto.setBounds(10, 11, 433, 49);
		getContentPane().add(lblFacturarProducto);
		
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarCombo(comboCliente);
				vaciarCombo(comboP);
				cargarCombo();
			}
		});
		btnRecargar.setBounds(738, 11, 36, 36);
		getContentPane().add(btnRecargar);
		
		modelo = new DefaultTableModel(null,columnas);
		
		comboP.setBounds(117, 106, 291, 24);
		getContentPane().add(comboP);
		
		JLabel lblRCC = new JLabel("Rellene los Correspondites Campos");
		lblRCC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRCC.setBounds(20, 71, 259, 17);
		getContentPane().add(lblRCC);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(30, 111, 64, 14);
		getContentPane().add(lblProducto);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(30, 150, 64, 14);
		getContentPane().add(lblCliente);
		
		comboCliente.setBounds(117, 145, 291, 24);
		getContentPane().add(comboCliente);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(455, 111, 64, 14);
		getContentPane().add(lblCantidad);
		
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfCantidad.getText().length()==0 || Integer.parseInt(tfCantidad.getText())<=0){
					JOptionPane.showMessageDialog(null,"Ingresa la cantidad de Productos a Comprar");
				}
				else{
					int seleccion=comboP.getSelectedIndex();
					F.setCantidad(Integer.parseInt(tfCantidad.getText()));
					String N = String.valueOf(comboP.getSelectedItem());
					String[] partes = N.split(" ");
					F.setIdproducto(Integer.parseInt(partes[0]));
					if(!pr.existencia(F.getIdproducto(), F.getCantidad())){
						
					}
					else{
						N = "";
						for(int i=1;i<partes.length;i++){
							N += partes[i]+" ";
						}
						F.setProducto(N);
						F.setPrecio(pr.getPrecio(F.getIdproducto(), F.getCantidad()));
						String[] datos = {String.valueOf(F.getIdproducto()),F.getProducto(),String.valueOf(F.getCantidad()),String.valueOf(F.getPrecio()),String.valueOf(F.monto())};
						btnRecargar.setEnabled(false);
						modelo.addRow(datos);
						tabla.setModel(modelo);
						comboP.removeItemAt(seleccion);
						total = 0;
						for (int i = 0; i < modelo.getRowCount(); i++){
						    total = total + Double.valueOf(modelo.getValueAt(i, 4).toString());
						}
						tfSub.setText(String.valueOf(total));
						tfIVA.setText(String.valueOf(F.iva(total)));
						tfTotal.setText(String.valueOf(total+F.iva(total)));
						btnFacturarProducto.setEnabled(true);
						if(comboP.getItemCount()==0){
							comboP.setEnabled(false);
						}
						
					}
				}
			}
		});
		btnCargar.setBounds(654, 141, 120, 32);
		getContentPane().add(btnCargar);
		
		JLabel lblSubtotal = new JLabel("SubTotal:");
		lblSubtotal.setBounds(569, 398, 64, 14);
		getContentPane().add(lblSubtotal);
		
		JLabel lblIva = new JLabel("IVA (12%):");
		lblIva.setBounds(569, 423, 64, 14);
		getContentPane().add(lblIva);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(569, 448, 46, 14);
		getContentPane().add(lblTotal);
		
		tfSub = new JTextField();
		tfSub.setHorizontalAlignment(SwingConstants.RIGHT);
		tfSub.setEnabled(false);
		tfSub.setBounds(654, 398, 112, 20);
		getContentPane().add(tfSub);
		tfSub.setColumns(10);
		
		tfIVA = new JTextField();
		tfIVA.setHorizontalAlignment(SwingConstants.RIGHT);
		tfIVA.setEnabled(false);
		tfIVA.setBounds(654, 422, 112, 20);
		getContentPane().add(tfIVA);
		tfIVA.setColumns(10);
		
		tfTotal = new JTextField();
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setEnabled(false);
		tfTotal.setBounds(654, 445, 112, 20);
		getContentPane().add(tfTotal);
		tfTotal.setColumns(10);
		
		tfCantidad = new JTextField();
		tfCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!Character.isDigit(arg0.getKeyChar())) {
					arg0.consume();
				}
				if (Character.isAlphabetic(arg0.getKeyChar())) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		tfCantidad.setBounds(539, 106, 235, 24);
		getContentPane().add(tfCantidad);
		tfCantidad.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 187, 764, 200);
		getContentPane().add(scrollPane);
		
		tabla = new JTable();
		tabla.setRowSelectionAllowed(false);
		scrollPane.setViewportView(tabla);
		
		
		btnFacturarProducto.setEnabled(false);
		btnFacturarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				int dia = cal.get(Calendar.DAY_OF_MONTH);
				int mes = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				F.setFecha(dia+"/"+mes+"/"+year); 
				String cliente = String.valueOf(comboCliente.getSelectedItem());
				String[] parteC = cliente.split(" ");
				F.setIdcliente(Integer.parseInt(parteC[0]));
				for(int i=0;i<modelo.getRowCount();i++){
						F.setIdproducto(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
						F.setCantidad(Integer.parseInt(modelo.getValueAt(i, 2).toString()));
						FQ.insertarV(F);						
				}
				FQ.insertar(F);
				btnRecargar.setEnabled(true);
				btnCargar.setEnabled(true);
				vaciarCombo(comboP);
				vaciarCombo(comboCliente);
				cargarCombo();
				int cantR = modelo.getRowCount();
				for (int i = cantR - 1; i >= 0; i--) {
				    modelo.removeRow(i);
				}
				comboP.setEnabled(true);
				JOptionPane.showMessageDialog(null, "FACTURA REGISTRADA");
				dispose();
			}
		});
		btnFacturarProducto.setBounds(618, 496, 156, 44);
		getContentPane().add(btnFacturarProducto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfIVA.setText("");
				tfTotal.setText("");
				tfSub.setText("");
				tfCantidad.setText("");
				btnRecargar.setEnabled(true);
				btnCargar.setEnabled(true);
				btnFacturarProducto.setEnabled(false);
				int cantR = modelo.getRowCount();
				for (int i = cantR - 1; i >= 0; i--) {
				    modelo.removeRow(i);
				}
				comboP.setEnabled(true);
				vaciarCombo(comboP);
				vaciarCombo(comboCliente);
				cargarCombo();
				dispose();
			}
		});
		btnCancelar.setBounds(20, 507, 89, 23);
		getContentPane().add(btnCancelar);
		cargarCombo();
	}
	
	public void cargarCombo(){
		FacturaQuery f = new FacturaQuery();
		try{
			f.cargarCombo(comboP,comboCliente);
		}catch(Exception error){
			error.printStackTrace();
		}
	}
	
	public void vaciarCombo(JComboBox<String> E){
		 int itemCount = E.getItemCount();
		 for(int i=0;i<itemCount;i++){
			 E.removeItemAt(0);
		 }
	}
}

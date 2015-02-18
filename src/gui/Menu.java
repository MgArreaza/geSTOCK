package gui;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;

public class Menu {
	
	private UsuarioUI newUser = new UsuarioUI();
	private UsuarioUI MODuser = new UsuarioUI();
	private UsuarioUI delUser = new UsuarioUI();
	private ProductoUI PC = new ProductoUI();
	private ProductoUI PM = new ProductoUI();
	private ProductoUI PE = new ProductoUI();
	private ProductoUI PS = new ProductoUI();
	private ClienteUI CC = new ClienteUI();
	private ClienteUI CM = new ClienteUI();
	private ClienteUI CE = new ClienteUI();
	private ProveedorUI PVC = new ProveedorUI();
	private ProveedorUI PVM = new ProveedorUI();
	private ProveedorUI PVE = new ProveedorUI();
	private JLabel lblNomb = new JLabel();
	private JLabel lblNivel = new JLabel();
	private JMenu mnUsuarios = new JMenu("USUARIOS");
	private JMenu mnProveedor = new JMenu("PROVEEDOR");
	private JMenu mnFactura = new JMenu("FACTURA");
	private JMenu mnCliente = new JMenu("CLIENTE");
	private JMenu mnProducto = new JMenu("PRODUCTO");
	private FacturaUI FC = new FacturaUI();
	private VerFacturaUI FV = new VerFacturaUI();
	private JMenuItem mntmFacturar = new JMenuItem("FACTURAR PRODUCTO");
	private JMenuItem mntmVerFactura = new JMenuItem("VER FACTURA");
	
	
	public Menu(){
		final JFrame frmGestockMenu = new JFrame("MENU PRINCIPAL");
		frmGestockMenu.setTitle("geSTOCK 1.1.1 MENU PRINCIPAL");
		frmGestockMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGestockMenu.setBounds(100, 100, 1024, 650);
		
		final JDesktopPane dp = new JDesktopPane();
		dp.setBackground(new Color(51, 51, 51));
		frmGestockMenu.getContentPane().add(dp);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(40, 76, 70, 15);
		lblNombre.setForeground(SystemColor.text);
		dp.add(lblNombre);
		
		JLabel lblRango = new JLabel("Rango:");
		lblRango.setBounds(40, 102, 70, 15);
		lblRango.setForeground(SystemColor.text);
		dp.add(lblRango);
		
		lblNivel.setBounds(120, 102, 120, 15);
		lblNivel.setForeground(SystemColor.text);
		dp.add(lblNivel);
		
		lblNomb.setBounds(120, 76, 120, 15);
		lblNomb.setForeground(SystemColor.text);
		dp.add(lblNomb);
		
		JLabel lblBienvenid = new JLabel("BIENVENID@");
		lblBienvenid.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblBienvenid.setForeground(Color.WHITE);
		lblBienvenid.setBounds(40, 11, 383, 54);
		dp.add(lblBienvenid);
		
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(Box.createRigidArea(new Dimension(12, 32)));
		frmGestockMenu.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("SISTEMA");
		mnSistema.setIcon(new ImageIcon("Imagenes/sistema.png"));
		menuBar.add(mnSistema);
		
		JMenuItem mntmSalir = new JMenuItem("SALIR");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmCerrar = new JMenuItem("CERRAR SESION");
		
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGestockMenu.dispose();
				Login login = new Login();
			}
		});
		
		JMenuItem mntmAcercaDe = new JMenuItem("ACERCA DE");
		mnSistema.add(mntmAcercaDe);
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acerca acercade = new Acerca();
				acercade.setVisible(true);
			}
		});
		mnSistema.add(mntmCerrar);
		mnSistema.add(mntmSalir);
		
		menuBar.add(mnCliente);
		mnCliente.setIcon(new ImageIcon("Imagenes/clientes.png"));
		
		JMenuItem mntmRegistrar = new JMenuItem("REGISTRAR CLIENTE");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CC.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{
					CC.getContentPane().add(panel);
					dp.add(CC);
					CC.bAgregar();
					CC.setVisible(true);
				}
			}
		});
		mnCliente.add(mntmRegistrar);
		
		JMenuItem mntmModificar = new JMenuItem("MODIFICAR CLIENTE");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CM.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{
					CM.getContentPane().add(panel);
					dp.add(CM);
					CM.bMOD();
					CM.setVisible(true);
				}
			}
		});
		mnCliente.add(mntmModificar);
		
		JMenuItem mntmBorrar = new JMenuItem("ELIMINAR CLIENTE");
		mntmBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CE.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{
					CE.getContentPane().add(panel);
					dp.add(CE);
					CE.bBorrar();
					CE.setVisible(true);
				}
			}
		});
		mnCliente.add(mntmBorrar);
		mnProducto.setIcon(new ImageIcon("Imagenes/producto.png"));
		menuBar.add(mnProducto);
		
		JMenuItem mntmRegistrarProducto = new JMenuItem("REGISTRAR PRODUCTO");
		mntmRegistrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PC.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PC.getContentPane().add(panel);
					dp.add(PC);
					PC.bAgregar();
					PC.setVisible(true);
				}
			}
		});
		mnProducto.add(mntmRegistrarProducto);
		
		JMenuItem mntmModifciarProducto = new JMenuItem("MODIFCIAR PRODUCTO");
		mntmModifciarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PM.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PM.getContentPane().add(panel);
					dp.add(PM);
					PM.bMOD();
					PM.setVisible(true);
				}
			}
		});
		mnProducto.add(mntmModifciarProducto);
		
		JMenuItem mntmEliminarProducto = new JMenuItem("ELIMINAR PRODUCTO");
		mntmEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PE.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PE.getContentPane().add(panel);
					dp.add(PE);
					PE.bBorrar();
					PE.setVisible(true);
				}
			}
		});
		mnProducto.add(mntmEliminarProducto);
		
		JMenuItem mntmAgregarStock = new JMenuItem("AGREGAR STOCK");
		mntmAgregarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PS.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PS.getContentPane().add(panel);
					dp.add(PS);
					PS.bStock(); 
					PS.setVisible(true);
				}
			}
		});
		mnProducto.add(mntmAgregarStock);
		mnProveedor.setIcon(new ImageIcon("Imagenes/proveedor.png"));
		menuBar.add(mnProveedor);
		
		
		JMenuItem mntmNuevoProveedor = new JMenuItem("NUEVO PROVEEDOR");
		mntmNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(PVC.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PVC.getContentPane().add(panel);
					dp.add(PVC);
					PVC.bAgregar();
					PVC.setVisible(true);
				}
			}
		});
		mnProveedor.add(mntmNuevoProveedor);
		
		JMenuItem mntmModificarProveedor = new JMenuItem("MODIFICAR PROVEEDOR");
		mntmModificarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PVM.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PVM.getContentPane().add(panel);
					dp.add(PVM);
					PVM.bMOD();
					PVM.setVisible(true);
				}
			}
		});
		mnProveedor.add(mntmModificarProveedor);
		
		JMenuItem mntmBorrarProveedor = new JMenuItem("BORRAR PROVEEDOR");
		mntmBorrarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PVE.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					PVE.getContentPane().add(panel);
					dp.add(PVE);
					PVE.bBorrar();
					PVE.setVisible(true);
				}
			}
		});
		mnProveedor.add(mntmBorrarProveedor);
		mnFactura.setIcon(new ImageIcon("Imagenes/factura.png"));
		menuBar.add(mnFactura);
		
		mntmFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FC.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					FC.getContentPane().add(panel);
					dp.add(FC);
					FC.setVisible(true);
				}
			}
		});
		mnFactura.add(mntmFacturar);
		
		
		mntmVerFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FV.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					FV.getContentPane().add(panel);
					dp.add(FV);
					FV.setVisible(true);
				}
				
			}
		});
		mnFactura.add(mntmVerFactura);
		mnUsuarios.setIcon(new ImageIcon("Imagenes/usuarios.png"));
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("AGREGAR USUARIO");
		mntmAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newUser.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					newUser.getContentPane().add(panel);
					dp.add(newUser);
					newUser.bAgregar();
					newUser.DisableID();
					newUser.setVisible(true);
				}
			}
		});
		
		mnUsuarios.add(mntmAgregarUsuario);
		JMenuItem mntmModificarUsuario = new JMenuItem("MODIFICAR USUARIO");
		mntmModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(MODuser.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					MODuser.getContentPane().add(panel);
					dp.add(MODuser);
					MODuser.bMOD();
					MODuser.getLblAgregarUsuario().setText("MODIFICAR USUARIO");
					MODuser.setVisible(true);
				}
			}
		});
		mnUsuarios.add(mntmModificarUsuario);
		
		JMenuItem mntmBorrarUsuario = new JMenuItem("BORRAR USUARIO");
		mntmBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(delUser.isShowing()) { 
					JOptionPane.showMessageDialog(null, "Ya esta Abierto");
				} 
				else{ 
					delUser.getContentPane().add(panel);
					dp.add(delUser);
					delUser.bBorrar();
					delUser.getLblAgregarUsuario().setText("ELIMINAR USUARIO");
					delUser.inhabilitar();
					delUser.setVisible(true);
				}
			}
		});
		mnUsuarios.add(mntmBorrarUsuario);
		frmGestockMenu.setVisible(true);
		frmGestockMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public void mostrarEtiquetas(String nombre,String apellido, int nivel){
		lblNomb.setText(nombre+" "+apellido);
		if(nivel==1){ lblNivel.setText("ADMINISTRADOR");}
		else if(nivel==2){ lblNivel.setText("GERENTE");opcionesGerente();}
		else if(nivel==3){ lblNivel.setText("VENDEDOR");opcionesVendedor();}
		else{ lblNivel.setText("ALMACENISTA");opcionesAlmacenista();}
	}
	
	public void opcionesGerente(){
		mnUsuarios.setVisible(false);
		mnCliente.setVisible(false);
		mnProveedor.setVisible(false);
		mnProducto.setVisible(false);
		mntmFacturar.setVisible(false);
	}
	
	public void opcionesVendedor(){
		mnUsuarios.setVisible(false);
		mnProveedor.setVisible(false);
		mnProducto.setVisible(false);
	}
	
	public void opcionesAlmacenista(){
		mnUsuarios.setVisible(false);
		mnCliente.setVisible(false);
		mnFactura.setVisible(false);
	}
}
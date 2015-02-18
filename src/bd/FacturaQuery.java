package bd;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.Factura;

public class FacturaQuery extends Query{
	private ConexionSQL con = new ConexionSQL();
	private ProductoQuery p = new ProductoQuery();
	public DefaultTableModel mostrar(){
		con.conectar();
		consulta ="SELECT factura.id,factura.fecha,cliente.nombre,cliente.apellido FROM factura,cliente WHERE factura.idcliente=cliente.cedula";
		DefaultTableModel modelo;
		String[] titulos = {"ID","Nombre","Apellido","Fecha"};
		String[] registro = new String[4];
		modelo = new DefaultTableModel(null, titulos);
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				registro[0] = con.rs.getString("id").toString();
				registro[1] = con.rs.getString("nombre");
				registro[2] = con.rs.getString("apellido");
				registro[3] = con.rs.getString("fecha");
				modelo.addRow(registro);
			}
			con.cerrarConexion();
			con.rs.close();
			return modelo;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public DefaultTableModel mostrarV(){
		con.conectar();
		consulta ="SELECT venta.cod,producto.nombre as Producto,cliente.nombre,cliente.apellido,venta.cantida FROM producto,venta,cliente WHERE venta.idpro=producto.codigo and venta.idcliente=cliente.cedula";
		DefaultTableModel modelo;
		String[] titulos = {"ID","Producto","Nombre","Apellido","Cantidad"};
		String[] registro = new String[5];
		modelo = new DefaultTableModel(null, titulos);
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				registro[0] = con.rs.getString("cod").toString();
				registro[1] = con.rs.getString("Producto");
				registro[2] = con.rs.getString("nombre");
				registro[3] = con.rs.getString("apellido");
				registro[4] = con.rs.getString("cantida");
				modelo.addRow(registro);
			}
			con.cerrarConexion();
			con.rs.close();
			return modelo;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public void cargarCombo(JComboBox J,JComboBox P){
		consulta = "SELECT producto.codigo,producto.nombre FROM producto,proveedor,categoria WHERE producto.codproveedor=proveedor.codigo and producto.idcategoria=categoria.codigo";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			String nombre;
			while(con.rs.next()){
				nombre = con.rs.getString("codigo")+" "+con.rs.getString("nombre");
				J.addItem(nombre);
			}
			con.rs = con.stmt.executeQuery("SELECT cedula,nombre,apellido FROM cliente,tipoSexo WHERE cliente.sexo=tipoSexo.cod");
			while(con.rs.next()){
				nombre = con.rs.getString("cedula")+" "+con.rs.getString("nombre")+" "+con.rs.getString("apellido");
				P.addItem(nombre);
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
	public void insertar(Factura F){
		consulta = "INSERT INTO factura (fecha,idcliente) VALUES ('"+F.getFecha()+"','"+F.getIdcliente()+"')";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
	}
	public void insertarV(Factura F){
		p.restarStock(F.getCantidad(), F.getIdproducto());
		consulta = "INSERT INTO venta (idpro,idcliente,cantida) VALUES ('"+F.getIdproducto()+"','"+F.getIdcliente()+"','"+F.getCantidad()+"')";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
	}
}

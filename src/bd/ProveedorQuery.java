package bd;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.Proveedor;

public class ProveedorQuery extends Query {
	private ConexionSQL con = new ConexionSQL();
	public DefaultTableModel mostrar(){
		con.conectar();
		consulta ="SELECT codigo,nombre,direccion,email,descripcion,fecha,tipoProveedor.Tipo,telefono FROM proveedor,tipoProveedor WHERE proveedor.tipo=tipoProveedor.id";
		DefaultTableModel modelo;
		String[] titulos = {"Codigo","Nombre","Direccion","Email","Descripcion","Fecha","Tipo","Telefono"};
		String[] registro = new String[8];
		modelo = new DefaultTableModel(null, titulos);
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				registro[0] = con.rs.getString("codigo").toString();
				registro[1] = con.rs.getString("nombre");
				registro[2] = con.rs.getString("direccion");
				registro[3] = con.rs.getString("email");
				registro[4] = con.rs.getString("descripcion");
				registro[5] = con.rs.getString("fecha");
				registro[6] = con.rs.getString("Tipo");
				registro[7] = con.rs.getString("telefono");
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

	public void cargarCombo(JComboBox<String> combo){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT Tipo from tipoProveedor");
			while(con.rs.next()){
				combo.addItem(con.rs.getString("Tipo"));
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga del combo del Tipo de Proveedor");
		}
	}
	
	public void insertar(Proveedor p){
		consulta = "INSERT INTO proveedor (nombre,direccion,email,descripcion,fecha,tipo,telefono) VALUES ('"+p.getNombre()+"','"+p.getDireccion()+"','"+p.getEmail()+"','"+p.getDescripcion()+"','"+p.getFecha()+"','"+p.getTipo()+"','"+p.getTelefono()+"');";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void actualizar(Proveedor P){
		consulta = "UPDATE proveedor SET nombre='"+P.getNombre()+"',direccion='"+P.getDireccion()+"',email='"+P.getEmail()+"',descripcion='"+P.getDescripcion()+"',tipo='"+P.getTipo()+"',telefono='"+P.getTelefono()+"' WHERE codigo='"+P.getId()+"'";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void borrar(Proveedor p){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate("DELETE FROM proveedor where codigo='"+p.getId()+"'");
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "PROVEEDOR ELIMINADO DEL SISTEMA");	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public boolean verificar(String nombre){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT proveedor.nombre FROM proveedor WHERE proveedor.nombre='"+nombre+"'");
			while(con.rs.next()){
				if(nombre.equals(con.rs.getString("nombre"))){
					contador++;
				}
			}
			con.cerrarConexion();
			con.rs.close();
			if(contador>0){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error");
		}
		return false;
	}
}

package bd;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.Cliente;

public class ClienteQuery extends Query {
	private ConexionSQL con = new ConexionSQL();
	public DefaultTableModel mostrar(){
		con.conectar();
		consulta ="SELECT cedula,nombre,apellido,edad,Tipo,correo,telefono,direccion FROM cliente,tipoSexo WHERE sexo=cod";
		DefaultTableModel modelo;
		String[] titulos = {"Cedula","Nombre","Apellido","Edad","Sexo","Correo","Telefono","Direccion"};
		String[] registro = new String[8];
		modelo = new DefaultTableModel(null, titulos);
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				registro[0] = con.rs.getString("cedula").toString();
				registro[1] = con.rs.getString("nombre");
				registro[2] = con.rs.getString("apellido");
				registro[3] = con.rs.getString("edad".toString());
				registro[4] = con.rs.getString("Tipo");
				registro[5] = con.rs.getString("correo");
				registro[6] = con.rs.getString("telefono");
				registro[7] = con.rs.getString("direccion");
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
			con.rs = con.stmt.executeQuery("SELECT Tipo FROM TipoSexo");
			while(con.rs.next()){
				combo.addItem(con.rs.getString("Tipo"));
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga del combo del Proveedor");
		}
	}
	
	public void insertar(Cliente c){
		consulta = "INSERT INTO cliente (cedula,nombre,apellido,edad,sexo,correo,telefono,direccion) VALUES ('"+c.getIdpersona()+"','"+c.getNombre()+"','"+c.getApellido()+"','"+c.getEdad()+"','"+c.getSexo()+"','"+c.getEmail()+"','"+c.getTelefono()+"','"+c.getDir()+"');";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null,"CLIENTE AGREGADO");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void actualizar(Cliente c){
		consulta = "UPDATE cliente SET nombre='"+c.getNombre()+"',apellido='"+c.getApellido()+"',edad='"+c.getEdad()+"',sexo='"+c.getSexo()+"',correo='"+c.getEmail()+"',telefono='"+c.getTelefono()+"',direccion='"+c.getDir()+"' WHERE cedula='"+c.getIdpersona()+"'";
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
	
	public void borrar(Cliente c){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate("DELETE FROM cliente where cedula='"+c.getIdpersona()+"'");
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "CLIENTE ELIMINADO DEL SISTEMA");	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public boolean verificar(int cedula){
		contador=0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT cliente.cedula FROM cliente WHERE cliente.cedula='"+cedula+"'");
			while(con.rs.next()){
				if(cedula==Integer.valueOf(con.rs.getString("cedula"))){
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

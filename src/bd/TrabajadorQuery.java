package bd;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.Trabajador;

public class TrabajadorQuery extends Query{
	private ConexionSQL con = new ConexionSQL();
	private Trabajador T = new Trabajador();
	public DefaultTableModel mostrar(){
		con.conectar();
		setContador(0);
		consulta ="SELECT trabajador.cod,trabajador.usuario,trabajador.nombre,trabajador.apellido,trabajador.email,trabajador.telefono,rango.Tipo,estado.Descripcion FROM trabajador,rango,estado WHERE trabajador.nivel=rango.cod and trabajador.status=estado.id";
		DefaultTableModel modelo;
		String[] titulos = {"ID","Usuario","Nombre","Apellido","Email","Telefono","Tipo","Estado"};
		String[] registro = new String[8];
		modelo = new DefaultTableModel(null, titulos);
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				registro[0] = con.rs.getString("cod").toString();
				registro[1] = con.rs.getString("usuario");
				registro[2] = con.rs.getString("nombre");
				registro[3] = con.rs.getString("apellido");
				registro[4] = con.rs.getString("email");
				registro[5] = con.rs.getString("telefono");
				registro[6] = con.rs.getString("Tipo");
				registro[7] = con.rs.getString("Descripcion");
				setContador(getContador() + 1);
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
	private String consulta;
	public void login(Trabajador T){
		con.conectar();
		setContador(0);
		consulta = "SELECT nombre,apellido,status,nivel FROM trabajador WHERE trabajador.usuario='"+T.getUsuario()+"' and trabajador.clave='"+T.getClave()+"'";
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				T.setNombre(con.rs.getString("nombre"));
				T.setApellido(con.rs.getString("apellido"));
				T.setEstado(Integer.parseInt(con.rs.getString("status")));
				T.setRango(Integer.parseInt(con.rs.getString("nivel")));
				setContador(getContador() + 1);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		con.cerrarConexion();
	}
	
	public void insertar(Trabajador J){
		consulta = "INSERT INTO trabajador (usuario,nombre,apellido,nivel,status,email,telefono,clave) VALUES ('"+J.getUsuario()+"','"+J.getNombre()+"','"+J.getApellido()+"','"+J.getRango()+"','"+J.getEstado()+"','"+J.getEmail()+"','"+J.getTelefono()+"','"+J.getClave()+"')";
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
	public void MOD(Trabajador J){
		consulta = "UPDATE trabajador SET usuario='"+J.getUsuario()+"',nombre='"+J.getNombre()+"',apellido='"+J.getApellido()+"',nivel='"+J.getRango()+"',status='"+J.getEstado()+"',email='"+J.getEmail()+"',telefono='"+J.getTelefono()+"',clave='"+J.getClave()+"' WHERE cod='"+J.getIdpersona()+"'";
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
	
	public void del(Trabajador J){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate("DELETE FROM trabajador where cod='"+J.getIdpersona()+"'");
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO DEL SISTEMA");	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public boolean verificar(String usuario){
		contador=0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT trabajador.usuario FROM trabajador WHERE trabajador.usuario='"+usuario+"'");
			while(con.rs.next()){
				if(usuario.equals(con.rs.getString("usuario"))){
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

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
}

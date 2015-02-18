package bd;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import logica.Producto;
import logica.Trabajador;
public class ProductoQuery extends Query {
	private ConexionSQL con = new ConexionSQL();
	public DefaultTableModel mostrar(){
		con.conectar();
		consulta ="SELECT producto.codigo,producto.nombre,inventario.cantidad,proveedor.nombre as Proveedor,producto.precioU,producto.precioM,producto.fecha,categoria.Descripcion as Categoria,producto.descripcion FROM producto,proveedor,categoria,inventario WHERE producto.codproveedor=proveedor.codigo and producto.idcategoria=categoria.codigo and producto.codigo=inventario.cod";
		DefaultTableModel modelo;
		String[] titulos = {"Codigo","Nombre","Cantidad","Proveedor","Precio Unitario","Precio Mayor","Fecha","Categoria","Descripcion"};
		String[] registro = new String[9];
		modelo = new DefaultTableModel(null, titulos);
		try{
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while (con.rs.next()){
				registro[0] = con.rs.getString("codigo").toString();
				registro[1] = con.rs.getString("nombre");
				registro[2] = con.rs.getString("cantidad");
				registro[3] = con.rs.getString("Proveedor");
				registro[4] = con.rs.getString("precioU");
				registro[5] = con.rs.getString("precioM");
				registro[6] = con.rs.getString("Fecha");
				registro[7] = con.rs.getString("Categoria");
				registro[8] = con.rs.getString("Descripcion");
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
	public void cargarCombo(JComboBox<String> combo,String campo,String consulta){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery(consulta);
			while(con.rs.next()){
				combo.addItem(con.rs.getString(campo));
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga del combo del Proveedor");
		}
	}
	
	public int getID(String nombre){
		int ca=0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT codigo FROM producto WHERE nombre='"+nombre+"'");
			while(con.rs.next()){
				ca = Integer.parseInt((con.rs.getString("codigo")));
			}
			con.cerrarConexion();
			con.rs.close();
			return ca;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga del combo del Proveedor");
			return -1;
		}
	}
	
	private int getCantidad(int s){
		int ca=0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT inventario.cantidad FROM inventario WHERE inventario.cod='"+s+"'");
			while(con.rs.next()){
				ca = Integer.parseInt((con.rs.getString("cantidad")));
			}
			con.cerrarConexion();
			con.rs.close();
			return ca;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error");
			return 0;
		}
	}
	
	public void restarStock(int cantidad, int id){
		cantidad=getCantidad(id)-cantidad;
		consulta = "UPDATE inventario SET cantidad='"+cantidad+"' WHERE cod='"+id+"'";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "STOCK ACTUALIZADO");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void AgregarStock(int cant, String nombre){
		contador = 0;
		int id=getID(nombre);
		contador = getCantidad(id) + cant; 
		consulta = "UPDATE inventario SET cantidad='"+contador+"' WHERE cod='"+id+"'";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "STOCK ACTUALIZADO");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public boolean existencia(int id,int cantidad){
		int ca=0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT producto.codigo,inventario.cantidad FROM producto,inventario WHERE producto.codigo='"+id+"' and inventario.cod=producto.codigo");
			while(con.rs.next()){
				ca = Integer.parseInt((con.rs.getString("cantidad")));
			}
			con.cerrarConexion();
			con.rs.close();
			if(ca>cantidad){
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null,"YA NO HAY SUFICIENTE STOCK");
				return false;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error");
			return false;
		}
	}
	
	public double getPrecio(int id, int cantidad){
		double precio=0.0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT precioU,precioM FROM producto WHERE producto.codigo='"+id+"'");
			while(con.rs.next()){
				if(cantidad>10)	precio = Double.parseDouble((con.rs.getString("precioM")));
				else precio = Double.parseDouble((con.rs.getString("precioU")));
			}
			con.cerrarConexion();
			con.rs.close();
			return precio;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga del combo del Proveedor");
			return 0.0;
		}
	}
	
	public void cargarCombo(JComboBox<String> combo){
		String nombre;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT codigo,nombre from proveedor,tipoProveedor WHERE proveedor.tipo=tipoProveedor.id");
			while(con.rs.next()){
				nombre = (con.rs.getString("codigo"))+" "+(con.rs.getString("nombre"));
				combo.addItem(nombre);
			}
			con.cerrarConexion();
			con.rs.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error durante la carga del combo del Proveedor");
		}
	}
	
	public void insertar(Producto p){
		consulta = "INSERT INTO producto (nombre,codproveedor,precioU,precioM,fecha,idcategoria,descripcion) VALUES ('"+p.getNombre()+"','"+p.getProveedor()+"','"+p.getPrecio()+"','"+p.getPrecioM()+"','"+p.getFecha()+"','"+p.getTipo()+"','"+p.getDescripcion()+"');";
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate(consulta);
			con.rs = con.stmt.executeQuery("SELECT codigo FROM producto WHERE nombre='"+p.getNombre()+"'");
			while(con.rs.next()){
				p.setCodigo(Integer.parseInt(con.rs.getString("codigo")));
			}
			consulta="INSERT INTO inventario (cod,cantidad,fechaUP) VALUES ('"+p.getCodigo()+"','"+Integer.parseInt("0")+"','"+p.getFecha()+"')";
			con.stmt.executeUpdate(consulta);
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null,"PRODUCTO AGREGADO");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void actualizar(Producto P){
		consulta = "UPDATE producto SET nombre='"+P.getNombre()+"',codproveedor='"+P.getProveedor()+"',precioM='"+P.getPrecioM()+"',precioU='"+P.getPrecio()+"',idcategoria='"+P.getTipo()+"',descripcion='"+P.getDescripcion()+"' WHERE nombre='"+P.getNombre()+"'";
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
	
	public void borrar(Producto p){
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.stmt.executeUpdate("DELETE FROM producto where nombre='"+p.getNombre()+"'");
			con.c.commit();
			con.cerrarConexion();
			JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO DEL SISTEMA");	
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public boolean verificar(String nombre){
		contador=0;
		try{
			con.conectar();
			con.c.setAutoCommit(false);
			con.stmt = con.c.createStatement();
			con.rs = con.stmt.executeQuery("SELECT producto.nombre FROM producto WHERE producto.nombre='"+nombre+"'");
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

package bd;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConexionSQL{
	private String consulta;
	public Connection c = null;
	public Statement stmt = null;
	public ResultSet rs = null;
	public PreparedStatement ps = null;
	private String nArchivo = "_sogespro.db";
	public void conectar(){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:_sogespro.db");
	    }catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      JOptionPane.showMessageDialog(null, "ERROR AL CONECTARSE A LA BASE DE DATOS");
	      System.exit(0);
	    }
	}
	public void detectarBD(){
        File fichero = new File(nArchivo);
        if (!fichero.exists()){
        	JOptionPane.showMessageDialog(null, "El fichero "+nArchivo+" no existe, se creara automaticamente");
        	crearBD();
        }
	}
    public void cerrarConexion(){
        try{
            stmt.close();
            c.close();
        }catch(Exception e){
        	JOptionPane.showMessageDialog(null, e);
        	JOptionPane.showMessageDialog(null, "Problemas al Cerrar la Conexion");
        }
    }
    public void crearBD(){
    	try{
    		conectar();
    		stmt = c.createStatement();
    		consulta = "PRAGMA foreign_keys = ON;"
    				+ "CREATE TABLE `inventario` (`cod`	INTEGER NOT NULL REFERENCES producto (codigo) ON UPDATE CASCADE ON DELETE RESTRICT, "
    				+ "`cantidad` INTEGER NOT NULL DEFAULT 0, "
    				+ "`fechaUP` TEXT NULL);"
    				+ "CREATE TABLE `estado_pedido` (`cod`	INTEGER NOT NULL, `status`	TEXT NOT NULL, PRIMARY KEY(cod));"
    				+ "INSERT INTO `estado_pedido` VALUES (1,'APROBADO');"
    				+ "INSERT INTO `estado_pedido` VALUES (2,'EN ESPERA');"
    				+ "CREATE TABLE `trabajador` ("
    				+ "`cod`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
    				+ "`usuario`	TEXT NOT NULL UNIQUE, "
    				+ "`nombre`	TEXT NOT NULL, "
    				+ "`apellido`	TEXT NOT NULL, "
    				+ "`nivel`	INTEGER NOT NULL, "
    				+ "`status`	INTEGER NOT NULL, "
    				+ "`email`	TEXT NOT NULL, "
    				+ "`telefono`	TEXT NOT NULL, "
    				+ "`clave`	TEXT NOT NULL);"
    				+ "INSERT INTO `trabajador` VALUES (1,'mart','MARTIN','CASTILLO',1,1,'asd@asd.com','435354353','123');"
    				+ "CREATE TABLE `tipoProveedor` ("
    				+ "`id`	INTEGER NOT NULL, "
    				+ "`Tipo`	TEXT NOT NULL UNIQUE, "
    				+ "PRIMARY KEY(id));"
    				+ "INSERT INTO `tipoProveedor` VALUES (1,'Detallista');"
    				+ "INSERT INTO `tipoProveedor` VALUES (2,'Mayorista');"
    				+ "CREATE TABLE `rango` ("
    				+ "`cod`	INTEGER NOT NULL, "
    				+ "`Tipo`	INTEGER NOT NULL UNIQUE, "
    				+ "PRIMARY KEY(cod));"
    				+ "INSERT INTO `rango` VALUES (1,'Administrador');"
    				+ "INSERT INTO `rango` VALUES (2,'Gerente');"
    				+ "INSERT INTO `rango` VALUES (3,'Vendedor');"
    				+ "INSERT INTO `rango` VALUES (4,'Almacenista');"
    				+ "CREATE TABLE `proveedor` ("
    				+ "`codigo`	INTEGER NOT NULL, "
    				+ "`nombre`	TEXT NOT NULL UNIQUE, "
    				+ "`direccion`	TEXT NOT NULL, "
    				+ "`email`	TEXT NOT NULL, "
    				+ "`descripcion`	TEXT, "
    				+ "`fecha`	TEXT NOT NULL, "
    				+ "`tipo`	INTEGER NOT NULL,"
    				+ "`telefono` TEXT NOT NULL, "
    				+ "PRIMARY KEY(codigo));"
    				+ "INSERT INTO `proveedor` VALUES (1,'ELECTRONICA GUARITOS','calle 105, av libertador','gfgfs@gmai.com','rgfhgfhgfhdf','30-02-2015','1','54353455');"
    				+ "CREATE TABLE `producto` ("
    				+ "`codigo`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
    				+ "`nombre`	TEXT NOT NULL UNIQUE, "
    				+ "`codproveedor`	INTEGER NOT NULL REFERENCES proveedor(codigo) ON UPDATE CASCADE ON DELETE RESTRICT, "
    				+ "`stock` INTEGER DEFAULT 0, "
    				+ "`precioU`	REAL NOT NULL, "
    				+ "`precioM`	REAL NOT NULL, "
    				+ "`fecha`	TEXT NOT NULL, "
    				+ "`idcategoria`	INTEGER NOT NULL, "
    				+ "`descripcion`	TEXT);"
    				+ "CREATE TABLE `pedido` ("
    				+ "`cod_ped`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
    				+ "`cantidad`	INTEGER NOT NULL, "
    				+ "`fechaPedido`	TEXT NOT NULL, "
    				+ "`idproducto`	INTEGER NOT NULL REFERENCES producto(codigo) ON UPDATE CASCADE ON DELETE RESTRICT, "
    				+ "`idtrabajador`	INTEGER NOT NULL REFERENCES trabajador(cod) ON UPDATE CASCADE ON DELETE RESTRICT);"
    				+ "CREATE TABLE `estado` ("
    				+ "`id`	INTEGER NOT NULL, "
    				+ "`Descripcion`	TEXT NOT NULL UNIQUE, "
    				+ "PRIMARY KEY(id));"
    				+ "INSERT INTO `estado` VALUES (1,'Habilitada');"
    				+ "INSERT INTO `estado` VALUES (2,'Deshabilitado');"
    				+ "CREATE TABLE `cliente` ("
    				+ "`cedula`	INTEGER NOT NULL, "
    				+ "`nombre`	TEXT NOT NULL, "
    				+ "`apellido`	TEXT NOT NULL, "
    				+ "`edad`	INTEGER NOT NULL, "
    				+ "`sexo`	INTEGER NOT NULL, "
    				+ "`correo`	TEXT NOT NULL, "
    				+ "`telefono`	TEXT NOT NULL, "
    				+ "`direccion`	TEXT, "
    				+ "PRIMARY KEY(cedula));"
    				+ "CREATE TABLE `tipoSexo` (`cod`	INTEGER NOT NULL, `Tipo` TEXT NOT NULL UNIQUE, PRIMARY KEY(cod));"
    				+ "CREATE TABLE `categoria` ("
    				+ "`codigo`	INTEGER NOT NULL, "
    				+ "`Descripcion`	TEXT NOT NULL, "
    				+ "PRIMARY KEY(codigo));"
    				+ "INSERT INTO `categoria` VALUES (1,'COMPONENTES');"
    				+ "INSERT INTO `categoria` VALUES (2,'SOFTWARE');"
    				+ "INSERT INTO `categoria` VALUES (3,'HARDWARE');"
    				+ "INSERT INTO `categoria` VALUES (4,'RESPUESTO');"
    				+ "INSERT INTO `categoria` VALUES (5,'OTROS');"
    				+ "CREATE TABLE `factura` (`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
    				+ "`fecha`	TEXT NOT NULL, "
    				+ "`idcliente`	INTEGER NOT NULL);"
    				+ "CREATE TABLE `venta` ("
    				+ "`cod`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
    				+ "`idpro`	INTEGER NOT NULL REFERENCES producto(codigo) ON UPDATE CASCADE ON DELETE RESTRICT, "
    				+ "`idcliente`	INTEGER NOT NULL REFERENCES cliente(cedula) ON UPDATE CASCADE ON DELETE RESTRICT, "
    				+ "`cantida`	INTEGER NOT NULL);"
    				+ "INSERT INTO `producto` VALUES (1,'WINDOWS 7 PRO',1,1,2000.0,1500.0,'31/02/2015',2,'Sistema Operativo');"
    				+ "INSERT INTO `inventario` VALUES (1,10,10/02/2015);"
    				+ "INSERT INTO `cliente` VALUES (12435123,'FRANK','DIAZ',33,1,'fra@gmail.com','041234343','NO DISPONIBLE');"
    				+ "INSERT INTO `pedido` VALUES (1,100,'10/02/2015',1,1);"
    				+ "INSERT INTO `tipoSexo` VALUES (1,'HOMBRE');"
    				+ "INSERT INTO `tipoSexo` VALUES (2,'MUJER');";
    		stmt.executeUpdate(consulta);
    		cerrarConexion();/*       */
        }catch(Exception e){
        	JOptionPane.showMessageDialog(null, e);
        	e.printStackTrace();
        }
    	
    }
}

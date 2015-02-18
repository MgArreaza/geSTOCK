 package logica;

import gui.Login;
import bd.ConexionSQL;

public class geSTOCK {
	public static void main(String[]ASD){
		ConexionSQL con = new ConexionSQL();
		Sistema conf = new Sistema();
		conf.setTema();
		con.detectarBD();
		Login login = new Login();
	}
}

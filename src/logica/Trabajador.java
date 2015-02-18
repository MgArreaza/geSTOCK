package logica;

public class Trabajador extends Persona {
	private int estado;
	private int rango;
	private String usuario;
	private String clave;
	
	public Trabajador(int estado, int rango, String usuario, String clave) {
		super();
		this.estado = estado;
		this.rango = rango;
		this.usuario = usuario;
		this.clave = clave;
	}
	
	public Trabajador() {

	}

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getRango() {
		return rango;
	}
	public void setRango(int rango) {
		this.rango = rango;
	}
	
}

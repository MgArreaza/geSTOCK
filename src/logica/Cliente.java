package logica;

public class Cliente extends Persona{
	private int sexo;
	private int edad;
	private String dir;
	private String fechaAgregado;

	public Cliente(){
		
	}
	
	public Cliente(int cedula, int estado_cliente,int edad, int sexo) {
		super();
		this.setIdpersona(cedula);
		this.edad = edad;
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getFechaAgregado() {
		return fechaAgregado;
	}
	public void setFechaAgregado(String fechaAgregado) {
		this.fechaAgregado = fechaAgregado;
	}
}

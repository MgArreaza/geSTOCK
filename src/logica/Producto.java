package logica;

public class Producto {
	
	private int codigo;
	private int stock;
	private int tipo;
	private int proveedor;
	private String nombre;
	private String fecha;
	private double precio;
	private String descripcion;
	private double precioM;
	
	public Producto(int codigo, int stock, int tipo, int proveedor,
			String nombre, String fecha, double precio) {
		super();
		this.codigo = codigo;
		this.stock = stock;
		this.tipo = tipo;
		this.proveedor = proveedor;
		this.nombre = nombre;
		this.fecha = fecha;
		this.precio = precio;
	}

	public Producto(){
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getProveedor() {
		return proveedor;
	}

	public void setProveedor(int proveedor) {
		this.proveedor = proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioM() {
		return precioM;
	}

	public void setPrecioM(double precioM) {
		this.precioM = precioM;
	}
}

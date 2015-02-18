package bd;

import javax.swing.table.DefaultTableModel;

public abstract class Query {
	protected String consulta;
	protected abstract DefaultTableModel mostrar();
	protected int contador=0;
}

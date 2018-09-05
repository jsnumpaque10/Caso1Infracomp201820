
public class main {
	
	public static void main ( String args[])
	{
		int capacidadBuffer = 20;
		int cantidadClientes = 20;
		int cantidadServidores = 100;
		int cantidadMensajesXCliente = 5;
		
		Buffer buffer = new Buffer(capacidadBuffer);
		Servidor[] servidores = new Servidor[cantidadServidores];
		Cliente[] clientes = new Cliente[cantidadClientes];
		
		for (int i = 0; i < servidores.length; i++) 
		{
			servidores[i] = new Servidor(buffer);
			servidores[i].start();
		}
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(buffer,cantidadMensajesXCliente);
			clientes[i].start();
		}
		
		
	}

}

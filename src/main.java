
public class main {
	
	public static void main ( String args[])
	{
		int capacidadBuffer = 20;
		int cantidadClientes = 20;
		int cantidadServidores = 100;
		int cantidadMensajesXCliente = 5;
		
		/**
		 * Se inicializa el buffer 
		 */
		Buffer buffer = new Buffer(capacidadBuffer);
		/**
		 * Se inicializan los arreglos que van a contener los threads
		 */
		Servidor[] servidores = new Servidor[cantidadServidores];
		Cliente[] clientes = new Cliente[cantidadClientes];
		
		/**
		 * Se inicializan los servidores
		 */
		
		for (int i = 0; i < servidores.length; i++) 
		{
			servidores[i] = new Servidor(buffer);
			servidores[i].start();
		}
		
		/**
		 * Se inicializan los clientes
		 */
		
		for (int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(buffer,cantidadMensajesXCliente);
			clientes[i].start();
		}
		
		
	}

}

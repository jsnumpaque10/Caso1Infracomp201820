import java.util.ArrayList;

public class Buffer
{
	/**
	 * Variable que modela la cantidad de clientes del sistema en un tiempo dado.
	 */
	private int numClientes; 
	
	/**
	 * Variable que modela la cantidad limitada de mensajes que puede almacenar el buffer.
	 */
	private int capacidadMensajes;
	
	/**
	 * Variable que modela la cantidad de mensajes que hay en el buffer en un momento dado.
	 */
	private int cantidadMensajes;
	
	/**
	 * Contenedor de todos los mensajes que recibe el buffer.
	 */
	private ArrayList<Mensaje> Mensajes;
	
	public Buffer(int pCapacidadMensajes)
	{
		
		setCapacidadMensajes(20);
		setNumClientes(0);
		setCantidadMensajes(0);
		Mensajes = new ArrayList<Mensaje>();

	}
	
	/**
	 * M�todo que ingresa un cliente al buffer para que pueda enviar mensajes.
	 */
	public void ingresarCliente()
	{
		synchronized(this){
			numClientes++;
		}
		
	}
	
	/**
	 * M�todo que retira un cliente del buffer cuando y ha enviado todos sus mensajes.
	 */	
	public void retirarCliente()
	{
		synchronized(this){
			numClientes--;
		}
	}
	
	public  Mensaje enviarMensaje()
	{ 
		
	Mensaje enviar = null;
		synchronized(this)
		{
			if (Mensajes.size() > 0)
			{
				
				enviar = Mensajes.get(0);
				Mensajes.remove(0);
			}
				
		
		}
		return enviar;
	}
	
	

	public int getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(int numClientes) {
		this.numClientes = numClientes;
	}

	public int getCapacidadMensajes() {
		return capacidadMensajes;
	}

	public void setCapacidadMensajes(int capacidadMensajes) {
		this.capacidadMensajes = capacidadMensajes;
	}

	public int getCantidadMensajes() {
		return cantidadMensajes;
	}

	public void setCantidadMensajes(int cantidadMensajes) {
		this.cantidadMensajes = cantidadMensajes;
	}
	
	
	public boolean recibir(Mensaje mensaje) 
	{
		synchronized (this) {
		if(cantidadMensajes<capacidadMensajes)
		{
			
				cantidadMensajes ++;
				Mensajes.add(mensaje);
				try {
					mensaje.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			
		}
		else
		{
			return false;
		}
		
		}
		
		
		
		
		
	}
	
	

}

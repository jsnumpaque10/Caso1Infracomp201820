package thread;

import vo.VOMensaje;

public class Cliente extends Thread
{
	//ATRIBUTOS
	
	/**
	 * Modela los mensajes del cliente
	 */
	private VOMensaje mensajes;

	/**
	 * Modela el identificador del cliente
	 */
	private int idCliente;

	//CONSTRUCTOR
	
	/**
	 * Constructor de la clase
	 * @param numeroDeMensajes
	 * @param idCliente
	 */
	public Cliente(int numeroDeMensajes, int idCliente) 
	{
		this.mensajes = new VOMensaje(numeroDeMensajes, idCliente);
		this.idCliente = idCliente;
	}

	//RUN DEL OBJETO
	
	@Override
	public void run()
	{
		System.out.println("El cliente " + idCliente + " ha empezado a enviar mensajes");
		while(!mensajes.seHanRespondidoTodosLosMensajes())
		{
			while(!mensajes.sePuedeEnviarABuffer()) 
			{
				yield();
			}
			mensajes.enviarMensajeABuffer(idCliente);
		}
		System.out.println("El cliente " + idCliente + " ha recibido todos sus mensajes");		
	}
}

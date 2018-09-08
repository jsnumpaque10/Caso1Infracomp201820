package buffer;
import java.util.ArrayList;

import vo.VOMensaje.VOMensajeRespuesta;

public class Buffer
{
	//ATRIBUTOS
	
	/**
	 * Modela el número de clientes que enviarán mensajes
	 */
	private int numClientes;
	
	/**
	 * Modela la capacidad máxima que puede almacenar el buffer
	 */
	private int capacidadMensajes;

	/**
	 * Modela objetos para modelar el buffer
	 */
	private Object lleno, vacio;

	/**
	 * Contenedor de todos los mensajes que recibe el buffer.
	 */
	private ArrayList<VOMensajeRespuesta> mensajes;

	//CONSTRUCTOR
	
	/**
	 * Constructor de la clase
	 * @param pCapacidadMensajes
	 * @param clientes
	 */
	public Buffer(int pCapacidadMensajes, int clientes)
	{
		this.numClientes = clientes;
		this.capacidadMensajes = pCapacidadMensajes;
		mensajes = new ArrayList<VOMensajeRespuesta>();
		lleno = new Object();
		vacio = new Object();
	}

	//MÉTODOS
	
	/**
	 * EnvÃ­a un mensaje al servidor
	 * @return El mensaje enviado
	 */
	public  VOMensajeRespuesta enviarMensaje()
	{ 
		VOMensajeRespuesta respuesta;
		synchronized( vacio )
		{
			while (mensajes.size( ) == 0 )
			{ 
				try 
				{ 
					vacio.wait( );
				}
				catch( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
		}
		synchronized(this)
		{		
			respuesta = mensajes.remove(0); 
			if(respuesta.esElUltimoMensaje())
			{
				numClientes--;
			}
		}
		synchronized(lleno)
		{ 
			lleno.notify();
		}
		return respuesta;

	}
	/**
	 * Recibe un mensaje del cliente
	 * @param mensaje El mensaje recibido
	 * @return TRUE si el mensaje se recibiÃ³ y FALSE de lo contrario
	 */
	public void recibir(VOMensajeRespuesta mensaje) 
	{
		synchronized(lleno)
		{
			while ( mensajes.size() == capacidadMensajes )
			{
				try 
				{
					lleno.wait(); 
				}
				catch( InterruptedException e )
				{
					e.printStackTrace();
				}
			}
		}
		synchronized(this)
		{
			mensajes.add( mensaje ); 
		}
		synchronized(vacio)
		{ 
			vacio.notify(); 
		}
	}

	/**
	 * Retorna el numero de mensajes qeu se encuentran en el buffer
	 * @return tamaño del array de mensajes
	 */
	public synchronized int mensajesDisponiblesParaServidor() 
	{
		return mensajes.size();
	}

	/**
	 * Retorna si el buffer está en su máxima capacidad
	 * @return true si el size del array de mensajes es igual a la capacidad de mensajes
	 */
	public synchronized boolean estaLleno() 
	{
		return mensajes.size() == capacidadMensajes;
	}

	/**
	 * Retorna si aún hay clientes por atender
	 * @return false si numClientes es mayor a 0.True de lo contrario
	 */
	public synchronized boolean aunHayClientes() 
	{
		return numClientes != 0;
	}

}

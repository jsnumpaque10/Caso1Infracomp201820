package vo;

import java.util.ArrayList;
import java.util.Random;

import buffer.Buffer;

public class VOMensaje
{
	//ATRIBUTOS ESTÁTICOS
	
	/**
	 * Modela el id del cliente al que pertenece
	 */
	private int idCliente;
	
	/**
	 * Modela al buffer que comunica a los clientes con los servidores
	 */
	private static Buffer buffer;
	
	//ATRIBUTOS 
	
	/**
	 * Modela el numero de mensajes que ya fueron respondidos por un servidor
	 */
    private int numMensajesVistos;
    
    /**
     * Modela el número de mensajes que enviará el cliente
     */
	private int numMensajes;
	
	/**
	 * Modela los mensajes del cliente
	 */
	private ArrayList<VOMensajeRespuesta> mensajes;
		
	//NESTED CLASSES
	
	/**
	 * Objeto que modela el mensaje de un cliente con su respectiva respuesta
	 */
	public class VOMensajeRespuesta
	{
		//ATRIBUTOS
		
		/**
		 * modela a su contenedor
		 */
		private VOMensaje contenedor;
		
		/**
		 * Modela un mensaje del cliente
		 */
		private int mensaje;
		
		/**
		 * Modela la respuesta que el servidor le da al mensaje del cliente
		 */
		private int respuesta;
		
		/**
		 * Dice si es el último mensaje que elcliente envía
		 */
		private boolean esElUltimoMensaje;
		
		//CONSTRUCTOR 
		
		/**
		 * Constructor de la clase
		 * @param pMensaje valor del mensaje del cliente
		 */
		public VOMensajeRespuesta(int pMensaje)
		{
			this.mensaje = pMensaje;
		}

		//GETTERS AND SETTERS
		
		/**
		 * @return the mensaje
		 */
		public int getMensaje()
		{
			return mensaje;
		}

		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(int mensaje)
		{
			this.mensaje = mensaje;
		}

		/**
		 * @return the respuesta
		 */
		public int getRespuesta()
		{
			return respuesta;
		}

		/**
		 * @param respuesta the respuesta to set
		 */
		public void setRespuesta(int respuesta)
		{
			this.respuesta = respuesta;
		}

		/**
		 * @return the esElUltimoMensaje
		 */
		public boolean esElUltimoMensaje() 
		{
			return esElUltimoMensaje;
		}

		/**
		 * @param esElUltimoMensaje the esElUltimoMensaje to set
		 */
		public void setEsElUltimoMensaje(boolean esElUltimoMensaje)
		{
			this.esElUltimoMensaje = esElUltimoMensaje;
		}

		/**
		 * @return the contenedor
		 */
		public VOMensaje getContenedor() 
		{
			return contenedor;
		}

		/**
		 * @param contenedor the contenedor to set
		 */
		public void setContenedor(VOMensaje contenedor) 
		{
			this.contenedor = contenedor;
		}

		/**
		 * @return the esElUltimoMensaje
		 */
		public boolean isEsElUltimoMensaje()
		{
			return esElUltimoMensaje;
		}							
	}
	
	//CONSTRUCTOR - INIT
	
	/**
	 * Inicializa al objeto con el static buffer
	 * @param buffercito
	 */
	public static void inicializar(Buffer buffercito)
	{
		buffer = buffercito;
	}
	
	/**
	 * Constructor de la clase
	 * @param numMensajes numero de mensajes que enviará el cliente!
	 */
	public VOMensaje(int numMensajes, int pIdCliente)
	{
	
		idCliente = pIdCliente;
		this.mensajes = new ArrayList<>();
		this.numMensajes = numMensajes;
		this.numMensajesVistos = 0;
		for (int i = 0; i < numMensajes; i++) 
		{
			VOMensajeRespuesta mensajito = new VOMensajeRespuesta(randInt(0, 1000));
			mensajito.setContenedor(this);
			if(i == numMensajes-1)
			{
				mensajito.setEsElUltimoMensaje(true);
			}
			else
			{
				mensajito.setEsElUltimoMensaje(false);
			}
			mensajes.add(mensajito);	
		}
	}
	
	// METODOS
	
	/**
	 * Indica si el buffer está lleno (o no) con el fin de decidir si se puede o no enviarle un mensaje
	 * @return
	 */
	public boolean sePuedeEnviarABuffer()
	{
		return !buffer.estaLleno();
	}
	
	/**
	 * Envía uno de los mensajes del cliente y deja dormido al cliente hasta que llegue respuesta
	 * @throws InterruptedException
	 */
	public synchronized void enviarMensajeABuffer(int idClient)
	{
		VOMensajeRespuesta mensajito = this.mensajes.get(numMensajesVistos);
		
		//Aumentamos el numero de mensajes vistos por 1
	    numMensajesVistos++;
				
		buffer.recibir(mensajito);
		try 
		{
			wait();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		imprimirRespuesta();
	}
	
	/**
	 * Se ejectuta cuando un servidor responde un mensaje, indicando que el cliente puede enviar
	 * otro mensaje al buffer!
	 */
	public synchronized void mensajeRespondido()
	{
		notify();
	}
	
	/**
	 * Se ejecuta cuando un servidor responde a uno de los mensajes
	 */
	public void imprimirRespuesta()
	{
		//Imprimimos la respuesta
		System.out.println("El cliente " + idCliente + " ha recibido la respuesta " + mensajes.get(numMensajesVistos-1).getRespuesta() + " a su mensaje " + mensajes.get(numMensajesVistos-1).getMensaje());	
	}
	
	/**
	 * Indica si se han respondido todos los mensajes
	 * @return true si sí, false de lo contrario
	 */
	public boolean seHanRespondidoTodosLosMensajes()
	{
		return (numMensajesVistos) == numMensajes;
	}
	
	/**
	 * Genera un numero random entre el rango indicado
	 * @param min
	 * @param max
	 * @return num random
	 */
	public static int randInt(int min, int max) 
	{
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/**
	 * @return the mensajes
	 */
	public ArrayList<VOMensajeRespuesta> getMensajes()
	{
		return mensajes;
	}
}

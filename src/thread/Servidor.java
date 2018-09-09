package thread;

import buffer.Buffer;
import vo.VOMensaje.VOMensajeRespuesta;

/**
 * Clase que modela una thread servidor, se encarga de comunicarce con el buffer y 
 * responder los mensajes que vaya extrayendo de allí
 */
public class Servidor extends Thread
{
	
	//ATRIBUTOS
	/**
	 * Modela el identificador del servidor
	 */
	private int idServidor;
	
	//ATRIBUTOS ESTÁTICOS
	
	/**
	 * Modela el buffer static
	 */
	private static Buffer buffer;

	//CONSTRUCTOR
	
	/**
	 * Constructor del servidor
	 * @param idServidor
	 */
	public Servidor(int idServidor)
	{
		this.idServidor = idServidor;		
	}

	//INIT
	
	/**
	 * Inicializa el buffer compartido con los clientes
	 * @param buff
	 */
	public static void inicializar(Buffer buff)
	{
		buffer = buff;
	}
	
	//RUN DEL OBJETO
	
	@Override
	public void run()
	{
		System.out.println("El servidor "+this.idServidor+" está listo para responder");
			while(buffer.aunHayClientes())
			{
				if(buffer.mensajesDisponiblesParaServidor()  == 0)
				{
					yield();
				}
				VOMensajeRespuesta mensajito = buffer.enviarMensaje();
		        responder(mensajito);
			}
			System.out.println("El servidor "+this.idServidor+" finalizo de ejecutarse"); 
	}
	
	//MÉTODOS
	
	/**
	 * Responde un mensaje
	 * @param mensaje ha responder
	 */
	public void responder(VOMensajeRespuesta mensaje)
	{
		int mensajeValue = mensaje.getMensaje();
		int respuestaServidor = mensajeValue + 1;
		mensaje.setRespuesta(respuestaServidor);
		mensaje.getContenedor().mensajeRespondido();
	}
}
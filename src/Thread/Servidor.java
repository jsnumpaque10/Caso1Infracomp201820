package Thread;

import Buffer.Buffer;
import VO.VOMensaje;

public class Servidor extends Thread
{
	private Buffer buffer;
	private VOMensaje mensajeActual ;
	
	/**
	 * Método constructor de la clase Servidor
	 */
	
	public Servidor (Buffer pBuffer)
	{
		setBuffer(pBuffer);
		setMensajeActual(null);
	}
	
	
	public void run()
	{
		while(buffer.getNumClientes() > 0)
		{
			setMensajeActual(buffer.enviarMensaje());
			if(mensajeActual == null)
			{
				this.yield();
			}
			else
			{
				mensajeActual.setRespuesta(mensajeActual.getPregunta() +1 );
				mensajeActual.notify();
			}
			
		}
		
	}

	/**
	 *  Retorna el mensaje que está procesando el servidor
	 * @return El mensaje deseado
	 */
	public VOMensaje getMensajeActual() {
		return mensajeActual;
	}

	/**
	 * Configura el mensaje que va a procesar el servidor
	 * @param mensajeActual
	 */
	public void setMensajeActual(VOMensaje mensajeActual) {
		mensajeActual = mensajeActual;
	}

	/**
	 * Configura el buffer que se va a comunicar con el servidor
	 * @param buffer
	 */
	public void setBuffer(Buffer buffer) {
		buffer = buffer;
	}
		

}
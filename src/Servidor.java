public class Servidor extends Thread
{
	private Buffer buffer;
	private Mensaje mensajeActual ;
	
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
			if(getMensajeActual() == null)
			{
				this.yield();
			}
			else
			{
				getMensajeActual().setRespuesta(getMensajeActual().getPregunta() +1 );
				getMensajeActual().notify();
			}
			
		}
		
	}

	/**
	 *  Retorna el mensaje que está procesando el servidor
	 * @return El mensaje deseado
	 */
	public Mensaje getMensajeActual() {
		return mensajeActual;
	}

	/**
	 * Configura el mensaje que va a procesar el servidor
	 * @param mensajeActual
	 */
	public void setMensajeActual(Mensaje mensajeActual) {
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
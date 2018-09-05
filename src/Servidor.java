public class Servidor extends Thread
{
	private static Buffer buffer;
	private Mensaje mensajeActual ;
	
	/**
	 * Método constructor de la clase Servidor
	 */
	
	public Servidor (Buffer pBuffer)
	{
		buffer = pBuffer;
		mensajeActual=null;
	}
	
	
	public void run()
	{
		while(buffer.getNumClientes() > 0)
		{
			mensajeActual = buffer.enviarMensaje();
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
	

}
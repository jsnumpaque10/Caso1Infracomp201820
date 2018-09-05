import java.util.Random;

public class Cliente extends Thread
{
	private Mensaje mensaje;
	private Buffer buffer;
	private int cantidadMensajes; 
	private int mensajesEnviados;
	
	/**
	 * Método constructor de la clase Cliente
	 */
	
	public Cliente (Buffer pBuffer, int pCantidadMensajes )
	{
		setBuffer(pBuffer);
		setCantidadMensajes(pCantidadMensajes);
		setMensajesEnviados(0);
		setMensaje(null);
	}
	
	/**
	 * Instrucciones que sigue el thread para enviar mensajes
	 */	
	public void run()
	{
		buffer.ingresarCliente();
		
		boolean exito;
		
		while(mensajesEnviados < cantidadMensajes)
		{
			
		setMensaje(new Mensaje(randInt(0,999999)));
		 exito = buffer.recibir(mensaje);
		 if(exito){
		setMensajesEnviados(mensajesEnviados + 1);
		 }
		 else
		 {
			 this.yield();
		 }

		
		}
		buffer.retirarCliente();
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
	

	public void setMensajesEnviados(int mensajesEnviados) {
		this.mensajesEnviados = mensajesEnviados;
	}


	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void setCantidadMensajes(int cantidadMensajes) {
		this.cantidadMensajes = cantidadMensajes;
	}
	

	 

}

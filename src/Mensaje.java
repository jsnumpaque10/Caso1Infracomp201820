
public class Mensaje
{
	private int pregunta;
	private int respuesta;
	
	public Mensaje (int pPregunta)
	{
		setPregunta(pPregunta);
	}
	
	/**
	 *  Retorna la pregunta que el cliente envía al buffer
	 * @return El mensaje o pregunta enviado al buffer
	 */
	public int getPregunta() {
		return pregunta;
	}
	
	/**
	 * Cambia el mensaje que se va a enviar al buffer
	 * @param pregunta
	 */
	public void setPregunta(int pregunta) {
		this.pregunta = pregunta;
	}
	
	/**
	 * Retorna la respuesta enviada por el servidor
	 * @return La respuesta deseada
	 */
	public int getRespuesta() {
		return respuesta;
	}
	
	/**
	 * Configura la respuesta que va a enviar el servidor
	 * @param respuesta La respuesta que se desea enviar
	 */

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	

}

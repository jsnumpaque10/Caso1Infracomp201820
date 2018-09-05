
public class Mensaje
{
	private int pregunta;
	private int respuesta;
	
	public Mensaje (int pPregunta)
	{
		setPregunta(pPregunta);
	}

	public int getPregunta() {
		return pregunta;
	}

	public void setPregunta(int pregunta) {
		this.pregunta = pregunta;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	

}

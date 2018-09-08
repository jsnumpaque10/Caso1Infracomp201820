package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import buffer.Buffer;
import thread.Cliente;
import thread.Servidor;
import vo.VOMensaje;

/**
 * Esta clase se encarga de correr la aplicación
 */
public class Principal
{
	/**
	 * Modela la direccion en la que se encuentran los datos con los que se inicializará la aplicación
	 */
	public static final String DATA_DIRECTORY = "./data/datosAplicacion.txt";
	
	/**
	 * Main de la aplicación
	 * @param args
	 */
	public static void main (String[] args)
	{
		FileReader fr;
		try 
		{
			
			//Se inicializa el file reader que se le pasa el buffer reader
			fr = new FileReader(DATA_DIRECTORY);
			
			//Creación de un un buffer reader para leer el archivo
			BufferedReader bfr = new BufferedReader(fr);
			try 
			{
				bfr.readLine();
				String[] data = bfr.readLine().split("-");
				
				//Obtenemos datos de la capacidad de buffer, numero servidores y numero clientes
				int numeroSevidores = Integer.parseInt(data[0]);
				int capacidadBuffer = Integer.parseInt(data[1]);
				int cantidadClientes = Integer.parseInt(data[2]);
				
				//Inicializamos arreglos servidores y clientes
				Servidor[] servidores = new Servidor[numeroSevidores];
				Cliente[] clientes = new Cliente[cantidadClientes];
				
				//Inicializamos el buffer
				Buffer buffercito = new Buffer(capacidadBuffer, cantidadClientes);
				Servidor.inicializar(buffercito);
				VOMensaje.inicializar(buffercito);
				
				//Inicializamos a los clientes con su número de mensajes
				bfr.readLine();
				String mensaje = bfr.readLine();
				int i = 0;
				while(mensaje != null)
				{
					clientes[i] = new Cliente(Integer.parseInt(mensaje), i);
					i++;
					mensaje = bfr.readLine();
				}
				
				//Llamamos el run de cada cliente
				for (int j = 0; j < clientes.length; j++) 
				{
					clientes[j].start();
				}
				//Inicializamos y damos run a cada servidor
				for (int j = 0; j < servidores.length; j++) 
				{
					servidores[j] = new Servidor(j);
					servidores[j].start();
				}
				
				
				
				
				bfr.close();
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("No hay archivo de inicialización de valores");
			e.printStackTrace();
		}	
	}

}

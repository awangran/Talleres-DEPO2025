package uniandes.dpoo.aerolinea.modelo;
import java.time.Duration;
import java.time.LocalTime;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */

public class Ruta
	{
	private Aeropuerto origen;
	
	private Aeropuerto destino;
	
	private java.lang.String horaSalida;
	
	private java.lang.String horaLlegada;
	
	private java.lang.String codigoRuta;

	
	public Ruta(Aeropuerto origen,
			Aeropuerto destino,
			java.lang.String horaSalida,
			java.lang.String horaLlegada,
			java.lang.String codigoRuta) {
	};

	public java.lang.String getCodigoRuta(){
		return this.codigoRuta;
	}
	
	public Aeropuerto getOrigen() {
		return this.origen;
	}
	
	public Aeropuerto getDestino() {
		return this.destino;
	}
	
	public java.lang.String getHoraSalida(){
		return this.horaSalida;
	}
	
	public java.lang.String getHoraLlegada(){
		return this.horaLlegada;
	}
	
	public int getDuracion() {
		
        LocalTime horaSalida = LocalTime.of(getHoras(this.horaSalida), getMinutos(this.horaSalida)); 
        LocalTime horaLlegada = LocalTime.of(getHoras(this.horaLlegada), getMinutos(this.horaLlegada));

        Duration duration = Duration.between(horaSalida, horaLlegada);

        int minutes = (int) duration.toMinutes();
        
        return minutes;

	};

    
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

  
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    
}

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
	        String horaSalida,
	        String horaLlegada,
	        String codigoRuta) {
	    this.origen = origen;
	    this.destino = destino;
	    this.horaSalida = horaSalida;
	    this.horaLlegada = horaLlegada;
	    this.codigoRuta = codigoRuta;
	}


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
	    LocalTime salida = LocalTime.of(getHoras(this.horaSalida), getMinutos(this.horaSalida));
	    LocalTime llegada = LocalTime.of(getHoras(this.horaLlegada), getMinutos(this.horaLlegada));

	    Duration duration = Duration.between(salida, llegada);
	    if (duration.isNegative()) {
	        duration = duration.plusDays(1);
	    }
	    return (int) duration.toMinutes();
	}


    
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

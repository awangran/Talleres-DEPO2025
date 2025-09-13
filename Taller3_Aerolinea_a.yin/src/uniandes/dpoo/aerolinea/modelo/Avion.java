package uniandes.dpoo.aerolinea.modelo;

public class Avion {
	private java.lang.String nombre;
	
	private int capacidad;

	public Avion(java.lang.String nombre, int capacidad) {
		nombre = this.nombre;
		
		capacidad = this.capacidad;
		
	};
	
	public java.lang.String getNombre(){
		return this.nombre;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}


}

package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	
	private Cliente cliente;
	
	private Vuelo vuelo;
	
	private java.lang.String codigo;
	
	private int tarifa;
	
	private boolean usado;
	
	public Tiquete(java.lang.String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
		
		clienteComprador = this.cliente;
		
		vuelo = this.vuelo;
		
		codigo = this.codigo;
		
		tarifa = this.tarifa;
		
		
	};
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Vuelo getVuelo() {
		return this.vuelo;
	}
	
	public java.lang.String getCodigo() {
		return this.codigo;
	}
	
	public int getTarifa() {
		return this.tarifa;
	}
	
	public void marcarComoUsado() {
		this.usado = true;
	}
	
	public boolean esUsado() {
		return this.usado == true;
	}


	
	




}

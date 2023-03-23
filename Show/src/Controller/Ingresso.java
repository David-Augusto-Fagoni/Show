package Controller;

import java.util.concurrent.Semaphore;

public class Ingresso extends Thread{
	private Semaphore fila;
	private int Cliente;
	private static int Total = 100;
	public Ingresso (Semaphore fila, int Cliente) {
		
		this.fila = fila;
		this.Cliente = Cliente;
	}
	public void run() {
		
			login();
}
			
	
	public void login() {
		int Ingresso = (int) Math.random()*4+1;
		long Tempo =  (long) (Math.random()*1951+50);
		try {
			sleep(Tempo);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		if (Tempo > 1000) {
			System.out.println("O cliente "+ Cliente +" passou mais de 1 segundo no login e levou Timeout");
		}else {
			Compra();
		}
	}
	public void Compra() {
		long Tempo = (long) (Math.random()*2001+1000);
		
		try {
			sleep(Tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (Tempo > 2500) {
			System.out.println("O cliente "+ Cliente +" passou mais de 2,5 segundos na compra e levou Timeout");
		}else {
			Validação();
		}
	}
	public void Validação() {
		try {
			fila.acquire();
			int Ingresso = (int) (Math.random()*4+1);
			
			if (Total > 0 && Ingresso<=Total) {
				System.out.println("O cliente "+ Cliente +" comprou "+Ingresso+" Ingresso(s), restam: "+Total+" Ingresso(s)");
				Total = Total - Ingresso;
			}else {
				System.out.println("O cliente "+ Cliente +" tentou comprar "+Ingresso+" Ingresso(s) mas não havia o suficiente, restam: "+Total+" Ingresso(s)");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			fila.release();
		}
	}
}

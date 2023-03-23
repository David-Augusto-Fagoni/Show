package View;
import java.util.concurrent.Semaphore;

import Controller.Ingresso;


public class Main {

	public static void main(String[] args) {
		Semaphore Fila = new Semaphore(1);
		for (int J = 0; J<300; J++) {
			Ingresso In = new Ingresso(Fila,J);
			In.start();
		}
	}

}

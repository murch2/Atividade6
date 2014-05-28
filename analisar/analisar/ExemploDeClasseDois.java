package analisar;

public class ExemploDeClasseDois {
	public void funcao (int a, int b, int c) {
		if (a > 0 && b == 0 && c == 0)
			System.out.println("Entrou no if");
	}

	public void metodoQualquer (int a, int b, int c) {
		if (a > 0 || b == 0 || c == 0)
			System.out.println("Entrou no if");
	}

}

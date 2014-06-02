package analisar;

import hash.HashTable;

public class ExemploDeClasseDois {
	
	public static void funcao (int a, int b, int c) {
//		Instrumentação do código.
		if (a > 0) {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"funcao.", "a > 0 && b == 0 && c == 0", "a > 0", true);
		}
		else {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"funcao.", "a > 0 && b == 0 && c == 0", "a > 0", false);
		}
		
		if (b == 0) {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"funcao.", "a > 0 && b == 0 && c == 0", "b == 0", true);
		} 
		else {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"funcao.", "a > 0 && b == 0 && c == 0", "b == 0", false);
		}
		
		if (c == 0) {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"funcao.", "a > 0 && b == 0 && c == 0", "c == 0", true);
		} 
		else {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"funcao.", "a > 0 && b == 0 && c == 0", "c == 0", false);
		}
		
		if (a > 0 && b == 0 && c == 0)
			System.out.println("Entrou no if");
	}

	public static void metodoQualquer (int a, int b, int c) {
//		Instrumentação de código
		
		if (a > 0) {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"metodoQualquer.", "a > 0 || b == 0 || c == 0", "a > 0", true);
		}
		else {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"metodoQualquer.", "a > 0 || b == 0 || c == 0", "a > 0", false);
		}
		
		if (b == 0) {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"metodoQualquer.", "a > 0 || b == 0 || c == 0", "b == 0", true);
		}
		else {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"metodoQualquer.", "a > 0 || b == 0 || c == 0", "b == 0", false);
		}
		
		if (c == 0) {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"metodoQualquer.", "a > 0 || b == 0 || c == 0", "c == 0", true);
		}
		else {
			HashTable.getInstance().setHashExecutados("ExemploDeClasseDois.",
					"metodoQualquer.", "a > 0 || b == 0 || c == 0", "c == 0", false);
		}
		
		if (a > 0 || b == 0 || c == 0)
			System.out.println("Entrou no if");
	}
}

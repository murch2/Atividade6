package analisar;

import hash.HashTable;

public class ExemploDeClasseUm {

    public void Metodo1 (int a, int b) {
//    		Instrumentação de código
    		if (a > 0) {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo1.", "a > 0 && b < 5", "a > 0", true);
    		}
    		else {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo1.", "a > 0 && b < 5", "a > 0", false);
    		}
    		
    		if (b < 5) {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo1.", "a > 0 && b < 5", "b < 5", true);
    		}
    		else {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo1.", "a > 0 && b < 5", "b < 5", false);
    		}
    		
            if (a > 0 && b < 5)
                    System.out.println("A eh maior que 0");
    }

    public void Metodo2 (int a, int b) {
    		if (a > b) {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo2.", "a > b || b > 3", "a > b", true);
    		} 
    		else {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo2.", "a > b || b > 3", "a > b", false);
    		}
    	
    		if (b > 3) {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo2.", "a > b || b > 3", "b > 3", true);
    		}
    		else {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseUm.",
    					"Metodo2.", "a > b || b > 3", "b > 3", false);
    		}
    		
            if (a > b || b > 3)
                    System.out.println("entrando no if");
    }
}


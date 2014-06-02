package analisar;

import hash.HashTable;

public class  ExemploDeClasseTres{
    public void funcao (int a, int b, int c) {
//    	Instrumentação do código
    	if (a > 0) {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "a > 0", true);
    	} 
    	else {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "a > 0", false);
    	}
    	
    	if (b == 0) {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "b == 0", true);
    	}
    	else {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "b == 0", false);
    	}
    	
    	if (c != 0) {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "c != 0", true);
    	}
    	else {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "c != 0", false);
    	}
    	
    	if (a < -5) {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "a < -5", true);
    	}
    	else {
    		HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
					"funcao.", "(a > 0 && b == 0  && c!= 0 )  ||  a < - 5", "a < -5", false);
    	}
    	
    	
    	if ((a > 0 && b == 0  && c!= 0 )  ||  a < - 5)
                    System.out.println("OK");
    }

    public void metodoDeTeste (boolean a) {
//    		Instrumentação de código
    		if (a) {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
    					"metodoDeTeste.", "a", "a", true);
    		} 
    		else {
    			HashTable.getInstance().setHashExecutados("ExemploDeClasseTres.",
    					"metodoDeTeste.", "a", "a", false);
    		}
    	
            if (a)
                    System.out.println("OK");
    }
}


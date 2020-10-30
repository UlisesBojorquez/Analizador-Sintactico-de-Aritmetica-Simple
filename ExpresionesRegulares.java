
public class ExpresionesRegulares {
	
	
	private String cadena,
				   patron,
				   sustituir;
	


	public ExpresionesRegulares(String cadena, String patron, String sustituir) {
		
		this.cadena=cadena;
		this.patron=patron;
		this.sustituir=sustituir;
		
	}
	
	
	public boolean is_match() {
		
		boolean[][] T=new boolean[this.cadena.length()+1][this.patron.length()+1];
		String[] c=this.cadena.split(""); 
		String[] p=this.patron.split(""); 
		//Se hace true la primera posicion
		T[0][0]=true;
		
		for(int i=0;i<c.length;i++)
			System.out.println(c[i]);
		 
		//Este for es exclusivamente para, a*, a*b*, a*b*c*
		//Inicia en 1 hasta length-1
		//Este for eciste porque puede hacer casos en los que nosotros no pasemos nada como cadena y el patron sea a* por ejemplo, esto si es aceptable.
		
		for(int i=1;i<T[0].length;i++) {
			 if(p[i-1]=="*" && T[0][i-2]==true) {
				 //Es una regla 
				 //T[0][i]=T[0][i-2];
				 T[0][i]=true;
			 }
		 }
		 
		 //Estos for iniciaran en 1 para poder recorrer toda la matrix empezando en 1 porque la posicion 0 se utilizo en la parte de arriba para verificar si el usuario paso una cadena sin simbolos
		 //Aqui ya se empiza a ver los matches
		for(int i=1;i<T.length;i++) {
			for(int j=1;j<T[i].length;j++) {
				 
				//Si las posiciones en la misma posicion son iguales
				if(p[j-1]=="." || p[j-1]==c[i-1]) {
					T[i][j]=T[i-1][j-1];
					 
				//Si el patron que sigue es un *
				}else if(p[j-1]=="*") {
					
					///
					if(p[j-2]=="." || p[j-2]==c[i-1]) {
						T[i][j]=T[i-1][j] || T[i][j-2];
					}else {
						T[i][j]=T[i][j-2];
					}
				}else {
					 T[i][j]=false;
				}
			}
		}
		
		for(int i=0;i<T.length;i++) {
			for(int j=0;j<T[i].length;j++) {
				System.out.print(T[i][j]+",");
			}
			System.out.println();
		}
		 
		
		 
		System.out.println(T[c.length][p.length]);
		return T[c.length][p.length];
		
	}
	
	
	public String solve() {
		
		String[] c=this.cadena.split(""); 
		String[] p=this.patron.split(""); 
		
		int clen=cadena.length();
		int plen=patron.length();
		
		char ch,ph;
		
		for(int i=0;i<clen;i++) {
			ch=cadena.charAt(i);
			ph=patron.charAt(0);
			
			if(ch==ph) {
				//for(int j=i,k=0;j<) {
					
				//}
			}
			
		}
		
		
		
		
		return "";
		
	}
	
	public boolean isMatch() {
        String res="";
		
        boolean[][] tabla = new boolean[this.cadena.length()+1][this.patron.length()+1];
        tabla[0][0] = true;
        
        char[] c = new char[this.cadena.length()+1];
        char[] p = new char[this.patron.length()+1];
        
        c[0] =p[0]= (char)0;
        
        for(int i=0;i<this.cadena.length();i++){
            c[i+1] = this.cadena.charAt(i);
        }
        
        for(int i=0;i<this.patron.length();i++){
            p[i+1] = this.patron.charAt(i);
        }
        
        for(int i=1;i<p.length;i++){
            if(p[i]=='*' && tabla[0][i-2]==true){
            	tabla[0][i] = true;
            }
        }
        
        for(int i=1;i<c.length;i++){
            for(int j=1;j<p.length;j++){
                if(p[j]==c[i] || p[j]=='.'){
                	tabla[i][j] = tabla[i-1][j-1];
                }else if(p[j]=='*'){
                    if(p[j-1]==c[i] || p[j-1]=='.'){ 
                    	tabla[i][j] = tabla[i][j-2] || tabla[i-1][j];
                    }else{
                    	tabla[i][j] = tabla[i][j-2];
                    }
                }
            }
        }
        
        for(int i=0;i<tabla.length;i++) {
        	//System.out.print(c[i]+"--");
        	for(int j=0;j<tabla[i].length;j++) {

        		System.out.print(tabla[i][j]+",");
        	}
        	System.out.println();
        }
        
        
        //"--------------------------------------------"
        if(!this.patron.contains("*")) {
        	
        	 for(int i=1;i<tabla.length;i++) {
             	for(int j=1;j<tabla[i].length;j++) {
             		
             	}
        	 }
        	
        }else if(this.patron.contains("*")){
        	
        	
        }
        
        return tabla[this.cadena.length()][this.patron.length()];

    }
	
	
	

	public static void main(String[] args) {
		
		ExpresionesRegulares expresion =new ExpresionesRegulares("aa b", "aa", "c");
		//expresion.solve();
		//expresion.is_match();
		System.out.println(expresion.isMatch());
	}

}

/*
Nombre: Ulises Bojorquez Ortiz
Matricula: A01114716
Materia: Matematicas Computacionales
PROYECTO SEGUNDO PARCIAL: ANALIZADOR SINTACTICO DE ARITMETICA SIMPLE 
*/

import java.util.Stack;

public class ExpresionAritmetica {
	
	private Stack<Character> pila, parentesis;
	private Stack<Double> operaciones;
	private char[] expresion;
	
	public ExpresionAritmetica(String ea) {
		this.expresion=ea.toCharArray();
		this.pila=new Stack<>();
		this.operaciones=new Stack<>();
		this.parentesis=new Stack<>();
	}
	
	//Maneja la prioridad de operadores y regresa un entero que representa su prioridad de mayor a menor
	public int prioridad(char operador) {
		if(operador=='(') {
			return 4;
		}else if(operador=='^') {
			return 3;
		}else if(operador=='*' || operador=='/') {
			return 2;
		}else if(operador=='+' || operador=='-') {
			return 1;
		}else {
			return 0;
		}
	}
	
	//Verifica si el caracter es operador
	public boolean esOperador(char caracter) {
		if(caracter=='(' || caracter=='*' || caracter=='+' || caracter=='-' || caracter==')' || caracter=='/' || caracter=='^') {
			return true;
		}else {
			return false;
		}
	}
	
	//Verifica si el caracter es numero o no
	public boolean esNumerico(char caracter) {
		String strNum=String.valueOf(caracter);
		
		if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	//Realiza las operaciones
	public double operacion(double num1, double num2, char operador) {
		if(operador=='*') {
			return num2*num1;
		}else if(operador=='/') {
			return num2/num1;
		}else if(operador=='^') {
			return Math.pow(num2, num1);
		}else if(operador=='+') {
			return num2+num1;
		}else{
			return num2-num1;
		}
	}
	
	//Valida toda la cadena es parte de la gramatica o no
	public boolean validador() {
		
		//Validar si tiene o no tiene ningun elemento
		boolean resNoVacio=true;
		try {
			this.expresion[0]=this.expresion[0];
		}catch(ArrayIndexOutOfBoundsException ex) {
			resNoVacio=false;
		}
		if(resNoVacio) {
			System.out.println("Lo cadena NO esta vacia");
		}else {
			System.out.println("Lo cadena esta vacia");
		}
		
		//Validador primer elemento
		boolean resPrimer=true;
		try {
			if(this.expresion[0]=='^' || this.expresion[0]=='*' || this.expresion[0]=='/' || this.expresion[0]=='-' || this.expresion[0]=='+') {
				resPrimer=false;
			}
		}catch(ArrayIndexOutOfBoundsException ex) {
			
		}  
		
		if(resPrimer) {
			System.out.println("No inicio con un operador");
		}else {
			System.out.println("Inicio con un operador");
		}
		
		
		//Validar operadores y parentesis
		boolean resOperadores=true;
		for(int i=0;i<this.expresion.length;i++) {
			//+,-,*/,^ y si es solo un operador
			if(this.expresion.length==1 && esOperador(this.expresion[i])) {
				resOperadores=false;
				break;
				
			// *),+), /), -),  ^)
			}else if((this.expresion[i]=='*' || this.expresion[i]=='-' || this.expresion[i]=='+' || this.expresion[i]=='/' || this.expresion[i]=='^') 
					&& this.expresion[i+1]==')') {
				resOperadores=false;
				break;
			
			//()() o () si tiene este tipo de formato no es valido
			}else if((i+1<this.expresion.length && this.expresion[i]==')' && this.expresion[i+1]=='(') || 
					(this.expresion[i]=='(' && this.expresion[i+1]==')')) {
				resOperadores=false;
				System.out.println("parentesis mal colocados");
				break;
			
			//*-, +/, -*, etc Operadores seguidos menos parentesis
			}else if((this.expresion[i]=='*' || this.expresion[i]=='-' || this.expresion[i]=='+' || this.expresion[i]=='/' || this.expresion[i]=='^')
					&& (this.expresion[i+1]=='*' || this.expresion[i+1]=='-' || this.expresion[i+1]=='+' || this.expresion[i+1]=='/' || this.expresion[i+1]=='^')) {
				resOperadores=false;
				System.out.println("ente operadores seguidos");
				break;
			}
		}
		
		if(resOperadores) {
			System.out.println("Tienes los operadores correctamente colocados");
		}else {
			System.out.println("NO Tienes los operadores correctamente colocados");
		}
		
		
		
		//Validador de aritmetica correcta
		boolean resAritmetica=true;
		for(int i=0;i<this.expresion.length;i++) {
			if(esOperador(this.expresion[i]) || esNumerico(this.expresion[i])) {
				resAritmetica=true;
			}else {
				resAritmetica=false;
				break;
			}
		}
		
		if(resAritmetica) {
			System.out.println("Tiene aritmetica valida");
		}else {
			System.out.println("NO Tiene aritmetica valida");
		}
		
		
		//Validador de parentesis bien ordenados
		boolean resParentesis, flag=true;
		
		for(int i=0;i<this.expresion.length;i++) {
			if(this.expresion[i]=='(' || this.expresion[i]==')') {
				if(this.expresion[i]=='(') {
					this.parentesis.push('(');
				}else if(this.expresion[i]==')' && !this.parentesis.isEmpty()) {
					this.parentesis.pop();
				}else {
					flag=false;
					break;
				}
			}
		}
		
		if(this.parentesis.isEmpty() && flag) {
			resParentesis=true;
			System.out.println("Los parentesis estan balanceados");
		}else {
			resParentesis=false;
			System.out.println("Los parentesis NO estan balanceados");
		}
		this.parentesis.clear(); //limpiar la pila
		
		//VERIFICAR SI PROCEDE A REALIZARCE O NO
		System.out.println("------------------------------------------------");
		if(resNoVacio && resPrimer && resOperadores && resAritmetica && resParentesis) {
			System.out.println("La operacion gramatica esta correctamente validada");
			System.out.println("------------------------------------------------");
			return true;
		}else {
			System.out.println("La operacion gramatica NO esta correctamente validada");
		}
		System.out.println("------------------------------------------------");
		return false;
	}
	
	//Funcion principal
	//Regresa una expresion postfija si es valida la cadena
	public String convertidor() {
		String expPostfija="";
		if(validador()) {
					
			for(int i=0;i<this.expresion.length;i++) {
				if(esOperador(this.expresion[i])) {
					if(this.expresion[i]=='(') {
						pila.push('(');
					}else if(this.expresion[i]==')') {
						while(!pila.isEmpty()) {
							if(pila.peek()!='(') {
								expPostfija+=pila.pop();
							}else {
								pila.pop();
								break;
							}
						}
					}else if(this.expresion[i]=='*' || this.expresion[i]=='+' || this.expresion[i]=='-' || this.expresion[i]=='/' || this.expresion[i]=='^') {
						if(!pila.isEmpty() && pila.peek()!='(') {
							if(prioridad(this.expresion[i])==prioridad(pila.peek())) {
								expPostfija+=pila.pop();
								pila.push(this.expresion[i]);
							}else if(prioridad(this.expresion[i])>prioridad(pila.peek())) {
								pila.push(this.expresion[i]);
							}else if(prioridad(this.expresion[i])<prioridad(pila.peek())) {
								while(!pila.isEmpty()) {
									expPostfija+=pila.pop();
								}
								pila.push(this.expresion[i]);
							}
						}else {
							pila.push(this.expresion[i]);
						}	
					}
				}else {
					expPostfija+=this.expresion[i];	
				}
			}
	
			while(!pila.isEmpty()) {
				expPostfija+=pila.pop();
			}
						
			evaluar(expPostfija);
			
			return expPostfija;
			
		}else {
			return "NO ES UNA OPERACION ARITMETICA VALIDA";
		}			
	} 
	
	//Evalua la expresion postfija e imprime el resultado
	public void evaluar(String expPostfija) {
		double valor;
		
		for(int i=0;i<expPostfija.length();i++) {
			
			if(!esOperador(expPostfija.charAt(i))) {
				this.operaciones.push((double) Character.getNumericValue(expPostfija.charAt(i)));
			}else {
				valor=operacion(this.operaciones.pop(), this.operaciones.pop(), expPostfija.charAt(i));
				this.operaciones.push(valor);
			}
		}
		System.out.println("El resultado es: "+this.operaciones.pop());
	}

	public static void main(String[] args) {
		
		//ExpresionAritmetica ea= new ExpresionAritmetica("(6+2)*3/2^2-4");
		ExpresionAritmetica ea= new ExpresionAritmetica("2/(3*2)");
		//ExpresionAritmetica ea= new ExpresionAritmetica("");
		
		String resultado=ea.convertidor();
		if(resultado=="NO ES UNA OPERACION ARITMETICA VALIDA") {
			System.out.println(resultado);
		}else {
			System.out.println("Expresión Aritmética en Postfijo: "+resultado);
			ArbolDeDerivacion ad=new ArbolDeDerivacion(resultado, "ej2.txt", "grafo2.jpg");
			ad.insercion();
		}		
	}
}

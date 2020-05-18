package br.com.codenation.calculadora;

// GitHub: MarceloSouza1983		e-mail: map_souza1983@gmail.com		Cel: (12) 98813-6040

public class CalculadoraSalario {

	private double salarioBruto, salarioBrutoINSS, salarioLiquido;
	public static final double descontoInssMaior = 0.89, descontoInssMedio = 0.91, descontoInssBaixo = 0.92;
	public static final double descontoIrpfMaior = 0.85, descontoIrpfMenor = 0.925;
	private String mensagem;
	
	public double getSalarioBruto() {
		return salarioBruto;
	}
	
	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioBrutoINSS() {
		return salarioBrutoINSS;
	}
	
	public void setSalarioBrutoINSS(double salarioBrutoINSS) {
		this.salarioBrutoINSS = salarioBrutoINSS;
	}
	
	public double getSalarioLiquido() {
		return salarioLiquido;
	}
	
	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	
	public long calcularSalarioLiquido(double salarioBruto) {
		//Use o Math.round apenas no final do m√©todo para arredondar o valor final.
		//Documenta√ß√£o do m√©todo: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-

		try {
			
			setSalarioBruto(salarioBruto);
			mensagem = "" + getSalarioBruto();
			
			if (verificaFormatoSalario(mensagem) == false) {
				System.out.println("Formato errado de dados. AtÈ Logo!");
				System.exit(0);
			}

			setSalarioBruto(Double.parseDouble(mensagem)); // Converte a string em double
			
			if (getSalarioBruto() < 1039.00) {
				System.out.println("O sal·rio base informado È menor que o sal·rio mÌnimo.\nCalculos n„o ser„o efetuados.\n");
				return (long) 0.0;
				
			} else {

				calcularDescontoInss(salarioBruto);
				calcularDescontoIRPF(getSalarioBrutoINSS());

				System.out.println("======================================");
				System.out.println("   O desconto do INSS foi de R$" + Math.round(getSalarioBruto() - getSalarioBrutoINSS()));
				System.out.println("   O desconto do IRPF foi de R$" + Math.round(getSalarioBrutoINSS() - getSalarioLiquido()));
				System.out.println("======================================");
				System.out.println("    Seu sal·rio lÌquido È: R$" + Math.round(getSalarioLiquido()));
				System.out.println("======================================\n");
				return Math.round(getSalarioLiquido());

			}

		} catch(NullPointerException e) { 
			System.out.print(e + "\n\nAtÈ logo!");
		} 

		return (long) 0.0;

	}

	//Exemplo de m√©todo que pode ser criado para separar melhor as respons√°bilidades de seu algor√≠tmo
	private double calcularDescontoInss(double salarioBruto) {

		if (getSalarioBruto() <= 1500.00) {
			System.out.println("VocÍ ter· um desconto de 8% do INSS.");
			setSalarioBrutoINSS(getSalarioBruto() * descontoInssBaixo);
			return getSalarioBrutoINSS();
		} else if (getSalarioBruto() <= 4000.00) {
			System.out.println("VocÍ ter· um desconto de 9% do INSS.");
			setSalarioBrutoINSS(getSalarioBruto() * descontoInssMedio);
			return getSalarioBrutoINSS();
		} else {
			System.out.println("VocÍ ter· um desconto de 11% do INSS.");
			setSalarioBrutoINSS(getSalarioBruto() * descontoInssMaior);
			return getSalarioBrutoINSS();
		}
		
	}

	private double calcularDescontoIRPF(double descontoIRPF) {

		if (getSalarioBrutoINSS() <= 3000.00) {
			System.out.println("VocÍ est· ISENTO do pagamento de IRPF.");
			setSalarioLiquido(getSalarioBrutoINSS());
			return getSalarioLiquido();
		} else if (getSalarioBrutoINSS() <= 6000.00) {
			System.out.println("VocÍ ter· um desconto de 7,5% no IRPF.");
			setSalarioLiquido(getSalarioBrutoINSS() * descontoIrpfMenor);
			return getSalarioLiquido();
		} else {
			System.out.println("VocÍ ter· um desconto de 15% no IRPF.");
			setSalarioLiquido(getSalarioBrutoINSS() * descontoIrpfMaior);
			return getSalarioLiquido();
		}

	}

	private static boolean verificaFormatoSalario(String mensagem) {

		// Se tiver letras, espaÁos ou outros caracteres retorna false
		// Aceita somente n˙meros no formato 000.00 atÈ 999999.99 ou 000 atÈ 999999
		return mensagem.matches("\\d{3,6}(\\.){0,1}(\\d){0,2}"); // "\\d{3,6}\\.\\d\\d"
	}


}
/*D√∫vidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
 */
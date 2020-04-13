package br.nom.penha.bruno.steps;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AprenderCucumberSteps {

//	@Dado("que criei o arquivo corretamente")
//	public void que_criei_o_arquivo_corretamente() {
//	    System.out.println("passei do quando");
//	}
//
//	@Quando("executa-lo")
//	public void executa_lo() {
//	}
//
//	@Então("a especificacão deve finalizar com sucesso")
//	public void a_especificacão_deve_finalizar_com_sucesso() {
//	}
	
	@Dado("^que criei o arquivo corretamente$")
	public void queCrieiOArquivoCorretamente() throws Throwable {
		System.out.println("Passei no Dado");
	}

	@Quando("^executa-lo$")
	public void executaLo() throws Throwable {
	}

	@Então("^a especificacão deve finalizar com sucesso$")
	public void aEspecificacãoDeveFinalizarComSucesso() throws Throwable {
	}
	
	private int contador = 0;
	@Dado("^que o contador é (\\d+)$")
	public void queOContadorÉ(int arg1) throws Throwable {
		contador = arg1;
		
	}

	@Quando("^eu incrementar em (\\d+)$")
	public void euIncrementarEm(int arg1) throws Throwable {
	    contador += arg1;
	}

	@Então("^o valor do contador será (\\d+)$")
	public void oValorDoContadorSerá(int arg1) throws Throwable {
		Assert.assertEquals(arg1, contador);
	}
	
	Date entrega = new Date();
	
//	@Dado("^que a entrega é dia (.*)$") Lib antiga
	@Dado("^que a entrega é dia (data)$")
//	public void que_a_entrega_é_dia(@Transform(DateConverter.class) Date data) throws Throwable { Lib antiga
	public void que_a_entrega_é_dia(Date data) throws Throwable {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DAY_OF_MONTH, dia);
//		cal.set(Calendar.MONTH, mes - 1);
//		cal.set(Calendar.YEAR, ano);
//		entrega = cal.getTime();
		entrega = data;
		System.out.println(entrega);
	}

	@Quando("^a entrega atrasar em (\\d+) (dia|dias|mes|meses)$")
	public void a_entrega_atrasar_em_dias(int dias, String tempo) throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entrega);
		switch (tempo) {
		case "dias":
			cal.add(Calendar.DAY_OF_MONTH, dias);
			break;
		case "meses":
			cal.add(Calendar.MONTH, dias);
		default:
			break;
		}
		entrega = cal.getTime();
	}

	@Então("^a entrega será efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
	public void a_entrega_será_efetuada_em(String data) throws Throwable {
	    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    String dataFormatada = format.format(entrega);
	    Assert.assertEquals(data, dataFormatada);
		
	}
	
	@Dado("^que o ticket( especial)? é (A.\\d{3})$")
	public void que_o_ticket_é_AF(String tipo, String arg1) throws Throwable {
	}

	@Dado("^que o valor da passagem é R\\$ (.*)$")
	public void que_o_valor_da_passagem_é_R$(Double valor) throws Throwable {
		System.out.println(valor);
	}

	@Dado("^que o nome do passageiro é \"(.{5,20})\"$")
	public void que_o_nome_do_passageiro_é(String arg1) throws Throwable {
	}

	@Dado("^que o telefone do passageiro é (9\\d{3}-\\d{4})$")
	public void que_o_telefone_do_passageiro_é(String telefone) throws Throwable {
	}

	@Quando("^criar os steps$")
	public void criar_os_steps() throws Throwable {
	}

	@Então("^o teste vai funcionar$")
	public void o_teste_vai_funcionar() throws Throwable {
	}

}

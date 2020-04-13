package br.nom.penha.bruno.steps;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import br.nom.penha.bruno.model.Filme;
import br.nom.penha.bruno.model.NotaAluguel;
import br.nom.penha.bruno.servicos.AluguelService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AlugarFilmesSteps {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private String tipoAluguel;

	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void umFilmeComEstoqueDeUnidades(int arg1) throws Throwable {
		filme = new Filme();
		filme.setEstoques(arg1);
	}

	@Dado("^que o preço do aluguel seja R\\$ (\\d+)$")
	public void queOPreçoDoAluguelSejaR$(int arg1) throws Throwable {
		filme.setAluguel(arg1);
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			erro = e.getMessage();
		}
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void oPreçoDoAluguelSeráR$(int arg1) throws Throwable {
		Assert.assertEquals(arg1, nota.getPreco());
	}

	@Então("^o estoque do filme será (\\d+) unidade$")
	public void oEstoqueDoFilmeSeráUnidade(int arg1) throws Throwable {
		Assert.assertEquals(arg1, filme.getEstoque());
	}

	@Então("^não será possível por falta de estoque$")
	public void nãoSeráPossívelPorFaltaDeEstoque() throws Throwable {
		Assert.assertEquals("Filme sem estoque", erro);
	}

	@Dado("^que o tipo do aluguel seja (.*)$")
	public void queOTipoDoAluguelSeja(String tipo) throws Throwable {
		tipoAluguel = tipo;
	}

	@Então("^a data de entrega será (\\d+) dias?$")
	public void aDataDeEntregaSeráDias(int arg1) throws Throwable {
		Date dataEsperada = obterDataDiferencaDias(arg1);
		DateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");

		Assert.assertEquals(formatado.format(dataEsperada), formatado.format(nota.getDataEntrega()));
	}

	@Então("^a pontuação será de (\\d+) pontos?$")
	public void aPontuaçãoSeráDePontos(int arg1) throws Throwable {
		Assert.assertEquals(arg1, nota.getPontuacao());
	}

	private Date obterDataDiferencaDias(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, dias);
		return cal.getTime();
	}

	@Dado("^um filme$")
	public void umFilme(DataTable tabelaArg) throws Throwable {
		Map<String, String> tabela = tabelaArg.asMap(String.class, String.class);
		filme = new Filme();
		filme.setEstoques(Integer.parseInt(tabela.get("estoque")));
		filme.setAluguel(Integer.parseInt(tabela.get("preco")));
		tipoAluguel = tabela.get("tipo");
	}

}

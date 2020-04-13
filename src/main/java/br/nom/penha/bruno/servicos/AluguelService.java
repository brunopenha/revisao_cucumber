/**
 * 
 */
package br.nom.penha.bruno.servicos;

import java.util.Calendar;
import java.util.Date;

import br.nom.penha.bruno.model.Filme;
import br.nom.penha.bruno.model.NotaAluguel;

public class AluguelService {

	public NotaAluguel alugar(Filme filme, String tipoAluguel) {
		if(filme.getEstoque() > 0) {
			
			NotaAluguel nota = new NotaAluguel();
			
			switch (tipoAluguel) {
			case "comum":
				nota.setPreco(filme.getAluguel());
				nota.setDataEntrega(obterDataDiferencaDias(1));
				nota.setPontuacao(1);
				break;

			case "extendido":
				nota.setPreco(2 * filme.getAluguel());
				nota.setDataEntrega(obterDataDiferencaDias(3));
				nota.setPontuacao(2);
				break;
			case "semanal":
				nota.setPreco(3 * filme.getAluguel());
				nota.setDataEntrega(obterDataDiferencaDias(7));
				nota.setPontuacao(3);
				break;
			default:
				break;
			}
			
			filme.setEstoques(filme.getEstoque() - 1);
			
			return nota;	
		}else {
			throw new RuntimeException("Filme sem estoque");
		}
		
	}

	private Date obterDataDiferencaDias(int dias) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,dias);
		
		return cal.getTime();
	}

}

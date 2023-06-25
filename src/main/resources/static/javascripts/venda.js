Scholar.Venda = (function() {
	
	function Venda(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.boxValorTotalVenda = $('.js-valor-total-box');
		this.inputValorFrete = $('#valorFrete');
		this.inputValorDesconto = $('#valorDesconto');
		this.boxValorTotalContainer = $('.js-valor-total-box-container');
		
		this.valorTotalItens = this.tabelaItens.valorTotal();
		this.valorFrete = this.inputValorFrete.data('valor');
		this.valorDesconto = this.inputValorDesconto.data('valor');
	}
	
	Venda.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		this.inputValorFrete.on('keyup', onValorFreteAlterado.bind(this));
		this.inputValorDesconto.on('keyup', onValorDescontoAlterado.bind(this));
		
		this.tabelaItens.on('tabela-itens-atualizada', onValoresAlterados.bind(this));
		this.inputValorFrete.on('keyup', onValoresAlterados.bind(this));
		this.inputValorDesconto.on('keyup', onValoresAlterados.bind(this));
		
		onValoresAlterados.call(this);
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
	}
	
	function onValorFreteAlterado(evento) {
		this.valorFrete = Scholar.recuperarValor($(evento.target).val());
	}
	
	function onValorDescontoAlterado(evento) {
		this.valorDesconto = Scholar.recuperarValor($(evento.target).val());
	}
	
	function onValoresAlterados() {
		var valorTotal = numeral(this.valorTotalItens) + numeral(this.valorFrete) - numeral(this.valorDesconto);
		
		this.boxValorTotalVenda.html(Scholar.formatarMoeda(valorTotal));
		
		this.boxValorTotalContainer.toggleClass('erro', valorTotal < 0)
	}
	
	return Venda;
	
}());

$(function() {
	var autocomplete = new Scholar.Autocomplete();
	autocomplete.iniciar();

	var tabelaItens = new Scholar.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var venda = new Scholar.Venda(tabelaItens);
	venda.iniciar();
});
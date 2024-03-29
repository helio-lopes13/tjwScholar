var Scholar = Scholar || {};

Scholar.Autocomplete = (function() {
	
	function Autocomplete() {
		this.inputSkuOuNome = $('.js-sku-nome-cerveja-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(skuOuNome) {
				return this.inputSkuOuNome.data('url') + '?skuOuNome=' + skuOuNome;
			}.bind(this),
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json',
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this),
			}
		};
		
		this.inputSkuOuNome.easyAutocomplete(options);
	}
	
	function template(nome, cerveja) {
		cerveja.valorFormatado = Scholar.formatarMoeda(cerveja.valor);
		return this.template(cerveja);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.inputSkuOuNome.getSelectedItemData());
		this.inputSkuOuNome.val('');
		this.inputSkuOuNome.focus();
	}
	
	return Autocomplete;
	
}());
var Scholar = Scholar || {};

Scholar.PesquisaRapidaProfessor = (function() {
	function PesquisaRapidaProfessor() {
		this.modalPesquisaRapidaProfessores = $('#pesquisaRapidaProfessores');
		this.form = this.modalPesquisaRapidaProfessores.find('form');
		this.nomeInput = $('#nomeProfessorModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-professores-btn');
		this.url = this.form.attr('action');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaProfessores');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-professor').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaProfessor.prototype.iniciar = function() {
		this.modalPesquisaRapidaProfessores.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.url,
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		this.mensagemErro.addClass('hidden');
		
		var tabelaProfessorPesquisaRapida = new Scholar.TabelaProfessorPesquisaRapida(this.modalPesquisaRapidaProfessores);
		tabelaProfessorPesquisaRapida.iniciar();
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaProfessor;
}());

Scholar.TabelaProfessorPesquisaRapida = (function() {
	
	function TabelaProfessorPesquisaRapida(modal) {
		this.professor = $('.js-professor-pesquisa-rapida');
		this.modalProfessor = modal;
	}
	
	TabelaProfessorPesquisaRapida.prototype.iniciar = function() {
		this.professor.on('click', onProfessorSelecionado.bind(this));
	}
	
	function onProfessorSelecionado(evento) {
		this.modalProfessor.modal('hide');
		
		var professorSelecionado = $(evento.currentTarget);
		$('#nomeProfessor').val(professorSelecionado.data('nome'));
		$('#idProfessor').val(professorSelecionado.data('id'));
	}
	
	return TabelaProfessorPesquisaRapida;
}());

$(function() {
	var pesquisaRapidaProfessor = new Scholar.PesquisaRapidaProfessor();
	pesquisaRapidaProfessor.iniciar();
});
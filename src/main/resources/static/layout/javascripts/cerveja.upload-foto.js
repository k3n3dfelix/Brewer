$(function(){
	var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: '/brewer/fotos',
			complete:function(resposta){
				
				var inputNomeFoto = $('input[name=foto]');
				var inputContentType = $('input[name=contentType]');
				
				inputNomeFoto.val(resposta.nome);
				inputContentType.val(resposta.contentType);
				
				var htmlFotoCervejaTemplate = $('#foto-cerveja').html();
				var template = Handlebars.compile(htmlFotoCervejaTemplate);
				var htmlFotoCerveja = template({nomeFoto: resposta.nome});
				
				var uploadDrop = $('#upload-drop');
				uploadDrop.addClass('hidden');
				
				var containerFotoCerveja = $('.js-container-foto-cerveja');
				containerFotoCerveja.append(htmlFotoCerveja);
				
				$('.js-remove-foto').on('click', function(){
					$('.js-foto-cerveja').remove();
					uploadDrop.removeClass('hidden');
					inputNomeFoto.val('');
					inputContentType.val('');
					
				});
				
				if(inputNomeFoto.val()){
					containerFotoCerveja.append(htmlFotoCerveja);
				}
				
			}
	};
	
	UIkit.uploadSelect($('#upload-select'), settings),
    UIkit.uploadDrop($('#upload-drop'), settings);
	
	
});

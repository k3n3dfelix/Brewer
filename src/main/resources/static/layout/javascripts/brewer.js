//Aplicando Design Patters JS
var Brewer = Brewer || {};

Brewer.maskMoney = (function(){
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({decimal:',', thousands:'.'});
		this.plain.maskMoney({precision:0, thousands: '.'});
	}
	return MaskMoney;
}());


$(function(){
	var maskMoney = new Brewer.maskMoney();
	maskMoney.enable();
	
}) 


/*
$(function(){
	var decimal = $('.js-decimal');
	decimal.maskMoney({decimal:',', thousands:'.'});
	
	var plain = $('.js-plain');
	plain.maskMoney({precision:0, thousands: '.'});
	
}) */
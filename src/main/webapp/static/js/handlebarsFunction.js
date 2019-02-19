var Handlebars = require('handlebars/dist/handlebars');

module.exports = {
	
	/**
	 * @desc handlebar helper regist 작업 
	 */	
	setHandlebarRegistHelper : function() {
		
		Handlebars.registerHelper('userEmailEncrypt', function(reservationEmail) {
			   
			var start = 0;
		    var end = 4;
		    
			var encryptedUserEmail = reservationEmail.substring(start,end)+'****';
		    return new Handlebars.SafeString(encryptedUserEmail);
		});
		
		Handlebars.registerHelper('reservationDateYYYYMMDD', function(reservationDate) {
		    
			var start = 0;
		    var end = 10;
			
		    var reservationDateYYYYMMDD = reservationDate.substring(start,end).replace(/-/g,'.')+'.';
		    return new Handlebars.SafeString(reservationDateYYYYMMDD);
		});
	},
	
	/**
	 * @desc handlebar로 compile해서 html 얻어오기
	 * @param templateId
	 * @param content
	 * @returns html
	 */
	getHandlebarTemplateFromHtml: function(templateId,content){
		var template = document.querySelector(templateId).innerHTML;
		var handlebarTemplate = Handlebars.compile(template);
		
		return handlebarTemplate(content);
	}
};

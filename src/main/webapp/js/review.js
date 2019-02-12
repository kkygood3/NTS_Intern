document.addEventListener("DOMContentLoaded", function() {
	reviewPage.getReviews(reviewPage.displayInfoId);
});

var reviewPage = {
	getReviews: function(displayInfoId){
		var httpRequest;
		
		if (window.XMLHttpRequest) {
			httpRequest =  new XMLHttpRequest();
			
			httpRequest.onreadystatechange = function() {
				var jsonResponse;
				
				if (httpRequest.readyState === 4 && httpRequest.status === 200) {
					jsonResponse = JSON.parse(httpRequest.responseText);
					
					this.elements.displayTitle.innerHTML = jsonResponse["displayInfo"].productDescription;
					
					this.displayComments(jsonResponse);
				}
			}.bind(this)
			
			httpRequest.open("GET", "../api/products/" + displayInfoId);
			httpRequest.setRequestHeader("Content-type", "charset=utf-8");
			httpRequest.send();
		}
		
		this.goToPrevPage();
		this.scrollTop();
	},
	
	displayInfoId : window.location.href.split("/").slice(-1)[0],
	
	elements: {
		displayTitle : document.querySelector(".title"),
		cnt : document.querySelector(".count_detail_image"),
		content : document.querySelector(".dsc"),
		star_rating : document.querySelector(".graph_value"),
		num_rating : document.querySelector(".text_value"),
		cntComment : document.querySelector(".green"),
		
		commentsContainer : document.querySelector(".list_short_review"),
		
		btnBack : document.querySelector(".btn_back"),
		btnTop : document.querySelector(".lnk_top")
	},
	
	template: {
		commentTemplate : document.querySelector("#commentTemplate").innerHTML
	},
	
	compileHendlebars: {
		bindTemplate : function(template){
			return Handlebars.compile(template);
		},
		
		anonymizeUserId: function(DISPLAYED_ID_LENGTH){
			Handlebars.registerHelper('anonymize', function(context) {
				return context.substring(0, DISPLAYED_ID_LENGTH) + "****";
			});
		}
	},
	
	displayComments: function(jsonResponse){
		var bindComments = this.compileHendlebars.bindTemplate(this.template.commentTemplate);
		
		this.elements.star_rating.style.width = (jsonResponse["averageScore"] / 5) * 100 + "%"
		this.elements.num_rating.innerHTML = "<span>" + jsonResponse["averageScore"] + "</span>";

		this.elements.cntComment.innerHTML = jsonResponse["comments"].length + "건";

		jsonResponse["comments"].forEach(function(comment){
			comment.score = comment.score.toFixed(1);
		});
		
		this.elements.commentsContainer.innerHTML = bindComments(jsonResponse);
	},
	
	goToPrevPage: function(){
		this.elements.btnBack.addEventListener("click", function(){
			history.back();
		});
	},
	
	scrollTop: function(){
		this.elements.btnTop.addEventListener("click", function(){
			document.documentElement.scrollTop = 0;
		});
	}
}
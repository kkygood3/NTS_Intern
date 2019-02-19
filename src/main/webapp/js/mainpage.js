document.addEventListener("DOMContentLoaded", function() {
	mainPage.getMainPage();
});

var mainPage = {
	getMainPage: function(){
		this.ajax.sendGet("/reservation/api/categories", this.ajaxOptions.getOptionsForCategories());
		this.ajax.sendGet("/reservation/api/products", this.ajaxOptions.getOptionsForProducts());
		this.ajax.sendGet("/reservation/api/promotions", this.ajaxOptions.getOptionsForPromotions());
		
		this.setEvent.preventLink();
		this.setEvent.showMore();
		this.setEvent.tabEvent();
		
		addScrollTopEvent(this.elements.btnTop);
	},
	
	ajax : new AjaxSend(),
	
	ajaxOptions : {
		getOptionsForCategories : function(){
			var options = {
					contentType : "charset=utf-8",
					callBack : this.mainPage.getCategories
			}
			
			return options;
		}.bind(this),
		
		getOptionsForProducts : function(){
			var options = {
				contentType : "charset=utf-8",
				callBack : this.mainPage.getProductsByCategory
			}
			
			return options;
		}.bind(this),
		
		getOptionsForPromotions : function(){
			var options = {
				contentType : "charset=utf-8",
				callBack : this.mainPage.getPromotions
			}
			
			return options;
		}.bind(this)
	},
	
	variables: {
		selectedCategoryId : null,
		start : 0,
		displayedProduct : 0
	},
	
	constants: {
		promotionWidth : 414,
		animationTime : 5000,
		productsPerPage : 4
	},
	
	elements: {
		reserveLink : document.querySelector(".lnk_logo[title='예약']"),
		
		slides : document.querySelectorAll(".visual_img li"),
		tab : document.querySelector(".section_event_tab").querySelector(".event_tab_lst"),
		btnShowMore : document.querySelector(".more"),
		btnTop : document.querySelector(".lnk_top"),
		
		countProduct : document.querySelector(".event_lst_txt span"),
		
		categoryContainer : document.querySelector(".event_tab_lst"),
		productContainer : document.querySelectorAll(".lst_event_box"),
		promotionContainer : document.querySelector(".visual_img")
	},
	
	template: {
		categoryTemplate : document.querySelector("#categories").innerHTML,
		productTemplate : document.querySelector("#itemList").innerHTML,
		promotionTemplate : document.querySelector("#promotionItem").innerHTML
	},
	
	compileHendlebars: {
		bindTemplate : function(template){
			return Handlebars.compile(template);
		}
	},
	
	getCategories: function(jsonResponse){
		var bindCategory = this.mainPage.compileHendlebars.bindTemplate(this.mainPage.template.categoryTemplate);
					
		this.mainPage.elements.categoryContainer.innerHTML += bindCategory(jsonResponse);
	}.bind(this),
	
	getProductsByCategory: function(jsonResponse){
		var bindProducts = this.mainPage.compileHendlebars.bindTemplate(this.mainPage.template.productTemplate);
		var left = 0;
		var right = 1;
		var productArr;
		
		this.mainPage.elements.countProduct.innerHTML = jsonResponse["totalCount"];
		this.mainPage.variables.displayedProduct += jsonResponse["items"].length;
		
		productArr = bindProducts(jsonResponse).split("</li>");
		productArr.forEach(function(product){
			if(productArr.indexOf(product) % 2 === 0){
				this.mainPage.elements.productContainer[left].innerHTML += product + "</li>";
			} else {
				this.mainPage.elements.productContainer[right].innerHTML += product + "</li>";
			}
		});

		if(this.mainPage.variables.displayedProduct >= jsonResponse["totalCount"]){
			this.mainPage.elements.btnShowMore.hidden = true;
		}
	}.bind(this),
	
	getPromotions: function(jsonResponse){
		var bindPromotions = this.mainPage.compileHendlebars.bindTemplate(this.mainPage.template.promotionTemplate);
		var executeAnimationTime;
		
		this.mainPage.elements.promotionContainer.innerHTML += bindPromotions(jsonResponse);

		executeAnimationTime = performance.now();
		this.mainPage.slideImage(0, 1, executeAnimationTime);
	}.bind(this),
	
	slideImage: function(slideOut, slideIn, executeAnimationTime){
		var now = performance.now();
		var slides = document.querySelectorAll(".visual_img li");

		slides[slideOut].style.left = "0";
		slides[slideOut].className = "item slide_out";
		slides[slideIn].style.left = this.mainPage.constants.promotionWidth + "px";
		slides[slideIn].className = "item slide_in";
		
		if(now - executeAnimationTime > this.mainPage.constants.animationTime) {
			slides[slideOut].className = "item";
			slideOut = slideIn;
			slideIn = (slideIn + 1) % slides.length;
			executeAnimationTime = performance.now();
		}
		
		requestAnimationFrame(function(){
			this.mainPage.slideImage(slideOut, slideIn, executeAnimationTime);
		});
	}.bind(this),
	
	setEvent: {
		preventLink : function(){
			this.mainPage.elements.reserveLink.addEventListener("click", function(event){
				event.preventDefault();
			});
		}.bind(this),
		
		showMore: function(){
			this.mainPage.elements.btnShowMore.addEventListener("click", function(event){
				this.mainPage.variables.start += this.mainPage.constants.productsPerPage;
				if(this.mainPage.variables.selectedCategoryId === null || this.mainPage.variables.selectedCategoryId === undefined){
					this.mainPage.ajax.sendGet("/reservation/api/products?start=" + this.mainPage.variables.start, this.mainPage.ajaxOptions.getOptionsForProducts());
				} else {
					this.mainPage.ajax.sendGet("/reservation/api/products?categoryId="+this.mainPage.variables.selectedCategoryId+"&start=" + this.mainPage.variables.start, this.mainPage.ajaxOptions.getOptionsForProducts());
				}
			}.bind(this));
		}.bind(this),
		
		tabEvent: function(){ 
			this.mainPage.elements.tab.addEventListener("click", function(event){
				var anchorElement;
				var previousActive = document.querySelector(".active");

 				event.preventDefault();
				
				if(event.target.className === "anchor"){
					anchorElement = event.target;
				} else if(event.target.className === "category_name"){
					anchorElement = event.target.parentNode;
				} else {
					return;
				}
	
				previousActive.className = "anchor";
				
				this.mainPage.variables.selectedCategoryId = anchorElement.parentNode.dataset.category;
				anchorElement.className += " active";
				
				this.mainPage.elements.productContainer.forEach(function(container){
					container.innerHTML = "";
				});
				
				this.mainPage.variables.start = 0;
				this.mainPage.variables.displayedProduct = 0;
				this.mainPage.elements.btnShowMore.hidden = false;
				
				if(this.mainPage.variables.selectedCategoryId === null || this.mainPage.variables.selectedCategoryId === undefined){
					this.mainPage.ajax.sendGet("/reservation/api/products?start=" + this.mainPage.variables.start + this.mainPage.variables.start, this.mainPage.ajaxOptions.getOptionsForProducts());
				} else {
					this.mainPage.ajax.sendGet("/reservation/api/products?categoryId="+this.mainPage.variables.selectedCategoryId+"&start=" + this.mainPage.variables.start, this.mainPage.ajaxOptions.getOptionsForProducts());
				}
			}.bind(this));
		}.bind(this)
	}
}
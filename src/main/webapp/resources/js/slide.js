function Slide(view, items){
	this.view = view;
	this.items = items;
	this.index = 0;
	var originalClass = view.className;
	
	if(this.items === undefined){
		this.items = Array.from(view.children);
	}
	
	this.view.innerHTML = this.items[this.index].outerHTML;
	
	this.view.addEventListener("transitionend", () =>{
		this.view.classList.remove("slide");
		this.view.style.transform = "translateX(0%)";
		this.view.innerHTML = this.items[this.index].outerHTML;
	});
}

Slide.prototype= {
	_direction : {
		LEFT : -1,
		RIGHT : 1
	},
		
	left : function(){
		var view = this.view;
		var items = this.items;
		var nextIndex = this.getNextIndex(this._direction.LEFT);

		view.style.transform = "translateX(-100%)";
		view.innerHTML = items[nextIndex].outerHTML + view.innerHTML;
		this.index = nextIndex;
		
		setTimeout(()=>{
			view.classList.add("slide");
			view.style.transform = "translateX(0%)";
		}, 0);
	},
	
	right : function(){
		var view = this.view;
		var items = this.items;
		var nextIndex = this.getNextIndex(this._direction.RIGHT);
		view.innerHTML = view.innerHTML + items[nextIndex].outerHTML;
		this.index = nextIndex;
		
		view.classList.add("slide");
		view.style.transform = "translateX(-100%)";
	},
	
	autoSlideRight : function(delay){
		setTimeout(() =>{
			this.right();
			this.autoSlideRight(delay);
		}, delay);
	},
	
	getNextIndex : function(direction){
		var nextIndex = this.index + direction;
		nextIndex = (nextIndex + this.items.length) % this.items.length;
		return nextIndex;
	},
}
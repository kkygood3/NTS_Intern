var slide = {
	direction : {
		LEFT : -1,
		RIGHT : 1
	},
	
	make : function(view){
		this.view = view;
		this.items = Array.from(view.children);
		this.index = 0;
		
		var originalClass = view.className;
		
		this.view.innerHTML = this.items[this.index].outerHTML;
		
		this.view.addEventListener("transitionend", () =>{
			this.view.className = originalClass;
			this.view.style.transform = "translateX(0%)";
			this.view.innerHTML = this.items[this.index].outerHTML;
		});
	},
	
	left : function(){
		var view = this.view;
		var items = this.items;
		var nextIndex = this.getNextIndex(this.direction.LEFT);

		view.style.transform = "translateX(-100%)";
		view.innerHTML = items[nextIndex].outerHTML + view.innerHTML;
		this.index = nextIndex;
		
		setTimeout(()=>{
			view.className += " slide";
			view.style.transform = "translateX(0%)";
		}, 0);
	},
	
	right : function(){
		var view = this.view;
		var items = this.items;
		var nextIndex = this.getNextIndex(this.direction.RIGHT);
		view.innerHTML = view.innerHTML + items[nextIndex].outerHTML;
		this.index = nextIndex;
		
		view.className += " slide";
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
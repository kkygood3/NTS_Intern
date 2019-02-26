function Rating(ratingElement){
	this.score = 1;
	this.scoreElement = ratingElement.querySelector("#score");
	this.updateScore();
	
	var pointElementList = Array.from(ratingElement.querySelectorAll("input[type=checkbox]"));
	this.pointList = pointElementList.map((pointElement) =>{
		return new Point(pointElement);
	});
	this.fill();
	
	ratingElement.addEventListener("click", (evt) =>{
		if(evt.target.type === "checkbox"){
			this.score = evt.target.value;
			this.fill();
			this.updateScore();
		}
	});
}

Rating.prototype = {
	empty : function(){
		this.pointList.forEach((point) =>{
			point.empty();
		});
	},
	fill : function(){
		this.empty();
		for(var i=0; i<this.score; i++){
			this.pointList[i].fill();
		}
	},
	updateScore : function(){
		this.scoreElement.innerText = this.score;
	},
}

function Point(pointElement){
	this.pointElement = pointElement;
}

Point.prototype = {
	empty : function(){
		this.pointElement.checked = false;
	},
	
	fill : function(){
		this.pointElement.checked = true;
	}
}
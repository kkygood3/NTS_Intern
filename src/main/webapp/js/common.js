let nowCatagoryId = 0;

var tab = document.querySelector(".section_event_tab").querySelectorAll(".anchor");
for(var i = 0; i < tab.length; i++){
	tab[i].addEventListener("click", function(){
		var nowActive = this.parentNode.parentNode.querySelector("[data-category='"+nowCatagoryId+"']").firstElementChild;
		nowActive.className = "anchor";
		this.className += " active";
		nowCatagoryId = this.parentNode.dataset.category;
		console.log(nowCatagoryId);
	});
}
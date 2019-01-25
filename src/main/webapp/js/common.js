let nowCatagoryId = 0;

var tab = document.querySelector(".section_event_tab").querySelectorAll(".anchor");
for(var i = 0; i < tab.length; i++){
	tab[i].addEventListener("click", function(){
		var nowActive = this.parentNode.parentNode.querySelector("[data-category='"+nowCatagoryId+"']").firstElementChild;
		nowActive.className = "anchor";
		this.className += " active";
		nowCatagoryId = this.parentNode.dataset.category;
		console.log(nowCatagoryId);
		categoryChange(nowCatagoryId);
	});
}


//var productTemplate = document.querySelector("#itemList").textContent;
//var id = 1;
//var description = "test1";
//var placeName = "test2";
//var content = "test3";

//ul[0].innerHTML = eval("`"+productTemplate+"`");
//ul[1].innerHTML = eval("`"+productTemplate+"`");

var ul = document.querySelectorAll(".lst_event_box");
function categoryChange(categoryId) {
	
	if (window.XMLHttpRequest) {
		var httpRequest =  new XMLHttpRequest();
		
		httpRequest.onreadystatechange = function() {  
		    if (httpRequest.readyState === 4 && httpRequest.status === 200) {
		    	var targetElement = ul[0];
		    	targetElement.innerHTML += "test<br>";
		    }
		}
		
		httpRequest.open("POST", "category", true);
		httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		var data = "id=" + categoryId;
		httpRequest.send(data);
	}
}
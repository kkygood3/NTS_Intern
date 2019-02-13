function sendGetCategorissAjax() {
	sendGetAjax("./category", makeCategoryBar);
}

function makeCategoryBar(categoris) {
	if (!categoris) {
		return false;
	}
	var html = document.getElementById("category_item").innerHTML;

	var resultHTML = "";
	categoris.forEach((category) => {
	    resultHTML += html.replace("{id}", category.id)
	    				.replace("{name}", category.name);
	});
	document.getElementsByClassName("event_tab_lst")[0].innerHTML += resultHTML;
}

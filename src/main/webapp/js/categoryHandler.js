var categories;

/**
 * @description : EventList 내용 제거 
 */
function eventListClear(){
	var eventUl = document.querySelector("#wrap_lst_event_box").getElementsByTagName("UL");
	
	for(var i=0, len=eventUl.length; i<len; i++){
		eventUl.item(i).innerText="";
	}
}
 
/**
 * @description : get method로 Category List를 요청
 */
function categoryListRequest(){
	var request = { method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
	}
	
	dataRequestGET("/api/categories", "", request)
		.then(result =>{
			appendTabList(result.items);
	});
}

/**
 * @description : 수신된 Category List를 HTML의 Tab UL에 추가
 */
function appendTabList(items){
	var tabUl = document.querySelector("#category_list");
	var tabListHTML = document.querySelector("#tabList").innerText;
	
	var allCategories = {
			"count" : "${count}",
			"id" : 0,
			"name" : "전체 리스트"
	}
	
	var count = 0;
	tabUl.innerHTML += replaceTabHTML(allCategories, tabListHTML);
	
	for(var i=0, len=items.length; i<len; i++){
		tabUl.innerHTML += replaceTabHTML(items[i], tabListHTML);
		count+=items[i]["count"];
	}
	
	tabUl.innerHTML = tabUl.innerHTML.replace("${count}",count);
	tabUl.firstElementChild.firstElementChild.setAttribute("class","anchor active");
	
	document.querySelector("#event_num").innerText = count;
	
	allCategories.count = count;
	categories = [];
	categories.push(allCategories);
	categories = categories.concat(items);
	
	addCategoryButtonListener();
}

/**
 * @description : 수신된 item과 html mapping
 */
function replaceTabHTML(item, html){
	
	return  html.replace("${id}",item.id)
				.replace("${name}",item.name);
}

/**
 * @description : category에 Event Listener 추가
 */
function addCategoryButtonListener(){
	var tabList = document.querySelector("#category_list").getElementsByClassName("item");
	for(var i=0, len=tabList.length; i<len; i++){
		
		tabList[i].addEventListener("click", (evt)=>{
			
			if(evt.target.tagName === "SPAN"){
				
				var li = evt.target.parentElement.parentElement;
				
			}else if(evt.target.tagName === "A"){
				
				var li = evt.target.parentElement;
				
			}else {
				return;
			}
			
			eventListClear();
			
			var child = li.parentElement.firstElementChild;
			
			while(child != null){
				
				child.firstElementChild.setAttribute("class","anchor");
				child = child.nextElementSibling;
				
			}
			
			li.firstElementChild.setAttribute("class","anchor active");
			var categoryId = li.getAttribute("data-category");
			document.querySelector("#event_num").innerText = categories[categoryId]["count"];
			
			productReset(categoryId);
		});
	}
}
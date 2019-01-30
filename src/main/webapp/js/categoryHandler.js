var categories;

/**
 * @description : EventList 내용 제거 
 */
function eventListClear(){
	var eventUl = document.querySelectorAll(".lst_event_box");
	eventUl.forEach((v)=>{
	    v.innerText="";
	    }
	);
}
 
/**
 * @description : get method로 Category List를 요청
 */
function categoryListRequest(){
	var requestInit = { method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
	}
	var url = "/api/categories";
	
	dataRequest("",requestInit,url)
		.then(result =>{
			appendTabList(result.items);
	});
}

/**
 * @description : 수신된 Category List를 HTML의 Tab UL에 추가
 */
function appendTabList(items){
	var tabUl = document.querySelector(".event_tab_lst");
	var tabListHTML = document.querySelector("#tabList").innerText;
	
	var allList = {
			"count" : "${count}",
			"id" : 0,
			"name" : "전체 리스트"
	}
	
	var count = 0;
	tabUl.innerHTML += replaceTabHTML(allList, tabListHTML);
	
	for(var i=0, len=items.length; i<len; i++){
		tabUl.innerHTML += replaceTabHTML(items[i], tabListHTML);
		count+=items[i]["count"];
	}
	
	tabUl.innerHTML = tabUl.innerHTML.replace("${count}",count);
	tabUl.firstElementChild.firstElementChild.setAttribute("class","anchor active");
	document.querySelector(".event_lst_txt").firstElementChild.innerText = count + "개";
	
	allList["count"] = count;
	categories = new Array();
	categories[0] = allList;
	categories = categories.concat(items);
	
	addCategoryButtonListener();
}

/**
 * @description : 수신된 item과 html mapping
 */
function replaceTabHTML(item, html){
	var id = item["id"];
	var name = item["name"];
	return  html.replace("${id}",id)
				.replace("${name}",name);
}

/**
 * @description : category에 Event Listener 추가
 */
function addCategoryButtonListener(){
	var tabList = document.querySelector(".event_tab_lst").getElementsByClassName("item");
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
			document.querySelector(".event_lst_txt").firstElementChild
													.innerText = categories[categoryId]["count"] + "개";
			productReset(categoryId);
		});
	}
}
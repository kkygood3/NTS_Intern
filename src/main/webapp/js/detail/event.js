/**
 * detail 페이지 이벤트 처리
 * @author 시윤
 *
 */

/**
 * 상세정보/오시는길 탭메뉴 클릭이벤트
 */
function addInfoTabClickEvent() {
	var infoTab = document.getElementsByClassName("info_tab_lst")[0];
	var infoSection = document.getElementsByClassName("section_info_tab")[0];
	
	infoTab.addEventListener("click", function(event){
		var target = event.target.closest("LI");
		if (!target) {
			return;
		}
		
		var tabs = infoTab.getElementsByTagName("li");
		var as = infoTab.getElementsByTagName("a");
		var divs = infoSection.querySelectorAll(".section_info_tab > div");
		
		changeTabUi(target, tabs, as, divs);
	});
}

function changeTabUi(target, tabs, as, divs) {
	for (var i = 0, len = tabs.length; i < len; i++) {
		if (tabs[i] == target) {
			as[i].className += " active";
			divs[i].className = divs[i].className.split(" ")[0];
		} else if (divs[i].className.split(" ").length == 1) {
			as[i].className = as[i].className.split(" ")[0];
			divs[i].className += " hide";
		}
	}
}

/**
 * 상단 비주얼영역 버튼 이벤트
 */
function NextButtonClickEvent() {
	var ul = document.getElementsByClassName("visual_img")[0];
	slideLeft(ul);
	disablePrevAndNextButton();
	paginate(1);
}

function PreviousButtonClickEvent() {
	var ul = document.getElementsByClassName("visual_img")[0];
	slideRight(ul);
	disablePrevAndNextButton();
	paginate(-1);
}

function disablePrevAndNextButton() {
	var groupVisual = document.getElementsByClassName("group_visual")[0];
	var nextButton = groupVisual.getElementsByClassName("btn_nxt")[0];
	var prevButton = groupVisual.getElementsByClassName("btn_prev")[0];
	disableElement(nextButton, 1000);
	disableElement(prevButton, 1000);
}

function disableElement(element, time) {
	var orgDisplay = element.style.display;
	element.style.display = "none";
	window.setTimeout(()=> {
		element.style.display = orgDisplay;
	}, time);
}

function paginate(movePage) {
	var curSpan = document.querySelector(".pagination .num");
	var len = document.querySelector(".pagination .off span").innerText * 1;
	var cur = curSpan.innerText * 1;
	
	cur += movePage;
	cur = cur < 1 ? len : cur;
	cur = cur > len ? 1 : cur;
	
	curSpan.innerText = cur;
}

/**
 * 비주얼영영 밑부분 더보기버튼 이벤트
 */
function addMoreButtonClickEvent() {
	var sectionStoreDetails = document.getElementsByClassName("section_store_details")[0];
	var buttons = sectionStoreDetails.getElementsByClassName("bk_more");
	var storeDetails = sectionStoreDetails.getElementsByClassName("store_details")[0];

	for (let button of buttons) {
		button.addEventListener("click", function(event){
			var target = 0, other = 1;
			if (event.currentTarget.className == "bk_more _close") {
				target = 1; other = 0;
			}
			
			buttons[target].style.display = "none";
			buttons[other].style.display = "block";
			setStoreDetails(storeDetails, target);
		});
	}
}

function setStoreDetails(storeDetails, target) {
	if (target == 0) {
		storeDetails.classList.remove("close3");
	} else {
		storeDetails.classList.add("close3");
	}
}


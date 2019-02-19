/**
 * @description : 수신된 item과 html mapping
 */
function replaceTemplateHTML(item, html){
	for(attribute in item){
		html = html.split('${'+attribute+'}').join(item[attribute]);
	}
	return  html;
}

/**
 * @description : Animation 설정
 */
function setRightMoveAnimation(ulId, movePixels, intervalMillis){
	var ul = document.querySelector(ulId);
	
	ul.style.right = 0;
	
	setTimeout(function run(){
		ul.style.transition = "right " + intervalMillis/1000 + "s";
		ul.style.right = parseInt(ul.style.right) + movePixels + "px";
		
		if(parseInt(ul.style.right) > movePixels){
			ul.innerHTML += ul.firstElementChild.outerHTML;
			ul.style.transition = "right 0s";
			ul.style.right = 0;
			ul.removeChild(ul.firstElementChild);
		}
		
	    setTimeout(run, intervalMillis);
	}, intervalMillis / 2);
}
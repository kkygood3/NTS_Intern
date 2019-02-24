const MAX_COMMENT_LENGTH = 400;

document.addEventListener('DOMContentLoaded', function() {
	initRationChangeEvent();
	initCommentChangeEvent();
	initImageChangeEvent();
	initWriteBtnClickEvent();
});


function initRationChangeEvent(){
	var starsArea = document.querySelectorAll('.rating input');
	var starsNumArea = document.querySelector('.star_rank');
	
	document.querySelector('.rating').addEventListener('click',function(evt){
		var clickedBtn = evt.target;
		
		if(clickedBtn.tagName == 'INPUT'){
			var score = parseInt(clickedBtn.value);
			starsNumArea.innerText = score;
			for(var i = 0; i < 5; i++){
				if(i < score){
					starsArea[i].checked = true;
				} else {
					starsArea[i].checked = false;
				}
			}
		}
	});
}

function initCommentChangeEvent(){
	var textArea = document.querySelector('textarea');
	var textCountArea = document.querySelector('.guide_review span');
	var textGuideArea = document.querySelector('.review_write_info');

	textGuideArea.addEventListener('click',()=>{
		textGuideArea.style.display = 'none';
		textArea.focus();
	});
	textArea.addEventListener('focusout',()=>{
		if(textArea.value.length < 1){
			textGuideArea.style.display = '';
		}
	});
	textArea.addEventListener('keypress',textChangeHandler);
	textArea.addEventListener('paste',textChangeHandler);
	function textChangeHandler(){
		setTimeout(function(){
			var length = textArea.value.length;
			textCountArea.innerText = length;
			if(length > MAX_COMMENT_LENGTH){
				alert('글자수는 '+MAX_COMMENT_LENGTH+'개를 넘길 수 없습니다');
				textArea.value = textArea.value.substr(0, MAX_COMMENT_LENGTH);
				textCountArea.innerText = MAX_COMMENT_LENGTH;
			}
		},10);
	}
}

function initImageChangeEvent(){
	var imageInput = document.querySelector('.hidden_input');
	var thumnailWrap = document.querySelector('.lst_thumb .item');
	var imageArea = thumnailWrap.querySelector('img');
	imageInput.addEventListener('change',function(evt){
		const image = evt.target.files[0];
		if(image){
			thumnailWrap.style.display = '';
			imageArea.src = window.URL.createObjectURL(image);
		}
	})
	
	var deleteImageBtn = document.querySelector('.ico_del');
	deleteImageBtn.addEventListener('click', ()=>{thumnailWrap.style.display = 'none'; imageInput.value = '';});
}

function initWriteBtnClickEvent(){
	var writeReviewBtn = document.querySelector('.bk_btn');
	
	var textArea = document.querySelector('textarea');
	var starsNumArea = document.querySelector('.star_rank');
	var imageInput = document.querySelector('.hidden_input');
	var productId = document.querySelector('#productIdInput').value;
	var reservationInfoId = document.querySelector('#reservationInfoIdInput').value;
	
	writeReviewBtn.addEventListener('click', function(evt){
		evt.preventDefault();
		// 글자수만 넘기면 POST 조건 충족
		if(textArea.value.length > 0){
			document.querySelector('.bk_btn').style.display = 'none';
			
			var writeForm = new FormData();
			writeForm.append('comment', textArea.value.substr(0, MAX_COMMENT_LENGTH));
			writeForm.append('score', parseInt(starsNumArea.innerText));
			writeForm.append('productId', productId);
			
			if(imageInput.files.length > 0){
				writeForm.append('imageFile', imageInput.files[0]);
			}
			
			requestAjax(writeResponseHandler, '/api/reservations/'+reservationInfoId+'/comments', 'post', writeForm);
		}
	});
}

function writeResponseHandler(response){
	if(!response || response.result != 'OK'){
		document.querySelector('.bk_btn').style.display = '';
		alert('리뷰 등록에 실패했습니다.\r\n잠시 후에 다시 시도해주시기 바랍니다.');
	} else {
		alert('등록 되었습니다.');
		location.href = '/myreservation';
	}
}
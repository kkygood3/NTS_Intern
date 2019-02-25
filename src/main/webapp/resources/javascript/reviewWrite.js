/**
 * 
 */

let reviewWrite = {
	imageBox : document.querySelector('.item'),
	imageFile : document.querySelector('#reviewImageFile'),
	stars : document.querySelectorAll('.rating_rdo'),
	score : document.querySelector('.star_rank'),
	reviewField : document.querySelector('.review_textarea'),
	submitButton : document.querySelector('.bk_btn'),
	
	file : '',
	fileName : '',
	contentType : ''
};

// file
reviewWrite.imageFile.addEventListener('change', e=>{
	let image = e.target.files[0];
	if(!validImageType(image)){
		alert('지원하는 형식의 파일이 아닙니다.');
		return;
	}
	reviewWrite.file = image;
	reviewWrite.fileName = image.name;
	reviewWrite.contentType = image.type;
	
	let elImage = document.querySelector('.item_thumb');
	elImage.src = window.URL.createObjectURL(image);
	
	reviewWrite.imageBox.style.display = "inline-block";
});

function validImageType(image){
	const result = (['image/jpg', 'image/png', 'image/jpeg'].indexOf(image.type) > -1);
	return result;

document.querySelector('.ico_del').addEventListener('click', e=>{
	reviewWrite.imageFile.value = '';
	reviewWrite.imageBox.style.display = "none";
	});

}


// 별점
reviewWrite.stars.forEach(star=>{
	star.addEventListener('click', e=>{
		// 숫자 점수
		reviewWrite.score.innerText = star.value;
		reviewWrite.score.classList.remove('gray_star');
		let targetValue = parseInt(e.target.value);
		for(let i = 2; i < 7; i++){
			if(i <= targetValue + 1){
				document.querySelector('input[name="rating' + i + '"]').checked = true;
			}else{
				document.querySelector('input[name="rating' + i + '"]').checked = false;
			}
		}
		
	});
});


// review
document.querySelector('.review_write_info').addEventListener('click', e=>{
	document.querySelector('.review_write_info').style.display = 'none';
	reviewWrite.reviewField.focus();
});
reviewWrite.reviewField.addEventListener('keyup', e=>{
	document.querySelector('.guide_review').firstElementChild.innerText = reviewWrite.reviewField.value.length;
});
reviewWrite.reviewField.addEventListener('blur', e=>{
	let textLength = reviewWrite.reviewField.value.length;
	if(textLength == 0){
		document.querySelector('.review_write_info').style.display = 'block';
		}
});


// submit
function submit(){
	let oReq = new XMLHttpRequest();
	oReq.onreadystatechange = function(data) {
		if (this.readyState == 4 && this.status == 200) {
			location.href = '/myreservation';
		}else if(this.status == 400){
			alert('Upload Fail!!')
		}
	};
	oReq.open('POST', '/api/reservations/' + reviewWrite.submitButton.dataset.reservationInfoId + '/comments');
	oReq.send(combineCommentData());
	}


reviewWrite.submitButton.addEventListener('click', e=>{
	// check validation()
	submit();
});
function combineCommentData(){
	let formData = new FormData();
	formData.append('reservationInfoId', reviewWrite.submitButton.dataset.reservationInfoId);
	formData.append('productId', reviewWrite.submitButton.dataset.productId);
	formData.append('score', reviewWrite.score.innerText);
	formData.append('comment', reviewWrite.reviewField.value);
	formData.append('fileName', reviewWrite.fileName);
	formData.append('contentType', reviewWrite.contentType);
	formData.append('file', reviewWrite.file);
	return formData;
}
function RegularExpression() {
	this.name = /^([가-힣]{2,})$|^(([A-Z][a-z]*\s)+[A-Z][a-z]*)$/;
	this.tel = /^01[016-9]-[0-9]{3,4}-[0-9]{4}$/;
	this.email = /^[a-z][-\.\w]*@[a-z][-\.\w]*\.[a-z]{2,3}$/i;
}

function isValidReservationInput(input, regExp) {
	return regExp.test(input) ? true : false;
}

function isValidAllReservationInputs() {
	var regex = new RegularExpression();
	var form = document.getElementsByClassName("form_horizontal")[0];
	var inputs = form.getElementsByTagName("input");
	
	if (!existCountOverZero()) {
		return false;
	} if (!isValidReservationInput(inputs[0].value, regex.name)) {
		return false;
	} if (!isValidReservationInput(inputs[1].value, regex.tel)) {
		return false;
	} if (!isValidReservationInput(inputs[2].value, regex.email)) {
		return false;
	} if (!agreed()) {
		return false;
	}
	return true;
}
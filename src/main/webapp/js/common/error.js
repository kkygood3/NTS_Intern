function getParamsForErrorPage(status) {
	return "?code=" + status + "&message=" + getErrorText(status);
}
function getErrorText(status) {
	switch(status) {
	case 400:
		return "Bad Request";
	case 404:
		return "Page Not Found";
	case 500:
		return "Internal Server Error"
	default:
		return "Unknown Error";
	}
}

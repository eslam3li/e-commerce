$(document).ready(function() {
	$("#login").click(function() {
		var name = $("#name").val();
		var radioValue = $("input[name='gender']:checked").val();
		url = 'chat.html?name=' + name + '&gender=' + radioValue;
		document.location.href = url;
	});
});

$(document).ready(function() {
	$("#send").click(function() {
		var name = $("#name").val();
		var review = $("#review").val();
		var rating = $("#rating").val();
		var jsonData = {
			"name" : name,
			"review" : review,
			"rating":rating
		};
		$.ajax({
			url : 'ReviewServlet', // servlet url
			type : 'Post', // servlet request typ
			data : jsonData, // input data
			dataType : 'json', // For output type
		});
	});
});
function start() {

	var eventSource = new EventSource("ReviewServlet");

	eventSource.onmessage = function(event) {
		console.log(event.data)
		var userR = JSON.parse(event.data);
		document.getElementById('Reviews').innerHTML +='Reviews '+userR.userRating
		document.getElementById('rev').innerHTML += '<div class="flex-w flex-t p-b-68">'+userR.userName + "<br>"
				+ userR.userReview+'</div>';
		console.table(userR)
	}
};
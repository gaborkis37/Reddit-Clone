/**
 * 
 */

$(document).ready(function(){
	
	$('.uploadBtn').on('click',function(event){
		$('.editPicModal #profilepicUpdate').modal('hide');
		$('.uploadPicModal #profilepicUpload').modal();
	});
});	
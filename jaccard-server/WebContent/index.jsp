<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	function uploadFile() {
		var title = $("#title").val();
		var filedata = $("#myfile").val();
		$.ajax({
			url : "UploadFileServlet",
			type : "post",
			dataType : "text",
			data : {
				title : title,
				filedata : filedata
			},
			success : function(datastr) {

			},
			error : function() {
				alert("error!");
			}
		});
	}
</script>
</head>
<body>
	<h1>Specify Details</h1>
	<div style="height: 200px; width: 800px; border: 1px solid black">
		<div style="margin-left: 20px">
			<form action="UploadFileServlet" enctype="multipart/form-data"
				method="post">
				<h2>Document Title:</h2>
				<input type="text" style="width: 500px;" name="tilte">
				<div style="margin-top: 20px">
					<input type="file" name="myfile">
				</div>
				<div style="margin: 20px 300px">
					<input type="submit" style="width: 150px;" value="Compare Document">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
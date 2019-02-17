<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="notify/notify.js"></script>
<script src="notify/notify.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Spark</title>
</head>

<style>
.button {
  border: 2px solid black;
  border-radius: 5px;
  background-color: white;
  color: black;
  padding: 10px 23px;
  font-size: 16px;
  cursor: pointer;
}
.btn {
  border-color: #2196F3;
  color: dodgerblue
}

.btn:hover {
  background: #2196F3;
  color: white;
}
</style>






<body>

<!--headr-->
<div class="container-fluid" style="background-color:rgb(153,153,153);">
<div class="container">
<div class="col-md-12" style=" height:50px;"></div>

</div>

</div>

<!--headr end-->

<div class="col-md-12" style="background-image:url(img/sparkbackimg.png); background-repeat:no-repeat; height:575px;width:100%; background-size: 100% 100%;"" >


<div >
	<div id="frmerror"></div>
	<label style="margin-left:80px;margin-top:50px;color:white">From-FilePath</label>&nbsp;&nbsp;
		<select id="ffp" style="border-radius: 5px; font-size: bold ;height: 28px">
						 <option value="">File Format
						 <option value="csv">CSV</option>
        				 <option value="xml">XML</option>
       					 <option value="json">JSON</option>
        				 <option value="txt">TXT</option>
        				 <option value="zip">ZIP</option>
        				 <option value="hdfs">HDFS</option>
     	 </select> 
	<input id="frompath" placeholder="Enter the filepath" style="margin-right:83px;width: 250px"/>
	<label style="margin-right:3px; color:white; margin-top: 184px;">To-FilePath</label>&nbsp;&nbsp;
		<select id="tfp" style="border-radius: 5px; font-size: bold;height: 28px" >
						 <option value="">File Format
						 <option value="local">LOCAL</option>
						 <option value="csv">CSV</option>
        				 <option value="xml">XML</option>
       					 <option value="json">JSON</option>
        				 <option value="txt">TXT</option>
        				 <option value="zip">ZIP</option>
     	 </select> 
	<input id="topath" placeholder="Enter the filepath" style="margin-right:160px;width: 250px" />
	
</div>
<button id="submit" style="margin-left:891px;margin-top:78px" class="button btn">Submit</button>
</div>


</body>



<script>

$(document).ready(function(){
	$("#submit").on("click", function() {
	var frmsel = $("#ffp").val();
	var tosel =$("#tfp").val();
	var frmip =$("#frompath").val();
	var toip =$("#topath").val();
	
	if(frmsel == null && frmip == null){
		$("frmerror").notify("Enter the File Path", "error");
	}
	
 	$.ajax({
        type:"POST",
        url :"url",
        data :{fromselect: frmsel,toselect: tosel,frominput: frmip,toinput: toip},
        
         success: function(responseText) {
               
               }
           }); 
	
	
	
	});
});








</script>
</html>

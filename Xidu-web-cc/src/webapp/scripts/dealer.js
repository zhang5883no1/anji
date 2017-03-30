function confirmdel(delid)
{	
	if(!confirm( "删除该用户?")){
		return false;
	}
	
	var msg = "#msg" + delid;
	
	$.ajax({
		   type: "DELETE",
		   url: "dealer/" + delid,
		   data: "",
		   beforeSend:function(){$(msg).text("deleting......");},
		   error: function(){
		        alert('Error');
		    },
		   success: delcallback
	}); 

}

function delcallback(msg){
	//alert( "Data delete: " + msg );
	$('#queryform').submit();
}

//valide the time format
function isDate(dateString){
	  if(dateString.trim() == "") return true;
	  alert(dateString);
	  var r=dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	  if(r==null){
		  alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例    如：2008-08-08\n\r");
		  return false;
	  }
	        var d=new Date(r[1],r[3]-1,r[4]);   
	  var num = (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
	  if(num==0){
	   alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例    如：2008-08-08\n\r");
	  }
	  return (num!=0);
	  
	}  


$(function(){
});

/**
向下弹出提示框
currentObj:当前对象
htmlText:弹出提示框内容
**/
function downwardPopup(currentObj,htmlText){
	var $currentObj=$(currentObj);
	var contentDiv=$('<div class="downwardPopup"><p>'+htmlText+'</p></div>');
	$currentObj.append(contentDiv);
	var objTop=$currentObj.offset().top;
	var objLeft=$currentObj.offset().left;
	var objHeight=$currentObj.height();
	var objWidth=$currentObj.width();
	var contentWidth=contentDiv.width();
	contentDiv.offset({top:objTop+objHeight+2,left:objLeft-(contentWidth-objWidth)/2});
	contentDiv.show();
}

function removeDownwardPopup(){
	$('.downwardPopup').remove();
}

/**
* 验证是id对象的值是否为空
* @param id
* @return
*/
function validateIsNull(idObj){
	//检验是否存在此对象
	if(idObj==null){
		return false;
	}
	var idValue=$.trim(idObj.value);
	idValue=idValue.replace(/\s+/g, "");
	
	if(idValue=="" || null==idValue){
		return false;
	}else{
		return true;
	}
}

/*获取时间差 单位（秒）
	@param：String 格式yyyy-mm-dd hh24:mi:ss
*/
function getTimeStuff(date1,date2){
	var year1=date1.substr(0,4);
	var month1=date1.substr(5,2);
	var day1=date1.substr(8,2);
	var hour1=date1.substr(11,2);
	var minute1=date1.substr(14,2);
	var second1=date1.substr(17,2);

	var year2=date2.substr(0,4);
	var month2=date2.substr(5,2);
	var day2=date2.substr(8,2);
	var hour2=date2.substr(11,2);
	var minute2=date2.substr(14,2);
	var second2=date2.substr(17,2);

	var time1=new Date(year1,month1,day1,hour1,minute1,second1).getTime();
	var time2=new Date(year2,month2,day2,hour2,minute2,second2).getTime();

	if(date1>date2){
		return (time1-time2)/1000;
	}else{
		return (time2-time1)/1000;
	}
}

/**      
* 对Date的扩展，将Date 转化为指定格式的String      
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用1-2 个占位符      
* 年(y)可以用1-4 个占位符，毫秒(S)只能用1 个占位符(是1-3 位的数字)      
* eg:      
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423      
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二20:09:04      
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二08:09:04      
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二08:09:04      
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
*/      

Date.prototype.pattern=function(fmt) {          
    var o = {          
    "M+" : this.getMonth()+1, //月份          
    "d+" : this.getDate(), //日          
    "h+" : this.getHours()%24 == 0 ? 00 : this.getHours()%24, //小时          
    "H+" : this.getHours(), //小时          
    "m+" : this.getMinutes(), //分          
    "s+" : this.getSeconds(), //秒          
    "q+" : Math.floor((this.getMonth()+3)/3), //季度          
    "S" : this.getMilliseconds() //毫秒          
    };          
    var week = {          
    "0" : "\u65e5",          
    "1" : "\u4e00",          
    "2" : "\u4e8c",          
    "3" : "\u4e09",          
    "4" : "\u56db",          
    "5" : "\u4e94",          
    "6" : "\u516d"         
    };          
    if(/(y+)/.test(fmt)){          
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));          
    }          
    if(/(E+)/.test(fmt)){          
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);          
    }          
    for(var k in o){          
        if(new RegExp("("+ k +")").test(fmt)){          
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));          
        }          
    }          
    return fmt;          
}

function submitForm(formId,actionPath,param){
	var form=$('#'+formId);
	var newActionPath=actionPath;
	var position=actionPath.indexOf('?');
	if(position>0){
		newActionPath=actionPath.substring(0,position)+"?currTime="+(new Date()).getTime()+"&"+actionPath.substring(position+1);
	}else{
		newActionPath=actionPath+"?currTime="+(new Date()).getTime();
	}
	form.attr('action',newActionPath);
	if(1==param){
		var str='';
		if('zh_CN'==VAR_LANGUAGE){
			str='<img src="'+VAR_APPLICATION_PATH+'/pic/process/processcn.gif"></img>';
		}else{
			str='<img src="'+VAR_APPLICATION_PATH+'/pic/process/processen.gif"></img>';
		}
		showSubmitDiv(str);
	}
	form.submit();
}
function submitHref(hrefPath,param){
	var newHref=hrefPath;
	var position=hrefPath.indexOf('?');
	if(position>0){
		newHref=hrefPath.substring(0,position)+"?currTime="+(new Date()).getTime()+"&"+hrefPath.substring(position+1);
	}else{
		newHref=hrefPath+"?currTime="+(new Date()).getTime();
	}
	if(1==param){
		var str='';
		if('zh_CN'==VAR_LANGUAGE){
			str='<img src="'+VAR_APPLICATION_PATH+'/pic/process/processcn.gif"></img>';
		}else{
			str='<img src="'+VAR_APPLICATION_PATH+'/pic/process/processen.gif"></img>';
		}
		showSubmitDiv(str);
	}
	document.location.href=newHref;
}



/**
 * 动态设置iframe的高度，自动调整高度
 * @param down iframe的id
 * @return
 */
function dyniframesize(down) { 
	var pTar = null; 
	if (document.getElementById){ 
		pTar = document.getElementById(down); 
	}else{ 
		eval('pTar = ' + down + ';'); 
	} 
	if (pTar && !window.opera){ 
		//begin resizing iframe 
		pTar.style.display="block" 
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){ 
			//ns6 syntax 
			pTar.height = pTar.contentDocument.body.offsetHeight +20; 
    		//pTar.width = pTar.contentDocument.body.scrollWidth+20; 
			pTar.width = pTar.contentDocument.body.scrollWidth;
		}else if (pTar.Document && pTar.Document.body.scrollHeight){ 
			//ie5+ syntax 
			pTar.height = pTar.Document.body.scrollHeight; 
			pTar.width = pTar.Document.body.scrollWidth; 
		} 
	} 
} 	
//提供此方法是为了获取ID方便
function $id(objId){
	return document.getElementById(objId);
}
//弹出层移动
function frameMove(elem){
	var option=this;
	option.deltaX=0;
	option.deltaY=0;
	function moveStart(event){
		var left=$(this).offset().left;
		var top=$(this).offset().top;
		var mouseX=event.clientX;
		var mouseY=event.clientY;
		option.deltaX=mouseX-left;
		option.deltaY=mouseY-top;
		$(document).bind("mousemove",moving);
		$(document).bind("mouseup",moveEnd);
		event.preventDefault();
	}
	
	function moving(event){
		elem.css({
			left:(event.clientX-option.deltaX)+"px",
			top:(event.clientY-option.deltaY)+"px"
		})
		event.preventDefault();
	}
	
	function moveEnd(event){
		$(document).unbind("mousemove",moving);
		$(document).unbind("mouseup",moveEnd);
	}
	
	$(document).ready(function(){
		elem.bind("mousedown",moveStart);
	})
}
		function showPostion(elem){
			var winWidth=window.screen.width;
			var winHeight=window.screen.height;
			var rWidth=elem.width();
			var rHeight=elem.height();
			var element=document.documentElement;
			var scrollTop=element.scrollTop;
			var scrollLeft=element.scrollLeft;
			var top=(winHeight-rHeight)/2+scrollTop;
			var left=(winWidth-rWidth)/2+scrollLeft;
			var errorMsg=elem.find(".errors");
			$(".bground").css("height",$("body").height()).show();
			if(errorMsg.length>0){
				errorMsg.hide();
			}
			elem.css("top",top).css("left",left).show();
			frameMove(elem);
		}
		
	
	function checkNull(className){
		var isOk=true;
		$("."+className).each(function(){
 			$(this).parent().find("span").remove();
 			if($(this).hasClass("mandatory") && $(this).val()=='') {
 				var info =$(this).attr("title")+"不能为空!";
				var $span=$("<span>",{html:info});
				$(this).parent().append($span);	
				$span.css("backgroundColor","#390");
				$span.css("padding","3px");
 				isOk=false;
 			}
 			if($(this).hasClass("email")){
 				var pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		 		var chkFlag = pattern.test($(this).val());
		 		if(!chkFlag){
		 			var info ="邮箱格式不正确";
					var $span=$("<span>",{html:info});
					$(this).parent().append($span);	
					$span.css("backgroundColor","#390");
					$span.css("padding","3px");
	 				isOk=false;
		 		}
 			}
 			if($(this).hasClass("integer")){//整数
 				var pattern = /^[0-9]*$/;
		 		var chkFlag = pattern.test($(this).val());
		 		if(!chkFlag){
		 			var info ="必须为整数";
					var $span=$("<span>",{html:info});
					$(this).parent().append($span);	
					$span.css("backgroundColor","#390");
					$span.css("padding","3px");
	 				isOk=false;
		 		}
 			}
 			
 			if($(this).hasClass("mandatory") &&  $(this).is("select") && $(this).val()=='0') {
 				var info =$(this).attr("title")+"不能为空!";
				var $span=$("<span>",{html:info});
				$(this).parent().append($span);	
				$span.css("backgroundColor","#390");
				$span.css("padding","3px");
 				isOk=false;
 			}
 			if($(this).hasClass("mandatory") &&  $(this).is("textarea") ) {
 				if($(this).text().lenght==0){
 					var info =$(this).attr("title")+"不能为空!";
 					var $span=$("<span>",{html:info});
 					$(this).parent().append($span);	
 					$span.css("backgroundColor","#390");
 					$span.css("padding","3px");
 	 				isOk=false;
 					
 				}else if($(this).text().length>$(this).attr("maxLength")){
 					var info =$(this).attr("title")+"过长!";
 					var $span=$("<span>",{html:info});
 					$(this).parent().append($span);	
 					$span.css("backgroundColor","#390");
 					$span.css("padding","3px");
 	 				isOk=false;
 				}
 				
 			}
 		});
		return isOk;
	}	
	
	function validateCheckBox(className){
		var isOk=true;
		$("."+className).each(function(){
			if($(this).find("input[type='checkbox']:checked").length==0){
				alertMsg('请选择'+$(this).attr("title"),'提示');
				isOk=false;
				return false;
			}
 		});
		return isOk;
		
	}
		
	
	function alertMsg(textMsg, alertType) {
	    $("#errorMessage").html(textMsg).dialog(
	        {modal:true,
	            buttons: {
	                "确认": function() {
	                    $(this).dialog("close")
	                }
	            },
	            title:alertType
	        });
	}
	
	
	//for anji
	function clearAll(){
		$(".userInput").filter("input[type='hidden']").val('');
		$(".userInput").filter("input[type='text']").val('');
		$(".userInput").filter("input[type='radio']:checked").removeAttr('checked');
		$(".userInput").filter("input[type='checkbox']:checked").removeAttr('checked');
		$(".userInput").filter("textarea").text('');
		$(".userInput").filter("select").attr("value",'');
	}
	
	//validate form
	function validateForm(){
		$('.validSpan').remove();
		var count=0;
		//validate required
		$(".RequiredField").each(function(i){
			//text
			if($(this).is("input[type='text']") && $.trim($(this).val())==''){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be not null.</span>');
				$(this).after(validSpan);
				count++;
			}else{
				$(this).val($.trim($(this).val()));
			}
			//radio
			if($(this).is("input[type='radio']") && $('input[name="'+$(this).attr("name")+'"]:checked').length==0){
				if($(this).val()==$('input[name="'+$(this).attr("name")+'"]:last').val()){// if the last
					var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be not null.</span>');
					$('input[name="'+$(this).attr("name")+'"]:last').after(validSpan);
					count++;
				}
				
			}
			//checkbox
			if($(this).is("input[type='checkbox']") && $('input[name="'+$(this).attr("name")+'"]:checked').length==0){
				if($(this).val()==$('input[name="'+$(this).attr("name")+'"]:last').val()){// if the last
					var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be not null.</span>');
					$('input[name="'+$(this).attr("name")+'"]:last').after(validSpan);
					count++;
				}
				
			}
			//textarea
			if($(this).is("input[type='textarea']") && $(this).text()==''){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be not null.</span>');
				$(this).after(validSpan);
				count++;
			}
			//select
			if($(this).is("select") && $(this).find('option:first').is(":selected")){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be not null.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		
		//validate integer
		$(".integer").each(function(i){
			if($(this).val()!='' && !/^\d+$/.test($(this).val())){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be Integer.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		
		//validate double
		$(".double").each(function(i){
			if($(this).val()!='' && !/^(\d+)(.{1})(\d+)$/.test($(this).val())){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be double.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		
		//validate telephone
		$(".telephone").each(function(i){
			if($(this).val()!='' && !/^(13[0-9]{9}$)|(15[0-35-9][0-9]{8}$)|([0-9]{1}[0-9]{2,3}-[1-9]{1}[0-9]{5,8}$)|(18[05-9][0-9]{8}$)/.test($(this).val())){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be telephone.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		
		//validate email
		$(".email").each(function(i){
			if ($(this).val()!='' && ($(this).val().charAt(0) == '.' || $(this).val().charAt(0) == '@' || $(this).val().indexOf('@', 0) == -1
					|| $(this).val().indexOf('.', 0) == -1 || $(this).val().lastIndexOf('@') == $(this).val().length-1 || $(this).val().lastIndexOf('.') == $(this).val().length-1)){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be email.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		
		$(".validExist").each(function(i){
				if(ajaxValid($(this).val(),$(this).attr('title'),$("#id").val())=="isExist"){
					var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' is exist.</span>');
					$(this).after(validSpan);
					count++;
				}
		});
		//validate Number
		$(".Number").each(function(i){
			if(isNaN($(this).val())){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be Number.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		//validate ContainerNumber
		$(".CtnNumber").each(function(i){
			if(!chkcntrno($(this).val(),0)){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' Error Container Number.</span>');
				$(this).after(validSpan);
				//count++;
			}
		});
		//validate number and 
		$(".NumReq").each(function(i){
			//text
			if(isNaN($(this).val())){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be number.</span>');
				$(this).after(validSpan);
				count++;
			}
			if(($(this).is("input[type='text']") && $(this).val()=='')){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be not null.</span>');
				$(this).after(validSpan);
				count++;
			}
			
		});
		
	if($("#currentPage")[0]){
		if(parseInt($("#currentPage").val())>0&&parseInt($("#currentPage").val())==$("#currentPage").val()){

		}else{
			alert("error page number");
			count++;
		}
	}
	
	if($("#pagerSize")[0]){
		if(parseInt($("#pagerSize").val())>0&&parseInt($("#pagerSize").val())==$("#pagerSize").val()&&parseInt($("#pagerSize").val())<=2147483647){

		}else{
			alert("error page number");
			count++;
		}	
	}
	
	$(".quantity").each(function(i){
		if($(this).val()!='' && !(/^(\d+)(.{1})([0]+)$/.test($(this).val())||/^(\d+)$/.test($(this).val()))){
			var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' is error.</span>');
			$(this).after(validSpan);
			count++;
		}
	});
	
		if(count==0){
			return true;
		}
		return false;
	}
	
	function submitFormForAnji(formId,actionPath,param){
		var form=$('#'+formId);
		var newActionPath=actionPath;
		var position=actionPath.indexOf('?');
		if(position>0){
			newActionPath=actionPath.substring(0,position)+"?currTime="+(new Date()).getTime()+"&"+actionPath.substring(position+1);
		}else{
			newActionPath=actionPath+"?currTime="+(new Date()).getTime();
		}
		form.attr('action',newActionPath);
		if(validateForm()){
			if(param!="2"){
				showSubmitDiv('');
			}
			form.submit();
		}
		
	}
	
	
	
	//show query
	function showQuery(key,targetId){
		window.open(WEB_CONTEXT+'/common/showQuery/init?queryKey='+key+'&targetId='+targetId,'newwindow','height=500,width=600,top=200,left=200,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
	}
		
	
	function ajaxValid(isvalue,type,id){
		var result="";
		var isurl=""+location;
		for(var i=0;i<4;i++){
			isurl=isurl.substring(isurl.indexOf("/")+1,isurl.length);
		}
		var isvalueurl=""+location;
		type = type.replace(" ","_");
		$.ajax({
			method:"GET",
			async:false,
			url:isvalueurl.substring(0,isvalueurl.indexOf(isurl))+"common/validDistinct/valid?code="+isvalue+"&type="+type+"&id="+id+"&currTime="+(new Date()).getTime(),
			success:function(msg){
				result+=msg;
			}
		});
		return result;
	}
	
	function submitHrefForAnji(url,param){
		var position=url.indexOf('?');
		if(position>0){
			newActionPath=url.substring(0,position)+"?currTime="+(new Date()).getTime()+"&"+url.substring(position+1);
		}else{
			newActionPath=url+"?currTime="+(new Date()).getTime();
		}
		showSubmitDiv('');
		location.href=newActionPath;
		
	}
	
	function alertForAnji(msg,param){
		alert(msg);
	}
	
	function comfirmForAnji(msg,param){
		return confirm(msg);
	}
	
	

function showSubmitDiv(str){ 
	$(".bground").animate({opacity:"0.4"},"normal");//设置透明度
	$(".bground").show();
	//elem.css("top",top).css("left",left).append(img).show();
} 
	
	
	
	
	//validate container No info
	//去除字符串的空格
	function gf_trim(as_string)
	{
	   while(as_string.length > 0 && as_string.indexOf(" ")==0) as_string = as_string.substr(1);
	   while(as_string.length > 0 && as_string.lastIndexOf(" ")==(as_string.length-1)) as_string = as_string.substr(0,as_string.length-1);
	   return as_string;
	}
	//集装箱箱号验证
	//功能：验证集装箱箱号：
	//参数：
	//   as_cntrno 是否符合国际标准，
	//返回值：True 符合国际标准或强行通过(特殊箱号)
	//举例：gf_chkcntrno( 'TEXU2982987', 0 )     
	function chkcntrno(as_cntrno,ai_choice)
	{
	 var fi_ki;
	 var fi_numsum;
	 var fi_nummod;
	 var fai_num = new Array(11);
	 var fb_errcntrno=false;

	 if (as_cntrno==null) return true; //null不进行验证
	 if (gf_trim(as_cntrno)=="") return true; //空不进行验证
	 
	 if (as_cntrno.length == 11)   //国际标准为11位，最后一位是校验位，若不是11位肯定不是标准箱
	 { for(fi_ki=1;fi_ki<=11;fi_ki++)
	   fai_num[fi_ki] = 0;
	  for(fi_ki=1;fi_ki<=4;fi_ki++)     //根据国际标准验证法则处理箱号前面的4个英文字母
	  {
	   fch_char=as_cntrno.charAt(fi_ki-1).toUpperCase();
	   switch(true)
	   { case (fch_char=="A"):{fai_num[fi_ki] = 10;break;}
	    case (fch_char>="V" && fch_char<="Z"):{fai_num[fi_ki] = fch_char.charCodeAt() - 52;break;}
	    case (fch_char>="L" && fch_char<="U"):{fai_num[fi_ki] = fch_char.charCodeAt() - 53;break;}
	    default:{fai_num[fi_ki] = fch_char.charCodeAt() - 54;break;}
	   }
	  }
	  for(fi_ki=5;fi_ki<=11;fi_ki++)
	  {  fch_char=as_cntrno.charAt(fi_ki-1);
	   fai_num[fi_ki] = parseInt(fch_char); //ctype((mid(as_cntrno, fi_ki, 1)), integer)
	      }
	  fi_numsum = 0
	  
	  for(fi_ki=1;fi_ki<=10;fi_ki++)
	  { 
	   fi_sqr = 1;
	   for(i=1;i<fi_ki;i++){fi_sqr *=2;}
	   fi_numsum += fai_num[fi_ki] * fi_sqr;
	  }

	  if (as_cntrno.substr(0,4) == "HLCU") fi_numsum = fi_numsum - 2; //hapaq lloyd的柜号与国际标准相差2
	  fi_nummod = fi_numsum % 11;
	  if (fi_nummod == 10) fi_nummod = 0;
	  if (fi_nummod == fai_num[11]) fb_errcntrno = true;
	  return fb_errcntrno;
	 }else{
	    return fb_errcntrno;
	 }  
	} 
	
	//validate Number
	function validateNumber(){
		var count=0;
		$(".Number").each(function(i){
			if(isNaN($(this).val())){
				var validSpan=$('<span class="validSpan">'+$(this).attr('title')+' must be Number.</span>');
				$(this).after(validSpan);
				count++;
			}
		});
		if(count==0){
			return true;
		}
		return false;
	}

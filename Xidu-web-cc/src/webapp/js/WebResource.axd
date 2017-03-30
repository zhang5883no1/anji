//获得或者失去焦点变颜色
function changeColor(inputID,color)
{
	document.getElementById(inputID).style.backgroundColor = color;
}
//校验TextBox数据是否正确
function isValid(inputID,regExps,Color,errMsg,clearText)
{
	var inputValue = document.getElementById(inputID).value;
	if(inputValue == "")
	{
		changeColor(inputID,Color);
		return true;
	}
	
	if(!RegExp) return true;
	var reg = new RegExp(regExps);
	if(!reg.test(inputValue))
	{
		alert(errMsg);
		document.getElementById(inputID).focus();
		if(clearText)
			document.getElementById(inputID).value = "";//？是否要清空控件的值

		return false;
	}
	else
	{
		changeColor(inputID,Color);
		return true;
	}

}

//校验TextBox数据是否包含特殊字符
function isSpecial(inputID,regExps,Color,errMsg,clearText)
{
	var inputValue = document.getElementById(inputID).value;
	if(inputValue == "")
	{
		changeColor(inputID,Color);
		return true;
	}
	
	if(!RegExp) return true;
	var reg = new RegExp(regExps);
	if(reg.test(inputValue))
	{
		alert(errMsg);
		document.getElementById(inputID).focus();
		if(clearText)
			document.getElementById(inputID).value = "";//？是否要清空控件的值

		return false;
	}
	else
	{
		changeColor(inputID,Color);
		return true;
	}

}
//限制输入
function restrictedInput(regExp)
{
	var srcElem = event.srcElement;
	var oSel = document.selection.createRange();
	var srcRange = srcElem.createTextRange();
	oSel.setEndPoint("StartToStart", srcRange);
	var num = oSel.text + String.fromCharCode(event.keyCode) + srcRange.text.substr(oSel.text.length);
	event.returnValue = regExp.test(num);
}

//回车触发Button的Click事件
function keydown(buttonid)
{
	if(window.event.keyCode == 13)
	{
		var button = document.getElementById(buttonid);
		if(typeof button != "undefined")
		{
			button.click();	
			event.returnValue = false;
		}
	}
}

function nokeydown()
{
	if(window.event.keyCode == 13)
	{
		event.returnValue = false;
	}
}

//回车聚焦指定控件
function keytotab(controlid)
{
	if(window.event.keyCode == 13)
	{
		var control = document.getElementById(controlid);
		if(typeof control != "undefined")
		{
			control.focus();
			event.returnValue = false;
		}
	}
}

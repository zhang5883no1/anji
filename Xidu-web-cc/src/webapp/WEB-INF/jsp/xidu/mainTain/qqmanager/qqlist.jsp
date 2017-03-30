<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	分配QQ
  </title>  
	<script LANGUAGE="JavaScript">	
		function search(){
			submitFormForAnji("qq_form","${webcontext}/mainTain/qqmanager/queryQQ",0);
		}
		//add 
		function addQQ(){
			$("#qq_form").attr("action","addQQ");
			submitFormForAnji("qq_form","${webcontext}/mainTain/qqmanager/addQQ",0);
			//$("#qq_form").submit();
		}
		//edit
        function editQQ(){
			var selqq = $("#tableResults input:radio:checked");
        	if(selqq.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	$("#qqId").val(selqq.val());
        	$("#qq_form").attr("action","editQQ");
			submitFormForAnji("qq_form","${webcontext}/mainTain/qqmanager/editQQ",0);
        }
		//delete
        function deleteQQ(){
        	var selqq=$("#tableResults input:radio:checked");
        	if(selqq.length==0){
        		alertForAnji('Please select one record.',null);
        		return;
        	}
        	if(comfirmForAnji('Do you confirm to delete this record?',null)){
	        	$("#qqId").val(selqq.val());
	        	$("#qq_form").attr("action","deleteQQ");
				submitFormForAnji("qq_form","${webcontext}/mainTain/qqmanager/deleteQQ",0);
//		 		$("#menu_form").submit();
		    }
        }
    </script> 
<head></head>
<body style="cursor: default;" leftmargin="0" topmargin="0">

<%@include file="../../include/head.jsp"%>
<form:form  id="qq_form" name ="qq_form" action="queryQQ" method="post" modelAttribute="queryQQBasicInfoDto">
	<form:hidden path="id" id="qqId"/>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">QQ分配</span></div>
		</div>
		<div class="search-wrap">
            <div class="search-content">
					<table class="search-tab">
					<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130">房间:</td>
							<td width="130">
								<form:select path="roomId" cssClass="userInput">
									<form:option value="">--未选择--</form:option>
									<core:forEach items="${queryQQBasicInfoDto.rooms}" var="roomlist">
										<form:option value="${roomlist.id }">${roomlist.roomName }</form:option>
									</core:forEach>
								</form:select>
							</td>
						<tr>
						</table>
						<td style="padding-right:2px;" align="right" nowrap="nowrap"><span id="lblMaxRows" class="LabelBold">返回行数:</span>
								<form:input id="pagerSize" path="pager.pageSize" cssStyle="width:40px;"/>
								<input name="btnSearch" value="查询" onClick="search();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
								<input name="btnSearch" value="清空" onClick="clearAll();" id="btnSearch" class="btn btn-primary btn2" type="button"/>
							</td>

						</tr>
					</table>
            </div>
        </div>
		<div class="result-wrap">
			<div class="result-content">
			<table id="tableResults" class="result-tab" width="100%">
				<tr>
					<td style="text-align: center;">当前值班：</td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.name}</font></td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.QQ1}</font></td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.QQ2}</font></td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.QQ3}</font></td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.QQ4}</font></td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.QQ5}</font></td>
					<td style="text-align: center;"><font color="#F14952">${currentQQBasicInfoDto.QQ6}</font></td>
				</tr>
				<tr><td colspan="8">
					<div class="result-title">
                    <div class="result-list">
                        <a href="javascript:void(0)" onClick="addQQ();" name="btnAdd">新增</a>
                        <a id="btnReset" href="javascript:void(0)" onClick="editQQ();">置顶</a>
                        <a id="btnReset" href="javascript:void(0)" onclick="deleteQQ();">删除</a>
                    </div>
                </div>
				</td></tr>
				<tr>
					<td width="5%" style="text-align: center;">选择</td>
					<td width="15%" style="text-align: center;">名称</td>
					<td width="10%" style="text-align: center;">QQ1</td>
					<td width="10%" style="text-align: center;">QQ2</td>
					<td width="10%" style="text-align: center;">QQ3</td>
					<td width="10%" style="text-align: center;">QQ4</td>
					<td width="10%" style="text-align: center;">QQ5</td>
					<td width="10%" style="text-align: center;">QQ6</td>
				</tr>
				<core:forEach items="${queryQQBasicInfoDto.resultList}" var="v">
					<tr>
						<td style="text-align: center;">
							<input type='radio' name='selId' value='${v.id}'/>
						</td>
						<td style="text-align: center;">
							${v.name}
						</td>
						<td style="text-align: center;">
							${v.QQ1}
						</td>
						<td style="text-align: center;">
							${v.QQ2}
						</td>
						<td style="text-align: center;">
							${v.QQ3}
						</td>
						<td style="text-align: center;">
							${v.QQ4}
						</td>
						<td style="text-align: center;">
							${v.QQ5}
						</td>
						<td style="text-align: center;">
							${v.QQ6}
						</td>					
					</tr>
				</core:forEach>
			</table>
		</div>
	</div>
</div>
</form:form>
<div id="iframe_div" style="display:none">
	<iframe src="#"></iframe>
</div>
<script LANGUAGE="JavaScript">	
  		
  </script> 
</body>
</html>
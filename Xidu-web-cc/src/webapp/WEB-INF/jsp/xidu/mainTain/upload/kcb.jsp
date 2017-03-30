<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../include/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../include/baseJsAndCss.jsp"%>

  <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
  <title>
	上传课程表
  </title>
  <script LANGUAGE="JavaScript">
		//query
		function search(){
			$("#kcb_form").attr("modelAttribute","queryKCBInfoDto");
			submitFormForAnji("kcb_form","${webcontext}/mainTain/kcb/queryKCB",0);
		}
		//save
		function saveKCB(){
			$("#btnDone").css("display","none");
			$("#tableResults input[style]").each(function(){
				$(this).css("border-style","none");
			});
			$("#kcb_form").attr("action","saveKCB");
			$("#kcb_form").attr("modelAttribute","kcbInfoDto");
			submitFormForAnji("kcb_form","${webcontext}/mainTain/kcb/saveKCB",null);			
		}
		//edit
        function editKCB(){
			$("#tableResults input[style]").each(function(){
				$(this).css("border-style","solid");
			});
			$("#btnDone").css("display","");
			
        }
	</script>
 </head>
<body leftMargin="0" topMargin="0">
<%@include file="../../include/head.jsp"%>
	<form:form  id="kcb_form" name ="kcb_form" action="queryKCB" method="post" modelAttribute="queryKCBInfoDto">
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">上传课程表</span></div>
		</div>
		<div class="search-wrap">
            <div class="search-content">
					<table class="search-tab">
					<tr><td width="80%">
					<table>
						<tr align="center">
							<td width="130" style="text-align:right;">房间:</td>
							<td width="130">
								<form:select path="roomId" cssClass="userInput">
									<form:option value="1">--未选择--</form:option>
									<core:forEach items="${queryKCBInfoDto.rooms}" var="roomlist">
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
		<div class="result-title">
		<div class="result-wrap">
			<div class="result-content">
				<table class="insert-tab" width="100%" id="tableResults">
					<tbody>
						<tr><td colspan="6">
							<div class="result-list">
								<a id="btnReset" href="javascript:void(0)" onclick="editKCB();">修改</a>
							</div>
						</td></tr>
						<tr style="background-color:#909090">
							<td style="text-align: center;">时间</span></td>
							<td style="text-align: center;">周一</span></td>
							<td style="text-align: center;">周二</span></td>
							<td style="text-align: center;">周三</span></td>
							<td style="text-align: center;">周四</span></td>
							<td style="text-align: center;">周五</span></td>
						</tr>
						<input type="hidden" name="id" value="${queryKCBInfoDto.resultList[0].id}" />
						<tr height="35px;">
							<td style="text-align: center;">
								<input type="text" name="time1_time" value="${queryKCBInfoDto.resultList[0].time1_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time1_lesson_mon" value="${queryKCBInfoDto.resultList[0].time1_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time1_teacher_mon" value="${queryKCBInfoDto.resultList[0].time1_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time1_lesson_tue" value="${queryKCBInfoDto.resultList[0].time1_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time1_teacher_tue" value="${queryKCBInfoDto.resultList[0].time1_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time1_lesson_wed" value="${queryKCBInfoDto.resultList[0].time1_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time1_teacher_wed" value="${queryKCBInfoDto.resultList[0].time1_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time1_lesson_thu" value="${queryKCBInfoDto.resultList[0].time1_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time1_teacher_thu" value="${queryKCBInfoDto.resultList[0].time1_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time1_lesson_fri" value="${queryKCBInfoDto.resultList[0].time1_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time1_teacher_fri" value="${queryKCBInfoDto.resultList[0].time1_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="text" name="time2_time" value="${queryKCBInfoDto.resultList[0].time2_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time2_lesson_mon" value="${queryKCBInfoDto.resultList[0].time2_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time2_teacher_mon" value="${queryKCBInfoDto.resultList[0].time2_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time2_lesson_tue" value="${queryKCBInfoDto.resultList[0].time2_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time2_teacher_tue" value="${queryKCBInfoDto.resultList[0].time2_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time2_lesson_wed" value="${queryKCBInfoDto.resultList[0].time2_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time2_teacher_wed" value="${queryKCBInfoDto.resultList[0].time2_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time2_lesson_thu" value="${queryKCBInfoDto.resultList[0].time2_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time2_teacher_thu" value="${queryKCBInfoDto.resultList[0].time2_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time2_lesson_fri" value="${queryKCBInfoDto.resultList[0].time2_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time2_teacher_fri" value="${queryKCBInfoDto.resultList[0].time2_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="text" name="time3_time" value="${queryKCBInfoDto.resultList[0].time3_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time3_lesson_mon" value="${queryKCBInfoDto.resultList[0].time3_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time3_teacher_mon" value="${queryKCBInfoDto.resultList[0].time3_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time3_lesson_tue" value="${queryKCBInfoDto.resultList[0].time3_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time3_teacher_tue" value="${queryKCBInfoDto.resultList[0].time3_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time3_lesson_wed" value="${queryKCBInfoDto.resultList[0].time3_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time3_teacher_wed" value="${queryKCBInfoDto.resultList[0].time3_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time3_lesson_thu" value="${queryKCBInfoDto.resultList[0].time3_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time3_teacher_thu" value="${queryKCBInfoDto.resultList[0].time3_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time3_lesson_fri" value="${queryKCBInfoDto.resultList[0].time3_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time3_teacher_fri" value="${queryKCBInfoDto.resultList[0].time3_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="text" name="time4_time" value="${queryKCBInfoDto.resultList[0].time4_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time4_lesson_mon" value="${queryKCBInfoDto.resultList[0].time4_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time4_teacher_mon" value="${queryKCBInfoDto.resultList[0].time4_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time4_lesson_tue" value="${queryKCBInfoDto.resultList[0].time4_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time4_teacher_tue" value="${queryKCBInfoDto.resultList[0].time4_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time4_lesson_wed" value="${queryKCBInfoDto.resultList[0].time4_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time4_teacher_wed" value="${queryKCBInfoDto.resultList[0].time4_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time4_lesson_thu" value="${queryKCBInfoDto.resultList[0].time4_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time4_teacher_thu" value="${queryKCBInfoDto.resultList[0].time4_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time4_lesson_fri" value="${queryKCBInfoDto.resultList[0].time4_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time4_teacher_fri" value="${queryKCBInfoDto.resultList[0].time4_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="text" name="time5_time" value="${queryKCBInfoDto.resultList[0].time5_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time5_lesson_mon" value="${queryKCBInfoDto.resultList[0].time5_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time5_teacher_mon" value="${queryKCBInfoDto.resultList[0].time5_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time5_lesson_tue" value="${queryKCBInfoDto.resultList[0].time5_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time5_teacher_tue" value="${queryKCBInfoDto.resultList[0].time5_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time5_lesson_wed" value="${queryKCBInfoDto.resultList[0].time5_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time5_teacher_wed" value="${queryKCBInfoDto.resultList[0].time5_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time5_lesson_thu" value="${queryKCBInfoDto.resultList[0].time5_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time5_teacher_thu" value="${queryKCBInfoDto.resultList[0].time5_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time5_lesson_fri" value="${queryKCBInfoDto.resultList[0].time5_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time5_teacher_fri" value="${queryKCBInfoDto.resultList[0].time5_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="text" name="time6_time" value="${queryKCBInfoDto.resultList[0].time6_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time6_lesson_mon" value="${queryKCBInfoDto.resultList[0].time6_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time6_teacher_mon" value="${queryKCBInfoDto.resultList[0].time6_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time6_lesson_tue" value="${queryKCBInfoDto.resultList[0].time6_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time6_teacher_tue" value="${queryKCBInfoDto.resultList[0].time6_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time6_lesson_wed" value="${queryKCBInfoDto.resultList[0].time6_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time6_teacher_wed" value="${queryKCBInfoDto.resultList[0].time6_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time6_lesson_thu" value="${queryKCBInfoDto.resultList[0].time6_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time6_teacher_thu" value="${queryKCBInfoDto.resultList[0].time6_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time6_lesson_fri" value="${queryKCBInfoDto.resultList[0].time6_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time6_teacher_fri" value="${queryKCBInfoDto.resultList[0].time6_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input type="text" name="time7_time" value="${queryKCBInfoDto.resultList[0].time7_time}" style="border-style:none;text-align:center;" /></br>
							</td>
							<td style="text-align: center;">
								<input type="text" name="time7_lesson_mon" value="${queryKCBInfoDto.resultList[0].time7_lesson_mon}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time7_teacher_mon" value="${queryKCBInfoDto.resultList[0].time7_teacher_mon}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time7_lesson_tue" value="${queryKCBInfoDto.resultList[0].time7_lesson_tue}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time7_teacher_tue" value="${queryKCBInfoDto.resultList[0].time7_teacher_tue}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time7_lesson_wed" value="${queryKCBInfoDto.resultList[0].time7_lesson_wed}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time7_teacher_wed" value="${queryKCBInfoDto.resultList[0].time7_teacher_wed}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time7_lesson_thu" value="${queryKCBInfoDto.resultList[0].time7_lesson_thu}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time7_teacher_thu" value="${queryKCBInfoDto.resultList[0].time7_teacher_thu}" style="border-style:none;text-align:center;" />
							</td>
							<td style="text-align: center;">
								<input type="text" name="time7_lesson_fri" value="${queryKCBInfoDto.resultList[0].time7_lesson_fri}" style="border-style:none;text-align:center;" /></br>
								<input type="text" name="time7_teacher_fri" value="${queryKCBInfoDto.resultList[0].time7_teacher_fri}" style="border-style:none;text-align:center;" />
							</td>
						</tr>
						<tr>
							<td colspan="6" style="text-align:right;">
								<input id="btnDone" class="btn btn-primary btn6 mr10" onClick="saveKCB()" value="提交" type="button" style="display:none;">
							</td>
                        </tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</form:form>
</body>
</html>
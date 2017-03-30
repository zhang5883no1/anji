function toPage(pageNo){
			$("#currentPage").val(pageNo);		
			search();
		}
		function goToPage(){
			var currentPage = parseInt($("#currentPage").val());
			var totalPage = parseInt($("#totalPage").val());
			var re = /^[0-9]+$/;
			
			if(!re.test(currentPage)){
				alert("Please enter a valid page number!");
			}else if(currentPage>totalPage){
				alert("You input exceeds the maximum number of pages of the page number, please enter again!");
			}else if($.trim(currentPage)==""){
				alert("page number is not null!");
			}else{
				toPage(currentPage);
			}
			
		}
		function gotoFirst(){
			var currentPage = parseInt($("#currentPage").val());
			if(currentPage == 1){
				alert("Current page is first page!");
			}else{
				toPage(1);
			}
		}
		function gotoLast(){
			var currentPage = parseInt($("#currentPage").val());
			var totalPage = parseInt($("#totalPage").val());
			if(currentPage == totalPage){
				alert("Current page is last page!");
			}else{
				toPage(totalPage);
			}
		}
		function gotoPre(){
			var currentPage =  parseInt($("#currentPage").val());
			if(currentPage>1){
				toPage(currentPage-1);
			}else{
				alert("Current page is last page!");
			}
		}
		function gotoNext(){
			var currentPage = parseInt($("#currentPage").val());
			var totalPage = parseInt($("#totalPage").val());
			if(currentPage<totalPage){
				toPage(currentPage-0+1);
			}else{
				alert("Current page is last page!");
			}
		
		}
		

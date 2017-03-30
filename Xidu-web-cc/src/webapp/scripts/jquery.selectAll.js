$(function(){
				   
			/*
			 *  jquery.selectAll
			 *  Qiu Xiaoyang 2011/03/31
			 *  1.#select_all全选checkbox id="select_all"
			 *  2..select_one选择单个checkbox class="select_one"
			 *  3.使用jQuery框架
			 */
				   
			var checkboxNum;			
			checkboxNum = $(".select_one").size();
			
			/*全选（点击 id="select_all" 的checkbox）*/
			$("#select_all").click(function(){
					bool = true;//bool==true:全选,bool==false:全不选
					for(i = 0; i < checkboxNum; i++){
						if($(".select_one").eq(i).attr("checked") == false){
							bool = true;
							break;
						} else {
							bool = false;
						}
					}
					if(bool){
						$(".select_one").attr("checked","true");	
					} else {
						$(".select_one").removeAttr("checked");
					}					
			});	
			
			/*选择一个(点击 class="select_one" 的checkbox)*/
			$(".select_one").click(function(){
				bool = true;//bool==true:select_one全部选中				
				if($(this).attr("checked")==false){
					$("#select_all").removeAttr("checked");
				}					
				for(i = 0; i < checkboxNum; i++){
					if($(".select_one").eq(i).attr("checked") == false){
						bool = false;
						break;
					}
				}
				if(bool){//select_one都选中，select_all也选中
					$("#select_all").attr("checked","true");
				}				
			});
			
		});
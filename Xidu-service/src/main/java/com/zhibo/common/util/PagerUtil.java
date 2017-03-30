package com.zhibo.common.util;

import java.util.ArrayList;
import java.util.List;

import com.xidu.framework.common.dto.BasePagerDto;

public class PagerUtil {

	@SuppressWarnings("rawtypes")
	public static BasePagerDto<?> setPager(BasePagerDto<?> queryDto){
		List<?> list=queryDto.getResultList();
		List resultlist=new ArrayList();
		int currentPage=queryDto.getPager().getCurrentPage();
		int pageSize=queryDto.getPager().getPageSize();
		int total=0;
		if(list.size()%pageSize!=0){
			total=queryDto.getResultList().size()/pageSize+1;
		}else{
			total=queryDto.getResultList().size()/pageSize;
		}
		if(currentPage>total){
			currentPage=1;
			queryDto.getPager().setCurrentPage(currentPage);
		}

		int start=(currentPage-1)*pageSize;
		int end=currentPage*pageSize;
		int index=0;
		if(start>list.size()){
			start=0;
			end=1*pageSize;
			currentPage=1;
			queryDto.getPager().setCurrentPage(1);
		}

		for(int i=0;i<list.size();i++){
			if(index>=start&&index<end){
				resultlist.add(list.get(i));
			}
			if(index>end){
				break;
			}
			index++;
		}
		queryDto.getPager().setTotalSize(list.size());
		queryDto.getPager().setTotalPages(list.size()%pageSize!=0?list.size()/pageSize+1:list.size()/pageSize);
		queryDto.setResultList(resultlist);
		return queryDto;
	}
}

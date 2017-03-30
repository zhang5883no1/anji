package com.zhibo.mainTain.dao;

import com.xidu.framework.common.dao.IBaseDao;
import com.xidu.framework.common.dto.BasePagerDto;
import com.zhibo.mainTain.domain.Video;
import com.zhibo.mainTain.dto.QueryVideoDto;

public interface VideoDao  extends IBaseDao<Video, Long>{

	BasePagerDto queryVideo(
			QueryVideoDto<Video> queryDto);

}

package com.zhibo.mainTain.service;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.exception.AppException;
import com.zhibo.mainTain.domain.Video;
import com.zhibo.mainTain.dto.VideoDto;
import com.zhibo.mainTain.dto.QueryVideoDto;

public interface VideoService {

	BasePagerDto queryVideo(QueryVideoDto<Video> queryDto);

	void deleteVideo(String id, long userId) throws AppException;

	VideoDto getVideoById(String id);

	void saveVideo(VideoDto videoDto, long userId);

}

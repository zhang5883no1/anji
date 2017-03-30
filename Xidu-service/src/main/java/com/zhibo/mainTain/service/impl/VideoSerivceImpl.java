package com.zhibo.mainTain.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xidu.framework.common.dto.BasePagerDto;
import com.xidu.framework.common.exception.AppException;
import com.xidu.framework.common.service.impl.BaseServiceImpl;
import com.zhibo.common.exception.ExceptionConstant;
import com.zhibo.mainTain.dao.VideoDao;
import com.zhibo.mainTain.domain.Video;
import com.zhibo.mainTain.dto.VideoDto;
import com.zhibo.mainTain.dto.QueryVideoDto;
import com.zhibo.mainTain.service.VideoService;

@Transactional(rollbackFor = AppException.class)
@Service
public class VideoSerivceImpl extends BaseServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;

	@Override
	public BasePagerDto queryVideo(
			QueryVideoDto<Video> queryDto) {
		videoDao.queryVideo(queryDto);
		return queryDto;
	}

	@Override
	public void deleteVideo(String id, long userId) throws AppException {
		Video entity = videoDao.findById(Long.parseLong(id));
		if (entity == null) {
			throw new AppException(
					ExceptionConstant.ERROR_BASEINFO_SUPPLER_ENTITY_NOTEXIST,
					"Customer is not exist.");
		}
		// videoDao.remove(entity);
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		entity.setDeleteFlag(1L);
		videoDao.update(entity);

	}

	@Override
	public VideoDto getVideoById(String id) {
		VideoDto dto = new VideoDto();
		Video entity = videoDao.findById(Long
				.parseLong(id));
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public void saveVideo(VideoDto videoDto, long userId) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Video entity = null;
		if (videoDto.getId() != null) {// edit
			entity = videoDao.findById(videoDto.getId());
		} else {// save
			entity = new Video();
			entity.setCreateBy(userId);
			entity.setCreateDate(new Date());
		}
		entity.setLastUpdateBy(userId);
		entity.setLastUpdateDate(new Date());
		BeanUtils.copyProperties(videoDto, entity);
		videoDao.save(entity);
	}

}

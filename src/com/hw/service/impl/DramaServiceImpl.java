package com.hw.service.impl;

import java.util.List;

import com.hw.entity.Drama;
import com.hw.mapper.DramaMapper;
import com.hw.service.DramaService;

/**
 * 话剧service层的业务的实现类
 * 
 * @author Smile
 *
 */
public class DramaServiceImpl implements DramaService{
	
	//依赖注入
	DramaMapper dramaMapper;

	public DramaMapper getDramaMapper() {
		return dramaMapper;
	}

	public void setDramaMapper(DramaMapper dramaMapper) {
		this.dramaMapper = dramaMapper;
	}

	/**
	 * 无条件查询所有话剧信息业务的具体实现
	 */
	@Override
	public List<Drama> getAllDramas() {
		return dramaMapper.getAllDramas();
	}

	/**
	 * 添加一条剧目信息业务的具体实现
	 */
	@Override
	public int addOneDrama(Drama drama) {
		return dramaMapper.addOneDrama(drama);
	}

	/**
	 * 根据剧目id删除一条剧目信息业务的具体实现
	 */
	@Override
	public int deleteOneDrama(int dramaId) {
		return dramaMapper.deleteOneDrama(dramaId);
	}

}

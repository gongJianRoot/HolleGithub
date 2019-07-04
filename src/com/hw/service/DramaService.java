package com.hw.service;

import java.util.List;

import com.hw.entity.Drama;

/**
 * 话剧的service层业务接口类
 * @author Smile
 *
 */
public interface DramaService {

	/**
	 * 无条件查询所有话剧信息
	 * 
	 * @return List
	 */
	public List<Drama> getAllDramas();
	
	/**
	 * 添加一条剧目信息，参数为一个Drama对象
	 * @param drama
	 * @return int
	 */
	public int addOneDrama(Drama drama);
	
	/**
	 * 根据剧目id删除一条剧目信息
	 * 
	 * @param dramaId
	 * @return int
	 */
	public int deleteOneDrama(int dramaId);
}

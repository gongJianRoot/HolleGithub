package com.hw.mapper;

import java.util.List;

import com.hw.entity.Drama;

/**
 * ����mapper��ӿڣ���Ҫ�Ƕ����ݿ��л�����Ϣ�Ĳ�����
 * 
 * @author Smile
 *
 */
public interface DramaMapper {

	/**
	 * ��������ѯ���л�����Ϣ
	 * 
	 * @return List
	 */
	public List<Drama> getAllDramas();
	
	/**
	 * ���һ����Ŀ��Ϣ������Ϊһ��Drama����
	 * @param drama
	 * @return int
	 */
	public int addOneDrama(Drama drama);
	
	/**
	 * ���ݾ�Ŀidɾ��һ����Ŀ��Ϣ
	 * 
	 * @param dramaId
	 * @return int
	 */
	public int deleteOneDrama(int dramaId);
}

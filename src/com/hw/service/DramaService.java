package com.hw.service;

import java.util.List;

import com.hw.entity.Drama;

/**
 * �����service��ҵ��ӿ���
 * @author Smile
 *
 */
public interface DramaService {

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

package com.hw.service.impl;

import java.util.List;

import com.hw.entity.Drama;
import com.hw.mapper.DramaMapper;
import com.hw.service.DramaService;

/**
 * ����service���ҵ���ʵ����
 * 
 * @author Smile
 *
 */
public class DramaServiceImpl implements DramaService{
	
	//����ע��
	DramaMapper dramaMapper;

	public DramaMapper getDramaMapper() {
		return dramaMapper;
	}

	public void setDramaMapper(DramaMapper dramaMapper) {
		this.dramaMapper = dramaMapper;
	}

	/**
	 * ��������ѯ���л�����Ϣҵ��ľ���ʵ��
	 */
	@Override
	public List<Drama> getAllDramas() {
		return dramaMapper.getAllDramas();
	}

	/**
	 * ���һ����Ŀ��Ϣҵ��ľ���ʵ��
	 */
	@Override
	public int addOneDrama(Drama drama) {
		return dramaMapper.addOneDrama(drama);
	}

	/**
	 * ���ݾ�Ŀidɾ��һ����Ŀ��Ϣҵ��ľ���ʵ��
	 */
	@Override
	public int deleteOneDrama(int dramaId) {
		return dramaMapper.deleteOneDrama(dramaId);
	}

}

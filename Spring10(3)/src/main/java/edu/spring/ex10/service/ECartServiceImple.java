package edu.spring.ex10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex10.domain.ECartVO;
import edu.spring.ex10.persistence.ECartDAO;

@Service
public class ECartServiceImple implements ECartService {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ECartServiceImple.class);
	
	@Autowired
	private ECartDAO dao;
	
	@Override
	// ���� �߰�
	public int create(ECartVO vo) {
		logger.info("---------create()ȣ�� : vo= "+vo.toString()+"---------");
		return dao.insert(vo);
	}

	@Override
	// ���� ���
	public List<ECartVO> readECart(String userId) {
		logger.info("---------readECart()ȣ�� ---------");
		return dao.listECart(userId);
	}

	@Override
	//���� ����
	public int delete(int ecartId, String userId) {
		logger.info("---------delete()ȣ�� ---------");
		return dao.delete(ecartId, userId);
	}

	@Override
	//���� ���� Ȯ��
	public int countECart(int eventId, String userId) {
		logger.info("---------countECart()ȣ�� ---------");
		return dao.countECart(eventId, userId);
	}

}

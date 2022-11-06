package edu.spring.ex10.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex10.domain.ECartVO;

@Repository
public class ECartDAOImple implements ECartDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ECartDAOImple.class);
	private static final String NAMESPACE =
			"edu.spring.ex10.ECartMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	// ���� �߰�
	public int insert(ECartVO vo) {
		logger.info("----------insert()ȣ�� : vo= "+vo.toString()+"----------");
		return sqlSession.insert(NAMESPACE+ ".insert", vo);
	}

	@Override
	// ���� ���
	public List<ECartVO> listECart(String userId) {
		logger.info("----------listCart()ȣ�� ----------");
		userId= "1";
		return sqlSession.selectList(NAMESPACE+ ".listECart", userId);		
	}

	@Override
	// ���� ����
	public int delete(int ecartId) {
		logger.info("----------delete()ȣ�� ----------");
		return sqlSession.delete(NAMESPACE+ ".delete", ecartId);
	}

	@Override
	// ���� ���� Ȯ��
	public int countECart(int eventId, String userId) {
		logger.info("----------countCart()ȣ�� ----------");
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("eventId", eventId);
		map.put("userId", userId);
		return sqlSession.selectOne(NAMESPACE+ ".countECart", map);
	}

}

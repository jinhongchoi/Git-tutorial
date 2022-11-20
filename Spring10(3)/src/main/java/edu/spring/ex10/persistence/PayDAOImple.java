package edu.spring.ex10.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.domain.ECartVO;
import edu.spring.ex10.domain.PayDetailVO;
import edu.spring.ex10.domain.PayProductVO;
import edu.spring.ex10.domain.PayVO;
import edu.spring.ex10.domain.ProductVO;

@Repository
public class PayDAOImple implements PayDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(PayDAOImple.class);
	private static final String NAMESPACE =
			"edu.spring.ex10.PayMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	// ���� �ֹ� ����
	@Override
	public int insert(PayVO vo) {
		logger.info("----------insert()ȣ�� : vo= "+vo.toString()+"----------");
		return sqlSession.insert(NAMESPACE+ ".insert", vo);
	}
	
	@Override
	public int insert2(PayDetailVO vo) {
		logger.info("----------insert2()ȣ�� : vo= "+vo.toString()+"----------");
		return sqlSession.insert(NAMESPACE+ ".insertDetail", vo);
	}
	

	@Override
	public List<PayVO> listPay(String userId) {
		logger.info("----------listPay()ȣ�� ----------");
		userId= "1";
		return sqlSession.selectList(NAMESPACE+ ".listPay", userId);		
	}
	// ��ٱ��� ���� ���
	@Override
	public List<PayDetailVO> listPayDetail(PayDetailVO vo) {
		// TODO Auto-generated method stub
		logger.info("----------listPayDetail()ȣ�� ----------");
		
		return sqlSession.selectList(NAMESPACE+ ".lisePayDetail", vo);
	}
	
	// ���� ���
	@Override
	public List<ECartVO> listECart(String userId) {
		logger.info("----------listCart()ȣ�� ----------");
		userId= "1";
		return sqlSession.selectList(NAMESPACE+ ".listECart", userId);
	}

	@Override
	public int sumMoney(String userId) {
		logger.info("----------sumMoney()ȣ�� ----------");
		
		return sqlSession.selectOne(NAMESPACE+ ".sumMoney", userId);
	}


	@Override
	public List<CartVO> listCart(String userId) {
		logger.info("----------listCart()ȣ�� ----------");
		userId= "1";
		return sqlSession.selectList(NAMESPACE+ ".listCart", userId);		
	}


	@Override
	public ProductVO detailProduct(int productId) {
		logger.info("--------detailProduct()ȣ��--------"); 
		logger.info(String.valueOf(productId));
		return sqlSession.selectOne(NAMESPACE + ".detailProduct", productId);
	}//end detail


	@Override
	public int sumMoney2(int productId) {
		logger.info("----------sumMoney()ȣ�� ----------");
		
		return sqlSession.selectOne(NAMESPACE+ ".sumMoney2", productId);
	}
	
	// ��ٱ��� ����
	@Override
	public int delete(String userId) {
		logger.info("----------delete()ȣ�� ----------");
		userId= "1";
		return sqlSession.delete(NAMESPACE+ ".cartAllDelete", userId);
	}

	



}

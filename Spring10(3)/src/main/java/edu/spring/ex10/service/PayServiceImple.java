package edu.spring.ex10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.domain.PayDetailVO;
import edu.spring.ex10.domain.PayProductVO;
import edu.spring.ex10.domain.PayVO;
import edu.spring.ex10.domain.ProductVO;
import edu.spring.ex10.persistence.PayDAO;

@Service
public class PayServiceImple implements PayService {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductServiceImple.class);
	
	@Autowired
	private PayDAO dao;

	@Override
	// ���� ���
	public int create(PayVO vo) {
		logger.info("---------create()ȣ�� ---------");
		return dao.insert(vo);
	}//end create
	
	//��ٱ��� ���� ���
	@Override
	public int create2(PayDetailVO vo) {
		logger.info("---------create2()ȣ�� ---------");
		return dao.insert2(vo);
	}//end create2

	@Override
	// ���� ���
	public List<PayVO> listPay(String userId) {
		 logger.info("---------List()ȣ�� ---------");
		return dao.listPay(userId);
	}//end list
	
	//��ٱ��� ���� ���
	@Override
	public List<PayDetailVO> listPayDetail(PayDetailVO vo) {
		 logger.info("---------listPayDetail()ȣ�� ---------");
		return dao.listPayDetail(vo);
	}

	
	@Override
	// ��ǰ ��� �ҷ�����
	public List<CartVO> readCart(String userId) {
		logger.info("---------readCart()ȣ�� ---------");
		return dao.listCart(userId);
	}

	@Override
	public int sumMoney(String userId) {
		logger.info("---------sumMoney()ȣ�� ---------");
		return dao.sumMoney(userId);
	}

	@Override
	public ProductVO detailProduct(int productId) {
		logger.info("---------detail()ȣ�� ---------");
		return dao.detailProduct(productId);
	}//end detail

	@Override
	public int sumMoney2(int productId) {
		logger.info("---------sumMoney2()ȣ�� ---------");
		return dao.sumMoney2(productId);
	}

	@Override
	public int cartAllDelete(String userId) {
		logger.info("---------cartAllDelete()ȣ�� ---------");
		return dao.delete(userId);
	}




}

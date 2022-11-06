package edu.spring.ex10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex10.domain.CartVO;
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

	@Override
	// ���� ���
	public List<PayVO> listPay(String userId) {
		// logger.info("---------List()ȣ�� ---------");
		return dao.listPay(userId);
	}//end list

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

}

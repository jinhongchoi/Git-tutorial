package edu.spring.ex10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.persistence.CartDAO;

@Service
public class CartServiceImple implements CartService {
	
	private static final Logger logger=
			LoggerFactory.getLogger(CartServiceImple.class);
	
	@Autowired
	private CartDAO dao;

	@Override
	// ��ٱ��� �߰�
	public int create(CartVO vo) { 
		logger.info("---------create()ȣ�� : vo= "+vo.toString()+"---------");
		return dao.insert(vo);
	}

	@Override
	// ��ٱ��� ���
	public List<CartVO> readCart(String userId) {
		logger.info("---------readCart()ȣ�� ---------");
		return dao.listCart(userId);
	}

	@Override
	// ��ٱ��� ����
	public int delete(int cartId) {
		logger.info("---------delete()ȣ�� ---------");
		return dao.delete(cartId);
	}

	@Override
	// ��ٱ��� ����
	public int update(CartVO vo) {
		logger.info("---------update()ȣ�� ---------");
		return dao.update(vo);
	}

	@Override
	// ��ٱ��� �ݾ� �հ�
	public int sumMoney(String userId) {
		logger.info("---------sumMoney()ȣ�� ---------");
		return dao.sumMoney(userId);
	}

	@Override
	// ��ٱ��� ��ǰ Ȯ��
	public int countCart(int productId, String userId) {
		logger.info("---------countCart()ȣ�� ---------");
		return dao.countCart(productId, userId);
	}

	@Override
	// ��ٱ��� ��ǰ ���� ����
	public int updateCart(CartVO vo) {
		logger.info("---------updateCart()ȣ�� ---------");
		return dao.updateCart(vo);
	}

}









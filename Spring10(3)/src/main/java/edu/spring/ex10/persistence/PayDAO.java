package edu.spring.ex10.persistence;

import java.util.List;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.domain.PayVO;
import edu.spring.ex10.domain.ProductVO;

public interface PayDAO {
	
	int insert(PayVO vo);
	
	List<PayVO>listPay(String userId);
	
	// ��ٱ��� ��� �ҷ�����
	List<CartVO>listCart(String userId);
	
	// ��ǰ ���� �ҷ�����
	ProductVO detailProduct(int productId);
	
	int sumMoney(String userId);
	
}

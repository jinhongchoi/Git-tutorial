package edu.spring.ex10.service;

import java.util.List;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.domain.ECartVO;
import edu.spring.ex10.domain.PayDetailVO;
import edu.spring.ex10.domain.PayProductVO;
import edu.spring.ex10.domain.PayVO;
import edu.spring.ex10.domain.ProductVO;

public interface PayService {
	
	// ���� ���� �ֹ� �ֱ�
	int create(PayVO vo);
	
	// ��ٱ��� �ֹ� �ֱ�
	int create2(PayDetailVO vo);
	
	List<PayVO>listPay(String userId);
	
	// �������	
	List<ECartVO>readECart(String userId);
	
	//��ٱ��� ���� ��� �ҷ�����
	List<PayDetailVO>listPayDetail(PayDetailVO vo);
	
	List<CartVO>readCart(String userId);
	
	// ��ǰ ����
	ProductVO detailProduct(int productId);
	
	int sumMoney(String userId);
	
	int sumMoney2(int productId);
	
	int cartAllDelete(String userId);
	
}

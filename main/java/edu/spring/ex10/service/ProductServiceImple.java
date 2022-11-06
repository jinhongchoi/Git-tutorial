package edu.spring.ex10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.spring.ex10.domain.ProductVO;
import edu.spring.ex10.pageutil.PageCriteria;
import edu.spring.ex10.persistence.ProductDAO;

@Service
public class ProductServiceImple implements ProductService {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductServiceImple.class);
	
	@Autowired
	private ProductDAO dao;
	
	@Override
	public int create(ProductVO vo) {
		logger.info("---------create()ȣ�� ---------");
		return dao.insert(vo);
	}//end create

	@Override
	public List<ProductVO>listProduct(String productCate) {
		logger.info("---------List()ȣ�� ---------");
		return dao.listProduct(productCate);
	}//end listRead

	@Override
	public ProductVO detailProduct(int productId) {
		logger.info("---------detail()ȣ�� ---------");
		return dao.detailProduct(productId);
	}//end detail

	@Override
	public int update(ProductVO vo) {
		logger.info("---------update()ȣ�� : vo= "+vo.toString()+"---------");
		return dao.update(vo);
	}

	@Override
	public int delete(int productId) {
		logger.info("---------delete()ȣ�� ---------");
		return dao.delete(productId);
	}

	@Override
	public List<ProductVO> read(PageCriteria criteria) {
		logger.info("---------read()ȣ�� ---------");
		logger.info("start = "+criteria.getStart());
		logger.info("end = "+ criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public int getTotalCount() {
		logger.info("--------getTotalCounts()ȣ�� --------");
		return dao.getTotalCount();
	}


}

package edu.spring.ex10.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.ex10.domain.EventVO;
import edu.spring.ex10.pageutil.PageCriteria;
import edu.spring.ex10.persistence.EventDAO;

@Service
public class EventServiceImple implements EventService {
	
	private static final Logger logger=
			LoggerFactory.getLogger(EventServiceImple.class);
	
	@Autowired
	private EventDAO dao;	
	
	@Override
	public int create(EventVO vo) {
		logger.info("---------create()ȣ�� ---------");
		return dao.insert(vo);
	}//end create

	@Override
	public List<EventVO> read(PageCriteria criteria) {
		logger.info("---------read()ȣ�� ---------");
		logger.info("start = "+criteria.getStart());
		logger.info("end = "+ criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public List<EventVO> listEvent() {
		logger.info("---------List()ȣ�� ---------");
		return dao.listEvent();
	}//end listRead

	@Override
	public EventVO detailEvent(int eventId) {
		logger.info("---------detail()ȣ�� ---------");
		return dao.detailEvent(eventId);
	}//end detail

	@Override
	public int update(EventVO vo) {
		logger.info("---------update()ȣ�� : vo= "+vo.toString()+"---------");
		return dao.update(vo);
	}//end update

	@Override
	public int delete(int eventId) {
		logger.info("---------delete()ȣ�� ---------");
		return dao.delete(eventId);
	}//end delete

	@Override
	public int getTotalCount() {
		logger.info("--------getTotalCounts()ȣ�� --------");
		return dao.getTotalCount();
	}//ent getTotalCount

}

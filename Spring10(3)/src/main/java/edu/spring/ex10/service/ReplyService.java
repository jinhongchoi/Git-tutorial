package edu.spring.ex10.service;

import java.util.List;

import edu.spring.ex10.domain.ReplyVO;

public interface ReplyService {
	
	int create(ReplyVO vo) throws Exception;
	
	List<ReplyVO> read(int boardId);
	
	int update(ReplyVO vo);
	
	int delete(int replyId, int boardId) throws Exception;
	// service �ܿ��� �Ű������� ������ ����� controller �ܱ��� ������ �ޱ⶧���� ������ ����� �Ѵ�! 
}

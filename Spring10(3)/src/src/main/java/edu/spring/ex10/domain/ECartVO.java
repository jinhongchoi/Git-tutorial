package edu.spring.ex10.domain;

public class ECartVO {
	
	private int ecartId; // ��ٱ��� ��ȣ 
	private String userId; // ����� ���̵�
	private String userName; // ����� �̸�
	private int eventId; // ��ǰ ��ȣ
	private String eventName; // ��ǰ �̸�
	private int eventPrice; // ��ǰ �ܰ�	
	private String eventUrl; // ��ǰ�̹���
	
	public ECartVO() {
		// TODO Auto-generated constructor stub
	}

	public ECartVO(int ecartId, String userId, String userName, int eventId, String eventName, int eventPrice,
			String eventUrl) {
		super();
		this.ecartId = ecartId;
		this.userId = userId;
		this.userName = userName;
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventPrice = eventPrice;
		this.eventUrl = eventUrl;
	}

	public int getEcartId() {
		return ecartId;
	}

	public void setEcartId(int ecartId) {
		this.ecartId = ecartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(int eventPrice) {
		this.eventPrice = eventPrice;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	@Override
	public String toString() {
		return "ECartVO [ecartId=" + ecartId + ", userId=" + userId + ", userName=" + userName + ", eventId=" + eventId
				+ ", eventName=" + eventName + ", eventPrice=" + eventPrice + ", eventUrl=" + eventUrl + "]";
	}
	
	
	
}

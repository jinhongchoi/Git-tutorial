package edu.spring.ex10.domain;

public class PayVO {
	
	private int payId; // ��ٱ��� ��ȣ 
	private String userId; // ����� ���̵�
	private String userName; // ����� �̸�
	private String userTell; // ����� ����ó
	private int productId; // ��ǰ ��ȣ
	private String productName; // ��ǰ �̸�
	private int productPrice; // ��ǰ �ܰ�
	private int amount; // ���� ����
	private int payPrice; // ��ǰ ����
	private int eventId; // �̺�Ʈ ��ȣ
	private String eventName; // �̺�Ʈ �̸�
	private int eventPrice; // �̺�Ʈ �ܰ�
	private String productUrl; // ��ǰ�̹���
	
	public PayVO() {
		// TODO Auto-generated constructor stub
	}

	public PayVO(int payId, String userId, String userName, String userTell, int productId, String productName,
			int productPrice, int amount, int payPrice, int eventId, String eventName, int eventPrice,
			String productUrl) {
		super();
		this.payId = payId;
		this.userId = userId;
		this.userName = userName;
		this.userTell = userTell;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.amount = amount;
		this.payPrice = payPrice;
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventPrice = eventPrice;
		this.productUrl = productUrl;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
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

	public String getUserTell() {
		return userTell;
	}

	public void setUserTell(String userTell) {
		this.userTell = userTell;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
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

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	@Override
	public String toString() {
		return "PayVO [payId=" + payId + ", userId=" + userId + ", userName=" + userName + ", userTell=" + userTell
				+ ", productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", amount=" + amount + ", payPrice=" + payPrice + ", eventId=" + eventId + ", eventName=" + eventName
				+ ", eventPrice=" + eventPrice + ", productUrl=" + productUrl + "]";
	}
	
	
	
}

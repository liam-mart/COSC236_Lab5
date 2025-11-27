package lab5;

public class BorrowingBookResult {
	
	private boolean isSuccess;
	private String borrowingMessage;
		
	public void isSuccess(boolean b) {
		if (b) {
			isSuccess = true;
		} else {
			isSuccess = false;
		}
	}
	
	public boolean getIsSuccess() {
		return this.isSuccess;
	}
	
	public void setBorrowingMessage(String message) {
		this.borrowingMessage = message;
	}
	
	public String getBorrowingMessage() {
		return this.borrowingMessage;
	}
}

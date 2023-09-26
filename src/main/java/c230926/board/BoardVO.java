package c230926.board;

public class BoardVO {
	private int id;
	private String name;
	private String subject;
	private String text;
	private String date;
		
	public BoardVO(String name, String subject, String text) {
		this.name = name;
		this.subject = subject;
		this.text = text;
	}
	public BoardVO(int id, String name, String subject, String text, String date) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.text = text;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSubject() {
		return subject;
	}
	public String getText() {
		return text;
	}
	public String getDate() {
		return date;
	}

	
}

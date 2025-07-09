public class Todos {
	private String title;
	private Boolean completed;

	public Todos(String title, Boolean completed) {
		this.title = title;
		this.completed = completed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Todos{" +
				"title='" + title + '\'' +
				", completed=" + completed +
				'}';
	}
}

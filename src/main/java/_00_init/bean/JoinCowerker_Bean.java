package _00_init.bean;

public class JoinCowerker_Bean {
	int id;
	String content, time, locate, describe, salary, name, welfare;
	
	public JoinCowerker_Bean() {
		super();
	}
	public JoinCowerker_Bean(String name) {
		super();
		this.name = name;
	}
	public JoinCowerker_Bean(String content, String time, String locate, String describe, String salary, String name,
			String welfare) {
		super();
		this.content = content;
		this.time = time;
		this.locate = locate;
		this.describe = describe;
		this.salary = salary;
		this.name = name;
		this.welfare = welfare;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocate() {
		return locate;
	}
	public void setLocate(String locate) {
		this.locate = locate;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	public String getResult() {
		return name+","+content+","+time+","+locate+","+describe+","+salary+","+welfare;
	}
	@Override
	public String toString() {
		return "JoinCowerker_DAO [id=" + id + ", content=" + content + ", time=" + time + ", locate=" + locate
				+ ", describe=" + describe + ", salary=" + salary + ", name=" + name + ", welfare=" + welfare + "]";
	}
	
}

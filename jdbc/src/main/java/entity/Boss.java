package entity;

public class Boss implements Entity {
	
	private Integer id;
	private String name;
	private Integer salary;
	private Boss boss;
	
	public Boss() {}
	
	public Boss(String name, Integer salary) {
		this.name = name;
		this.salary = salary;
	}
	
	public Boss(String name, Integer salary, Boss boss) {
		this.name = name;
		this.salary = salary;
		this.boss = boss;
	}
	
	public Boss(Integer id, String name, Integer salary, Boss boss) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.boss = boss;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	@Override
	public String toString() {
		return "Boss [id=" + id + ", name=" + name + ", salary=" + salary + ", boss=" + boss + "]";
	}
	

}

package entity;

public class Grunt implements Entity {

	private Integer id;
	private String name;
	private Integer salary;
	private Boss boss;
	
	public Grunt() {}

	public Grunt(String name, Integer salary, Boss boss) {
		super();
		this.name = name;
		this.salary = salary;
		this.boss = boss;
	}

	public Grunt(Integer id, String name, Integer salary, Boss boss) {
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
	
}

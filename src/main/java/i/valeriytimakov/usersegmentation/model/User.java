package i.valeriytimakov.usersegmentation.model;

import static org.hamcrest.CoreMatchers.instanceOf;

public class User {

	private final String id;

	public User(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (id == null || !(obj instanceof  User)) {
			return false;
		}
		return id.equals(((User)obj).id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "{" + id + "}";
	}	
	
}

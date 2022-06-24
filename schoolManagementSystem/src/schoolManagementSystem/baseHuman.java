package schoolManagementSystem;

public abstract class baseHuman {
	
	String name = null;
	String surname = null;
	String gender = null;
	String id = null;
	String password = null;
	public abstract boolean enter();
	public abstract void listNotes();
}

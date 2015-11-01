public class InputSetting {
	public final int NONE = 0;
	public final int ADD = 1;
	public final int UPDATE = 2;
	public final int DELETE = 3;
	public final int VIEW = 4;
	String select;

	public void setEditable(int n) {
		StudentInfoSystem.input_id.setEditable(false);
		StudentInfoSystem.input_name.setEditable(false);
		StudentInfoSystem.input_depart.setEditable(false);
		StudentInfoSystem.input_pnum.setEditable(false);

		switch (n) {

		case ADD:
			StudentInfoSystem.input_id.setEditable(true);
			StudentInfoSystem.input_name.setEditable(true);
			StudentInfoSystem.input_depart.setEditable(true);
			StudentInfoSystem.input_pnum.setEditable(true);
			break;

		case UPDATE:
			StudentInfoSystem.input_id.setEditable(true);
			StudentInfoSystem.input_pnum.setEditable(true);
			break;

		case DELETE:
			StudentInfoSystem.input_id.setEditable(true);
			break;

		case VIEW:
			StudentInfoSystem.input_id.setEditable(true);
			break;
		}
	}

}

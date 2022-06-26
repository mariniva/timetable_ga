package Model;

public class TimeslotsModel {
	private final int timeslotId;
	private final int timeslotShift;
	private final String timeslot;

	public TimeslotsModel(int timeslotId, int timeslotShift, String timeslot) {
		this.timeslotId = timeslotId;
		this.timeslotShift = timeslotShift;
		this.timeslot = timeslot;
	}

	public int getTimeslotId() {
		return this.timeslotId;
	}
	
	public int getTimeslotShift() {
		return this.timeslotShift;
	}

	public String getTimeslot() {
		return this.timeslot;
	}
}

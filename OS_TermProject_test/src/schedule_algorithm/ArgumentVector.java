package schedule_algorithm;

public class ArgumentVector {
	int ArrivalTime;
	int RunningTime;
	String PID;
	
	public void FixArgument(int ArrivalTime, int RunningTime, String PID) {
		this.ArrivalTime = ArrivalTime;
		this.RunningTime = RunningTime;
		this.PID = PID;
	}
}

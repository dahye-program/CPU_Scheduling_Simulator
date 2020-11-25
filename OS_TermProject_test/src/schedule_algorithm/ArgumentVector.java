package schedule_algorithm;

public class ArgumentVector {
	int StartTime;
	int RunningTime;
	String PID;
	
	public void FixArgument(int StartTime, int RunningTime, String PID) {
		this.StartTime = StartTime;
		this.RunningTime = RunningTime;
		this.PID = PID;
	}
	
	void ReSet(int StartTime, int RunningTime, String PID) {
		this.StartTime = StartTime;
		this.RunningTime = RunningTime;
		this.PID = PID;
	}
	
	int ReturnArrivalTime() {
		return StartTime;
	}
	
	int ReturnRunningTime() {
		return RunningTime;
	}
	
	String ReturnPID() {
		return PID;
	}
}
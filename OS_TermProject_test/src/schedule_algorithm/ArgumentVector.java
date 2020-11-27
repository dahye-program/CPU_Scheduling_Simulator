package schedule_algorithm;

import java.awt.Color;
import java.util.Random;

public class ArgumentVector {
	int ArrivalTime;
	int RunningTime;
	String PID;
	int Priority;
	int WaitTime;
	int ReturnTime;
	int ResponTime;
	public Color color;
	
	public ArgumentVector(int ArrivalTime, int RunningTime, String PID, int Priority, Color color) {
		this.ArrivalTime = ArrivalTime;
		this.RunningTime = RunningTime;
		this.PID = PID;
		this.Priority = Priority;
		
		
//		Random rand = new Random();
//		float r = rand.nextFloat() / 2f + (float)0.5;
//		float g = rand.nextFloat() / 2f + (float)0.5;
//		float b = rand.nextFloat() / 2f + (float)0.5;
//		color = new Color(r, g, b);
		this.color = color;
	}
	
	public void ReSet(int StartTime, int RunningTime, String PID, int Priority) {
		this.ArrivalTime = StartTime;
		this.RunningTime = RunningTime;
		this.PID = PID;
		this.Priority = Priority;
	}
	
	void SetRunningTime(int RunningTime) {
		this.RunningTime = RunningTime;
	}
	
	int ReturnArrivalTime() {
		return ArrivalTime;
	}
	
	int ReturnRunningTime() {
		return RunningTime;
	}
	
	String ReturnPID() {
		return PID;
	}
	
	int ReturnPriority() {
		return Priority;
	}
	
	Color ReturnColor() {
		return color;
	}
	
	public ArgumentVector clone() {
		return new ArgumentVector(this.ArrivalTime, this.RunningTime, this.PID, this.Priority, this.color);
	}
}
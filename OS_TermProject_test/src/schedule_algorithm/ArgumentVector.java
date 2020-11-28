package schedule_algorithm;

import java.awt.Color;
import java.util.Random;

public class ArgumentVector {
	int ArrivalTime;
	int RunningTime;
	String PID;
	int Priority;
	double WaitingTime = 0;
	double ReturnTime;
	int RespawnTime;
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
	
	void SetWaitingTime(double WaitingTime) {
		this.WaitingTime += WaitingTime;
	}
	
	void SetRespawnTime (int SpawnTime) {
		this.RespawnTime = SpawnTime;
	}
	
	void SetReturnTime (double ReturnTime) {
		this.ReturnTime = ReturnTime;
	}
	
	void AddWaitingTime (double WaitingTime) {
		this.WaitingTime += WaitingTime;
	}
	
	int GetRunningTime() {
		return RunningTime;
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
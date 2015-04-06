package generator.util;

import java.io.Serializable;
import java.util.ArrayList;

public class BatchLocation implements Serializable{
	private static final long serialVersionUID = 7995078902089733230L;
	private int x;
	private ArrayList<Integer> yList;
	private int z;

	public BatchLocation(int x, ArrayList<Integer> yList, int z) {
		this.x = x;
		this.z = z;
		this.yList = yList;
	}

	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	public ArrayList<Integer> getYList() {
		return yList;
	}
}

package generator.util;

import generator.GeneratorMain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BatchQueue extends Vector <BatchLocation> implements Serializable {
	private static final long serialVersionUID = 7995078902089733230L;
	private int batch;

	public BatchQueue(int batch) {
		super();
		this.batch = batch;
	}

	public void sendQueue(){
		if(super.size() == 0 || batch < 0)
			return;
		try {
			File file = new File("locations");
			if(!file.exists()){
				file.mkdirs();
			}
			file = new File("locations/location" + batch + ".dat");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream("locations/location" + batch + ".dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.close();
			super.clear();
			batch--;
		}
		catch (Exception exception) { 
			exception.printStackTrace(); 
			return;
		}
	}

	public void add(int x, int z) {
		Location location = GeneratorMain.getWorld().getBlockAt(x, 100, z).getLocation();
		ArrayList<Integer> yList = new ArrayList<Integer>();

		for(int a = 0; a < 50; a++){
			Block block = location.getWorld().getBlockAt(location);
			location.add(0, -1, 0);
			if(!block.getType().equals(Material.AIR) && !MathUtil.isSurrounded(block)){
				yList.add(block.getY());
			}
		}
		addElement(new BatchLocation(x, yList, z));
	}
}

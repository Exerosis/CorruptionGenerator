package generator;

import generator.util.BatchLocation;
import generator.util.BatchQueue;
import generator.util.MathUtil;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Generator {

	private int size;
	private double layersPerSecond;
	private World world;
	private int generator;
	private int x = GeneratorMain.getData().getMapSizeX()/2;
	int blocksPerTick;

	public Generator(double secondsToCenter) {
		size = GeneratorMain.getData().getMapSizeX();
		world = GeneratorMain.getWorld();
		layersPerSecond = secondsToCenter;
	}

	public void generate(){	
		generator = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(GeneratorMain.getPlugin(), new Runnable(){
			BatchQueue queue = getQueue();

			public void run() {
				if(queue.size() == 0){
					if(x == 0){	
						Bukkit.getServer().getScheduler().cancelTask(generator);
						Bukkit.broadcastMessage("Done generating!");
					}
					queue = getQueue();
					if(queue == null){
						Bukkit.getServer().getScheduler().cancelTask(generator);
					}
					x--;
				}
				if(queue != null){
					blocksPerTick = (int) ((queue.size())/layersPerSecond*200);
					
					for(int y = 0; y < blocksPerTick; y++){
						if(queue.size()-1 >= 0){

							int random = (int)MathUtil.randomDouble(0, queue.size()-1);
							BatchLocation batchLocation = queue.get(random);
							queue.remove(random);

							for(int yValue: batchLocation.getYList()){
								Block block = world.getBlockAt(batchLocation.getX(), yValue, batchLocation.getZ());
								if(block.getType() != Material.NETHERRACK && block.getType() != Material.SOUL_SAND && !GeneratorMain.getData().getReplacements().containsValue(block.getType())){
									block.getChunk().getBlock(block.getX(), block.getY(), block.getZ()).setType(getMaterial(block.getType()));
								}
							}
							
						}
					}
				}
			}

		}, (long) 0, (long) 0.1);
	}

	private BatchQueue getQueue(){
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("locations/location" + x + ".dat");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			BatchQueue theQueue = (BatchQueue) objectInputStream.readObject();
			objectInputStream.close();
			return theQueue;
		} catch (Exception e){}
		return null;
	}

	private Material getMaterial(Material material){
		Material returnMaterial = Material.NETHERRACK;

		if(GeneratorMain.getData().getReplacements().containsKey(material)){		
			returnMaterial = GeneratorMain.getData().getReplacements().get(material);
		}		
		else if(MathUtil.percent(25)){
			returnMaterial = Material.SOUL_SAND;
		}
		return returnMaterial;
	}

	public void setSecondsToCenter(int seconds){
		layersPerSecond = size/seconds;
	}
}
/*
	private boolean isInRange(final Player player){
		for(ArrayList<ArrayList<Location>> list: batches){
			for(ArrayList<Location> listY: list){
				for(Location location: listY){
					if(location.distance(player.getLocation()) <= 4){
						return true;
					}
				}	
			}	
		}	
		return false;
	}

	if(MathUtil.percent(1) && layer == 0){
		Bukkit.broadcastMessage("Sonar, Con; Con aye!");
		Location tendrilStem = block.getLocation();
		Tendril tendril = new Tendril(tendrilStem, MathUtil.getDirectionTowardCenter(tendrilStem, red.getCenterPoint()));
		tendril.createLine();
	}
 */
package generator.util;

import java.util.HashMap;

import org.bukkit.Material;

public class CorruptionData {
	final public HashMap<Material, Material> replacements = new HashMap<Material, Material>();
	private int mapSizeX;
	private int mapSizeY;
	//private Location mapCenter;
	public CorruptionData() {
		mapSizeX = 250;
		mapSizeY = 250;
		
		replacements.put(Material.LOG, Material.COAL_BLOCK);
		replacements.put(Material.LOG_2, Material.COAL_BLOCK);
		replacements.put(Material.LEAVES, Material.GLOWSTONE);
		replacements.put(Material.SAND, Material.GRAVEL);
		replacements.put(Material.LONG_GRASS, Material.AIR);
		replacements.put(Material.SNOW, Material.AIR);
		replacements.put(Material.VINE, Material.AIR);
		replacements.put(Material.YELLOW_FLOWER, Material.AIR);
	}
	public HashMap<Material, Material> getReplacements(){
		return replacements;
	}
	public int getMapSizeX() {
		return mapSizeX;
	}
	public int getMapSizeY() {
		return mapSizeY;
	}
}

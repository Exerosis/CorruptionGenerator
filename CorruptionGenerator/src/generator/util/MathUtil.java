package generator.util;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class MathUtil { 
	private static Random randomInstance = new Random();
	
	public static double randomDouble(double min, double max){
		return randomInstance.nextDouble() * (max - min) + min;
	}
	
	public static int random(int min, int max){
		return (int)randomDouble(min, max);
	}
	
	public static Random getRandom(){
		return randomInstance;
	}
	
	public static boolean isSurrounded(Block block){
		BlockFace[] blockFaces = new BlockFace[]{ BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.DOWN, BlockFace.UP};
		for(BlockFace face: blockFaces){
			if(block.getRelative(face).getType().isTransparent()){
				return false;
			}
		}
		return true;
	}
	
	public static boolean percent(int chance){
		if(chance < 100){
			int random = (int)(randomInstance.nextDouble() * 100);
			if(random <= chance)
				return true;
			return false;
		}
		else{
			return false;
		}
	}

	public static BlockFace getDirectionTowardCenter(Location point, Location center){
		return getDirection(point.setDirection(point.toVector().subtract(center.toVector())));    
	}

	public static BlockFace getDirection(Location location){
		float angle = location.getYaw();
		while (angle < 0)
			angle += 360;
		while (angle > 360)
			angle -= 360;

		BlockFace direction = BlockFace.SOUTH;
		if (angle <= 45 || angle > 315)
		{
			direction = BlockFace.NORTH;
		}
		else if (angle > 45 && angle <= 135)
		{
			direction = BlockFace.EAST;
		}
		else if (angle > 135 && angle <= 225)
		{
			direction = BlockFace.SOUTH;
		}
		else if (angle > 225 && angle <= 315)
		{
			direction = BlockFace.WEST;
		}

		return direction;
	}
}

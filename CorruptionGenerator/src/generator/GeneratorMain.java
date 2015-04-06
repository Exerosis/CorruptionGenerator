package generator;


import generator.util.CorruptionData;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GeneratorMain extends JavaPlugin implements Listener {
	public static JavaPlugin plugin;
	public static Generator generator;
	private static CorruptionData data;
	private static World world;

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		plugin = this;
		data = new CorruptionData();
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event){	
		world = event.getPlayer().getWorld();
		if(event.getPlayer().getItemInHand().getType() == Material.CARROT_ITEM){
			Bukkit.broadcastMessage("Generating!");
			generator = new Generator(100);
			generator.generate();
		}
	}
	
	public static World getWorld() {
		return world;
	}
	public static CorruptionData getData() {
		return data;
	}
	public static JavaPlugin getPlugin(){
		return plugin;
	}
}
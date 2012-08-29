package org.digiplex.bukkitplugin.commander.ev;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.digiplex.bukkitplugin.commander.api.BadEVPathException;
import org.digiplex.bukkitplugin.commander.api.CmdrEnvVarModule;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class WorldGuardEVM implements CmdrEnvVarModule {
	private WorldGuardPlugin plugin;
	
	public WorldGuardEVM() {
		plugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		if (plugin == null) throw new NullPointerException("Could not get World Guard plugin!");
	}

	@Override public String getNamespace() { return "WorldGuard"; }

	@Override public Object getEVValue(String varname, CommandSender sender) throws BadEVPathException {
		String[] args = varname.split("\\s+");
		String[] varpath = args[0].split("\\.");
		
		if (varpath.length < 1) throw new BadEVPathException("Path too short!");
		
		Player p = null; //player instance, if needed
		if (sender instanceof Player) p = (Player) sender;
		
		if (varpath[0].matches("can-?build")) return plugin.canBuild(p, p.getLocation());
			
		if (varpath[0].matches("region")) {
			if (varpath.length < 3) throw new BadEVPathException("Path too short!");
			String regionname = varpath[1];
			ProtectedRegion r = plugin.getGlobalRegionManager().get(p.getWorld()).getRegion(regionname);
			
			
		}
		
		return null;
	}

}

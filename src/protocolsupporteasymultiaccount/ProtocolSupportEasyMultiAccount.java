package protocolsupporteasymultiaccount;

import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import protocolsupport.api.events.PlayerProfileCompleteEvent;
import protocolsupport.api.utils.Profile;

public class ProtocolSupportEasyMultiAccount extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	protected final Pattern validUsername = Pattern.compile("^\\w{3,16}$");

	@EventHandler
	protected void onProfileComplete(PlayerProfileCompleteEvent event) {
		String[] hostnameSplit = event.getConnection().getVirtualHost().getHostString().split("\\.");
		if (hostnameSplit.length >= 3 && hostnameSplit[1].equalsIgnoreCase("multiaccount")) {
			String username = hostnameSplit[0];
			if (validUsername.matcher(username).matches()) {
				event.setForcedName(username);
				event.setForcedUUID(Profile.generateOfflineModeUUID(username));	
			}
		}
	}

}

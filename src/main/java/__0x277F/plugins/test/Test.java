package __0x277F.plugins.test;

import com.torchmind.minecraft.annotation.Plugin;
import com.torchmind.minecraft.annotation.command.Command;
import com.torchmind.minecraft.annotation.command.Commands;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

@Plugin(name = "Test", version = "1.0")
@Commands(@Command(name = "test"))
public class Test extends JavaPlugin implements Listener {
    private Set<Player> gliding;
    @Override
    public void onEnable() {
        this.gliding = new HashSet<Player>();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        if(gliding.contains(event.getEntity()) && !event.isGliding()) {
            event.setCancelled(true);
            //Bukkit.broadcastMessage("Gliding cancellation canceled!");
            //event.getEntity().setVelocity(event.getEntity().getLocation().getDirection().multiply(2));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("test") && sender instanceof Player) {
            Player p = (Player) sender;
            p.setGliding(true);
            gliding.add(p);
        }
        return true;
    }
}

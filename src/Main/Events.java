package Main;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if(!(e.getAction() == Action.RIGHT_CLICK_AIR)) return; //if the player does not right click the air, it will stop the code right there.
		if(!(e.getItem().getType() == Material.BLAZE_ROD)) return; //if the item that the player right clicked with is not a Blaze Rod, it will stop the code right there.
		Snowball s = e.getPlayer().launchProjectile(Snowball.class); //here we launch the snowball
		s.getWorld().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 10); //We will play a smoke effect at the shooter's location
	}
	 @EventHandler
	 public void onProjectileHit(ProjectileHitEvent e) {
		 Projectile p = e.getEntity(); // states that we will now be able to call the projectile "p"
		 if(!(p instanceof Snowball)) return; // if the projectile is not a snowball, the code will stop
		 Snowball s = (Snowball) p; // states that the snowball will not be called "s"
		 s.getWorld().createExplosion(s.getLocation(), 5.0F); //we create a 10 block explosion where the snowball lands
		 for(Entity en : s.getNearbyEntities(5, 30, 5)) { //we get the entities in a 5 block radius of where the snowball hit and call them "en"
			 if(en instanceof Player) { // we check that "en" are players
				 Player pl = (Player) en; // we call the player "ens" "pl"
				 if(!(pl == e.getEntity().getShooter())) pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0)); // we add blindness to all of these "pls" for 5 seconds. In minecraft, 20 ticks is one second. Therefore 20*5 = 100. No effect will be added to the shooter.
			 }
		 }
}
}

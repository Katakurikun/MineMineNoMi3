package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.CyborgProjectiles.CoupDeVent;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.CyborgProjectiles.FreshFire;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.CyborgProjectiles.RadicalBeam;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.CyborgProjectiles.StrongRight;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class ToriPhoenixProjectiles
{
	
	
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();
	
	static
	{
		abilitiesClassesArray.add(new Object[] {PhoenixGoen.class, ListAttributes.PHOENIXGOEN});
	}
	
	
	public static class PhoenixGoen extends AbilityProjectile
	{
		public PhoenixGoen(World world)
		{super(world);}
		
		public PhoenixGoen(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public PhoenixGoen(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{				
			MainMod.proxy.spawnCustomParticles(this, ID.PARTICLE_NAME_BLUEFLAME, this.posX, this.posY, this.posZ, 0, 0, 0);
			super.onUpdate();
		}
	}
}
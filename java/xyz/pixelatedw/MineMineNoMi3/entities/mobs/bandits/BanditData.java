package xyz.pixelatedw.MineMineNoMi3.entities.mobs.bandits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.pirates.PirateData;

public class BanditData extends EntityNewMob
{
	protected EntityAIBase entityAIMeleeAttack = new EntityAIAttackOnCollide(this, 1.0D, false);
	private EntityAIBase entityAIAttackNonMarine = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true);
	
	public BanditData(World world)
	{
		this(world, null);
	}
	
	public BanditData(World world, String[] textures) 
	{
		super(world, textures);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, entityAIMeleeAttack);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, PirateData.class, 0, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, MarineData.class, 0, true));
	}
  
	public void onEntityUpdate() 
	{
		if(this.getAttackTarget() == null)
		{
			this.targetTasks.removeTask(entityAIAttackNonMarine);
			for(EntityLivingBase target : WyHelper.getEntitiesNear(this, 20))
			{	
				if(target instanceof EntityPlayer)
				{
					EntityPlayer targetP = (EntityPlayer) target;
					ExtendedEntityData props = ExtendedEntityData.get(targetP);
	
					this.setTarget(targetP);
					this.targetTasks.addTask(1, entityAIAttackNonMarine);
				}
			}
		}
		
		super.onEntityUpdate();
	}

	protected boolean isValidLightLevel()
	{return true;} 
    
	protected boolean canDespawn()
	{return true;}
    
	public boolean isAIEnabled()
	{return true;}
	
	public boolean getCanSpawnHere()
	{return true;}
		
}


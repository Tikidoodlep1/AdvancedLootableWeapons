package com.tiki.advancedlootableweapons.handlers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tiki.advancedlootableweapons.entity.MobType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class GlobalDropsHandler {
	private static final Set<ResourceLocation> nameList = EntityList.getEntityNameList();
	private static final List<Class<? extends EntityLivingBase>> classList = Lists.newArrayList();
	private static final Map<Class<? extends EntityLivingBase>, Boolean> entityMap = Maps.<Class<? extends EntityLivingBase>, Boolean>newHashMap();
	
	public static void registerDrops(){
		for(ResourceLocation name : nameList) {
			Class<? extends Entity> clazz = EntityList.getClass(name);
			if(clazz != null && EntityLivingBase.class.isAssignableFrom(clazz) && !(clazz.equals(EntityArmorStand.class))) {
				//System.out.println("clazz extends EntityLivingBase + " + clazz.getName());
				classList.add(clazz.asSubclass(EntityLivingBase.class));
			}
		}
		
		for(Class<? extends EntityLivingBase> cls : classList) {
			String clsName = cls.getName();
			String firstFormattedName = clsName.substring(21);
			String secondFormattedName = firstFormattedName.substring(firstFormattedName.indexOf('.') + 7).toUpperCase();
			//System.out.println("formatted name is: " + secondFormattedName);
			MobType type = getEntityType(cls);
			MobType hostility = getEntityHostility(cls);
			try {
				if(!ConfigHandler.ONLY_BOSS_DROPS_SHADOW) {
					if((ConfigHandler.LAND_MOBS_DROP_SHADOW && type.equals(MobType.LAND)) || (ConfigHandler.WATER_MOBS_DROP_SHADOW && type.equals(MobType.WATER)) || (ConfigHandler.AIR_MOBS_DROP_SHADOW && type.equals(MobType.AIR)) || (ConfigHandler.BOSS_DROPS_SHADOW && isEntityBoss(cls))) {
						if((ConfigHandler.PEACEFUL_MOBS_DROP_SHADOW && hostility.equals(MobType.PEACEFUL)) || (ConfigHandler.NEUTRAL_MOBS_DROP_SHADOW && hostility.equals(MobType.NEUTRAL)) || (ConfigHandler.HOSTILE_MOBS_DROP_SHADOW && hostility.equals(MobType.HOSTILE))) {
							//System.out.println(ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW").getName() + ", MobType: " + type.toString() + " & " + hostility.toString());
							entityMap.put(cls, ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW").getBoolean(ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW")));
						}else {
							//System.out.println(ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW").getName() + ", MobType: " + type.toString() + " & " + hostility.toString());
							entityMap.put(cls, false);
						}
					}else {
						//System.out.println(ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW").getName() + ", MobType: " + type.toString() + " & " + hostility.toString());
						entityMap.put(cls, false);
					}
				}else {
					if(isEntityBoss(cls)) {
						//System.out.println(ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW").getName() + ", MobType: " + type.toString() + " & " + hostility.toString());
						entityMap.put(cls, ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW").getBoolean(ConfigHandler.class.getDeclaredField(secondFormattedName + "_DROP_SHADOW")));
					}
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Map<Class<? extends EntityLivingBase>, Boolean> getEntityMap(){
		Map<Class<? extends EntityLivingBase>, Boolean> tempMap = Maps.<Class<? extends EntityLivingBase>, Boolean>newHashMap();
		tempMap.putAll(entityMap);
		return tempMap;
	}
	
	public static MobType getEntityType(Class<? extends EntityLivingBase> cls) {
		if(EntityWaterMob.class.isAssignableFrom(cls) || EntityGuardian.class.isAssignableFrom(cls) || cls.equals(EntityGuardian.class)) {
			return MobType.WATER;
		}else if(cls.equals(EntityDragon.class) || cls.equals(EntityWither.class) || cls.equals(EntityBat.class) || cls.equals(EntityBlaze.class) 
				|| cls.equals(EntityGhast.class) || cls.equals(EntityVex.class) || cls.equals(EntityParrot.class)){
			return MobType.AIR;
		}else {
			return MobType.LAND;
		}
	}
	
	public static MobType getEntityHostility(Class<? extends EntityLivingBase> cls) {
		if(cls.equals(EntityWolf.class) || cls.equals(EntityLlama.class) || cls.equals(EntitySpider.class) || cls.equals(EntityPigZombie.class) || cls.equals(EntityIronGolem.class)
				|| cls.equals(EntityEnderman.class) || cls.equals(EntityPolarBear.class)) {
			return MobType.NEUTRAL;
		}else if(EntityAnimal.class.isAssignableFrom(cls) || EntityAmbientCreature.class.isAssignableFrom(cls) || cls.equals(EntityVillager.class) || cls.equals(EntitySquid.class)) {
			return MobType.PEACEFUL;
		}else if(EntityMob.class.isAssignableFrom(cls) || IMob.class.isAssignableFrom(cls)) {
			return MobType.HOSTILE;
		}else {
			return MobType.NEUTRAL;
		}
	}
	
	public static boolean isEntityBoss(Class<? extends EntityLivingBase> cls) {
		if(cls.equals(EntityWither.class) || cls.equals(EntityDragon.class)) {
			return true;
		}else {
			return false;
		}
	}
}

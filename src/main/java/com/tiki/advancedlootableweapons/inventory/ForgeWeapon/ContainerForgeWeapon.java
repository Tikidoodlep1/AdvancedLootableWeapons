package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ContainerForgeWeapon extends Container{
	
    public final IInventory inputSlot;
    private ItemStack toolForgeHammer;
    private final World world;
    private int buttonPressed;
    private EntityPlayer player;
    private static final ForgeWeaponRecipes recipes = new ForgeWeaponRecipes();
    private ItemStack[] toolHeadArr = new ItemStack[] {new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD), new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD_2),
    		new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_3),
    		new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD), new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_3),
    		new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD), new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD_2),
    		new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD), new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3),
    		new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD_2),
    		new ItemStack(ItemInit.HOT_TOOL_ROD), new ItemStack(ItemInit.HOT_TOOL_ROD_2)
    		};
    private static final Map<String, ItemStack> ingotMap = Maps.<String, ItemStack>newHashMap();
    private static final Map<String, NonNullList<ItemStack>> oreDictMap = Maps.<String, NonNullList<ItemStack>>newHashMap();
    
    @SideOnly(Side.CLIENT)
    public ContainerForgeWeapon(InventoryPlayer playerInventory, World worldIn, EntityPlayer player, int i)
    {
        this(playerInventory, worldIn, player);
        this.buttonPressed = -1;
    }
    
	public ContainerForgeWeapon(InventoryPlayer playerInventory, final World worldIn, EntityPlayer player)
    {
        this.inputSlot = new InventoryBasic("Forge Weapon", true, 4);
        if(player.getActiveItemStack().getItem() instanceof ToolForgeHammer) {
        	this.toolForgeHammer = player.getActiveItemStack();
        }else {
        	for(ItemStack inv : playerInventory.mainInventory) {
        		if(inv.getItem() instanceof ToolForgeHammer) {
        			this.toolForgeHammer = inv;
        		}
        	}
        }
        this.player = player;
        this.world = worldIn;
        this.buttonPressed = -1;
        oreDictMap.put("Copper", OreDictionary.getOres("ingotCopper"));
        oreDictMap.put("Silver", OreDictionary.getOres("ingotSilver"));
        oreDictMap.put("Bronze", OreDictionary.getOres("ingotBronze"));
        oreDictMap.put("Platinum", OreDictionary.getOres("ingotPlatinum"));
        oreDictMap.put("Steel", OreDictionary.getOres("ingotSteel"));
        ingotMap.put("Iron", new ItemStack(Items.IRON_INGOT));
        ingotMap.put("Kobold", new ItemStack(ItemInit.INGOT_KOBOLD));
        ingotMap.put("Copper", new ItemStack(ItemInit.INGOT_COPPER));
        ingotMap.put("Silver", new ItemStack(ItemInit.INGOT_SILVER));
        ingotMap.put("Bronze", new ItemStack(ItemInit.INGOT_BRONZE));
        ingotMap.put("Platinum", new ItemStack(ItemInit.INGOT_PLATINUM));
        ingotMap.put("Steel", new ItemStack(ItemInit.INGOT_STEEL));
        ingotMap.put("Shadow Platinum", new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM));
        ingotMap.put("Frost Steel", new ItemStack(ItemInit.INGOT_FROST_STEEL));
        ingotMap.put("Obsidian", new ItemStack(ItemInit.INGOT_OBSIDIAN));
        ingotMap.put("Crystallite", new ItemStack(ItemInit.INGOT_CRYSTALLITE));
        ingotMap.put("Dusksteel", new ItemStack(ItemInit.INGOT_DUSKSTEEL));
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 56, 33) {
        	public boolean isItemValid(ItemStack stack)
            {
        		for(NonNullList<ItemStack> oreStack : oreDictMap.values()) {
        			if(OreDictionary.containsMatch(false, oreStack, stack)) {
        				return true;
        			}
        		}
        		if(stack.getItem() instanceof ItemHotToolHead || ingotMap.containsKey(getItemString(stack, false))) {
        			changeItem(stack);
        			return true;
        		}else {
            		return false;
            	}
            }
        	
        	public int getSlotStackLimit() {
        		return 1;
        	}
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
        		thePlayer.inventory.placeItemBackInInventory(worldIn, inputSlot.removeStackFromSlot(1));
                return stack;
            }
        });
        
        this.addSlotToContainer(new Slot(this.inputSlot, 1, 56, 53) {
        	public boolean isItemValid(ItemStack stack)
            {
        		for(NonNullList<ItemStack> oreStack : oreDictMap.values()) {
        			if(OreDictionary.containsMatch(false, oreStack, stack)) {
        				return true;
        			}
        		}
        		if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD)) || ingotMap.containsKey(getItemString(stack, false))) {
        			return true;
        		}else {
        			for(int j = 0; j < toolHeadArr.length; j++) {
        				if(stack.isItemEqualIgnoreDurability(toolHeadArr[j]) && j != 1 && j != 6 && j != 9 && j != 13 && j != 16 && j != 21 && j != 25 && j != 27 && j != 32 && j != 37 && j != 41 && j != 45 && j != 48 && j != 50 && j != 52) {
        					return true;
        				}
            		}
        			return false;
        		}
            }

			public int getSlotStackLimit() {
        		return 1;
        	}
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
                return stack;
            }
        });
        
        this.addSlotToContainer(new Slot(this.inputSlot, 2, 114, 43) {
        	public boolean isItemValid(ItemStack stack)
            {
        		return false;
            }
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
        		System.out.println("Does recipes contain an exp value: " + recipes.getExpValue(stack) + ", stack is: " + stack);
        		if(recipes.getExpValue(stack) > 0) {
        			thePlayer.addExperience(recipes.getExpValue(stack));
        			thePlayer.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        		}
                return stack;
            }
        });
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }
	
	private boolean checkItem(ItemStack stack) {
		System.out.println("is slot 1 equal to a hot tood rod: " + (inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))));
		System.out.println("Slot 1 is: " + inputSlot.getStackInSlot(1).getDisplayName());
		if((inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD)))) {
			ItemStack result = ItemStack.EMPTY;
			ItemStack input = inputSlot.getStackInSlot(0);
			ItemStack toolRod = inputSlot.getStackInSlot(1);
			NBTTagCompound nbt = new NBTTagCompound();
			NBTTagCompound nbt2 = new NBTTagCompound();
			NBTTagCompound list = new NBTTagCompound();
			NBTTagCompound list2 = new NBTTagCompound();
			String material, material2;
			nbt.setString("Material", nbt.getString("Material"));
			nbt.setDouble("addedDamage", nbt.getDouble("addedDamage"));
			nbt.setInteger("addedDurability", nbt.getInteger("addedDurability"));
			nbt2.setString("Material", nbt2.getString("Material"));
			nbt2.setDouble("addedDamage", nbt2.getDouble("addedDamage"));
			nbt2.setInteger("addedDurability", nbt2.getInteger("addedDurability"));
			
			for(int j = 0; j < toolHeadArr.length; j++) {
				if((inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) && inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2))) || stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD)) || stack.isItemEqualIgnoreDurability(toolHeadArr[j]) && (j == 1 || j == 6 || j == 9 || j == 13 || j == 16 || j == 21 || j == 25 || j == 27 || j == 32 || j == 37 || j == 41 || j == 45 || j == 48 || j == 50 || j == 52)) {
					if(!input.hasTagCompound()) {
						input.setTagCompound(nbt);
					}
					list = input.getTagCompound();
					
					if(!toolRod.hasTagCompound()) {
						toolRod.setTagCompound(nbt2);
					}
					list2 = toolRod.getTagCompound();
					
					material = list.getString("Material");
					material2 = list2.getString("Material");
					nbt.setDouble("addedDamage", list.getDouble("addedDamage") + list2.getDouble("addedDamage"));
					nbt.setInteger("addedDurability", list.getInteger("addedDurability") + list2.getInteger("addedDurability"));
					System.out.println("Are Materials the same: " + material.equalsIgnoreCase(material2) + ", individual materials are: " + material + ", " + material2);
					if(material.equalsIgnoreCase(material2)){
						ForgeWeaponRecipes recipe = new ForgeWeaponRecipes();
						result = recipe.getItemResult(material, input, toolRod);
						System.out.println("result: " + result);
						if(result != ItemStack.EMPTY) {
							inputSlot.setInventorySlotContents(2, result);
							if(result.getItem() instanceof ToolStabSword) {
								((ToolStabSword)inputSlot.getStackInSlot(2).getItem()).setMaximumDamage(result, (list.getInteger("addedDurability") + list2.getInteger("addedDurability")));
								((ToolStabSword)inputSlot.getStackInSlot(2).getItem()).generateNameAndModifiers(inputSlot.getStackInSlot(2), (list.getDouble("addedDamage") + list2.getDouble("addedDamage")));
								damageItem(1, this.toolForgeHammer);
							}else if(result.getItem() instanceof ToolSlashSword){
								((ToolSlashSword)inputSlot.getStackInSlot(2).getItem()).setMaximumDamage(result, (list.getInteger("addedDurability") + list2.getInteger("addedDurability")));
								((ToolSlashSword)inputSlot.getStackInSlot(2).getItem()).generateNameAndModifiers(inputSlot.getStackInSlot(2), (list.getDouble("addedDamage") + list2.getDouble("addedDamage")));
								damageItem(1, this.toolForgeHammer);
							}else if(result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))) {
								nbt.setString("Material", material);
								inputSlot.getStackInSlot(2).setTagCompound(nbt);
								damageItem(1, this.toolForgeHammer);
								if((((inputSlot.getStackInSlot(0).getItemDamage() + inputSlot.getStackInSlot(1).getItemDamage())/2) + 2800) < 6000) {
									inputSlot.getStackInSlot(2).setItemDamage(((inputSlot.getStackInSlot(0).getItemDamage() + inputSlot.getStackInSlot(1).getItemDamage())/2) + 2800);
								}else {
									inputSlot.getStackInSlot(2).setItemDamage(6000);
								}
							}
							inputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
							inputSlot.setInventorySlotContents(1, ItemStack.EMPTY);
							return true;
						}
					}
					break;
				}
			}
		}
		return false;
	}
	
	protected String getItemString(ItemStack stack, boolean useOreDict) {
		if(useOreDict) {
			for(NonNullList<ItemStack> oreStack : oreDictMap.values()) {
				if(OreDictionary.containsMatch(false, oreStack, stack)) {
					for(int ID : OreDictionary.getOreIDs(stack)) {
						if(OreDictionary.getOreName(ID).contains("ingot")){
							return OreDictionary.getOreName(ID).substring(5);
						}
					}
				}
			}
		}else {
			for(String compare : ingotMap.keySet()) {
				if(ingotMap.get(compare).isItemEqualIgnoreDurability(stack)) {
					return compare;
				}
			}
		}
		
		return "";
	}
	
	@Override
	public boolean enchantItem(EntityPlayer playerIn, int id)
    {
		this.buttonPressed = id;
		
		if(player.getActiveItemStack().getItem() instanceof ToolForgeHammer) {
        	this.toolForgeHammer = player.getActiveItemStack();
        }else {
        	NonNullList<ItemStack> inv = player.inventory.mainInventory;
        	for(int i = 35; i >= 0; i--) {
        		if(inv.get(i).getItem() instanceof ToolForgeHammer) {
        			this.toolForgeHammer = inv.get(i);
        			break;
        		}else {
        			this.toolForgeHammer = null;
        		}
        	}
        	if(this.toolForgeHammer == null) {
        		playerIn.closeScreen();
        	}
        }
		
		if(changeItem(this.inputSlot.getStackInSlot(0))) {
			this.buttonPressed = -1;
        	return true;
		}else {
			this.buttonPressed = -1;
			return false;
		}
    }
	
	public boolean changeItem(ItemStack stack) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("Material", getItemString(stack, false));
		if(nbt.getString("Material").equalsIgnoreCase("")) {
			nbt.setString("Material", getItemString(stack, true));
		}
		nbt.setDouble("addedDamage", nbt.getDouble("addedDamage"));
		nbt.setInteger("addedDurability", nbt.getInteger("addedDurability"));
		
		int weapon = this.buttonPressed;
		
		if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
			NBTTagCompound list = new NBTTagCompound();
			list = stack.getTagCompound();
			int heat = stack.getItemDamage() + 2800;
			if(heat > 6000) {
				heat = 6000;
			}
			System.out.println("button pressed is " + this.buttonPressed);
        	if(weapon == 0 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 1 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 2 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 3 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 4 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 5 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 6 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 7 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 8 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 9 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 10 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 11 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 12 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 13 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}else if(weapon == 14 && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
        		ItemStack changeStack = new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.decrStackSize(1, 1);
        		inputSlot.setInventorySlotContents(2, changeStack);
        		nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
    			nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(2).setTagCompound(nbt);
        		damageItem(1, this.toolForgeHammer);
        	}
        	return true;
		}else {
			if(weapon == 98) {
				if(ingotMap.containsKey(getItemString(stack, false))) {
					ItemStack changeStack = new ItemStack(ItemInit.HOT_TOOL_ROD);
					changeStack.setItemDamage(6000);
					inputSlot.decrStackSize(0, 1);
					inputSlot.setInventorySlotContents(2, changeStack);
					inputSlot.getStackInSlot(2).setTagCompound(nbt);
					damageItem(1, this.toolForgeHammer);
					return true;
				}
			}else if(weapon == 99) {
				if(checkItem(stack) == false) {
					boolean containsOreDict = false;
					System.out.println("button pressed is 99");
					for(NonNullList<ItemStack> oreStack : oreDictMap.values()) {
	        			if(OreDictionary.containsMatch(false, oreStack, stack)) {
	        				containsOreDict = true;
	        			}
	        		}
					if(ingotMap.containsKey(getItemString(stack, false)) || containsOreDict) {
						ItemStack result = new ItemStack(ItemInit.HOT_TOOL_HEAD);
						result.setItemDamage(6000);
						inputSlot.decrStackSize(0, 1);
						inputSlot.setInventorySlotContents(2, result);
						inputSlot.getStackInSlot(2).setTagCompound(nbt);
						damageItem(1, this.toolForgeHammer);
						return true;
					}else if(stack.getItem() instanceof ItemHotToolHead) {
						ItemStack result = ItemStack.EMPTY;
						System.out.println("slot 0 is: " + inputSlot.getStackInSlot(0) + ", slot 1 is: " + inputSlot.getStackInSlot(1));
						result = getNextToolHead(stack);
						System.out.println("Result is: " + result);
						NBTTagCompound list = new NBTTagCompound();
						list = stack.getTagCompound();
						int heat = stack.getItemDamage() + 2800;
						System.out.println("Next tool head is: " + getNextToolHead(stack).getDisplayName());
						if(result.getItem() instanceof ItemHotToolHead && getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material")) && !(result.isItemEqualIgnoreDurability(ItemStack.EMPTY) && result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD)) || result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD)))){
							if(heat > 6000) {
								heat = 6000;
							}
							result.setItemDamage(heat);
							inputSlot.decrStackSize(0, 1);
							inputSlot.decrStackSize(1, 1);
							inputSlot.setInventorySlotContents(2, result);
							nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
							nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
							nbt.setString("Material", list.getString("Material"));
							inputSlot.getStackInSlot(2).setTagCompound(nbt);
							damageItem(1, this.toolForgeHammer);
							return true;
						}else {
							return false;
						}
					}
				}
			}
			return false;
    	}
	}
	
	private void damageItem(int amount, ItemStack forgeHammer) {
		if(forgeHammer.attemptDamageItem(amount, new Random(), null)) {
			player.inventory.deleteStack(forgeHammer);
			Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		}
	}
	
	private double checkHeat(ItemStack stack) {
		int heat = stack.getItemDamage();
		if(inputSlot.getStackInSlot(2).getItem() instanceof ItemHotToolHead) {
			if(heat <= 2999) {
				return 0.5D;
			}else if(heat > 2999 && heat <= 4999) {
				return 0.25D;
			}else {
				return 0.0D;
			}
		}
		return 0.0D;
	}
	
	private ItemStack getNextToolHead(ItemStack stack) {
		ItemStack[] headArr = new ItemStack[] {new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD), new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD_2),
	    		new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5),
	    		new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_3),
	    		new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_4),
	    		new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD), new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_3),
	    		new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_5),
	    		new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4),
	    		new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD), new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD_2),
	    		new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5),
	    		new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5),
	    		new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_4),
	    		new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_4),
	    		new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD), new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3),
	    		new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD_2),
	    		new ItemStack(ItemInit.HOT_TOOL_ROD), new ItemStack(ItemInit.HOT_TOOL_ROD_2)
	    		};
		ItemStack newStack = stack.copy();
		
		for(int i = 0; i < headArr.length; i++) {
			if(newStack.isItemEqualIgnoreDurability(headArr[i])) {
				if(i != 1 && i != 6 && i != 9 && i != 13 && i != 16 && i != 21 && i != 25 && i != 27 && i != 32 && i != 37 && i != 41 && i != 45 && i != 48 && i != 50 && i != 52 && (i + 1) < headArr.length) {
					System.out.println("i is: " + i + ", i + 1  is: " + headArr[i + 1]);
					return headArr[i + 1];
				}else {
					if(i >= headArr.length) {
						System.out.println("--CRITICAL-- java.lang.ArrayIndexOutOfBoundsException: " + (i + 1));
						System.out.println("Error has been discarded. Please let the mod author know about this problem.");
					}
					return ItemStack.EMPTY;
				}
			}
		}
		return ItemStack.EMPTY;
	}

    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        super.onCraftMatrixChanged(inventoryIn);
    }
    
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, inputSlot);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
    }

    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote)
        {
            this.clearContainer(playerIn, this.world, this.inputSlot);
        }
    }
    
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }
    
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 36, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0 && index != 1)
            {
                if (index >= 3 && index < 36 && !this.mergeItemStack(itemstack1, 0, 2, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 36, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}

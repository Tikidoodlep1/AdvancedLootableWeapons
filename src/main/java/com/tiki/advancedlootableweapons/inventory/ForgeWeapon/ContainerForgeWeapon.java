package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;
import com.tiki.advancedlootableweapons.armor.ArmorBonusesBase;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemArmorBinding;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.ItemUnboundArmor;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ContainerForgeWeapon extends Container {
	
    public final IInventory inputSlot;
    private int toolForgeHammer;
    private final World world;
    private int buttonPressed;
    private EntityPlayer player;
    private static final ForgeWeaponRecipes recipes = ForgeWeaponRecipes.INSTANCE;
    private static final Map<Item, String> ingotMap = Maps.<Item, String>newHashMap();
    private static final Map<NonNullList<ItemStack>, String> oreDictMap = Maps.<NonNullList<ItemStack>, String>newHashMap();
    public static final int TOOL_ROD_BUTTON = 98;
    public static final int HAMMER_BUTTON = 99;
    
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
        	this.toolForgeHammer = player.inventory.currentItem;
        }else {
        	NonNullList<ItemStack> inv = player.inventory.mainInventory;
        	for(int i = 35; i >= 0; i--) {
        		if(inv.get(i).getItem() instanceof ToolForgeHammer) {
        			toolForgeHammer = i;
        			break;
        		}else {
        			toolForgeHammer = -1;
        		}
        	}
        }
        
        this.player = player;
        this.world = worldIn;
        this.buttonPressed = -1;
        oreDictMap.put(OreDictionary.getOres("ingotCopper"), "Copper");
        oreDictMap.put(OreDictionary.getOres("ingotSilver"), "Silver");
        oreDictMap.put(OreDictionary.getOres("ingotBronze"), "Bronze");
        oreDictMap.put(OreDictionary.getOres("ingotPlatinum"), "Platinum");
        oreDictMap.put(OreDictionary.getOres("ingotSteel"), "Steel");
        ingotMap.put(Items.IRON_INGOT, "Iron");
        ingotMap.put(ItemInit.INGOT_KOBOLD, "Kobold");
        ingotMap.put(ItemInit.INGOT_COPPER, "Copper");
        ingotMap.put(ItemInit.INGOT_SILVER, "Silver");
        ingotMap.put(ItemInit.INGOT_BRONZE, "Bronze");
        ingotMap.put(ItemInit.INGOT_PLATINUM, "Platinum");
        ingotMap.put(ItemInit.INGOT_STEEL, "Steel");
        ingotMap.put(ItemInit.INGOT_SHADOW_PLATINUM, "Shadow Platinum");
        ingotMap.put(ItemInit.INGOT_FROST_STEEL, "Frost Steel");
        ingotMap.put(ItemInit.INGOT_OBSIDIAN, "Obsidian");
        ingotMap.put(ItemInit.INGOT_CRYSTALLITE, "Crystallite");
        ingotMap.put(ItemInit.INGOT_DUSKSTEEL, "Dusksteel");
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 56, 33) {
        	public boolean isItemValid(ItemStack stack)
            {
        		for(NonNullList<ItemStack> oreStack : oreDictMap.keySet()) {
        			if(OreDictionary.containsMatch(false, oreStack, stack)) {
        				return true;
        			}
        		}
        		if(stack.getItem() instanceof ItemHotToolHead || ingotMap.containsKey(stack.getItem())) {
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
        		for(NonNullList<ItemStack> oreStack : oreDictMap.keySet()) {
        			if(OreDictionary.containsMatch(false, oreStack, stack)) {
        				return true;
        			}
        		}
        		if(stack.getItem() instanceof ItemHotToolHead || ingotMap.containsKey(stack.getItem())) {
        			return true;
        		}
        		return false;
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
        		if(toolForgeHammer == -1) {
        			if(player.getActiveItemStack().getItem() instanceof ToolForgeHammer) {
        		    	toolForgeHammer = player.inventory.currentItem;
        		    }else {
        		    	NonNullList<ItemStack> inv = player.inventory.mainInventory;
        		    	for(int i = 35; i >= 0; i--) {
        		    		if(inv.get(i).getItem() instanceof ToolForgeHammer) {
        		    			toolForgeHammer = i;
        		    			break;
        		    		}else {
        		    			toolForgeHammer = -1;
        		    		}
        		    	}
        		    	if(toolForgeHammer == -1) {
        		    		thePlayer.closeScreen();
        		    	}
        		    }
        		}
        		
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
	
	protected String getItemString(ItemStack stack, boolean useOreDict) {
		if(useOreDict) {
			for(NonNullList<ItemStack> oreStack : oreDictMap.keySet()) {
				if(OreDictionary.containsMatch(false, oreStack, stack)) {
					return oreDictMap.get(oreStack);
				}
			}
			for(Item compare : ingotMap.keySet()) {
				if(compare == stack.getItem()) {
					return ingotMap.get(compare);
				}
			}
		}else {
			for(Item compare : ingotMap.keySet()) {
				if(compare == stack.getItem()) {
					return ingotMap.get(compare);
				}
			}
		}
		
		return "";
	}
	
	@Override
	public boolean enchantItem(EntityPlayer playerIn, int id)
    {
		this.buttonPressed = id;
		
		if(craftItem()) {
			this.buttonPressed = -1;
        	return true;
		}else {
			this.buttonPressed = -1;
			return false;
		}
    }
	
	public boolean craftItem() {
		ItemStack input1 = this.inputSlot.getStackInSlot(0);
		ItemStack input2 = this.inputSlot.getStackInSlot(1);
		ItemStack outputSlot = this.inputSlot.getStackInSlot(2);
		if(outputSlot == ItemStack.EMPTY) {
			if(this.buttonPressed == HAMMER_BUTTON) {
				
				//We're binding armor here
				if(input1.getItem() instanceof ItemUnboundArmor && input2.getItem() instanceof ItemArmorBinding) {
					ItemStack result = recipes.getArmorBindingResult(input1.getItem(), input2.getItem());
					if(result.getItem() instanceof ArmorBonusesBase) {
						((ArmorBonusesBase)result.getItem()).setMaxDamage(((ItemArmorBinding)input2.getItem()).getExtraDur());
					}
					
					takeResourcesAndSetOutput(result, false, true);
				}
				
				//Here we're making the hot tool head into the next level
				if(input1.getItem() instanceof ItemHotToolHead) {
					ItemHotToolHead slot1 = ((ItemHotToolHead)input1.getItem());
					if(!slot1.finished && slot1.next != null) {
						ItemStack result = new ItemStack(slot1.next);
						result.setItemDamage(input1.getItemDamage() + 2300 > 6000 ? 6000 : input1.getItemDamage() + 2300);
						setNbtValues(input1, input2, result);
						
						takeResourcesAndSetOutput(result, true, false);
						return true;
					}
				}
				
				//Here we're making a weapon or long tool rod
				if(input1.getItem() instanceof ItemHotToolHead) {
					ItemHotToolHead slot1 = ((ItemHotToolHead)input1.getItem());
					
					if(input2.getItem() instanceof ItemHotToolHead) {
					ItemHotToolHead slot2 = ((ItemHotToolHead)input2.getItem());
					NBTTagCompound input1Tag = input1.getTagCompound();
					NBTTagCompound input2Tag = input2.getTagCompound();
					
					boolean matsMatch = input1Tag.getString("Material").contentEquals(input2Tag.getString("Material"));
						if(slot1.finished && slot2.finished && matsMatch) {
							ItemStack result = recipes.getItemResult(input1Tag.getString("Material"), slot1, slot2);
							if(result.getItem() instanceof ToolStabSword) {
								((ToolStabSword)result.getItem()).setMaximumDamage(result, result.getMaxDamage() + input1Tag.getInteger("addedDurability") + input2Tag.getInteger("addedDurability"));
								((ToolStabSword)result.getItem()).generateNameAndModifiers(result, input1Tag.getDouble("addedDamage") + input2Tag.getDouble("addedDamage"), input1Tag.getString("Material"));
								
								damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
							}else if(result.getItem() instanceof ToolSlashSword) {
								((ToolSlashSword)result.getItem()).setMaximumDamage(result, result.getMaxDamage() + input1Tag.getInteger("addedDurability") + input2Tag.getInteger("addedDurability"));
								((ToolSlashSword)result.getItem()).generateNameAndModifiers(result, input1Tag.getDouble("addedDamage") + input2Tag.getDouble("addedDamage"), input1Tag.getString("Material"));
								
								damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
							}else if (result.getItem() == ItemInit.LONG_TOOL_ROD) {
								result.setItemDamage(input1.getItemDamage() + 2300 > 6000 ? 6000 : input1.getItemDamage() + 2300);
								setNbtValues(input1, input2, result);
							}
							
							takeResourcesAndSetOutput(result, true, true);
							return true;
						}
					}
				}
				
				//Here we're making a base hot tool head
				boolean oreMapContains = false;
				
				for(NonNullList<ItemStack> list : oreDictMap.keySet()) {
					if(list.contains(input1)) {
						oreMapContains = true;
						break;
					}
				}
				
				if(oreMapContains || ingotMap.containsKey(input1.getItem())) {
					NBTTagCompound resultNBT = new NBTTagCompound();
					resultNBT.setString("Material", oreMapContains ? getItemString(input1, true) : getItemString(input1, false));
					resultNBT.setInteger("addedDurability", 0);
					resultNBT.setDouble("addedDamage", 0.0D);
					ItemStack result = new ItemStack(ItemInit.HOT_TOOL_HEAD);
					result.setItemDamage(6000);
					result.setTagCompound(resultNBT);
					
					takeResourcesAndSetOutput(result, true, false);
					return true;
				}
			}else if(this.buttonPressed == TOOL_ROD_BUTTON) {
				//We're making a tool rod here
				if(input1.getItem() == ItemInit.HOT_TOOL_HEAD) {
					ItemStack result = new ItemStack(ItemInit.HOT_TOOL_ROD);
					result.setItemDamage(input1.getItemDamage() + 2300 > 6000 ? 6000 : input1.getItemDamage() + 2300);
					setNbtValues(input1, input2, result);
					
					takeResourcesAndSetOutput(result, true, false);
					return true;
				}
			}else if(this.buttonPressed != -1){
				//We're changing a hot tool head into a specific weapon hot tool head here
				if(input1.getItem() == ItemInit.HOT_TOOL_HEAD) {
					WeaponLevels weaponEnum = WeaponLevels.getEnumByOrdinal(this.buttonPressed);
					ItemStack result = WeaponLevels.getStackByOrdinal(this.buttonPressed);
					result.setCount(weaponEnum.getCount());
					NBTTagCompound input1Tag = input1.getTagCompound();
					if(weaponEnum.needsAdditionalIngot()) {
						boolean isOreIngot = false;
						for(int id : OreDictionary.getOreIDs(input2)) {
							if(OreDictionary.getOreName(id).contains("ingot")) {
								isOreIngot = true;
								break;
							}
						}
						if(isOreIngot) {
							
							if(input1Tag.getString("Material").equals(getItemString(input2, true))) {
								if(weaponEnum == WeaponLevels.ARMOR_PLATE) {
									result = WeaponLevels.getArmorPlateByMaterial(input1Tag.getString("Material")).getStack();
								}
								
								result.setItemDamage(input1.getItemDamage() + 2300 > 6000 ? 6000 : input1.getItemDamage() + 2300);
								if(result != ItemStack.EMPTY) {
									setNbtValues(input1, input2, result);
									
									takeResourcesAndSetOutput(result, true, true);
									return true;
								}
							}
						}
					}else {
						result.setItemDamage(input1.getItemDamage() + 2300 > 6000 ? 6000 : input1.getItemDamage() + 2300);
						if(result != ItemStack.EMPTY) {
							setNbtValues(input1, input2, result);
								
							takeResourcesAndSetOutput(result, true, false);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private void setNbtValues(ItemStack input1, ItemStack input2, ItemStack to) {
		NBTTagCompound input1Tag = input1.getTagCompound();
		NBTTagCompound input2Tag = input2.getTagCompound();
		NBTTagCompound outputTag = new NBTTagCompound();
		double input1Heat = checkHeat(input1);
		double input2Heat = checkHeat(input2);
		
		System.out.println("heat 1: " + input1Heat + "heat 2: " + input2Heat);
				
		outputTag.setString("Material", input1Tag.getString("Material"));
		if(input2Tag != null) {
			outputTag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + input2Tag.getInteger("addedDurability") + ((int)((input1Heat + input2Heat) * 100)));
			outputTag.setDouble("addedDamage", input1Tag.getDouble("addedDamage") + input2Tag.getDouble("addedDamage") + input1Heat + input2Heat);
		}else {
			outputTag.setInteger("addedDurability", input1Tag.getInteger("addedDurability") + ((int)((input1Heat ) * 100)));
			outputTag.setDouble("addedDamage", input1Tag.getDouble("addedDamage") + input1Heat );
		}
		
		System.out.println("Added Dur: " + outputTag.getInteger("addedDurability") + ", added Damage: " + outputTag.getInteger("addedDamage"));
		to.setTagCompound(outputTag);
	}
	
	private void takeResourcesAndSetOutput(ItemStack output, boolean damageHammer, boolean removeBothInputs) {
		this.inputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
		if(removeBothInputs) {
			this.inputSlot.setInventorySlotContents(1, ItemStack.EMPTY);
		}
		this.inputSlot.setInventorySlotContents(2, output);
		if(damageHammer) {
			if(!(this.player.inventory.getStackInSlot(toolForgeHammer).getItem() instanceof ToolForgeHammer)) {
				toolForgeHammer = -1;
			}else {
				this.player.inventory.getStackInSlot(toolForgeHammer).attemptDamageItem(1, new Random(), null);
			}
			this.world.playSound(this.player.posX, this.player.posY, this.player.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
		}
		
	}
	
	private void damageItem(int amount, ItemStack forgeHammer) {
		Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
		if(forgeHammer.attemptDamageItem(amount, new Random(), null)) {
			player.inventory.deleteStack(forgeHammer);
			Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		}
	}
	
	private double checkHeat(ItemStack stack) {
		int heat = stack.getItemDamage();
		if(heat <= 2999) {
			return 0.5D;
		}else if(heat > 2999 && heat <= 4999) {
			return 0.25D;
		}else {
			return 0.0D;
		}
	}
	
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
    	//System.out.println("onCraftMatrixChanged()");
        super.onCraftMatrixChanged(inventoryIn);
    }
    
    public void addListener(IContainerListener listener)
    {
    	//System.out.println("addListener()");
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
            
            if (index == 0 || index == 1 || index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }
                
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 0 && index != 1)
            {
                if (index >= 3 && index < 39 && !this.mergeItemStack(itemstack1, 0, 2, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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

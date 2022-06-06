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

public class ContainerForgeWeapon extends Container {
	
    public final IInventory inputSlot;
    private int toolForgeHammer;
    private final World world;
    private int buttonPressed;
    private EntityPlayer player;
    private static final ForgeWeaponRecipes recipes = new ForgeWeaponRecipes();
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
        		if(stack.getItem() instanceof ItemHotToolHead || ingotMap.containsKey(getItemString(stack, false))) {
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
			ItemStack nextToolHead = getNextToolHead(stack);
			
			if((inputSlot.getStackInSlot(0).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) && inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2))) || nextToolHead == ItemStack.EMPTY) {
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
							damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
						}else if(result.getItem() instanceof ToolSlashSword){
							((ToolSlashSword)inputSlot.getStackInSlot(2).getItem()).setMaximumDamage(result, (list.getInteger("addedDurability") + list2.getInteger("addedDurability")));
							((ToolSlashSword)inputSlot.getStackInSlot(2).getItem()).generateNameAndModifiers(inputSlot.getStackInSlot(2), (list.getDouble("addedDamage") + list2.getDouble("addedDamage")));
							damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
						}else if(result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))) {
							nbt.setString("Material", material);
							inputSlot.getStackInSlot(2).setTagCompound(nbt);
							damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
							if((((inputSlot.getStackInSlot(0).getItemDamage() + inputSlot.getStackInSlot(1).getItemDamage())/2) + 2800) < 6000) {
								inputSlot.getStackInSlot(2).setItemDamage(((inputSlot.getStackInSlot(0).getItemDamage() + inputSlot.getStackInSlot(1).getItemDamage())/2) + 2800);
							}else {
								inputSlot.getStackInSlot(2).setItemDamage(6000);
							}
						}
						if(!(player.inventory.getStackInSlot(toolForgeHammer).getItem() instanceof ToolForgeHammer)) {
							toolForgeHammer = -1;
						}
						inputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
						inputSlot.setInventorySlotContents(1, ItemStack.EMPTY);
						return true;
					}
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
		if(inputSlot.getStackInSlot(2).getItem() == Items.AIR) {
			if(weapon <= 14 && stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
				NBTTagCompound list = new NBTTagCompound();
				list = stack.getTagCompound();
				int heat = stack.getItemDamage() + 2800;
				if(heat > 6000) {
					heat = 6000;
				}
				if(getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))) {
					ItemStack changeStack = WeaponLevels.getStackByOrdinal(weapon);
					changeStack.setItemDamage(heat);
					inputSlot.decrStackSize(0, 1);
					inputSlot.decrStackSize(1, 1);
					inputSlot.setInventorySlotContents(2, changeStack);
					nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
					nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
					nbt.setString("Material", list.getString("Material"));
					inputSlot.getStackInSlot(2).setTagCompound(nbt);
					damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
					if(!(player.inventory.getStackInSlot(toolForgeHammer).getItem() instanceof ToolForgeHammer)) {
						toolForgeHammer = -1;
					}
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
						damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
						if(!(player.inventory.getStackInSlot(toolForgeHammer).getItem() instanceof ToolForgeHammer)) {
							toolForgeHammer = -1;
						}
						return true;
					}
				}else if(weapon == 99) {
					if(checkItem(stack) == false) {
						boolean containsOreDict = false;
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
							damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
							if(!(player.inventory.getStackInSlot(toolForgeHammer).getItem() instanceof ToolForgeHammer)) {
								toolForgeHammer = -1;
							}
							return true;
						}else if(stack.getItem() instanceof ItemHotToolHead) {
							ItemStack result = ItemStack.EMPTY;
							result = getNextToolHead(inputSlot.getStackInSlot(0));
							NBTTagCompound list = new NBTTagCompound();
							list = stack.getTagCompound();
							int heat = stack.getItemDamage() + 2800;
						//																This section requires each hammering of the material to use an extra ingot
							if(result.getItem() instanceof ItemHotToolHead /*&& getItemString(inputSlot.getStackInSlot(1), false).equalsIgnoreCase(list.getString("Material"))*/ && !(result.isItemEqualIgnoreDurability(ItemStack.EMPTY) && result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD)) || result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD)))){
								if(heat > 6000) {
									heat = 6000;
								}
								result.setItemDamage(heat);
								inputSlot.decrStackSize(0, 1);
								//inputSlot.decrStackSize(1, 1);
								inputSlot.setInventorySlotContents(2, result);
								nbt.setDouble("addedDamage", list.getDouble("addedDamage") + checkHeat(stack));
								nbt.setInteger("addedDurability", list.getInteger("addedDurability") + (int) (checkHeat(stack) * 100));
								nbt.setString("Material", list.getString("Material"));
								inputSlot.getStackInSlot(2).setTagCompound(nbt);
								damageItem(1, player.inventory.getStackInSlot(toolForgeHammer));
								if(!(player.inventory.getStackInSlot(toolForgeHammer).getItem() instanceof ToolForgeHammer)) {
									toolForgeHammer = -1;
								}
								return true;
							}else {
								return false;
							}
						}
					}
				}
			}
    	}
		return false;
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
	
	private static ItemStack getNextToolHead(ItemStack stack) {
		WeaponLevels weapon = WeaponLevels.getWeaponByStack(stack);
		return WeaponLevels.getWeaponByLevelAndType(weapon.getLevel() + 1, weapon.getType());
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

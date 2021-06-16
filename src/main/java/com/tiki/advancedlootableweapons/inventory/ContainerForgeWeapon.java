package com.tiki.advancedlootableweapons.inventory;

import java.util.HashMap;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerForgeWeapon extends Container{
	
    private final IInventory inputSlot;
    private final IInventory forgeSlots;
    private final World world;
    private int buttonPressed;
    private ItemStack[] toolHeadArr = new ItemStack[] {new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD), new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD_2),
    		new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD_3),
    		new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD), new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD_3),
    		new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD), new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_4), new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD_5),
    		new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_3), new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD_4),
    		new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD), new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_2), new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD_3),
    		new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD), new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD_2),
    		new ItemStack(ItemInit.HOT_TOOL_ROD), new ItemStack(ItemInit.HOT_TOOL_ROD_2)
    		};
    private static final HashMap<String, ToolMaterial> ingots = new HashMap<String, ToolMaterial>();
    
    @SideOnly(Side.CLIENT)
    public ContainerForgeWeapon(InventoryPlayer playerInventory, World worldIn, EntityPlayer player, int i)
    {
        this(playerInventory, worldIn, player);
        this.buttonPressed = -1;
    }
    
	public ContainerForgeWeapon(InventoryPlayer playerInventory, final World worldIn, EntityPlayer player)
    {
        this.inputSlot = new InventoryBasic("Forge Weapon", true, 1);
        this.forgeSlots = new InventoryBasic("Forge Weapons", true, 2);
        this.world = worldIn;
        this.buttonPressed = -1;
        ingots.put("Iron", ToolMaterial.IRON);
        ingots.put("Kobold", ItemInit.MAT_KOBOLD);
        ingots.put("Copper", ItemInit.MAT_COPPER);
        ingots.put("Silver", ItemInit.MAT_SILVER);
        ingots.put("Bronze", ItemInit.MAT_BRONZE);
        ingots.put("Platinum", ItemInit.MAT_PLATINUM);
        ingots.put("Steel", ItemInit.MAT_STEEL);
        ingots.put("Shadow_platinum", ItemInit.MAT_SHADOW_PLATINUM);
        ingots.put("Frost_steel", ItemInit.MAT_FROST_STEEL);
        ingots.put("Obsidian", ItemInit.MAT_OBSIDIAN);
        ingots.put("Crystallite", ItemInit.MAT_CRYSTALLITE);
        ingots.put("Dusksteel", ItemInit.MAT_DUSKSTEEL);
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 80, 33) {
        	public boolean isItemValid(ItemStack stack)
            {
        		if(stack.getItem() instanceof ItemHotToolHead || ingots.containsKey(getItemString(stack))) {
        			for(int j = 0; j < toolHeadArr.length; j++) {
        				if(stack.isItemEqualIgnoreDurability(toolHeadArr[j])) {
        					addSlots(j);
        				}
        			}
        			changeItem(stack);
        			return true;
        		}else {
            		return false;
            	}
            }
        	
        	public int getSlotStackLimit() {
        		return 1;
        	}
        });
        
        this.addSlotToContainer(new Slot(this.forgeSlots, 1, 0, 0) {
        	
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
	
	protected String getItemString(ItemStack stack) {
		if(stack.isItemEqual(new ItemStack(Items.IRON_INGOT))) {
			return "Iron";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_KOBOLD))) {
			return "Kobold";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_COPPER))) {
			return "Copper";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_SILVER))) {
			return "Silver";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_BRONZE))) {
			return "Bronze";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_PLATINUM))) {
			return "Platinum";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_STEEL))) {
			return "Steel";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_SHADOW_PLATINUM))) {
			return "Shadow_platinum";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_FROST_STEEL))) {
			return "Frost_steel";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_OBSIDIAN))) {
			return "Obsidian";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_CRYSTALLITE))) {
			return "Crystallite";
		}else if(stack.isItemEqual(new ItemStack(ItemInit.INGOT_DUSKSTEEL))) {
			return "Dusksteel";
		}else {
			return "";
		}
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
		nbt.setString("Material", getItemString(stack));
		nbt.setDouble("reducedDamage", nbt.getDouble("reducedDamage"));
		nbt.setInteger("reducedDurability", nbt.getInteger("reducedDurability"));
		
		int weapon = this.buttonPressed;
		
		if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD))) {
			NBTTagCompound list = new NBTTagCompound();
			list = stack.getTagCompound();
			int heat = stack.getItemDamage() + 2800;
			if(heat > 6000) {
				heat = 6000;
			}
			System.out.println("button pressed is " + this.buttonPressed);
        	if(weapon == 0) {
        		ItemStack changeStack = new ItemStack(ItemInit.DAGGER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int)(checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 1) {
        		ItemStack changeStack = new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 2) {
        		ItemStack changeStack = new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 3) {
        		ItemStack changeStack = new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 4) {
        		ItemStack changeStack = new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 5) {
        		ItemStack changeStack = new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 6) {
        		ItemStack changeStack = new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 7) {
        		ItemStack changeStack = new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 8) {
        		ItemStack changeStack = new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 9) {
        		ItemStack changeStack = new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 10) {
        		ItemStack changeStack = new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 11) {
        		ItemStack changeStack = new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 12) {
        		ItemStack changeStack = new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 13) {
        		ItemStack changeStack = new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 14) {
        		ItemStack changeStack = new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}
        	return true;
		}else {
			if(weapon == 98) {
				if(ingots.containsKey(getItemString(stack))) {
					ItemStack changeStack = new ItemStack(ItemInit.HOT_TOOL_ROD);
					changeStack.setItemDamage(6000);
					inputSlot.decrStackSize(0, 1);
					inputSlot.setInventorySlotContents(0, changeStack);
					inputSlot.getStackInSlot(0).setTagCompound(nbt);
					return true;
				}
			}else if(weapon == 99) {
				System.out.println("button pressed is 99");
				if(ingots.containsKey(getItemString(stack))) {
					ItemStack result = new ItemStack(ItemInit.HOT_TOOL_HEAD);
					result.setItemDamage(6000);
					inputSlot.decrStackSize(0, 1);
					inputSlot.setInventorySlotContents(0, result);
					inputSlot.getStackInSlot(0).setTagCompound(nbt);
					return true;
				}else if(stack.getItem() instanceof ItemHotToolHead) {
					ItemStack result = getNextToolHead(stack);
	        		System.out.println("Next tool head is: " + getNextToolHead(stack).getDisplayName());
	        		if(result.getItem() instanceof ItemHotToolHead && !(result.isItemEqualIgnoreDurability(ItemStack.EMPTY) && result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD)) || result.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD)))){
	        			NBTTagCompound list = new NBTTagCompound();
	        			list = stack.getTagCompound();
	        			int heat = stack.getItemDamage() + 2800;
	        			if(heat > 6000) {
	        				heat = 6000;
	        			}
	        			result.setItemDamage(heat);
	        			inputSlot.decrStackSize(0, 1);
	        			inputSlot.setInventorySlotContents(0, result);
	        			nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack, nbt));
	        			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack, nbt) * 100));
	                	nbt.setString("Material", list.getString("Material"));
	                	inputSlot.getStackInSlot(0).setTagCompound(nbt);
	        			return true;
	        		}else {
	        			return false;
	        		}
				}
			}
    		return false;
    	}
	}
	
	private double checkHeat(ItemStack stack, NBTTagCompound nbt) {
		int heat = stack.getItemDamage();
		if(inputSlot.getStackInSlot(0).getItem() instanceof ItemHotToolHead) {
			if(heat <= 2999) {
				System.out.println("adding 0.0");
				return 0.0D;
			}else if(heat >= 2999 && heat <= 4999) {
				System.out.println("adding 0.25");
				return 0.25D;
			}else {
				System.out.println("adding 0.5");
				return 0.5D;
			}
		}
		return 0.0D;
	}
	
	private ItemStack getNextToolHead(ItemStack stack) {
		ItemStack newStack = stack.copy();
		
		for(int i = 0; i < toolHeadArr.length; i++) {
			if(newStack.isItemEqualIgnoreDurability(toolHeadArr[i])) {
				if(i != 1 && i != 6 && i != 9 && i != 13 && i != 16 && i != 21 && i != 25 && i != 29 && i != 34 && i != 39 && i != 43 && i != 47 && i != 50 && i != 52 && (i + 1) < toolHeadArr.length) {
					return toolHeadArr[i + 1];
				}else {
					if(i >= toolHeadArr.length) {
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

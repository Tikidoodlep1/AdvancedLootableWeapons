package com.tiki.advancedlootableweapons.inventory;

import java.util.HashMap;

import com.tiki.advancedlootableweapons.Alw;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.tools.ToolSlashSword;
import com.tiki.advancedlootableweapons.tools.ToolStabSword;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
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
	
    public final IInventory inputSlot;
    private boolean slot1Enabled, slot2Enabled;
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
        this.inputSlot = new InventoryBasic("Forge Weapon", true, 4);
        this.world = worldIn;
        this.buttonPressed = -1;
        this.slot1Enabled = false;
        this.slot2Enabled = false;
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
        
        this.addSlotToContainer(new Slot(this.inputSlot, 1, 0, 0) {
        	public boolean isItemValid(ItemStack stack)
            {
        		if(slot1Enabled == false) {
        			return false;
        		}else {
        			if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))) {
        				return true;
        			}else {
        				return false;
        			}
        		}
            }
        	
        	public int getSlotStackLimit() {
        		return 1;
        	}
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
        		inputSlot.setInventorySlotContents(3, ItemStack.EMPTY);
                return stack;
            }
        });
        
        this.addSlotToContainer(new Slot(this.inputSlot, 2, -20, -20) {
        	public boolean isItemValid(ItemStack stack)
            {
        		if(slot2Enabled == false) {
        			return false;
        		}else {
        			if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))) {
        				return true;
        			}else {
        				return false;
        			}
        		}
            }
        	
        	public int getSlotStackLimit() {
        		return 1;
        	}
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
        		inputSlot.setInventorySlotContents(3, ItemStack.EMPTY);
                return stack;
            }
        });
        
        this.addSlotToContainer(new Slot(this.inputSlot, 3, 0, 0) {
        	public boolean isItemValid(ItemStack stack)
            {
        		return false;
            }
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
                return stack;
            }
        });
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 80, 33) {
        	public boolean isItemValid(ItemStack stack)
            {
        		if(stack.getItem() instanceof ItemHotToolHead || ingots.containsKey(getItemString(stack))) {
        			if(!(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_HEAD)))) {
        				if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
        					addSlots(1);
        					System.out.println("Adding slots for: " + 1);
        				}else {
        					for(int j = 0; j < toolHeadArr.length; j++) {
        						if(stack.isItemEqualIgnoreDurability(toolHeadArr[j])) {
        							addSlots(j);
        							System.out.println("Adding slots for: " + j);
        							break;
        						}else {
        							addSlots(-1);
        						}
        					}
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
        	
        	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
            {
        		thePlayer.inventory.placeItemBackInInventory(worldIn, inputSlot.removeStackFromSlot(1));
        		thePlayer.inventory.placeItemBackInInventory(worldIn, inputSlot.removeStackFromSlot(2));
        		addSlots(-1);
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
	
	private void checkItem(ItemStack stack) {
		System.out.println("is slot 1 equal to a hot tood rod: " + (inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD))));
		System.out.println("Slot 1 is: " + inputSlot.getStackInSlot(1).getDisplayName());
		if(slot1Enabled == true && (inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.HOT_TOOL_ROD_2)) || inputSlot.getStackInSlot(1).isItemEqualIgnoreDurability(new ItemStack(ItemInit.LONG_TOOL_ROD)))) {
			ItemStack result;
			ItemStack input = inputSlot.getStackInSlot(0);
			ItemStack toolRod = inputSlot.getStackInSlot(1);
			NBTTagCompound nbt = new NBTTagCompound();
			NBTTagCompound nbt2 = new NBTTagCompound();
			NBTTagCompound list = new NBTTagCompound();
			NBTTagCompound list2 = new NBTTagCompound();
			String material, material2;
			nbt.setString("Material", nbt.getString("Material"));
			nbt.setDouble("reducedDamage", nbt.getDouble("reducedDamage"));
			nbt.setInteger("reducedDurability", nbt.getInteger("reducedDurability"));
			nbt2.setString("Material", nbt2.getString("Material"));
			nbt2.setDouble("reducedDamage", nbt2.getDouble("reducedDamage"));
			nbt2.setInteger("reducedDurability", nbt2.getInteger("reducedDurability"));
			
			for(int j = 0; j < toolHeadArr.length; j++) {
				if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD)) || stack.isItemEqualIgnoreDurability(toolHeadArr[j]) && (j == 1 || j == 6 || j == 9 || j == 13 || j == 16 || j == 21 || j == 25 || j == 29 || j == 34 || j == 39 || j == 43 || j == 47 || j == 50 || j == 52)) {
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
					nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + list2.getDouble("reducedDamage"));
					nbt.setInteger("reducedDurabiity", list.getInteger("reducedDurability") + list2.getInteger("reducedDurability"));
					System.out.println("Are Materials the same: " + material.equalsIgnoreCase(material2) + ", individual materials are: " + material + ", " + material2);
					if(material.equalsIgnoreCase(material2)){
						switch(material) {
							case "Iron":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_IRON);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_IRON);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_IRON);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Kobold":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_KOBOLD);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_KOBOLD);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_KOBOLD);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Copper":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_COPPER);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_COPPER);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_COPPER);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Silver":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_SILVER);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_SILVER);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_SILVER);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Bronze":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_BRONZE);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_BRONZE);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_BRONZE);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Platinum":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_PLATINUM);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_PLATINUM);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Steel":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_STEEL);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_STEEL);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Shadow_Platinum":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_SHADOW_PLATINUM);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_SHADOW_PLATINUM);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_SHADOW_PLATINUM);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Frost_Steel":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_FROST_STEEL);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_FROST_STEEL);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_FROST_STEEL);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Obsidian":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_OBSIDIAN);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_OBSIDIAN);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_OBSIDIAN);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Crystallite":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_CRYSTALLITE);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_CRYSTALLITE);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_CRYSTALLITE);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							case "Dusksteel":
								if(getWeapon(j).equalsIgnoreCase("dagger")) {
									result = new ItemStack(ItemInit.DAGGER_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("kabutowari")) {
									result = new ItemStack(ItemInit.KABUTOWARI_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("rapier")) {
									result = new ItemStack(ItemInit.RAPIER_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("talwar")) {
									result = new ItemStack(ItemInit.TALWAR_DUSKSTEEL);
								}else if(stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD))) {
									result = new ItemStack(ItemInit.CLEAVER_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("mace")) {
									result = new ItemStack(ItemInit.MACE_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("staff")) {
									result = new ItemStack(ItemInit.STAFF_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("spear")) {
									result = new ItemStack(ItemInit.SPEAR_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("longsword")) {
									result = new ItemStack(ItemInit.LONGSWORD_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("kodachi")) {
									result = new ItemStack(ItemInit.KODACHI_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("battleaxe")) {
									result = new ItemStack(ItemInit.BATTLEAXE_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("zweihander")) {
									result = new ItemStack(ItemInit.ZWEIHANDER_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("nodachi")) {
									result = new ItemStack(ItemInit.NODACHI_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("sabre")) {
									result = new ItemStack(ItemInit.SABRE_DUSKSTEEL);
								}else if(getWeapon(j).equalsIgnoreCase("makhaira")) {
									result = new ItemStack(ItemInit.MAKHAIRA_DUSKSTEEL);
								}else {
									result = ItemStack.EMPTY;
								}
								break;
							default:
								System.out.println("material not found, can't make a weapon");
								result = ItemStack.EMPTY;
						}
						if(result != ItemStack.EMPTY) {
							inputSlot.setInventorySlotContents(3, result);
							inputSlot.getStackInSlot(3).setTagCompound(nbt);
							if(getWeapon(j) == "dagger" || getWeapon(j) == "kabutowari" || getWeapon(j) == "rapier" || getWeapon(j) == "talwar" || stack.isItemEqualIgnoreDurability(new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD)) || getWeapon(j) == "mace" || getWeapon(j) == "staff" || getWeapon(j) == "spear") {
								((ToolStabSword)inputSlot.getStackInSlot(3).getItem()).setMaximumDamage(result, (list.getInteger("reducedDurability") + list2.getInteger("reducedDurability")));
								((ToolStabSword)inputSlot.getStackInSlot(3).getItem()).generateNameAndModifiers(inputSlot.getStackInSlot(3), (list.getDouble("reducedDamage") + list2.getDouble("reducedDamage")));
							}else {
								((ToolSlashSword)inputSlot.getStackInSlot(3).getItem()).setMaximumDamage(result, (list.getInteger("reducedDurability") + list2.getInteger("reducedDurability")));
								((ToolSlashSword)inputSlot.getStackInSlot(3).getItem()).generateNameAndModifiers(inputSlot.getStackInSlot(3), (list.getDouble("reducedDamage") + list2.getDouble("reducedDamage")));
							}
							inputSlot.setInventorySlotContents(0, ItemStack.EMPTY);
							inputSlot.setInventorySlotContents(1, ItemStack.EMPTY);
						}
					}
				}
			}
		}
	}
	
	private void addSlots(int toolHead) {
		System.out.println("Attempting to add slots for: " + toolHead);
		if(toolHead == 1 || toolHead == 6 || toolHead == 9 || toolHead == 13 || toolHead == 16 || toolHead == 21 || toolHead == 25 || toolHead == 29 || toolHead == 34 || toolHead == 39 || toolHead == 43 || toolHead == 47 || toolHead == 50) {
			this.getSlotFromInventory(this.inputSlot, 1).xPos = 80;
			this.getSlotFromInventory(this.inputSlot, 1).yPos = 51;
			this.getSlotFromInventory(this.inputSlot, 3).xPos = 108;
			this.getSlotFromInventory(this.inputSlot, 3).yPos = 33;
			slot1Enabled = true;
		}else if(toolHead == 52) {
			this.getSlotFromInventory(this.inputSlot, 1).xPos = 80;
			this.getSlotFromInventory(this.inputSlot, 1).yPos = 51;
			this.getSlotFromInventory(this.inputSlot, 2).xPos = 80;
			this.getSlotFromInventory(this.inputSlot, 2).yPos = 69;
			this.getSlotFromInventory(this.inputSlot, 3).xPos = 108;
			this.getSlotFromInventory(this.inputSlot, 3).yPos = 33;
			slot1Enabled = true;
			slot2Enabled = true;
		}else {
			this.getSlotFromInventory(this.inputSlot, 1).xPos = 0;
			this.getSlotFromInventory(this.inputSlot, 1).yPos = 0;
			this.getSlotFromInventory(this.inputSlot, 2).xPos = 0;
			this.getSlotFromInventory(this.inputSlot, 2).yPos = 0;
			this.getSlotFromInventory(this.inputSlot, 3).xPos = 0;
			this.getSlotFromInventory(this.inputSlot, 3).yPos = 0;
			slot1Enabled = false;
			slot2Enabled = false;
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
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int)(checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 1) {
        		ItemStack changeStack = new ItemStack(ItemInit.KABUTOWARI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 2) {
        		ItemStack changeStack = new ItemStack(ItemInit.TALWAR_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 3) {
        		ItemStack changeStack = new ItemStack(ItemInit.RAPIER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 4) {
        		ItemStack changeStack = new ItemStack(ItemInit.MACE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 5) {
        		ItemStack changeStack = new ItemStack(ItemInit.CLEAVER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 6) {
        		ItemStack changeStack = new ItemStack(ItemInit.STAFF_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 7) {
        		ItemStack changeStack = new ItemStack(ItemInit.LONGSWORD_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 8) {
        		ItemStack changeStack = new ItemStack(ItemInit.KODACHI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 9) {
        		ItemStack changeStack = new ItemStack(ItemInit.BATTLEAXE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 10) {
        		ItemStack changeStack = new ItemStack(ItemInit.ZWEIHANDER_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 11) {
        		ItemStack changeStack = new ItemStack(ItemInit.NODACHI_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 12) {
        		ItemStack changeStack = new ItemStack(ItemInit.SABRE_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 13) {
        		ItemStack changeStack = new ItemStack(ItemInit.MAKHAIRA_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
        		nbt.setString("Material", list.getString("Material"));
        		inputSlot.getStackInSlot(0).setTagCompound(nbt);
        	}else if(weapon == 14) {
        		ItemStack changeStack = new ItemStack(ItemInit.SPEAR_HOT_TOOL_HEAD);
        		changeStack.setItemDamage(heat);
        		inputSlot.decrStackSize(0, 1);
        		inputSlot.setInventorySlotContents(0, changeStack);
        		nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
    			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
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
				checkItem(stack);
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
	        			nbt.setDouble("reducedDamage", list.getDouble("reducedDamage") + checkHeat(stack));
	        			nbt.setInteger("reducedDurability", list.getInteger("reducedDurability") + (int) (checkHeat(stack) * 100));
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
	
	private String getWeapon(int weaponInt) {
		switch(weaponInt) {
			case 1:
				return "dagger";
			case 6:
				return "kabutowari";
			case 9:
				return "talwar";
			case 13:
				return "rapier";
			case 16:
				return "mace";
			case 21:
				return "staff";
			case 25:
				return "longsword";
			case 29:
				return "kodachi";
			case 34:
				return "battleaxe";
			case 39:
				return "zweihander";
			case 43:
				return "nodachi";
			case 47:
				return "sabre";
			case 50:
				return "makhaira";
			case 52:
				return "spear";
			default:
				return "";
		}	
	}
	
	private double checkHeat(ItemStack stack) {
		int heat = stack.getItemDamage();
		if(inputSlot.getStackInSlot(0).getItem() instanceof ItemHotToolHead) {
			if(heat <= 2999) {
				return 0.0D;
			}else if(heat >= 2999 && heat <= 4999) {
				return 0.25D;
			}else {
				return 0.5D;
			}
		}
		return 0.0D;
	}
	
	private ItemStack getNextToolHead(ItemStack stack) {
		ItemStack newStack = stack.copy();
		
		for(int i = 0; i < toolHeadArr.length; i++) {
			if(newStack.isItemEqualIgnoreDurability(toolHeadArr[i])) {
				if(i != 1 && i != 6 && i != 9 && i != 13 && i != 16 && i != 21 && i != 25 && i != 29 && i != 34 && i != 39 && i != 43 && i != 47 && i != 50 && i != 52 && i != 54 && (i + 1) < toolHeadArr.length) {
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

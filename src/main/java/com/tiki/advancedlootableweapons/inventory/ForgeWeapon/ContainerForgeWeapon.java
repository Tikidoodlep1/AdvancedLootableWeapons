package com.tiki.advancedlootableweapons.inventory.ForgeWeapon;

import java.util.Random;

import javax.annotation.Nullable;

import com.tiki.advancedlootableweapons.handlers.ConfigHandler;
import com.tiki.advancedlootableweapons.init.ItemInit;
import com.tiki.advancedlootableweapons.items.ItemArmorBinding;
import com.tiki.advancedlootableweapons.items.ItemHotToolHead;
import com.tiki.advancedlootableweapons.items.ItemUnboundArmor;
import com.tiki.advancedlootableweapons.recipes.ForgeArmorBindingRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeArmorPlateRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolHeadRecipe;
import com.tiki.advancedlootableweapons.recipes.ForgeToolRecipe;
import com.tiki.advancedlootableweapons.tools.ToolForgeHammer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerForgeWeapon extends Container {
	
    public final IInventory inputSlot;
    private int toolForgeHammer;
    private final World world;
    private int buttonPressed;
    private EntityPlayer player;
    public static final int TOOL_ROD_BUTTON = 98;
    public static final int HAMMER_BUTTON = 99;
    protected IRecipe recipe = null;
    private final BlockPos pos;
    private InventoryCrafting invCraft = new InventoryCrafting(this, 3, 1);
    
    @SideOnly(Side.CLIENT)
    public ContainerForgeWeapon(InventoryPlayer playerInventory, World worldIn)
    {
        this(playerInventory, worldIn, BlockPos.ORIGIN);
        this.buttonPressed = -1;
    }
    
	public ContainerForgeWeapon(InventoryPlayer playerInventory, final World worldIn, BlockPos pos)
    {
		this.pos = pos;
        this.inputSlot = new InventoryBasic("Forge Weapon", true, 3);
        this.player = playerInventory.player;
        this.world = worldIn;
        this.buttonPressed = -1;
                
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
        
        this.addSlotToContainer(new Slot(this.inputSlot, 0, 56, 33) {
        	public boolean isItemValid(ItemStack stack)
            {
        		if(stack.getItem() instanceof ItemHotToolHead || stack.getItem() instanceof ItemUnboundArmor || ItemInit.acceptedForgeItems.contains(stack.getItem())) {
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
        		if(stack.getItem() instanceof ItemHotToolHead || stack.getItem() instanceof ItemArmorBinding || ItemInit.acceptedForgeItems.contains(stack.getItem())) {// || ingotMap.containsKey(stack.getItem())) {
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
	
	@Override
	public boolean enchantItem(EntityPlayer playerIn, int id)
    {
		this.buttonPressed = id;
		
		if(toolForgeHammer > -1 && craftItem()) {
			playerIn.getFoodStats().addExhaustion(ConfigHandler.FORGING_EXHAUSTION);
			this.buttonPressed = -1;
        	return true;
		}else {
			this.buttonPressed = -1;
			return false;
		}
    }
	
	public boolean craftItem() {
		for(int i = 0; i < this.inputSlot.getSizeInventory(); i++) {
			this.invCraft.setInventorySlotContents(i, this.inputSlot.getStackInSlot(i));
		}
		recipe = this.findMatchingRecipe(this.invCraft, this.world);
		
//		if(recipe != null) {
//			System.out.println("Recipe Name: " + recipe.getRegistryName().toString());
//			System.out.println("Ingredients:" );
//			for(Ingredient i : recipe.getIngredients()) {
//				System.out.println(Arrays.toString(i.getMatchingStacks()));
//			}
//			System.out.println("Result: " + recipe.getRecipeOutput());
//		}else {
//			System.out.println("Recipe is NULL!");
//		}
		
		ItemStack outputSlot = this.inputSlot.getStackInSlot(2);
		if(recipe != null && outputSlot == ItemStack.EMPTY && this.getCanCraft(this.player)) {
			if(recipe instanceof ForgeToolHeadRecipe) {
				ForgeToolHeadRecipe headRecipe = ((ForgeToolHeadRecipe)this.recipe);
				if(getButtonIdFromName(headRecipe.getButton()) == this.buttonPressed) {
					ItemStack result = headRecipe.getCraftingResult(this.invCraft);
					
					NonNullList<ItemStack> remaining = headRecipe.getRemainingItems(this.invCraft);
					for(int i = 0; i < remaining.size(); i++) {
						this.inputSlot.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.inputSlot.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(1);
					this.giveExp(headRecipe.getExp());
					this.detectAndSendChanges();
					return true;
				}
			}else if(recipe instanceof ForgeToolRecipe) {
				ForgeToolRecipe toolRecipe = ((ForgeToolRecipe)this.recipe);
				if(getButtonIdFromName(toolRecipe.getButton()) == this.buttonPressed) {
					ItemStack result = toolRecipe.getCraftingResult(this.invCraft);
					
					NonNullList<ItemStack> remaining = toolRecipe.getRemainingItems(this.invCraft);
					for(int i = 0; i < remaining.size(); i++) {
						this.inputSlot.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.inputSlot.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(1);
					this.giveExp(toolRecipe.getExp());
					this.detectAndSendChanges();
					return true;
				}
			}else if(recipe instanceof ForgeArmorPlateRecipe) {
				ForgeArmorPlateRecipe toolRecipe = ((ForgeArmorPlateRecipe)this.recipe);
				if(getButtonIdFromName(toolRecipe.getButton()) == this.buttonPressed) {
					ItemStack result = toolRecipe.getCraftingResult(this.invCraft);
					
					NonNullList<ItemStack> remaining = toolRecipe.getRemainingItems(this.invCraft);
					for(int i = 0; i < remaining.size(); i++) {
						this.inputSlot.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.inputSlot.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(1);
					this.giveExp(toolRecipe.getExp());
					this.detectAndSendChanges();
					return true;
				}
			}else if(recipe instanceof ForgeArmorBindingRecipe) {
				ForgeArmorBindingRecipe bindingRecipe = ((ForgeArmorBindingRecipe)this.recipe);
				if(getButtonIdFromName(bindingRecipe.getButton()) == this.buttonPressed) {
					ItemStack result = bindingRecipe.getCraftingResult(this.invCraft);
					
					NonNullList<ItemStack> remaining = bindingRecipe.getRemainingItems(this.invCraft);
					for(int i = 0; i < remaining.size(); i++) {
						this.inputSlot.setInventorySlotContents(i, remaining.get(i));
					}
					
					this.inputSlot.setInventorySlotContents(2, result);
					Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_USE, 0.8F, 1.0F);
					this.damageForgeHammer(1);
					this.detectAndSendChanges();
					return true;
				}
			}
		}
		return false;
	}
	
	private void giveExp(int exp) {
		this.player.addExperience(exp);
		System.out.println("Giving " + exp + " from Forge Weapons");
//		NetHandlerPlayClient clientHandler = Minecraft.getMinecraft().getConnection();
//		SPacketSpawnExperienceOrb packet = new SPacketSpawnExperienceOrb(new EntityXPOrb(this.world, this.player.posX, this.player.posY, this.player.posZ, exp));
//		Minecraft.getMinecraft().addScheduledTask(() -> packet.processPacket(clientHandler));
	}
	
	private void damageForgeHammer(int amount) {
		ItemStack forgeHammer = player.inventory.getStackInSlot(toolForgeHammer);
		if(forgeHammer.attemptDamageItem(amount, new Random(), null)) {
			player.inventory.deleteStack(forgeHammer);
			Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
			this.toolForgeHammer = -1;
		}
	}
	
	@Nullable
    private IRecipe findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn)
    {
        for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof ForgeToolHeadRecipe)
            {
            	if( getButtonIdFromName(((ForgeToolHeadRecipe)irecipe).getButton()) == this.buttonPressed && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
            else if (irecipe instanceof ForgeToolRecipe)
            {
            	if( getButtonIdFromName(((ForgeToolRecipe)irecipe).getButton()) == this.buttonPressed && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
            else if (irecipe instanceof ForgeArmorPlateRecipe)
            {
            	if( getButtonIdFromName(((ForgeArmorPlateRecipe)irecipe).getButton()) == this.buttonPressed && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
            else if (irecipe instanceof ForgeArmorBindingRecipe)
            {
            	if( getButtonIdFromName(((ForgeArmorBindingRecipe)irecipe).getButton()) == this.buttonPressed && irecipe.matches(craftMatrix, worldIn)) {
            		return irecipe;
            	}
            }
        }

        return null;
    }
	
	public static int getButtonIdFromName(String button) {
		if(button.equalsIgnoreCase("dagger")) {
			return 0;
		}else if(button.equalsIgnoreCase("kabutowari")) {
			return 1;
		}else if(button.equalsIgnoreCase("talwar")) {
			return 2;
		}else if(button.equalsIgnoreCase("rapier")) {
			return 3;
		}else if(button.equalsIgnoreCase("mace")) {
			return 4;
		}else if(button.equalsIgnoreCase("cleaver")) {
			return 5;
		}else if(button.equalsIgnoreCase("staff")) {
			return 6;
		}else if(button.equalsIgnoreCase("longsword")) {
			return 7;
		}else if(button.equalsIgnoreCase("kodachi")) {
			return 8;
		}else if(button.equalsIgnoreCase("battleaxe")) {
			return 9;
		}else if(button.equalsIgnoreCase("zweihander")) {
			return 10;
		}else if(button.equalsIgnoreCase("nodachi")) {
			return 11;
		}else if(button.equalsIgnoreCase("sabre")) {
			return 12;
		}else if(button.equalsIgnoreCase("makhaira")) {
			return 13;
		}else if(button.equalsIgnoreCase("spear")) {
			return 14;
		}else if(button.equalsIgnoreCase("chain")) {
			return 15;
		}else if(button.equalsIgnoreCase("armor plate")) {
			return 16;
		}else if(button.equalsIgnoreCase("tool handle")) {
			return 98;
		}else if(button.equalsIgnoreCase("forge weapon")) {
			return 99;
		}
		return -1;
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
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
    	//System.out.println((playerIn.getDistanceSq(this.pos) <= 64.0D) + ", " + ConfigHandler.VALID_ANVILS.contains(playerIn.world.getBlockState(this.pos).getBlock().getRegistryName().toString()));
    	if(playerIn.getDistanceSq(this.pos) <= 64.0D && ConfigHandler.VALID_ANVILS.contains(playerIn.world.getBlockState(this.pos).getBlock().getRegistryName().toString())) {
    		return true;
    	}
        return false;
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
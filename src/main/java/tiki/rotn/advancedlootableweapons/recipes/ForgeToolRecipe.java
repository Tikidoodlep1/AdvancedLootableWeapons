package tiki.rotn.advancedlootableweapons.recipes;

import java.util.Map.Entry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tiki.rotn.advancedlootableweapons.ModInfo;
import tiki.rotn.advancedlootableweapons.handlers.ConfigHandler;
import tiki.rotn.advancedlootableweapons.init.ItemInit;
import tiki.rotn.advancedlootableweapons.items.ItemHotToolHead;
import tiki.rotn.advancedlootableweapons.tools.ToolSlashSword;
import tiki.rotn.advancedlootableweapons.tools.ToolStabSword;

public class ForgeToolRecipe extends ShapelessOreRecipe {
	
	private final NonNullList<Ingredient> inputs;
	private final String button;
	private final int exp;
	private final String weaponType;
	private ItemStack material = null;
	public final Block block;
	
	public ForgeToolRecipe(final String button, final NonNullList<Ingredient> inputs, int exp, String result, Block block) {
		super(null, inputs, new ItemStack(getWeaponFromString(result)).setStackDisplayName("Example Output"));
		this.inputs = inputs;
		this.button = button;
		this.exp = exp;
		this.weaponType = result.toLowerCase();
		this.block = block;
	}
	
	public ForgeToolRecipe(final String button, final NonNullList<Ingredient> inputs, int exp, ItemStack result, Block block) {
		super(null, inputs, result);
		this.inputs = inputs;
		this.button = button;
		this.exp = exp;
		this.weaponType = "";
		this.block = block;
	}
	
	private ItemStack getModifiedOutput() {
//		System.out.println("Material Registry Name: " + this.material.getItem().getRegistryName());
//		System.out.println("Forge Registry Contains " + (ModInfo.ID + ":" + weaponType + "_" + material.getItem().getRegistryName().getResourcePath()) + ": " + (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_" + material.getItem().getRegistryName().getResourcePath()))) );
		if(material != null && material.getItem().getRegistryName().getResourceDomain().equals("advancedlootableweapons") || material.getItem() == Items.IRON_INGOT) {
			if(material.getItem() == ItemInit.INGOT_BRONZE) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_bronze")));
			}else if(material.getItem() == ItemInit.INGOT_COPPER) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_copper")));
			}else if(material.getItem() == ItemInit.INGOT_CRYSTALLITE) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_crystallite")));
			}else if(material.getItem() == ItemInit.INGOT_DUSKSTEEL) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_dusksteel")));
			}else if(material.getItem() == ItemInit.INGOT_FROST_STEEL) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_frost_steel")));
			}else if(material.getItem() == ItemInit.INGOT_KOBOLD) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_kobold")));
			}else if(material.getItem() == ItemInit.INGOT_OBSIDIAN) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_obsidian")));
			}else if(material.getItem() == ItemInit.INGOT_PLATINUM) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_platinum")));
			}else if(material.getItem() == ItemInit.INGOT_SHADOW_PLATINUM) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_shadow_platinum")));
			}else if(material.getItem() == ItemInit.INGOT_SILVER) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_silver")));
			}else if(material.getItem() == ItemInit.INGOT_STEEL) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_steel")));
			}else if(material.getItem() == Items.IRON_INGOT) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_iron")));
			}
		}
		
		if(material.getItem() != null) {
			if(ForgeRegistries.ITEMS.containsKey(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_" + material.getItem().getRegistryName().getResourcePath()))) {
				return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_" + material.getItem().getRegistryName().getResourcePath())));
			}
			
			for(Entry<ToolMaterial, ItemStack> e : ItemInit.customRepairItems.entrySet()) {
				if(e.getValue().getItem() == material.getItem() && e.getValue().getItemDamage() == material.getItemDamage()) {
					return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_" + e.getKey().name().replace(':', '_'))));
				}
			}
			
			for(ToolMaterial mat : ToolMaterial.values()) {
				if(mat.getRepairItemStack().getItem() == material.getItem() && mat.getRepairItemStack().getItemDamage() == material.getItemDamage()) {
					return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ModInfo.ID + ":" + weaponType + "_" + mat.name().replace(':', '_'))));
				}
			}
		}
		
		return this.output.copy();
	}
	
	public static Item getWeaponFromString(String weapon) {
		switch(weapon) {
		case "battleaxe":
			return ItemInit.BATTLEAXE_IRON;
		case "cleaver":
			return ItemInit.CLEAVER_IRON;
		case "dagger":
			return ItemInit.DAGGER_IRON;
		case "kabutowari":
			return ItemInit.KABUTOWARI_IRON;
		case "kodachi":
			return ItemInit.KODACHI_IRON;
		case "longsword":
			return ItemInit.LONGSWORD_IRON;
		case "mace":
			return ItemInit.MACE_IRON;
		case "makhaira":
			return ItemInit.MAKHAIRA_IRON;
		case "nodachi":
			return ItemInit.NODACHI_IRON;
		case "rapier":
			return ItemInit.RAPIER_IRON;
		case "sabre":
			return ItemInit.SABRE_IRON;
		case "spear":
			return ItemInit.SPEAR_IRON;
		case "staff":
			return ItemInit.STAFF_IRON;
		case "talwar":
			return ItemInit.TALWAR_IRON;
		case "zweihander":
			return ItemInit.ZWEIHANDER_IRON;
		}
		return ItemInit.DAGGER_IRON;
	}
	
	public NonNullList<ItemStack> getRemainingItems(final NonNullList<ItemStack> inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.size(), ItemStack.EMPTY);
		
		if(inputs.get(0) == Ingredient.EMPTY) {
			remainingItems.set(0, inventoryCrafting.get(0));
		}
		if(inputs.get(1) == Ingredient.EMPTY) {
			remainingItems.set(1, inventoryCrafting.get(1));
		}
		return remainingItems;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
		if(inputs.get(0) == Ingredient.EMPTY) {
			remainingItems.set(0, inventoryCrafting.getStackInSlot(0));
		}
		if(inputs.get(1) == Ingredient.EMPTY) {
			remainingItems.set(1, inventoryCrafting.getStackInSlot(1));
		}
		return remainingItems;
	}
	
	public boolean matches(NonNullList<ItemStack> inv, World world) {		
		if(inv.size() != 3) {
			return false;
		}
		
		boolean match1 = false;
		boolean match2 = false;
		boolean matsMatch = false;
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		NBTTagCompound tag1 = input1.getTagCompound();
		NBTTagCompound tag2 = input2.getTagCompound();
		
		if(this.inputs.get(0) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(0).getMatchingStacks()) {
				if(input1.getItem() == stack.getItem()) {
					if(ConfigHandler.ENABLE_QUENCHING) {
						if(input1.hasTagCompound() && input1.getTagCompound().getBoolean("quenched")) {
							match1 = true;
						}
					}else {
						match1 = true;
					}
					break;
				}
			}
		}else {
			match1 = true;
			matsMatch = true;
		}
		
		if(this.inputs.get(1) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(1).getMatchingStacks()) {
				if(input2.getItem() == stack.getItem()) {
					match2 = true;
					break;
				}
			}
		}else {
			match2 = true;
			matsMatch = true;
		}
		
		if(!input1.isEmpty() && input1.getItem() instanceof ItemHotToolHead) {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(tag1.hasKey("Material") && tag2.hasKey("Material")) {
					if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == new ItemStack(tag2.getCompoundTag("Material")).getItem()) {
						matsMatch = true;
					}
				}else {
					//Mats don't match if the hot tool heads don't have mats
					return false;
				}
			}else {
				if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == input2.getItem()) {
					matsMatch = true;
				}
			}
		}else {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(new ItemStack(tag2.getCompoundTag("Material")).getItem() == input1.getItem()) {
					matsMatch = true;
				}
			}else {
				if(input1.isEmpty()	|| input2.isEmpty()) {
					matsMatch = true;
				}else {
					if(input1.getItem() == input2.getItem()) {
						matsMatch = true;
					}
				}
			}
		}
		
		//System.out.println("Match1: " + match1 + ", Match2: " + match2 + ", matsMatch: " + matsMatch);
		return match1 && match2 && matsMatch;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World world) {		
		if(inv.getSizeInventory() != 3) {
			return false;
		}
		
		boolean match1 = false;
		boolean match2 = false;
		boolean matsMatch = false;
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		NBTTagCompound tag1 = input1.getTagCompound();
		NBTTagCompound tag2 = input2.getTagCompound();
		
		if(this.inputs.get(0) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(0).getMatchingStacks()) {
				if(input1.getItem() == stack.getItem()) {
					if(ConfigHandler.ENABLE_QUENCHING) {
						if(input1.hasTagCompound() && input1.getTagCompound().getBoolean("quenched")) {
							match1 = true;
						}
					}else {
						match1 = true;
					}
					break;
				}
			}
		}else {
			match1 = true;
			matsMatch = true;
		}
		
		if(this.inputs.get(1) != Ingredient.EMPTY) {
			for(ItemStack stack : this.inputs.get(1).getMatchingStacks()) {
				if(input2.getItem() == stack.getItem()) {
					match2 = true;
					break;
				}
			}
		}else {
			match2 = true;
			matsMatch = true;
		}
		
		if(!input1.isEmpty() && input1.getItem() instanceof ItemHotToolHead) {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(tag1.hasKey("Material") && tag2.hasKey("Material")) {
					if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == new ItemStack(tag2.getCompoundTag("Material")).getItem()) {
						matsMatch = true;
					}
				}else {
					//Mats don't match if the hot tool heads don't have mats
					return false;
				}
			}else {
				if(new ItemStack(tag1.getCompoundTag("Material")).getItem() == input2.getItem()) {
					matsMatch = true;
				}
			}
		}else {
			if(!input2.isEmpty() && input2.getItem() instanceof ItemHotToolHead) {
				if(new ItemStack(tag2.getCompoundTag("Material")).getItem() == input1.getItem()) {
					matsMatch = true;
				}
			}else {
				if(input1.isEmpty()	|| input2.isEmpty()) {
					matsMatch = true;
				}else {
					if(input1.getItem() == input2.getItem()) {
						matsMatch = true;
					}
				}
			}
		}
		
		//System.out.println("Match1: " + match1 + ", Match2: " + match2 + ", matsMatch: " + matsMatch);
		return match1 && match2 && matsMatch;
	}
	
	public ItemStack getCraftingResult(final NonNullList<ItemStack> inv) {
		ItemStack input1 = inv.get(0);
		ItemStack input2 = inv.get(1);
		NBTTagCompound input1Tag = input1.getTagCompound();
		NBTTagCompound input2Tag = input2.getTagCompound();
		this.setMaterial(input1, input2);
		//System.out.println("Material: " + this.material);
		ItemStack result = this.getModifiedOutput();
		double addedDamage = 0D;
		int addedDur = 0;
		
		if(input1Tag == null) {
			input1Tag = new NBTTagCompound();
		}
		
		if(input2Tag != null) {
			addedDamage += input2Tag.getDouble("addedDamage");
			addedDamage += checkHeat(input2);
			
			addedDur += input2Tag.getInteger("addedDurability");
			addedDur += (int)(checkHeat(input2) * 100);
		}
		addedDamage += input1Tag.getDouble("addedDamage");
		addedDamage += checkHeat(input1);
			
		addedDur += input1Tag.getInteger("addedDurability");
		addedDur += (int)(checkHeat(input1) * 100);
		
		result.setTagCompound(input1Tag);
		
		if(result.getItem() instanceof ToolSlashSword) {
			ToolSlashSword sword = ((ToolSlashSword)result.getItem());
			sword.generateNameAndModifiers(result, addedDamage, addedDur, this.material);
		}else if(result.getItem() instanceof ToolStabSword) {
			ToolStabSword sword = ((ToolStabSword)result.getItem());
			sword.generateNameAndModifiers(result, addedDamage, addedDur, this.material);
		}
		
		return result;
	}
	
	@Override
	public ItemStack getCraftingResult(final InventoryCrafting inv) {
		ItemStack input1 = inv.getStackInSlot(0);
		ItemStack input2 = inv.getStackInSlot(1);
		NBTTagCompound input1Tag = input1.getTagCompound();
		NBTTagCompound input2Tag = input2.getTagCompound();
		this.setMaterial(input1, input2);
		//System.out.println("Material: " + this.material);
		ItemStack result = this.getModifiedOutput();
		double addedDamage = 0D;
		int addedDur = 0;
		
		if(input1Tag == null) {
			input1Tag = new NBTTagCompound();
		}
		
		if(input2Tag != null) {
			addedDamage += input2Tag.getDouble("addedDamage");
			addedDamage += checkHeat(input2);
			
			addedDur += input2Tag.getInteger("addedDurability");
			addedDur += (int)(checkHeat(input2) * 100);
		}
		addedDamage += input1Tag.getDouble("addedDamage");
		addedDamage += checkHeat(input1);
			
		addedDur += input1Tag.getInteger("addedDurability");
		addedDur += (int)(checkHeat(input1) * 100);
		
		result.setTagCompound(input1Tag);
		
		if(result.getItem() instanceof ToolSlashSword) {
			ToolSlashSword sword = ((ToolSlashSword)result.getItem());
			sword.generateNameAndModifiers(result, addedDamage, addedDur, this.material);
		}else if(result.getItem() instanceof ToolStabSword) {
			ToolStabSword sword = ((ToolStabSword)result.getItem());
			sword.generateNameAndModifiers(result, addedDamage, addedDur, this.material);
		}
		
		return result;
	}
	
	private void setMaterial(ItemStack input1, ItemStack input2) {
		if(input1.getItem() instanceof ItemHotToolHead) {
			NBTTagCompound tag = input1.getTagCompound();
			if(tag.hasKey("Material")) {
				this.material = new ItemStack(tag.getCompoundTag("Material"));
				return;
			}
		}
		
		if(input2.getItem() instanceof ItemHotToolHead) {
			NBTTagCompound tag = input1.getTagCompound();
			if(tag.hasKey("Material")) {
				this.material = new ItemStack(tag.getCompoundTag("Material"));
				return;
			}
		}
		
		if(!input1.isEmpty()) {
			this.material = input1;
			return;
		}
		
		if(!input2.isEmpty()) {
			this.material = input2;
			return;
		}
	}
	
	public String getButton() {
		return this.button;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	private double checkHeat(ItemStack stack) {
		int heat = stack.getItemDamage();
		
		return map(6000 - heat, 0, 6000, 0.0, 0.5);
	}
	
	private double map(double value, double curRangeLowBound, double curRangeHighBound, double wantRangeLowBound, double wantRangeHighBound) {
		return wantRangeLowBound + (wantRangeHighBound - wantRangeLowBound) * ((value - curRangeLowBound) / (curRangeHighBound - curRangeLowBound));
	}
	
	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			final String button = JsonUtils.getString(json, "button");
			final String block = JsonUtils.getString(json, "block", "");
			
			final NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
			JsonArray ing = JsonUtils.getJsonArray(json, "ingredients");
			if(ing.size() > inputs.size()) {
				throw new JsonParseException("There cannot be more ingredients than slots to input items!");
			}
				
			for(int i = 0; i < ing.size(); i++) {
				JsonElement e = ing.get(i);
				if(e.getAsJsonObject().has("slot")) {
					final JsonElement slot = e.getAsJsonObject().get("slot");
					if(slot.getAsString().equalsIgnoreCase("metal")) {
						inputs.set(i, Ingredient.fromItems(ItemInit.acceptedForgeMetals.toArray(new Item[0])));
					}
				}else {
					inputs.set(i, CraftingHelper.getIngredient(e, context));
				}
			}
			
			final int exp = JsonUtils.getInt(json, "exp");
			final String result = JsonUtils.getString(json, "result");
			
			return new ForgeToolRecipe(button, inputs, exp, result, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(block)));
		}
		
	}
	
	
}

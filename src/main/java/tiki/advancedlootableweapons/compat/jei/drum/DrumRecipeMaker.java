package tiki.advancedlootableweapons.compat.jei.drum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import tiki.advancedlootableweapons.compat.jei.drum.DrumRecipe;
import tiki.advancedlootableweapons.init.BlockInit;
import tiki.advancedlootableweapons.items.ItemHotToolHead;
import tiki.advancedlootableweapons.recipes.DrumItemRecipe;
import tiki.advancedlootableweapons.recipes.DrumQuenchingRecipe;

public class DrumRecipeMaker {

	public static List<DrumRecipe> getRecipes(IJeiHelpers helpers)	{
		List<DrumRecipe> recipes = new ArrayList<DrumRecipe>();
		
		for (IRecipe irecipe : CraftingManager.REGISTRY)
        {
            if (irecipe instanceof DrumItemRecipe)
            {
            	List<List<ItemStack>> stackList = new ArrayList<List<ItemStack>>(4);
    			NonNullList<Ingredient> ingrs = irecipe.getIngredients();
    			for(Ingredient i : ingrs) {
    				stackList.add(Arrays.stream(i.getMatchingStacks()).collect(Collectors.toList()));
    			}
    			List<ItemStack> bucketList = new ArrayList<ItemStack>();
    			bucketList.add(FluidUtil.getFilledBucket(new FluidStack(((DrumItemRecipe)irecipe).getFluid(), 1000)));
    			stackList.add(bucketList);
    			List<ItemStack> drumList = new ArrayList<ItemStack>();
    			drumList.add(new ItemStack(BlockInit.drum));
    			stackList.add(drumList);
    			
    			recipes.add(new DrumRecipe(stackList, irecipe.getRecipeOutput(), ((DrumItemRecipe)irecipe).getTime(), false));
            }
            else if(irecipe instanceof DrumQuenchingRecipe)
            {
            	List<List<ItemStack>> stackList = new ArrayList<List<ItemStack>>(5);
    			NonNullList<Ingredient> ingrs = irecipe.getIngredients();
    			for(Ingredient i : ingrs) {
    				List<ItemStack> items = new ArrayList<ItemStack>();
    				for(ItemStack s : i.getMatchingStacks()) {
    					if(s.getItem() instanceof ItemHotToolHead) {
    						NBTTagCompound tag = s.hasTagCompound() ? s.getTagCompound() : new NBTTagCompound();
    						tag.setBoolean("clay", true);
    						s.setTagCompound(tag);
    						items.add(s);
    					}
    				}
    				stackList.add(items);
    			}
    			List<ItemStack> addList = new ArrayList<ItemStack>();
    			addList.add(ItemStack.EMPTY);
    			stackList.add(addList);
    			List<ItemStack> bucketList = new ArrayList<ItemStack>();
    			bucketList.add(FluidUtil.getFilledBucket(new FluidStack(((DrumQuenchingRecipe)irecipe).getFluid(), 1000)));
    			stackList.add(bucketList);
    			List<ItemStack> drumList = new ArrayList<ItemStack>();
    			drumList.add(new ItemStack(BlockInit.drum));
    			stackList.add(drumList);
    			
    			recipes.add(new DrumRecipe(stackList, irecipe.getRecipeOutput(), ((DrumQuenchingRecipe)irecipe).getTime(), true));
            }
        }
		
		return recipes;
	}
}

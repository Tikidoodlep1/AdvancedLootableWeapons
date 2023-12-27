package tiki.advancedlootableweapons.compat.jei.anvilForgingComplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mezz.jei.api.IJeiHelpers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.init.ItemInit;
import tiki.advancedlootableweapons.items.ItemHotToolHead;
import tiki.advancedlootableweapons.recipes.ForgeToolHeadRecipe;
import tiki.advancedlootableweapons.recipes.ForgeToolRecipe;

public class AnvilForgingComplexRecipeMaker {
	
	public static List<AnvilForgingComplexRecipe> getRecipes(IJeiHelpers helpers) {
		List<AnvilForgingComplexRecipe> jeiRecipes = new ArrayList<AnvilForgingComplexRecipe>();
		long millis = System.currentTimeMillis();
		jeiRecipes.addAll(getRecipesForTools()); // Must call this first so that tool head recipes are not generated for ALL tool heads, and only excess ones.
		Alw.logger.info("Loading weapon recipes took " + (System.currentTimeMillis() - millis) + "ms for size " + CraftingManager.REGISTRY.getKeys().size());
		
		millis = System.currentTimeMillis();
		jeiRecipes.addAll(getRecipesForToolHeads());
		Alw.logger.info("Loading tool head recipes took " + (System.currentTimeMillis() - millis) + "ms for size " + CraftingManager.REGISTRY.getKeys().size());
		
		return jeiRecipes;
	}
	
	public static List<AnvilForgingComplexRecipe> getRecipesForTools() {
		List<AnvilForgingComplexRecipe> jeiRecipes = new ArrayList<AnvilForgingComplexRecipe>();
		List<List<ForgeToolHeadRecipe>> toolHeads = new ArrayList<List<ForgeToolHeadRecipe>>();
		List<ForgeToolRecipe> tools = new ArrayList<ForgeToolRecipe>();
		ItemStack[] matList = null;
		
		for (IRecipe irecipe : CraftingManager.REGISTRY) {
            
            if (irecipe instanceof ForgeToolRecipe) {
            	ForgeToolRecipe recipe = (ForgeToolRecipe)irecipe;
            	ingredients: for(ItemStack stack : recipe.getIngredients().get(0).getMatchingStacks()) {
            		if(stack.getItem() instanceof ItemHotToolHead) {
            			tools.add(recipe);
            			break ingredients;
            		}
            	}
            }
        }
		
		for(int i = 0; i < tools.size(); i++) {
			toolHeads.add(new ArrayList<ForgeToolHeadRecipe>(5));
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
		}
		
		for(ForgeToolRecipe tool : tools) {
			for (IRecipe irecipe : CraftingManager.REGISTRY) {
				if (irecipe instanceof ForgeToolHeadRecipe) {
					ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
					
					ItemStack input1 = recipe.getRecipeOutput();
					
            		for(ItemStack toolInput : tool.getIngredients().get(0).getMatchingStacks()) {
            			if(toolInput.getItem() == input1.getItem()) {
            				
            				int toolIndex = tools.indexOf(tool);
            				toolHeads.get(toolIndex).set(5, recipe);
            			}
            		}
            	}
            	
            }
		}
		
		for(int i = 0; i < 5; i++) {
			
			Alw.logger.debug("Pass " + (i + 1));
			
			branch: for(List<ForgeToolHeadRecipe> branch : toolHeads) {
				int lastIndex = 5;
				for(int j = 5; j >= 0; j--) {
					if(branch.get(j) == null) {
						lastIndex = j + 1;
						break;
					}
				}
				
				Alw.logger.debug("LastIndex is " + lastIndex + ", Branch is " + Arrays.toString(branch.toArray()));
				
				if(lastIndex >= branch.size() || branch.get(lastIndex) == null) {
					continue;
				}
				for (IRecipe irecipe : CraftingManager.REGISTRY) {
					if (irecipe instanceof ForgeToolHeadRecipe) {
						ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
						
						ItemStack input1 = recipe.getRecipeOutput();
						Ingredient last = branch.get(lastIndex).getIngredients().get(0);
	            		for(ItemStack toolInput : last.getMatchingStacks()) {
	            			if(toolInput.getItem() == input1.getItem()) {
	            				branch.set(lastIndex - 1, recipe);
	            				if(input1.getItem() == ItemInit.HOT_TOOL_HEAD) {
	            					matList = recipe.getIngredients().get(0).getMatchingStacks();
	            				}
	            				continue branch;
	            			}
	            		}
	            	}
	            }
				
			}
		}
		
		for(int i = 0; i < tools.size(); i++) {
			ForgeToolRecipe tool = tools.get(i);
			String button = tool.getButton();
			int exp = tool.getExp();
			
			if(matList != null) {
				for(int j = 0; j < matList.length; j++) {
					ArrayList<NonNullList<Ingredient>> ingrs = new ArrayList<NonNullList<Ingredient>>();
					ArrayList<String> buttons = new ArrayList<String>();
					ArrayList<Integer> exps = new ArrayList<Integer>();
					
					Item metal = matList[j].getItem();
					ingrs.add(NonNullList.from(Ingredient.EMPTY, Ingredient.fromItem(metal))); // Adding the repair ingot as the first ingredient
					
					Alw.logger.debug("Metal is " + metal.getRegistryName());
					
					for(int fthr = 0; fthr < toolHeads.get(i).size(); fthr++) { //ForgeToolHeadRecipe heads : toolHeads.get(i)
						ForgeToolHeadRecipe heads = toolHeads.get(i).get(fthr);
						if(heads == null) {
							continue;
						}
						buttons.add(heads.getButton());
						exps.add(heads.getExp());
						
						NBTTagCompound tag = new NBTTagCompound();
						tag.setTag("Material", new ItemStack(metal).serializeNBT());
						
						boolean isBaseHead = heads.getRecipeOutput().getItem() == ItemInit.HOT_TOOL_HEAD;
						NonNullList<Ingredient> headIngrs = NonNullList.create();
						for(int ii = 0; ii < heads.getIngredients().size(); ii++) {
							Ingredient ingr = heads.getIngredients().get(ii);
							if(isBaseHead && ii == 0) {
								continue;
							}
							ItemStack[] matStacks = new ItemStack[ingr.getMatchingStacks().length];
							int m = 0;
							for(ItemStack match : ingr.getMatchingStacks()) {
								if(match.getItem() instanceof ItemHotToolHead) {
									ItemStack matchCopy = match.copy();
									matchCopy.setTagCompound(tag);
									matStacks[m++] = matchCopy;
								}
							}
							
							boolean isMatStacksEmpty = false;
							for(int k = 0; k < matStacks.length; k++) {
								if(matStacks[k] == ItemStack.EMPTY || matStacks[k] == null) {
									isMatStacksEmpty = true;
									break;
								}
							}
							
							if(isMatStacksEmpty) {
								headIngrs.add(ingr);
							}else {
								headIngrs.add(Ingredient.fromStacks(matStacks));
							}
							
						}
						ingrs.add(headIngrs);
					}
					
					boolean isMatStacksEmpty = false;
					ItemStack[] matStacks = new ItemStack[0];
					NonNullList<Ingredient> toolIngrs = NonNullList.create();
					for(Ingredient toolIngr : tool.getIngredients()) {
						
						NBTTagCompound tag = new NBTTagCompound();
						tag.setTag("Material", new ItemStack(metal).serializeNBT());
						
						matStacks = new ItemStack[toolIngr.getMatchingStacks().length];
						int m = 0;
						
						for(ItemStack toolInputStack : toolIngr.getMatchingStacks()) {
							if(toolInputStack.getItem() instanceof ItemHotToolHead) {
								ItemStack matchCopy = toolInputStack.copy();
								matchCopy.setTagCompound(tag);
								matStacks[m++] = matchCopy;
							}
						}
						
						isMatStacksEmpty = false;
						for(int k = 0; k < matStacks.length; k++) {
							if(matStacks[k] == ItemStack.EMPTY || matStacks[k] == null) {
								isMatStacksEmpty = true;
								break;
							}
						}
						
						if(isMatStacksEmpty) {
							toolIngrs.add(toolIngr);
						}else {
							toolIngrs.add(Ingredient.fromStacks(matStacks));
						}
						
					}
					
					if(!isMatStacksEmpty) {
						ItemStack copy = tool.getCraftingResult(NonNullList.from(ItemStack.EMPTY, matStacks));
						toolIngrs.add(Ingredient.fromStacks(copy));
					}else {
						ItemStack copy = tool.getRecipeOutput().copy();
						toolIngrs.add(Ingredient.fromStacks(copy));
					}
					
					ingrs.add(toolIngrs);
					
					jeiRecipes.add(new AnvilForgingComplexRecipe(ingrs, exps, buttons, exp, button));
				}
			}
		}
		
		return jeiRecipes;
	}
	
	public static List<AnvilForgingComplexRecipe> getRecipesForToolHeads() {
		List<AnvilForgingComplexRecipe> jeiRecipes = new ArrayList<AnvilForgingComplexRecipe>();
		List<List<ForgeToolHeadRecipe>> toolHeads = new ArrayList<List<ForgeToolHeadRecipe>>();
		List<ForgeToolHeadRecipe> tools = new ArrayList<ForgeToolHeadRecipe>();
		ItemStack[] matList = null;
		
		for (IRecipe irecipe : CraftingManager.REGISTRY) {
            
            if (irecipe instanceof ForgeToolHeadRecipe && !AnvilForgingComplexRecipe.hasRecipe.contains(irecipe.getRecipeOutput().getItem())) {
            	ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
            	ingredients: for(ItemStack stack : recipe.getIngredients().get(0).getMatchingStacks()) {
            		if(stack.getItem() instanceof ItemHotToolHead) {
            			tools.add(recipe);
            			break ingredients;
            		}
            	}
            }
        }
		
		for(int i = 0; i < tools.size(); i++) {
			toolHeads.add(new ArrayList<ForgeToolHeadRecipe>(5));
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
			toolHeads.get(i).add(null);
		}
		
		for(ForgeToolHeadRecipe tool : tools) {
			for (IRecipe irecipe : CraftingManager.REGISTRY) {
				if (irecipe instanceof ForgeToolHeadRecipe) {
					ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
					
					ItemStack input1 = recipe.getRecipeOutput();
					
            		for(ItemStack toolInput : tool.getIngredients().get(0).getMatchingStacks()) {
            			if(toolInput.getItem() == input1.getItem()) {
            				
            				int toolIndex = tools.indexOf(tool);
            				toolHeads.get(toolIndex).set(5, recipe);
            			}
            		}
            	}
            	
            }
		}
		
		for(int i = 0; i < 5; i++) {
			
			Alw.logger.debug("Pass " + (i + 1));
			
			branch: for(List<ForgeToolHeadRecipe> branch : toolHeads) {
				int lastIndex = 5;
				for(int j = 5; j >= 0; j--) {
					if(branch.get(j) == null) {
						lastIndex = j + 1;
						break;
					}
				}
				
				Alw.logger.debug("LastIndex is " + lastIndex + ", Branch is " + Arrays.toString(branch.toArray()));
				
				if(lastIndex >= branch.size() || branch.get(lastIndex) == null) {
					continue;
				}
				for (IRecipe irecipe : CraftingManager.REGISTRY) {
					if (irecipe instanceof ForgeToolHeadRecipe) {
						ForgeToolHeadRecipe recipe = (ForgeToolHeadRecipe)irecipe;
						
						ItemStack input1 = recipe.getRecipeOutput();
						Ingredient last = branch.get(lastIndex).getIngredients().get(0);
	            		for(ItemStack toolInput : last.getMatchingStacks()) {
	            			if(toolInput.getItem() == input1.getItem()) {
	            				branch.set(lastIndex - 1, recipe);
	            				if(input1.getItem() == ItemInit.HOT_TOOL_HEAD) {
	            					matList = recipe.getIngredients().get(0).getMatchingStacks();
	            				}
	            				continue branch;
	            			}
	            		}
	            	}
	            }
				
			}
		}
		
		for(int i = 0; i < tools.size(); i++) {
			ForgeToolHeadRecipe tool = tools.get(i);
			String button = tool.getButton();
			int exp = tool.getExp();
			
			if(matList != null) {
				for(int j = 0; j < matList.length; j++) {
					ArrayList<NonNullList<Ingredient>> ingrs = new ArrayList<NonNullList<Ingredient>>();
					ArrayList<String> buttons = new ArrayList<String>();
					ArrayList<Integer> exps = new ArrayList<Integer>();
					
					Item metal = matList[j].getItem();
					ingrs.add(NonNullList.from(Ingredient.EMPTY, Ingredient.fromItem(metal))); // Adding the repair ingot as the first ingredient
					Alw.logger.debug("Metal is " + metal.getRegistryName());
					for(int fthr = 0; fthr < toolHeads.get(i).size(); fthr++) { //ForgeToolHeadRecipe heads : toolHeads.get(i)
						ForgeToolHeadRecipe heads = toolHeads.get(i).get(fthr);
						if(heads == null) {
							continue;
						}
						buttons.add(heads.getButton());
						exps.add(heads.getExp());
						
						NBTTagCompound tag = new NBTTagCompound();
						tag.setTag("Material", new ItemStack(metal).serializeNBT());
						
						boolean isBaseHead = heads.getRecipeOutput().getItem() == ItemInit.HOT_TOOL_HEAD;
						NonNullList<Ingredient> headIngrs = NonNullList.create();
						for(int ii = 0; ii < heads.getIngredients().size(); ii++) {
							Ingredient ingr = heads.getIngredients().get(ii);
							if(isBaseHead && ii == 0) {
								continue;
							}
							ItemStack[] matStacks = new ItemStack[ingr.getMatchingStacks().length];
							int m = 0;
							for(ItemStack match : ingr.getMatchingStacks()) {
								if(match.getItem() instanceof ItemHotToolHead) {
									ItemStack matchCopy = match.copy();
									matchCopy.setTagCompound(tag);
									matStacks[m++] = matchCopy;
								}
							}
							
							boolean isMatStacksEmpty = false;
							for(int k = 0; k < matStacks.length; k++) {
								if(matStacks[k] == ItemStack.EMPTY || matStacks[k] == null) {
									isMatStacksEmpty = true;
									break;
								}
							}
							
							if(isMatStacksEmpty) {
								headIngrs.add(ingr);
							}else {
								headIngrs.add(Ingredient.fromStacks(matStacks));
							}
							
						}
						ingrs.add(headIngrs);
					}
					
					boolean isMatStacksEmpty = false;
					ItemStack[] matStacks = new ItemStack[0];
					NonNullList<Ingredient> toolIngrs = NonNullList.create();
					for(Ingredient toolIngr : tool.getIngredients()) {
						
						NBTTagCompound tag = new NBTTagCompound();
						tag.setTag("Material", new ItemStack(metal).serializeNBT());
						
						matStacks = new ItemStack[toolIngr.getMatchingStacks().length];
						int m = 0;
						
						for(ItemStack toolInputStack : toolIngr.getMatchingStacks()) {
							if(toolInputStack.getItem() instanceof ItemHotToolHead) {
								ItemStack matchCopy = toolInputStack.copy();
								matchCopy.setTagCompound(tag);
								matStacks[m++] = matchCopy;
							}
						}
						
						isMatStacksEmpty = false;
						for(int k = 0; k < matStacks.length; k++) {
							if(matStacks[k] == ItemStack.EMPTY || matStacks[k] == null) {
								isMatStacksEmpty = true;
								break;
							}
						}
						
						if(isMatStacksEmpty) {
							toolIngrs.add(toolIngr);
						}else {
							toolIngrs.add(Ingredient.fromStacks(matStacks));
						}
						
					}
					
					ItemStack copy = tool.getRecipeOutput().copy();
					toolIngrs.add(Ingredient.fromStacks(copy));
					
					ingrs.add(toolIngrs);
					
					jeiRecipes.add(new AnvilForgingComplexRecipe(ingrs, exps, buttons, exp, button));
				}
			}
		}
		
		return jeiRecipes;
	}
	
	
}

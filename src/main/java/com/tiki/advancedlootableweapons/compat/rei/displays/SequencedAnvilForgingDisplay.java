package com.tiki.advancedlootableweapons.compat.rei.displays;

import com.tiki.advancedlootableweapons.compat.rei.REICompat;
import com.tiki.advancedlootableweapons.items.HeatableToolPartItem;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SequencedAnvilForgingDisplay extends BasicDisplay {
    public final IntList xps;

    public SequencedAnvilForgingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, IntList xps) {
        super(inputs, outputs);
        this.xps = xps;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICompat.SEQUENCED_ANVIL_FORGING;
    }

    public static class Builder {
        public final ItemStack last;
        public final List<Pair<ItemStack,ItemStack>> itemOrder = new ArrayList<>();
        public boolean finished;
        public final IntList xps = new IntArrayList();

        public Builder(ItemStack last, int xp) {
            this.last = last;
            xps.add(xp);
        }

        public Builder addItem(ItemStack item,ItemStack item2,int xp) {
            itemOrder.add(Pair.of(item,item2));
            xps.add(0,xp);
            if (!(item.getItem()instanceof HeatableToolPartItem)) {
                finished = true;
            }
            return this;
        }

        public ItemStack getLast() {
            return itemOrder.get(itemOrder.size()-1).getKey();
        }

        public static Builder builder(ItemStack last,int xp) {
            return new Builder(last,xp);
        }

        public SequencedAnvilForgingDisplay build() {
            Collections.reverse(itemOrder);
            List<EntryIngredient> inputs = new ArrayList<>(itemOrder.size() * 2);

            for (Pair<ItemStack,ItemStack> pair : itemOrder) {
                inputs.add(EntryIngredients.of(pair.getKey()));
                inputs.add(EntryIngredients.of(pair.getValue()));
            }
            return new SequencedAnvilForgingDisplay(inputs,List.of(EntryIngredients.of(last)),xps);
        }
    }
}

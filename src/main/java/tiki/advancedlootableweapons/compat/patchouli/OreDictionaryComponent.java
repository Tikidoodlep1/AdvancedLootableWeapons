package tiki.advancedlootableweapons.compat.patchouli;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.patchouli.api.IComponentRenderContext;
import vazkii.patchouli.api.ICustomComponent;
import vazkii.patchouli.api.VariableHolder;

public class OreDictionaryComponent implements ICustomComponent {

	@VariableHolder
	public String oreDict;
	
	private transient NonNullList<ItemStack> ores = null;
	private int x, y;
	private transient boolean triedTwice = false;
	private transient float ticksElapsed = 0;
	
	public OreDictionaryComponent() {
		//super();
	}

	@Override
	public void build(int componentX, int componentY, int pageNum) {
		this.x = componentX;
		this.y = componentY;
		ores = OreDictionary.getOres(oreDict);
	}

	@Override
	public void render(IComponentRenderContext context, float pticks, int mouseX, int mouseY) {
		if(ores.size() > 0) {
			ticksElapsed += pticks;
			if(ticksElapsed > Float.MAX_VALUE - 10000f) {
				ticksElapsed -= (Float.MAX_VALUE - 10000f);
			}
			//context.renderItemStack(x, y, mouseX, mouseY, stack)
			context.renderItemStack(this.x, this.y, mouseX, mouseY, ores.get((int)(ticksElapsed / 20f) % ores.size()));
		}else if(!triedTwice){
			ores = OreDictionary.getOres(oreDict);
			triedTwice = true;
		}
		
	}
}

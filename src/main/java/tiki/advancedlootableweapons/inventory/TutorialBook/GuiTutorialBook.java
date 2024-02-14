package tiki.advancedlootableweapons.inventory.TutorialBook;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class GuiTutorialBook extends GuiScreen {

	private final int page;
	
	public GuiTutorialBook(int page) {
		this.page = page;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		// TODO Auto-generated method stub
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public static class Page {
		
		public static final int PAGE_SIZE = 50;
		
		private final String text;
		
		Page(String text) {
			this.text = text;
		}
		
		public List<String> getFormattedText(FontRenderer fr) {
			StringBuilder working = new StringBuilder(this.text);
			StringBuilder curr = new StringBuilder();
			ArrayList<String> lines = new ArrayList<String>();
			working: while(working.length() > 0) {
				page: while(fr.getStringWidth(curr.toString()) < PAGE_SIZE) {
					if(working.length() <= 0) {
						lines.add(curr.toString());
						break working;
					}
					int sp = working.indexOf(" ");
					if(fr.getStringWidth(curr.toString() + working.substring(0, sp)) > PAGE_SIZE) {
						break page;
					}
					curr.append(working.substring(0, sp));
					working.delete(0, sp + 1);
				}
				lines.add(curr.toString());
			}
			
			return lines;
		}
	}

}

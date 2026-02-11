package tiki.advancedlootableweapons.commands;

import java.util.Arrays;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import tiki.advancedlootableweapons.init.ItemInit;


public class SeeToolMatErrorsCommand extends CommandBase {

	@Override
	public String getName() {
		return "materialerrors";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "alw.command.materialerrors.use";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(ItemInit.erroredToolMaterials.size() > 0) {
			String errMats = Arrays.toString(ItemInit.erroredToolMaterials.toArray(new String[0]));
			errMats = errMats.replace("[", "");
			errMats = errMats.replace("]", "");
			sender.getCommandSenderEntity().sendMessage(new TextComponentString(TextFormatting.RED + "GENERATED TOOL MATERIAL ERRORS: You can see this message again by typing /materialerrors. Tool Materials " + errMats + " were added to the generated materials in the Alw Weapon config but have no crafting and repair item. Please specify a crafting/repair item. The weapons were unable to be generated for these materials."));
		}else {
			sender.getCommandSenderEntity().sendMessage(new TextComponentString(TextFormatting.GREEN + "No Generated Tool Material Errors."));
		}
		
	}

}

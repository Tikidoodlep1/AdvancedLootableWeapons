package com.tiki.advancedlootableweapons.commands;

import java.util.Arrays;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;


public class SeeToolMatsCommand extends CommandBase {

	@Override
	public String getName() {
		return "materials";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "alw.command.materials.use";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		String mats = Arrays.toString(ToolMaterial.values());
		mats = mats.replace("[", "");
		mats = mats.replace("]", "");
		sender.getCommandSenderEntity().sendMessage(new TextComponentString("Available tool materials are: " + TextFormatting.BLUE + mats));
	}

}

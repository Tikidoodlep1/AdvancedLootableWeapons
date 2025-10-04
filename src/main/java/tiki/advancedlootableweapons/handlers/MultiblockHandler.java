package tiki.advancedlootableweapons.handlers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import tiki.advancedlootableweapons.Alw;
import tiki.advancedlootableweapons.util.MultiblockPattern;

public class MultiblockHandler {

	private static final HashMap<String, MultiblockPattern> registry = new HashMap<String, MultiblockPattern>();
	
	public static void registerMultiblock(String name, MultiblockPattern pattern) {
		if(registry.containsKey(name)) {
			Alw.logger.error(new IllegalArgumentException("name " + name + " has already been registered with the multiblock pattern registry! Pattern will NOT be registered!"));
		}
		registry.put(name, pattern);
	}
	
	public static Map<String, MultiblockPattern> getRegistry() {
		return Collections.unmodifiableMap(registry);
	}
}

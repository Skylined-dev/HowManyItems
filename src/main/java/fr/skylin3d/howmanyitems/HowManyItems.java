package fr.skylin3d.howmanyitems;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HowManyItems implements ModInitializer {
	public static final String MOD_ID = "howmanyitems";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

	}

	public static String MessageStringFormat(String msg){
		return "[" + LOGGER.getName() + "] " + msg;
	}
}

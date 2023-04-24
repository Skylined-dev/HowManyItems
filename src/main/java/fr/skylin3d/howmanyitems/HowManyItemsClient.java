package fr.skylin3d.howmanyitems;

import fr.skylin3d.howmanyitems.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class HowManyItemsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}

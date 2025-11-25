package firelack.freeinventory.client;

import net.fabricmc.api.ClientModInitializer;

public class FreeInventoryClient implements ClientModInitializer {
    
    // Flag to indicate if the player's tick method is being executed
    public static boolean isPlayerTicking = false;

    @Override
    public void onInitializeClient() {
        System.out.println("FreeInventory Client Initialized!");
    }
}
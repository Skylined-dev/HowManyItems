package fr.skylin3d.howmanyitems.event;

import fr.skylin3d.howmanyitems.HowManyItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Material;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_HOWMANYITEMS = "key.category.howmanyitems.open_material_list";
    public static final String KEY_OPEN_MATERIAL_LIST = "key.howmanyitems.open_material_list";
    public static final String KEY_TEST = "key.howmanyitems.test";


    public static KeyBinding openGuiKey;
    public static KeyBinding test;

    public static void rehisterKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(openGuiKey.wasPressed()){
                client.player.sendMessage(Text.of(HowManyItems.MessageStringFormat("OPEN MATERIAL LIST")));
            }
            if(test.wasPressed()){
                if(client.player.getInventory().getMainHandStack().getItem() == null || client.player.getInventory().getMainHandStack().getItem() == Items.AIR) return;

                World world = client.player.getWorld();
                Collection<Recipe<?>> recipes = world.getRecipeManager().values();
                /*client.player.sendMessage(Text.of(HowManyItems.MessageStringFormat(String.valueOf(
                        client.player.getInventory().getMainHandStack().getItem().getRawId(client.player.getInventory().getMainHandStack().getItem())
                ))));*/

                for (Recipe<?> recipe : recipes) {
                    if(recipe.getOutput(DynamicRegistryManager.EMPTY).getItem().getRawId(recipe.getOutput(DynamicRegistryManager.EMPTY).getItem()) == client.player.getInventory().getMainHandStack().getItem().getRawId(client.player.getInventory().getMainHandStack().getItem())){
                        for(Ingredient ingredient : recipe.getIngredients()){
                            if(ingredient.isEmpty()) continue;
                            for(int id : ingredient.getMatchingItemIds()){
                                client.player.sendMessage(Text.of(HowManyItems.MessageStringFormat(String.valueOf(Item.byRawId(id).getName()))));
                            }
                        }
                        break;
                    }
                }
            }
        });
    }

    public static void register(){
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_MATERIAL_LIST,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                KEY_CATEGORY_HOWMANYITEMS
        ));

        test = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TEST,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_0,
                KEY_CATEGORY_HOWMANYITEMS
        ));

        rehisterKeyInputs();
    }

}

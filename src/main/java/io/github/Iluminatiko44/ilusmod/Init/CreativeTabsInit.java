package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ilusmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabsInit {

    public static CreativeModeTab ILUSTAB;

    // Called on the creation of the creative tabs in the game
    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        ILUSTAB = event.registerCreativeModeTab(new ResourceLocation(Ilusmod.MODID, "ilusmod_tab"),
                        builder -> builder.icon(() -> ItemInit.HAPPY_BALL.get().getDefaultInstance()).title(Component.translatable("creativemodetab.ilusmod_tab")));

    }
}

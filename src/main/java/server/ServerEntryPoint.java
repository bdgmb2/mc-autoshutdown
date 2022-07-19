package server;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@Mod("mcautoshutdown")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.DEDICATED_SERVER})
public class ServerEntryPoint {
    private static TimerEvent timer;

    @SubscribeEvent
    public static void Init(InterModEnqueueEvent evt) {
        timer = new TimerEvent();
    }
}

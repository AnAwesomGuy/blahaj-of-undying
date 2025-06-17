package dev.enjarai.blahajtotem;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;

public class BlahajCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("blahaj")
                .then(LiteralArgumentBuilder.<CommandSourceStack>literal("wiki")
                        .executes(BlahajCommand::showWiki)
                )
        );
    }

    private static int showWiki(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSystemMessage(
                Component.translatable("blahaj_totem.command.show_wiki",
                        Component.literal("Blåhaj").withColor(0x77DBFF)
                                .append(Component.literal(" of ").withColor(0xFFFFFF))
                                .append(Component.literal("Undying").withColor(0xF8abb9))
                ).append("\n").append(
                        Component.literal("https://enjarai.dev/blahaj-of-undying/")
                                .withStyle(style -> style
                                        .withColor(0x6666FF)
                                        .withUnderlined(true)
                                        .withClickEvent(new ClickEvent(
                                                ClickEvent.Action.OPEN_URL, "https://enjarai.dev/blahaj-of-undying/"
                                        ))
                                )
                )
        );
        return 1;
    }
}

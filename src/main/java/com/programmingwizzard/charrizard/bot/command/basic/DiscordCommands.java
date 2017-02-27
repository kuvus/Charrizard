package com.programmingwizzard.charrizard.bot.command.basic;

import com.programmingwizzard.charrizard.bot.Charrizard;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import pl.themolka.commons.command.CommandContext;
import pl.themolka.commons.command.CommandUsageException;
import pl.themolka.commons.command.Commands;

/*
 * @author ProgrammingWizzard
 * @date 27.02.2017
 */
public class DiscordCommands extends Commands implements EventListener {

    private final Charrizard charrizard;

    public DiscordCommands(Charrizard charrizard) {
        this.charrizard = charrizard;
    }

    @Override
    public void handleCommand(Message message, CommandContext context) {
        try {
            if (context.getCommand().getMin() > context.getParamsLength()) {
                throw new CommandUsageException("error"); // TODO
            }
            try {
                context.getCommand().handleCommand(message, context);
            } catch (Throwable ignored) {
            }
        } catch (CommandUsageException ex) {
            // TODO
        }
    }

    @Override
    public void onEvent(Event event) {
        if (!(event instanceof MessageReceivedEvent)) {
            return;
        }
        MessageReceivedEvent messageEvent = (MessageReceivedEvent) event;
        String[] args = messageEvent.getMessage().getContent().split(" ");
        if (!args[0].startsWith("!")) {
            return;
        }
        handleCommand(messageEvent.getMessage(), this.getCommand(args[0]), args[0], args);
    }
}
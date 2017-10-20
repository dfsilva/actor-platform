package im.actor.core.api;
/*
 *  Generated by the Actor API Scheme generator.  DO NOT EDIT!
 */

import im.actor.runtime.bser.*;
import im.actor.runtime.collections.*;
import static im.actor.runtime.bser.Utils.*;
import im.actor.core.network.parser.*;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.google.j2objc.annotations.ObjectiveCName;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ApiBotCommand extends BserObject {

    private String slashCommand;
    private String description;
    private String locKey;

    public ApiBotCommand(@NotNull String slashCommand, @NotNull String description, @Nullable String locKey) {
        this.slashCommand = slashCommand;
        this.description = description;
        this.locKey = locKey;
    }

    public ApiBotCommand() {

    }

    @NotNull
    public String getSlashCommand() {
        return this.slashCommand;
    }

    @NotNull
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public String getLocKey() {
        return this.locKey;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.slashCommand = values.getString(1);
        this.description = values.getString(2);
        this.locKey = values.optString(3);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        if (this.slashCommand == null) {
            throw new IOException();
        }
        writer.writeString(1, this.slashCommand);
        if (this.description == null) {
            throw new IOException();
        }
        writer.writeString(2, this.description);
        if (this.locKey != null) {
            writer.writeString(3, this.locKey);
        }
    }

    @Override
    public String toString() {
        String res = "struct BotCommand{";
        res += "slashCommand=" + this.slashCommand;
        res += ", description=" + this.description;
        res += ", locKey=" + this.locKey;
        res += "}";
        return res;
    }

}

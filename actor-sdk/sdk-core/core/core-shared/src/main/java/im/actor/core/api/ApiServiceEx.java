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

public abstract class ApiServiceEx extends BserObject {
    public static ApiServiceEx fromBytes(byte[] src) throws IOException {
        BserValues values = new BserValues(BserParser.deserialize(new DataInput(src, 0, src.length)));
        int key = values.getInt(1);
        byte[] content = values.getBytes(2);
        switch(key) { 
            case 1: return Bser.parse(new ApiServiceExUserInvited(), content);
            case 17: return Bser.parse(new ApiServiceExUserJoined(), content);
            case 2: return Bser.parse(new ApiServiceExUserKicked(), content);
            case 3: return Bser.parse(new ApiServiceExUserLeft(), content);
            case 4: return Bser.parse(new ApiServiceExGroupCreated(), content);
            case 5: return Bser.parse(new ApiServiceExChangedTitle(), content);
            case 18: return Bser.parse(new ApiServiceExChangedTopic(), content);
            case 19: return Bser.parse(new ApiServiceExChangedAbout(), content);
            case 6: return Bser.parse(new ApiServiceExChangedAvatar(), content);
            case 8: return Bser.parse(new ApiServiceExContactRegistered(), content);
            case 9: return Bser.parse(new ApiServiceExPhoneMissed(), content);
            case 16: return Bser.parse(new ApiServiceExPhoneCall(), content);
            case 20: return Bser.parse(new ApiServiceExChatArchived(), content);
            case 21: return Bser.parse(new ApiServiceExChatRestored(), content);
            default: return new ApiServiceExUnsupported(key, content);
        }
    }
    public abstract int getHeader();

    public byte[] buildContainer() throws IOException {
        DataOutput res = new DataOutput();
        BserWriter writer = new BserWriter(res);
        writer.writeInt(1, getHeader());
        writer.writeBytes(2, toByteArray());
        return res.toByteArray();
    }

}

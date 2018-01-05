package im.actor.core.api.rpc;
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
import im.actor.core.api.*;

public class RequestChangeGroupPre extends Request<ResponseSeq> {

    public static final int HEADER = 0x18;
    public static RequestChangeGroupPre fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestChangeGroupPre(), data);
    }

    private int groupId;
    private boolean isGroupPre;

    public RequestChangeGroupPre(int groupId, boolean isGroupPre) {
        this.groupId = groupId;
        this.isGroupPre = isGroupPre;
    }

    public RequestChangeGroupPre() {

    }

    public int getGroupId() {
        return this.groupId;
    }

    public boolean isGroupPre() {
        return this.isGroupPre;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.groupId = values.getInt(1);
        this.isGroupPre = values.getBool(2);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeInt(1, this.groupId);
        writer.writeBool(2, this.isGroupPre);
    }

    @Override
    public String toString() {
        String res = "rpc ChangeGroupPre{";
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}
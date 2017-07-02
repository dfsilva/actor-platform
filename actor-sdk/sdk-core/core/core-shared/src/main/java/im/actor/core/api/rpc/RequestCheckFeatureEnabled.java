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

public class RequestCheckFeatureEnabled extends Request<ResponseBool> {

    public static final int HEADER = 0xa1e;
    public static RequestCheckFeatureEnabled fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestCheckFeatureEnabled(), data);
    }

    private ApiUserOutPeer userOutPeer;
    private String featureName;

    public RequestCheckFeatureEnabled(@NotNull ApiUserOutPeer userOutPeer, @NotNull String featureName) {
        this.userOutPeer = userOutPeer;
        this.featureName = featureName;
    }

    public RequestCheckFeatureEnabled() {

    }

    @NotNull
    public ApiUserOutPeer getUserOutPeer() {
        return this.userOutPeer;
    }

    @NotNull
    public String getFeatureName() {
        return this.featureName;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.userOutPeer = values.getObj(1, new ApiUserOutPeer());
        this.featureName = values.getString(2);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        if (this.userOutPeer == null) {
            throw new IOException();
        }
        writer.writeObject(1, this.userOutPeer);
        if (this.featureName == null) {
            throw new IOException();
        }
        writer.writeString(2, this.featureName);
    }

    @Override
    public String toString() {
        String res = "rpc CheckFeatureEnabled{";
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}

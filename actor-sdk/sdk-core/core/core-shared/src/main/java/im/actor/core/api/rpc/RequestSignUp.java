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

public class RequestSignUp extends Request<ResponseAuth> {

    public static final int HEADER = 0xbe;
    public static RequestSignUp fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestSignUp(), data);
    }

    private String transactionHash;
    private String name;
    private ApiSex sex;
    private String password;

    public RequestSignUp(@NotNull String transactionHash, @NotNull String name, @Nullable ApiSex sex, @Nullable String password) {
        this.transactionHash = transactionHash;
        this.name = name;
        this.sex = sex;
        this.password = password;
    }

    public RequestSignUp() {

    }

    @NotNull
    public String getTransactionHash() {
        return this.transactionHash;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @Nullable
    public ApiSex getSex() {
        return this.sex;
    }

    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.transactionHash = values.getString(1);
        this.name = values.getString(2);
        int val_sex = values.getInt(3, 0);
        if (val_sex != 0) {
            this.sex = ApiSex.parse(val_sex);
        }
        this.password = values.optString(4);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        if (this.transactionHash == null) {
            throw new IOException();
        }
        writer.writeString(1, this.transactionHash);
        if (this.name == null) {
            throw new IOException();
        }
        writer.writeString(2, this.name);
        if (this.sex != null) {
            writer.writeInt(3, this.sex.getValue());
        }
        if (this.password != null) {
            writer.writeString(4, this.password);
        }
    }

    @Override
    public String toString() {
        String res = "rpc SignUp{";
        res += "name=" + this.name;
        res += ", sex=" + this.sex;
        res += ", password=" + this.password;
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}

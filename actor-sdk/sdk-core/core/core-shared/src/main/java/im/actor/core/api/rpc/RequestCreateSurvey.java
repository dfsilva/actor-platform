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

public class RequestCreateSurvey extends Request<ResponseVoid> {

    public static final int HEADER = 0x8b;
    public static RequestCreateSurvey fromBytes(byte[] data) throws IOException {
        return Bser.parse(new RequestCreateSurvey(), data);
    }

    private ApiSurvey survey;

    public RequestCreateSurvey(@NotNull ApiSurvey survey) {
        this.survey = survey;
    }

    public RequestCreateSurvey() {

    }

    @NotNull
    public ApiSurvey getSurvey() {
        return this.survey;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.survey = values.getObj(1, new ApiSurvey());
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        if (this.survey == null) {
            throw new IOException();
        }
        writer.writeObject(1, this.survey);
    }

    @Override
    public String toString() {
        String res = "rpc CreateSurvey{";
        res += "}";
        return res;
    }

    @Override
    public int getHeaderKey() {
        return HEADER;
    }
}
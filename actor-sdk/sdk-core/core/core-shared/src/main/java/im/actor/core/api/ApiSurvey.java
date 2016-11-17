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

public class ApiSurvey extends BserObject {

    private long id;
    private String titulo;
    private String uf;
    private long creationTime;
    private long endTime;
    private int ownerId;
    private List<String> answers;

    public ApiSurvey(long id, @NotNull String titulo, @Nullable String uf, long creationTime, long endTime, int ownerId, @NotNull List<String> answers) {
        this.id = id;
        this.titulo = titulo;
        this.uf = uf;
        this.creationTime = creationTime;
        this.endTime = endTime;
        this.ownerId = ownerId;
        this.answers = answers;
    }

    public ApiSurvey() {

    }

    public long getId() {
        return this.id;
    }

    @NotNull
    public String getTitulo() {
        return this.titulo;
    }

    @Nullable
    public String getUf() {
        return this.uf;
    }

    public long getCreationTime() {
        return this.creationTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    @NotNull
    public List<String> getAnswers() {
        return this.answers;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.id = values.getLong(1);
        this.titulo = values.getString(2);
        this.uf = values.optString(3);
        this.creationTime = values.getLong(4);
        this.endTime = values.getLong(5);
        this.ownerId = values.getInt(6);
        this.answers = values.getRepeatedString(7);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeLong(1, this.id);
        if (this.titulo == null) {
            throw new IOException();
        }
        writer.writeString(2, this.titulo);
        if (this.uf != null) {
            writer.writeString(3, this.uf);
        }
        writer.writeLong(4, this.creationTime);
        writer.writeLong(5, this.endTime);
        writer.writeInt(6, this.ownerId);
        writer.writeRepeatedString(7, this.answers);
    }

    @Override
    public String toString() {
        String res = "struct Survey{";
        res += "}";
        return res;
    }

}

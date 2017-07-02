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

public class ApiDoubleValue extends ApiRawValue {

    private double value;

    public ApiDoubleValue(double value) {
        this.value = value;
    }

    public ApiDoubleValue() {

    }

    public int getHeader() {
        return 4;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public void parse(BserValues values) throws IOException {
        this.value = values.getDouble(1);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeDouble(1, this.value);
    }

    @Override
    public String toString() {
        String res = "struct DoubleValue{";
        res += "}";
        return res;
    }

}

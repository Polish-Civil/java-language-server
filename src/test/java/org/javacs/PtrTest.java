package org.javacs;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.util.Set;
import org.junit.Test;

public class PtrTest {

    static JavaLanguageServer server = LanguageServerFixture.getJavaLanguageServer();
    static Path file = FindResource.path("/org/javacs/example/Ptrs.java");
    static CompileBatch compile = server.compiler().compileBatch(Set.of(new SourceFileObject(file)));

    @Test
    public void classPtr() {
        var el = compile.element(file, 3, 15).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void fieldPtr() {
        var el = compile.element(file, 4, 20).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs#field"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void emptyMethodPtr() {
        var el = compile.element(file, 6, 20).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs#method()"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void intMethodPtr() {
        var el = compile.element(file, 8, 20).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs#method(int)"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void stringMethodPtr() {
        var el = compile.element(file, 10, 20).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs#method(java.lang.String)"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void constructorPtr() {
        var el = compile.element(file, 12, 13).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs#<init>(int)"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void innerClassPtr() {
        var el = compile.element(file, 14, 20).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs.InnerClass"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void innerFieldPtr() {
        var el = compile.element(file, 15, 20).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs.InnerClass#innerField"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void innerEmptyMethodPtr() {
        var el = compile.element(file, 17, 25).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs.InnerClass#innerMethod()"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }

    @Test
    public void innerConstructorPtr() {
        var el = compile.element(file, 19, 21).get();
        var ptr = new Ptr(el);
        assertThat(ptr.toString(), equalTo("org.javacs.example/Ptrs.InnerClass#<init>()"));

        var copy = new Ptr(ptr.toString());
        assertThat(copy, equalTo(ptr));
    }
}

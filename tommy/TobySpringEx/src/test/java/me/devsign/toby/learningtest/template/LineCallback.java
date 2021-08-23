package me.devsign.toby.learningtest.template;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}

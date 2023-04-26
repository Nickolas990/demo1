package com.example.demo.interfaces;

public interface Mapper<V, T> {
    T toDto(V value);
}

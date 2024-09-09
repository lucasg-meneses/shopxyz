package com.lucasgmeneses.shopxyz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface ICrudController<T,V> {
    public ResponseEntity create(@RequestBody T request);
    public ResponseEntity update(@PathVariable UUID id, @RequestBody V request);
    public ResponseEntity delete(@PathVariable UUID id);
    public ResponseEntity get(@PathVariable UUID id);
    public ResponseEntity getAll();
}

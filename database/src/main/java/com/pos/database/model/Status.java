package com.pos.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private String uid;
    private Set<Item> items = new LinkedHashSet<>();
    private Instant createdAt;
}

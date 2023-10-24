package org.carrental.model.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.carrental.model.repository.RepositoryEntry;

@EqualsAndHashCode(callSuper = true)
@Data
public class Client extends RepositoryEntry {
    private String name;
    private Gender gender;
}

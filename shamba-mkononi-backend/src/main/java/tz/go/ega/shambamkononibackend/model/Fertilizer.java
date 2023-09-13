package tz.go.ega.shambamkononibackend.model;

import lombok.AllArgsConstructor;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "fertilizer")
public class Fertilizer extends BaseEntity{
    private String name;
    private String code;
}

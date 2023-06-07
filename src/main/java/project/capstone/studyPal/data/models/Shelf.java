package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
//@JoinTable(
//        joinColumns = @JoinColumn(
//                name = "shelf_id",
//                foreignKey = @ForeignKey(
//                        foreignKeyDefinition = "foreign key (shelf_id) references shelf(id) ON DELETE CASCADE"
//                )),
//        inverseJoinColumns = @JoinColumn(
//                name = "resource_materials_id",
//                foreignKey = @ForeignKey(
//                        foreignKeyDefinition = "foreign key (resource_materials_id) references resource_material(id) ON DELETE CASCADE"
//                ))
//)
    private List<ResourceMaterial> resourceMaterials = new ArrayList<>();
}
package co.pupil.shapes;

import org.springframework.data.repository.CrudRepository;

public interface ShapeRepository extends CrudRepository<Shape, Long>, CustomShapeRepository {
}

package co.pupil.shapes;

public interface CustomShapeRepository {
    <S extends Shape> S save(S entity);
}

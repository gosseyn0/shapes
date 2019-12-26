package co.pupil.shapes;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomShapeRepositoryImpl implements CustomShapeRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public <S extends Shape> S save(S shape) {
        Query query = entityManager.createQuery(
                "from Shape where (x1>=:x1 and y1>=:y1 and x1<=:x2 and y1<=:y2) " +
                        "or (x2>=:x1 and y2>=:y1 and x2<=:x2 and y2<=:y2)")
                .setParameter("x1", shape.getX1())
                .setParameter("y1", shape.getY1())
                .setParameter("x2", shape.getX2())
                .setParameter("y2", shape.getY2());
        List<Shape> firstOverlappingShape = query.setMaxResults(1).getResultList();
        if(firstOverlappingShape.size() > 0) {
            throw new IllegalArgumentException("The shape being saved overlaps the shape with ID [" + firstOverlappingShape.get(0).getName() + "]");
        }
        return entityManager.merge(shape);
    }
}

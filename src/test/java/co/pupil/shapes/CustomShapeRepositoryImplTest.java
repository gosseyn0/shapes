package co.pupil.shapes;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomShapeRepositoryImplTest {

    @InjectMocks
    CustomShapeRepositoryImpl customShapeRepository;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    EntityManager entityManager;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Query query;

    @Spy
    static Shape shape = new Shape();

    @BeforeEach
    public void setup() {
        shape.setName("name");
        shape.setX1(1);
        shape.setX2(2);
        shape.setX2(3);
        shape.setY2(4);

        when(entityManager.createQuery(anyString())
                .setParameter(anyString(), any())
                .setParameter(anyString(), any())
                .setParameter(anyString(), any())
                .setParameter(anyString(), any()))
                .thenReturn(query);
    }

    @Test
    void save() {
        // Return no overlapping shapes
        when(query.setMaxResults(1).getResultList())
                .thenReturn(Arrays.asList());

        customShapeRepository.save(shape);

        // TODO write further test to ensure params are set correctly

        // Verify shape was saved
        verify(entityManager).merge(shape);
    }

    @Test
    void saveOverlapping() {
        // Return one overlapping shape
        when(query.setMaxResults(1).getResultList())
                .thenReturn(Arrays.asList(shape));

        // Ensure overlapping shape throws an exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customShapeRepository.save(shape);
        });

        // Verify new shape is not merged
        verify(entityManager, never()).merge(shape);
    }
}
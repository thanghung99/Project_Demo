package net.javaguides.springboot.tutorial.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = 1559197461L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudent student = new QStudent("student");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLop lop;

    public final StringPath name = createString("name");

    public final NumberPath<Long> phoneNo = createNumber("phoneNo", Long.class);

    public QStudent(String variable) {
        this(Student.class, forVariable(variable), INITS);
    }

    public QStudent(Path<? extends Student> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudent(PathMetadata metadata, PathInits inits) {
        this(Student.class, metadata, inits);
    }

    public QStudent(Class<? extends Student> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lop = inits.isInitialized("lop") ? new QLop(forProperty("lop")) : null;
    }

}


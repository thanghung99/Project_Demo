package net.javaguides.springboot.tutorial.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLop is a Querydsl query type for Lop
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLop extends EntityPathBase<Lop> {

    private static final long serialVersionUID = 2031293735L;

    public static final QLop lop = new QLop("lop");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nameLop = createString("nameLop");

    public final ListPath<Student, QStudent> s = this.<Student, QStudent>createList("s", Student.class, QStudent.class, PathInits.DIRECT2);

    public QLop(String variable) {
        super(Lop.class, forVariable(variable));
    }

    public QLop(Path<? extends Lop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLop(PathMetadata metadata) {
        super(Lop.class, metadata);
    }

}


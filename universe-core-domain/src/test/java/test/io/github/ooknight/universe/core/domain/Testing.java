package test.io.github.ooknight.universe.core.domain;

import test.io.github.ooknight.universe.core.domain.entity.Sample;

import io.ebean.DB;
import io.ebean.Database;
import io.ebean.Query;
import io.ebean.SqlRow;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    private final Database db = DB.getDefault();

    @Test
    public void testNewEntity() {
        Sample s1 = new Sample();
        assertTrue(s1.active());
    }

    @Test
    public void testInsert() {
        Sample sample = new Sample();
        db.insert(sample);
    }

    @Test
    public void testDelete() {
        db.delete(Sample.class, 1L);
        db.deletePermanent(Sample.class, 1L);
        db.delete(new Sample());
        db.deletePermanent(new Sample());
    }

    @Test
    public void testUpdate() {
        Sample sample = new Sample();
        sample.setName("test");
        sample.setValue1("hello");
        db.save(sample);
        sample.setValue1("hello@163.com");
        db.update(sample);
        //
        //Ebean.update(Sample.class).set("name", "hello2").where().idEq(1).update();
        //
        db.find(Sample.class).findList().forEach(System.out::println);
        db.sqlQuery("select * from e_sample").findList().forEach(System.out::println);
        //
        //Ebean.update(Sample.class).set(Sample.FIELD_MAIL, "hello").where().eq(Sample.FIELD_NAME, "sample1").update();
        //System.out.println(Ebean.find(Sample.class, 1));
    }

    @Test
    public void testQuery() {
        db.createQuery(Sample.class).findList();
    }

    @Test
    public void test1() {
        Query<Sample> query = db.createQuery(Sample.class);
        List<Sample> r = query.findList();
        assertEquals(2, r.size());
    }

    @Test
    public void test2() {
        Query<Sample> query = db.createQuery(Sample.class);
        List<Sample> r = query.setIncludeSoftDeletes().findList();
        assertEquals(3, r.size());
        //assertEquals(Active.ENABLED, r2.get(0).active());
        //assertEquals(Active.ENABLED, r2.get(1).active());
        //assertEquals(Active.DISABLED, r2.get(2).active());
        assertTrue(r.get(0).active());
        assertTrue(r.get(1).active());
        assertFalse(r.get(2).active());
        //Sample sample = query.id.eq(1).findOne();
        //Assert.assertNotNull(sample);
        //System.out.println(sample);
    }

    @Test
    public void test3() {
        //List<Sample> r1 = new QSample().id.eq(1).findList();
        //System.out.println(r1);
    }

    @Test
    public void test4() {
        //System.out.println(QueryEngine.SELECT(Sample.class, 1L));
        //System.out.println(QueryEngine.SELECT(Sample.class, Scope.DUMMY(), 1L));
        //System.out.println(QueryEngine.SELECT(Sample.class));
        //System.out.println(QueryEngine.SELECT(Sample.class, Scope.DUMMY()));
    }

    @Test
    public void test5() {
        //System.out.println(QueryEngine.GETONE(Sample.class, 1L));
        //thrown.expect(BusinessException.class);
        //System.out.println(QueryEngine.GETONE(Sample.class, Scope.DUMMY(), 1L));
    }

    @Test
    public void test6() {

        //db.beginTransaction();
        Sample sample = new Sample();
        sample.setId(999L);
        sample.setName("test");
        sample.setValue1("v1@test.com");
        sample.setValue2("v2@test.com");
        db.insert(sample);
        //db.commitTransaction();

        db.createQuery(Sample.class).findList().forEach(System.out::println);

        SqlRow row = db.sqlQuery("select * from e_sample where name_='test'").findOne();
        if (row != null) {
            System.out.println(row.getLong("id"));
            System.out.println(row.get("value1_"));
            System.out.println(row.get("value2_"));

        }

        //List<Sample> s1 = db.createQuery(Sample.class).where().eq("mail", "test@test.com").findList();
        //Assert.assertEquals(1, s1.size());
        //List<Sample> s2 = new QSample().mail.equalTo("test@test.com").findList();
        //Assert.assertEquals(1, s2.size());
    }
}

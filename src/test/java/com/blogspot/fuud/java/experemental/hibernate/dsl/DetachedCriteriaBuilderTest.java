package com.blogspot.fuud.java.experemental.hibernate.dsl;

import com.blogspot.fuud.java.bugtracker.dao.BaseTest;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class DetachedCriteriaBuilderTest extends BaseTest {

    @Entity
    public static class TestClass {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String title;

        @OneToOne(cascade = CascadeType.ALL) // automatically save child
        private TestClass child;

        public Long getId() {
            return id;
        }

        private void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public TestClass getChild() {
            return child;
        }

        public void setChild(TestClass child) {
            this.child = child;
        }
    }

    @Override
    protected Configuration getConfiguration() {
        return new Configuration().addAnnotatedClass(TestClass.class).configure();
    }

    @Test
    public void oneWhereClause() throws Exception {
        final DetachedCriteria criteria =
                new DetachedCriteriaBuilder<TestClass>() {{
                    where(eq(object.getTitle(), "Lord Foo"));
                }}.getCriteria();


        save("Lord Foo", "childTitle");//1
        save("Lord Foo", "childTitle");//2
        save("Lord Car", "childTitle");
        save("Lord Foo", "childTitle");//3
        save("Lord Bar", "childTitle");

        final List lordsFoo = (List<TestClass>) criteria.getExecutableCriteria(getSession()).list();

        assertEquals(3, lordsFoo.size());
        for (TestClass testClass : (List<TestClass>) lordsFoo) {
            assertEquals("Lord Foo", testClass.getTitle());
        }
    }

    @Test
    public void twoWhereClause() throws Exception {
        final DetachedCriteria criteria =
                new DetachedCriteriaBuilder<TestClass>() {{
                    where(
                            or(
                                    eq(object.getTitle(), "Lord Foo"),
                                    eq(object.getTitle(), "Lord Bar")
                            )
                    );
                }}.getCriteria();


        save("Lord Foo", "childTitle");//1
        save("Lord Foo", "childTitle");//2
        save("Lord Car", "childTitle");
        save("Lord Foo", "childTitle");//3
        save("Lord Bar", "childTitle");//4

        // only Foos and Bars, not Cars
        final List<TestClass> lords = (List<TestClass>) criteria.getExecutableCriteria(getSession()).list();

        assertEquals(4, lords.size());
        assertEquals("Lord Foo", lords.get(0).getTitle());
        assertEquals("Lord Foo", lords.get(1).getTitle());
        assertEquals("Lord Foo", lords.get(2).getTitle());
        assertEquals("Lord Bar", lords.get(3).getTitle());
    }

    @Test
    public void oneDeepWhereClause() throws Exception {
        final DetachedCriteria criteria =
                new DetachedCriteriaBuilder<TestClass>() {{
                    where(
                            eq(object.getChild().getTitle(), "Alice")
                    );
                }}.getCriteria();


        save("Lord Foo", "Adriana");
        save("Lord Foo", "Alberta");
        save("Lord Car", "Alice");//1
        save("Lord Foo", "Alice");//2
        save("Lord Bar", "Alice");//3

        final List lordsFoo = (List<TestClass>) criteria.getExecutableCriteria(getSession()).list();

        assertEquals(3, lordsFoo.size());
        for (TestClass testClass : (List<TestClass>) lordsFoo) {
            assertEquals("Alice", testClass.getChild().getTitle());
        }
    }

    private void save(String title, String childTitle) {
        final Session session = getSessionFactory().openSession();
        session.beginTransaction();
        final TestClass testClass = new TestClass();
        testClass.setTitle(title);
        if (childTitle!=null){
            final TestClass child = new TestClass();
            child.setTitle(childTitle);
            testClass.setChild(child);
        }
        session.save(testClass);
        session.getTransaction().commit();
        session.close();
    }

}

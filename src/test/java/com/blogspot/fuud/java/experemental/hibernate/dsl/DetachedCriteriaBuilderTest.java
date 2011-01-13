package com.blogspot.fuud.java.experemental.hibernate.dsl;

import org.junit.Test;

public class DetachedCriteriaBuilderTest {
    public static class TestClass{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Test
    public void test() throws Exception{
        new DetachedCriteriaBuilder<TestClass>(TestClass.class){{
            where(eq(object.getTitle(), "Lord Foo"));
        }};
    }
}

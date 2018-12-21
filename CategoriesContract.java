package com.example.joe.broanalytics;

import android.provider.BaseColumns;

public final class CategoriesContract {
        private CategoriesContract() {}

        /* Inner class that defines the table contents */
        public static class FeedEntry1 implements BaseColumns {
            public static final String TABLE_NAME = "Categories";
            public static final String COLUMN_NAME_NAME = "Name";

        }

        public static class FeedEntry2 implements BaseColumns {
            public static final String TABLE_NAME = "Activities";
            public static final String COLUMN_NAME_NAME = "Name";
            public static final String COLUMN_NAME_FIELD1_NAME = "F1Name";
            public static final String COLUMN_NAME_FIELD2_NAME = "F2Name";
            public static final String COLUMN_NAME_CATEGORY = "Category";
        }

        public static class FeedEntry3 implements BaseColumns {
            public static final String TABLE_NAME = "Data";
            public static final String COLUMN_NAME_ACTIVITY = "Activity";
            public static final String COLUMN_NAME_FIELD1 = "Field1";
            public static final String COLUMN_NAME_FIELD2 = "Field2";
            public static final String COLUMN_NAME_DATE = "Date";
        }

        public static class FeedEntry4 implements BaseColumns {
            public static final String TABLE_NAME = "ToDo";
            public static final String COLUMN_NAME_NAME = "Name";
            public static final String COLUMN_NAME_PRIORITY = "Priority";
            public static final String COLUMN_NAME_CREATION_DATE = "Created";
            public static final String COLUMN_NAME_DUE_DATE = "Due_By";
            public static final String COLUMN_NAME_REPEAT_UNTIL = "Repeat Until";
            public static final String COLUMN_NAME_REPEAT = "Repeat";
            public static final String COLUMN_NAME_OCCURRENCE = "Occurrence";
            public static final String COLUMN_NAME_ACTIVE = "Active";
            public static final String COLUMN_NAME_STORE = "Store";
        }

        public static class FeedEntry5 implements BaseColumns {
            public static final String TABLE_NAME = "Stored ToDo";
            public static final String COLUMN_NAME_NAME = "Name";
            public static final String COLUMN_NAME_Completion_DATE = "Completed On";
            public static final String COLUMN_NAME_NOTES = "Notes";
        }
}

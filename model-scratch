public final class Book {

    private long id = -1;
    private String title;

    public Book(String title){
        this.title = title;
    }

    private Book(long id, String title){
        this.title = title;
        this.id = id;
    }


    public void save(DatabaseController db){
        //save or update the book, throw an exception on failure.
    }

    //More non static methods (getters, setters, database methods) here

    public static Book getById(DatabaseController db, long id){
        //Do select query and get an existing book from the database.
    }

    //More static methods here

    public static class Model extends LocalDatabaseModel {

        public Model(){
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
             //Implement update logic for this model/table
         }
         @Override
         public void onCreate(SQLiteDatabase database){
             //Implement create logic for this model/table
          }
    }
}
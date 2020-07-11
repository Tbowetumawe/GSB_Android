package bts.sio.gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;
import java.util.List;

public class DaoBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GSBVisteur";
    private static final String TABLE_VISITEUR = "Visiteur";
    private static final int DATABASE_VERSION = 1;
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM= "prenom";
    private static final String COL_LOGIN= "login";
    private static final String COL_PASSWORD= "password";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_VISITEUR + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " TEXT NOT NULL, "
            + COL_PRENOM + " TEXT NOT NULL, " + COL_LOGIN + " TEXT NOT NULL, " + COL_PASSWORD + " TEXT );";

    public DaoBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_VISITEUR + ";");
        this.onCreate(db);
    }

    public void addVisiteur (Visiteur visiteur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        Values.put("COL_NOM ", visiteur.getNom());
        Values.put("COL_PRENOM", visiteur.getPrenom());
        Values.put("COL_LOGIN", visiteur.getLogin());
        Values.put("COL_PASSWORD", visiteur.getPassword());
        db.insert("Visiteur", null, Values);
        db.close();
    }

    public List<Visiteur> getAllVisiteur() {
        // array of columns to fetch
        String[] columns = {
                COL_ID,
                COL_NOM,
                COL_PRENOM,
                COL_LOGIN,
                COL_PASSWORD
        };

        String sortOrder = COL_NOM + " ASC";
        List<Visiteur> VisiteurList = new ArrayList<Visiteur>();

        SQLiteDatabase db = this.getReadableDatabase();

        /** query the Visiteur table
         * Here query function is used to fetch records from Visiteur table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT Visiteur_id,Visiteur_name,Visiteur_email,Visiteur_password FROM Visiteur ORDER BY Visiteur_name;
         */
        Cursor cursor = db.query(TABLE_VISITEUR, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Visiteur Visiteur = new Visiteur(1,null,null,null,null);
                Visiteur.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
                Visiteur.setNom(cursor.getString(cursor.getColumnIndex(COL_NOM)));
                Visiteur.setPrenom(cursor.getString(cursor.getColumnIndex(COL_PRENOM)));
                Visiteur.setLogin(cursor.getString(cursor.getColumnIndex(COL_LOGIN)));
                Visiteur.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
                // Adding Visiteur record to list
                VisiteurList.add(Visiteur);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return Visiteur list
        return VisiteurList;
    }


    public void updateVisiteur(Visiteur visiteur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        Values.put("COL_NOM ", Visiteur.getNom());
        Values.put("COL_PRENOM", Visiteur.getPrenom());
        Values.put("COL_LOGIN", Visiteur.getLogin());
        Values.put("COL_PASSWORD", Visiteur.getPassword());
        db.update(TABLE_VISITEUR, Values, COL_ID + " = ?",
                new String[]{String.valueOf(Visiteur.getId())});
        db.close();
    }

    public void deleteVisiteur(Visiteur visiteur) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VISITEUR, COL_ID + " = ?",
                new String[]{String.valueOf(Visiteur.getId())});
        db.close();
    }

    public Visiteur Authenticate(Visiteur user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VISITEUR,// Selecting Table
                new String[]{COL_ID, COL_NOM, COL_PRENOM,COL_LOGIN,COL_PASSWORD},//Selecting columns want to query
                COL_LOGIN + "=?",
                new String[]{Visiteur.getLogin()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            Visiteur user1 = new Visiteur(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4));

            //Match both passwords check they are same or not
            if (Visiteur.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean loginExists(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_VISITEUR,// Selecting Table
                new String[]{COL_ID, COL_NOM, COL_PRENOM,COL_LOGIN,COL_PASSWORD},//Selecting columns want to query
                COL_LOGIN + "=?",
                new String[]{login},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

}

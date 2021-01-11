package cat.itb.readbooks.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;
    private String status;
    private float stars;

    public Book(String title, String author, String status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        status = in.readString();
        stars = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(status);
        dest.writeFloat(stars);
    }
}

package org.ktachibana.cloudemoji.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class holding information and a list of categories
 */
public class Source implements Parcelable {
    public static Creator<Source> CREATOR = new Creator<Source>() {
        public Source createFromParcel(Parcel source) {
            return new Source(source);
        }

        public Source[] newArray(int size) {
            return new Source[size];
        }
    };
    private List<String> information;
    private List<Category> categories;

    public Source(ArrayList<String> information, List<Category> categories) {
        this.information = information;
        this.categories = categories;
    }

    private Source(Parcel in) {
        in.readStringList(information);
        in.readTypedList(categories, Category.CREATOR);
    }

    public List<String> getInformation() {
        return information;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(information);
        dest.writeTypedList(categories);
    }
}

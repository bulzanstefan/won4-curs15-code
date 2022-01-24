package ro.fasttrackit.curs15;

public record Quote(int id, String author, String quote, FavouriteFlag favourite) {
    public Quote(int id, String author, String quote) {
        this(id, author, quote, new FavouriteFlag());
    }

    public void setFavourite(boolean value) {
        this.favourite.setFavourite(value);
    }
}
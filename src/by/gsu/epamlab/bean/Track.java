package by.gsu.epamlab.bean;

public class Track implements Comparable<Track> {

    private int    id;
    private String name;
    private String image;
    private String url;
    private int    rating;

    public Track() {
        super();
    }

    public Track(final int id, final String name, final String image, final String url,
            final int rating) {
        init(id, name, url, rating);
        this.image = image;
    }

    public Track(final int id, final String name, final String url, final int rating) {
        init(id, name, url, rating);
        image = "";
    }

    private void init(final int id, final String name, final String url, final int rating) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Track other = (Track) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Track track) {
        return name.compareTo(track.getName());
    }

}

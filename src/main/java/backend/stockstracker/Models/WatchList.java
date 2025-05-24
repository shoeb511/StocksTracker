package backend.stockstracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class WatchList extends BaseModel {

    private String name;
    private String description;

    private long userId;

    @OneToMany(mappedBy = "watchList")
    private List<StckWatchList> stckWatchList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
